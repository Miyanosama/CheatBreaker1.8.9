package io.netty.channel;

import io.netty.buffer.Unpooled;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import io.netty.util.ReferenceCountUtil;
import java.nio.ByteBuffer;
import net.minecraft.block.BlockRedstoneTorch$Toggle;
import net.minecraft.client.resources.model.WeightedBakedModel;

public class ChannelOutboundBuffer$Entry {
   public Recycler$Handle handle;
   public long total;
   public ChannelPromise promise;
   public ByteBuffer[] bufs;
   public WeightedBakedModel __junk3765982942084440749;
   public Object msg;
   public long progress;
   public boolean cancelled;
   public ByteBuffer buf;
   public int count = -1;
   public static Recycler<ChannelOutboundBuffer$Entry> RECYCLER = new ChannelOutboundBuffer$Entry$1();
   public DefaultChannelConfig __junk2634310548068524469;
   public int pendingSize;
   public BlockRedstoneTorch$Toggle __junk4414500444932327361;
   public ChannelOutboundBuffer$Entry next;

   public int cancel() {
      if (!this.cancelled) {
         this.cancelled = true;
         int var1 = this.pendingSize;
         ReferenceCountUtil.safeRelease(this.msg);
         this.msg = Unpooled.EMPTY_BUFFER;
         this.pendingSize = 0;
         this.total = 1971392001L & 8586715447607439408L;
         this.progress = 211080290L & 1375797656L;
         this.bufs = null;
         this.buf = null;
         return var1;
      } else {
         return 0;
      }
   }

   public static ChannelOutboundBuffer$Entry newInstance(Object var0, int var1, long var2, ChannelPromise var4) {
      ChannelOutboundBuffer$Entry var5 = RECYCLER.get();
      var5.msg = var0;
      var5.pendingSize = var1;
      var5.total = var2;
      var5.promise = var4;
      return var5;
   }

   public ChannelOutboundBuffer$Entry recycleAndGetNext() {
      ChannelOutboundBuffer$Entry var1 = this.next;
      this.recycle();
      return var1;
   }

   public void recycle() {
      this.next = null;
      this.bufs = null;
      this.buf = null;
      this.msg = null;
      this.promise = null;
      this.progress = 6296321532251873394L & 270630913L;
      this.total = -9063284694266640064L & 308085403L;
      this.pendingSize = 0;
      this.count = -1;
      this.cancelled = false;
      RECYCLER.recycle(this, this.handle);
   }

   public ChannelOutboundBuffer$Entry(Recycler$Handle var1) {
      this.handle = var1;
   }
}
