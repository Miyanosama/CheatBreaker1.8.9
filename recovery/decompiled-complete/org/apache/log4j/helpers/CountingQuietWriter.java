package org.apache.log4j.helpers;

import com.cheatbreaker.client.cosmetic.model.DragonWingsModel;
import io.netty.channel.socket.nio.NioSocketChannel$NioSocketChannelConfig;
import java.io.IOException;
import java.io.Writer;
import net.minecraft.client.gui.GuiScreenBook$NextPageButton;
import net.minecraft.command.server.CommandBlockLogic$2;
import org.apache.log4j.spi.ErrorHandler;

public class CountingQuietWriter extends QuietWriter {
   public DragonWingsModel field_0002;
   public GuiScreenBook$NextPageButton field_0004;
   public long count;
   public NioSocketChannel$NioSocketChannelConfig field_0003;
   public CommandBlockLogic$2 field_0000;

   public void write(String var1) {
      try {
         this.out.write(var1);
         this.count = this.count + var1.length();
      } catch (IOException var3) {
         this.errorHandler.error("Write failure.", var3, 1);
      }
   }

   public long getCount() {
      return this.count;
   }

   public CountingQuietWriter(Writer var1, ErrorHandler var2) {
      super(var1, var2);
   }

   public void setCount(long var1) {
      this.count = var1;
   }
}
