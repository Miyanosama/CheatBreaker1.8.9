package javazoom.jl.player;

import io.netty.handler.codec.spdy.SpdyHeaderBlockEncoder;
import java.applet.Applet;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;
import net.minecraft.block.BlockSilverfish$EnumType$2;

public class PlayerApplet extends Applet implements Runnable {
   public Thread playerThread;
   public SpdyHeaderBlockEncoder __junk8931964570081819594;
   public static String AUDIO_PARAMETER;
   public BlockSilverfish$EnumType$2 __junk1605348890129085341;
   public Player player = null;
   public String fileName;

   @Override
   public void stop() {
      try {
         this.stopPlayer();
      } catch (JavaLayerException var2) {
         System.err.println(var2);
      }
   }

   public String getAudioFileName() {
      String var1 = this.fileName;
      if (var1 == null) {
         var1 = this.getParameter("audioURL");
      }

      return var1;
   }

   public void play(InputStream var1, AudioDevice var2) {
      this.stopPlayer();
      if (var1 != null && var2 != null) {
         this.player = new Player(var1, var2);
         this.playerThread = this.createPlayerThread();
         this.playerThread.start();
      }
   }

   public AudioDevice getAudioDevice() {
      return FactoryRegistry.systemRegistry().createAudioDevice();
   }

   @Override
   public void destroy() {
   }

   public Thread createPlayerThread() {
      return new Thread(this, "Audio player thread");
   }

   @Override
   public void init() {
   }

   public void stopPlayer() {
      if (this.player != null) {
         this.player.close();
         this.player = null;
         this.playerThread = null;
      }
   }

   public void setFileName(String var1) {
      this.fileName = var1;
   }

   public String getFileName() {
      return this.fileName;
   }

   public PlayerApplet() {
      this.playerThread = null;
      this.fileName = null;
   }

   public URL getAudioURL() {
      String var1 = this.getAudioFileName();
      URL var2 = null;
      if (var1 != null) {
         try {
            var2 = new URL(this.getDocumentBase(), var1);
         } catch (Exception var4) {
            System.err.println(var4);
         }
      }

      return var2;
   }

   public InputStream getAudioStream() {
      InputStream var1 = null;

      try {
         URL var2 = this.getAudioURL();
         if (var2 != null) {
            var1 = var2.openStream();
         }
      } catch (IOException var3) {
         System.err.println(var3);
      }

      return var1;
   }

   @Override
   public void run() {
      if (this.player != null) {
         try {
            this.player.play();
         } catch (JavaLayerException var2) {
            System.err.println("Problem playing audio: " + var2);
         }
      }
   }

   @Override
   public void start() {
      String var1 = this.getAudioFileName();

      try {
         InputStream var7 = this.getAudioStream();
         AudioDevice var8 = this.getAudioDevice();
         this.play(var7, var8);
      } catch (JavaLayerException var6) {
         JavaLayerException var2 = var6;
         synchronized (System.err) {
            System.err.println("Unable to play " + var1);
            var2.printStackTrace(System.err);
         }
      }
   }
}
