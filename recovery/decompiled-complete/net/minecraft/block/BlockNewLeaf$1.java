package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.client.gui.inventory.CreativeCrafting;
import net.minecraft.entity.EntityTrackerEntry;
import net.optifine.CustomPanoramaProperties;
import net.optifine.config.MatchBlock;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$9;

public class BlockNewLeaf$1 implements Predicate<BlockPlanks$EnumType> {
   public EntityTrackerEntry field_0002;
   public LogBrokerMonitor$9 field_0004;
   public CustomPanoramaProperties field_0001;
   public CreativeCrafting field_0003;
   public MatchBlock field_0000;

   public boolean apply(BlockPlanks$EnumType var1) {
      return var1.getMetadata() >= 4;
   }
}
