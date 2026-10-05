package net.minecraft.command;

import net.minecraft.client.particle.EntityLargeExplodeFX$Factory;
import net.minecraft.client.settings.GameSettings$2;
import net.optifine.ConnectedTexturesCompact;

public class WrongUsageException extends SyntaxErrorException {
   public EntityLargeExplodeFX$Factory field_0001;
   public ConnectedTexturesCompact field_0002;
   public GameSettings$2 field_0000;

   public WrongUsageException(String var1, Object... var2) {
      super(var1, var2);
   }
}
