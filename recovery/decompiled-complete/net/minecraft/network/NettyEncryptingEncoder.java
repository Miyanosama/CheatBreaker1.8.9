package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import javax.crypto.Cipher;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.tileentity.TileEntitySign$1;
import net.minecraft.world.chunk.Chunk$2;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$MonumentBuilding;
import recovered.unidentified.UnidentifiedClass1954;

public class NettyEncryptingEncoder extends MessageToByteEncoder<ByteBuf> {
   public EntitySmokeFX field_0003;
   public NettyEncryptionTranslator field_0005;
   public UnidentifiedClass1954 field_0002;
   public Chunk$2 field_0004;
   public TileEntitySign$1 field_0000;
   public StructureOceanMonumentPieces$MonumentBuilding field_0001;

   public void method_21131(ChannelHandlerContext var1, ByteBuf var2, ByteBuf var3) {
      this.field_0005.cipher(var2, var3);
   }

   public NettyEncryptingEncoder(Cipher var1) {
      this.field_0005 = new NettyEncryptionTranslator(var1);
   }
}
