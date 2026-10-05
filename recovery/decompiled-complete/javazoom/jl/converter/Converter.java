package javazoom.jl.converter;

import io.netty.buffer.ByteBufUtil;
import io.netty.handler.codec.spdy.SpdyHttpHeaders$Names;
import io.netty.util.internal.logging.Log4JLogger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Decoder$Params;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.Obuffer;
import net.minecraft.block.BlockCocoa$1;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.optifine.CustomItems;
import recovered.unidentified.UnidentifiedClass1750;

public class Converter {
   public MapGenStructureIO __junk3084747563342111340;
   public BlockCocoa$1 __junk6696596098712311302;
   public UnidentifiedClass1750 __junk5834228354566392335;
   public Log4JLogger __junk2081916625113991185;
   public ByteBufUtil __junk3618564503561661902;
   public SpdyHttpHeaders$Names __junk8589173074145570667;
   public CustomItems __junk6234346611075183770;

   public synchronized void convert(String var1, String var2, Converter$ProgressListener var3) {
      this.convert(var1, var2, var3, null);
   }

   public InputStream openInput(String var1) {
      File var2 = new File(var1);
      FileInputStream var3 = new FileInputStream(var2);
      return new BufferedInputStream(var3);
   }

   public void convert(String var1, String var2, Converter$ProgressListener var3, Decoder$Params var4) {
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

   public synchronized void convert(InputStream var1, String var2, Converter$ProgressListener var3, Decoder$Params var4) {
      if (var3 == null) {
         var3 = Converter$PrintWriterProgressListener.newStdOut(0);
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

         ((Converter$ProgressListener)var3).converterUpdate(1, var5, 0);
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

                  ((Converter$ProgressListener)var3).readFrame(var9, var12);
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

                  ((Converter$ProgressListener)var3).decodedFrame(var9, var12, var6);
                  var8.closeFrame();
               } catch (Exception var19) {
                  boolean var13 = !((Converter$ProgressListener)var3).converterException(var19);
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
         ((Converter$ProgressListener)var3).converterUpdate(2, var22, var9);
      } catch (IOException var21) {
         throw new JavaLayerException(var21.getLocalizedMessage(), var21);
      }
   }

   public int countFrames(InputStream var1) {
      return -1;
   }

   public synchronized void convert(String var1, String var2) {
      this.convert(var1, var2, null, null);
   }
}
