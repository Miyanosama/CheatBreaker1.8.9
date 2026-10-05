package net.minecraft.item;

import net.minecraft.block.Block;
import net.optifine.reflect.ReflectorClass;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$14;

public class ItemPiston extends ItemBlock {
   public ReflectorClass field_0000;
   public LogBrokerMonitor$14 field_0001;

   public ItemPiston(Block var1) {
      super(var1);
   }

   @Override
   public int getMetadata(int var1) {
      return 7;
   }
}
