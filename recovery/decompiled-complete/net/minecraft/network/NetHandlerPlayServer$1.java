package net.minecraft.network;

import io.netty.handler.codec.spdy.DefaultSpdySynStreamFrame;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.util.ChatComponentText;
import org.apache.log4j.jmx.AbstractDynamicMBean;

public class NetHandlerPlayServer$1 implements GenericFutureListener<Future<? super Void>> {
   public AbstractDynamicMBean field_0001;
   public DefaultSpdySynStreamFrame field_0003;

   @Override
   public void operationComplete(Future<? super Void> var1) {
      this.field_0002.netManager.closeChannel(this.field_0000);
   }

   public NetHandlerPlayServer$1(NetHandlerPlayServer var1, ChatComponentText var2) {
      this.field_0002 = var1;
      this.field_0000 = var2;
      super();
   }
}
