package com.cheatbreaker.client.util.dash;

import com.cheatbreaker.client.CheatBreaker;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl.Type;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDeviceBase;

public class DashPlayer extends AudioDeviceBase {
   public SourceDataLine sourceDataLine = null;
   public byte[] byteArray;
   public AudioFormat audioFormat = null;

   public byte[] method_27649(short[] var1, int var2, int var3) {
      byte[] var4 = this.method_27647(var3 * 2);
      int var5 = 0;

      while (var3-- > 0) {
         short var6 = var1[var2++];
         var4[var5++] = (byte)var6;
         var4[var5++] = (byte)(var6 >>> 8);
      }

      return var4;
   }

   @Override
   public void openImpl() {
   }

   public void setFloatControlValue(float var1) {
      if (this.sourceDataLine != null) {
         FloatControl var2 = (FloatControl)this.sourceDataLine.getControl(Type.MASTER_GAIN);
         float var3 = var2.getMaximum() - var2.getMinimum();
         float var4 = var3 * (var1 / 100.0F) + var2.getMinimum();
         var2.setValue(var4);
      }
   }

   public void setAudioFormat(AudioFormat var1) {
      this.audioFormat = var1;
   }

   @Override
   public int getPosition() {
      int var1 = 0;
      if (this.sourceDataLine != null) {
         var1 = (int)(this.sourceDataLine.getMicrosecondPosition() / 1000L);
      }

      return var1;
   }

   public AudioFormat getAudioFormat() {
      if (this.audioFormat == null) {
         Decoder var1 = this.getDecoder();
         this.audioFormat = new AudioFormat(var1.getOutputFrequency(), 16, var1.getOutputChannels(), true, false);
      }

      return this.audioFormat;
   }

   @Override
   public void writeImpl(short[] var1, int var2, int var3) throws javazoom.jl.decoder.JavaLayerException {
      if (this.sourceDataLine == null) {
         this.method_27650();
      }

      byte[] var4 = this.method_27649(var1, var2, var3);
      this.sourceDataLine.write(var4, 0, var3 * 2);
   }

   @Override
   public void closeImpl() {
      if (this.sourceDataLine != null) {
         this.sourceDataLine.close();
      }
   }

   @Override
   public void flushImpl() {
      if (this.sourceDataLine != null) {
         this.sourceDataLine.drain();
      }
   }

   public void method_27651() throws javazoom.jl.decoder.JavaLayerException {
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

   public byte[] method_27647(int var1) {
      if (this.byteArray.length < var1) {
         this.byteArray = new byte[var1 + 1024];
      }

      return this.byteArray;
   }

   public DashPlayer() {
      this.byteArray = new byte[4096];
   }

   public Info getInfo() {
      AudioFormat var1 = this.getAudioFormat();
      return new Info(SourceDataLine.class, var1);
   }

   public void method_27650() throws javazoom.jl.decoder.JavaLayerException {
      Throwable var1 = null;

      try {
         Line var2 = AudioSystem.getLine(this.getInfo());
         if (var2 instanceof SourceDataLine) {
            this.sourceDataLine = (SourceDataLine)var2;
            this.sourceDataLine.open(this.audioFormat);
            this.sourceDataLine.start();
            this.setFloatControlValue((Float)CheatBreaker.getInstance().getGlobalSettings().recoveredField546.getValue());
         }
      } catch (LineUnavailableException | LinkageError | RuntimeException var3) {
         var1 = var3;
      }

      if (this.sourceDataLine == null) {
         throw new JavaLayerException("cannot obtain source audio line", var1);
      }
   }

   public void open(AudioFormat var1) {
      if (!this.isOpen()) {
         this.setAudioFormat(var1);
         this.openImpl();
         this.setOpen(true);
      }
   }
}
