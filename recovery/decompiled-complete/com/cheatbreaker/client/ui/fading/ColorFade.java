package com.cheatbreaker.client.ui.fading;

import com.cheatbreaker.client.network.CustomPayloadSender;
import java.awt.Color;
import net.minecraft.server.MinecraftServer;

public class ColorFade extends ExponentialFade {
   public CustomPayloadSender field_0004;
   public MinecraftServer field_0002;
   public Color field_0005;
   public boolean field_0006;
   public int field_0000;
   public Color field_0001;
   public int field_0003;

   public ColorFade(long var1, int var3, int var4) {
      super(var1);
      this.field_0003 = var3;
      this.field_0000 = var4;
   }

   public void method_25067(int var1) {
      this.field_0003 = var1;
   }

   public ColorFade(int var1, int var2) {
      this(579634431L & 202506415L, var1, var2);
   }

   public void method_25068(int var1) {
      this.field_0000 = var1;
   }

   public Color method_25066(boolean var1) {
      Color var2 = new Color(var1 ? this.field_0000 : this.field_0003, true);
      if (var1 && !this.field_0006) {
         this.field_0006 = true;
         this.field_0001 = new Color(this.field_0003, true);
         this.field_0005 = new Color(this.field_0000, true);
         this.method_20200();
      } else if (this.field_0006 && !var1) {
         this.field_0006 = false;
         this.field_0001 = new Color(this.field_0000, true);
         this.field_0005 = new Color(this.field_0003, true);
         this.method_20200();
      }

      if (this.method_21233()) {
         float var3 = super.method_21227();
         int var4 = (int)Math.abs(var3 * this.field_0005.getRed() + (1.0F - var3) * this.field_0001.getRed());
         int var5 = (int)Math.abs(var3 * this.field_0005.getGreen() + (1.0F - var3) * this.field_0001.getGreen());
         int var6 = (int)Math.abs(var3 * this.field_0005.getBlue() + (1.0F - var3) * this.field_0001.getBlue());
         int var7 = (int)Math.abs(var3 * this.field_0005.getAlpha() + (1.0F - var3) * this.field_0001.getAlpha());
         var2 = new Color(var4, var5, var6, var7);
      }

      return var2;
   }
}
