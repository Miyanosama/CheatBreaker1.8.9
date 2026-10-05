package net.minecraft.command;

import com.google.common.base.Predicate;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerSelector$5 implements Predicate<Entity> {
   public RenderSquid field_0000;

   public boolean apply(Entity var1) {
      return var1 instanceof EntityPlayer;
   }
}
