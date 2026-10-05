package net.minecraft.scoreboard;

import java.util.List;
import net.minecraft.client.particle.EntitySuspendFX$Factory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.optifine.http.FileUploadThread;

public class ScoreHealthCriteria extends ScoreDummyCriteria {
   public EntitySuspendFX$Factory field_0001;
   public FileUploadThread field_0000;

   public ScoreHealthCriteria(String var1) {
      super(var1);
   }

   @Override
   public boolean isReadOnly() {
      return true;
   }

   @Override
   public IScoreObjectiveCriteria$EnumRenderType getRenderType() {
      return IScoreObjectiveCriteria$EnumRenderType.HEARTS;
   }

   @Override
   public int setScore(List<EntityPlayer> var1) {
      float var2 = 0.0F;

      for (EntityPlayer var4 : var1) {
         var2 += var4.getHealth() + var4.getAbsorptionAmount();
      }

      if (var1.size() > 0) {
         var2 /= var1.size();
      }

      return MathHelper.ceiling_float_int(var2);
   }
}
