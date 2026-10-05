package io.netty.channel.local;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.border.EnumBorderStatus;

public class LocalChannel$3 implements Runnable {
   public EntityPlayer __junk36830195479807072;
   public EnumBorderStatus __junk4952778941475040988;

   public LocalChannel$3(LocalChannel var1, LocalChannel var2) {
      this.this$0 = var1;
      this.val$peer = var2;
      super();
   }

   @Override
   public void run() {
      LocalChannel.access$202(this.this$0, false);
      this.val$peer.pipeline().fireChannelActive();
      LocalChannel.access$300(this.val$peer).setSuccess();
   }
}
