package net.minecraft.entity;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.player.EntityPlayer$EnumStatus;
import recovered.unidentified.UnidentifiedClass0599;

public class Entity$3 implements Callable<String> {
   public UnidentifiedClass0599 field_0003;
   public EntityPlayer$EnumStatus field_0000;
   public RenderChicken field_0002;

   public Entity$3(Entity var1) {
      this.field_180119_a = var1;
      super();
   }

   public String call() {
      return this.field_180119_a.l.toString();
   }
}
