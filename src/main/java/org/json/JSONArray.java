package org.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONArray implements Iterable<Object> {
   public ArrayList<Object> recoveredField111;

   public String getString(int var1) throws org.json.JSONException {
      Object var2 = this.get(var1);
      if (var2 instanceof String) {
         return (String)var2;
      } else {
         throw new JSONException("JSONArray[" + var1 + "] not a string.");
      }
   }

   public long method_08580(int var1, long var2) {
      Object var4 = this.opt(var1);
      if (JSONObject.recoveredField2781.equals(var4)) {
         return var2;
      } else if (var4 instanceof Number) {
         return ((Number)var4).longValue();
      } else if (var4 instanceof String) {
         try {
            return new BigDecimal(var4.toString()).longValue();
         } catch (Exception var6) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   public long optLong(int var1) {
      return this.method_08580(var1, 0L);
   }

   public JSONArray put(int var1, long var2) throws org.json.JSONException {
      this.method_08582(var1, new Long(var2));
      return this;
   }

   public JSONArray put(int var1, Map<?, ?> var2) throws org.json.JSONException {
      this.method_08582(var1, new JSONObject(var2));
      return this;
   }

   public String optString(int var1) {
      return this.optString(var1, "");
   }

   public JSONArray(JSONTokener var1) throws org.json.JSONException {
      this();
      if (var1.nextClean() != '[') {
         throw var1.syntaxError("A JSONArray text must start with '['");
      } else if (var1.nextClean() != ']') {
         var1.back();

         while (true) {
            if (var1.nextClean() == ',') {
               var1.back();
               this.recoveredField111.add(JSONObject.recoveredField2781);
            } else {
               var1.back();
               this.recoveredField111.add(var1.nextValue());
            }

            switch (var1.nextClean()) {
               case ',':
                  if (var1.nextClean() == ']') {
                     return;
                  }

                  var1.back();
                  break;
               case ']':
                  return;
               default:
                  throw var1.syntaxError("Expected a ',' or ']'");
            }
         }
      }
   }

   public int method_08579(int var1, int var2) {
      Object var3 = this.opt(var1);
      if (JSONObject.recoveredField2781.equals(var3)) {
         return var2;
      } else if (var3 instanceof Number) {
         return ((Number)var3).intValue();
      } else if (var3 instanceof String) {
         try {
            return new BigDecimal(var3.toString()).intValue();
         } catch (Exception var5) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   public String join(String var1) throws org.json.JSONException {
      int var2 = this.length();
      StringBuilder var3 = new StringBuilder();

      for (int var4 = 0; var4 < var2; var4++) {
         if (var4 > 0) {
            var3.append(var1);
         }

         var3.append(JSONObject.valueToString(this.recoveredField111.get(var4)));
      }

      return var3.toString();
   }

   public double method_08577(int var1, double var2) {
      Object var4 = this.opt(var1);
      if (JSONObject.recoveredField2781.equals(var4)) {
         return var2;
      } else if (var4 instanceof Number) {
         return ((Number)var4).doubleValue();
      } else if (var4 instanceof String) {
         try {
            return Double.parseDouble((String)var4);
         } catch (Exception var6) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   public JSONArray put(int var1, int var2) throws org.json.JSONException {
      this.method_08582(var1, new Integer(var2));
      return this;
   }

   public JSONArray method_08582(int var1, Object var2) throws org.json.JSONException {
      JSONObject.testValidity(var2);
      if (var1 < 0) {
         throw new JSONException("JSONArray[" + var1 + "] not found.");
      } else {
         if (var1 < this.length()) {
            this.recoveredField111.set(var1, var2);
         } else if (var1 == this.length()) {
            this.put(var2);
         } else {
            this.recoveredField111.ensureCapacity(var1 + 1);

            while (var1 != this.length()) {
               this.put(JSONObject.recoveredField2781);
            }

            this.put(var2);
         }

         return this;
      }
   }

   public JSONArray() {
      this.recoveredField111 = new ArrayList<>();
   }

   public boolean isNull(int var1) {
      return JSONObject.recoveredField2781.equals(this.opt(var1));
   }

   public JSONArray getJSONArray(int var1) throws org.json.JSONException {
      Object var2 = this.get(var1);
      if (var2 instanceof JSONArray) {
         return (JSONArray)var2;
      } else {
         throw new JSONException("JSONArray[" + var1 + "] is not a JSONArray.");
      }
   }

   public JSONArray put(boolean var1) {
      this.put(var1 ? Boolean.TRUE : Boolean.FALSE);
      return this;
   }

   public List<Object> toList() {
      ArrayList var1 = new ArrayList(this.recoveredField111.size());

      for (Object var3 : this.recoveredField111) {
         if (var3 == null || JSONObject.recoveredField2781.equals(var3)) {
            var1.add(null);
         } else if (var3 instanceof JSONArray) {
            var1.add(((JSONArray)var3).toList());
         } else if (var3 instanceof JSONObject) {
            var1.add(((JSONObject)var3).method_07187());
         } else {
            var1.add(var3);
         }
      }

      return var1;
   }

   public long method_08567(int var1) {
      Object var2 = this.get(var1);

      try {
         return var2 instanceof Number ? ((Number)var2).longValue() : Long.parseLong((String)var2);
      } catch (Exception var4) {
         throw new JSONException("JSONArray[" + var1 + "] is not a number.", var4);
      }
   }

   public <E extends Enum<E>> E method_08593(Class<E> var1, int var2) {
      Enum var3 = this.optEnum(var1, var2);
      if (var3 == null) {
         throw new JSONException("JSONArray[" + var2 + "] is not an enum of type " + JSONObject.quote(var1.getSimpleName()) + ".");
      } else {
         return (E)var3;
      }
   }

   public boolean optBoolean(int var1) {
      return this.optBoolean(var1, false);
   }

   public Object method_08619(String var1) {
      return this.method_08620(new JSONPointer(var1));
   }

   public <E extends Enum<E>> E optEnum(Class<E> var1, int var2, E var3) {
      try {
         Object var4 = this.opt(var2);
         if (JSONObject.recoveredField2781.equals(var4)) {
            return (E)var3;
         } else {
            return (E)(var1.isAssignableFrom(var4.getClass()) ? var4 : Enum.valueOf(var1, var4.toString()));
         }
      } catch (IllegalArgumentException var6) {
         return (E)var3;
      } catch (NullPointerException var7) {
         return (E)var3;
      }
   }

   public float method_08605(int var1) {
      return this.method_08578(var1, Float.NaN);
   }

   public int optInt(int var1) {
      return this.method_08579(var1, 0);
   }

   public String toString(int var1) throws org.json.JSONException {
      StringWriter var2 = new StringWriter();
      synchronized (var2.getBuffer()) {
         return this.method_08592(var2, var1, 0).toString();
      }
   }

   public BigDecimal method_08584(int var1, BigDecimal var2) {
      Object var3 = this.opt(var1);
      if (JSONObject.recoveredField2781.equals(var3)) {
         return var2;
      } else if (var3 instanceof BigDecimal) {
         return (BigDecimal)var3;
      } else if (var3 instanceof BigInteger) {
         return new BigDecimal((BigInteger)var3);
      } else if (var3 instanceof Double || var3 instanceof Float) {
         return new BigDecimal(((Number)var3).doubleValue());
      } else if (!(var3 instanceof Long) && !(var3 instanceof Integer) && !(var3 instanceof Short) && !(var3 instanceof Byte)) {
         try {
            return new BigDecimal(var3.toString());
         } catch (Exception var5) {
            return var2;
         }
      } else {
         return new BigDecimal(((Number)var3).longValue());
      }
   }

   public Object method_08599(JSONPointer var1) {
      try {
         return var1.queryFrom(this);
      } catch (JSONPointerException var3) {
         return null;
      }
   }

   public JSONArray put(int var1) {
      this.put(new Integer(var1));
      return this;
   }

   public float method_08578(int var1, float var2) {
      Object var3 = this.opt(var1);
      if (JSONObject.recoveredField2781.equals(var3)) {
         return var2;
      } else if (var3 instanceof Number) {
         return ((Number)var3).floatValue();
      } else if (var3 instanceof String) {
         try {
            return Float.parseFloat((String)var3);
         } catch (Exception var5) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   public boolean optBoolean(int var1, boolean var2) {
      try {
         return this.getBoolean(var1);
      } catch (Exception var4) {
         return var2;
      }
   }

   @Override
   public String toString() {
      try {
         return this.toString(0);
      } catch (Exception var2) {
         return null;
      }
   }

   public Number method_08601(int var1) {
      return this.method_08581(var1, null);
   }

   public Object method_08572(String var1) {
      return this.method_08599(new JSONPointer(var1));
   }

   public JSONArray put(Map<?, ?> var1) {
      this.put(new JSONObject(var1));
      return this;
   }

   public boolean method_08595(Object var1) {
      if (!(var1 instanceof JSONArray)) {
         return false;
      } else {
         int var2 = this.length();
         if (var2 != ((JSONArray)var1).length()) {
            return false;
         } else {
            for (int var3 = 0; var3 < var2; var3++) {
               Object var4 = this.recoveredField111.get(var3);
               Object var5 = ((JSONArray)var1).recoveredField111.get(var3);
               if (var4 == var5) {
                  return true;
               }

               if (var4 == null) {
                  return false;
               }

               if (var4 instanceof JSONObject) {
                  if (!((JSONObject)var4).method_07183(var5)) {
                     return false;
                  }
               } else if (var4 instanceof JSONArray) {
                  if (!((JSONArray)var4).method_08595(var5)) {
                     return false;
                  }
               } else if (!var4.equals(var5)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public Number method_08581(int var1, Number var2) {
      Object var3 = this.opt(var1);
      if (JSONObject.recoveredField2781.equals(var3)) {
         return var2;
      } else if (var3 instanceof Number) {
         return (Number)var3;
      } else if (var3 instanceof String) {
         try {
            return JSONObject.method_07212((String)var3);
         } catch (Exception var5) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   public float method_08604(int var1) {
      Object var2 = this.get(var1);

      try {
         return var2 instanceof Number ? ((Number)var2).floatValue() : Float.parseFloat(var2.toString());
      } catch (Exception var4) {
         throw new JSONException("JSONArray[" + var1 + "] is not a number.", var4);
      }
   }

   public JSONObject toJSONObject(JSONArray var1) throws org.json.JSONException {
      if (var1 != null && var1.length() != 0 && this.length() != 0) {
         JSONObject var2 = new JSONObject(var1.length());

         for (int var3 = 0; var3 < var1.length(); var3++) {
            var2.put(var1.getString(var3), this.opt(var3));
         }

         return var2;
      } else {
         return null;
      }
   }

   public JSONArray(Collection<?> var1) {
      if (var1 == null) {
         this.recoveredField111 = new ArrayList<>();
      } else {
         this.recoveredField111 = new ArrayList<>(var1.size());

         for (Object var3 : var1) {
            this.recoveredField111.add(JSONObject.method_07188(var3));
         }
      }
   }

   public Object opt(int var1) {
      return var1 >= 0 && var1 < this.length() ? this.recoveredField111.get(var1) : null;
   }

   public double method_08606(int var1) {
      Object var2 = this.get(var1);

      try {
         return var2 instanceof Number ? ((Number)var2).doubleValue() : Double.parseDouble((String)var2);
      } catch (Exception var4) {
         throw new JSONException("JSONArray[" + var1 + "] is not a number.", var4);
      }
   }

   public JSONArray put(Object var1) {
      this.recoveredField111.add(var1);
      return this;
   }

   public int length() {
      return this.recoveredField111.size();
   }

   public JSONArray put(int var1, boolean var2) throws org.json.JSONException {
      this.method_08582(var1, var2 ? Boolean.TRUE : Boolean.FALSE);
      return this;
   }

   public JSONArray(String var1) throws org.json.JSONException {
      this(new JSONTokener(var1));
   }

   public Number method_08610(int var1) {
      Object var2 = this.get(var1);

      try {
         return var2 instanceof Number ? (Number)var2 : JSONObject.method_07212(var2.toString());
      } catch (Exception var4) {
         throw new JSONException("JSONArray[" + var1 + "] is not a number.", var4);
      }
   }

   public JSONArray put(int var1, Collection<?> var2) throws org.json.JSONException {
      this.method_08582(var1, new JSONArray(var2));
      return this;
   }

   public JSONArray(Object var1) throws org.json.JSONException {
      this();
      if (!var1.getClass().isArray()) {
         throw new JSONException("JSONArray initial value should be a string or collection or array.");
      } else {
         int var2 = Array.getLength(var1);
         this.recoveredField111.ensureCapacity(var2);

         for (int var3 = 0; var3 < var2; var3++) {
            this.put(JSONObject.method_07188(Array.get(var1, var3)));
         }
      }
   }

   public BigDecimal method_08563(int var1) throws org.json.JSONException {
      Object var2 = this.get(var1);

      try {
         return new BigDecimal(var2.toString());
      } catch (Exception var4) {
         throw new JSONException("JSONArray[" + var1 + "] could not convert to BigDecimal.", var4);
      }
   }

   public double optDouble(int var1) {
      return this.method_08577(var1, Double.NaN);
   }

   public Object get(int var1) throws org.json.JSONException {
      Object var2 = this.opt(var1);
      if (var2 == null) {
         throw new JSONException("JSONArray[" + var1 + "] not found.");
      } else {
         return var2;
      }
   }

   public String optString(int var1, String var2) {
      Object var3 = this.opt(var1);
      return JSONObject.recoveredField2781.equals(var3) ? var2 : var3.toString();
   }

   public JSONArray optJSONArray(int var1) {
      Object var2 = this.opt(var1);
      return var2 instanceof JSONArray ? (JSONArray)var2 : null;
   }

   @Override
   public Iterator<Object> iterator() {
      return this.recoveredField111.iterator();
   }

   public JSONArray put(double var1) throws org.json.JSONException {
      Double var3 = new Double(var1);
      JSONObject.testValidity(var3);
      this.put(var3);
      return this;
   }

   public BigInteger method_08585(int var1, BigInteger var2) {
      Object var3 = this.opt(var1);
      if (JSONObject.recoveredField2781.equals(var3)) {
         return var2;
      } else if (var3 instanceof BigInteger) {
         return (BigInteger)var3;
      } else if (var3 instanceof BigDecimal) {
         return ((BigDecimal)var3).toBigInteger();
      } else if (var3 instanceof Double || var3 instanceof Float) {
         return new BigDecimal(((Number)var3).doubleValue()).toBigInteger();
      } else if (!(var3 instanceof Long) && !(var3 instanceof Integer) && !(var3 instanceof Short) && !(var3 instanceof Byte)) {
         try {
            String var4 = var3.toString();
            return JSONObject.method_07192(var4) ? new BigDecimal(var4).toBigInteger() : new BigInteger(var4);
         } catch (Exception var5) {
            return var2;
         }
      } else {
         return BigInteger.valueOf(((Number)var3).longValue());
      }
   }

   public Object remove(int var1) {
      return var1 >= 0 && var1 < this.length() ? this.recoveredField111.remove(var1) : null;
   }

   public JSONArray put(long var1) {
      this.put(new Long(var1));
      return this;
   }

   public JSONObject getJSONObject(int var1) throws org.json.JSONException {
      Object var2 = this.get(var1);
      if (var2 instanceof JSONObject) {
         return (JSONObject)var2;
      } else {
         throw new JSONException("JSONArray[" + var1 + "] is not a JSONObject.");
      }
   }

   public boolean getBoolean(int var1) throws org.json.JSONException {
      Object var2 = this.get(var1);
      if (!var2.equals(Boolean.FALSE) && (!(var2 instanceof String) || !((String)var2).equalsIgnoreCase("false"))) {
         if (!var2.equals(Boolean.TRUE) && (!(var2 instanceof String) || !((String)var2).equalsIgnoreCase("true"))) {
            throw new JSONException("JSONArray[" + var1 + "] is not a boolean.");
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   public BigInteger method_08560(int var1) throws org.json.JSONException {
      Object var2 = this.get(var1);

      try {
         return new BigInteger(var2.toString());
      } catch (Exception var4) {
         throw new JSONException("JSONArray[" + var1 + "] could not convert to BigInteger.", var4);
      }
   }

   public JSONObject optJSONObject(int var1) {
      Object var2 = this.opt(var1);
      return var2 instanceof JSONObject ? (JSONObject)var2 : null;
   }

   public Writer method_08592(Writer var1, int var2, int var3) throws org.json.JSONException {
      try {
         boolean var4 = false;
         int var5 = this.length();
         var1.write(91);
         if (var5 == 1) {
            try {
               JSONObject.method_07207(var1, this.recoveredField111.get(0), var2, var3);
            } catch (Exception var10) {
               throw new JSONException("Unable to write JSONArray value at index: 0", var10);
            }
         } else if (var5 != 0) {
            int var6 = var3 + var2;

            for (int var7 = 0; var7 < var5; var7++) {
               if (var4) {
                  var1.write(44);
               }

               if (var2 > 0) {
                  var1.write(10);
               }

               JSONObject.indent(var1, var6);

               try {
                  JSONObject.method_07207(var1, this.recoveredField111.get(var7), var2, var6);
               } catch (Exception var9) {
                  throw new JSONException("Unable to write JSONArray value at index: " + var7, var9);
               }

               var4 = true;
            }

            if (var2 > 0) {
               var1.write(10);
            }

            JSONObject.indent(var1, var3);
         }

         var1.write(93);
         return var1;
      } catch (IOException var11) {
         throw new JSONException(var11);
      }
   }

   public JSONArray put(Collection<?> var1) {
      this.put(new JSONArray(var1));
      return this;
   }

   public int method_08603(int var1) {
      Object var2 = this.get(var1);

      try {
         return var2 instanceof Number ? ((Number)var2).intValue() : Integer.parseInt((String)var2);
      } catch (Exception var4) {
         throw new JSONException("JSONArray[" + var1 + "] is not a number.", var4);
      }
   }

   public Object method_08620(JSONPointer var1) {
      return var1.queryFrom(this);
   }

   public JSONArray put(int var1, double var2) throws org.json.JSONException {
      this.method_08582(var1, new Double(var2));
      return this;
   }

   public <E extends Enum<E>> E optEnum(Class<E> var1, int var2) {
      return this.optEnum(var1, var2, null);
   }

   public Writer write(Writer var1) throws org.json.JSONException {
      return this.method_08592(var1, 0, 0);
   }
}
