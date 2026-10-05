package net.minecraft.item;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;

public enum EnumRarity {
   RARE(EnumChatFormatting.AQUA, "Rare"),
   UNCOMMON(EnumChatFormatting.YELLOW, "Uncommon"),
   COMMON(EnumChatFormatting.WHITE, "Common"),
   EPIC(EnumChatFormatting.LIGHT_PURPLE, "Epic");

   public EnumChatFormatting rarityColor;
   public String rarityName;
   public WorldGenMegaPineTree field_0007;

   public EnumRarity(EnumChatFormatting var3, String var4) {
      this.rarityColor = var3;
      this.rarityName = var4;
   }
}
