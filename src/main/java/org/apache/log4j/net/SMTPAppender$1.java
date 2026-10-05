package org.apache.log4j.net;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAppender$1 extends Authenticator {
   // $VF: synthetic field
   public SMTPAppender this$0;

   public SMTPAppender$1(SMTPAppender var1) {
      this.this$0 = var1;
   }

   public PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(SMTPAppender.access$000(this.this$0), SMTPAppender.access$100(this.this$0));
   }
}
