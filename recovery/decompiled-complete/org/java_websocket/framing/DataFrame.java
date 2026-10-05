package org.java_websocket.framing;

import net.minecraft.world.gen.layer.GenLayerBiome;
import org.java_websocket.enums.Opcode;
import recovered.unidentified.UnidentifiedClass3884;

public abstract class DataFrame extends FramedataImpl1 {
   public GenLayerBiome field_0000;
   public UnidentifiedClass3884 field_0001;

   public DataFrame(Opcode var1) {
      super(var1);
   }

   @Override
   public void isValid() {
   }
}
