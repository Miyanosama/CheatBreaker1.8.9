package org.apache.log4j.net;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import net.minecraft.client.renderer.RenderGlobal$1;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.optifine.gui.GuiScreenCapeOF;

public class SMTPAppender$1 extends Authenticator {
   public ScoreboardSaveData field_0001;
   public GuiScreenCapeOF field_0003;
   public SMTPAppender this$0;
   public RenderGlobal$1 field_0002;

   public SMTPAppender$1(SMTPAppender var1) {
      this.this$0 = var1;
      super();
   }

   public PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(SMTPAppender.access$000(this.this$0), SMTPAppender.access$100(this.this$0));
   }
}
