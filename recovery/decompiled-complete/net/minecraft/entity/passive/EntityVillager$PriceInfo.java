package net.minecraft.entity.passive;

import java.util.Random;
import net.minecraft.util.Tuple;
import net.optifine.config.Weather;

public class EntityVillager$PriceInfo extends Tuple<Integer, Integer> {
   public Weather field_0000;

   public int getPrice(Random var1) {
      return this.getFirst() >= this.getSecond() ? this.getFirst() : this.getFirst() + var1.nextInt(this.getSecond() - this.getFirst() + 1);
   }

   public EntityVillager$PriceInfo(int var1, int var2) {
      super(var1, var2);
   }
}
