package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class PatternParser {
   public static final int recoveredField3532 = 2004;
   public static final int recoveredField3533 = 1000;
   public static final int recoveredField3534 = 5;
   public static final int recoveredField3535 = 3;
   public PatternConverter head;
   public int patternLength;
   public int state;
   public int i;
   public PatternConverter tail;
   public static final int recoveredField3536 = 1001;
   public static final int recoveredField3537 = 4;
   public static final char recoveredField3538 = 37;
   public static final int recoveredField3539 = 2001;
   public static final int recoveredField3540 = 1003;
   public static Class class$java$text$DateFormat;
   public static final int recoveredField3541 = 1004;
   public StringBuffer currentLiteral = new StringBuffer(32);
   public static final int recoveredField3542 = 2000;
   public static final int recoveredField3543 = 1002;
   public static final int recoveredField3544 = 2002;
   public FormattingInfo formattingInfo = new FormattingInfo();
   public String pattern;
   public static final int recoveredField3545 = 1;
   public static final int recoveredField3546 = 2003;
   public static final int recoveredField3547 = 0;

   public void addToList(PatternConverter var1) {
      if (this.head == null) {
         this.head = this.tail = var1;
      } else {
         this.tail.next = var1;
         this.tail = var1;
      }
   }

   public String extractOption() {
      if (this.i < this.patternLength && this.pattern.charAt(this.i) == '{') {
         int var1 = this.pattern.indexOf(125, this.i);
         if (var1 > this.i) {
            String var2 = this.pattern.substring(this.i + 1, var1);
            this.i = var1 + 1;
            return var2;
         }
      }

      return null;
   }

   public PatternConverter parse() {
      this.i = 0;

      while (this.i < this.patternLength) {
         char var1 = this.pattern.charAt(this.i++);
         switch (this.state) {
            case 0:
               if (this.i == this.patternLength) {
                  this.currentLiteral.append(var1);
               } else if (var1 == '%') {
                  switch (this.pattern.charAt(this.i)) {
                     case '%':
                        this.currentLiteral.append(var1);
                        this.i++;
                        break;
                     case 'n':
                        this.currentLiteral.append(Layout.LINE_SEP);
                        this.i++;
                        break;
                     default:
                        if (this.currentLiteral.length() != 0) {
                           this.addToList(new PatternParser.LiteralPatternConverter(this.currentLiteral.toString()));
                        }

                        this.currentLiteral.setLength(0);
                        this.currentLiteral.append(var1);
                        this.state = 1;
                        this.formattingInfo.reset();
                  }
               } else {
                  this.currentLiteral.append(var1);
               }
               break;
            case 1:
               this.currentLiteral.append(var1);
               switch (var1) {
                  case '-':
                     this.formattingInfo.leftAlign = true;
                     break;
                  case '.':
                     this.state = 3;
                     break;
                  default:
                     if (var1 >= '0' && var1 <= '9') {
                        this.formattingInfo.min = var1 - '0';
                        this.state = 4;
                     } else {
                        this.finalizeConverter(var1);
                     }
               }
            case 2:
            default:
               break;
            case 3:
               this.currentLiteral.append(var1);
               if (var1 >= '0' && var1 <= '9') {
                  this.formattingInfo.max = var1 - '0';
                  this.state = 5;
                  break;
               }

               LogLog.error("Error occured in position " + this.i + ".\n Was expecting digit, instead got char \"" + var1 + "\".");
               this.state = 0;
               break;
            case 4:
               this.currentLiteral.append(var1);
               if (var1 >= '0' && var1 <= '9') {
                  this.formattingInfo.min = this.formattingInfo.min * 10 + (var1 - '0');
               } else if (var1 == '.') {
                  this.state = 3;
               } else {
                  this.finalizeConverter(var1);
               }
               break;
            case 5:
               this.currentLiteral.append(var1);
               if (var1 >= '0' && var1 <= '9') {
                  this.formattingInfo.max = this.formattingInfo.max * 10 + (var1 - '0');
               } else {
                  this.finalizeConverter(var1);
                  this.state = 0;
               }
         }
      }

      if (this.currentLiteral.length() != 0) {
         this.addToList(new PatternParser.LiteralPatternConverter(this.currentLiteral.toString()));
      }

      return this.head;
   }

   public void finalizeConverter(char var1) {
      Object var2 = null;
      switch (var1) {
         case 'C':
            var2 = new PatternParser.ClassNamePatternConverter(this.formattingInfo, this.extractPrecisionOption());
            this.currentLiteral.setLength(0);
            break;
         case 'D':
         case 'E':
         case 'G':
         case 'H':
         case 'I':
         case 'J':
         case 'K':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'S':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'Y':
         case 'Z':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         case 'a':
         case 'b':
         case 'e':
         case 'f':
         case 'g':
         case 'h':
         case 'i':
         case 'j':
         case 'k':
         case 'n':
         case 'o':
         case 'q':
         case 's':
         case 'u':
         case 'v':
         case 'w':
         default:
            LogLog.error("Unexpected char [" + var1 + "] at position " + this.i + " in conversion patterrn.");
            var2 = new PatternParser.LiteralPatternConverter(this.currentLiteral.toString());
            this.currentLiteral.setLength(0);
            break;
         case 'F':
            var2 = new PatternParser.LocationPatternConverter(this.formattingInfo, 1004);
            this.currentLiteral.setLength(0);
            break;
         case 'L':
            var2 = new PatternParser.LocationPatternConverter(this.formattingInfo, 1003);
            this.currentLiteral.setLength(0);
            break;
         case 'M':
            var2 = new PatternParser.LocationPatternConverter(this.formattingInfo, 1001);
            this.currentLiteral.setLength(0);
            break;
         case 'X':
            String var6 = this.extractOption();
            var2 = new PatternParser.MDCPatternConverter(this.formattingInfo, var6);
            this.currentLiteral.setLength(0);
            break;
         case 'c':
            var2 = new PatternParser.CategoryPatternConverter(this.formattingInfo, this.extractPrecisionOption());
            this.currentLiteral.setLength(0);
            break;
         case 'd':
            String var3 = "ISO8601";
            String var5 = this.extractOption();
            if (var5 != null) {
               var3 = var5;
            }

            Object var4;
            if (var3.equalsIgnoreCase("ISO8601")) {
               var4 = new ISO8601DateFormat();
            } else if (var3.equalsIgnoreCase("ABSOLUTE")) {
               var4 = new AbsoluteTimeDateFormat();
            } else if (var3.equalsIgnoreCase("DATE")) {
               var4 = new DateTimeDateFormat();
            } else {
               try {
                  var4 = new SimpleDateFormat(var3);
               } catch (IllegalArgumentException var7) {
                  LogLog.error("Could not instantiate SimpleDateFormat with " + var3, var7);
                  var4 = (DateFormat)OptionConverter.instantiateByClassName(
                     "org.apache.log4j.helpers.ISO8601DateFormat",
                     class$java$text$DateFormat == null ? (class$java$text$DateFormat = class$("java.text.DateFormat")) : class$java$text$DateFormat,
                     null
                  );
               }
            }

            var2 = new PatternParser.DatePatternConverter(this.formattingInfo, (DateFormat)var4);
            this.currentLiteral.setLength(0);
            break;
         case 'l':
            var2 = new PatternParser.LocationPatternConverter(this.formattingInfo, 1000);
            this.currentLiteral.setLength(0);
            break;
         case 'm':
            var2 = new PatternParser.BasicPatternConverter(this.formattingInfo, 2004);
            this.currentLiteral.setLength(0);
            break;
         case 'p':
            var2 = new PatternParser.BasicPatternConverter(this.formattingInfo, 2002);
            this.currentLiteral.setLength(0);
            break;
         case 'r':
            var2 = new PatternParser.BasicPatternConverter(this.formattingInfo, 2000);
            this.currentLiteral.setLength(0);
            break;
         case 't':
            var2 = new PatternParser.BasicPatternConverter(this.formattingInfo, 2001);
            this.currentLiteral.setLength(0);
            break;
         case 'x':
            var2 = new PatternParser.BasicPatternConverter(this.formattingInfo, 2003);
            this.currentLiteral.setLength(0);
      }

      this.addConverter((PatternConverter)var2);
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public void addConverter(PatternConverter var1) {
      this.currentLiteral.setLength(0);
      this.addToList(var1);
      this.state = 0;
      this.formattingInfo.reset();
   }

   public PatternParser(String var1) {
      this.pattern = var1;
      this.patternLength = var1.length();
      this.state = 0;
   }

   public int extractPrecisionOption() {
      String var1 = this.extractOption();
      int var2 = 0;
      if (var1 != null) {
         try {
            var2 = Integer.parseInt(var1);
            if (var2 <= 0) {
               LogLog.error("Precision option (" + var1 + ") isn't a positive integer.");
               var2 = 0;
            }
         } catch (NumberFormatException var4) {
            LogLog.error("Category option \"" + var1 + "\" not a decimal integer.", var4);
         }
      }

      return var2;
   }

   public static class BasicPatternConverter extends PatternConverter {
      public int type;

      public BasicPatternConverter(FormattingInfo var1, int var2) {
         super(var1);
         this.type = var2;
      }

      public String convert(LoggingEvent var1) {
         switch (this.type) {
            case 2000:
               return Long.toString(var1.timeStamp - LoggingEvent.getStartTime());
            case 2001:
               return var1.getThreadName();
            case 2002:
               return var1.getLevel().toString();
            case 2003:
               return var1.getNDC();
            case 2004:
               return var1.getRenderedMessage();
            default:
               return null;
         }
      }
   }

   public class CategoryPatternConverter extends PatternParser.NamedPatternConverter {
      public CategoryPatternConverter(FormattingInfo var2, int var3) {
         super(var2, var3);
      }

      public String getFullyQualifiedName(LoggingEvent var1) {
         return var1.getLoggerName();
      }
   }

   public class ClassNamePatternConverter extends PatternParser.NamedPatternConverter {
      public String getFullyQualifiedName(LoggingEvent var1) {
         return var1.getLocationInformation().getClassName();
      }

      public ClassNamePatternConverter(FormattingInfo var2, int var3) {
         super(var2, var3);
      }
   }

   public static class DatePatternConverter extends PatternConverter {
      public Date date = new Date();
      public DateFormat df;

      public String convert(LoggingEvent var1) {
         this.date.setTime(var1.timeStamp);
         String var2 = null;

         try {
            var2 = this.df.format(this.date);
         } catch (Exception var4) {
            LogLog.error("Error occured while converting date.", var4);
         }

         return var2;
      }

      public DatePatternConverter(FormattingInfo var1, DateFormat var2) {
         super(var1);
         this.df = var2;
      }
   }

   public static class LiteralPatternConverter extends PatternConverter {
      public String literal;

      public LiteralPatternConverter(String var1) {
         this.literal = var1;
      }

      public String convert(LoggingEvent var1) {
         return this.literal;
      }

      public void format(StringBuffer var1, LoggingEvent var2) {
         var1.append(this.literal);
      }
   }

   public class LocationPatternConverter extends PatternConverter {
      public int type;

      public String convert(LoggingEvent var1) {
         LocationInfo var2 = var1.getLocationInformation();
         switch (this.type) {
            case 1000:
               return var2.fullInfo;
            case 1001:
               return var2.getMethodName();
            case 1002:
            default:
               return null;
            case 1003:
               return var2.getLineNumber();
            case 1004:
               return var2.getFileName();
         }
      }

      public LocationPatternConverter(FormattingInfo var2, int var3) {
         super(var2);
         this.type = var3;
      }
   }

   public static class MDCPatternConverter extends PatternConverter {
      public String key;

      public String convert(LoggingEvent var1) {
         if (this.key != null) {
            Object var6 = var1.getMDC(this.key);
            return var6 == null ? null : var6.toString();
         } else {
            StringBuffer var2 = new StringBuffer("{");
            Map var3 = var1.getProperties();
            if (var3.size() > 0) {
               Object[] var4 = var3.keySet().toArray();
               Arrays.sort(var4);

               for (int var5 = 0; var5 < var4.length; var5++) {
                  var2.append('{');
                  var2.append(var4[var5]);
                  var2.append(',');
                  var2.append(var3.get(var4[var5]));
                  var2.append('}');
               }
            }

            var2.append('}');
            return var2.toString();
         }
      }

      public MDCPatternConverter(FormattingInfo var1, String var2) {
         super(var1);
         this.key = var2;
      }
   }

   public abstract static class NamedPatternConverter extends PatternConverter {
      public int precision;

      public abstract String getFullyQualifiedName(LoggingEvent var1);

      public String convert(LoggingEvent var1) {
         String var2 = this.getFullyQualifiedName(var1);
         if (this.precision <= 0) {
            return var2;
         } else {
            int var3 = var2.length();
            int var4 = var3 - 1;

            for (int var5 = this.precision; var5 > 0; var5--) {
               var4 = var2.lastIndexOf(46, var4 - 1);
               if (var4 == -1) {
                  return var2;
               }
            }

            return var2.substring(var4 + 1, var3);
         }
      }

      public NamedPatternConverter(FormattingInfo var1, int var2) {
         super(var1);
         this.precision = var2;
      }
   }
}
