package com.cheatbreaker.client.ui.overlay.friend;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.FloatFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.friend.Friend;
import com.cheatbreaker.client.util.friend.FriendsManager;
import com.cheatbreaker.client.websocket.client.WSPacketClientFriendRemove;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class FriendElement extends AbstractElement {
   public Material field_0003;
   public static ResourceLocation removeIcon = new ResourceLocation("client/icons/garbage-26.png");
   public IntegratedServer field_0002;
   public static ResourceLocation cheatBreakerIcon = new ResourceLocation("client/logo_26.png");
   public FloatFade field_0000;
   public Friend friend;
   public CosineFade field_0006;

   public Friend getFriend() {
      return this.friend;
   }

   public FriendElement(Friend var1) {
      this.friend = var1;
      this.field_0006 = new CosineFade(7345629L & -3514398947956718114L);
      this.field_0000 = new FloatFade(-3462317005807267640L & 3462317005355779566L);
      this.field_0006.method_21234();
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      if (var3 && this.a_(var1, var2)) {
         Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13750738);
      }

      GL11.glPushMatrix();
      FriendsManager var5 = CheatBreaker.getInstance().getFriendsManager();
      if (var5.method_26537().containsKey(this.friend.getPlayerId())) {
         List var4 = var5.method_26537().get(this.friend.getPlayerId());
         if (var4 != null && var4.size() > 0) {
            if (!this.field_0006.method_21217()) {
               this.field_0006.method_20200();
            }

            Gui.drawRect(
               this.x,
               this.y,
               this.x + this.width,
               this.y + this.height,
               new Color(0.89F, 0.54F, 0.05F, 0.65F * (0.154F + this.field_0006.method_21227())).getRGB()
            );
            float var10002 = this.x + this.width - 15.0F;
            float var10003 = this.y + 6.0F;
            CheatBreaker.getInstance().field_0039.drawCenteredString("2345345", var10002, var10003, -1);
         } else if (this.field_0006.method_21217() && this.field_0006.method_21210()) {
            this.field_0006.method_21216();
         }
      }

      Gui.drawRect(this.x, this.y - 0.5F, this.x + this.width, this.y, -1357572843);
      Gui.drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + 0.5F, -1357572843);
      Gui.drawRect(
         this.x + 4.0F,
         this.y + 3.0F,
         this.x + 20.0F,
         this.y + 19.0F,
         this.friend.isOnline() ? Friend.getStatusColor(this.friend.getOnlineStatus()) : -13158601
      );
      if (this.friend.getName().startsWith(EnumChatFormatting.RED.toString())) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.drawIcon(cheatBreakerIcon, 6.5F, this.x + 24.0F, this.y + 4.0F);
         CheatBreaker.getInstance().field_0036.drawString(this.friend.getName(), this.x + 40.0F, this.y + 2.0F, -1);
         CheatBreaker.getInstance().field_0063.drawString(this.friend.method_04073(), this.x + 40.0F, this.y + 11.0F, -5460820);
      } else {
         CheatBreaker.getInstance().field_0036.drawString(this.friend.getName(), this.x + 24.0F, this.y + 2.0F, -1);
         CheatBreaker.getInstance().field_0063.drawString(this.friend.method_04073(), this.x + 24.0F, this.y + 11.0F, -5460820);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      ResourceLocation var6 = CheatBreaker.getInstance().method_19810(EnumChatFormatting.getTextWithoutFormattingCodes(this.friend.getName()));
      RenderUtil.drawIcon(var6, 7.0F, this.x + 5.0F, this.y + 4.0F);
      boolean var7 = var3 && this.a_(var1, var2) && var1 > this.x + this.width - 20.0F;
      float var8 = this.field_0000.method_21232(var7);
      float var9 = this.x + this.width - 20.5F * var8;
      if (var3) {
         Gui.drawRect(var9, this.y, this.x + this.width, this.y + this.height, -52429);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
         RenderUtil.method_22064(removeIcon, var9 + 4.0F, this.y + 5.0F, 12.0F, 12.0F);
      }

      GL11.glPopMatrix();
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else {
         boolean var5 = this.a_(var1, var2) && var1 > this.x + this.width - 20.0F && var4;
         if (var5 && this.field_0000.method_21210()) {
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new WSPacketClientFriendRemove(this.friend.getPlayerId()));
            OverlayGui.getInstance().getFriendsListElement().getElements().add(this);
            CheatBreaker.getInstance().getFriendsManager().getFriends().remove(this.friend.getPlayerId());
            return true;
         } else if (!var5 && this.a_(var1, var2)) {
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            OverlayGui.getInstance().method_26645(this.friend);
            CheatBreaker.getInstance().getFriendsManager().readMessages(this.friend.getPlayerId());
            return true;
         } else {
            return super.handleElementMouseClicked(var1, var2, var3, var4);
         }
      }
   }
}
