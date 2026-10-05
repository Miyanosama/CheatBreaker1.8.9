package net.minecraft.client.particle;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler$1;
import io.netty.handler.ssl.JettyNpnSslEngine$2;
import net.minecraft.network.play.server.S38PacketPlayerListItem$Action;
import net.minecraft.world.World;

public class EntityReddustFX$Factory implements IParticleFactory {
   public JettyNpnSslEngine$2 field_0001;
   public S38PacketPlayerListItem$Action field_0002;
   public WebSocketServerProtocolHandshakeHandler$1 field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityReddustFX(var2, var3, var5, var7, (float)var9, (float)var11, (float)var13);
   }
}
