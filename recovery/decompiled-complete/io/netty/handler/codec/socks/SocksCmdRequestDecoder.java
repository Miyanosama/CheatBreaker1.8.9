package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil$TransferEncodingMechanism;
import io.netty.util.CharsetUtil;
import java.util.List;
import net.minecraft.block.BlockButton;
import net.minecraft.command.PlayerSelector$6;
import net.minecraft.network.NetworkSystem$7;
import net.minecraft.world.WorldServer$ServerBlockEventList;
import org.apache.log4j.pattern.BridgePatternParser;

public class SocksCmdRequestDecoder extends ReplayingDecoder<SocksCmdRequestDecoder$State> {
   public SocksProtocolVersion version;
   public SocksAddressType addressType;
   public PlayerSelector$6 __junk5191627507428242727;
   public SocksRequest msg = SocksCommonUtils.UNKNOWN_SOCKS_REQUEST;
   public static String name;
   public BlockButton __junk6876188585539914620;
   public SocksCmdType cmdType;
   public BridgePatternParser __junk1698091022447866846;
   public HttpPostBodyUtil$TransferEncodingMechanism __junk1175919239504392760;
   public byte reserved;
   public int fieldLength;
   public String host;
   public NetworkSystem$7 __junk1428490382882312140;
   public WorldServer$ServerBlockEventList __junk3467637641433599446;
   public int port;

   public static String getName() {
      return "SOCKS_CMD_REQUEST_DECODER";
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      switch (SocksCmdRequestDecoder$1.$SwitchMap$io$netty$handler$codec$socks$SocksCmdRequestDecoder$State[this.state().ordinal()]) {
         case 1:
            this.version = SocksProtocolVersion.valueOf(var2.readByte());
            if (this.version != SocksProtocolVersion.SOCKS5) {
               break;
            }

            this.checkpoint(SocksCmdRequestDecoder$State.READ_CMD_HEADER);
         case 2:
            this.cmdType = SocksCmdType.valueOf(var2.readByte());
            this.reserved = var2.readByte();
            this.addressType = SocksAddressType.valueOf(var2.readByte());
            this.checkpoint(SocksCmdRequestDecoder$State.READ_CMD_ADDRESS);
         case 3:
            switch (SocksCmdRequestDecoder$1.$SwitchMap$io$netty$handler$codec$socks$SocksAddressType[this.addressType.ordinal()]) {
               case 1:
                  this.host = SocksCommonUtils.intToIp(var2.readInt());
                  this.port = var2.readUnsignedShort();
                  this.msg = new SocksCmdRequest(this.cmdType, this.addressType, this.host, this.port);
                  break;
               case 2:
                  this.fieldLength = var2.readByte();
                  this.host = var2.readBytes(this.fieldLength).toString(CharsetUtil.US_ASCII);
                  this.port = var2.readUnsignedShort();
                  this.msg = new SocksCmdRequest(this.cmdType, this.addressType, this.host, this.port);
                  break;
               case 3:
                  this.host = SocksCommonUtils.ipv6toStr(var2.readBytes(16).array());
                  this.port = var2.readUnsignedShort();
                  this.msg = new SocksCmdRequest(this.cmdType, this.addressType, this.host, this.port);
               case 4:
            }
      }

      var1.pipeline().remove(this);
      var3.add(this.msg);
   }

   public SocksCmdRequestDecoder() {
      super(SocksCmdRequestDecoder$State.CHECK_PROTOCOL_VERSION);
   }
}
