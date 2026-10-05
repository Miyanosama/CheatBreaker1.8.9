package io.netty.channel;

import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.entity.RenderSkeleton$1;

public class PendingWriteQueue$PendingWrite {
   public ChannelPromise promise;
   public Recycler$Handle handle;
   public static Recycler<PendingWriteQueue$PendingWrite> RECYCLER = new PendingWriteQueue$PendingWrite$1();
   public Object msg;
   public long size;
   public FontRenderer __junk7819922388893429212;
   public RenderSkeleton$1 __junk7927917616893429674;
   public PendingWriteQueue$PendingWrite next;
   public TextureOffset __junk8480657012058606380;

   public static PendingWriteQueue$PendingWrite newInstance(Object var0, int var1, ChannelPromise var2) {
      PendingWriteQueue$PendingWrite var3 = RECYCLER.get();
      var3.size = var1;
      var3.msg = var0;
      var3.promise = var2;
      return var3;
   }

   public void recycle() {
      this.size = 1659793121288390689L & -1659793122101091704L;
      this.next = null;
      this.msg = null;
      this.promise = null;
      RECYCLER.recycle(this, this.handle);
   }

   public PendingWriteQueue$PendingWrite(Recycler$Handle var1) {
      this.handle = var1;
   }
}
