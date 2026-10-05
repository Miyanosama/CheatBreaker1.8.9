package org.apache.log4j;

import java.util.Date;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class HTMLLayout extends Layout {
   public int BUF_SIZE = 256;
   public static final String recoveredField224 = "Title";
   public int MAX_CAPACITY = 1024;
   public String title;
   public static final String recoveredField225 = "LocationInfo";
   public StringBuffer sbuf = new StringBuffer(256);
   public boolean locationInfo = false;
   public static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";

   public String format(LoggingEvent var1) {
      if (this.sbuf.capacity() > 1024) {
         this.sbuf = new StringBuffer(256);
      } else {
         this.sbuf.setLength(0);
      }

      this.sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);
      this.sbuf.append("<td>");
      this.sbuf.append(var1.timeStamp - LoggingEvent.getStartTime());
      this.sbuf.append("</td>" + Layout.LINE_SEP);
      String var2 = Transform.escapeTags(var1.getThreadName());
      this.sbuf.append("<td title=\"" + var2 + " thread\">");
      this.sbuf.append(var2);
      this.sbuf.append("</td>" + Layout.LINE_SEP);
      this.sbuf.append("<td title=\"Level\">");
      if (var1.getLevel().equals(Level.DEBUG)) {
         this.sbuf.append("<font color=\"#339933\">");
         this.sbuf.append(Transform.escapeTags(String.valueOf(var1.getLevel())));
         this.sbuf.append("</font>");
      } else if (var1.getLevel().isGreaterOrEqual(Level.WARN)) {
         this.sbuf.append("<font color=\"#993300\"><strong>");
         this.sbuf.append(Transform.escapeTags(String.valueOf(var1.getLevel())));
         this.sbuf.append("</strong></font>");
      } else {
         this.sbuf.append(Transform.escapeTags(String.valueOf(var1.getLevel())));
      }

      this.sbuf.append("</td>" + Layout.LINE_SEP);
      String var3 = Transform.escapeTags(var1.getLoggerName());
      this.sbuf.append("<td title=\"" + var3 + " category\">");
      this.sbuf.append(var3);
      this.sbuf.append("</td>" + Layout.LINE_SEP);
      if (this.locationInfo) {
         LocationInfo var4 = var1.getLocationInformation();
         this.sbuf.append("<td>");
         this.sbuf.append(Transform.escapeTags(var4.getFileName()));
         this.sbuf.append(':');
         this.sbuf.append(var4.getLineNumber());
         this.sbuf.append("</td>" + Layout.LINE_SEP);
      }

      this.sbuf.append("<td title=\"Message\">");
      this.sbuf.append(Transform.escapeTags(var1.getRenderedMessage()));
      this.sbuf.append("</td>" + Layout.LINE_SEP);
      this.sbuf.append("</tr>" + Layout.LINE_SEP);
      if (var1.getNDC() != null) {
         this.sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : xx-small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
         this.sbuf.append("NDC: " + Transform.escapeTags(var1.getNDC()));
         this.sbuf.append("</td></tr>" + Layout.LINE_SEP);
      }

      String[] var5 = var1.getThrowableStrRep();
      if (var5 != null) {
         this.sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : xx-small;\" colspan=\"6\">");
         this.appendThrowableAsHTML(var5, this.sbuf);
         this.sbuf.append("</td></tr>" + Layout.LINE_SEP);
      }

      return this.sbuf.toString();
   }

   public void setTitle(String var1) {
      this.title = var1;
   }

   public String E_() {
      StringBuffer var1 = new StringBuffer();
      var1.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" + Layout.LINE_SEP);
      var1.append("<html>" + Layout.LINE_SEP);
      var1.append("<head>" + Layout.LINE_SEP);
      var1.append("<title>" + this.title + "</title>" + Layout.LINE_SEP);
      var1.append("<style type=\"text/css\">" + Layout.LINE_SEP);
      var1.append("<!--" + Layout.LINE_SEP);
      var1.append("body, table {font-family: arial,sans-serif; font-size: x-small;}" + Layout.LINE_SEP);
      var1.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);
      var1.append("-->" + Layout.LINE_SEP);
      var1.append("</style>" + Layout.LINE_SEP);
      var1.append("</head>" + Layout.LINE_SEP);
      var1.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" + Layout.LINE_SEP);
      var1.append("<hr size=\"1\" noshade>" + Layout.LINE_SEP);
      var1.append("Log session start time " + new Date() + "<br>" + Layout.LINE_SEP);
      var1.append("<br>" + Layout.LINE_SEP);
      var1.append("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" + Layout.LINE_SEP);
      var1.append("<tr>" + Layout.LINE_SEP);
      var1.append("<th>Time</th>" + Layout.LINE_SEP);
      var1.append("<th>Thread</th>" + Layout.LINE_SEP);
      var1.append("<th>Level</th>" + Layout.LINE_SEP);
      var1.append("<th>Category</th>" + Layout.LINE_SEP);
      if (this.locationInfo) {
         var1.append("<th>File:Line</th>" + Layout.LINE_SEP);
      }

      var1.append("<th>Message</th>" + Layout.LINE_SEP);
      var1.append("</tr>" + Layout.LINE_SEP);
      return var1.toString();
   }

   public String getTitle() {
      return this.title;
   }

   public void activateOptions() {
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public boolean ignoresThrowable() {
      return false;
   }

   public String getFooter() {
      StringBuffer var1 = new StringBuffer();
      var1.append("</table>" + Layout.LINE_SEP);
      var1.append("<br>" + Layout.LINE_SEP);
      var1.append("</body></html>");
      return var1.toString();
   }

   public String getContentType() {
      return "text/html";
   }

   public HTMLLayout() {
      this.title = "Log4J Log Messages";
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public void appendThrowableAsHTML(String[] var1, StringBuffer var2) {
      if (var1 != null) {
         int var3 = var1.length;
         if (var3 == 0) {
            return;
         }

         var2.append(Transform.escapeTags(var1[0]));
         var2.append(Layout.LINE_SEP);

         for (int var4 = 1; var4 < var3; var4++) {
            var2.append(TRACE_PREFIX);
            var2.append(Transform.escapeTags(var1[var4]));
            var2.append(Layout.LINE_SEP);
         }
      }
   }
}
