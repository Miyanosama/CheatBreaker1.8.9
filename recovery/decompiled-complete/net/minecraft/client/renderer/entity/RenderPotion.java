package net.minecraft.client.renderer.entity;

import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenSavanna;
import recovered.unidentified.UnidentifiedClass0212;
import recovered.unidentified.UnidentifiedClass3394;

public class RenderPotion extends UnidentifiedClass3394<EntityPotion> {
   public UnidentifiedClass0212 field_0000;
   public BiomeGenSavanna field_0001;
   public EntityAITargetNonTamed field_0002;

   public RenderPotion(RenderManager var1, RenderItem var2) {
      super(var1, Items.potionitem, var2);
   }

   public ItemStack method_04792(EntityPotion var1) {
      return new ItemStack(this.field_0001, 1, var1.getPotionDamage());
   }
}
