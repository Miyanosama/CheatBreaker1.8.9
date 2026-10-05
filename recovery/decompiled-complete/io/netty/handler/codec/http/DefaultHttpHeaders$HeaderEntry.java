package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.module.SomeRandomAssEnum;
import io.netty.buffer.ByteBuf;
import java.util.Map.Entry;
import net.optifine.config.VillagerProfession;

public class DefaultHttpHeaders$HeaderEntry implements Entry<String, String> {
   public DefaultHttpHeaders$HeaderEntry next;
   public CharSequence value;
   public int hash;
   public DefaultHttpHeaders$HeaderEntry before;
   public SomeRandomAssEnum __junk461571883652675864;
   public DefaultHttpHeaders$HeaderEntry after;
   public CharSequence key;
   public VillagerProfession __junk5041398229360573266;

   public String getKey() {
      return this.key.toString();
   }

   public DefaultHttpHeaders$HeaderEntry(DefaultHttpHeaders var1) {
      this.this$0 = var1;
      super();
      this.hash = -1;
      this.key = null;
      this.value = null;
   }

   public void addBefore(DefaultHttpHeaders$HeaderEntry var1) {
      this.after = var1;
      this.before = var1.before;
      this.before.after = this;
      this.after.before = this;
   }

   public DefaultHttpHeaders$HeaderEntry(DefaultHttpHeaders var1, int var2, CharSequence var3, CharSequence var4) {
      this.this$0 = var1;
      super();
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
