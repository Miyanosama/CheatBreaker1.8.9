package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.inventory.ContainerHorseInventory$2;
import net.minecraft.tileentity.TileEntityBrewingStand;

public class Minecraft$14 implements Callable<String> {
   public ContainerHorseInventory$2 field_0002;
   public EntityAIFollowParent field_0004;
   public EnchantmentProtection field_0001;
   public TileEntityBrewingStand field_0003;

   public Minecraft$14(Minecraft var1) {
      this.field_74421_a = var1;
      super();
   }

   public String call() {
      return Minecraft.access$000(this.field_74421_a);
   }
}
