package net.minecraft.potion;

import net.minecraft.block.material.Material$1;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.realms.RealmsServerStatusPinger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.optifine.NaturalTextures;

public class PotionHealthBoost extends Potion {
   public NaturalTextures field_0000;
   public Material$1 field_0001;
   public RealmsServerStatusPinger field_0002;
   public ChunkProviderEnd field_0003;

   @Override
   public void removeAttributesModifiersFromEntity(EntityLivingBase var1, BaseAttributeMap var2, int var3) {
      super.removeAttributesModifiersFromEntity(var1, var2, var3);
      if (var1.getHealth() > var1.getMaxHealth()) {
         var1.setHealth(var1.getMaxHealth());
      }
   }

   public PotionHealthBoost(int var1, ResourceLocation var2, boolean var3, int var4) {
      super(var1, var2, var3, var4);
   }
}
