package org.apache.log4j.lf5.util;

import io.netty.handler.codec.compression.JZlibEncoder$3;
import net.minecraft.command.server.CommandSetDefaultSpawnpoint;

public class LogFileParser$1 implements Runnable {
   public CommandSetDefaultSpawnpoint field_0001;
   public JZlibEncoder$3 field_0002;
   public LogFileParser this$0;

   public LogFileParser$1(LogFileParser var1) {
      this.this$0 = var1;
      super();
   }

   public void run() {
      LogFileParser.access$000(this.this$0);
   }
}
