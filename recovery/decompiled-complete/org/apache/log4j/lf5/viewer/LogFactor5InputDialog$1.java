package org.apache.log4j.lf5.viewer;

import io.netty.channel.AbstractChannel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import net.minecraft.command.server.CommandSetBlock;

public class LogFactor5InputDialog$1 extends KeyAdapter {
   public CommandSetBlock field_0001;
   public AbstractChannel field_0002;
   public LogFactor5InputDialog this$0;

   public void keyPressed(KeyEvent var1) {
      if (var1.getKeyCode() == 10) {
         this.this$0.hide();
      }
   }

   public LogFactor5InputDialog$1(LogFactor5InputDialog var1) {
      this.this$0 = var1;
      super();
   }
}
