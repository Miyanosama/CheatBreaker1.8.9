package net.minecraft.block;

import io.netty.handler.codec.http.multipart.DiskAttribute;
import net.minecraft.dispenser.IBehaviorDispenseItem$1;
import net.minecraft.util.IStringSerializable;

public enum BlockTrapDoor$DoorHalf implements IStringSerializable {
   BOTTOM("bottom"),
   TOP("top");

   public IBehaviorDispenseItem$1 field_0003;
   public String name;
   public DiskAttribute field_0001;

   public BlockTrapDoor$DoorHalf(String var3) {
      this.name = var3;
   }

   @Override
   public String toString() {
      return this.name;
   }

   @Override
   public String getName() {
      return this.name;
   }
}
