package io.netty.buffer;

import io.netty.handler.codec.http.HttpConstants;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.StringUtil;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.LongHashMap;

public class DefaultByteBufHolder implements ByteBufHolder {
   public ByteBuf data;

   public DefaultByteBufHolder(ByteBuf var1) {
      if (var1 == null) {
         throw new NullPointerException("data");
      } else {
         this.data = var1;
      }
   }

   @Override
   public ByteBufHolder duplicate() {
      return new DefaultByteBufHolder(this.data.duplicate());
   }

   @Override
   public ByteBufHolder retain() {
      this.data.retain();
      return this;
   }

   @Override
   public boolean release() {
      return this.data.release();
   }

   @Override
   public ByteBufHolder copy() {
      return new DefaultByteBufHolder(this.data.copy());
   }

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this) + '(' + this.content().toString() + ')';
   }

   @Override
   public ByteBufHolder retain(int var1) {
      this.data.retain(var1);
      return this;
   }

   @Override
   public boolean release(int var1) {
      return this.data.release(var1);
   }

   @Override
   public int refCnt() {
      return this.data.refCnt();
   }

   @Override
   public ByteBuf content() {
      if (this.data.refCnt() <= 0) {
         throw new IllegalReferenceCountException(this.data.refCnt());
      } else {
         return this.data;
      }
   }
}
