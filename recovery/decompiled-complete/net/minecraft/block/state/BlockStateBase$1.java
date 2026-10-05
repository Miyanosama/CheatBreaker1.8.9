package net.minecraft.block.state;

import com.google.common.base.Function;
import java.util.Map.Entry;
import net.minecraft.block.properties.IProperty;
import net.minecraft.network.NetHandlerPlayServer$3;
import org.apache.log4j.chainsaw.LoggingReceiver$Slurper;

public class BlockStateBase$1 implements Function<Entry<IProperty, Comparable>, String> {
   public LoggingReceiver$Slurper field_0000;
   public NetHandlerPlayServer$3 field_0001;

   public String apply(Entry<IProperty, Comparable> var1) {
      if (var1 == null) {
         return "<NULL>";
      } else {
         IProperty var2 = (IProperty)var1.getKey();
         return var2.getName() + "=" + var2.getName((Comparable)var1.getValue());
      }
   }
}
