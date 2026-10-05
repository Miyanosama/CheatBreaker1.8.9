package org.apache.log4j.lf5.viewer;

import com.cheatbreaker.client.util.voicechat.VoiceUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import net.minecraft.block.BlockAnvil;
import net.optifine.shaders.CustomTextureRaw;
import org.apache.log4j.lf5.LogLevel;

public class LogBrokerMonitor$11 implements ActionListener {
   public LogBrokerMonitor this$0;
   public CustomTextureRaw field_0005;
   public LogLevel val$logLevel;
   public VoiceUser field_0004;
   public JMenuItem val$result;
   public BlockAnvil field_0001;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.showLogLevelColorChangeDialog(this.val$result, this.val$logLevel);
   }

   public LogBrokerMonitor$11(LogBrokerMonitor var1, JMenuItem var2, LogLevel var3) {
      this.this$0 = var1;
      this.val$result = var2;
      this.val$logLevel = var3;
      super();
   }
}
