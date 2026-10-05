package io.netty.buffer;

import io.netty.util.concurrent.SingleThreadEventExecutor$4;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.EntityRenderer$2;
import net.minecraft.client.resources.ResourcePackRepository$2;
import org.apache.log4j.helpers.BoundedFIFO;

public class ByteBufProcessor$8 implements ByteBufProcessor {
   public GuiChat __junk5383413985750222035;
   public EntityRenderer$2 __junk5417620901728697186;
   public BoundedFIFO __junk9073735099406877826;
   public ResourcePackRepository$2 __junk1658380953560163964;
   public SingleThreadEventExecutor$4 __junk8190930644064465363;

   @Override
   public boolean process(byte var1) {
      return var1 == 13 || var1 == 10;
   }
}
