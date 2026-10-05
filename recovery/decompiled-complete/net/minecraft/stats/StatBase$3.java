package net.minecraft.stats;

import io.netty.handler.codec.http.HttpMethod;
import net.optifine.entity.model.ModelAdapterSlime;

public class StatBase$3 implements IStatType {
   public ModelAdapterSlime field_0000;
   public HttpMethod field_0001;

   @Override
   public String format(int var1) {
      double var2 = var1 / 100.0;
      double var4 = var2 / 1000.0;
      return var4 > 0.5 ? StatBase.access$100().format(var4) + " km" : (var2 > 0.5 ? StatBase.access$100().format(var2) + " m" : var1 + " cm");
   }
}
