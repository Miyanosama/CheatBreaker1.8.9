package org.apache.log4j;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker07;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.spdy.DefaultSpdySettingsFrame;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.pattern.BridgePatternConverter;
import org.apache.log4j.pattern.BridgePatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class EnhancedPatternLayout extends Layout {
   public static String field_0005;
   public static String field_0008;
   public WebSocketServerHandshaker07 field_0004;
   public String conversionPattern;
   public int BUF_SIZE = 256;
   public int MAX_CAPACITY = 1024;
   public DefaultSpdySettingsFrame field_0009;
   public boolean handlesExceptions;
   public ProtobufVarint32FrameDecoder field_0003;
   public static String field_0010;
   public PatternConverter head;

   public String format(LoggingEvent var1) {
      StringBuffer var2 = new StringBuffer();

      for (PatternConverter var3 = this.head; var3 != null; var3 = var3.next) {
         var3.format(var2, var1);
      }

      return var2.toString();
   }

   public PatternParser createPatternParser(String var1) {
      return new BridgePatternParser(var1);
   }

   public EnhancedPatternLayout() {
      this("%m%n");
   }

   public EnhancedPatternLayout(String var1) {
      this.conversionPattern = var1;
      this.head = this.createPatternParser(var1 == null ? "%m%n" : var1).parse();
      if (this.head instanceof BridgePatternConverter) {
         this.handlesExceptions = !((BridgePatternConverter)this.head).ignoresThrowable();
      } else {
         this.handlesExceptions = false;
      }
   }

   public void activateOptions() {
   }

   public String getConversionPattern() {
      return this.conversionPattern;
   }

   public void setConversionPattern(String var1) {
      this.conversionPattern = OptionConverter.convertSpecialChars(var1);
      this.head = this.createPatternParser(this.conversionPattern).parse();
      if (this.head instanceof BridgePatternConverter) {
         this.handlesExceptions = !((BridgePatternConverter)this.head).ignoresThrowable();
      } else {
         this.handlesExceptions = false;
      }
   }

   public boolean ignoresThrowable() {
      return !this.handlesExceptions;
   }
}
