package javazoom.jl.converter;

import java.io.PrintWriter;
import javazoom.jl.decoder.Crc16;
import javazoom.jl.decoder.JavaLayerException;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;

public class jlc {

   public static void main(String[] var0) {
      long var2 = System.currentTimeMillis();
      int var4 = var0.length + 1;
      String[] var1 = new String[var4];
      var1[0] = "jlc";

      for (int var5 = 0; var5 < var0.length; var5++) {
         var1[var5 + 1] = var0[var5];
      }

      jlc.jlcArgs var11 = new jlc.jlcArgs();
      if (!var11.processArgs(var1)) {
         System.exit(1);
      }

      Converter var6 = new Converter();
      int var7 = var11.verbose_mode ? var11.verbose_level : 0;
      Converter.PrintWriterProgressListener var8 = new Converter.PrintWriterProgressListener(new PrintWriter(System.out, true), var7);

      try {
         var6.convert(var11.filename, var11.output_filename, var8);
      } catch (JavaLayerException var10) {
         System.err.println("Convertion failure: " + var10);
      }

      System.exit(0);
   }

   public static class jlcArgs {
      public String filename;
      public int which_c;
      public int verbose_level = 3;
      public String output_filename;
      public float scalefactor;
      public boolean use_own_scalefactor;
      public boolean verbose_mode;
      public int output_mode;

      public boolean Usage() {
         System.out.println("JavaLayer Converter :");
         System.out.println("  -v[x]         verbose mode. ");
         System.out.println("                default = 2");
         System.out.println("  -p name    output as a PCM wave file");
         System.out.println("");
         System.out.println("  More info on http://www.javazoom.net");
         return false;
      }

      public jlcArgs() {
         this.which_c = 0;
         this.use_own_scalefactor = false;
         this.scalefactor = 32768.0F;
         this.verbose_mode = false;
      }

      public boolean processArgs(String[] var1) {
         this.filename = null;
         Crc16[] var2 = new Crc16[1];
         int var4 = var1.length;
         this.verbose_mode = false;
         this.output_mode = 0;
         this.output_filename = "";
         if (var4 >= 2 && !var1[1].equals("-h")) {
            for (int var3 = 1; var3 < var4; var3++) {
               if (var1[var3].charAt(0) == '-') {
                  if (var1[var3].startsWith("-v")) {
                     this.verbose_mode = true;
                     if (var1[var3].length() > 2) {
                        try {
                           String var5 = var1[var3].substring(2);
                           this.verbose_level = Integer.parseInt(var5);
                        } catch (NumberFormatException var6) {
                           System.err.println("Invalid verbose level. Using default.");
                        }
                     }

                     System.out.println("Verbose Activated (level " + this.verbose_level + ")");
                  } else {
                     if (!var1[var3].equals("-p")) {
                        return this.Usage();
                     }

                     if (++var3 == var4) {
                        System.out.println("Please specify an output filename after the -p option!");
                        System.exit(1);
                     }

                     this.output_filename = var1[var3];
                  }
               } else {
                  this.filename = var1[var3];
                  System.out.println("FileName = " + var1[var3]);
                  if (this.filename == null) {
                     return this.Usage();
                  }
               }
            }

            return this.filename == null ? this.Usage() : true;
         } else {
            return this.Usage();
         }
      }
   }
}
