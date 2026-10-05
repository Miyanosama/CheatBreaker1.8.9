package net.minecraft.client.audio;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;

public class SoundHandler$1 implements ParameterizedType {
   public ChunkCompileTaskGenerator field_0000;

   @Override
   public Type[] getActualTypeArguments() {
      return new Type[]{String.class, SoundList.class};
   }

   @Override
   public Type getOwnerType() {
      return null;
   }

   @Override
   public Type getRawType() {
      return Map.class;
   }
}
