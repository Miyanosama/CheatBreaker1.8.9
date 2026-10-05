package io.netty.handler.codec.socks;

import com.cheatbreaker.client.util.thread.AliasesThread;
import io.netty.channel.SucceededChannelFuture;
import net.minecraft.nbt.JsonToNBT$List;
import net.minecraft.network.play.server.S22PacketMultiBlockChange$BlockUpdateData;
import org.apache.log4j.lf5.LF5Appender;

public enum SocksAuthRequestDecoder$State {
   CHECK_PROTOCOL_VERSION,
   READ_PASSWORD,
   READ_USERNAME;

   public JsonToNBT$List __junk3005897081205741091;
   public LF5Appender __junk8011213800209453289;
   public AliasesThread __junk624036301508385422;
   public S22PacketMultiBlockChange$BlockUpdateData __junk2872269708401996525;
   public SucceededChannelFuture __junk4531851835622053537;
}
