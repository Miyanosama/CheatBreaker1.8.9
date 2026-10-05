package org.json;

import java.io.IOException;

public class JSONWriter {
   public Appendable recoveredField1299;
   public boolean comma = false;
   public char mode = 'i';
   public int top;
   public static final int recoveredField1300 = 200;
   public JSONObject[] stack = new JSONObject[200];

   public JSONWriter method_23785(String var1) {
      if (var1 == null) {
         throw new JSONException("Null key.");
      } else if (this.mode == 'k') {
         try {
            this.stack[this.top - 1].putOnce(var1, Boolean.TRUE);
            if (this.comma) {
               this.recoveredField1299.append(',');
            }

            this.recoveredField1299.append(JSONObject.quote(var1));
            this.recoveredField1299.append(':');
            this.comma = false;
            this.mode = 'o';
            return this;
         } catch (IOException var3) {
            throw new JSONException(var3);
         }
      } else {
         throw new JSONException("Misplaced key.");
      }
   }

   public JSONWriter value(long var1) throws org.json.JSONException {
      return this.append(Long.toString(var1));
   }

   public JSONWriter value(Object var1) throws org.json.JSONException {
      return this.append(JSONObject.valueToString(var1));
   }

   public JSONWriter append(String var1) throws org.json.JSONException {
      if (var1 == null) {
         throw new JSONException("Null pointer");
      } else if (this.mode != 'o' && this.mode != 'a') {
         throw new JSONException("Value out of sequence.");
      } else {
         try {
            if (this.comma && this.mode == 'a') {
               this.recoveredField1299.append(',');
            }

            this.recoveredField1299.append(var1);
         } catch (IOException var3) {
            throw new JSONException(var3);
         }

         if (this.mode == 'o') {
            this.mode = 'k';
         }

         this.comma = true;
         return this;
      }
   }

   public JSONWriter endArray() throws org.json.JSONException {
      return this.end('a', ']');
   }

   public JSONWriter value(boolean var1) throws org.json.JSONException {
      return this.append(var1 ? "true" : "false");
   }

   public JSONWriter(Appendable var1) {
      this.top = 0;
      this.recoveredField1299 = var1;
   }

   public void push(JSONObject var1) throws org.json.JSONException {
      if (this.top >= 200) {
         throw new JSONException("Nesting too deep.");
      } else {
         this.stack[this.top] = var1;
         this.mode = (char)(var1 == null ? 97 : 107);
         this.top++;
      }
   }

   public JSONWriter object() throws org.json.JSONException {
      if (this.mode == 'i') {
         this.mode = 'o';
      }

      if (this.mode != 'o' && this.mode != 'a') {
         throw new JSONException("Misplaced object.");
      } else {
         this.append("{");
         this.push(new JSONObject());
         this.comma = false;
         return this;
      }
   }

   public void pop(char var1) throws org.json.JSONException {
      if (this.top <= 0) {
         throw new JSONException("Nesting error.");
      } else {
         int var2 = this.stack[this.top - 1] == null ? 97 : 107;
         if (var2 != var1) {
            throw new JSONException("Nesting error.");
         } else {
            this.top--;
            this.mode = (char)(this.top == 0 ? 100 : (this.stack[this.top - 1] == null ? 97 : 107));
         }
      }
   }

   public JSONWriter end(char var1, char var2) throws org.json.JSONException {
      if (this.mode != var1) {
         throw new JSONException(var1 == 'a' ? "Misplaced endArray." : "Misplaced endObject.");
      } else {
         this.pop(var1);

         try {
            this.recoveredField1299.append(var2);
         } catch (IOException var4) {
            throw new JSONException(var4);
         }

         this.comma = true;
         return this;
      }
   }

   public JSONWriter endObject() throws org.json.JSONException {
      return this.end('k', '}');
   }

   public JSONWriter array() throws org.json.JSONException {
      if (this.mode != 'i' && this.mode != 'o' && this.mode != 'a') {
         throw new JSONException("Misplaced array.");
      } else {
         this.push(null);
         this.append("[");
         this.comma = false;
         return this;
      }
   }

   public JSONWriter value(double var1) throws org.json.JSONException {
      return this.value(new Double(var1));
   }
}
