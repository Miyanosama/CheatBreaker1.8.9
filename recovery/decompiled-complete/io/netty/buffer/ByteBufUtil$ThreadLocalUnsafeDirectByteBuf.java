package io.netty.buffer;

import io.netty.channel.rxtx.RxtxChannelConfig$Paritybit;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.optifine.expr.ExpressionFloatArrayCached;

public class ByteBufUtil$ThreadLocalUnsafeDirectByteBuf extends UnpooledUnsafeDirectByteBuf {
   public Recycler$Handle handle;
   public HttpResponseStatus __junk3117402861280098230;
   public RxtxChannelConfig$Paritybit __junk2921122600770524819;
   public static Recycler<ByteBufUtil$ThreadLocalUnsafeDirectByteBuf> RECYCLER = new ByteBufUtil$ThreadLocalUnsafeDirectByteBuf$1();
   public ExpressionFloatArrayCached __junk7283985581700645983;

   public ByteBufUtil$ThreadLocalUnsafeDirectByteBuf(Recycler$Handle var1) {
      super(UnpooledByteBufAllocator.DEFAULT, 256, Integer.MAX_VALUE);
      this.handle = var1;
   }

   public static ByteBufUtil$ThreadLocalUnsafeDirectByteBuf newInstance() {
      ByteBufUtil$ThreadLocalUnsafeDirectByteBuf var0 = RECYCLER.get();
      var0.setRefCnt(1);
      return var0;
   }

   @Override
   public void deallocate() {
      if (this.capacity() > ByteBufUtil.access$100()) {
         super.deallocate();
      } else {
         this.clear();
         RECYCLER.recycle(this, this.handle);
      }
   }
}
