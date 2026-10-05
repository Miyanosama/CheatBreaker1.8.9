package io.netty.handler.codec.http;

import com.cheatbreaker.client.module.type.PackDisplayModule;
import com.cheatbreaker.client.ui.module.SomeRandomAssEnum;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker00;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.client.gui.GuiResourcePackList;
import net.optifine.config.VillagerProfession;
import com.cheatbreaker.client.emote.type.SuperFacepalmEmote;

public class DefaultHttpHeaders extends HttpHeaders {
   public static final int BUCKET_SIZE = 17;
   public DefaultHttpHeaders.HeaderEntry head;
   public boolean validate;
   public DefaultHttpHeaders.HeaderEntry[] entries = new DefaultHttpHeaders.HeaderEntry[17];

   @Override
   public HttpHeaders set(HttpHeaders var1) {
      if (!(var1 instanceof DefaultHttpHeaders)) {
         return super.set(var1);
      } else {
         this.clear();
         DefaultHttpHeaders var2 = (DefaultHttpHeaders)var1;

         for (DefaultHttpHeaders.HeaderEntry var3 = var2.head.after; var3 != var2.head; var3 = var3.after) {
            this.add(var3.key, var3.value);
         }

         return this;
      }
   }

   @Override
   public boolean contains(CharSequence var1, CharSequence var2, boolean var3) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         int var4 = hash(var1);
         int var5 = index(var4);

