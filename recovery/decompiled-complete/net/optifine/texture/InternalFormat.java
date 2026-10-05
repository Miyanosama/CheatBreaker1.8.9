package net.optifine.texture;

import io.netty.handler.codec.spdy.DefaultSpdyPingFrame;
import net.minecraft.block.state.BlockState$1;
import net.minecraft.network.play.client.C0DPacketCloseWindow;

public enum InternalFormat {
   RG16(33324),
   R8(33321),
   RG32F(33328),
   RG8(33323),
   RGB10_A2(32857),
   RGBA16_SNORM(36763),
   RGBA16(32859),
   RG8_SNORM(36757),
   RGB16(32852),
   R3_G3_B2(10768),
   R8_SNORM(36756),
   RGB8(32849),
   R32F(33326),
   R16F(33325),
   RGBA8(32856),
   RG32UI(33340),
   RGBA16F(34842),
   R32I(33333),
   RG16F(33327),
   RGB32I(36227),
   RGB32UI(36209),
   R16_SNORM(36760),
   RGB5_A1(32855),
   RGB16F(34843),
   RGB9_E5(35901),
   RGBA32UI(36208),
   RG16_SNORM(36761),
   R32UI(33334),
   RGB16_SNORM(36762),
   RGBA32I(36226),
   RG32I(33339),
   R16(33322),
   RGBA8_SNORM(36759),
   RGBA32F(34836),
   R11F_G11F_B10F(35898),
   RGB32F(34837),
   RGB8_SNORM(36758);

   public BlockState$1 field_0022;
   public DefaultSpdyPingFrame field_0003;
   // $VF: synthetic field
   public static InternalFormat[] $VALUES = new InternalFormat[]{
      R8,
      RG8,
      RGB8,
      RGBA8,
      R8_SNORM,
      RG8_SNORM,
      InternalFormat.RGB8_SNORM,
      InternalFormat.RGBA8_SNORM,
      InternalFormat.R16,
      RG16,
      RGB16,
      RGBA16,
      R16_SNORM,
      InternalFormat.RG16_SNORM,
      InternalFormat.RGB16_SNORM,
      RGBA16_SNORM,
      R16F,
      RG16F,
      RGB16F,
      RGBA16F,
      R32F,
      RG32F,
      InternalFormat.RGB32F,
      InternalFormat.RGBA32F,
      R32I,
      InternalFormat.RG32I,
      RGB32I,
      InternalFormat.RGBA32I,
      InternalFormat.R32UI,
      RG32UI,
      RGB32UI,
      InternalFormat.RGBA32UI,
      R3_G3_B2,
      RGB5_A1,
      RGB10_A2,
      InternalFormat.R11F_G11F_B10F,
      InternalFormat.RGB9_E5
   };
   public C0DPacketCloseWindow field_0000;
   public int id;

   public InternalFormat(int var3) {
      this.id = var3;
   }

   public int getId() {
      return this.id;
   }
}
