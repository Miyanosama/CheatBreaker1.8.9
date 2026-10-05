package net.minecraft.client.renderer.chunk;

import com.google.common.util.concurrent.FutureCallback;
import java.util.List;
import java.util.concurrent.CancellationException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;
import net.minecraft.crash.CrashReport;
import net.optifine.util.NativeMemory$1;

public class ChunkRenderWorker$2 implements FutureCallback<List<Object>> {
   public LayerSnowmanHead field_0004;
   public NativeMemory$1 field_0003;

   public void onSuccess(List<Object> var1) {
      ChunkRenderWorker.access$000(this.field_178483_c, this.field_178484_a);
      this.field_178484_a.getLock().lock();

      try {
         if (this.field_178484_a.getStatus() != ChunkCompileTaskGenerator$Status.UPLOADING) {
            if (!this.field_178484_a.isFinished()) {
               ChunkRenderWorker.access$100()
                  .warn("Chunk render task was " + this.field_178484_a.getStatus() + " when I expected it to be uploading; aborting task");
            }

            return;
         }

         this.field_178484_a.setStatus(ChunkCompileTaskGenerator$Status.DONE);
      } finally {
         this.field_178484_a.getLock().unlock();
      }

      this.field_178484_a.getRenderChunk().setCompiledChunk(this.field_178482_b);
   }

   public void onFailure(Throwable var1) {
      ChunkRenderWorker.access$000(this.field_178483_c, this.field_178484_a);
      if (!(var1 instanceof CancellationException) && !(var1 instanceof InterruptedException)) {
         Minecraft.getMinecraft().crashed(CrashReport.makeCrashReport(var1, "Rendering chunk"));
      }
   }

   public ChunkRenderWorker$2(ChunkRenderWorker var1, ChunkCompileTaskGenerator var2, CompiledChunk var3) {
      this.field_178483_c = var1;
      this.field_178484_a = var2;
      this.field_178482_b = var3;
      super();
   }
}