         for (DefaultHttpHeaders.HeaderEntry var6 = this.entries[var5]; var6 != null; var6 = var6.next) {
            if (var6.hash == var4 && equalsIgnoreCase(var1, var6.key)) {
               if (var3) {
                  if (equalsIgnoreCase(var6.value, var2)) {
                     return true;
                  }
               } else if (var6.value.equals(var2)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public Set<String> names() {
      LinkedHashSet var1 = new LinkedHashSet();

      for (DefaultHttpHeaders.HeaderEntry var2 = this.head.after; var2 != this.head; var2 = var2.after) {
         var1.add(var2.getKey());
      }

      return var1;
   }

   @Override
   public boolean contains(String var1) {
      return this.get(var1) != null;
   }

   public void encode(ByteBuf var1) {
      for (DefaultHttpHeaders.HeaderEntry var2 = this.head.after; var2 != this.head; var2 = var2.after) {
         var2.encode(var1);
      }
   }

   @Override
   public HttpHeaders set(CharSequence var1, Iterable<?> var2) {
      if (var2 == null) {
         throw new NullPointerException("values");
      } else {
         if (this.validate) {
            this.validateHeaderName0(var1);
         }

         int var3 = hash(var1);
         int var4 = index(var3);
         this.remove0(var3, var4, var1);

         for (Object var6 : var2) {
            if (var6 == null) {
               break;
            }

            CharSequence var7 = toCharSequence(var6);
            if (this.validate) {
               validateHeaderValue(var7);
            }

            this.add0(var3, var4, var1, var7);
         }

         return this;
      }
   }

   @Override
   public HttpHeaders add(CharSequence var1, Iterable<?> var2) {
      if (this.validate) {
         this.validateHeaderName0(var1);
      }

      int var3 = hash(var1);
      int var4 = index(var3);

      for (Object var6 : var2) {
         CharSequence var7 = toCharSequence(var6);
         if (this.validate) {
            validateHeaderValue(var7);
         }

         this.add0(var3, var4, var1, var7);
      }

      return this;
   }

   @Override
   public HttpHeaders add(CharSequence var1, Object var2) {
      CharSequence var3;
      if (this.validate) {
         this.validateHeaderName0(var1);
         var3 = toCharSequence(var2);
         validateHeaderValue(var3);
      } else {
         var3 = toCharSequence(var2);
      }

      int var4 = hash(var1);
      int var5 = index(var4);
      this.add0(var4, var5, var1, var3);
      return this;
   }

   @Override
   public String get(String var1) {
      return this.get((CharSequence)var1);
   }

   public void validateHeaderName0(CharSequence var1) {
      validateHeaderName(var1);
   }

   @Override
   public HttpHeaders set(String var1, Object var2) {
      return this.set((CharSequence)var1, var2);
   }

   @Override
   public String get(CharSequence var1) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         int var2 = hash(var1);
         int var3 = index(var2);
         DefaultHttpHeaders.HeaderEntry var4 = this.entries[var3];

         CharSequence var5;
         for (var5 = null; var4 != null; var4 = var4.next) {
            if (var4.hash == var2 && equalsIgnoreCase(var1, var4.key)) {
               var5 = var4.value;
            }
         }

         return var5 == null ? null : var5.toString();
      }
   }

   @Override
   public HttpHeaders add(String var1, Iterable<?> var2) {
      return this.add((CharSequence)var1, var2);
   }

   @Override
   public HttpHeaders remove(CharSequence var1) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         int var2 = hash(var1);
         int var3 = index(var2);
         this.remove0(var2, var3, var1);
         return this;
      }
   }

   @Override
   public List<Entry<String, String>> entries() {
      LinkedList var1 = new LinkedList();

      for (DefaultHttpHeaders.HeaderEntry var2 = this.head.after; var2 != this.head; var2 = var2.after) {
         var1.add(var2);
      }

      return var1;
   }

   public void remove0(int var1, int var2, CharSequence var3) {
      DefaultHttpHeaders.HeaderEntry var4 = this.entries[var2];
      if (var4 != null) {
         while (var4.hash == var1 && equalsIgnoreCase(var3, var4.key)) {
            var4.remove();
            DefaultHttpHeaders.HeaderEntry var5 = var4.next;
            if (var5 == null) {
               this.entries[var2] = null;
               return;
            }

            this.entries[var2] = var5;
            var4 = var5;
         }

         while (true) {
            DefaultHttpHeaders.HeaderEntry var6 = var4.next;
            if (var6 == null) {
               return;
            }

            if (var6.hash == var1 && equalsIgnoreCase(var3, var6.key)) {
               var4.next = var6.next;
               var6.remove();
            } else {
               var4 = var6;
            }
         }
      }
   }

   @Override
   public HttpHeaders add(String var1, Object var2) {
      return this.add((CharSequence)var1, var2);
   }

   public static int index(int var0) {
      return var0 % 17;
   }

   public DefaultHttpHeaders(boolean var1) {
      this.head = new DefaultHttpHeaders.HeaderEntry();
      this.validate = var1;
      this.head.before = this.head.after = this.head;
   }

   @Override
   public HttpHeaders add(HttpHeaders var1) {
      if (!(var1 instanceof DefaultHttpHeaders)) {
         return super.add(var1);
      } else {
         DefaultHttpHeaders var2 = (DefaultHttpHeaders)var1;

         for (DefaultHttpHeaders.HeaderEntry var3 = var2.head.after; var3 != var2.head; var3 = var3.after) {
            this.add(var3.key, var3.value);
         }

         return this;
      }
   }

   public DefaultHttpHeaders() {
      this(true);
   }

   @Override
   public List<String> getAll(CharSequence var1) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         LinkedList var2 = new LinkedList();
         int var3 = hash(var1);
         int var4 = index(var3);

         for (DefaultHttpHeaders.HeaderEntry var5 = this.entries[var4]; var5 != null; var5 = var5.next) {
            if (var5.hash == var3 && equalsIgnoreCase(var1, var5.key)) {
               var2.addFirst(var5.getValue());
            }
         }

         return var2;
      }
   }

   @Override
   public HttpHeaders set(CharSequence var1, Object var2) {
      CharSequence var3;
      if (this.validate) {
         this.validateHeaderName0(var1);
         var3 = toCharSequence(var2);
         validateHeaderValue(var3);
      } else {
         var3 = toCharSequence(var2);
      }

      int var4 = hash(var1);
      int var5 = index(var4);
      this.remove0(var4, var5, var1);
      this.add0(var4, var5, var1, var3);
      return this;
   }

   @Override
   public HttpHeaders clear() {
      Arrays.fill(this.entries, null);
      this.head.before = this.head.after = this.head;
      return this;
   }

   @Override
   public Iterator<Entry<String, String>> iterator() {
      return new DefaultHttpHeaders.HeaderIterator();
   }

   @Override
   public HttpHeaders remove(String var1) {
      return this.remove((CharSequence)var1);
   }

   @Override
   public List<String> getAll(String var1) {
      return this.getAll((CharSequence)var1);
   }

   public static CharSequence toCharSequence(Object var0) {
      if (var0 == null) {
         return null;
      } else if (var0 instanceof CharSequence) {
         return (CharSequence)var0;
      } else if (var0 instanceof Number) {
         return var0.toString();
      } else if (var0 instanceof Date) {
         return HttpHeaderDateFormat.get().format((Date)var0);
      } else {
         return var0 instanceof Calendar ? HttpHeaderDateFormat.get().format(((Calendar)var0).getTime()) : var0.toString();
      }
   }

   @Override
   public boolean contains(String var1, String var2, boolean var3) {
      return this.contains((CharSequence)var1, (CharSequence)var2, var3);
   }

   @Override
   public boolean isEmpty() {
      return this.head == this.head.after;
   }

   @Override
   public HttpHeaders set(String var1, Iterable<?> var2) {
      return this.set((CharSequence)var1, var2);
   }

   @Override
   public boolean contains(CharSequence var1) {
      return this.get(var1) != null;
   }

   public void add0(int var1, int var2, CharSequence var3, CharSequence var4) {
      DefaultHttpHeaders.HeaderEntry var5 = this.entries[var2];
      DefaultHttpHeaders.HeaderEntry var6;
      this.entries[var2] = var6 = new DefaultHttpHeaders.HeaderEntry(var1, var3, var4);
      var6.next = var5;
      var6.addBefore(this.head);
   }

   public final class HeaderEntry implements Entry<String, String> {
      public DefaultHttpHeaders.HeaderEntry next;
      public CharSequence value;
      public int hash;
      public DefaultHttpHeaders.HeaderEntry before;
      public DefaultHttpHeaders.HeaderEntry after;
      public CharSequence key;

      public String getKey() {
         return this.key.toString();
      }

      public HeaderEntry() {
         this.hash = -1;
         this.key = null;
         this.value = null;
      }

      public void addBefore(DefaultHttpHeaders.HeaderEntry var1) {
         this.after = var1;
         this.before = var1.before;
         this.before.after = this;
         this.after.before = this;
      }

      public HeaderEntry(int var2, CharSequence var3, CharSequence var4) {
         this.hash = var2;
         this.key = var3;
         this.value = var4;
      }

      public void remove() {
         this.before.after = this.after;
         this.after.before = this.before;
      }

      public String getValue() {
         return this.value.toString();
      }

      @Override
      public String toString() {
         return this.key.toString() + '=' + this.value.toString();
      }

      public void encode(ByteBuf var1) {
         HttpHeaders.encode(this.key, this.value, var1);
      }

      public String setValue(String var1) {
         if (var1 == null) {
            throw new NullPointerException("value");
         } else {
            HttpHeaders.validateHeaderValue(var1);
            CharSequence var2 = this.value;
            this.value = var1;
            return var2.toString();
         }
      }
   }

   public final class HeaderIterator implements Iterator<Entry<String, String>> {
      public DefaultHttpHeaders.HeaderEntry current = DefaultHttpHeaders.this.head;

      @Override
      public boolean hasNext() {
         return this.current.after != DefaultHttpHeaders.this.head;
      }

      public Entry<String, String> next() {
         this.current = this.current.after;
         if (this.current == DefaultHttpHeaders.this.head) {
            throw new NoSuchElementException();
         } else {
            return this.current;
         }
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }

      public HeaderIterator() {
      }
   }
}
