package org.java_websocket.exceptions;

import net.minecraft.client.particle.Barrier;
import net.minecraft.world.EnumSkyBlock;

public class LimitExceededException extends InvalidDataException {
   public int limit;
   public static long field_0001;
   public EnumSkyBlock field_0003;
   public Barrier field_0002;

   public int getLimit() {
      return this.limit;
   }

   public LimitExceededException(String var1, int var2) {
      super(1009, var1);
      this.limit = var2;
   }

   public LimitExceededException(int var1) {
      super(1009);
      this.limit = var1;
   }

   public LimitExceededException(String var1) {
      this(var1, Integer.MAX_VALUE);
   }

   public LimitExceededException() {
      this(Integer.MAX_VALUE);
   }
}
