package org.apache.log4j.or;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.client.Minecraft;

public class DefaultRenderer implements ObjectRenderer {
   public BlockDaylightDetector field_0001;
   public BlockTripWireHook field_0003;
   public ObjectDecoderInputStream field_0000;
   public Minecraft field_0002;

   public String doRender(Object var1) {
      try {
         return var1.toString();
      } catch (Exception var3) {
         return var3.toString();
      }
   }
}
