package javazoom.jl.converter;

import net.minecraft.network.play.server.S28PacketEffect;

public class WaveFile$WaveFormat_Chunk {
   public S28PacketEffect __junk7257787164173472484;
   public WaveFile$WaveFormat_ChunkData data;
   public RiffFile$RiffChunkHeader header;

   public int VerifyValidity() {
      boolean var1 = this.header.ckID == RiffFile.FourCC("fmt ")
         && (this.data.nChannels == 1 || this.data.nChannels == 2)
         && this.data.nAvgBytesPerSec == this.data.nChannels * this.data.nSamplesPerSec * this.data.nBitsPerSample / 8
         && this.data.nBlockAlign == this.data.nChannels * this.data.nBitsPerSample / 8;
      return var1 ? 1 : 0;
   }

   public WaveFile$WaveFormat_Chunk(WaveFile var1) {
      this.this$0 = var1;
      super();
      this.header = new RiffFile$RiffChunkHeader(var1);
      this.data = new WaveFile$WaveFormat_ChunkData(var1);
      this.header.ckID = RiffFile.FourCC("fmt ");
      this.header.ckSize = 16;
   }
}
