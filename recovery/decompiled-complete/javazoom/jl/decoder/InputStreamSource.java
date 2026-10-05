package javazoom.jl.decoder;

import io.netty.handler.codec.spdy.SpdyFrameDecoder;
import java.io.InputStream;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.stats.StatisticsFile;
import recovered.unidentified.UnidentifiedClass1577;

public class InputStreamSource implements Source {
   public UnidentifiedClass1577 __junk1530892726867876517;
   public StatisticsFile __junk5090591627535934652;
   public GuiOverlayDebug __junk2082483132252938021;
   public ArmorStandRenderer __junk6903131667195045735;
   public SpdyFrameDecoder __junk4353384602838255599;
   public InputStream in;

   @Override
   public long tell() {
      return -1L & -1L;
   }

   @Override
   public boolean willReadBlock() {
      return true;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      return this.in.read(var1, var2, var3);
   }

   @Override
   public long length() {
      return -1L & -1L;
   }

   @Override
   public long seek(long var1) {
      return -1L & -1L;
   }

   public InputStreamSource(InputStream var1) {
      if (var1 == null) {
         throw new NullPointerException("in");
      } else {
         this.in = var1;
      }
   }

   @Override
   public boolean isSeekable() {
      return false;
   }
}
