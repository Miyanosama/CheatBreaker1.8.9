package net.minecraft.client.gui.spectator.categories;

import com.google.common.collect.Lists;
import io.netty.handler.codec.rtsp.RtspRequestDecoder;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.ModelAdapterEnderman;
import net.optifine.shaders.SVertexFormat;
import net.optifine.util.ChunkUtils;
import org.java_websocket.framing.BinaryFrame;

public class TeleportToTeam$TeamSelectionObject implements ISpectatorMenuObject {
   public EntitySheep field_0008;
   public ItemCameraTransforms field_0004;
   public List<NetworkPlayerInfo> field_178675_d;
   public RtspRequestDecoder field_0001;
   public ScorePlayerTeam field_178676_b;
   public BinaryFrame field_0009;
   public SVertexFormat field_0006;
   public ChunkUtils field_0003;
   public ResourceLocation field_178677_c;
   public ModelAdapterEnderman field_0000;

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

   public TeleportToTeam$TeamSelectionObject(TeleportToTeam var1, ScorePlayerTeam var2) {
      this.field_178678_a = var1;
      super();
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
