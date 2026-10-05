package io.netty.handler.ssl;

import java.util.List;
import org.eclipse.jetty.npn.NextProtoNego.ClientProvider;

public class JettyNpnSslEngine$2 implements ClientProvider {
   public String selectProtocol(List<String> var1) {
      for (String var5 : this.val$list) {
         if (var1.contains(var5)) {
            return var5;
         }
      }

      return this.val$fallback;
   }

   public void unsupported() {
      JettyNpnSslEngine.access$000(this.this$0).setApplicationProtocol(null);
   }

   public boolean supports() {
      return true;
   }

   public JettyNpnSslEngine$2(JettyNpnSslEngine var1, String[] var2, String var3) {
      this.this$0 = var1;
      this.val$list = var2;
      this.val$fallback = var3;
      super();
   }
}
