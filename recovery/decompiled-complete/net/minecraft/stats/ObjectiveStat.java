package net.minecraft.stats;

import io.netty.util.HashedWheelTimer$HashedWheelTimeout;
import net.minecraft.scoreboard.ScoreDummyCriteria;
import org.apache.log4j.net.SocketAppender$Connector;
import org.apache.log4j.pattern.RelativeTimePatternConverter;

public class ObjectiveStat extends ScoreDummyCriteria {
   public HashedWheelTimer$HashedWheelTimeout field_0003;
   public StatBase stat;
   public SocketAppender$Connector field_0000;
   public RelativeTimePatternConverter field_0001;

   public ObjectiveStat(StatBase var1) {
      super(var1.statId);
      this.stat = var1;
   }
}
