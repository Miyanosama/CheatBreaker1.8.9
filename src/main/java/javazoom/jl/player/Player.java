package javazoom.jl.player;

import java.io.InputStream;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import net.minecraft.command.server.CommandSetDefaultSpawnpoint;

public class Player {
   public int lastPosition;
   public int frame = 0;
   public AudioDevice audio;
   public boolean complete;
   public Bitstream bitstream;
   public Decoder decoder;
   public boolean closed = false;

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

   public Player(InputStream var1, AudioDevice var2) throws javazoom.jl.decoder.JavaLayerException {
      this.complete = false;
      this.lastPosition = 0;
      this.bitstream = new Bitstream(var1);
      this.decoder = new Decoder();
      if (var2 != null) {
         this.audio = var2;
      } else {
         FactoryRegistry var3 = FactoryRegistry.systemRegistry();
         this.audio = var3.createAudioDevice();
      }

      this.audio.open(this.decoder);
   }

   public int getPosition() {
      int var1 = this.lastPosition;
      AudioDevice var2 = this.audio;
      if (var2 != null) {
         var1 = var2.getPosition();
      }

      return var1;
   }

   public synchronized boolean isComplete() {
      return this.complete;
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

   public boolean play(int var1) throws javazoom.jl.decoder.JavaLayerException {
      boolean var2 = true;

      while (var1-- > 0 && var2) {
         var2 = this.decodeFrame();
      }

      if (!var2) {
         AudioDevice var3 = this.audio;
         if (var3 != null) {
            var3.flush();
            synchronized (this) {
               this.complete = !this.closed;
               this.close();
            }
         }
      }

      return var2;
   }

   public Player(InputStream var1) throws javazoom.jl.decoder.JavaLayerException {
      this(var1, null);
   }

   public void play() throws javazoom.jl.decoder.JavaLayerException {
      this.play(Integer.MAX_VALUE);
   }
}
