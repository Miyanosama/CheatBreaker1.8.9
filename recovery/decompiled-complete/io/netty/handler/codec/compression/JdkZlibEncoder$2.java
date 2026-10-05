package io.netty.handler.codec.compression;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.optifine.entity.model.ModelAdapterSheepWool;
import net.optifine.shaders.ShaderProgramData;
import recovered.unidentified.UnidentifiedClass0347;
import recovered.unidentified.UnidentifiedClass1883;
import recovered.unidentified.UnidentifiedClass4535;

public class JdkZlibEncoder$2 implements ChannelFutureListener {
   public UnidentifiedClass0347 __junk7287257288961327450;
   public ShaderProgramData __junk1869862711208870108;
   public UnidentifiedClass1883 __junk8292911812499475093;
   public UnidentifiedClass4535 __junk6736057750340550237;
   public ModelAdapterSheepWool __junk4205288331948324423;

   public JdkZlibEncoder$2(JdkZlibEncoder var1, ChannelHandlerContext var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$ctx = var2;
      this.val$promise = var3;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      this.val$ctx.close(this.val$promise);
   }
}
