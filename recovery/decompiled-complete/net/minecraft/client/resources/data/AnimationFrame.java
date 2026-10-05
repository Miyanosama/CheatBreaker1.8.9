package net.minecraft.client.resources.data;

import net.minecraft.stats.StatList;

public class AnimationFrame {
   public int frameIndex;
   public int frameTime;
   public StatList field_0000;

   public int getFrameIndex() {
      return this.frameIndex;
   }

   public int getFrameTime() {
      return this.frameTime;
   }

   public AnimationFrame(int var1, int var2) {
      this.frameIndex = var1;
      this.frameTime = var2;
   }

   public boolean hasNoTime() {
      return this.frameTime == -1;
   }

   public AnimationFrame(int var1) {
      this(var1, -1);
   }
}
