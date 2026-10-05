package net.minecraft.command;

public class CommandException extends Exception {
   public Object[] errorObjects;

   public Object[] getErrorObjects() {
      return this.errorObjects;
   }

   public CommandException(String var1, Object... var2) {
      super(var1);
      this.errorObjects = var2;
   }
}
