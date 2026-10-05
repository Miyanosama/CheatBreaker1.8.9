package org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import net.minecraft.entity.passive.EntityBat;
import net.optifine.util.CompoundKey;
import recovered.unidentified.UnidentifiedClass1381;

public class JSONTokener {
   public long field_0004;
   public UnidentifiedClass1381 field_0008;
   public boolean field_0001;
   public long field_0002;
   public CompoundKey field_0009;
   public char field_0007;
   public boolean field_0003;
   public Reader field_0010;
   public EntityBat field_0000;
   public long field_0005;
   public long field_0006;

   public JSONTokener(InputStream var1) {
      this(new InputStreamReader(var1));
   }

   public String nextTo(String var1) {
      StringBuilder var3 = new StringBuilder();

      while (true) {
         char var2 = this.next();
         if (var1.indexOf(var2) >= 0 || var2 == 0 || var2 == '\n' || var2 == '\r') {
            if (var2 != 0) {
               this.back();
            }

            return var3.toString().trim();
         }

         var3.append(var2);
      }
   }

   public static int dehexchar(char var0) {
      if (var0 >= '0' && var0 <= '9') {
         return var0 - 48;
      } else if (var0 >= 'A' && var0 <= 'F') {
         return var0 - 55;
      } else {
         return var0 >= 97 && var0 <= 102 ? var0 - 87 : -1;
      }
   }

   public Object method_01502() {
      char var1 = this.method_01490();
      switch (var1) {
         case '"':
         case '\'':
            return this.method_01491(var1);
         case '[':
            this.back();
            return new JSONArray(this);
         case '{':
            this.back();
            return new JSONObject(this);
         default:
            StringBuilder var3;
            for (var3 = new StringBuilder(); var1 >= ' ' && ",:]}/\\\"[{;=#".indexOf(var1) < 0; var1 = this.next()) {
               var3.append(var1);
            }

            this.back();
            String var2 = var3.toString().trim();
            if ("".equals(var2)) {
               throw this.syntaxError("Missing value");
            } else {
               return JSONObject.stringToValue(var2);
            }
      }
   }

   public String nextTo(char var1) {
      StringBuilder var2 = new StringBuilder();

      while (true) {
         char var3 = this.next();
         if (var3 == var1 || var3 == 0 || var3 == '\n' || var3 == '\r') {
            if (var3 != 0) {
               this.back();
            }

            return var2.toString().trim();
         }

         var2.append(var3);
      }
   }

   public String method_01496(int var1) {
      if (var1 == 0) {
         return "";
      } else {
         char[] var2 = new char[var1];

         for (int var3 = 0; var3 < var1; var3++) {
            var2[var3] = this.next();
            if (this.method_01506()) {
               throw this.syntaxError("Substring bounds error");
            }
         }

         return new String(var2);
      }
   }

   public void back() {
      if (!this.field_0003 && this.field_0004 > (997238771324427908L & -997238772514652086L)) {
         this.method_01492();
         this.field_0003 = true;
         this.field_0001 = false;
      } else {
         throw new JSONException("Stepping back two steps is not supported");
      }
   }

   public boolean more() {
      if (this.field_0003) {
         return true;
      } else {
         try {
            this.field_0010.mark(1);
         } catch (IOException var3) {
            throw new JSONException("Unable to preserve stream position", var3);
         }

         try {
            if (this.field_0010.read() <= 0) {
               this.field_0001 = true;
               return false;
            } else {
               this.field_0010.reset();
               return true;
            }
         } catch (IOException var2) {
            throw new JSONException("Unable to read the next character from the stream", var2);
         }
      }
   }

   public JSONException syntaxError(String var1) {
      return new JSONException(var1 + this.toString());
   }

   @Override
   public String toString() {
      return " at " + this.field_0004 + " [character " + this.field_0006 + " line " + this.field_0005 + "]";
   }

   public char method_01494(char var1) {
      char var2;
      try {
         long var3 = this.field_0004;
         long var5 = this.field_0006;
         long var7 = this.field_0005;
         this.field_0010.mark(1000000);

         do {
            var2 = this.next();
            if (var2 == 0) {
               this.field_0010.reset();
               this.field_0004 = var3;
               this.field_0006 = var5;
               this.field_0005 = var7;
               return '\u0000';
            }
         } while (var2 != var1);

         this.field_0010.mark(1);
      } catch (IOException var9) {
         throw new JSONException(var9);
      }

      this.back();
      return var2;
   }

