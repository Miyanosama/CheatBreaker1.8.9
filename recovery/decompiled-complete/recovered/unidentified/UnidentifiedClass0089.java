package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.util.internal.logging.Log4JLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class UnidentifiedClass0089 {
   public int field_0014;
   public Log4JLogger field_0017;
   public int field_0003;
   public int field_0007;
   public double[] field_0010;
   public float field_0016;
   public float field_0002;
   public boolean field_0001;
   public int field_0004;
   public int field_0000;
   public long field_0015;
   public int field_0011;
   public Minecraft field_0013;
   public int field_0009;
   public float field_0018;
   public int field_0012;
   public int field_0006;
   public String field_0005;
   public int field_0008;

   public void method_00750() {
      int var1;
      if (this.field_0001 && (var1 = Mouse.getEventDWheel()) != 0) {
         var1 = var1 > 0 ? -1 : 1;
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0121.getValue()) {
            this.field_0012 = var1;
            this.field_0000 = 0;
         } else {
            this.field_0018 = this.field_0018 + var1 * this.field_0007 / 2;
         }
      }
   }

   public UnidentifiedClass0089(Minecraft var1, int var2, int var3, int var4, int var5, int var6, String var7) {
      this.field_0013 = var1;
      this.field_0011 = var4;
      this.field_0006 = var5;
      this.field_0014 = var2;
      this.field_0004 = var3;
      this.field_0009 = var2 + var4;
      this.field_0008 = var3 + var5;
      this.field_0007 = var6;
      this.field_0005 = var7;
      this.field_0003 = -1;
      this.field_0000 = -1;
      this.field_0016 = -2.0F;
      this.field_0010 = new double[]{
         5.088448,
         4.809692672,
         3.4292885120000003,
         3.268147903999999,
         2.697228288,
         2.019487744,
         1.8882322560000002,
         1.6936698879999996,
         1.4352491520000008,
         1.2045501440000006,
         0.7097322879999997,
         0.5842770560000003,
         0.5043583360000001,
         0.37950342400000014,
         0.282300416,
         0.21170873600000029,
         0.09733600000000031,
         0.08991539199999998,
         0.06209913599999961,
         0.030371328000000197,
         0.01452678400000007,
         0.006229504000000219,
         0.0017279999999999518
      };
   }

   public abstract int method_00746();

   public abstract void method_00749(int var1, boolean var2);

   public abstract void method_00751();

   public abstract void method_00748(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7);

   public void method_00744() {
      int var1 = this.method_00745() - (this.field_0008 - this.field_0004 - 4);
      if (var1 < 0) {
         var1 /= 2;
      }

      if (this.field_0018 > var1) {
         this.field_0018 = var1;
      }

      if (this.field_0018 < 0.0F) {
         this.field_0018 = 0.0F;
      }
   }

   public void method_00747(int var1, int var2) {
      this.method_00751();
      this.field_0001 = this.field_0014 <= var1 && var1 <= this.field_0014 + this.field_0011 && this.field_0004 <= var2 && var2 <= this.field_0008;
      int var4 = this.method_00746();
      byte var5 = 6;
      int var6 = this.field_0014 + this.field_0011;
      int var7 = var6 - 6;
      int var8 = var7 - 1;
      int var9 = this.field_0008 - this.field_0004;
      byte var10 = 4;
      if (Mouse.isButtonDown(0)) {
         if (this.field_0016 == -1.0F) {
            if (this.field_0001) {
               int var11 = var2 - this.field_0004 + (int)this.field_0018 - 4;
               int var12 = var11 / this.field_0007;
               if (0 <= var12 && var12 < var4 && var1 <= var8 && 0 <= var11) {
                  this.method_00749(var12, var12 == this.field_0003 && System.currentTimeMillis() - this.field_0015 < (590332L & 1740502455524808180L));
                  this.field_0003 = this.field_0003 == var12 ? -1 : var12;
                  this.field_0015 = System.currentTimeMillis();
               }

               if (var7 <= var1 && var1 <= var6) {
                  this.field_0002 = -1.0F;
                  int var13 = this.method_00745() - var9 - 4;
                  if (var13 < 1) {
                     var13 = 1;
                  }

                  int var3;
                  if ((var3 = (int)((float)(var9 * var9) / this.method_00745())) < 32) {
                     var3 = 32;
                  }

                  if (var3 > var9 - 8) {
                     var3 = var9 - 8;
                  }

                  this.field_0002 /= (float)(var9 - var3) / var13;
               } else {
                  this.field_0002 = 1.0F;
               }

               this.field_0016 = var2;
            } else {
               this.field_0016 = -2.0F;
            }
         } else if (this.field_0016 >= 0.0F) {
            this.field_0018 = this.field_0018 - (var2 - this.field_0016) * this.field_0002;
            this.field_0016 = var2;
         }
      } else {
         if (this.field_0000 != -1) {
            this.field_0018 = (float)(this.field_0018 + this.field_0010[this.field_0000] * this.field_0012 * 2.0);
            this.field_0000++;
            if (this.field_0000 >= this.field_0010.length) {
               this.field_0000 = -1;
            }
         }

         this.field_0016 = -1.0F;
      }

      this.method_00744();
      int var18 = this.field_0004 + 2 - (int)this.field_0018;

      for (int var19 = 0; var19 < var4; var19++) {
         int var21 = var18 + var19 * this.field_0007;
         int var14 = this.field_0007 - 4;
         int var15 = var21 + var14 + 2;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         UnidentifiedClass4676.method_28200(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(3042);
         GL11.glPushMatrix();
         GL11.glEnable(3089);
         ScaledResolution var16 = new ScaledResolution(this.field_0013);
         RenderUtil.method_22062(this.field_0014, this.field_0004 + 1, this.field_0009, this.field_0008, var16);
         this.method_00748(var19, var8, var21, var14, var1, var2, this.field_0014 <= var1 && var1 < var8 && var21 - 2 <= var2 && var2 < var15);
         GL11.glDisable(3089);
         GL11.glPopMatrix();
      }

      int var20 = this.method_00745() - var9 - 4;
      if (var20 > 0) {
         int var22 = var9 * var9 / this.method_00745();
         if (var22 < 32) {
            var22 = 32;
         }

         if (var22 > var9 - 8) {
            var22 = var9 - 8;
         }

         int var17;
         if ((var17 = (int)this.field_0018 * (var9 - var22) / var20 + this.field_0004) < this.field_0004) {
            var17 = this.field_0004;
         }

         Gui.a(var7, var17, var6, var17 + var22, -4144960);
      }

      UnidentifiedClass4676.method_28201(7424);
      GL11.glEnable(3553);
      GL11.glEnable(3008);
      Gui.field_0003 = 1.0F;
      this.field_0013.ingameGUI.method_00889(this.field_0014, this.field_0004, var6, this.field_0004 + 5, -16777216, 0);
      this.field_0013.ingameGUI.method_00889(this.field_0014, this.field_0008 - 5, var6, this.field_0008, 0, -16777216);
      Gui.field_0003 = -90.0F;
   }

   public int method_00745() {
      return this.method_00746() * this.field_0007;
   }
}
