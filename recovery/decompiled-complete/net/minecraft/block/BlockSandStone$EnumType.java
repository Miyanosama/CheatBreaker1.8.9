package net.minecraft.block;

import io.netty.channel.epoll.Native$NativeInetAddress;
import net.minecraft.util.IStringSerializable;
import org.apache.log4j.spi.NullWriter;

public enum BlockSandStone$EnumType implements IStringSerializable {
   CHISELED(1, "chiseled_sandstone", "chiseled"),
   SMOOTH(2, "smooth_sandstone", "smooth"),
   DEFAULT(0, "sandstone", "default");

   public static BlockSandStone$EnumType[] META_LOOKUP = new BlockSandStone$EnumType[values().length];
   public int metadata;
   // $VF: synthetic field
   public static BlockSandStone$EnumType[] $VALUES = new BlockSandStone$EnumType[]{
      BlockSandStone$EnumType.DEFAULT, BlockSandStone$EnumType.CHISELED, BlockSandStone$EnumType.SMOOTH
   };
   public String name;
   public Native$NativeInetAddress field_0008;
   public NullWriter field_0002;
   public String unlocalizedName;

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   public static BlockSandStone$EnumType byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   @Override
   public String getName() {
      return this.name;
   }

   public int getMetadata() {
      return this.metadata;
   }

   public BlockSandStone$EnumType(int var3, String var4, String var5) {
      this.metadata = var3;
      this.name = var4;
      this.unlocalizedName = var5;
   }

   @Override
   public String toString() {
      return this.name;
   }

   static {
      for (BlockSandStone$EnumType var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
      }
   }
}
