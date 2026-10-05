package javazoom.jl.converter;

import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker13;
import junit.swingui.DefaultFailureDetailView;
import net.minecraft.client.network.NetHandlerHandshakeMemory;
import net.minecraft.client.renderer.entity.RenderCaveSpider;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import org.apache.log4j.varia.FallbackErrorHandler;
import com.cheatbreaker.client.ui.module.ModulePlacementGui$EnumSwitch;

public class WaveFile extends RiffFile {
   public long pcm_data_offset = 0L;
   public WaveFile.WaveFormat_Chunk wave_format;
   public static final int MAX_WAVE_CHANNELS = 2;
   public int num_samples = 0;
   public RiffFile.RiffChunkHeader pcm_data = new RiffFile.RiffChunkHeader();

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
      this.wave_format = new WaveFile.WaveFormat_Chunk();
      this.pcm_data.ckID = FourCC("data");
      this.pcm_data.ckSize = 0;
      this.num_samples = 0;
   }

   public class WaveFileSample {
      public short[] chan = new short[2];
   }

   public class WaveFormat_Chunk {
      public WaveFile.WaveFormat_ChunkData data;
      public RiffFile.RiffChunkHeader header = WaveFile.this.new RiffChunkHeader();

      public int VerifyValidity() {
         boolean var1 = this.header.ckID == RiffFile.FourCC("fmt ")
            && (this.data.nChannels == 1 || this.data.nChannels == 2)
            && this.data.nAvgBytesPerSec == this.data.nChannels * this.data.nSamplesPerSec * this.data.nBitsPerSample / 8
            && this.data.nBlockAlign == this.data.nChannels * this.data.nBitsPerSample / 8;
         return var1 ? 1 : 0;
      }

      public WaveFormat_Chunk() {
         this.data = WaveFile.this.new WaveFormat_ChunkData();
         this.header.ckID = RiffFile.FourCC("fmt ");
         this.header.ckSize = 16;
      }
   }

   public class WaveFormat_ChunkData {
      public short nBitsPerSample;
      public int nAvgBytesPerSec;
      public short wFormatTag = 0;
      public short nBlockAlign;
      public short nChannels = 0;
      public int nSamplesPerSec = 0;

      public void Config(int var1, short var2, short var3) {
         this.nSamplesPerSec = var1;
         this.nChannels = var3;
         this.nBitsPerSample = var2;
         this.nAvgBytesPerSec = this.nChannels * this.nSamplesPerSec * this.nBitsPerSample / 8;
         this.nBlockAlign = (short)(this.nChannels * this.nBitsPerSample / 8);
      }

      public WaveFormat_ChunkData() {
         this.nAvgBytesPerSec = 0;
         this.nBlockAlign = 0;
         this.nBitsPerSample = 0;
         this.wFormatTag = 1;
         this.Config(44100, (short)16, (short)1);
      }
   }
}
