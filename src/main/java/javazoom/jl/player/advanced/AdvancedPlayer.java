package javazoom.jl.player.advanced;

import java.io.InputStream;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.realms.RealmsLevelSummary;
import org.apache.log4j.lf5.DefaultLF5Configurator;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$16;

public class AdvancedPlayer {
   public Decoder decoder;
   public AudioDevice audio;
   public boolean complete;
   public boolean closed = false;
   public int lastPosition;
   public Bitstream bitstream;
   public PlaybackListener listener;

   public PlaybackListener getPlayBackListener() {
      return this.listener;
   }

   public void stop() {
      this.listener.playbackFinished(this.createEvent(PlaybackEvent.STOPPED));
      this.close();
   }

   public boolean decodeFrame() throws javazoom.jl.decoder.JavaLayerException {
      try {
         AudioDevice var1 = this.audio;
         if (var1 == null) {
            return false;
         } else {
            Header var2 = this.bitstream.readFrame();
            if (var2 == null) {
               return false;
            } else {
               SampleBuffer var3 = (SampleBuffer)this.decoder.decodeFrame(var2, this.bitstream);
               synchronized (this) {
                  var1 = this.audio;
                  if (var1 != null) {
                     var1.write(var3.getBuffer(), 0, var3.getBufferLength());
                  }
               }

               this.bitstream.closeFrame();
               return true;
            }
         }
      } catch (RuntimeException var7) {
         throw new JavaLayerException("Exception decoding audio frame", var7);
      }
   }

   public void setPlayBackListener(PlaybackListener var1) {
      this.listener = var1;
   }

   public boolean skipFrame() throws javazoom.jl.decoder.JavaLayerException {
      Header var1 = this.bitstream.readFrame();
      if (var1 == null) {
         return false;
      } else {
         this.bitstream.closeFrame();
         return true;
      }
   }

   public void play() throws javazoom.jl.decoder.JavaLayerException {
      this.play(Integer.MAX_VALUE);
   }

   public boolean play(int var1, int var2) throws javazoom.jl.decoder.JavaLayerException {
      boolean var3 = true;
      int var4 = var1;

      while (var4-- > 0 && var3) {
         var3 = this.skipFrame();
      }

      return this.play(var2 - var1);
   }

   public PlaybackEvent createEvent(int var1) {
      return this.createEvent(this.audio, var1);
   }

   public AdvancedPlayer(InputStream var1) throws javazoom.jl.decoder.JavaLayerException {
      this(var1, null);
   }

   public PlaybackEvent createEvent(AudioDevice var1, int var2) {
      return new PlaybackEvent(this, var2, var1.getPosition());
   }

   public boolean play(int var1) throws javazoom.jl.decoder.JavaLayerException {
      boolean var2 = true;
      if (this.listener != null) {
         this.listener.playbackStarted(this.createEvent(PlaybackEvent.STARTED));
      }

      while (var1-- > 0 && var2) {
         var2 = this.decodeFrame();
      }

      AudioDevice var3 = this.audio;
      if (var3 != null) {
         var3.flush();
         synchronized (this) {
            this.complete = !this.closed;
            this.close();
         }

         if (this.listener != null) {
            this.listener.playbackFinished(this.createEvent(var3, PlaybackEvent.STOPPED));
         }
      }

      return var2;
   }

   public synchronized void close() {
      AudioDevice var1 = this.audio;
      if (var1 != null) {
         this.closed = true;
         this.audio = null;
         var1.close();
         this.lastPosition = var1.getPosition();

         try {
            this.bitstream.close();
         } catch (BitstreamException var3) {
         }
      }
   }

   public AdvancedPlayer(InputStream var1, AudioDevice var2) throws javazoom.jl.decoder.JavaLayerException {
      this.complete = false;
      this.lastPosition = 0;
      this.bitstream = new Bitstream(var1);
      if (var2 != null) {
         this.audio = var2;
      } else {
         this.audio = FactoryRegistry.systemRegistry().createAudioDevice();
      }

      this.audio.open(this.decoder = new Decoder());
   }
}
