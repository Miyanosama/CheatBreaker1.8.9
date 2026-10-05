package io.netty.buffer;

import com.cheatbreaker.client.module.type.AutoTextModule;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.profiler.PlayerUsageSnooper;
import recovered.unidentified.UnidentifiedClass1784;

public class ByteBufUtil$ThreadLocalDirectByteBuf extends UnpooledDirectByteBuf {
   public PlayerUsageSnooper __junk5793727479108457612;
   public AutoTextModule __junk2056085095267051517;
   public Recycler$Handle handle;
   public UnidentifiedClass1784 __junk6882980821468125142;
   public static Recycler<ByteBufUtil$ThreadLocalDirectByteBuf> RECYCLER = new ByteBufUtil$ThreadLocalDirectByteBuf$1();

   @Override
   public void deallocate() {
      if (this.capacity() > ByteBufUtil.access$100()) {
         super.deallocate();
      } else {
         this.clear();
         RECYCLER.recycle(this, this.handle);
      }
   }

   public ByteBufUtil$ThreadLocalDirectByteBuf(Recycler$Handle var1) {
      super(UnpooledByteBufAllocator.DEFAULT, 256, Integer.MAX_VALUE);
      this.handle = var1;
   }

   public static ByteBufUtil$ThreadLocalDirectByteBuf newInstance() {
      ByteBufUtil$ThreadLocalDirectByteBuf var0 = RECYCLER.get();
      var0.setRefCnt(1);
      return var0;
   }
}
