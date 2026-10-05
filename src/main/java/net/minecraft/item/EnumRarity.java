package net.minecraft.item;

import net.minecraft.util.EnumChatFormatting;

public enum EnumRarity {
      COMMON(EnumChatFormatting.WHITE, "Common"),
      UNCOMMON(EnumChatFormatting.YELLOW, "Uncommon"),
      RARE(EnumChatFormatting.AQUA, "Rare"),
      EPIC(EnumChatFormatting.LIGHT_PURPLE, "Epic");

   public EnumChatFormatting rarityColor;
   public String rarityName;
   public static EnumRarity[] $VALUES = new EnumRarity[]{COMMON, UNCOMMON, RARE, EPIC};

   EnumRarity(EnumChatFormatting var3, String var4) {
      this.rarityColor = var3;
      this.rarityName = var4;
   }
}
