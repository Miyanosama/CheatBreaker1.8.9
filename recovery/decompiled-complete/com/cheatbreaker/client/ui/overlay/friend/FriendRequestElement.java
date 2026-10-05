package com.cheatbreaker.client.ui.overlay.friend;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.command.PlayerSelector$8;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass1658;

public class FriendRequestElement extends AbstractElement {
   public GuiWinGame field_0001;
   public static byte[] field_0003 = new byte[]{
      107, -20, -16, 107, 16, 12, 30, 82, -34, -44, -106, 14, 91, -126, 45, -85, -63, 42, 106, -17, 19, 94, -92, -48, 91, 77, 116, -15, -116, 20, 36, -123
   };
   public FriendRequest friendRequest;
   public PlayerSelector$8 field_0002;

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else {
         if (this.friendRequest.isFriend()) {
            boolean var5 = var1 > this.x + 24.0F && var1 < this.x + 84.0F && var2 < this.y + this.height && var2 > this.y + 10.0F && var4;
            if (var5) {
               this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
               CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new UnidentifiedClass1658(false, this.friendRequest.getPlayerId()));
               OverlayGui.getInstance().getFriendRequestsElement().getElements().add(this);
            }
         } else {
            boolean var6 = var1 > this.x + 24.0F && var1 < this.x + 52.0F && var2 < this.y + this.height && var2 > this.y + 10.0F;
            boolean var8 = var1 > this.x + 52.0F && var1 < this.x + 84.0F && var2 < this.y + this.height && var2 > this.y + 10.0F;
            if (var6) {
               this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
               CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new UnidentifiedClass1658(true, this.friendRequest.getPlayerId()));
               OverlayGui.getInstance().getFriendRequestsElement().getElements().add(this);
            } else if (var8) {
               this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
               CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new UnidentifiedClass1658(false, this.friendRequest.getPlayerId()));
               OverlayGui.getInstance().getFriendRequestsElement().getElements().add(this);
            }
         }

         return super.handleElementMouseClicked(var1, var2, var3, var4);
      }
   }

   public FriendRequest getFriendRequest() {
      return this.friendRequest;
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      if (var3 && this.a_(var1, var2)) {
         Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13750738);
      }

      GL11.glPushMatrix();
      Gui.drawRect(this.x, this.y - 0.5F, this.x + this.width, this.y, -1357572843);
      Gui.drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + 0.5F, -1357572843);
      Gui.drawRect(this.x + 4.0F, this.y + 3.0F, this.x + 20.0F, this.y + 19.0F, -16747106);
      CheatBreaker.getInstance().field_0036.drawString(this.friendRequest.getUsername(), this.x + 24.0F, this.y + 2.0F, -1);
      if (this.friendRequest.isFriend()) {
         boolean var4 = var1 > this.x + 24.0F && var1 < this.x + 84.0F && var2 < this.y + this.height && var2 > this.y + 10.0F && var3;
         float var10002 = this.x + 24.0F;
         float var10003 = this.y + 11.0F;
         CheatBreaker.getInstance().playRegular14px.drawString("CANCEL", var10002, var10003, var4 ? -52429 : 2147431219);
      } else {
         boolean var6 = var1 > this.x + 24.0F && var1 < this.x + 52.0F && var2 < this.y + this.height && var2 > this.y + 10.0F && var3;
         boolean var5 = var1 > this.x + 52.0F && var1 < this.x + 84.0F && var2 < this.y + this.height && var2 > this.y + 10.0F && var3;
         float var8 = this.x + 24.0F;
         float var10 = this.y + 11.0F;
         CheatBreaker.getInstance().playRegular14px.drawString("ACCEPT", var8, var10, var6 ? -13369549 : 2134114099);
         var8 = this.x + 56.0F;
         var10 = this.y + 11.0F;
         CheatBreaker.getInstance().playRegular14px.drawString("DENY", var8, var10, var5 ? -52429 : 2147431219);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      ResourceLocation var7 = CheatBreaker.getInstance().method_19810(EnumChatFormatting.getTextWithoutFormattingCodes(this.friendRequest.getUsername()));
      RenderUtil.drawIcon(var7, 7.0F, this.x + 5.0F, this.y + 4.0F);
      GL11.glPopMatrix();
   }

   public FriendRequestElement(FriendRequest var1) {
      this.friendRequest = var1;
   }
}
