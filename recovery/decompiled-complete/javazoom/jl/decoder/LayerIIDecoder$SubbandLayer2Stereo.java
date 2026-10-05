package javazoom.jl.decoder;

import net.minecraft.client.resources.ResourcePackFileNotFoundException;
import recovered.unidentified.UnidentifiedClass3218;

public class LayerIIDecoder$SubbandLayer2Stereo extends LayerIIDecoder$SubbandLayer2 {
   public float[] channel2_c;
   public float channel2_scalefactor3;
   public int[] channel2_codelength = new int[]{0};
   public float[] channel2_d;
   public int channel2_scfsi;
   public ResourcePackFileNotFoundException __junk5199525536614945498;
   public int channel2_allocation;
   public float[] channel2_factor = new float[]{0.0F};
   public float[] channel2_samples;
   public float channel2_scalefactor2;
   public UnidentifiedClass3218 __junk3014184696478289283;
   public float channel2_scalefactor1;

   @Override
   public void read_allocation(Bitstream var1, Header var2, Crc16 var3) {
      int var4 = this.get_allocationlength(var2);
      this.allocation = var1.get_bits(var4);
      this.channel2_allocation = var1.get_bits(var4);
      if (var3 != null) {
         var3.add_bits(this.allocation, var4);
         var3.add_bits(this.channel2_allocation, var4);
      }
   }

   @Override
   public boolean put_next_sample(int var1, SynthesisFilter var2, SynthesisFilter var3) {
      boolean var4 = super.put_next_sample(var1, var2, var3);
      if (this.channel2_allocation != 0 && var1 != 1) {
         float var5 = this.channel2_samples[this.samplenumber - 1];
         if (this.groupingtable[1] == null) {
            var5 = (var5 + this.channel2_d[0]) * this.channel2_c[0];
         }

         if (this.groupnumber <= 4) {
            var5 *= this.channel2_scalefactor1;
         } else if (this.groupnumber <= 8) {
            var5 *= this.channel2_scalefactor2;
         } else {
            var5 *= this.channel2_scalefactor3;
         }

         if (var1 == 0) {
            var3.input_sample(var5, this.subbandnumber);
         } else {
            var2.input_sample(var5, this.subbandnumber);
         }
      }

      return var4;
   }

   @Override
   public void read_scalefactor_selection(Bitstream var1, Crc16 var2) {
      if (this.allocation != 0) {
         this.scfsi = var1.get_bits(2);
         if (var2 != null) {
            var2.add_bits(this.scfsi, 2);
         }
      }

      if (this.channel2_allocation != 0) {
         this.channel2_scfsi = var1.get_bits(2);
         if (var2 != null) {
            var2.add_bits(this.channel2_scfsi, 2);
         }
      }
   }

   @Override
   public boolean read_sampledata(Bitstream var1) {
      boolean var2 = super.read_sampledata(var1);
      if (this.channel2_allocation != 0) {
         if (this.groupingtable[1] != null) {
            int var3 = var1.get_bits(this.channel2_codelength[0]);
            var3 += var3 << 1;
            float[] var4 = this.channel2_samples;
            float[] var5 = this.groupingtable[1];
            int var6 = 0;
            var4[var6] = var5[var3];
            int var7 = var3 + 1;
            var4[++var6] = var5[var7];
            var7++;
            var4[++var6] = var5[var7];
         } else {
            this.channel2_samples[0] = (float)(var1.get_bits(this.channel2_codelength[0]) * this.channel2_factor[0] - 1.0);
            this.channel2_samples[1] = (float)(var1.get_bits(this.channel2_codelength[0]) * this.channel2_factor[0] - 1.0);
            this.channel2_samples[2] = (float)(var1.get_bits(this.channel2_codelength[0]) * this.channel2_factor[0] - 1.0);
         }
      }

      return var2;
   }

   @Override
   public void read_scalefactor(Bitstream var1, Header var2) {
      super.read_scalefactor(var1, var2);
      if (this.channel2_allocation != 0) {
         switch (this.channel2_scfsi) {
            case 0:
               this.channel2_scalefactor1 = scalefactors[var1.get_bits(6)];
               this.channel2_scalefactor2 = scalefactors[var1.get_bits(6)];
               this.channel2_scalefactor3 = scalefactors[var1.get_bits(6)];
               break;
            case 1:
               this.channel2_scalefactor1 = this.channel2_scalefactor2 = scalefactors[var1.get_bits(6)];
               this.channel2_scalefactor3 = scalefactors[var1.get_bits(6)];
               break;
            case 2:
               this.channel2_scalefactor1 = this.channel2_scalefactor2 = this.channel2_scalefactor3 = scalefactors[var1.get_bits(6)];
               break;
            case 3:
               this.channel2_scalefactor1 = scalefactors[var1.get_bits(6)];
               this.channel2_scalefactor2 = this.channel2_scalefactor3 = scalefactors[var1.get_bits(6)];
         }

         this.prepare_sample_reading(var2, this.channel2_allocation, 1, this.channel2_factor, this.channel2_codelength, this.channel2_c, this.channel2_d);
      }
   }

   public LayerIIDecoder$SubbandLayer2Stereo(int var1) {
      super(var1);
      this.channel2_c = new float[]{0.0F};
      this.channel2_d = new float[]{0.0F};
      this.channel2_samples = new float[3];
   }
}
