package io.netty.util.internal;

import com.cheatbreaker.client.module.type.CPSModule;
import io.netty.handler.codec.socks.SocksInitRequestDecoder;
import io.netty.util.Recycler;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.util.ScreenShotHelper;
import net.optifine.VersionCheckThread;

public abstract class RecyclableMpscLinkedQueueNode<T> extends MpscLinkedQueueNode<T> {
   public Recycler.Handle handle;

   @Override
   public void unlink() {
      super.unlink();
      this.recycle(this.handle);
   }

   public abstract void recycle(Recycler.Handle var1);

   public RecyclableMpscLinkedQueueNode(Recycler.Handle var1) {
      if (var1 == null) {
         throw new NullPointerException("handle");
      } else {
         this.handle = var1;
      }
   }
}
