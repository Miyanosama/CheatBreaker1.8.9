package net.minecraft.command;

import com.google.common.base.Predicate;
import io.netty.handler.codec.spdy.DefaultSpdyStreamFrame;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerPlayer$1;
import net.minecraft.util.MessageSerializer2;

public class PlayerSelector$7 implements Predicate<Entity> {
   public ContainerPlayer$1 field_0002;
   public DefaultSpdyStreamFrame field_0004;
   public MessageSerializer2 field_0001;
   public EntityAIVillagerMate field_0000;

   public boolean apply(Entity var1) {
      if (!(var1 instanceof EntityPlayerMP)) {
         return false;
      } else {
         EntityPlayerMP var2 = (EntityPlayerMP)var1;
         return var2.theItemInWorldManager.getGameType().getID() == this.field_179620_a;
      }
   }

   public PlayerSelector$7(int var1) {
      this.field_179620_a = var1;
      super();
   }
}
