package javazoom.jl.converter;

import net.minecraft.client.Minecraft$7;
import net.minecraft.client.network.NetHandlerHandshakeMemory;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;

public class WaveFile$WaveFormat_ChunkData {
   public short nBitsPerSample;
   public C13PacketPlayerAbilities __junk5494327396475727453;
   public NetHandlerHandshakeMemory __junk4738434373907065140;
   public int nAvgBytesPerSec;
   public short wFormatTag;
   public short nBlockAlign;
   public short nChannels;
   public int nSamplesPerSec;
   public Minecraft$7 __junk5362742585924858220;

   public void Config(int var1, short var2, short var3) {
      this.nSamplesPerSec = var1;
      this.nChannels = var3;
      this.nBitsPerSample = var2;
      this.nAvgBytesPerSec = this.nChannels * this.nSamplesPerSec * this.nBitsPerSample / 8;
      this.nBlockAlign = (short)(this.nChannels * this.nBitsPerSample / 8);
   }

   public WaveFile$WaveFormat_ChunkData(WaveFile var1) {
      this.this$0 = var1;
      super();
      this.wFormatTag = 0;
      this.nChannels = 0;
      this.nSamplesPerSec = 0;
      this.nAvgBytesPerSec = 0;
      this.nBlockAlign = 0;
      this.nBitsPerSample = 0;
      this.wFormatTag = 1;
      this.Config(44100, (short)16, (short)1);
   }
}
