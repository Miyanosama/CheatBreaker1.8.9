package javazoom.jl.decoder;

import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.optifine.shaders.uniform.ShaderUniform4i;

public class BitReserve {
   public int buf_bit_idx;
   public ShaderUniform4i __junk3444203027800923433;
   public int offset;
   public static int BUFSIZE_MASK;
   public int[] buf = new int[32768];
   public WorldGenAbstractTree __junk2784944974942972363;
   public GuiListExtended __junk3288631002099039619;
   public int buf_byte_idx;
   public GuiOptionButton __junk3403674889229638566;
   public static int BUFSIZE;
   public BiomeColorHelper __junk5815589422476491218;
   public int totbit;

   public void rewindNbytes(int var1) {
      int var2 = var1 << 3;
      this.totbit -= var2;
      this.buf_byte_idx -= var2;
      if (this.buf_byte_idx < 0) {
         this.buf_byte_idx += 32768;
      }
   }

   public void rewindNbits(int var1) {
      this.totbit -= var1;
      this.buf_byte_idx -= var1;
      if (this.buf_byte_idx < 0) {
         this.buf_byte_idx += 32768;
      }
   }

   public int hget1bit() {
      this.totbit++;
      int var1 = this.buf[this.buf_byte_idx];
      this.buf_byte_idx = this.buf_byte_idx + 1 & 32767;
      return var1;
   }

   public BitReserve() {
      this.offset = 0;
      this.totbit = 0;
      this.buf_byte_idx = 0;
   }

   public void hputbuf(int var1) {
      int var2 = this.offset;
      this.buf[var2++] = var1 & 128;
      this.buf[var2++] = var1 & 64;
      this.buf[var2++] = var1 & 32;
      this.buf[var2++] = var1 & 16;
      this.buf[var2++] = var1 & 8;
      this.buf[var2++] = var1 & 4;
      this.buf[var2++] = var1 & 2;
      this.buf[var2++] = var1 & 1;
      if (var2 == 32768) {
         this.offset = 0;
      } else {
         this.offset = var2;
      }
   }

   public int hsstell() {
      return this.totbit;
   }

   public int hgetbits(int var1) {
      this.totbit += var1;
      int var2 = 0;
      int var3 = this.buf_byte_idx;
      if (var3 + var1 < 32768) {
         while (var1-- > 0) {
            var2 <<= 1;
            var2 |= this.buf[var3++] != 0 ? 1 : 0;
         }
      } else {
         while (var1-- > 0) {
            var2 <<= 1;
            var2 |= this.buf[var3] != 0 ? 1 : 0;
            var3 = var3 + 1 & 32767;
         }
      }

      this.buf_byte_idx = var3;
      return var2;
   }
}
