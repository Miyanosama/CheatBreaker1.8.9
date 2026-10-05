package io.netty.channel;

import io.netty.handler.codec.spdy.SpdyOrHttpChooser$1;
import io.netty.util.concurrent.EventExecutor;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.world.chunk.NibbleArray;

public class SucceededChannelFuture extends CompleteChannelFuture {
   public GuiInventory __junk8138472891306265588;
   public NibbleArray __junk1555010640235106220;
   public SpdyOrHttpChooser$1 __junk4610615023659209785;

   @Override
   public boolean isSuccess() {
      return true;
   }

   public SucceededChannelFuture(Channel var1, EventExecutor var2) {
      super(var1, var2);
   }

   @Override
   public Throwable cause() {
      return null;
   }
}
