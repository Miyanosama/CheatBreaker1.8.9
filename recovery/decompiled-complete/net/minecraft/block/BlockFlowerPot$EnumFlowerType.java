package net.minecraft.block;

import net.minecraft.util.IStringSerializable;

public enum BlockFlowerPot$EnumFlowerType implements IStringSerializable {
   ACACIA_SAPLING("acacia_sapling"),
   CACTUS("cactus"),
   WHITE_TULIP("white_tulip"),
   HOUSTONIA("houstonia"),
   MUSHROOM_BROWN("mushroom_brown"),
   EMPTY("empty"),
   ALLIUM("allium"),
   PINK_TULIP("pink_tulip"),
   DANDELION("dandelion"),
   MUSHROOM_RED("mushroom_red"),
   RED_TULIP("red_tulip"),
   BLUE_ORCHID("blue_orchid"),
   POPPY("rose"),
   JUNGLE_SAPLING("jungle_sapling"),
   SPRUCE_SAPLING("spruce_sapling"),
   DARK_OAK_SAPLING("dark_oak_sapling"),
   FERN("fern"),
   OAK_SAPLING("oak_sapling"),
   ORANGE_TULIP("orange_tulip"),
   DEAD_BUSH("dead_bush"),
   OXEYE_DAISY("oxeye_daisy"),
   BIRCH_SAPLING("birch_sapling");
   // $VF: synthetic field
   public static BlockFlowerPot$EnumFlowerType[] $VALUES = new BlockFlowerPot$EnumFlowerType[]{
      BlockFlowerPot$EnumFlowerType.EMPTY,
      BlockFlowerPot$EnumFlowerType.POPPY,
      BlockFlowerPot$EnumFlowerType.BLUE_ORCHID,
      BlockFlowerPot$EnumFlowerType.ALLIUM,
      BlockFlowerPot$EnumFlowerType.HOUSTONIA,
      BlockFlowerPot$EnumFlowerType.RED_TULIP,
      BlockFlowerPot$EnumFlowerType.ORANGE_TULIP,
      BlockFlowerPot$EnumFlowerType.WHITE_TULIP,
      BlockFlowerPot$EnumFlowerType.PINK_TULIP,
      BlockFlowerPot$EnumFlowerType.OXEYE_DAISY,
      BlockFlowerPot$EnumFlowerType.DANDELION,
      BlockFlowerPot$EnumFlowerType.OAK_SAPLING,
      BlockFlowerPot$EnumFlowerType.SPRUCE_SAPLING,
      BlockFlowerPot$EnumFlowerType.BIRCH_SAPLING,
      BlockFlowerPot$EnumFlowerType.JUNGLE_SAPLING,
      ACACIA_SAPLING,
      BlockFlowerPot$EnumFlowerType.DARK_OAK_SAPLING,
      BlockFlowerPot$EnumFlowerType.MUSHROOM_RED,
      BlockFlowerPot$EnumFlowerType.MUSHROOM_BROWN,
      BlockFlowerPot$EnumFlowerType.DEAD_BUSH,
      BlockFlowerPot$EnumFlowerType.FERN,
      BlockFlowerPot$EnumFlowerType.CACTUS
   };
   public String name;

   public BlockFlowerPot$EnumFlowerType(String var3) {
      this.name = var3;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.name;
   }
}
