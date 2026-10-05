package net.minecraft.entity.passive;

import com.cheatbreaker.client.module.type.ReachDisplayModule;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class EntitySheep$1 extends Container {
   public ReachDisplayModule field_0001;
   public BlockNewLeaf field_0002;

   public EntitySheep$1(EntitySheep var1) {
      this.field_90034_a = var1;
      super();
   }

   @Override
   public boolean canInteractWith(EntityPlayer var1) {
      return false;
   }
}
