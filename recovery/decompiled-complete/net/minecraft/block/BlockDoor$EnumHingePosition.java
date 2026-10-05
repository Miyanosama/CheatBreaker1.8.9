package net.minecraft.block;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachTransformedMappingTask;
import net.minecraft.client.particle.EntityFlameFX$Factory;
import net.minecraft.util.IStringSerializable;

public enum BlockDoor$EnumHingePosition implements IStringSerializable {
   LEFT,
   RIGHT;

   public EntityFlameFX$Factory field_0003;
   public BlockRedSandstone field_0005;
   // $VF: synthetic field
   public static BlockDoor$EnumHingePosition[] $VALUES = new BlockDoor$EnumHingePosition[]{BlockDoor$EnumHingePosition.LEFT, BlockDoor$EnumHingePosition.RIGHT};
   public ConcurrentHashMapV8$ForEachTransformedMappingTask field_0001;

   @Override
   public String toString() {
      return this.getName();
   }

   @Override
   public String getName() {
      return this == LEFT ? "left" : "right";
   }
}
