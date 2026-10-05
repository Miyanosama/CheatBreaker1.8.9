package javazoom.jl.player.advanced;

import net.minecraft.client.Minecraft$16;
import net.minecraft.client.renderer.entity.RenderWolf;

public class jlap$InfoListener extends PlaybackListener {
   public Minecraft$16 __junk5684920603164101578;
   public RenderWolf __junk6668805252102920642;

   @Override
   public void playbackFinished(PlaybackEvent var1) {
      System.out.println("Play completed at frame " + var1.getFrame());
      System.exit(0);
   }

   public jlap$InfoListener(jlap var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void playbackStarted(PlaybackEvent var1) {
      System.out.println("Play started from frame " + var1.getFrame());
   }
}
