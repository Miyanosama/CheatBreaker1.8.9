package net.minecraft.server.network;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import io.netty.handler.codec.http.HttpHeaders;
import java.math.BigInteger;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.server.management.UserList;
import net.minecraft.util.CryptManager;

public class NetHandlerLoginServer$2 extends Thread {
   public UserList field_0002;
   public Minecraft field_0004;
   public SoundManager field_0003;
   public HttpHeaders field_0000;

   public NetHandlerLoginServer$2(NetHandlerLoginServer var1, String var2) {
      this.field_180221_a = var1;
      super(var2);
   }

   @Override
   public void run() {
      GameProfile var1 = NetHandlerLoginServer.access$100(this.field_180221_a);

      try {
         String var2 = new BigInteger(
               CryptManager.getServerIdHash(
                  NetHandlerLoginServer.access$200(this.field_180221_a),
                  NetHandlerLoginServer.access$000(this.field_180221_a).getKeyPair().getPublic(),
                  NetHandlerLoginServer.access$300(this.field_180221_a)
               )
            )
            .toString(16);
         NetHandlerLoginServer.access$102(
            this.field_180221_a,
            NetHandlerLoginServer.access$000(this.field_180221_a)
               .getMinecraftSessionService()
               .hasJoinedServer(new GameProfile((UUID)null, var1.getName()), var2)
         );
         if (NetHandlerLoginServer.access$100(this.field_180221_a) != null) {
            NetHandlerLoginServer.access$400()
               .info(
                  "UUID of player "
                     + NetHandlerLoginServer.access$100(this.field_180221_a).getName()
                     + " is "
                     + NetHandlerLoginServer.access$100(this.field_180221_a).getId()
               );
            NetHandlerLoginServer.access$502(this.field_180221_a, NetHandlerLoginServer$LoginState.READY_TO_ACCEPT);
         } else if (NetHandlerLoginServer.access$000(this.field_180221_a).isSinglePlayer()) {
            NetHandlerLoginServer.access$400().warn("Failed to verify username but will let them in anyway!");
            NetHandlerLoginServer.access$102(this.field_180221_a, this.field_180221_a.getOfflineProfile(var1));
            NetHandlerLoginServer.access$502(this.field_180221_a, NetHandlerLoginServer$LoginState.READY_TO_ACCEPT);
         } else {
            this.field_180221_a.closeConnection("Failed to verify username!");
            NetHandlerLoginServer.access$400()
               .error("Username '" + NetHandlerLoginServer.access$100(this.field_180221_a).getName() + "' tried to join with an invalid session");
         }
      } catch (AuthenticationUnavailableException var3) {
         if (NetHandlerLoginServer.access$000(this.field_180221_a).isSinglePlayer()) {
            NetHandlerLoginServer.access$400().warn("Authentication servers are down but will let them in anyway!");
            NetHandlerLoginServer.access$102(this.field_180221_a, this.field_180221_a.getOfflineProfile(var1));
            NetHandlerLoginServer.access$502(this.field_180221_a, NetHandlerLoginServer$LoginState.READY_TO_ACCEPT);
         } else {
            this.field_180221_a.closeConnection("Authentication servers are down. Please try again later, sorry!");
            NetHandlerLoginServer.access$400().error("Couldn't verify username because servers are unavailable");
         }
      }
   }
}
