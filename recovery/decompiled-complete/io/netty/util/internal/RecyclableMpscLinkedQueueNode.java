package io.netty.util.internal;

import com.cheatbreaker.client.module.type.CPSModule;
import io.netty.handler.codec.socks.SocksInitRequestDecoder$State;
import io.netty.util.Recycler$Handle;
import net.minecraft.block.BlockPressurePlate$Sensitivity;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayer$1;
import net.minecraft.util.ScreenShotHelper;
import net.optifine.VersionCheckThread;

public abstract class RecyclableMpscLinkedQueueNode<T> extends MpscLinkedQueueNode<T> {
   public BlockPressurePlate$Sensitivity __junk874126095855593326;
   public Recycler$Handle handle;
   public SocksInitRequestDecoder$State __junk6427793834098438656;
   public ScreenShotHelper __junk3965500391673259605;
   public TeleportToPlayer$1 __junk8926801957043356004;
   public VersionCheckThread __junk95861168633220947;
   public CPSModule __junk3223319820589995974;

   @Override
   public void unlink() {
      super.unlink();
      this.recycle(this.handle);
   }

   public abstract void recycle(Recycler$Handle var1);

   public RecyclableMpscLinkedQueueNode(Recycler$Handle var1) {
      if (var1 == null) {
         throw new NullPointerException("handle");
      } else {
         this.handle = var1;
      }
   }
}
