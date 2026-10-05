package javazoom.jl.decoder;

import com.cheatbreaker.client.event.type.TickEvent;
import io.netty.handler.codec.http.HttpHeaderDateFormat$HttpHeaderDateFormatObsolete2;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;

public class LayerIIDecoder$SubbandLayer2IntensityStereo extends LayerIIDecoder$SubbandLayer2 {
   public TickEvent __junk7616707080265276585;
   public float channel2_scalefactor3;
   public int channel2_scfsi;
   public WorldGeneratorBonusChest __junk5031393591154137418;
   public float channel2_scalefactor2;
   public HttpHeaderDateFormat$HttpHeaderDateFormatObsolete2 __junk2082220848723832349;
   public CloseWebSocketFrame __junk930349869134609052;
   public float channel2_scalefactor1;

   public LayerIIDecoder$SubbandLayer2IntensityStereo(int var1) {
      super(var1);
   }

   @Override
   public boolean read_sampledata(Bitstream var1) {
      return super.read_sampledata(var1);
   }

   @Override
   public boolean put_next_sample(int var1, SynthesisFilter var2, SynthesisFilter var3) {
      if (this.allocation != 0) {
         float var4 = this.samples[this.samplenumber];
         if (this.groupingtable[0] == null) {
            var4 = (var4 + this.d[0]) * this.c[0];
         }

         if (var1 == 0) {
            float var9;
            if (this.groupnumber <= 4) {
               var4 *= this.scalefactor1;
               var9 = var4 * this.channel2_scalefactor1;
            } else if (this.groupnumber <= 8) {
               var4 *= this.scalefactor2;
               var9 = var4 * this.channel2_scalefactor2;
            } else {
               var4 *= this.scalefactor3;
               var9 = var4 * this.channel2_scalefactor3;
            }

            var2.input_sample(var4, this.subbandnumber);
            var3.input_sample(var9, this.subbandnumber);
         } else if (var1 == 1) {
            if (this.groupnumber <= 4) {
               var4 *= this.scalefactor1;
            } else if (this.groupnumber <= 8) {
               var4 *= this.scalefactor2;
            } else {
               var4 *= this.scalefactor3;
            }

            var2.input_sample(var4, this.subbandnumber);
         } else {
            if (this.groupnumber <= 4) {
               var4 *= this.channel2_scalefactor1;
            } else if (this.groupnumber <= 8) {
               var4 *= this.channel2_scalefactor2;
            } else {
               var4 *= this.channel2_scalefactor3;
            }

            var2.input_sample(var4, this.subbandnumber);
         }
      }

      return ++this.samplenumber == 3;
   }

   @Override
   public void read_scalefactor_selection(Bitstream var1, Crc16 var2) {
      if (this.allocation != 0) {
         this.scfsi = var1.get_bits(2);
         this.channel2_scfsi = var1.get_bits(2);
         if (var2 != null) {
            var2.add_bits(this.scfsi, 2);
            var2.add_bits(this.channel2_scfsi, 2);
         }
      }
   }

   @Override
   public void read_scalefactor(Bitstream var1, Header var2) {
      if (this.allocation != 0) {
         super.read_scalefactor(var1, var2);
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
      }
   }

   @Override
   public void read_allocation(Bitstream var1, Header var2, Crc16 var3) {
      super.read_allocation(var1, var2, var3);
   }
}
