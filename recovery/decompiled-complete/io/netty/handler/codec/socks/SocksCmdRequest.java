package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ReadOnlyUnsafeDirectByteBuf;
import io.netty.util.CharsetUtil;
import io.netty.util.NetUtil;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ValueSpliterator;
import java.net.IDN;
import net.minecraft.block.Block$3;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.world.gen.structure.StructureOceanMonument;

public class SocksCmdRequest extends SocksRequest {
   public EnchantmentHelper __junk570074230999346546;
   public ConcurrentHashMapV8$ValueSpliterator __junk8475277965244028688;
   public ReadOnlyUnsafeDirectByteBuf __junk6919896379143869919;
   public SocksAddressType addressType;
   public String host;
   public int port;
   public Block$3 __junk7526466279081128687;
   public SocksCmdType cmdType;
   public StructureOceanMonument __junk3806708707809121081;

   public SocksAddressType addressType() {
      return this.addressType;
   }

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
      var1.writeByte(this.protocolVersion().byteValue());
      var1.writeByte(this.cmdType.byteValue());
      var1.writeByte(0);
      var1.writeByte(this.addressType.byteValue());
      switch (SocksCmdRequest$1.$SwitchMap$io$netty$handler$codec$socks$SocksAddressType[this.addressType.ordinal()]) {
         case 1:
            var1.writeBytes(NetUtil.createByteArrayFromIpAddressString(this.host));
            var1.writeShort(this.port);
            break;
         case 2:
            var1.writeByte(this.host.length());
            var1.writeBytes(this.host.getBytes(CharsetUtil.US_ASCII));
            var1.writeShort(this.port);
            break;
         case 3:
            var1.writeBytes(NetUtil.createByteArrayFromIpAddressString(this.host));
            var1.writeShort(this.port);
      }
   }

   public int port() {
      return this.port;
   }

   public SocksCmdRequest(SocksCmdType var1, SocksAddressType var2, String var3, int var4) {
      super(SocksRequestType.CMD);
      if (var1 == null) {
         throw new NullPointerException("cmdType");
      } else if (var2 == null) {
         throw new NullPointerException("addressType");
      } else if (var3 == null) {
         throw new NullPointerException("host");
      } else {
         switch (SocksCmdRequest$1.$SwitchMap$io$netty$handler$codec$socks$SocksAddressType[var2.ordinal()]) {
            case 1:
               if (!NetUtil.isValidIpV4Address(var3)) {
                  throw new IllegalArgumentException(var3 + " is not a valid IPv4 address");
               }
               break;
            case 2:
               if (IDN.toASCII(var3).length() > 255) {
                  throw new IllegalArgumentException(var3 + " IDN: " + IDN.toASCII(var3) + " exceeds 255 char limit");
               }
               break;
            case 3:
               if (!NetUtil.isValidIpV6Address(var3)) {
                  throw new IllegalArgumentException(var3 + " is not a valid IPv6 address");
               }
            case 4:
         }

         if (var4 > 0 && var4 < 65536) {
            this.cmdType = var1;
            this.addressType = var2;
            this.host = IDN.toASCII(var3);
            this.port = var4;
         } else {
            throw new IllegalArgumentException(var4 + " is not in bounds 0 < x < 65536");
         }
      }
   }

   public String host() {
      return IDN.toUnicode(this.host);
   }

   public SocksCmdType cmdType() {
      return this.cmdType;
   }
}
