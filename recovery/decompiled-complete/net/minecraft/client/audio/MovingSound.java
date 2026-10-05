package net.minecraft.client.audio;

import net.minecraft.client.gui.GuiStreamIndicator;
import net.minecraft.client.renderer.GlStateManager$ColorMask;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import net.optifine.gui.GuiButtonOF;

public abstract class MovingSound extends PositionedSound implements ITickableSound {
   public ItemCameraTransforms field_0002;
   public GuiStreamIndicator field_0003;
   public boolean donePlaying = false;
   public GuiButtonOF field_0001;
   public GlStateManager$ColorMask field_0004;

   @Override
   public boolean isDonePlaying() {
      return this.donePlaying;
   }

   public MovingSound(ResourceLocation var1) {
      super(var1);
   }
}
