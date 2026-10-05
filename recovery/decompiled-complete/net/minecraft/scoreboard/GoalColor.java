package net.minecraft.scoreboard;

import io.netty.channel.sctp.nio.NioSctpChannel;
import java.util.List;
import net.minecraft.client.renderer.entity.layers.LayerHeldItemWitch;
import net.minecraft.creativetab.CreativeTabs$2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$31;
import org.apache.log4j.pattern.NameAbbreviator$NOPAbbreviator;
import recovered.unidentified.UnidentifiedClass0373;
import recovered.unidentified.UnidentifiedClass4584;

public class GoalColor implements IScoreObjectiveCriteria {
   public LayerHeldItemWitch field_0003;
   public UnidentifiedClass0373 field_0006;
   public NioSctpChannel field_0002;
   public NameAbbreviator$NOPAbbreviator field_0005;
   public CreativeTabs$2 field_0000;
   public UnidentifiedClass4584 field_0001;
   public String goalName;
   public LogBrokerMonitor$31 field_0004;

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
   public IScoreObjectiveCriteria$EnumRenderType getRenderType() {
      return IScoreObjectiveCriteria$EnumRenderType.INTEGER;
   }

   public GoalColor(String var1, EnumChatFormatting var2) {
      this.goalName = var1 + var2.getFriendlyName();
      IScoreObjectiveCriteria.INSTANCES.put(this.goalName, this);
   }
}
