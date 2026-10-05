package net.minecraft.block;

import javazoom.jl.decoder.Equalizer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityCritFX$Factory;
import net.minecraft.util.IStringSerializable;
import recovered.unidentified.UnidentifiedClass4541;

public enum BlockSilverfish$EnumType implements IStringSerializable {
   MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick"),
   STONE(0, "stone"),
   COBBLESTONE(1, "cobblestone", "cobble"),
   CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick"),
   CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick"),
   STONEBRICK(2, "stone_brick", "brick");
   public EntityCritFX$Factory field_0006;
   public String unlocalizedName;
   // $VF: synthetic field
   public static BlockSilverfish$EnumType[] $VALUES = new BlockSilverfish$EnumType[]{
      BlockSilverfish$EnumType.STONE,
      BlockSilverfish$EnumType.COBBLESTONE,
      BlockSilverfish$EnumType.STONEBRICK,
      MOSSY_STONEBRICK,
      BlockSilverfish$EnumType.CRACKED_STONEBRICK,
      BlockSilverfish$EnumType.CHISELED_STONEBRICK
   };
   public static BlockSilverfish$EnumType[] META_LOOKUP = new BlockSilverfish$EnumType[values().length];
   public Equalizer field_0002;
   public String name;
   public UnidentifiedClass4541 field_0007;
   public int meta;

   public BlockSilverfish$EnumType(int var3, String var4) {
      this(var3, var4, var4);
   }

   public static BlockSilverfish$EnumType forModelBlock(IBlockState var0) {
      for (BlockSilverfish$EnumType var4 : values()) {
         if (var0 == var4.getModelBlock()) {
            return var4;
         }
      }

      return STONE;
   }

   public int getMetadata() {
      return this.meta;
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   public BlockSilverfish$EnumType(int var3, String var4, String var5) {
      this.meta = var3;
      this.name = var4;
      this.unlocalizedName = var5;
   }

   static {
      for (BlockSilverfish$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
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

   public static BlockSilverfish$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   public abstract IBlockState getModelBlock();
}
