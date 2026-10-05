package net.minecraft.tileentity;

import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.event.ClickEvent;
import recovered.unidentified.UnidentifiedClass1927;

public class TileEntityBeacon$BeamSegment {
   public UnidentifiedClass1927 field_0003;
   public int height;
   public float[] field_0002;
   public ClickEvent field_0004;
   public GuiGameOver field_0000;
   public ItemModelGenerator field_0001;

   public int getHeight() {
      return this.height;
   }

   public void incrementHeight() {
      this.height++;
   }

   public TileEntityBeacon$BeamSegment(float[] var1) {
      this.field_0002 = var1;
      this.height = 1;
   }

   public float[] method_28192() {
      return this.field_0002;
   }
}
