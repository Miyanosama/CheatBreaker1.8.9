package net.minecraft.server.network;

import net.minecraft.block.BlockHay;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.util.StatCollector;
import net.optifine.shaders.config.PropertyDefaultFastFancyOff;

// $VF: synthetic class
public class NetHandlerHandshakeTCP$1 {
   public StatCollector field_0001;
   public BlockHay field_0003;
   public PropertyDefaultFastFancyOff field_0002;

   static {
      try {
         field_151291_a[EnumConnectionState.LOGIN.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_151291_a[EnumConnectionState.STATUS.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
