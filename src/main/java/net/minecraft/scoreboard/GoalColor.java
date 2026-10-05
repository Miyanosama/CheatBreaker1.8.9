package net.minecraft.scoreboard;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class GoalColor implements IScoreObjectiveCriteria {
   public String goalName;

   @Override
   public String getName() {
      return this.goalName;
   }

   @Override
   public boolean isReadOnly() {
      return false;
   }

   @Override
   public int setScore(List<EntityPlayer> var1) {
      return 0;
   }

   @Override
   public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
      return IScoreObjectiveCriteria.EnumRenderType.INTEGER;
   }

   public GoalColor(String var1, EnumChatFormatting var2) {
      this.goalName = var1 + var2.getFriendlyName();
      IScoreObjectiveCriteria.INSTANCES.put(this.goalName, this);
   }
}
