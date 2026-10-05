package net.minecraft.world.gen.structure;

import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.util.EnumFacing;
import org.apache.log4j.helpers.SyslogQuietWriter;

// $VF: synthetic class
public class StructureVillagePieces$1 {
   public SyslogQuietWriter field_0002;
   public C0FPacketConfirmTransaction field_0000;

   static {
      try {
         field_176064_a[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_176064_a[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_176064_a[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_176064_a[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
