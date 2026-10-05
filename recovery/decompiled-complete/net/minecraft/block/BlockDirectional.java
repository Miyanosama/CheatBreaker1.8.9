package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.item.ItemBook;
import net.minecraft.util.EnumFacing$Plane;
import org.java_websocket.exceptions.InvalidDataException;
import recovered.unidentified.UnidentifiedClass1617;

public abstract class BlockDirectional extends Block {
   public static PropertyDirection O = PropertyDirection.create("facing", EnumFacing$Plane.HORIZONTAL);
   public UnidentifiedClass1617 field_0003;
   public InvalidDataException field_0002;
   public ItemBook field_0000;

   public BlockDirectional(Material var1, MapColor var2) {
      super(var1, var2);
   }

   public BlockDirectional(Material var1) {
      super(var1);
   }
}
