package net.minecraft.client.gui;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceMappingsToDoubleTask;
import junit.swingui.StatusLine;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.optifine.player.PlayerConfigurations;
import net.optifine.shaders.config.PropertyDefaultTrueFalse;

public class ScaledResolution {
   public double scaledHeightD;
   public PlayerConfigurations field_0007;
   public PropertyDefaultTrueFalse field_0003;
   public ConcurrentHashMapV8$MapReduceMappingsToDoubleTask field_0006;
   public int scaledHeight;
   public double scaledWidthD;
   public int scaleFactor;
   public int scaledWidth;
   public StatusLine field_0002;
   public BlockPortal field_0009;

   public ScaledResolution(Minecraft var1) {
      this.scaledWidth = var1.displayWidth;
      this.scaledHeight = var1.displayHeight;
      this.scaleFactor = 1;
      boolean var2 = var1.isUnicode();
      int var3 = var1.gameSettings.guiScale;
      if (var3 == 0) {
         var3 = 1000;
      }

      while (this.scaleFactor < var3 && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
         this.scaleFactor++;
      }

      if (var2 && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
         this.scaleFactor--;
      }

      this.scaledWidthD = (double)this.scaledWidth / this.scaleFactor;
      this.scaledHeightD = (double)this.scaledHeight / this.scaleFactor;
      this.scaledWidth = MathHelper.ceiling_double_int(this.scaledWidthD);
      this.scaledHeight = MathHelper.ceiling_double_int(this.scaledHeightD);
   }

   public int getScaleFactor() {
      return this.scaleFactor;
   }

   public int getScaledWidth() {
      return this.scaledWidth;
   }

   public double getScaledWidth_double() {
      return this.scaledWidthD;
   }

   public double getScaledHeight_double() {
      return this.scaledHeightD;
   }

   public int getScaledHeight() {
      return this.scaledHeight;
   }
}
