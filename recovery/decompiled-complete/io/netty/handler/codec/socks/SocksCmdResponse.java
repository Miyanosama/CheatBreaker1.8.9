package io.netty.handler.codec.socks;

import com.cheatbreaker.client.ui.overlay.element.PrivateMessageElement;
import io.netty.bootstrap.AbstractBootstrap$PendingRegistrationPromise;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import io.netty.util.NetUtil;
import java.net.IDN;
import net.minecraft.client.renderer.entity.RenderTntMinecart;
import net.minecraft.nbt.NBTBase;

public class SocksCmdResponse extends SocksResponse {
   public int port;
   public SocksCmdStatus cmdStatus;
   public static byte[] IPv4_HOSTNAME_ZEROED = new byte[]{0, 0, 0, 0};
   public RenderTntMinecart __junk2327169145272003549;
   public AbstractBootstrap$PendingRegistrationPromise __junk2073160879574617191;
   public SocksAddressType addressType;
   public NBTBase __junk4300637263127515754;
   public static byte[] IPv6_HOSTNAME_ZEROED = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   public String host;
   public PrivateMessageElement __junk4539890960806829017;
   public static byte[] DOMAIN_ZEROED = new byte[]{0};

   public String host() {
      return this.host != null ? IDN.toUnicode(this.host) : null;
   }

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
      var1.writeByte(this.protocolVersion().byteValue());
      var1.writeByte(this.cmdStatus.byteValue());
      var1.writeByte(0);
      var1.writeByte(this.addressType.byteValue());
      switch (SocksCmdResponse$1.$SwitchMap$io$netty$handler$codec$socks$SocksAddressType[this.addressType.ordinal()]) {
         case 1:
            byte[] var4 = this.host == null ? IPv4_HOSTNAME_ZEROED : NetUtil.createByteArrayFromIpAddressString(this.host);
            var1.writeBytes(var4);
            var1.writeShort(this.port);
            break;
         case 2:
            byte[] var3 = this.host == null ? DOMAIN_ZEROED : this.host.getBytes(CharsetUtil.US_ASCII);
            var1.writeByte(var3.length);
            var1.writeBytes(var3);
            var1.writeShort(this.port);
            break;
         case 3:
            byte[] var2 = this.host == null ? IPv6_HOSTNAME_ZEROED : NetUtil.createByteArrayFromIpAddressString(this.host);
            var1.writeBytes(var2);
            var1.writeShort(this.port);
      }
   }

   public int port() {
      return this.port;
   }

   public SocksCmdResponse(SocksCmdStatus var1, SocksAddressType var2) {
      this(var1, var2, null, 0);
   }

   public SocksAddressType addressType() {
      return this.addressType;
   }

   public SocksCmdStatus cmdStatus() {
      return this.cmdStatus;
   }

   public SocksCmdResponse(SocksCmdStatus var1, SocksAddressType var2, String var3, int var4) {
      super(SocksResponseType.CMD);
      if (var1 == null) {
         throw new NullPointerException("cmdStatus");
      } else if (var2 == null) {
         throw new NullPointerException("addressType");
      } else {
         if (var3 != null) {
            switch (SocksCmdResponse$1.$SwitchMap$io$netty$handler$codec$socks$SocksAddressType[var2.ordinal()]) {
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

            var3 = IDN.toASCII(var3);
         }

         if (var4 >= 0 && var4 <= 65535) {
            this.cmdStatus = var1;
            this.addressType = var2;
            this.host = var3;
            this.port = var4;
         } else {
            throw new IllegalArgumentException(var4 + " is not in bounds 0 <= x <= 65535");
         }
      }
   }
}
