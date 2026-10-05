package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import net.minecraft.client.renderer.block.model.ItemModelGenerator$1;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.network.play.server.S44PacketWorldBorder$Action;

public class EntityAIFindEntityNearestPlayer$1 implements Predicate<Entity> {
   public S44PacketWorldBorder$Action field_0003;
   public ItemModelGenerator$1 field_0000;
   public ItemEnderPearl field_0002;

   public boolean apply(Entity var1) {
      if (!(var1 instanceof EntityPlayer)) {
         return false;
      } else if (((EntityPlayer)var1).bA.disableDamage) {
         return false;
      } else {
         double var2 = this.field_179881_a.maxTargetRange();
         if (var1.isSneaking()) {
            var2 *= 0.8F;
         }

         if (var1.isInvisible()) {
            float var4 = ((EntityPlayer)var1).getArmorVisibility();
            if (var4 < 0.1F) {
               var4 = 0.1F;
            }

            var2 *= 0.7F * var4;
         }

         return var1.g(EntityAIFindEntityNearestPlayer.access$000(this.field_179881_a)) > var2
            ? false
            : EntityAITarget.isSuitableTarget(EntityAIFindEntityNearestPlayer.access$000(this.field_179881_a), (EntityLivingBase)var1, false, true);
      }
   }

   public EntityAIFindEntityNearestPlayer$1(EntityAIFindEntityNearestPlayer var1) {
      this.field_179881_a = var1;
      super();
   }
}
