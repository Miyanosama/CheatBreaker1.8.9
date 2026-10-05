package javazoom.jl.converter;

import net.minecraft.client.renderer.entity.RenderCaveSpider;
import recovered.unidentified.UnidentifiedClass4502;

public class WaveFile extends RiffFile {
   public long pcm_data_offset = 4520780025241862153L & -4520780026982948816L;
   public RenderCaveSpider __junk6630045942928194232;
   public WaveFile$WaveFormat_Chunk wave_format;
   public static int MAX_WAVE_CHANNELS;
   public int num_samples = 0;
   public RiffFile$RiffChunkHeader pcm_data = new RiffFile$RiffChunkHeader(this);
   public UnidentifiedClass4502 __junk6705010205083207043;

   public short NumChannels() {
      return this.wave_format.data.nChannels;
   }

   public int NumSamples() {
      return this.num_samples;
   }

   public int OpenForWrite(String var1, WaveFile var2) {
      return this.OpenForWrite(var1, var2.SamplingRate(), var2.BitsPerSample(), var2.NumChannels());
   }

   @Override
   public int Close() {
      int var1 = 0;
      if (this.fmode == 1) {
         var1 = this.Backpatch(this.pcm_data_offset, this.pcm_data, 8);
      }

      if (var1 == 0) {
         var1 = super.Close();
      }

      return var1;
   }

   public short BitsPerSample() {
      return this.wave_format.data.nBitsPerSample;
   }

   public int WriteData(short[] var1, int var2) {
      int var3 = var2 * 2;
      this.pcm_data.ckSize += var3;
      return super.Write(var1, var3);
   }

   public int OpenForWrite(String var1, int var2, short var3, short var4) {
      if (var1 != null && (var3 == 8 || var3 == 16) && var4 >= 1 && var4 <= 2) {
         this.wave_format.data.Config(var2, var3, var4);
         int var5 = this.Open(var1, 1);
         if (var5 == 0) {
            byte[] var6 = new byte[]{87, 65, 86, 69};
            var5 = this.Write(var6, 4);
            if (var5 == 0) {
               var5 = this.Write(this.wave_format.header, 8);
               var5 = this.Write(this.wave_format.data.wFormatTag, 2);
               var5 = this.Write(this.wave_format.data.nChannels, 2);
               var5 = this.Write(this.wave_format.data.nSamplesPerSec, 4);
               var5 = this.Write(this.wave_format.data.nAvgBytesPerSec, 4);
               var5 = this.Write(this.wave_format.data.nBlockAlign, 2);
               var5 = this.Write(this.wave_format.data.nBitsPerSample, 2);
               if (var5 == 0) {
                  this.pcm_data_offset = this.CurrentFilePosition();
                  var5 = this.Write(this.pcm_data, 8);
               }
            }
         }

         return var5;
      } else {
         return 4;
      }
   }

   public int SamplingRate() {
      return this.wave_format.data.nSamplesPerSec;
   }

   @Override
   public long CurrentFilePosition() {
      return super.CurrentFilePosition();
   }

   public WaveFile() {
      this.wave_format = new WaveFile$WaveFormat_Chunk(this);
      this.pcm_data.ckID = FourCC("data");
      this.pcm_data.ckSize = 0;
      this.num_samples = 0;
   }
}
