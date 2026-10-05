package net.minecraft.creativetab;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Room;

public class CreativeTabs$3 extends CreativeTabs {
   public StructureMineshaftPieces$Room field_0000;

   @Override
   public Item getTabIconItem() {
      return Items.stick;
   }

   public CreativeTabs$3(int var1, String var2) {
      super(var1, var2);
   }
}
