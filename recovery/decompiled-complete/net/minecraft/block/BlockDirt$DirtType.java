package net.minecraft.block;

import io.netty.channel.DefaultChannelPromise;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;
import net.optifine.util.CompoundKey;

public enum BlockDirt$DirtType implements IStringSerializable {
   PODZOL(2, "podzol", MapColor.obsidianColor),
   COARSE_DIRT(1, "coarse_dirt", "coarse", MapColor.dirtColor),
   DIRT(0, "dirt", "default", MapColor.dirtColor);

   public String unlocalizedName;
   public String name;
   // $VF: synthetic field
   public static BlockDirt$DirtType[] $VALUES = new BlockDirt$DirtType[]{BlockDirt$DirtType.DIRT, BlockDirt$DirtType.COARSE_DIRT, BlockDirt$DirtType.PODZOL};
   public CompoundKey field_0001;
   public int metadata;
   public static BlockDirt$DirtType[] METADATA_LOOKUP = new BlockDirt$DirtType[values().length];
   public MapColor field_181067_h;
   public DefaultChannelPromise field_0000;

   public MapColor func_181066_d() {
      return this.field_181067_h;
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public int getMetadata() {
      return this.metadata;
   }

   public static BlockDirt$DirtType byMetadata(int var0) {
      if (var0 < 0 || var0 >= METADATA_LOOKUP.length) {
         var0 = 0;
      }

      return METADATA_LOOKUP[var0];
   }

   static {
      for (BlockDirt$DirtType var3 : values()) {
         METADATA_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   public BlockDirt$DirtType(int var3, String var4, MapColor var5) {
      this(var3, var4, var4, var5);
   }

   public BlockDirt$DirtType(int var3, String var4, String var5, MapColor var6) {
      this.metadata = var3;
      this.name = var4;
      this.unlocalizedName = var5;
      this.field_181067_h = var6;
   }

   @Override
   public String toString() {
      return this.name;
   }
}
