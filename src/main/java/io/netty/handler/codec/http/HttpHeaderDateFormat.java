package io.netty.handler.codec.http;

import io.netty.handler.ssl.util.FingerprintTrustManagerFactory;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javazoom.jl.decoder.LayerIIDecoder;
import javazoom.jl.player.JavaSoundAudioDeviceFactory;
import net.minecraft.client.audio.GuardianSound;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.command.CommandEffect;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.optifine.gui.GuiChatOF;
import net.optifine.model.BlockModelUtils;
import org.apache.log4j.chainsaw.XMLFileHandler;
import org.java_websocket.util.ByteBufferUtils;
import junit.swingui.TestRunner$4;

public class HttpHeaderDateFormat extends SimpleDateFormat {
   public static final long serialVersionUID = -925286159755905325L;
   public static FastThreadLocal<HttpHeaderDateFormat> dateFormatThreadLocal = new FastThreadLocal<HttpHeaderDateFormat>() {

      public HttpHeaderDateFormat initialValue() {
         return new HttpHeaderDateFormat();
      }
   };
   public SimpleDateFormat format1 = new HttpHeaderDateFormat.HttpHeaderDateFormatObsolete1();
   public SimpleDateFormat format2 = new HttpHeaderDateFormat.HttpHeaderDateFormatObsolete2();

   public static HttpHeaderDateFormat get() {
      return dateFormatThreadLocal.get();
   }

   public HttpHeaderDateFormat() {
      super("E, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
      this.setTimeZone(TimeZone.getTimeZone("GMT"));
   }

   @Override
   public Date parse(String var1, ParsePosition var2) {
      Date var3 = super.parse(var1, var2);
      if (var3 == null) {
         var3 = this.format1.parse(var1, var2);
      }

      if (var3 == null) {
         var3 = this.format2.parse(var1, var2);
      }

      return var3;
   }

   public static final class HttpHeaderDateFormatObsolete1 extends SimpleDateFormat {
      public static final long serialVersionUID = -3178072504225114298L;

      public HttpHeaderDateFormatObsolete1() {
         super("E, dd-MMM-yy HH:mm:ss z", Locale.ENGLISH);
         this.setTimeZone(TimeZone.getTimeZone("GMT"));
      }
   }

   public static final class HttpHeaderDateFormatObsolete2 extends SimpleDateFormat {
      public static final long serialVersionUID = 3010674519968303714L;

      public HttpHeaderDateFormatObsolete2() {
         super("E MMM d HH:mm:ss yyyy", Locale.ENGLISH);
         this.setTimeZone(TimeZone.getTimeZone("GMT"));
      }
   }
}
