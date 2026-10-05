package net.minecraft.block;

import io.netty.channel.DefaultMessageSizeEstimator;
import io.netty.util.internal.PendingWrite$1;
import net.minecraft.client.gui.ServerListEntryLanScan;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.enchantment.EnchantmentHelper$ModifierLiving;
import net.minecraft.util.IStringSerializable;

public enum BlockTallGrass$EnumType implements IStringSerializable {
   GRASS(1, "tall_grass"),
   DEAD_BUSH(0, "dead_bush"),
   FERN(2, "fern");

   public int meta;
   public PendingWrite$1 field_0004;
   // $VF: synthetic field
   public static BlockTallGrass$EnumType[] $VALUES = new BlockTallGrass$EnumType[]{BlockTallGrass$EnumType.DEAD_BUSH, GRASS, BlockTallGrass$EnumType.FERN};
   public String name;
   public ServerListEntryLanScan field_0010;
   public static BlockTallGrass$EnumType[] META_LOOKUP = new BlockTallGrass$EnumType[values().length];
   public TileEntityRendererDispatcher field_0011;
   public DefaultMessageSizeEstimator field_0000;
   public EnchantmentHelper$ModifierLiving field_0006;

   public int getMeta() {
      return this.meta;
   }

   static {
      for (BlockTallGrass$EnumType var3 : values()) {
         META_LOOKUP[var3.getMeta()] = var3;
      }
   }

   @Override
   public String toString() {
      return this.name;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public static BlockTallGrass$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   public BlockTallGrass$EnumType(int var3, String var4) {
      this.meta = var3;
      this.name = var4;
   }
}
