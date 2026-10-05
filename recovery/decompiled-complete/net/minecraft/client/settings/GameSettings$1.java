package net.minecraft.client.settings;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import net.minecraft.entity.projectile.EntityWitherSkull;

public class GameSettings$1 implements ParameterizedType {
   public EntityWitherSkull field_0000;

   @Override
   public Type getRawType() {
      return List.class;
   }

   @Override
   public Type[] getActualTypeArguments() {
      return new Type[]{String.class};
   }

   @Override
   public Type getOwnerType() {
      return null;
   }
}
