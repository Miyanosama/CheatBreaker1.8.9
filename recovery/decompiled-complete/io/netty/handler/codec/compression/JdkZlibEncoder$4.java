package io.netty.handler.codec.compression;

import io.netty.handler.timeout.WriteTimeoutHandler$2;
import net.minecraft.enchantment.EnchantmentFishingSpeed;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.optifine.player.PlayerItemParser;
import org.slf4j.helpers.SubstituteLogger;

// $VF: synthetic class
public class JdkZlibEncoder$4 {
   public EnchantmentFishingSpeed __junk8093088036439462512;
   public WriteTimeoutHandler$2 __junk8084712989883377809;
   public InventoryLargeChest __junk1113404792131916748;
   public SubstituteLogger __junk5981751197891074140;
   public WorldGenDungeons __junk9063146424268602775;
   public PlayerItemParser __junk1874394977897124911;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.GZIP.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
