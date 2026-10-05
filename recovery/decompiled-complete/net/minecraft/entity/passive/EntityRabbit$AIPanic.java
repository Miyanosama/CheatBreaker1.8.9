package net.minecraft.entity.passive;

import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.item.ItemColored;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.tileentity.TileEntityComparator;
import net.optifine.shaders.gui.GuiSlotShaders;

public class EntityRabbit$AIPanic extends EntityAIPanic {
   public TileEntityComparator field_0004;
   public GuiSlotShaders field_0001;
   public ItemColored field_0005;
   public EntityRabbit theEntity;
   public EnchantmentProtection field_0002;
   public S3FPacketCustomPayload field_0003;

   @Override
   public void updateTask() {
      super.updateTask();
      this.theEntity.setMovementSpeed(this.a);
   }

   public EntityRabbit$AIPanic(EntityRabbit var1, double var2) {
      super(var1, var2);
      this.theEntity = var1;
   }
}
