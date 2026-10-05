package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import net.minecraft.block.BlockHalfStoneSlabNew;
import net.minecraft.block.BlockStairs;
import net.minecraft.realms.RealmsScrolledSelectionList;
import org.apache.log4j.Layout;
import recovered.unidentified.UnidentifiedClass4731;

public class PatternParser {
   public static int field_0013;
   public static int field_0025;
   public static int field_0012;
   public static int field_0022;
   public PatternConverter head;
   public int patternLength;
   public int state;
   public int i;
   public PatternConverter tail;
   public static int field_0028;
   public static int field_0005;
   public static char field_0014;
   public static int field_0017;
   public static int field_0010;
   public static Class class$java$text$DateFormat;
   public BlockHalfStoneSlabNew field_0024;
   public static int field_0003;
   public StringBuffer currentLiteral = new StringBuffer(32);
   public static int field_0015;
   public static int field_0023;
   public static int field_0002;
   public FormattingInfo formattingInfo = new FormattingInfo();
   public String pattern;
   public UnidentifiedClass4731 field_0000;
   public static int field_0021;
   public static int field_0016;
   public static int field_0019;
   public BlockStairs field_0011;
   public RealmsScrolledSelectionList field_0027;

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
                           this.addToList(new PatternParser$LiteralPatternConverter(this.currentLiteral.toString()));
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
         this.addToList(new PatternParser$LiteralPatternConverter(this.currentLiteral.toString()));
      }

      return this.head;
   }

   public void finalizeConverter(char var1) {
      Object var2 = null;
      switch (var1) {
         case 'C':
            var2 = new PatternParser$ClassNamePatternConverter(this, this.formattingInfo, this.extractPrecisionOption());
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
            var2 = new PatternParser$LiteralPatternConverter(this.currentLiteral.toString());
            this.currentLiteral.setLength(0);
            break;
         case 'F':
            var2 = new PatternParser$LocationPatternConverter(this, this.formattingInfo, 1004);
            this.currentLiteral.setLength(0);
            break;
         case 'L':
            var2 = new PatternParser$LocationPatternConverter(this, this.formattingInfo, 1003);
            this.currentLiteral.setLength(0);
            break;
         case 'M':
            var2 = new PatternParser$LocationPatternConverter(this, this.formattingInfo, 1001);
            this.currentLiteral.setLength(0);
            break;
         case 'X':
            String var6 = this.extractOption();
            var2 = new PatternParser$MDCPatternConverter(this.formattingInfo, var6);
            this.currentLiteral.setLength(0);
            break;
         case 'c':
            var2 = new PatternParser$CategoryPatternConverter(this, this.formattingInfo, this.extractPrecisionOption());
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

            var2 = new PatternParser$DatePatternConverter(this.formattingInfo, (DateFormat)var4);
            this.currentLiteral.setLength(0);
            break;
         case 'l':
            var2 = new PatternParser$LocationPatternConverter(this, this.formattingInfo, 1000);
            this.currentLiteral.setLength(0);
            break;
         case 'm':
            var2 = new PatternParser$BasicPatternConverter(this.formattingInfo, 2004);
            this.currentLiteral.setLength(0);
            break;
         case 'p':
            var2 = new PatternParser$BasicPatternConverter(this.formattingInfo, 2002);
            this.currentLiteral.setLength(0);
            break;
         case 'r':
            var2 = new PatternParser$BasicPatternConverter(this.formattingInfo, 2000);
            this.currentLiteral.setLength(0);
            break;
         case 't':
            var2 = new PatternParser$BasicPatternConverter(this.formattingInfo, 2001);
            this.currentLiteral.setLength(0);
            break;
         case 'x':
            var2 = new PatternParser$BasicPatternConverter(this.formattingInfo, 2003);
            this.currentLiteral.setLength(0);
      }

      this.addConverter((PatternConverter)var2);
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
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
}
