package net.minecraft.util;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.passive.EntitySheep$1;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;

public class ChatComponentTranslationFormatException extends IllegalArgumentException {
   public EnchantmentData field_0001;
   public EntitySheep$1 field_0002;
   public GenLayerRemoveTooMuchOcean field_0000;

   public ChatComponentTranslationFormatException(ChatComponentTranslation var1, Throwable var2) {
      super(String.format("Error while parsing: %s", var1), var2);
   }

   public ChatComponentTranslationFormatException(ChatComponentTranslation var1, int var2) {
      super(String.format("Invalid index %d requested for %s", var2, var1));
   }

   public ChatComponentTranslationFormatException(ChatComponentTranslation var1, String var2) {
      super(String.format("Error parsing: %s: %s", var1, var2));
   }
}
