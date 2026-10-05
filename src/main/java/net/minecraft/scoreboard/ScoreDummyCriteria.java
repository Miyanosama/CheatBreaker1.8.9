package net.minecraft.scoreboard;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class ScoreDummyCriteria implements IScoreObjectiveCriteria {
   public String dummyName;

   public ScoreDummyCriteria(String var1) {
      this.dummyName = var1;
      IScoreObjectiveCriteria.INSTANCES.put(var1, this);
   }

   @Override
   public int setScore(List<EntityPlayer> var1) {
      return 0;
   }

   @Override
   public String getName() {
      return this.dummyName;
   }

   @Override
   public boolean isReadOnly() {
      return false;
   }

   @Override
   public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
      return IScoreObjectiveCriteria.EnumRenderType.INTEGER;
   }
}
