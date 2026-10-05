package javazoom.jl.decoder;

import io.netty.handler.codec.spdy.SpdyFrameDecoder;
import java.io.InputStream;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.stats.StatisticsFile;
import com.cheatbreaker.client.ui.element.type.CrosshairPreviewElement;

public class InputStreamSource implements Source {
   public InputStream in;

   @Override
   public long tell() {
      return -1L;
   }

   @Override
   public boolean willReadBlock() {
      return true;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws java.io.IOException {
      return this.in.read(var1, var2, var3);
   }

   @Override
   public long length() {
      return -1L;
   }

   @Override
   public long seek(long var1) {
      return -1L;
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
