package net.minecraft.util;

import io.netty.handler.codec.spdy.SpdyHttpHeaders$Names;
import javazoom.jl.decoder.Obuffer;
import net.minecraft.enchantment.EnchantmentHelper$HurtIterator;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.item.ItemBlock;
import net.optifine.entity.model.ModelAdapterWolf;

public class StatCollector {
   public static StringTranslate localizedName = StringTranslate.getInstance();
   public EnchantmentHelper$HurtIterator field_0006;
   public EntityMinecartChest field_0002;
   public SpdyHttpHeaders$Names field_0005;
   public ItemBlock field_0000;
   public ModelAdapterWolf field_0001;
   public Obuffer field_0007;
   public static StringTranslate fallbackTranslator = new StringTranslate();

   public static String translateToFallback(String var0) {
      return fallbackTranslator.translateKey(var0);
   }

   public static boolean canTranslate(String var0) {
      return localizedName.isKeyTranslated(var0);
   }

   public static String translateToLocal(String var0) {
      return localizedName.translateKey(var0);
   }

   public static String translateToLocalFormatted(String var0, Object... var1) {
      return localizedName.translateKeyFormat(var0, var1);
   }

   public static long getLastTranslationUpdateTimeInMilliseconds() {
      return localizedName.getLastUpdateTimeInMilliseconds();
   }
}
