package net.minecraft.block.material;

import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget$Sorter;
import net.minecraft.network.play.server.S40PacketDisconnect;

public class MaterialTransparent extends Material {
   public ResourcePackListEntryDefault field_0000;
   public S40PacketDisconnect field_0001;
   public EntityAINearestAttackableTarget$Sorter field_0002;

   @Override
   public boolean isSolid() {
      return false;
   }

   @Override
   public boolean blocksLight() {
      return false;
   }

   @Override
   public boolean blocksMovement() {
      return false;
   }

   public MaterialTransparent(MapColor var1) {
      super(var1);
      this.i();
   }
}
