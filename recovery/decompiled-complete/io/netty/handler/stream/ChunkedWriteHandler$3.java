package io.netty.handler.stream;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.HashedWheelTimer;
import net.minecraft.block.BlockSilverfish$EnumType$3;
import net.minecraft.client.stream.ChatController$EnumChannelState;
import net.minecraft.enchantment.EnchantmentOxygen;
import net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook;
import net.minecraft.server.network.NetHandlerHandshakeTCP$1;
import net.optifine.reflect.ReflectorConstructor;

public class ChunkedWriteHandler$3 implements ChannelFutureListener {
   public ChatController$EnumChannelState __junk3553967668793366448;
   public C03PacketPlayer$C06PacketPlayerPosLook __junk9080567009371291874;
   public NetHandlerHandshakeTCP$1 __junk8325536909851947124;
   public ReflectorConstructor __junk4328258062600594901;
   public BlockSilverfish$EnumType$3 __junk1542732557320286722;
   public HashedWheelTimer __junk3849249722120489692;
   public EnchantmentOxygen __junk4062080218674385334;

   public ChunkedWriteHandler$3(ChunkedWriteHandler var1, Object var2, ChunkedWriteHandler$PendingWrite var3, int var4) {
      this.this$0 = var1;
      this.val$pendingMessage = var2;
      this.val$currentWrite = var3;
      this.val$amount = var4;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         ChunkedWriteHandler.closeInput((ChunkedInput<?>)this.val$pendingMessage);
         this.val$currentWrite.fail(var1.cause());
      } else {
         this.val$currentWrite.progress(this.val$amount);
      }
   }
}
