package org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

public class JSONTokener {
   public long recoveredField1269;
   public boolean recoveredField1270;
   public long recoveredField1271;
   public char recoveredField1272;
   public boolean recoveredField1273;
   public Reader recoveredField1274;
   public long recoveredField1275;
   public long recoveredField1276;

   public JSONTokener(InputStream var1) {
      this(new InputStreamReader(var1));
   }

   public String nextTo(String var1) throws org.json.JSONException {
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

   public Object nextValue() throws org.json.JSONException {
      char var1 = this.nextClean();
      switch (var1) {
         case '"':
         case '\'':
            return this.nextString(var1);
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

   public String nextTo(char var1) throws org.json.JSONException {
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

   public String next(int var1) throws org.json.JSONException {
      if (var1 == 0) {
         return "";
      } else {
         char[] var2 = new char[var1];

         for (int var3 = 0; var3 < var1; var3++) {
            var2[var3] = this.next();
            if (this.end()) {
               throw this.syntaxError("Substring bounds error");
            }
         }

         return new String(var2);
      }
   }

   public void back() throws org.json.JSONException {
      if (!this.recoveredField1273 && this.recoveredField1269 > 0L) {
         this.method_01492();
         this.recoveredField1273 = true;
         this.recoveredField1270 = false;
      } else {
         throw new JSONException("Stepping back two steps is not supported");
      }
   }

   public boolean more() throws org.json.JSONException {
      if (this.recoveredField1273) {
         return true;
      } else {
         try {
            this.recoveredField1274.mark(1);
         } catch (IOException var3) {
            throw new JSONException("Unable to preserve stream position", var3);
         }

         try {
            if (this.recoveredField1274.read() <= 0) {
               this.recoveredField1270 = true;
               return false;
            } else {
               this.recoveredField1274.reset();
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
      return " at " + this.recoveredField1269 + " [character " + this.recoveredField1276 + " line " + this.recoveredField1275 + "]";
   }

   public char method_01494(char var1) {
      char var2;
      try {
         long var3 = this.recoveredField1269;
         long var5 = this.recoveredField1276;
         long var7 = this.recoveredField1275;
         this.recoveredField1274.mark(1000000);

         do {
            var2 = this.next();
            if (var2 == 0) {
               this.recoveredField1274.reset();
               this.recoveredField1269 = var3;
               this.recoveredField1276 = var5;
               this.recoveredField1275 = var7;
               return '\u0000';
            }
         } while (var2 != var1);

         this.recoveredField1274.mark(1);
      } catch (IOException var9) {
         throw new JSONException(var9);
      }

      this.back();
      return var2;
   }

   public char next(char var1) throws org.json.JSONException {
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

   public char nextClean() throws org.json.JSONException {
      char var1;
      do {
         var1 = this.next();
      } while (var1 != 0 && var1 <= ' ');

      return var1;
   }

   public String nextString(char var1) throws org.json.JSONException {
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
                        var3.append((char)Integer.parseInt(this.next(4), 16));
                        continue;
                     } catch (NumberFormatException var5) {
                        throw this.syntaxError("Illegal escape.", var5);
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

   public JSONException syntaxError(String var1, Throwable var2) {
      return new JSONException(var1 + this.toString(), var2);
   }

   public void method_01504(int var1) {
      if (var1 > 0) {
         this.recoveredField1269++;
         if (var1 == 13) {
            this.recoveredField1275++;
            this.recoveredField1271 = this.recoveredField1276;
            this.recoveredField1276 = 0L;
         } else if (var1 == 10) {
            if (this.recoveredField1272 != '\r') {
               this.recoveredField1275++;
               this.recoveredField1271 = this.recoveredField1276;
            }

            this.recoveredField1276 = 0L;
         } else {
            this.recoveredField1276++;
         }
      }
   }

   public JSONTokener(String var1) {
      this(new StringReader(var1));
   }

   public JSONTokener(Reader var1) {
      this.recoveredField1274 = (Reader)(var1.markSupported() ? var1 : new BufferedReader(var1));
      this.recoveredField1270 = false;
      this.recoveredField1273 = false;
      this.recoveredField1272 = 0;
      this.recoveredField1269 = 0L;
      this.recoveredField1276 = 1L;
      this.recoveredField1271 = 0L;
      this.recoveredField1275 = 1L;
   }

   public void method_01492() throws org.json.JSONException {
      this.recoveredField1269--;
      if (this.recoveredField1272 == '\r' || this.recoveredField1272 == '\n') {
         this.recoveredField1275--;
         this.recoveredField1276 = this.recoveredField1271;
      } else if (this.recoveredField1276 > 0L) {
         this.recoveredField1276--;
      }
   }

   public boolean end() {
      return this.recoveredField1270 && !this.recoveredField1273;
   }

   public char next() throws org.json.JSONException {
      int var1;
      if (this.recoveredField1273) {
         this.recoveredField1273 = false;
         var1 = this.recoveredField1272;
      } else {
         try {
            var1 = this.recoveredField1274.read();
         } catch (IOException var3) {
            throw new JSONException(var3);
         }
      }

      if (var1 <= 0) {
         this.recoveredField1270 = true;
         return '\u0000';
      } else {
         this.method_01504(var1);
         this.recoveredField1272 = (char)var1;
         return this.recoveredField1272;
      }
   }
}
