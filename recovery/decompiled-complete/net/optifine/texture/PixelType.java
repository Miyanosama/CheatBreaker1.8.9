package net.optifine.texture;

import net.minecraft.block.BlockDoor;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher$1;
import net.minecraft.client.stream.ChatController;
import net.minecraft.entity.passive.EntityVillager$EmeraldForItems;
import net.minecraft.util.LongHashMap;
import net.optifine.override.PlayerControllerOF;

public enum PixelType {
   UNSIGNED_SHORT_1_5_5_5_REV(33638),
   HALF_FLOAT(5131),
   INT(5124),
   UNSIGNED_BYTE(5121),
   SHORT(5122),
   UNSIGNED_SHORT_5_5_5_1(32820),
   UNSIGNED_SHORT_4_4_4_4(32819),
   UNSIGNED_SHORT_5_6_5_REV(33636),
   UNSIGNED_SHORT_4_4_4_4_REV(33637),
   UNSIGNED_BYTE_3_3_2(32818),
   UNSIGNED_INT_8_8_8_8(32821),
   UNSIGNED_INT_2_10_10_10_REV(33640),
   UNSIGNED_BYTE_2_3_3_REV(33634),
   BYTE(5120),
   UNSIGNED_SHORT(5123),
   UNSIGNED_SHORT_5_6_5(33635),
   UNSIGNED_INT_10_10_10_2(32822),
   FLOAT(5126),
   UNSIGNED_INT_8_8_8_8_REV(33639),
   UNSIGNED_INT(5125);
   public EntityVillager$EmeraldForItems field_0025;
   public int id;
   public BlockDoor field_0026;
   public ChatController field_0014;
   public PlayerControllerOF field_0010;
   public ChunkRenderDispatcher$1 field_0003;
   public LongHashMap field_0001;
   // $VF: synthetic field
   public static PixelType[] $VALUES = new PixelType[]{
      BYTE,
      SHORT,
      INT,
      HALF_FLOAT,
      PixelType.FLOAT,
      UNSIGNED_BYTE,
      UNSIGNED_BYTE_3_3_2,
      UNSIGNED_BYTE_2_3_3_REV,
      UNSIGNED_SHORT,
      PixelType.UNSIGNED_SHORT_5_6_5,
      UNSIGNED_SHORT_5_6_5_REV,
      UNSIGNED_SHORT_4_4_4_4,
      UNSIGNED_SHORT_4_4_4_4_REV,
      UNSIGNED_SHORT_5_5_5_1,
      UNSIGNED_SHORT_1_5_5_5_REV,
      PixelType.UNSIGNED_INT,
      UNSIGNED_INT_8_8_8_8,
      PixelType.UNSIGNED_INT_8_8_8_8_REV,
      PixelType.UNSIGNED_INT_10_10_10_2,
      UNSIGNED_INT_2_10_10_10_REV
   };

   public PixelType(int var3) {
      this.id = var3;
   }

   public int getId() {
      return this.id;
   }
}
