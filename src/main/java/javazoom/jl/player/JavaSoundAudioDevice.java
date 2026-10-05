package javazoom.jl.player;

import io.netty.handler.codec.DecoderException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.JavaLayerException;
import net.minecraft.network.play.client.C07PacketPlayerDigging;

public class JavaSoundAudioDevice extends AudioDeviceBase {
   public SourceDataLine source = null;
   public byte[] byteBuf;
   public AudioFormat fmt = null;

   @Override
   public void openImpl() throws javazoom.jl.decoder.JavaLayerException {
   }

   public Info getSourceLineInfo() {
      AudioFormat var1 = this.getAudioFormat();
      return new Info(SourceDataLine.class, var1);
   }

   public int millisecondsToBytes(AudioFormat var1, int var2) {
      return (int)(var2 * (var1.getSampleRate() * var1.getChannels() * var1.getSampleSizeInBits()) / 8000.0);
   }

   @Override
   public void flushImpl() {
      if (this.source != null) {
         this.source.drain();
      }
   }

   @Override
   public int getPosition() {
      int var1 = 0;
      if (this.source != null) {
         var1 = (int)(this.source.getMicrosecondPosition() / 1000L);
      }

      return var1;
   }

   public void setAudioFormat(AudioFormat var1) {
      this.fmt = var1;
   }

   public byte[] toByteArray(short[] var1, int var2, int var3) {
      byte[] var4 = this.getByteArray(var3 * 2);
      int var5 = 0;

      while (var3-- > 0) {
         short var6 = var1[var2++];
         var4[var5++] = (byte)var6;
         var4[var5++] = (byte)(var6 >>> 8);
      }

      return var4;
   }

   public void test() throws javazoom.jl.decoder.JavaLayerException {
      try {
         this.open(new AudioFormat(22050.0F, 16, 1, true, false));
         short[] var1 = new short[2205];
         this.write(var1, 0, var1.length);
         this.flush();
         this.close();
      } catch (RuntimeException var2) {
         throw new JavaLayerException("Device test failed: " + var2);
      }
   }

   public byte[] getByteArray(int var1) {
      if (this.byteBuf.length < var1) {
         this.byteBuf = new byte[var1 + 1024];
      }

      return this.byteBuf;
   }

   public void open(AudioFormat var1) throws javazoom.jl.decoder.JavaLayerException {
      if (!this.isOpen()) {
         this.setAudioFormat(var1);
         this.openImpl();
         this.setOpen(true);
      }
   }

   @Override
   public void writeImpl(short[] var1, int var2, int var3) throws javazoom.jl.decoder.JavaLayerException {
      if (this.source == null) {
         this.createSource();
      }

      byte[] var4 = this.toByteArray(var1, var2, var3);
      this.source.write(var4, 0, var3 * 2);
   }

   @Override
   public void closeImpl() {
      if (this.source != null) {
         this.source.close();
      }
   }

   public void createSource() throws javazoom.jl.decoder.JavaLayerException {
      Object var1 = null;

      try {
         Line var2 = AudioSystem.getLine(this.getSourceLineInfo());
         if (var2 instanceof SourceDataLine) {
            this.source = (SourceDataLine)var2;
            this.source.open(this.fmt);
            this.source.start();
         }
      } catch (RuntimeException var3) {
         var1 = var3;
      } catch (LinkageError var4) {
         var1 = var4;
      } catch (LineUnavailableException var5) {
         var1 = var5;
      }

      if (this.source == null) {
         throw new JavaLayerException("cannot obtain source audio line", (Throwable)var1);
      }
   }

   public JavaSoundAudioDevice() {
      this.byteBuf = new byte[4096];
   }

   public AudioFormat getAudioFormat() {
      if (this.fmt == null) {
         Decoder var1 = this.getDecoder();
         this.fmt = new AudioFormat(var1.getOutputFrequency(), 16, var1.getOutputChannels(), true, false);
      }

      return this.fmt;
   }
}
