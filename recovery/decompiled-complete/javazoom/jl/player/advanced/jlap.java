package javazoom.jl.player.advanced;

import io.netty.channel.DefaultChannelPipeline$3;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.minecraft.world.WorldProviderSurface;

public class jlap {
   public DefaultChannelPipeline$3 __junk3331810498720978855;
   public WorldProviderSurface __junk3879584568871809101;

   public static AdvancedPlayer playMp3(File var0, int var1, int var2, PlaybackListener var3) {
      return playMp3(new BufferedInputStream(new FileInputStream(var0)), var1, var2, var3);
   }

   public static AdvancedPlayer playMp3(File var0, PlaybackListener var1) {
      return playMp3(var0, 0, Integer.MAX_VALUE, var1);
   }

   public void showUsage() {
      System.out.println("Usage: jla <filename>");
      System.out.println("");
      System.out.println(" e.g. : java javazoom.jl.player.advanced.jlap localfile.mp3");
   }

   public static void main(String[] var0) {
      jlap var1 = new jlap();
      if (var0.length != 1) {
         var1.showUsage();
         System.exit(0);
      } else {
         try {
            var1.play(var0[0]);
         } catch (Exception var3) {
            System.err.println(var3.getMessage());
            System.exit(0);
         }
      }
   }

   public void play(String var1) {
      jlap$InfoListener var2 = new jlap$InfoListener(this);
      playMp3(new File(var1), var2);
   }

   public static AdvancedPlayer playMp3(InputStream var0, int var1, int var2, PlaybackListener var3) {
      AdvancedPlayer var4 = new AdvancedPlayer(var0);
      var4.setPlayBackListener(var3);
      new jlap$1(var4, var1, var2).start();
      return var4;
   }
}
