package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.netty.channel.epoll.AbstractEpollChannel$AbstractEpollUnsafe;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie$GroupData;
import net.minecraft.util.EnumWorldBlockLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkRenderWorker implements Runnable {
   public static Logger LOGGER = LogManager.getLogger();
   public ChunkRenderDispatcher chunkRenderDispatcher;
   public RegionRenderCacheBuilder regionRenderCacheBuilder;
   public EntityZombie$GroupData field_0003;
   public AbstractEpollChannel$AbstractEpollUnsafe field_0000;

   public ChunkRenderWorker(ChunkRenderDispatcher var1) {
      this(var1, (RegionRenderCacheBuilder)null);
   }

   @Override
   public void run() {
      while (true) {
         try {
            this.processTask(this.chunkRenderDispatcher.getNextChunkUpdate());
         } catch (InterruptedException var3) {
            LOGGER.debug("Stopping due to interrupt");
            return;
         } catch (Throwable var4) {
            CrashReport var2 = CrashReport.makeCrashReport(var4, "Batching chunks");
            Minecraft.getMinecraft().crashed(Minecraft.getMinecraft().addGraphicsAndWorldToCrashReport(var2));
            return;
         }
      }
   }

   public RegionRenderCacheBuilder getRegionRenderCacheBuilder() {
      return this.regionRenderCacheBuilder != null ? this.regionRenderCacheBuilder : this.chunkRenderDispatcher.allocateRenderBuilder();
   }

   public ChunkRenderWorker(ChunkRenderDispatcher var1, RegionRenderCacheBuilder var2) {
      this.chunkRenderDispatcher = var1;
      this.regionRenderCacheBuilder = var2;
   }

   public void freeRenderBuilder(ChunkCompileTaskGenerator var1) {
      if (this.regionRenderCacheBuilder == null) {
         this.chunkRenderDispatcher.freeRenderBuilder(var1.getRegionRenderCacheBuilder());
      }
   }

   public void processTask(ChunkCompileTaskGenerator var1) {
      var1.getLock().lock();

      try {
         if (var1.getStatus() != ChunkCompileTaskGenerator$Status.PENDING) {
            if (!var1.isFinished()) {
               LOGGER.warn("Chunk render task was " + var1.getStatus() + " when I expected it to be pending; ignoring task");
            }

            return;
         }

         var1.setStatus(ChunkCompileTaskGenerator$Status.COMPILING);
      } finally {
         var1.getLock().unlock();
      }

      Entity var2 = Minecraft.getMinecraft().getRenderViewEntity();
      if (var2 == null) {
         var1.finish();
      } else {
         var1.setRegionRenderCacheBuilder(this.getRegionRenderCacheBuilder());
         float var3 = (float)var2.s;
         float var4 = (float)var2.t + var2.getEyeHeight();
         float var5 = (float)var2.u;
         ChunkCompileTaskGenerator$Type var6 = var1.getType();
         if (var6 == ChunkCompileTaskGenerator$Type.REBUILD_CHUNK) {
            var1.getRenderChunk().rebuildChunk(var3, var4, var5, var1);
         } else if (var6 == ChunkCompileTaskGenerator$Type.RESORT_TRANSPARENCY) {
            var1.getRenderChunk().resortTransparency(var3, var4, var5, var1);
         }

         var1.getLock().lock();

         try {
            if (var1.getStatus() != ChunkCompileTaskGenerator$Status.COMPILING) {
               if (!var1.isFinished()) {
                  LOGGER.warn("Chunk render task was " + var1.getStatus() + " when I expected it to be compiling; aborting task");
               }

               this.freeRenderBuilder(var1);
               return;
            }

            var1.setStatus(ChunkCompileTaskGenerator$Status.UPLOADING);
         } finally {
            var1.getLock().unlock();
         }

         CompiledChunk var7 = var1.getCompiledChunk();
         ArrayList var8 = Lists.newArrayList();
         if (var6 == ChunkCompileTaskGenerator$Type.REBUILD_CHUNK) {
            for (EnumWorldBlockLayer var12 : EnumWorldBlockLayer.values()) {
               if (var7.isLayerStarted(var12)) {
                  var8.add(
                     this.chunkRenderDispatcher
                        .uploadChunk(var12, var1.getRegionRenderCacheBuilder().getWorldRendererByLayer(var12), var1.getRenderChunk(), var7)
                  );
               }
            }
         } else if (var6 == ChunkCompileTaskGenerator$Type.RESORT_TRANSPARENCY) {
            var8.add(
               this.chunkRenderDispatcher
                  .uploadChunk(
                     EnumWorldBlockLayer.TRANSLUCENT,
                     var1.getRegionRenderCacheBuilder().getWorldRendererByLayer(EnumWorldBlockLayer.TRANSLUCENT),
                     var1.getRenderChunk(),
                     var7
                  )
            );
         }

         ListenableFuture var19 = Futures.allAsList(var8);
         var1.addFinishRunnable(new ChunkRenderWorker$1(this, var19));
         Futures.addCallback(var19, new ChunkRenderWorker$2(this, var1, var7));
      }
   }
}
