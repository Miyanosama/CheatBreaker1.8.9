package javazoom.jl.converter;

import com.cheatbreaker.client.ui.fading.MinMaxFade;
import io.netty.buffer.ByteBufUtil;
import io.netty.handler.codec.spdy.SpdyHttpHeaders;
import io.netty.util.internal.logging.Log4JLogger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.Obuffer;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.optifine.CustomItems;
import junit.swingui.TestRunner$14;

public class Converter {

   public synchronized void convert(String var1, String var2, Converter.ProgressListener var3) throws javazoom.jl.decoder.JavaLayerException {
      this.convert(var1, var2, var3, null);
   }

   public InputStream openInput(String var1) throws java.io.IOException {
      File var2 = new File(var1);
      FileInputStream var3 = new FileInputStream(var2);
      return new BufferedInputStream(var3);
   }

   public void convert(String var1, String var2, Converter.ProgressListener var3, Decoder.Params var4) throws javazoom.jl.decoder.JavaLayerException {
      if (var2.length() == 0) {
         var2 = null;
      }

      try {
         InputStream var5 = this.openInput(var1);
         this.convert(var5, var2, var3, var4);
         var5.close();
      } catch (IOException var6) {
         throw new JavaLayerException(var6.getLocalizedMessage(), var6);
      }
   }

   public synchronized void convert(InputStream var1, String var2, Converter.ProgressListener var3, Decoder.Params var4) throws javazoom.jl.decoder.JavaLayerException {
      if (var3 == null) {
         var3 = Converter.PrintWriterProgressListener.newStdOut(0);
      }

      try {
         if (!(var1 instanceof BufferedInputStream)) {
            var1 = new BufferedInputStream((InputStream)var1);
         }

         int var5 = -1;
         if (var1.markSupported()) {
            var1.mark(-1);
            var5 = this.countFrames((InputStream)var1);
            var1.reset();
         }

         ((Converter.ProgressListener)var3).converterUpdate(1, var5, 0);
         WaveFileObuffer var6 = null;
         Decoder var7 = new Decoder(var4);
         Bitstream var8 = new Bitstream((InputStream)var1);
         if (var5 == -1) {
            var5 = Integer.MAX_VALUE;
         }

         int var9 = 0;
         long var10 = System.currentTimeMillis();

         try {
            for (; var9 < var5; var9++) {
               try {
                  Header var12 = var8.readFrame();
                  if (var12 == null) {
                     break;
                  }

                  ((Converter.ProgressListener)var3).readFrame(var9, var12);
                  if (var6 == null) {
                     int var23 = var12.mode() == 3 ? 1 : 2;
                     int var14 = var12.frequency();
                     var6 = new WaveFileObuffer(var23, var14, var2);
                     var7.setOutputBuffer(var6);
                  }

                  Obuffer var24 = var7.decodeFrame(var12, var8);
                  if (var24 != var6) {
                     throw new InternalError("Output buffers are different.");
                  }

                  ((Converter.ProgressListener)var3).decodedFrame(var9, var12, var6);
                  var8.closeFrame();
               } catch (Exception var19) {
                  boolean var13 = !((Converter.ProgressListener)var3).converterException(var19);
                  if (var13) {
                     throw new JavaLayerException(var19.getLocalizedMessage(), var19);
                  }
               }
            }
         } finally {
            if (var6 != null) {
               var6.close();
            }
         }

         int var22 = (int)(System.currentTimeMillis() - var10);
         ((Converter.ProgressListener)var3).converterUpdate(2, var22, var9);
      } catch (IOException var21) {
         throw new JavaLayerException(var21.getLocalizedMessage(), var21);
      }
   }

   public int countFrames(InputStream var1) {
      return -1;
   }

   public synchronized void convert(String var1, String var2) throws javazoom.jl.decoder.JavaLayerException {
      this.convert(var1, var2, null, null);
   }

   public static class PrintWriterProgressListener implements Converter.ProgressListener {
      public int detailLevel;
      public static final int EXPERT_DETAIL = 1;
      public static final int DEBUG_DETAIL = 7;
      public static final int NO_DETAIL = 0;
      public static final int VERBOSE_DETAIL = 2;
      public PrintWriter pw;
      public static final int MAX_DETAIL = 10;

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

      public static Converter.PrintWriterProgressListener newStdOut(int var0) {
         return new Converter.PrintWriterProgressListener(new PrintWriter(System.out, true), var0);
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

      public PrintWriterProgressListener(PrintWriter var1, int var2) {
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

   public interface ProgressListener {
      int UPDATE_FRAME_COUNT = 1;
      int UPDATE_CONVERT_COMPLETE = 2;

      void parsedFrame(int var1, Header var2);

      void decodedFrame(int var1, Header var2, Obuffer var3);

      void converterUpdate(int var1, int var2, int var3);

      void readFrame(int var1, Header var2);

      boolean converterException(Throwable var1);
   }
}
