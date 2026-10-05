package io.netty.handler.codec.compression;

import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.http.DefaultHttpHeaders$1;
import io.netty.util.concurrent.ScheduledFutureTask;
import io.netty.util.internal.chmv8.ForkJoinTask$AdaptedCallable;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.gui.GuiScreenDemo;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntitySkull;

public class CompressionException extends EncoderException {
   public DefaultHttpHeaders$1 __junk6034940963479946364;
   public PropertyDirection __junk7181971670801372907;
   public TileEntitySkull __junk752633673555197800;
   public ForkJoinTask$AdaptedCallable __junk3003923459862006169;
   public GuiScreenDemo __junk5682321357534113392;
   public ItemSword __junk176591571605345191;
   public static long serialVersionUID;
   public ScheduledFutureTask __junk7633701122013875889;

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
