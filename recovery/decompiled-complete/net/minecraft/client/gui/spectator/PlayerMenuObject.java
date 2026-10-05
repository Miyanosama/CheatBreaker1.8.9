package net.minecraft.client.gui.spectator;

import com.mojang.authlib.GameProfile;
import io.netty.channel.group.DefaultChannelGroup$1;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class PlayerMenuObject implements ISpectatorMenuObject {
   public DiskFileUpload field_0003;
   public ResourceLocation resourceLocation;
   public GameProfile profile;
   public DefaultChannelGroup$1 field_0004;
   public PacketBuffer field_0000;
   public TileEntitySpecialRenderer field_0001;

   @Override
   public IChatComponent getSpectatorName() {
      return new ChatComponentText(this.profile.getName());
   }

   public PlayerMenuObject(GameProfile var1) {
      this.profile = var1;
      this.resourceLocation = AbstractClientPlayer.getLocationSkin(var1.getName());
      AbstractClientPlayer.getDownloadImageSkin(this.resourceLocation, var1.getName());
   }

   @Override
   public boolean func_178662_A_() {
      return true;
   }

   @Override
   public void func_178663_a(float var1, int var2) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(this.resourceLocation);
      GlStateManager.color(1.0F, 1.0F, 1.0F, var2 / 255.0F);
      Gui.drawScaledCustomSizeModalRect(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      Gui.drawScaledCustomSizeModalRect(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
   }

   @Override
   public void func_178661_a(SpectatorMenu var1) {
      Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C18PacketSpectate(this.profile.getId()));
   }
}
