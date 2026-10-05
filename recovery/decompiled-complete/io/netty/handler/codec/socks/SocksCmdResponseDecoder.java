package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;
import java.util.List;
import net.minecraft.block.BlockBeacon$1;

public class SocksCmdResponseDecoder extends ReplayingDecoder<SocksCmdResponseDecoder$State> {
   public SocksProtocolVersion version;
   public byte reserved;
   public String host;
   public int port;
   public BlockBeacon$1 __junk6425619225066315806;
   public int fieldLength;
   public static String name;
   public SocksCmdStatus cmdStatus;
   public SocksResponse msg = SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE;
   public SocksAddressType addressType;

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      switch (SocksCmdResponseDecoder$1.$SwitchMap$io$netty$handler$codec$socks$SocksCmdResponseDecoder$State[this.state().ordinal()]) {
         case 1:
            this.version = SocksProtocolVersion.valueOf(var2.readByte());
            if (this.version != SocksProtocolVersion.SOCKS5) {
               break;
            }

            this.checkpoint(SocksCmdResponseDecoder$State.READ_CMD_HEADER);
         case 2:
            this.cmdStatus = SocksCmdStatus.valueOf(var2.readByte());
            this.reserved = var2.readByte();
            this.addressType = SocksAddressType.valueOf(var2.readByte());
            this.checkpoint(SocksCmdResponseDecoder$State.READ_CMD_ADDRESS);
         case 3:
            switch (SocksCmdResponseDecoder$1.$SwitchMap$io$netty$handler$codec$socks$SocksAddressType[this.addressType.ordinal()]) {
               case 1:
                  this.host = SocksCommonUtils.intToIp(var2.readInt());
                  this.port = var2.readUnsignedShort();
                  this.msg = new SocksCmdResponse(this.cmdStatus, this.addressType, this.host, this.port);
                  break;
               case 2:
                  this.fieldLength = var2.readByte();
                  this.host = var2.readBytes(this.fieldLength).toString(CharsetUtil.US_ASCII);
                  this.port = var2.readUnsignedShort();
                  this.msg = new SocksCmdResponse(this.cmdStatus, this.addressType, this.host, this.port);
                  break;
               case 3:
                  this.host = SocksCommonUtils.ipv6toStr(var2.readBytes(16).array());
                  this.port = var2.readUnsignedShort();
                  this.msg = new SocksCmdResponse(this.cmdStatus, this.addressType, this.host, this.port);
               case 4:
            }
      }

      var1.pipeline().remove(this);
      var3.add(this.msg);
   }

   public static String getName() {
      return "SOCKS_CMD_RESPONSE_DECODER";
   }

   public SocksCmdResponseDecoder() {
      super(SocksCmdResponseDecoder$State.CHECK_PROTOCOL_VERSION);
   }
}
