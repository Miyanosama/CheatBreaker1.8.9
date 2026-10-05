package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Piece;

public class SocksAuthResponse extends SocksResponse {
   public StructureNetherBridgePieces$Piece __junk5810075011957287748;
   public static SocksSubnegotiationVersion SUBNEGOTIATION_VERSION = SocksSubnegotiationVersion.AUTH_PASSWORD;
   public SocksAuthStatus authStatus;
   public TextureMetadataSection __junk6112824276789398758;

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
      var1.writeByte(SUBNEGOTIATION_VERSION.byteValue());
      var1.writeByte(this.authStatus.byteValue());
   }

   public SocksAuthStatus authStatus() {
      return this.authStatus;
   }

   public SocksAuthResponse(SocksAuthStatus var1) {
      super(SocksResponseType.AUTH);
      if (var1 == null) {
         throw new NullPointerException("authStatus");
      } else {
         this.authStatus = var1;
      }
   }
}
