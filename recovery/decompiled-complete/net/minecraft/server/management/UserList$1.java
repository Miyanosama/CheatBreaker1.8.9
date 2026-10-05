package net.minecraft.server.management;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import net.minecraft.client.resources.SimpleReloadableResourceManager$1;
import net.minecraft.client.settings.GameSettings;

public class UserList$1 implements ParameterizedType {
   public SimpleReloadableResourceManager$1 field_0000;
   public GameSettings field_0001;

   @Override
   public Type getRawType() {
      return List.class;
   }

   @Override
   public Type[] getActualTypeArguments() {
      return new Type[]{UserListEntry.class};
   }

   @Override
   public Type getOwnerType() {
      return null;
   }
}
