package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.crafting.RecipeFireworks;
import net.optifine.SmartAnimations;
import net.optifine.shaders.uniform.ShaderUniform4i;

public class EntityGuardian$1 implements Predicate<EntityPlayerMP> {
   public SmartAnimations field_0002;
   public BlockStateBase field_0004;
   public RecipeFireworks field_0001;
   public ShaderUniform4i field_0000;

   public boolean apply(EntityPlayerMP var1) {
      return this.field_179914_a.h(var1) < 2500.0 && var1.theItemInWorldManager.survivalOrAdventure();
   }

   public EntityGuardian$1(EntityGuardian var1) {
      this.field_179914_a = var1;
      super();
   }
}
