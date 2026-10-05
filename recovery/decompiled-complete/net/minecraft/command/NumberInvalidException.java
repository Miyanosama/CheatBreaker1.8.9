package net.minecraft.command;

import net.optifine.entity.model.ModelAdapterCaveSpider;

public class NumberInvalidException extends CommandException {
   public ModelAdapterCaveSpider field_0000;

   public NumberInvalidException(String var1, Object... var2) {
      super(var1, var2);
   }

   public NumberInvalidException() {
      this("commands.generic.num.invalid");
   }
}
