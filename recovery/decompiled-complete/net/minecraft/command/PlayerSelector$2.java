package net.minecraft.command;

import com.cheatbreaker.client.module.type.HitboxesModule;
import com.google.common.base.Predicate;
import io.netty.channel.nio.AbstractNioMessageChannel$1;
import net.minecraft.entity.Entity;
import net.minecraft.world.gen.structure.StructureVillagePieces$House3;

public class PlayerSelector$2 implements Predicate<Entity> {
   public StructureVillagePieces$House3 field_0002;
   public HitboxesModule field_0004;
   public AbstractNioMessageChannel$1 field_0000;

   public boolean apply(Entity var1) {
      int var2 = PlayerSelector.func_179650_a((int)Math.floor(var1.z));
      return this.field_179618_a > this.field_179617_b
         ? var2 >= this.field_179618_a || var2 <= this.field_179617_b
         : var2 >= this.field_179618_a && var2 <= this.field_179617_b;
   }

   public PlayerSelector$2(int var1, int var2) {
      this.field_179618_a = var1;
      this.field_179617_b = var2;
      super();
   }
}
