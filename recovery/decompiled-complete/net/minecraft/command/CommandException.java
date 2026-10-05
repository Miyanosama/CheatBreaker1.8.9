package net.minecraft.command;

import net.minecraft.block.BlockStone;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.item.crafting.CraftingManager;

public class CommandException extends Exception {
   public CraftingManager field_0002;
   public Object[] errorObjects;
   public ModelBoat field_0001;
   public PlayerSelector$11 field_0003;
   public BlockStone field_0000;

   public Object[] getErrorObjects() {
      return this.errorObjects;
   }

   public CommandException(String var1, Object... var2) {
      super(var1);
      this.errorObjects = var2;
   }
}
