package javazoom.jl.decoder;

import io.netty.util.UniqueName;
import net.minecraft.block.properties.PropertyEnum;
import org.java_websocket.AbstractWrappedByteChannel;

public class Equalizer {
   public PropertyEnum __junk755025132008056876;
   public static int BANDS;
   public static Equalizer PASS_THRU_EQ = new Equalizer();
   public float[] settings = new float[32];
   public UniqueName __junk3419742867674985607;
   public AbstractWrappedByteChannel __junk3005286445242759347;
   public static float BAND_NOT_PRESENT;

   public float setBand(int var1, float var2) {
      float var3 = 0.0F;
      if (var1 >= 0 && var1 < 32) {
         var3 = this.settings[var1];
         this.settings[var1] = this.limit(var2);
      }

      return var3;
   }

   public int getBandCount() {
      return this.settings.length;
   }

   public Equalizer(Equalizer$EQFunction var1) {
      this.setFrom(var1);
   }

   public Equalizer() {
   }

   public float getBandFactor(float var1) {
      return var1 == Float.NEGATIVE_INFINITY ? 0.0F : (float)Math.pow(2.0, var1);
   }

   public float[] getBandFactors() {
      float[] var1 = new float[32];
      int var2 = 0;

      for (byte var3 = 32; var2 < var3; var2++) {
         var1[var2] = this.getBandFactor(this.settings[var2]);
      }

      return var1;
   }

   public void setFrom(Equalizer var1) {
      if (var1 != this) {
         this.setFrom(var1.settings);
      }
   }

   public void setFrom(float[] var1) {
      this.reset();
      int var2 = var1.length > 32 ? 32 : var1.length;

      for (int var3 = 0; var3 < var2; var3++) {
         this.settings[var3] = this.limit(var1[var3]);
      }
   }

   public Equalizer(float[] var1) {
      this.setFrom(var1);
   }

   public void reset() {
      for (int var1 = 0; var1 < 32; var1++) {
         this.settings[var1] = 0.0F;
      }
   }

   public float limit(float var1) {
      if (var1 == Float.NEGATIVE_INFINITY) {
         return var1;
      } else if (var1 > 1.0F) {
         return 1.0F;
      } else {
         return var1 < -1.0F ? -1.0F : var1;
      }
   }

   public float getBand(int var1) {
      float var2 = 0.0F;
      if (var1 >= 0 && var1 < 32) {
         var2 = this.settings[var1];
      }

      return var2;
   }

   public void setFrom(Equalizer$EQFunction var1) {
      this.reset();
      byte var2 = 32;

      for (int var3 = 0; var3 < var2; var3++) {
         this.settings[var3] = this.limit(var1.getBand(var3));
      }
   }
}
