package javazoom.jl.decoder;

import io.netty.buffer.PoolThreadCache$SubPageMemoryRegionCache;
import net.minecraft.realms.RealmsServerStatusPinger$1;

public class LayerIDecoder$SubbandLayer1IntensityStereo extends LayerIDecoder$SubbandLayer1 {
   public float channel2_scalefactor;
   public RealmsServerStatusPinger$1 __junk145451486163871434;
   public PoolThreadCache$SubPageMemoryRegionCache __junk4167361039477249900;

   @Override
   public boolean put_next_sample(int var1, SynthesisFilter var2, SynthesisFilter var3) {
      if (this.allocation != 0) {
         this.sample = this.sample * this.factor + this.offset;
         if (var1 == 0) {
            float var4 = this.sample * this.scalefactor;
            float var5 = this.sample * this.channel2_scalefactor;
            var2.input_sample(var4, this.subbandnumber);
            var3.input_sample(var5, this.subbandnumber);
         } else if (var1 == 1) {
            float var6 = this.sample * this.scalefactor;
            var2.input_sample(var6, this.subbandnumber);
         } else {
            float var7 = this.sample * this.channel2_scalefactor;
            var2.input_sample(var7, this.subbandnumber);
         }
      }

      return true;
   }

   @Override
   public boolean read_sampledata(Bitstream var1) {
      return super.read_sampledata(var1);
   }

   @Override
   public void read_allocation(Bitstream var1, Header var2, Crc16 var3) {
      super.read_allocation(var1, var2, var3);
   }

   @Override
   public void read_scalefactor(Bitstream var1, Header var2) {
      if (this.allocation != 0) {
         this.scalefactor = scalefactors[var1.get_bits(6)];
         this.channel2_scalefactor = scalefactors[var1.get_bits(6)];
      }
   }

   public LayerIDecoder$SubbandLayer1IntensityStereo(int var1) {
      super(var1);
   }
}
