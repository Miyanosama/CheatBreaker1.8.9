package net.minecraft.world.gen.structure;

import io.netty.channel.oio.AbstractOioChannel;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.Chunk$1;
import recovered.unidentified.UnidentifiedClass3330;

// $VF: synthetic class
public class StructureMineshaftPieces$1 {
   public AbstractOioChannel field_0004;
   public UnidentifiedClass3330 field_0001;
   public EntityTameable field_0003;
   public Chunk$1 field_0000;

   static {
      try {
         field_175894_a[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_175894_a[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_175894_a[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_175894_a[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
