package net.optifine;

import java.util.BitSet;
import net.minecraft.src.Config;
import net.optifine.shaders.Shaders;

public class SmartAnimations {
   public static boolean active;
   public static BitSet spritesRendered = new BitSet();
   public static BitSet texturesRendered = new BitSet();

   public static void textureRendered(int var0) {
      if (var0 >= 0) {
         texturesRendered.set(var0);
      }
   }

   public static void spritesRendered(BitSet var0) {
      if (var0 != null) {
         spritesRendered.or(var0);
      }
   }

   public static void resetTexturesRendered() {
      texturesRendered.clear();
   }

   public static void method_01908(int var0) {
      if (var0 >= 0) {
         spritesRendered.set(var0);
      }
   }

   public static boolean method_01901(int var0) {
      return var0 < 0 ? false : texturesRendered.get(var0);
   }

   public static void update() {
      active = Config.getGameSettings().ofSmartAnimations;
   }

   public static void resetSpritesRendered() {
      spritesRendered.clear();
   }

   public static boolean isActive() {
      return active && !Shaders.isShadowPass;
   }

   public static boolean method_01906(int var0) {
      return var0 < 0 ? false : spritesRendered.get(var0);
   }
}
