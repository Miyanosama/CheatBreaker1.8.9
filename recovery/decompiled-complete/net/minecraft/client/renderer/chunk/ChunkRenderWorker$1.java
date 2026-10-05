package net.minecraft.client.renderer.chunk;

import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.client.gui.achievement.GuiAchievements;

public class ChunkRenderWorker$1 implements Runnable {
   public GuiAchievements field_0001;

   @Override
   public void run() {
      this.field_0000.cancel(false);
   }

   public ChunkRenderWorker$1(ChunkRenderWorker var1, ListenableFuture var2) {
      this.field_0002 = var1;
      this.field_0000 = var2;
      super();
   }
}
