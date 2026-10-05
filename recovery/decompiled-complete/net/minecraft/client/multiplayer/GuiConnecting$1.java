package net.minecraft.client.multiplayer;

import com.cheatbreaker.client.CheatBreaker;
import java.net.InetAddress;
import java.net.UnknownHostException;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.particle.EntityRainFX$Factory;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentTranslation;
import org.newsclub.net.unix.NativeUnixSocket;
import recovered.unidentified.UnidentifiedClass4396;

public class GuiConnecting$1 extends Thread {
   public NativeUnixSocket field_0002;
   public EntityRainFX$Factory field_0001;

   public GuiConnecting$1(GuiConnecting var1, String var2, String var3, int var4) {
      this.field_148230_c = var1;
      this.field_148231_a = var3;
      this.field_148229_b = var4;
      super(var2);
   }

   @Override
   public void run() {
      InetAddress var1 = null;

      try {
         if (GuiConnecting.access$000(this.field_148230_c)) {
            return;
         }

         CheatBreaker.getInstance().method_19817().method_21935(new UnidentifiedClass4396());
         var1 = InetAddress.getByName(this.field_148231_a);
         GuiConnecting.access$102(
            this.field_148230_c,
            NetworkManager.createNetworkManagerAndConnect(var1, this.field_148229_b, this.field_148230_c.j.gameSettings.isUsingNativeTransport())
         );
         GuiConnecting.access$100(this.field_148230_c)
            .setNetHandler(
               new NetHandlerLoginClient(GuiConnecting.access$100(this.field_148230_c), this.field_148230_c.j, GuiConnecting.access$200(this.field_148230_c))
            );
         GuiConnecting.access$100(this.field_148230_c).sendPacket(new C00Handshake(47, this.field_148231_a, this.field_148229_b, EnumConnectionState.LOGIN));
         GuiConnecting.access$100(this.field_148230_c).sendPacket(new C00PacketLoginStart(this.field_148230_c.j.getSession().getProfile()));
      } catch (UnknownHostException var5) {
         if (GuiConnecting.access$000(this.field_148230_c)) {
            return;
         }

         GuiConnecting.access$300().error("Couldn't connect to server", var5);
         this.field_148230_c
            .j
            .displayGuiScreen(
               new GuiDisconnected(
                  GuiConnecting.access$200(this.field_148230_c), "connect.failed", new ChatComponentTranslation("disconnect.genericReason", "Unknown host")
               )
            );
      } catch (Exception var6) {
         if (GuiConnecting.access$000(this.field_148230_c)) {
            return;
         }

         GuiConnecting.access$300().error("Couldn't connect to server", var6);
         String var3 = var6.toString();
         if (var1 != null) {
            String var4 = var1.toString() + ":" + this.field_148229_b;
            var3 = var3.replaceAll(var4, "");
         }

         this.field_148230_c
            .j
            .displayGuiScreen(
               new GuiDisconnected(
                  GuiConnecting.access$200(this.field_148230_c), "connect.failed", new ChatComponentTranslation("disconnect.genericReason", var3)
               )
            );
      }
   }
}
