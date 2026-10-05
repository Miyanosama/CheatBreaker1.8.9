package io.netty.handler.codec.compression;

import io.netty.handler.codec.EncoderException;
import io.netty.util.concurrent.ScheduledFutureTask;
import io.netty.util.internal.chmv8.ForkJoinTask;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.gui.GuiScreenDemo;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntitySkull;

public class CompressionException extends EncoderException {
   public static final long serialVersionUID = 5603413481274811897L;

   public CompressionException(Throwable var1) {
      super(var1);
   }

   public CompressionException(String var1) {
      super(var1);
   }

   public CompressionException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public CompressionException() {
   }
}
