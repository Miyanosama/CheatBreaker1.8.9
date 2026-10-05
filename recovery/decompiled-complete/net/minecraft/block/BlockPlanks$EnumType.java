package net.minecraft.block;

import com.cheatbreaker.client.ui.util.HudUtil;
import io.netty.channel.AbstractChannel$AbstractUnsafe$1;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.particle.EntitySpellParticleFX$Factory;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.util.IStringSerializable;

public enum BlockPlanks$EnumType implements IStringSerializable {
   ACACIA(4, "acacia", MapColor.adobeColor),
   DARK_OAK(5, "dark_oak", "big_oak", MapColor.brownColor),
   SPRUCE(1, "spruce", MapColor.obsidianColor),
   OAK(0, "oak", MapColor.woodColor),
   JUNGLE(3, "jungle", MapColor.dirtColor),
   BIRCH(2, "birch", MapColor.sandColor);

   public AbstractChannel$AbstractUnsafe$1 field_0015;
   public static BlockPlanks$EnumType[] META_LOOKUP = new BlockPlanks$EnumType[values().length];
   // $VF: synthetic field
   public static BlockPlanks$EnumType[] $VALUES = new BlockPlanks$EnumType[]{
      BlockPlanks$EnumType.OAK, BlockPlanks$EnumType.SPRUCE, BlockPlanks$EnumType.BIRCH, BlockPlanks$EnumType.JUNGLE, ACACIA, BlockPlanks$EnumType.DARK_OAK
   };
   public String unlocalizedName;
   public GuiEnchantment field_0003;
   public String name;
   public MapColor mapColor;
   public HudUtil field_0010;
   public int meta;
   public BlockCarpet field_0014;
   public EntitySpellParticleFX$Factory field_0000;
   public EntityAIAvoidEntity field_0005;

   @Override
   public String getName() {
      return this.name;
   }

   public BlockPlanks$EnumType(int var3, String var4, MapColor var5) {
      this(var3, var4, var4, var5);
   }

   public BlockPlanks$EnumType(int var3, String var4, String var5, MapColor var6) {
      this.meta = var3;
      this.name = var4;
      this.unlocalizedName = var5;
      this.mapColor = var6;
   }

   static {
      for (BlockPlanks$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   public int getMetadata() {
      return this.meta;
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   public static BlockPlanks$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   @Override
   public String toString() {
      return this.name;
   }
}
