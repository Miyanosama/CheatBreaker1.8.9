package net.minecraft.entity.passive;

import junit.runner.StandardTestSuiteLoader;
import net.minecraft.block.BlockDoubleWoodSlab;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.item.crafting.CraftingManager$1;
import net.minecraft.server.network.NetHandlerLoginServer$1;

public class EntityRabbit$RabbitMoveHelper extends EntityMoveHelper {
   public CraftingManager$1 field_0000;
   public BlockDoubleWoodSlab field_0002;
   public StandardTestSuiteLoader field_0003;
   public NetHandlerLoginServer$1 field_0001;
   public EntityRabbit theEntity;

   public EntityRabbit$RabbitMoveHelper(EntityRabbit var1) {
      super(var1);
      this.theEntity = var1;
   }

   @Override
   public void onUpdateMoveHelper() {
      if (this.theEntity.C && !this.theEntity.func_175523_cj()) {
         this.theEntity.setMovementSpeed(0.0);
      }

      super.onUpdateMoveHelper();
   }
}
