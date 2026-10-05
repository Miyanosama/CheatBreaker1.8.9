package net.minecraft.client.renderer.chunk;

import com.cheatbreaker.client.CheatBreaker$1;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.util.Util;
import net.minecraft.world.chunk.Chunk$2;
import org.apache.log4j.pattern.ClassNamePatternConverter;

public class ChunkCompileTaskGenerator {
   public boolean finished;
   public ChunkCompileTaskGenerator$Status status;
   public Util field_0004;
   public RegionRenderCacheBuilder regionRenderCacheBuilder;
   public Chunk$2 field_0001;
   public ChunkCompileTaskGenerator$Type type;
   public RenderChunk renderChunk;
   public ClassNamePatternConverter field_0007;
   public ReentrantLock lock = new ReentrantLock();
   public CheatBreaker$1 field_0011;
   public List<Runnable> listFinishRunnables = Lists.newArrayList();
   public CompiledChunk compiledChunk;

   public RenderChunk getRenderChunk() {
      return this.renderChunk;
   }

   public CompiledChunk getCompiledChunk() {
      return this.compiledChunk;
   }

   public ChunkCompileTaskGenerator(RenderChunk var1, ChunkCompileTaskGenerator$Type var2) {
      this.status = ChunkCompileTaskGenerator$Status.PENDING;
      this.renderChunk = var1;
      this.type = var2;
   }

   public void setStatus(ChunkCompileTaskGenerator$Status var1) {
      this.lock.lock();

      try {
         this.status = var1;
      } finally {
         this.lock.unlock();
      }
   }

   public void setCompiledChunk(CompiledChunk var1) {
      this.compiledChunk = var1;
   }

   public ChunkCompileTaskGenerator$Status getStatus() {
      return this.status;
   }

   public ChunkCompileTaskGenerator$Type getType() {
      return this.type;
   }

   public ReentrantLock getLock() {
      return this.lock;
   }

   public void finish() {
      this.lock.lock();

      try {
         if (this.type == ChunkCompileTaskGenerator$Type.REBUILD_CHUNK && this.status != ChunkCompileTaskGenerator$Status.DONE) {
            this.renderChunk.setNeedsUpdate(true);
         }

         this.finished = true;
         this.status = ChunkCompileTaskGenerator$Status.DONE;

         for (Runnable var2 : this.listFinishRunnables) {
            var2.run();
         }
      } finally {
         this.lock.unlock();
      }
   }

   public void setRegionRenderCacheBuilder(RegionRenderCacheBuilder var1) {
      this.regionRenderCacheBuilder = var1;
   }

   public void addFinishRunnable(Runnable var1) {
      this.lock.lock();

      try {
         this.listFinishRunnables.add(var1);
         if (this.finished) {
            var1.run();
         }
      } finally {
         this.lock.unlock();
      }
   }

   public RegionRenderCacheBuilder getRegionRenderCacheBuilder() {
      return this.regionRenderCacheBuilder;
   }

   public boolean isFinished() {
      return this.finished;
   }
}
