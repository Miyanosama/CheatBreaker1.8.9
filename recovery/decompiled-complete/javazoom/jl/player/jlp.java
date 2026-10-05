package javazoom.jl.player;

import io.netty.handler.codec.socks.SocksCmdRequestDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;
import net.minecraft.client.stream.BroadcastController;

public class jlp {
   public BroadcastController __junk385163080392422624;
   public boolean remote;
   public SocksCmdRequestDecoder __junk6481843075366880641;
   public String fFilename = null;

   public void showUsage() {
      System.out.println("Usage: jlp [-url] <filename>");
      System.out.println("");
      System.out.println(" e.g. : java javazoom.jl.player.jlp localfile.mp3");
      System.out.println("        java javazoom.jl.player.jlp -url http://www.server.com/remotefile.mp3");
      System.out.println("        java javazoom.jl.player.jlp -url http://www.shoutcastserver.com:8000");
   }

   public AudioDevice getAudioDevice() {
      return FactoryRegistry.systemRegistry().createAudioDevice();
   }

   public InputStream getInputStream() {
      FileInputStream var1 = new FileInputStream(this.fFilename);
      return new BufferedInputStream(var1);
   }

   public static jlp createInstance(String[] var0) {
      jlp var1 = new jlp();
      if (!var1.parseArgs(var0)) {
         var1 = null;
      }

      return var1;
   }

   public void play() {
      try {
         System.out.println("playing " + this.fFilename + "...");
         Object var1 = null;
         if (this.remote) {
            var1 = this.getURLInputStream();
         } else {
            var1 = this.getInputStream();
         }

         AudioDevice var2 = this.getAudioDevice();
         Player var3 = new Player((InputStream)var1, var2);
         var3.play();
      } catch (IOException var4) {
         throw new JavaLayerException("Problem playing file " + this.fFilename, var4);
      } catch (Exception var5) {
         throw new JavaLayerException("Problem playing file " + this.fFilename, var5);
      }
   }

   public boolean parseArgs(String[] var1) {
      boolean var2 = false;
      if (var1.length == 1) {
         this.init(var1[0]);
         var2 = true;
         this.remote = false;
      } else if (var1.length == 2) {
         if (!var1[0].equals("-url")) {
            this.showUsage();
         } else {
            this.init(var1[1]);
            var2 = true;
            this.remote = true;
         }
      } else {
         this.showUsage();
      }

      return var2;
   }

   public jlp(String var1) {
      this.remote = false;
      this.init(var1);
   }

   public static void main(String[] var0) {
      byte var1 = 0;

      try {
         jlp var2 = createInstance(var0);
         if (var2 != null) {
            var2.play();
         }
      } catch (Exception var3) {
         System.err.println(var3);
         var3.printStackTrace(System.err);
         var1 = 1;
      }

      System.exit(var1);
   }

   public void init(String var1) {
      this.fFilename = var1;
   }

   public jlp() {
      this.remote = false;
   }

   public InputStream getURLInputStream() {
      URL var1 = new URL(this.fFilename);
      InputStream var2 = var1.openStream();
      return new BufferedInputStream(var2);
   }
}
