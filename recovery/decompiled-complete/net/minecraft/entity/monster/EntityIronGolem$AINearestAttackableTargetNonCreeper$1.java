package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import javax.vecmath.Vector3d;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.gen.structure.StructureVillagePieces$WoodHut;
import net.optifine.config.ItemLocator;
import net.optifine.util.ArrayUtils;
import org.json.HTTPTokener;

public class EntityIronGolem$AINearestAttackableTargetNonCreeper$1 implements Predicate<T> {
   public StructureVillagePieces$WoodHut field_0006;
   public HTTPTokener field_0002;
   public ItemLocator field_0005;
   public ArrayUtils field_0001;
   public Vector3d field_0004;

   public EntityIronGolem$AINearestAttackableTargetNonCreeper$1(EntityIronGolem$AINearestAttackableTargetNonCreeper var1, Predicate var2, EntityCreature var3) {
      this.field_180098_c = var1;
      this.field_180099_a = var2;
      this.field_180097_b = var3;
      super();
   }

   public boolean apply(T var1) {
      if (this.field_180099_a != null && !this.field_180099_a.apply(var1)) {
         return false;
      } else if (var1 instanceof EntityCreeper) {
         return false;
      } else {
         if (var1 instanceof EntityPlayer) {
            double var2 = EntityIronGolem$AINearestAttackableTargetNonCreeper.access$000(this.field_180098_c);
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

            if (var1.g(this.field_180097_b) > var2) {
               return false;
            }
         }

         return EntityIronGolem$AINearestAttackableTargetNonCreeper.access$100(this.field_180098_c, var1, false);
      }
   }
}
