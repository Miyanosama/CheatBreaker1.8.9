package net.minecraft.command;

import net.minecraft.client.renderer.texture.TextureMap$2;
import net.optifine.reflect.ReflectorField;

public class CommandNotFoundException extends CommandException {
   public ReflectorField field_0000;
   public TextureMap$2 field_0001;

   public CommandNotFoundException() {
      this("commands.generic.notFound");
   }

   public CommandNotFoundException(String var1, Object... var2) {
      super(var1, var2);
   }
}
