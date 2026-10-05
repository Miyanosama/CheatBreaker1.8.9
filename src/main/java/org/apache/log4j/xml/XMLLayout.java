package org.apache.log4j.xml;

import java.util.Arrays;
import java.util.Set;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class XMLLayout extends Layout {
   public boolean locationInfo;
   public StringBuffer buf;
   public int DEFAULT_SIZE = 256;
   public boolean properties;
   public int UPPER_LIMIT = 2048;

   public boolean getProperties() {
      return this.properties;
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public boolean ignoresThrowable() {
      return false;
   }

   public void activateOptions() {
   }

   public String format(LoggingEvent var1) {
      if (this.buf.capacity() > 2048) {
         this.buf = new StringBuffer(256);
      } else {
         this.buf.setLength(0);
      }

      this.buf.append("<log4j:event logger=\"");
      this.buf.append(Transform.escapeTags(var1.getLoggerName()));
      this.buf.append("\" timestamp=\"");
      this.buf.append(var1.timeStamp);
      this.buf.append("\" level=\"");
      this.buf.append(Transform.escapeTags(String.valueOf(var1.getLevel())));
      this.buf.append("\" thread=\"");
      this.buf.append(Transform.escapeTags(var1.getThreadName()));
      this.buf.append("\">\r\n");
      this.buf.append("<log4j:message><![CDATA[");
      Transform.appendEscapingCDATA(this.buf, var1.getRenderedMessage());
      this.buf.append("]]></log4j:message>\r\n");
      String var2 = var1.getNDC();
      if (var2 != null) {
         this.buf.append("<log4j:NDC><![CDATA[");
         Transform.appendEscapingCDATA(this.buf, var2);
         this.buf.append("]]></log4j:NDC>\r\n");
      }

      String[] var3 = var1.getThrowableStrRep();
      if (var3 != null) {
         this.buf.append("<log4j:throwable><![CDATA[");

         for (int var4 = 0; var4 < var3.length; var4++) {
            Transform.appendEscapingCDATA(this.buf, var3[var4]);
            this.buf.append("\r\n");
         }

         this.buf.append("]]></log4j:throwable>\r\n");
      }

      if (this.locationInfo) {
         LocationInfo var9 = var1.getLocationInformation();
         this.buf.append("<log4j:locationInfo class=\"");
         this.buf.append(Transform.escapeTags(var9.getClassName()));
         this.buf.append("\" method=\"");
         this.buf.append(Transform.escapeTags(var9.getMethodName()));
         this.buf.append("\" file=\"");
         this.buf.append(Transform.escapeTags(var9.getFileName()));
         this.buf.append("\" line=\"");
         this.buf.append(var9.getLineNumber());
         this.buf.append("\"/>\r\n");
      }

      if (this.properties) {
         Set var10 = var1.getPropertyKeySet();
         if (var10.size() > 0) {
            this.buf.append("<log4j:properties>\r\n");
            Object[] var5 = var10.toArray();
            Arrays.sort(var5);

            for (int var6 = 0; var6 < var5.length; var6++) {
               String var7 = var5[var6].toString();
               Object var8 = var1.getMDC(var7);
               if (var8 != null) {
                  this.buf.append("<log4j:data name=\"");
                  this.buf.append(Transform.escapeTags(var7));
                  this.buf.append("\" value=\"");
                  this.buf.append(Transform.escapeTags(String.valueOf(var8)));
                  this.buf.append("\"/>\r\n");
               }
            }

            this.buf.append("</log4j:properties>\r\n");
         }
      }

      this.buf.append("</log4j:event>\r\n\r\n");
      return this.buf.toString();
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public void setProperties(boolean var1) {
      this.properties = var1;
   }

   public XMLLayout() {
      this.buf = new StringBuffer(256);
      this.locationInfo = false;
      this.properties = false;
   }
}
