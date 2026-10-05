package net.minecraft.server.management;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.optifine.entity.model.ModelAdapterOcelot;
import recovered.unidentified.UnidentifiedClass5100;

public class PlayerProfileCache$1 implements ParameterizedType {
   public TileEntityEndPortal field_0001;
   public ModelAdapterOcelot field_0002;
   public UnidentifiedClass5100 field_0000;

   @Override
   public Type getRawType() {
      return List.class;
   }

   @Override
   public Type[] getActualTypeArguments() {
      return new Type[]{PlayerProfileCache$ProfileEntry.class};
   }

   @Override
   public Type getOwnerType() {
      return null;
   }
}
