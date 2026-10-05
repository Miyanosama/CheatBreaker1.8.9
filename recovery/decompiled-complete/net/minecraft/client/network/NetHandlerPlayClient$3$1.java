package net.minecraft.client.network;

import com.google.common.util.concurrent.Futures;
import io.netty.util.internal.PlatformDependent0;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.main.lIIIIIIlIIllllIIlIIIlllII;
import net.minecraft.client.multiplayer.ServerData$ServerResourceMode;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.client.C19PacketResourcePackStatus$Action;
import net.optifine.entity.model.anim.RenderResolverTileEntity;

public class NetHandlerPlayClient$3$1 implements GuiYesNoCallback {
   public RenderResolverTileEntity field_0002;
   public C03PacketPlayer field_0004;
   public lIIIIIIlIIllllIIlIIIlllII field_0001;
   public PlatformDependent0 field_0000;

   public NetHandlerPlayClient$3$1(NetHandlerPlayClient$3 var1) {
      this.field_175395_a = var1;
      super();
   }

   @Override
   public void confirmClicked(boolean var1, int var2) {
      NetHandlerPlayClient.access$102(this.field_175395_a.field_178899_c, Minecraft.getMinecraft());
      if (var1) {
         if (NetHandlerPlayClient.access$100(this.field_175395_a.field_178899_c).getCurrentServerData() != null) {
            NetHandlerPlayClient.access$100(this.field_175395_a.field_178899_c).getCurrentServerData().setResourceMode(ServerData$ServerResourceMode.ENABLED);
         }

         NetHandlerPlayClient.access$000(this.field_175395_a.field_178899_c)
            .sendPacket(new C19PacketResourcePackStatus(this.field_175395_a.field_178900_a, C19PacketResourcePackStatus$Action.ACCEPTED));
         Futures.addCallback(
            NetHandlerPlayClient.access$100(this.field_175395_a.field_178899_c)
               .getResourcePackRepository()
               .downloadResourcePack(this.field_175395_a.field_178898_b, this.field_175395_a.field_178900_a),
            new NetHandlerPlayClient$3$1$1(this)
         );
      } else {
         if (NetHandlerPlayClient.access$100(this.field_175395_a.field_178899_c).getCurrentServerData() != null) {
            NetHandlerPlayClient.access$100(this.field_175395_a.field_178899_c).getCurrentServerData().setResourceMode(ServerData$ServerResourceMode.DISABLED);
         }

         NetHandlerPlayClient.access$000(this.field_175395_a.field_178899_c)
            .sendPacket(new C19PacketResourcePackStatus(this.field_175395_a.field_178900_a, C19PacketResourcePackStatus$Action.DECLINED));
      }

      ServerList.func_147414_b(NetHandlerPlayClient.access$100(this.field_175395_a.field_178899_c).getCurrentServerData());
      NetHandlerPlayClient.access$100(this.field_175395_a.field_178899_c).displayGuiScreen((GuiScreen)null);
   }
}
