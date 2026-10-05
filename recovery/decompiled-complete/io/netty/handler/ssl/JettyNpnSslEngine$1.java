package io.netty.handler.ssl;

import java.util.List;
import net.minecraft.client.renderer.BlockModelShapes$6;
import net.minecraft.network.ServerStatusResponse$MinecraftProtocolVersionIdentifier;
import org.eclipse.jetty.npn.NextProtoNego.ServerProvider;

public class JettyNpnSslEngine$1 implements ServerProvider {
   public BlockModelShapes$6 __junk1425521777078871267;
   public ServerStatusResponse$MinecraftProtocolVersionIdentifier __junk4178519851969390605;

   public void unsupported() {
      this.this$0.getSession().setApplicationProtocol((String)this.val$nextProtocols.get(this.val$nextProtocols.size() - 1));
   }

   public List<String> protocols() {
      return this.val$nextProtocols;
   }

   public JettyNpnSslEngine$1(JettyNpnSslEngine var1, List var2) {
      this.this$0 = var1;
      this.val$nextProtocols = var2;
      super();
   }

   public void protocolSelected(String var1) {
      this.this$0.getSession().setApplicationProtocol(var1);
   }
}
