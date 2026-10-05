package io.netty.channel.rxtx;

import com.cheatbreaker.client.util.SessionServer;
import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelProgressivePromise;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.RegistryNamespaced;
import net.optifine.BlockPosM$1$1;
import net.optifine.util.CompoundKey;
import recovered.unidentified.UnidentifiedClass4676;

public class RxtxChannel$RxtxUnsafe extends AbstractChannel$AbstractUnsafe {
   public UnidentifiedClass4676 __junk3306341070414552550;
   public SessionServer __junk4611513881020416635;
   public RegistryNamespaced __junk47031607754198733;
   public DefaultChannelProgressivePromise __junk9133507841855176543;
   public EntityList __junk4802585382325728997;
   public BlockPosM$1$1 __junk9209034154794421752;
   public EntityTNTPrimed __junk1623410870287079430;
   public CompoundKey __junk5589147353845087653;

   @Override
   public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      if (var3.setUncancellable() && this.ensureOpen(var3)) {
         try {
            boolean var4 = this.this$0.isActive();
            this.this$0.doConnect(var1, var2);
            int var5 = this.this$0.config().getOption(RxtxChannelOption.WAIT_TIME);
            if (var5 > 0) {
               this.this$0.eventLoop().schedule(new RxtxChannel$RxtxUnsafe$1(this, var3, var4), var5, TimeUnit.MILLISECONDS);
            } else {
               this.this$0.doInit();
               this.safeSetSuccess(var3);
               if (!var4 && this.this$0.isActive()) {
                  this.this$0.pipeline().fireChannelActive();
               }
            }
         } catch (Throwable var6) {
            this.safeSetFailure(var3, var6);
            this.closeIfClosed();
         }
      }
   }

   public RxtxChannel$RxtxUnsafe(RxtxChannel var1) {
      this.this$0 = var1;
      super(var1);
   }
}
