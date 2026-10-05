package net.minecraft.client.renderer;

import com.google.common.base.Predicate;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.util.EntitySelectors$3;

public class EntityRenderer$1 implements Predicate<Entity> {
   public GuiControls field_0001;
   public EntitySelectors$3 field_0003;
   public EntityLookHelper field_0000;

   public boolean apply(Entity var1) {
      return var1.canBeCollidedWith();
   }

   public EntityRenderer$1(EntityRenderer var1) {
      this.this$0 = var1;
      super();
   }
}
