package io.netty.channel;

import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import net.minecraft.block.Block$1;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.monster.EntitySilverfish$AIHideInStone;
import net.optifine.reflect.FieldLocatorFixed;

public class ThreadPerChannelEventLoopGroup$1 implements FutureListener<Object> {
   public EntityLookHelper __junk5643919553928218695;
   public EntitySilverfish$AIHideInStone __junk8425676505881479420;
   public FieldLocatorFixed __junk3142307020707523665;
   public Block$1 __junk2878491961484708297;
   public CBGuiAnchor __junk1827038271017957860;

   public ThreadPerChannelEventLoopGroup$1(ThreadPerChannelEventLoopGroup var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void operationComplete(Future<Object> var1) {
      if (this.this$0.isTerminated()) {
         ThreadPerChannelEventLoopGroup.access$000(this.this$0).trySuccess(null);
      }
   }
}
