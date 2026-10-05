package net.minecraft.block;

import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Corridor2;

// $VF: synthetic class
public class BlockRailPowered$2 {
   public EntityLargeExplodeFX field_0001;
   public EntityGhast field_0003;
   public StructureNetherBridgePieces$Corridor2 field_0000;

   static {
      try {
         field_0002[BlockRailBase$EnumRailDirection.NORTH_SOUTH.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_0002[BlockRailBase$EnumRailDirection.EAST_WEST.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_0002[BlockRailBase$EnumRailDirection.ASCENDING_EAST.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0002[BlockRailBase$EnumRailDirection.ASCENDING_WEST.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0002[BlockRailBase$EnumRailDirection.ASCENDING_NORTH.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0002[BlockRailBase$EnumRailDirection.ASCENDING_SOUTH.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
