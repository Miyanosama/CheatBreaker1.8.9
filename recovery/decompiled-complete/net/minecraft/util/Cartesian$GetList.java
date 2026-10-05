package net.minecraft.util;

import com.google.common.base.Function;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.client.particle.EntityCloudFX;

public class Cartesian$GetList<T> implements Function<Object[], List<T>> {
   public EntityCloudFX field_0000;
   public BlockPumpkin field_0001;

   public Cartesian$GetList() {
   }

   public List<T> apply(Object[] var1) {
      return Arrays.asList((T[])var1);
   }
}
