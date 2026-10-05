package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import net.minecraft.client.particle.EntitySuspendFX;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase$1;
import net.minecraft.client.util.JsonException;
import org.newsclub.net.unix.AFUNIXServerSocket;
import recovered.unidentified.UnidentifiedClass5074;

public class AbstractChannelHandlerContext$3 extends OneTimeTask {
   public EntitySuspendFX __junk8283086998701187030;
   public JsonException __junk8376870253537103415;
   public AFUNIXServerSocket __junk3821164142561059922;
   public LayerArmorBase$1 __junk6643319723000383548;
   public UnidentifiedClass5074 __junk6782138025575432457;

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$200(this.val$next);
   }

   public AbstractChannelHandlerContext$3(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$next = var2;
      super();
   }
}
