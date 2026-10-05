package net.minecraft.command.server;

import com.google.common.base.Predicate;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachValueTask;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Deserializer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;

public class CommandAchievement$1 implements Predicate<Achievement> {
   public ModelBlockDefinition$Deserializer field_0004;
   public ConcurrentHashMapV8$ForEachValueTask field_0000;
   public OldServerPinger field_0001;

   public CommandAchievement$1(CommandAchievement var1, EntityPlayerMP var2, StatBase var3) {
      this.field_179607_c = var1;
      this.field_179608_a = var2;
      this.field_179606_b = var3;
      super();
   }

   public boolean apply(Achievement var1) {
      return this.field_179608_a.getStatFile().hasAchievementUnlocked(var1) && var1 != this.field_179606_b;
   }
}
