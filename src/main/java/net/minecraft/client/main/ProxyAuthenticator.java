package net.minecraft.client.main;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyAuthenticator extends Authenticator {
   public String recoveredField775;
   public String recoveredField776;

   public ProxyAuthenticator(String var1, String var2) {
      this.recoveredField776 = var1;
      this.recoveredField775 = var2;
   }

   @Override
   public PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(this.recoveredField776, this.recoveredField775.toCharArray());
   }
}
