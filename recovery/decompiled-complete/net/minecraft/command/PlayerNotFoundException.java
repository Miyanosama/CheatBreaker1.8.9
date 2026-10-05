package net.minecraft.command;

import io.netty.util.internal.ThreadLocalRandom$1;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntitySlime$AISlimeFloat;
import net.minecraft.inventory.ContainerPlayer;

public class PlayerNotFoundException extends CommandException {
   public ContainerPlayer field_0000;
   public ThreadLocalRandom$1 field_0003;
   public EntityAINearestAttackableTarget field_0002;
   public EntitySlime$AISlimeFloat field_0001;

   public PlayerNotFoundException() {
      this("commands.generic.player.notFound");
   }

   public PlayerNotFoundException(String var1, Object... var2) {
      super(var1, var2);
   }
}
