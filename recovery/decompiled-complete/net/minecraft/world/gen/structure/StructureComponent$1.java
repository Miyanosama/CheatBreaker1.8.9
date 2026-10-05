package net.minecraft.world.gen.structure;

import javax.vecmath.Tuple3d;
import net.minecraft.util.EnumFacing;
import org.apache.log4j.net.SMTPAppender;

// $VF: synthetic class
public class StructureComponent$1 {
   public SMTPAppender field_0001;
   public StructureStrongholdPieces$1 field_0000;
   public Tuple3d field_0002;

   static {
      try {
         field_176100_a[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_176100_a[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_176100_a[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_176100_a[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
