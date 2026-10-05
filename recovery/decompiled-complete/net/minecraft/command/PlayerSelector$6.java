package net.minecraft.command;

import com.cheatbreaker.client.module.AbstractModule$PreviewType;
import com.google.common.base.Predicate;
import io.netty.buffer.DefaultByteBufHolder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerSelector$6 implements Predicate<Entity> {
   public DefaultByteBufHolder field_0003;
   public AbstractModule$PreviewType field_0000;

   public PlayerSelector$6(int var1, int var2) {
      this.field_179627_a = var1;
      this.field_179626_b = var2;
      super();
   }

   public boolean apply(Entity var1) {
      if (!(var1 instanceof EntityPlayerMP)) {
         return false;
      } else {
         EntityPlayerMP var2 = (EntityPlayerMP)var1;
         return (this.field_179627_a <= -1 || var2.bB >= this.field_179627_a) && (this.field_179626_b <= -1 || var2.bB <= this.field_179626_b);
      }
   }
}
