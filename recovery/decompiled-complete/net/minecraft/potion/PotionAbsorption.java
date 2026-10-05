package net.minecraft.potion;

import com.cheatbreaker.client.module.type.MemoryUsageModule;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;
import net.optifine.player.ModelPlayerItem;
import org.scijava.nativelib.BaseJniExtractor;

public class PotionAbsorption extends Potion {
   public BaseJniExtractor field_0000;
   public ModelPlayerItem field_0001;
   public WeightedRandom field_0002;
   public MemoryUsageModule field_0003;

   @Override
   public void removeAttributesModifiersFromEntity(EntityLivingBase var1, BaseAttributeMap var2, int var3) {
      var1.setAbsorptionAmount(var1.getAbsorptionAmount() - 4 * (var3 + 1));
      super.removeAttributesModifiersFromEntity(var1, var2, var3);
   }

   public PotionAbsorption(int var1, ResourceLocation var2, boolean var3, int var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   public void applyAttributesModifiersToEntity(EntityLivingBase var1, BaseAttributeMap var2, int var3) {
      var1.setAbsorptionAmount(var1.getAbsorptionAmount() + 4 * (var3 + 1));
      super.applyAttributesModifiersToEntity(var1, var2, var3);
   }
}
