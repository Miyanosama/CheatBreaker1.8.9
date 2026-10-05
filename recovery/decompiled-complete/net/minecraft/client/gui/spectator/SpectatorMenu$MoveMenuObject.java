package net.minecraft.client.gui.spectator;

import com.cheatbreaker.client.module.type.CPSModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.entity.Entity$4;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SpectatorMenu$MoveMenuObject implements ISpectatorMenuObject {
   public CPSModule field_0003;
   public boolean field_178665_b;
   public int field_178666_a;
   public Entity$4 field_0004;
   public S0DPacketCollectItem field_0000;
   public EntityWitch field_0001;

   @Override
   public void func_178663_a(float var1, int var2) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
      if (this.field_178666_a < 0) {
         Gui.drawModalRectWithCustomSizedTexture(0, 0, 144.0F, 0.0F, 16, 16, 256.0F, 256.0F);
      } else {
         Gui.drawModalRectWithCustomSizedTexture(0, 0, 160.0F, 0.0F, 16, 16, 256.0F, 256.0F);
      }
   }

   @Override
   public boolean func_178662_A_() {
      return this.field_178665_b;
   }

   @Override
   public IChatComponent getSpectatorName() {
      return this.field_178666_a < 0 ? new ChatComponentText("Previous Page") : new ChatComponentText("Next Page");
   }

   public SpectatorMenu$MoveMenuObject(int var1, boolean var2) {
      this.field_178666_a = var1;
      this.field_178665_b = var2;
   }

   @Override
   public void func_178661_a(SpectatorMenu var1) {
      SpectatorMenu.access$112(var1, this.field_178666_a);
   }
}
