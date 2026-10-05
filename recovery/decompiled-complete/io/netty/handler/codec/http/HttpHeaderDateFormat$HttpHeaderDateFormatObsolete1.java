package io.netty.handler.codec.http;

import io.netty.handler.ssl.util.FingerprintTrustManagerFactory;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import net.optifine.model.BlockModelUtils;
import org.apache.log4j.chainsaw.XMLFileHandler;

public class HttpHeaderDateFormat$HttpHeaderDateFormatObsolete1 extends SimpleDateFormat {
   public HttpContentEncoder$Result __junk7071184525958983507;
   public XMLFileHandler __junk5311694423983049903;
   public BlockModelUtils __junk1752365427718004338;
   public FingerprintTrustManagerFactory __junk2075745140502668768;
   public static long serialVersionUID;

   public HttpHeaderDateFormat$HttpHeaderDateFormatObsolete1() {
      super("E, dd-MMM-yy HH:mm:ss z", Locale.ENGLISH);
      this.setTimeZone(TimeZone.getTimeZone("GMT"));
   }
}
