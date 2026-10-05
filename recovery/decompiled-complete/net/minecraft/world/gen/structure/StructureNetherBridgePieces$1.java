package net.minecraft.world.gen.structure;

import io.netty.buffer.ByteBufProcessor$9;
import net.minecraft.block.BlockStairs$EnumHalf;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.EnumFacing;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$23;

// $VF: synthetic class
public class StructureNetherBridgePieces$1 {
   public LogBrokerMonitor$23 field_0002;
   public EntityIronGolem field_0004;
   public BlockStairs$EnumHalf field_0003;
   public ByteBufProcessor$9 field_0000;

   static {
      try {
         field_175888_a[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_175888_a[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_175888_a[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_175888_a[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
