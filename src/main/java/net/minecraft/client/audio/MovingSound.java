package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public abstract class MovingSound extends PositionedSound implements ITickableSound {
   public boolean donePlaying = false;

   @Override
   public boolean isDonePlaying() {
      return this.donePlaying;
   }

   public MovingSound(ResourceLocation var1) {
      super(var1);
   }
}
