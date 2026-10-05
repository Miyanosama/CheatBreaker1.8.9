package net.minecraft.entity.player;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$EntryIterator;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public enum EnumPlayerModelParts {
   HAT(6, "hat"),
   CAPE(0, "cape"),
   RIGHT_PANTS_LEG(5, "right_pants_leg"),
   LEFT_PANTS_LEG(4, "left_pants_leg"),
   LEFT_SLEEVE(2, "left_sleeve"),
   JACKET(1, "jacket"),
   RIGHT_SLEEVE(3, "right_sleeve");

   // $VF: synthetic field
   public static EnumPlayerModelParts[] $VALUES = new EnumPlayerModelParts[]{
      EnumPlayerModelParts.CAPE,
      EnumPlayerModelParts.JACKET,
      EnumPlayerModelParts.LEFT_SLEEVE,
      EnumPlayerModelParts.RIGHT_SLEEVE,
      EnumPlayerModelParts.LEFT_PANTS_LEG,
      EnumPlayerModelParts.RIGHT_PANTS_LEG,
      HAT
   };
   public BlockSilverfish field_0005;
   public int partMask;
   public ConcurrentHashMapV8$EntryIterator field_0002;
   public IChatComponent field_179339_k;
   public String partName;
   public int partId;

   public int getPartId() {
      return this.partId;
   }

   public EnumPlayerModelParts(int var3, String var4) {
      this.partId = var3;
      this.partMask = 1 << var3;
      this.partName = var4;
      this.field_179339_k = new ChatComponentTranslation("options.modelPart." + var4);
   }

   public IChatComponent func_179326_d() {
      return this.field_179339_k;
   }

   public int getPartMask() {
      return this.partMask;
   }

   public String getPartName() {
      return this.partName;
   }
}
