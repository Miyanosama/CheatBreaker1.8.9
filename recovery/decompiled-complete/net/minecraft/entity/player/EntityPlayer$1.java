package net.minecraft.entity.player;

import com.jagrosh.discordipc.entities.pipe.UnixPipe;
import net.minecraft.command.CommandClearInventory;
import net.minecraft.util.EnumFacing;
import recovered.unidentified.UnidentifiedClass1449;

// $VF: synthetic class
public class EntityPlayer$1 {
   public CommandClearInventory field_0001;
   public UnidentifiedClass1449 field_0000;
   public UnixPipe field_0002;

   static {
      try {
         field_179420_a[EnumFacing.SOUTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_179420_a[EnumFacing.NORTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_179420_a[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_179420_a[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
