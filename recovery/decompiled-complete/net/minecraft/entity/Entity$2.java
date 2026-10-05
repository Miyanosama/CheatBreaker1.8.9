package net.minecraft.entity;

import java.util.concurrent.Callable;
import recovered.unidentified.UnidentifiedClass3279;
import recovered.unidentified.UnidentifiedClass3734;

public class Entity$2 implements Callable<String> {
   public UnidentifiedClass3279 field_0001;
   public UnidentifiedClass3734 field_0000;

   public Entity$2(Entity var1) {
      this.field_96564_a = var1;
      super();
   }

   public String call() {
      return this.field_96564_a.z_();
   }
}