   public char next(char var1) {
      char var2 = this.next();
      if (var2 != var1) {
         if (var2 > 0) {
            throw this.syntaxError("Expected '" + var1 + "' and instead saw '" + var2 + "'");
         } else {
            throw this.syntaxError("Expected '" + var1 + "' and instead saw ''");
         }
      } else {
         return var2;
      }
   }

   public char method_01490() {
      char var1;
      do {
         var1 = this.next();
      } while (var1 != 0 && var1 <= ' ');

      return var1;
   }

   public String method_01491(char var1) {
      StringBuilder var3 = new StringBuilder();

      while (true) {
         char var2 = this.next();
         switch (var2) {
            case '\u0000':
            case '\n':
            case '\r':
               throw this.syntaxError("Unterminated string");
            case '\\':
               var2 = this.next();
               switch (var2) {
                  case '"':
                  case '\'':
                  case '/':
                  case '\\':
                     var3.append(var2);
                     continue;
                  case 'b':
                     var3.append('\b');
                     continue;
                  case 'f':
                     var3.append('\f');
                     continue;
                  case 'n':
                     var3.append('\n');
                     continue;
                  case 'r':
                     var3.append('\r');
                     continue;
                  case 't':
                     var3.append('\t');
                     continue;
                  case 'u':
                     try {
                        var3.append((char)Integer.parseInt(this.method_01496(4), 16));
                        continue;
                     } catch (NumberFormatException var5) {
                        throw this.method_01498("Illegal escape.", var5);
                     }
                  default:
                     throw this.syntaxError("Illegal escape.");
               }
            default:
               if (var2 == var1) {
                  return var3.toString();
               }

               var3.append(var2);
         }
      }
   }

   public JSONException method_01498(String var1, Throwable var2) {
      return new JSONException(var1 + this.toString(), var2);
   }

   public void method_01504(int var1) {
      if (var1 > 0) {
         this.field_0004 += 34080779L & 1694536705L;
         if (var1 == 13) {
            this.field_0005 += 281346435L & 706891781L;
            this.field_0002 = this.field_0006;
            this.field_0006 = 944791487520981046L & 336671872L;
         } else if (var1 == 10) {
            if (this.field_0007 != '\r') {
               this.field_0005 += 58818625L & -8291142522702636493L;
               this.field_0002 = this.field_0006;
            }

            this.field_0006 = 335545254L & -8048856369756586992L;
         } else {
            this.field_0006 += -4322935347450216447L & 4322935345695229189L;
         }
      }
   }

   public JSONTokener(String var1) {
      this(new StringReader(var1));
   }

   public JSONTokener(Reader var1) {
      this.field_0010 = (Reader)(var1.markSupported() ? var1 : new BufferedReader(var1));
      this.field_0001 = false;
      this.field_0003 = false;
      this.field_0007 = 0;
      this.field_0004 = 201334784L & 17991680L;
      this.field_0006 = 337969665L & 21165075L;
      this.field_0002 = 2965298784046686214L & -2965298786093311192L;
      this.field_0005 = 17174565L & -2932942069510422519L;
   }

   public void method_01492() {
      this.field_0004 -= 606528521L & 295240455L;
      if (this.field_0007 == '\r' || this.field_0007 == '\n') {
         this.field_0005 -= 7910410884266066469L & 34533459L;
         this.field_0006 = this.field_0002;
      } else if (this.field_0006 > (218106272L & -4143862428179783587L)) {
         this.field_0006 -= -3802205812677410303L & 3802205811980118195L;
      }
   }

   public boolean method_01506() {
      return this.field_0001 && !this.field_0003;
   }

   public char next() {
      int var1;
      if (this.field_0003) {
         this.field_0003 = false;
         var1 = this.field_0007;
      } else {
         try {
            var1 = this.field_0010.read();
         } catch (IOException var3) {
            throw new JSONException(var3);
         }
      }

      if (var1 <= 0) {
         this.field_0001 = true;
         return '\u0000';
      } else {
         this.method_01504(var1);
         this.field_0007 = (char)var1;
         return this.field_0007;
      }
   }
}
