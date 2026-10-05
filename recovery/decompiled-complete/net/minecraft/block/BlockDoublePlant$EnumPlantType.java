package net.minecraft.block;

import com.cheatbreaker.client.module.AbstractModule$PreviewType;
import io.netty.handler.codec.spdy.SpdyFrameCodec$1;
import javazoom.jl.converter.jlc$jlcArgs;
import net.minecraft.util.IStringSerializable;

public enum BlockDoublePlant$EnumPlantType implements IStringSerializable {
   PAEONIA(5, "paeonia"),
   ROSE(4, "double_rose", "rose"),
   SYRINGA(1, "syringa"),
   SUNFLOWER(0, "sunflower"),
   GRASS(2, "double_grass", "grass"),
   FERN(3, "double_fern", "fern");
   public AbstractModule$PreviewType field_0011;
   public String name;
   public SpdyFrameCodec$1 field_0002;
   // $VF: synthetic field
   public static BlockDoublePlant$EnumPlantType[] $VALUES = new BlockDoublePlant$EnumPlantType[]{
      BlockDoublePlant$EnumPlantType.SUNFLOWER, SYRINGA, BlockDoublePlant$EnumPlantType.GRASS, BlockDoublePlant$EnumPlantType.FERN, ROSE, PAEONIA
   };
   public int meta;
   public static BlockDoublePlant$EnumPlantType[] META_LOOKUP = new BlockDoublePlant$EnumPlantType[values().length];
   public jlc$jlcArgs field_0000;
   public String unlocalizedName;

   public static BlockDoublePlant$EnumPlantType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   public BlockDoublePlant$EnumPlantType(int var3, String var4, String var5) {
      this.meta = var3;
      this.name = var4;
      this.unlocalizedName = var5;
   }

   public int getMeta() {
      return this.meta;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public BlockDoublePlant$EnumPlantType(int var3, String var4) {
      this(var3, var4, var4);
   }

   static {
      for (BlockDoublePlant$EnumPlantType var3 : values()) {
         META_LOOKUP[var3.getMeta()] = var3;
      }
   }
}
