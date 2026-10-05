package net.optifine.shaders;

import io.netty.channel.sctp.oio.OioSctpChannel;
import java.nio.IntBuffer;
import java.util.Arrays;

public class FlipTextures {
   public int indexFlipped;
   public IntBuffer textures;
   public boolean[] changed;
   public OioSctpChannel field_0003;
   public boolean[] flips;

   public int getB(int var1) {
      return this.get(var1, !this.flips[var1]);
   }

   public int getA(int var1) {
      return this.get(var1, this.flips[var1]);
   }

   public void reset() {
      Arrays.fill(this.flips, false);
      Arrays.fill(this.changed, false);
   }

   public int get(int var1, boolean var2) {
      int var3 = var2 ? this.indexFlipped : 0;
      return this.textures.get(var3 + var1);
   }

   public boolean isChanged(int var1) {
      return this.changed[var1];
   }

   public FlipTextures(IntBuffer var1, int var2) {
      this.textures = var1;
      this.indexFlipped = var2;
      this.flips = new boolean[var1.capacity()];
      this.changed = new boolean[var1.capacity()];
   }

   public void flip(int var1) {
      this.flips[var1] = !this.flips[var1];
      this.changed[var1] = true;
   }
}
