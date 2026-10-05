package net.minecraft.block;

import io.netty.handler.codec.http.HttpObjectAggregator$AggregatedFullHttpMessage;
import io.netty.handler.codec.http.HttpObjectDecoder$LineParser;
import net.minecraft.client.gui.GuiScreenCustomizePresets$ListPreset;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.WorldProviderHell;
import net.optifine.shaders.uniform.ShaderUniform2i;
import org.slf4j.helpers.SubstituteLoggerFactory;

public enum BlockQuartz$EnumType implements IStringSerializable {
   LINES_Z(4, "lines_z", "lines"),
   LINES_X(3, "lines_x", "lines"),
   LINES_Y(2, "lines_y", "lines"),
   DEFAULT(0, "default", "default"),
   CHISELED(1, "chiseled", "chiseled");
   public String unlocalizedName;
   public static BlockQuartz$EnumType[] META_LOOKUP = new BlockQuartz$EnumType[values().length];
   public String field_176805_h;
   public WorldProviderHell field_0001;
   public GuiScreenCustomizePresets$ListPreset field_0002;
   public ShaderUniform2i field_0014;
   public HttpObjectAggregator$AggregatedFullHttpMessage field_0015;
   public SubstituteLoggerFactory field_0000;
   // $VF: synthetic field
   public static BlockQuartz$EnumType[] $VALUES = new BlockQuartz$EnumType[]{
      BlockQuartz$EnumType.DEFAULT, BlockQuartz$EnumType.CHISELED, LINES_Y, LINES_X, LINES_Z
   };
   public int meta;
   public HttpObjectDecoder$LineParser field_0010;

   public static BlockQuartz$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   @Override
   public String toString() {
      return this.unlocalizedName;
   }

   public BlockQuartz$EnumType(int var3, String var4, String var5) {
      this.meta = var3;
      this.field_176805_h = var4;
      this.unlocalizedName = var5;
   }

   public int getMetadata() {
      return this.meta;
   }

   @Override
   public String getName() {
      return this.field_176805_h;
   }

   static {
      for (BlockQuartz$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }
}
