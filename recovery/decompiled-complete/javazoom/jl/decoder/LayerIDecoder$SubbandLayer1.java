package javazoom.jl.decoder;

import javax.vecmath.Color3b;

public class LayerIDecoder$SubbandLayer1 extends LayerIDecoder$Subband {
   public static float[] table_factor = new float[]{
      0.0F,
      0.6666667F,
      0.2857143F,
      0.13333334F,
      0.06451613F,
      0.031746034F,
      0.015748031F,
      0.007843138F,
      0.0039138943F,
      0.0019550342F,
      9.770396E-4F,
      4.884005E-4F,
      2.4417043E-4F,
      1.2207776E-4F,
      6.103702E-5F
   };
   public float scalefactor;
   public float factor;
   public float offset;
   public Color3b __junk3478040921050500743;
   public int allocation;
   public static float[] table_offset = new float[]{
      0.0F,
      -0.6666667F,
      -0.8571429F,
      -0.9333334F,
      -0.9677419F,
      -0.98412704F,
      -0.992126F,
      -0.9960785F,
      -0.99804306F,
      -0.9990225F,
      -0.9995115F,
      -0.99975586F,
      -0.9998779F,
      -0.99993896F,
      -0.9999695F
   };
   public int samplenumber;
   public float sample;
   public int samplelength;
   public int subbandnumber;

   @Override
   public void read_scalefactor(Bitstream var1, Header var2) {
      if (this.allocation != 0) {
         this.scalefactor = scalefactors[var1.get_bits(6)];
      }
   }

   @Override
   public boolean read_sampledata(Bitstream var1) {
      if (this.allocation != 0) {
         this.sample = var1.get_bits(this.samplelength);
      }

      if (++this.samplenumber == 12) {
         this.samplenumber = 0;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void read_allocation(Bitstream var1, Header var2, Crc16 var3) {
      if ((this.allocation = var1.get_bits(4)) == 15) {
         throw new DecoderException(514, null);
      } else {
         if (var3 != null) {
            var3.add_bits(this.allocation, 4);
         }

         if (this.allocation != 0) {
            this.samplelength = this.allocation + 1;
            this.factor = table_factor[this.allocation];
            this.offset = table_offset[this.allocation];
         }
      }
   }

   public LayerIDecoder$SubbandLayer1(int var1) {
      this.subbandnumber = var1;
      this.samplenumber = 0;
   }

   @Override
   public boolean put_next_sample(int var1, SynthesisFilter var2, SynthesisFilter var3) {
      if (this.allocation != 0 && var1 != 2) {
         float var4 = (this.sample * this.factor + this.offset) * this.scalefactor;
         var2.input_sample(var4, this.subbandnumber);
      }

      return true;
   }
}
