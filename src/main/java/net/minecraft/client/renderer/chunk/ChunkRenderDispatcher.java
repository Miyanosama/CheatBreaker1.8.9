package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class ChunkRenderDispatcher {
   public List<RegionRenderCacheBuilder> listPausedBuilders;
   public BlockingQueue<ChunkCompileTaskGenerator> queueChunkUpdates;
   public static Logger logger = LogManager.getLogger();
   public List<ChunkRenderWorker> listThreadedWorkers = Lists.newArrayList();
   public int countRenderBuilders;
   public BlockingQueue<RegionRenderCacheBuilder> queueFreeRenderBuilders;
   public VertexBufferUploader vertexUploader;
   public ChunkRenderWorker renderWorker;
   public Queue<ListenableFutureTask<?>> queueChunkUploads;
   public WorldVertexBufferUploader worldVertexUploader;
   public static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Chunk Batcher %d").setDaemon(true).build();

   public ListenableFuture<Object> uploadChunk(final EnumWorldBlockLayer var1, final WorldRenderer var2, final RenderChunk var3, final CompiledChunk var4) {
      if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
         if (OpenGlHelper.useVbo()) {
            this.uploadVertexBuffer(var2, var3.getVertexBufferByLayer(var1.ordinal()));
         } else {
            this.uploadDisplayList(var2, ((ListedRenderChunk)var3).getDisplayList(var1, var4), var3);
         }

         var2.setTranslation(0.0, 0.0, 0.0);
         return Futures.immediateFuture(null);
      } else {
         ListenableFutureTask var5 = ListenableFutureTask.create(new Runnable() {
            @Override
            public void run() {
               ChunkRenderDispatcher.this.uploadChunk(var1, var2, var3, var4);
            }
         }, null);
         synchronized (this.queueChunkUploads) {
            this.queueChunkUploads.add(var5);
            return var5;
         }
      }
   }

   public boolean hasChunkUpdates() {
      return this.queueChunkUpdates.isEmpty() && this.queueChunkUploads.isEmpty();
   }

   public String getDebugInfo() {
      return String.format("pC: %03d, pU: %1d, aB: %1d", this.queueChunkUpdates.size(), this.queueChunkUploads.size(), this.queueFreeRenderBuilders.size());
   }

   public boolean updateChunkNow(RenderChunk var1) {
      var1.getLockCompileTask().lock();

      boolean var2;
      try {
         ChunkCompileTaskGenerator var3 = var1.makeCompileTaskChunk();

         try {
            this.renderWorker.processTask(var3);
         } catch (InterruptedException var8) {
         }

         var2 = true;
      } finally {
         var1.getLockCompileTask().unlock();
      }

      return var2;
   }

   public void pauseChunkUpdates() {
      while (this.listPausedBuilders.size() != this.countRenderBuilders) {
         try {
            this.runChunkUploads(Long.MAX_VALUE);
            RegionRenderCacheBuilder var1 = this.queueFreeRenderBuilders.poll(100L, TimeUnit.MILLISECONDS);
            if (var1 != null) {
               this.listPausedBuilders.add(var1);
            }
         } catch (InterruptedException var2) {
         }
      }
   }

   public void uploadDisplayList(WorldRenderer var1, int var2, RenderChunk var3) {
      GL11.glNewList(var2, 4864);
      GlStateManager.pushMatrix();
      var3.multModelviewMatrix();
      this.worldVertexUploader.draw(var1);
      GlStateManager.popMatrix();
      GL11.glEndList();
   }

   public void freeRenderBuilder(RegionRenderCacheBuilder var1) {
      this.queueFreeRenderBuilders.add(var1);
   }

   public ChunkCompileTaskGenerator getNextChunkUpdate() throws java.lang.InterruptedException {
      return this.queueChunkUpdates.take();
   }

   public boolean updateTransparencyLater(RenderChunk var1) {
      var1.getLockCompileTask().lock();

      boolean var5;
      try {
         final ChunkCompileTaskGenerator var3 = var1.makeCompileTaskTransparency();
         if (var3 == null) {
            return true;
         }

         var3.addFinishRunnable(new Runnable() {
            @Override
            public void run() {
               ChunkRenderDispatcher.this.queueChunkUpdates.remove(var3);
            }
         });
         boolean var4 = this.queueChunkUpdates.offer(var3);
         var5 = var4;
      } finally {
         var1.getLockCompileTask().unlock();
      }

      return var5;
   }

   public void resumeChunkUpdates() {
      this.queueFreeRenderBuilders.addAll(this.listPausedBuilders);
      this.listPausedBuilders.clear();
   }

   public void clearChunkUpdates() {
      while (!this.queueChunkUpdates.isEmpty()) {
         ChunkCompileTaskGenerator var1 = this.queueChunkUpdates.poll();
         if (var1 != null) {
            var1.finish();
         }
      }
   }

   public RegionRenderCacheBuilder allocateRenderBuilder() throws java.lang.InterruptedException {
      return this.queueFreeRenderBuilders.take();
   }

   public void uploadVertexBuffer(WorldRenderer var1, VertexBuffer var2) {
      this.vertexUploader.setVertexBuffer(var2);
      this.vertexUploader.draw(var1);
   }

   public ChunkRenderDispatcher(int var1) {
      this.queueChunkUpdates = Queues.newArrayBlockingQueue(100);
      this.worldVertexUploader = new WorldVertexBufferUploader();
      this.vertexUploader = new VertexBufferUploader();
      this.queueChunkUploads = Queues.newArrayDeque();
      this.listPausedBuilders = new ArrayList<>();
      int var2 = Math.max(1, (int)(Runtime.getRuntime().maxMemory() * 0.3) / 10485760);
      int var3 = Math.max(1, MathHelper.clamp_int(Runtime.getRuntime().availableProcessors() - 2, 1, var2 / 5));
      if (var1 < 0) {
         this.countRenderBuilders = MathHelper.clamp_int(var3 * 8, 1, var2);
      } else {
         this.countRenderBuilders = var1;
      }

      for (int var4 = 0; var4 < var3; var4++) {
         ChunkRenderWorker var5 = new ChunkRenderWorker(this);
         Thread var6 = threadFactory.newThread(var5);
         var6.start();
         this.listThreadedWorkers.add(var5);
      }

      this.queueFreeRenderBuilders = Queues.newArrayBlockingQueue(this.countRenderBuilders);

      for (int var7 = 0; var7 < this.countRenderBuilders; var7++) {
         this.queueFreeRenderBuilders.add(new RegionRenderCacheBuilder());
      }

      this.renderWorker = new ChunkRenderWorker(this, new RegionRenderCacheBuilder());
   }

   public boolean runChunkUploads(long var1) {
      boolean var3 = false;

      long var10;
      do {
         boolean var4 = false;
         ListenableFutureTask var5 = null;
         synchronized (this.queueChunkUploads) {
            var5 = this.queueChunkUploads.poll();
         }

         if (var5 != null) {
            var5.run();
            var4 = true;
            var3 = true;
         }

         if (var1 == 0L || !var4) {
            break;
         }

         var10 = var1 - System.nanoTime();
      } while (var10 >= 0L);

      return var3;
   }

   public boolean updateChunkLater(RenderChunk var1) {
      var1.getLockCompileTask().lock();

      boolean var2;
      try {
         final ChunkCompileTaskGenerator var3 = var1.makeCompileTaskChunk();
         var3.addFinishRunnable(new Runnable() {
            @Override
            public void run() {
               ChunkRenderDispatcher.this.queueChunkUpdates.remove(var3);
            }
         });
         boolean var4 = this.queueChunkUpdates.offer(var3);
         if (!var4) {
            var3.finish();
         }

         var2 = var4;
      } finally {
         var1.getLockCompileTask().unlock();
      }

      return var2;
   }

   public ChunkRenderDispatcher() {
      this(-1);
   }

   public void stopChunkUpdates() {
      this.clearChunkUpdates();

      while (this.runChunkUploads(0L)) {
      }

      ArrayList var1 = Lists.newArrayList();

      while (var1.size() != this.countRenderBuilders) {
         try {
            var1.add(this.allocateRenderBuilder());
         } catch (InterruptedException var3) {
         }
      }

      this.queueFreeRenderBuilders.addAll(var1);
   }
}
