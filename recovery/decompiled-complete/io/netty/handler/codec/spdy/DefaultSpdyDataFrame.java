package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpObjectDecoder;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.StringUtil;

public class DefaultSpdyDataFrame extends DefaultSpdyStreamFrame implements SpdyDataFrame {
   public HttpObjectDecoder __junk7175372022017075770;
   public ByteBuf data;

   @Override
   public SpdyDataFrame retain(int var1) {
      this.data.retain(var1);
      return this;
   }

   @Override
   public boolean release(int var1) {
      return this.data.release(var1);
   }

   @Override
   public SpdyDataFrame setStreamId(int var1) {
      super.setStreamId(var1);
      return this;
   }

   @Override
   public SpdyDataFrame copy() {
      DefaultSpdyDataFrame var1 = new DefaultSpdyDataFrame(this.streamId(), this.content().copy());
      var1.setLast(this.isLast());
      return var1;
   }

   @Override
   public boolean release() {
      return this.data.release();
   }

   public static ByteBuf validate(ByteBuf var0) {
      if (var0.readableBytes() > 16777215) {
         throw new IllegalArgumentException("data payload cannot exceed 16777215 bytes");
      } else {
         return var0;
      }
   }

   @Override
   public ByteBuf content() {
      if (this.data.refCnt() <= 0) {
         throw new IllegalReferenceCountException(this.data.refCnt());
      } else {
         return this.data;
      }
   }

   @Override
   public int refCnt() {
      return this.data.refCnt();
   }

   public DefaultSpdyDataFrame(int var1) {
      this(var1, Unpooled.buffer(0));
   }

   public DefaultSpdyDataFrame(int var1, ByteBuf var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException("data");
      } else {
         this.data = validate(var2);
      }
   }

   @Override
   public SpdyDataFrame setLast(boolean var1) {
      super.setLast(var1);
      return this;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append("(last: ");
      var1.append(this.isLast());
      var1.append(')');
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Stream-ID = ");
      var1.append(this.streamId());
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Size = ");
      if (this.refCnt() == 0) {
         var1.append("(freed)");
      } else {
         var1.append(this.content().readableBytes());
      }

      return var1.toString();
   }

   @Override
   public SpdyDataFrame duplicate() {
      DefaultSpdyDataFrame var1 = new DefaultSpdyDataFrame(this.streamId(), this.content().duplicate());
      var1.setLast(this.isLast());
      return var1;
   }

   @Override
   public SpdyDataFrame retain() {
      this.data.retain();
      return this;
   }
}
