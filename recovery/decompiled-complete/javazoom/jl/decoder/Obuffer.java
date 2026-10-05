package javazoom.jl.decoder;

import net.minecraft.client.model.ModelHorse;
import net.minecraft.world.GameRules$ValueType;

public abstract class Obuffer {
   public ModelHorse __junk2860727646177585390;
   public GameRules$ValueType __junk3153038608720052998;
   public static int MAXCHANNELS;
   public static int OBUFFERSIZE;

   public abstract void clear_buffer();

   public abstract void append(int var1, short var2);

   public void appendSamples(int var1, float[] var2) {
      int var4 = 0;

      while (var4 < 32) {
         short var3 = this.clip(var2[var4++]);
         this.append(var1, var3);
      }
   }

   public abstract void close();

   public short clip(float var1) {
      return var1 > 32767.0F ? 32767 : (var1 < -32768.0F ? -32768 : (short)var1);
   }

   public abstract void write_buffer(int var1);

   public abstract void set_stop_flag();
}
