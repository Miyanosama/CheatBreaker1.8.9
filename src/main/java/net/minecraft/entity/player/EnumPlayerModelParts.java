package net.minecraft.entity.player;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public enum EnumPlayerModelParts {
      CAPE(0, "cape"),
      JACKET(1, "jacket"),
      LEFT_SLEEVE(2, "left_sleeve"),
      RIGHT_SLEEVE(3, "right_sleeve"),
      LEFT_PANTS_LEG(4, "left_pants_leg"),
      RIGHT_PANTS_LEG(5, "right_pants_leg"),
      HAT(6, "hat");

   public static EnumPlayerModelParts[] $VALUES = new EnumPlayerModelParts[]{
      EnumPlayerModelParts.CAPE,
      EnumPlayerModelParts.JACKET,
      EnumPlayerModelParts.LEFT_SLEEVE,
      EnumPlayerModelParts.RIGHT_SLEEVE,
      EnumPlayerModelParts.LEFT_PANTS_LEG,
      EnumPlayerModelParts.RIGHT_PANTS_LEG,
      HAT
   };
   public int partMask;
   public IChatComponent field_179339_k;
   public String partName;
   public int partId;

   public int getPartId() {
      return this.partId;
   }

   EnumPlayerModelParts(int var3, String var4) {
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
