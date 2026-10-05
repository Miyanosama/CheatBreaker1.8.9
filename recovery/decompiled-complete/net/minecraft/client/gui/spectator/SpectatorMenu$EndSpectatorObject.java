package net.minecraft.client.gui.spectator;

import io.netty.handler.codec.marshalling.CompatibleMarshallingDecoder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SpectatorMenu$EndSpectatorObject implements ISpectatorMenuObject {
   public NumberInvalidException field_0000;
   public CompatibleMarshallingDecoder field_0001;

   public SpectatorMenu$EndSpectatorObject() {
   }

   @Override
   public void func_178661_a(SpectatorMenu var1) {
      var1.func_178641_d();
   }

   @Override
   public void func_178663_a(float var1, int var2) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
      Gui.drawModalRectWithCustomSizedTexture(0, 0, 128.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   @Override
   public IChatComponent getSpectatorName() {
      return new ChatComponentText("Close menu");
   }

   @Override
   public boolean func_178662_A_() {
      return true;
   }
}
