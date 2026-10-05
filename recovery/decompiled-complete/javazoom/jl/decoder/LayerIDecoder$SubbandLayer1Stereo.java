package javazoom.jl.decoder;

import io.netty.channel.ChannelOutboundBuffer$2;
import net.optifine.TextureAnimation;
import org.apache.log4j.helpers.OptionConverter;

public class LayerIDecoder$SubbandLayer1Stereo extends LayerIDecoder$SubbandLayer1 {
   public float channel2_offset;
   public ChannelOutboundBuffer$2 __junk9162634693486820110;
   public float channel2_sample;
   public float channel2_factor;
   public float channel2_scalefactor;
   public TextureAnimation __junk1697008536440404886;
   public int channel2_allocation;
   public int channel2_samplelength;
   public OptionConverter __junk389645930768061091;

   @Override
   public boolean put_next_sample(int var1, SynthesisFilter var2, SynthesisFilter var3) {
      super.put_next_sample(var1, var2, var3);
      if (this.channel2_allocation != 0 && var1 != 1) {
         float var4 = (this.channel2_sample * this.channel2_factor + this.channel2_offset) * this.channel2_scalefactor;
         if (var1 == 0) {
            var3.input_sample(var4, this.subbandnumber);
         } else {
            var2.input_sample(var4, this.subbandnumber);
         }
      }

      return true;
   }

   public LayerIDecoder$SubbandLayer1Stereo(int var1) {
      super(var1);
   }

   @Override
   public void read_scalefactor(Bitstream var1, Header var2) {
      if (this.allocation != 0) {
         this.scalefactor = scalefactors[var1.get_bits(6)];
      }

      if (this.channel2_allocation != 0) {
         this.channel2_scalefactor = scalefactors[var1.get_bits(6)];
      }
   }

   @Override
   public void read_allocation(Bitstream var1, Header var2, Crc16 var3) {
      this.allocation = var1.get_bits(4);
      this.channel2_allocation = var1.get_bits(4);
      if (var3 != null) {
         var3.add_bits(this.allocation, 4);
         var3.add_bits(this.channel2_allocation, 4);
      }

      if (this.allocation != 0) {
         this.samplelength = this.allocation + 1;
         this.factor = table_factor[this.allocation];
         this.offset = table_offset[this.allocation];
      }

      if (this.channel2_allocation != 0) {
         this.channel2_samplelength = this.channel2_allocation + 1;
         this.channel2_factor = table_factor[this.channel2_allocation];
         this.channel2_offset = table_offset[this.channel2_allocation];
      }
   }

   @Override
   public boolean read_sampledata(Bitstream var1) {
      boolean var2 = super.read_sampledata(var1);
      if (this.channel2_allocation != 0) {
         this.channel2_sample = var1.get_bits(this.channel2_samplelength);
      }

      return var2;
   }
}
