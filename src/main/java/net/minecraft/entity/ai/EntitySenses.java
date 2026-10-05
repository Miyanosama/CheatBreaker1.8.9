package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class EntitySenses {
   public List<Entity> unseenEntities;
   public List<Entity> seenEntities = Lists.newArrayList();
   public EntityLiving entityObj;

   public boolean canSee(Entity var1) {
      if (this.seenEntities.contains(var1)) {
         return true;
      } else if (this.unseenEntities.contains(var1)) {
         return false;
      } else {
         this.entityObj.o.B.startSection("canSee");
         boolean var2 = this.entityObj.t(var1);
         this.entityObj.o.B.endSection();
         if (var2) {
            this.seenEntities.add(var1);
         } else {
            this.unseenEntities.add(var1);
         }

         return var2;
      }
   }

   public EntitySenses(EntityLiving var1) {
      this.unseenEntities = Lists.newArrayList();
      this.entityObj = var1;
   }

   public void clearSensingCache() {
      this.seenEntities.clear();
      this.unseenEntities.clear();
   }
}
