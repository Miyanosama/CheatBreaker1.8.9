package net.minecraft.block;

import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ReduceValuesTask;
import net.minecraft.util.IStringSerializable;

public enum BlockRedstoneWire$EnumAttachPosition implements IStringSerializable {
   SIDE("side"),
   UP("up"),
   NONE("none");
   public PipeStatus field_0005;
   public ConcurrentHashMapV8$ReduceValuesTask field_0002;
   public String name;
   // $VF: synthetic field
   public static BlockRedstoneWire$EnumAttachPosition[] $VALUES = new BlockRedstoneWire$EnumAttachPosition[]{
      BlockRedstoneWire$EnumAttachPosition.UP, SIDE, BlockRedstoneWire$EnumAttachPosition.NONE
   };

   public BlockRedstoneWire$EnumAttachPosition(String var3) {
      this.name = var3;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.getName();
   }
}
