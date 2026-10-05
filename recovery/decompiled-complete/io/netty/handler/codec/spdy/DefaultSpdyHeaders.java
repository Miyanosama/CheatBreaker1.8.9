package io.netty.handler.codec.spdy;

import io.netty.handler.codec.http.HttpObjectAggregator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import org.apache.log4j.pattern.FormattingInfo;

public class DefaultSpdyHeaders extends SpdyHeaders {
   public DefaultSpdyHeaders$HeaderEntry[] entries = new DefaultSpdyHeaders$HeaderEntry[17];
   public static int BUCKET_SIZE;
   public HttpObjectAggregator __junk7141494873443673282;
   public FormattingInfo __junk8575147887876196297;
   public DefaultSpdyHeaders$HeaderEntry head = new DefaultSpdyHeaders$HeaderEntry(-1, null, null);

   @Override
   public SpdyHeaders add(String var1, Iterable<?> var2) {
      SpdyCodecUtil.validateHeaderValue(var1);
      int var3 = hash(var1);
      int var4 = index(var3);

      for (Object var6 : var2) {
         String var7 = toString(var6);
         SpdyCodecUtil.validateHeaderValue(var7);
         this.add0(var3, var4, var1, var7);
      }

      return this;
   }

   @Override
   public List<String> getAll(String var1) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         LinkedList var2 = new LinkedList();
         int var3 = hash(var1);
         int var4 = index(var3);

         for (DefaultSpdyHeaders$HeaderEntry var5 = this.entries[var4]; var5 != null; var5 = var5.next) {
            if (var5.hash == var3 && eq(var1, var5.key)) {
               var2.addFirst(var5.value);
            }
         }

         return var2;
      }
   }

   public static int hash(String var0) {
      int var1 = 0;

      for (int var2 = var0.length() - 1; var2 >= 0; var2--) {
         char var3 = var0.charAt(var2);
         if (var3 >= 'A' && var3 <= 'Z') {
            var3 = (char)(var3 + ' ');
         }

         var1 = 31 * var1 + var3;
      }

      if (var1 > 0) {
         return var1;
      } else {
         return var1 == Integer.MIN_VALUE ? Integer.MAX_VALUE : -var1;
      }
   }

   @Override
   public SpdyHeaders set(String var1, Iterable<?> var2) {
      if (var2 == null) {
         throw new NullPointerException("values");
      } else {
         String var3 = var1.toLowerCase();
         SpdyCodecUtil.validateHeaderName(var3);
         int var4 = hash(var3);
         int var5 = index(var4);
         this.remove0(var4, var5, var3);

         for (Object var7 : var2) {
            if (var7 == null) {
               break;
            }

            String var8 = toString(var7);
            SpdyCodecUtil.validateHeaderValue(var8);
            this.add0(var4, var5, var3, var8);
         }

         return this;
      }
   }

   public DefaultSpdyHeaders() {
      this.head.before = this.head.after = this.head;
   }

   public static String toString(Object var0) {
      return var0 == null ? null : var0.toString();
   }

   public void remove0(int var1, int var2, String var3) {
      DefaultSpdyHeaders$HeaderEntry var4 = this.entries[var2];
      if (var4 != null) {
         while (var4.hash == var1 && eq(var3, var4.key)) {
            var4.remove();
            DefaultSpdyHeaders$HeaderEntry var5 = var4.next;
            if (var5 == null) {
               this.entries[var2] = null;
               return;
            }

            this.entries[var2] = var5;
            var4 = var5;
         }

         while (true) {
            DefaultSpdyHeaders$HeaderEntry var6 = var4.next;
            if (var6 == null) {
               return;
            }

            if (var6.hash == var1 && eq(var3, var6.key)) {
               var4.next = var6.next;
               var6.remove();
            } else {
               var4 = var6;
            }
         }
      }
   }

   @Override
   public boolean contains(String var1) {
      return this.get(var1) != null;
   }

   public static int index(int var0) {
      return var0 % 17;
   }

   @Override
   public SpdyHeaders add(String var1, Object var2) {
      String var3 = var1.toLowerCase();
      SpdyCodecUtil.validateHeaderName(var3);
      String var4 = toString(var2);
      SpdyCodecUtil.validateHeaderValue(var4);
      int var5 = hash(var3);
      int var6 = index(var5);
      this.add0(var5, var6, var3, var4);
      return this;
   }

   @Override
   public List<Entry<String, String>> entries() {
      LinkedList var1 = new LinkedList();

      for (DefaultSpdyHeaders$HeaderEntry var2 = this.head.after; var2 != this.head; var2 = var2.after) {
         var1.add(var2);
      }

      return var1;
   }

   public void add0(int var1, int var2, String var3, String var4) {
      DefaultSpdyHeaders$HeaderEntry var5 = this.entries[var2];
      DefaultSpdyHeaders$HeaderEntry var6;
      this.entries[var2] = var6 = new DefaultSpdyHeaders$HeaderEntry(var1, var3, var4);
      var6.next = var5;
      var6.addBefore(this.head);
   }

   @Override
   public String get(String var1) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         int var2 = hash(var1);
         int var3 = index(var2);

         for (DefaultSpdyHeaders$HeaderEntry var4 = this.entries[var3]; var4 != null; var4 = var4.next) {
            if (var4.hash == var2 && eq(var1, var4.key)) {
               return var4.value;
            }
         }

         return null;
      }
   }

   public static boolean eq(String var0, String var1) {
      int var2 = var0.length();
      if (var2 != var1.length()) {
         return false;
      } else {
         for (int var3 = var2 - 1; var3 >= 0; var3--) {
            char var4 = var0.charAt(var3);
            char var5 = var1.charAt(var3);
            if (var4 != var5) {
               if (var4 >= 'A' && var4 <= 'Z') {
                  var4 = (char)(var4 + ' ');
               }

               if (var5 >= 'A' && var5 <= 'Z') {
                  var5 = (char)(var5 + ' ');
               }

               if (var4 != var5) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   @Override
   public boolean isEmpty() {
      return this.head == this.head.after;
   }

   @Override
   public Iterator<Entry<String, String>> iterator() {
      return new DefaultSpdyHeaders$HeaderIterator(this, null);
   }

   @Override
   public SpdyHeaders remove(String var1) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         String var2 = var1.toLowerCase();
         int var3 = hash(var2);
         int var4 = index(var3);
         this.remove0(var3, var4, var2);
         return this;
      }
   }

   @Override
   public SpdyHeaders clear() {
      for (int var1 = 0; var1 < this.entries.length; var1++) {
         this.entries[var1] = null;
      }

      this.head.before = this.head.after = this.head;
      return this;
   }

   @Override
   public SpdyHeaders set(String var1, Object var2) {
      String var3 = var1.toLowerCase();
      SpdyCodecUtil.validateHeaderName(var3);
      String var4 = toString(var2);
      SpdyCodecUtil.validateHeaderValue(var4);
      int var5 = hash(var3);
      int var6 = index(var5);
      this.remove0(var5, var6, var3);
      this.add0(var5, var6, var3, var4);
      return this;
   }

   @Override
   public Set<String> names() {
      TreeSet var1 = new TreeSet();

      for (DefaultSpdyHeaders$HeaderEntry var2 = this.head.after; var2 != this.head; var2 = var2.after) {
         var1.add(var2.key);
      }

      return var1;
   }
}
