package net.minecraft.command;

import com.google.common.base.Predicate;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;

public class PlayerSelector$9 implements Predicate<Entity> {
   public PlayerSelector$9(Map var1) {
      this.field_179604_a = var1;
      super();
   }

   public boolean apply(Entity var1) {
      Scoreboard var2 = MinecraftServer.getServer().worldServerForDimension(0).Z();

      for (Entry var4 : this.field_179604_a.entrySet()) {
         String var5 = (String)var4.getKey();
         boolean var6 = false;
         if (var5.endsWith("_min") && var5.length() > 4) {
            var6 = true;
            var5 = var5.substring(0, var5.length() - 4);
         }

         ScoreObjective var7 = var2.getObjective(var5);
         if (var7 == null) {
            return false;
         }

         String var8 = var1 instanceof EntityPlayerMP ? var1.z_() : var1.aK().toString();
         if (!var2.entityHasObjective(var8, var7)) {
            return false;
         }

         Score var9 = var2.getValueFromObjective(var8, var7);
         int var10 = var9.getScorePoints();
         if (var10 < (Integer)var4.getValue() && var6) {
            return false;
         }

         if (var10 > (Integer)var4.getValue() && !var6) {
            return false;
         }
      }

      return true;
   }
}
