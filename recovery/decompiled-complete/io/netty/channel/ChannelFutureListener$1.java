package io.netty.channel;

import io.netty.handler.codec.socks.SocksCommonUtils;
import io.netty.util.concurrent.MultithreadEventExecutorGroup$1;
import net.minecraft.block.BlockStone;
import net.minecraft.entity.passive.EntityRabbit$RabbitMoveHelper;
import net.minecraft.nbt.JsonToNBT;
import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.MDC;

public class ChannelFutureListener$1 implements ChannelFutureListener {
   public BlockStone __junk512938242871814550;
   public MultithreadEventExecutorGroup$1 __junk7403732671504971940;
   public MDC __junk6081697471760804505;
   public LoggingEvent __junk6323287036468537296;
   public EntityRabbit$RabbitMoveHelper __junk7825910859743167104;
   public JsonToNBT __junk1649802668065479744;
   public SocksCommonUtils __junk1211818495665464070;

   public void operationComplete(ChannelFuture var1) {
      var1.channel().close();
   }
}
