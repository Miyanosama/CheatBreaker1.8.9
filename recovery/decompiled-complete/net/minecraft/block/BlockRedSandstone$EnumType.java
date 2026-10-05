package net.minecraft.block;

import net.minecraft.network.NetHandlerPlayServer$1;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.util.IStringSerializable;
import net.optifine.shaders.uniform.ShaderUniform1i;

public enum BlockRedSandstone$EnumType implements IStringSerializable {
   DEFAULT(0, "red_sandstone", "default"),
   CHISELED(1, "chiseled_red_sandstone", "chiseled"),
   SMOOTH(2, "smooth_red_sandstone", "smooth");
   public int meta;
   public static BlockRedSandstone$EnumType[] META_LOOKUP = new BlockRedSandstone$EnumType[values().length];
   public S07PacketRespawn field_0008;
   public ShaderUniform1i field_0001;
   public NetHandlerPlayServer$1 field_0002;
   public BlockSand field_0010;
   public String name;
   public String unlocalizedName;
   // $VF: synthetic field
   public static BlockRedSandstone$EnumType[] $VALUES = new BlockRedSandstone$EnumType[]{DEFAULT, CHISELED, BlockRedSandstone$EnumType.SMOOTH};

   @Override
   public String getName() {
      return this.name;
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   public BlockRedSandstone$EnumType(int var3, String var4, String var5) {
      this.meta = var3;
      this.name = var4;
      this.unlocalizedName = var5;
   }

   public static BlockRedSandstone$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   public int getMetadata() {
      return this.meta;
   }

   static {
      for (BlockRedSandstone$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }

   @Override
   public String toString() {
      return this.name;
   }
}
