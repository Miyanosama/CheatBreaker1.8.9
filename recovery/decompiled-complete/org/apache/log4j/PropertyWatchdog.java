package org.apache.log4j;

import net.minecraft.client.renderer.chunk.RenderChunk;
import org.apache.log4j.helpers.FileWatchdog;

public class PropertyWatchdog extends FileWatchdog {
   public RenderChunk field_0000;

   public void doOnChange() {
      new PropertyConfigurator().doConfigure(this.filename, LogManager.getLoggerRepository());
   }

   public PropertyWatchdog(String var1) {
      super(var1);
   }
}
