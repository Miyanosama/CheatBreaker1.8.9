package javazoom.jl.converter;

import com.cheatbreaker.client.ui.fading.MinMaxFade;
import java.io.PrintWriter;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.Obuffer;
import net.minecraft.command.server.CommandBlockLogic$1;

public class Converter$PrintWriterProgressListener implements Converter$ProgressListener {
   public int detailLevel;
   public static int EXPERT_DETAIL;
   public static int DEBUG_DETAIL;
   public static int NO_DETAIL;
   public static int VERBOSE_DETAIL;
   public MinMaxFade __junk7344626793650035480;
   public PrintWriter pw;
   public static int MAX_DETAIL;
   public CommandBlockLogic$1 __junk8458666775878551761;

   @Override
   public boolean converterException(Throwable var1) {
      if (this.detailLevel > 0) {
         var1.printStackTrace(this.pw);
         this.pw.flush();
      }

      return false;
   }

   @Override
   public void decodedFrame(int var1, Header var2, Obuffer var3) {
      if (this.isDetail(10)) {
         String var4 = var2.toString();
         this.pw.println("Decoded frame " + var1 + ": " + var4);
         this.pw.println("Output: " + var3);
      } else if (this.isDetail(2)) {
         if (var1 == 0) {
            this.pw.print("Converting.");
            this.pw.flush();
         }

         if (var1 % 10 == 0) {
            this.pw.print('.');
            this.pw.flush();
         }
      }
   }

   @Override
   public void readFrame(int var1, Header var2) {
      if (var1 == 0 && this.isDetail(2)) {
         String var4 = var2.toString();
         this.pw.println("File is a " + var4);
      } else if (this.isDetail(10)) {
         String var3 = var2.toString();
         this.pw.println("Read frame " + var1 + ": " + var3);
      }
   }

   public static Converter$PrintWriterProgressListener newStdOut(int var0) {
      return new Converter$PrintWriterProgressListener(new PrintWriter(System.out, true), var0);
   }

   @Override
   public void parsedFrame(int var1, Header var2) {
      if (var1 == 0 && this.isDetail(2)) {
         String var4 = var2.toString();
         this.pw.println("File is a " + var4);
      } else if (this.isDetail(10)) {
         String var3 = var2.toString();
         this.pw.println("Prased frame " + var1 + ": " + var3);
      }
   }

   public Converter$PrintWriterProgressListener(PrintWriter var1, int var2) {
      this.pw = var1;
      this.detailLevel = var2;
   }

   @Override
   public void converterUpdate(int var1, int var2, int var3) {
      if (this.isDetail(2)) {
         switch (var1) {
            case 2:
               if (var3 == 0) {
                  var3 = 1;
               }

               this.pw.println();
               this.pw.println("Converted " + var3 + " frames in " + var2 + " ms (" + var2 / var3 + " ms per frame.)");
         }
      }
   }

   public boolean isDetail(int var1) {
      return this.detailLevel >= var1;
   }
}
