package net.minecraft.client.gui.spectator.categories;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TeleportToTeam implements ISpectatorMenuView, ISpectatorMenuObject {
   public List<ISpectatorMenuObject> field_178672_a = Lists.newArrayList();

   @Override
   public IChatComponent getSpectatorName() {
      return new ChatComponentText("Teleport to team member");
   }

   @Override
   public IChatComponent func_178670_b() {
      return new ChatComponentText("Select a team to teleport to");
   }

   @Override
   public void func_178661_a(SpectatorMenu var1) {
      var1.func_178647_a(this);
   }

   @Override
   public boolean func_178662_A_() {
      for (ISpectatorMenuObject var2 : this.field_178672_a) {
         if (var2.func_178662_A_()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public List<ISpectatorMenuObject> func_178669_a() {
      return this.field_178672_a;
   }

   @Override
   public void func_178663_a(float var1, int var2) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
      Gui.drawModalRectWithCustomSizedTexture(0, 0, 16.0F, 0.0F, 16, 16, 256.0F, 256.0F);
   }

   public TeleportToTeam() {
      Minecraft var1 = Minecraft.getMinecraft();

      for (ScorePlayerTeam var3 : var1.theWorld.Z().getTeams()) {
         this.field_178672_a.add(new TeleportToTeam.TeamSelectionObject(var3));
      }
   }

   public class TeamSelectionObject implements ISpectatorMenuObject {
      public List<NetworkPlayerInfo> field_178675_d;
      public ScorePlayerTeam field_178676_b;
      public ResourceLocation field_178677_c;

      @Override
      public void func_178661_a(SpectatorMenu var1) {
         var1.func_178647_a(new TeleportToPlayer(this.field_178675_d));
      }

      @Override
      public boolean func_178662_A_() {
         return !this.field_178675_d.isEmpty();
      }

      @Override
      public void func_178663_a(float var1, int var2) {
         int var3 = -1;
         String var4 = FontRenderer.getFormatFromString(this.field_178676_b.getColorPrefix());
         if (var4.length() >= 2) {
            var3 = Minecraft.getMinecraft().fontRendererObj.getColorCode(var4.charAt(1));
         }

         if (var3 >= 0) {
            float var5 = (var3 >> 16 & 0xFF) / 255.0F;
            float var6 = (var3 >> 8 & 0xFF) / 255.0F;
            float var7 = (var3 & 0xFF) / 255.0F;
            Gui.a(1, 1, 15, 15, MathHelper.func_180183_b(var5 * var1, var6 * var1, var7 * var1) | var2 << 24);
         }

         Minecraft.getMinecraft().getTextureManager().bindTexture(this.field_178677_c);
         GlStateManager.color(var1, var1, var1, var2 / 255.0F);
         Gui.drawScaledCustomSizeModalRect(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
         Gui.drawScaledCustomSizeModalRect(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      }

      @Override
      public IChatComponent getSpectatorName() {
         return new ChatComponentText(this.field_178676_b.getTeamName());
      }

      public TeamSelectionObject(ScorePlayerTeam var2) {
         this.field_178676_b = var2;
         this.field_178675_d = Lists.newArrayList();

         for (String var4 : var2.getMembershipCollection()) {
            NetworkPlayerInfo var5 = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(var4);
            if (var5 != null) {
               this.field_178675_d.add(var5);
            }
         }

         if (!this.field_178675_d.isEmpty()) {
            String var6 = this.field_178675_d.get(new Random().nextInt(this.field_178675_d.size())).getGameProfile().getName();
            this.field_178677_c = AbstractClientPlayer.getLocationSkin(var6);
            AbstractClientPlayer.getDownloadImageSkin(this.field_178677_c, var6);
         } else {
            this.field_178677_c = DefaultPlayerSkin.getDefaultSkinLegacy();
         }
      }
   }
}
