package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.friend.Friend;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.websocket.shared.WSPacketMessage;

public class PrivateMessageElement extends DraggableElement {
   public Friend recoveredField2394;
   public InputFieldElement recoveredField2395;
   public FlatButtonElement recoveredField2396;
   public ScrollableElement recoveredField2397;
   public ScrollableElement recoveredField2398;
   public FlatButtonElement recoveredField2399;
   public int recoveredField2400 = 22;

   public void method_01185(Friend var1) {
      this.recoveredField2394 = var1;
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      this.recoveredField2395.handleElementMouseClicked(var1, var2, var3, var4);
      if (!var4) {
         return false;
      } else {
         if (!this.recoveredField2395.getText().equals("") && this.recoveredField2396.a_(var1, var2)) {
            this.method_01182();
         }

         this.recoveredField2396.handleElementMouseClicked(var1, var2, var3, var4);
         this.recoveredField2397.handleElementMouseClicked(var1, var2, var3, var4);
         if (this.recoveredField2399.a_(var1, var2)) {
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            OverlayGui.getInstance().removeElements(this);
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
      this.recoveredField2395.setElementSize(var1 + 26.0F, var2 + var4 - 15.0F, var3 - 62.0F, 13.0F);
      this.recoveredField2396.setElementSize(var1 + var3 - 37.0F, var2 + var4 - 15.0F, 35.0F, 13.0F);
      this.recoveredField2397.setElementSize(var1 + var3 - 6.0F, var2 + 22.0F, 4.0F, var4 - 39.0F);
      this.recoveredField2398.setElementSize(var1 + 2.0F, var2 + 2.0F, 0.0F, var4 - 4.0F);
      this.recoveredField2399.setElementSize(var1 + var3 - 12.0F, var2 + 2.0F, 10.0F, 16.0F);
   }

   @Override
   public boolean handleElementMouseRelease(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else {
         this.recoveredField2395.handleElementMouseRelease(var1, var2, var3, var4);
         this.recoveredField2396.handleElementMouseRelease(var1, var2, var3, var4);
         this.recoveredField2397.handleElementMouseRelease(var1, var2, var3, var4);
         this.recoveredField2399.handleElementMouseRelease(var1, var2, var3, var4);
         return false;
      }
   }

   public InputFieldElement method_01183() {
      return this.recoveredField2395;
   }

   public PrivateMessageElement(Friend var1) {
      this.recoveredField2394 = var1;
      this.recoveredField2395 = new InputFieldElement(CheatBreaker.getInstance().playRegular14px, "Message", 805306367, 1879048191);
      this.recoveredField2395.trimToLength(256);
      this.recoveredField2396 = new FlatButtonElement("SEND");
      this.recoveredField2397 = new ScrollableElement(this);
      this.recoveredField2398 = new ScrollableElement(this);
      this.recoveredField2399 = new FlatButtonElement("X");
   }

   @Override
   public void handleElementClose() {
      this.recoveredField2395.handleElementClose();
      this.recoveredField2396.handleElementClose();
      this.recoveredField2397.handleElementClose();
      this.recoveredField2399.handleElementClose();
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      this.drag(var1, var2);
      Gui.drawBoxWithOutLine(this.x, this.y, this.x + 23.0F, this.y + this.height, 0.5F, -16777216, -14869219);
      Gui.drawBoxWithOutLine(this.x + 23.0F, this.y, this.x + this.width, this.y + this.height, 0.5F, -16777216, -15395563);
      GL11.glPushMatrix();
      Gui.drawRect(this.x + 25.0F, this.y - 0.5F, this.x + this.width, this.y, -1357572843);
      Gui.drawRect(this.x + 25.0F, this.y + this.height, this.x + this.width, this.y + this.height + 0.5F, -1357572843);
      Gui.drawRect(
         this.x + 27.0F,
         this.y + 3.0F,
         this.x + 43.0F,
         this.y + 19.0F,
         this.recoveredField2394.isOnline() ? Friend.getStatusColor(this.recoveredField2394.getOnlineStatus()) : -13158601
      );
      CheatBreaker.getInstance().recoveredField1548.drawString(this.recoveredField2394.getName(), this.x + 52.0F, this.y + 2.0F, -1);
      CheatBreaker.getInstance().recoveredField1554.drawString(this.recoveredField2394.method_04073(), this.x + 52.0F, this.y + 11.0F, -5460820);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      ResourceLocation var5 = CheatBreaker.getInstance().method_19810(EnumChatFormatting.getTextWithoutFormattingCodes(this.recoveredField2394.getName()));
      RenderUtil.drawIcon(var5, 7.0F, this.x + 28.0F, this.y + 4.0F);
      Gui.drawRect(this.x + 27.0F, this.y + 22.0F, this.x + this.width - 2.0F, this.y + this.height - 17.0F, -1356783327);
      this.recoveredField2398.drawScrollable(var1, var2, var3);
      GL11.glPushMatrix();
      GL11.glEnable(3089);
      OverlayGui var6 = OverlayGui.getInstance();
      RenderUtil.method_22061(
         0,
         (int)(this.y + 2.0F),
         (int)var6.getScaledWidth(),
         (int)(this.y + this.height - 2.0F),
         (int)(var6.getResolution().getScaleFactor() * var6.getScaleFactor()),
         (int)var6.getScaledHeight()
      );
      byte var7 = 18;
      int var8 = 0;

      for (Friend var10 : this.client.getFriendsManager().getFriends().values()) {
         if (var10 == this.recoveredField2394 || this.client.getFriendsManager().method_26532().containsKey(var10.getPlayerId()) || var10.isOnline()) {
            float var11 = this.y + 3.0F + var8;
            boolean var12 = var1 > this.x
               && var1 < this.x + 25.0F
               && var2 > var11 - this.recoveredField2398.method_12074()
               && var2 < var11 + 16.0F - this.recoveredField2398.method_12074()
               && var2 > this.y
               && var2 < this.y + this.height;
            Gui.drawRect(this.x + 3.0F, var11, this.x + 19.0F, var11 + 16.0F, var10.isOnline() ? Friend.getStatusColor(var10.getOnlineStatus()) : -13158601);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var12 ? 1.0F : 0.85F);
            ResourceLocation var13 = CheatBreaker.getInstance().method_19810(EnumChatFormatting.getTextWithoutFormattingCodes(var10.getName()));
            RenderUtil.drawIcon(var13, 7.0F, this.x + 4.0F, this.y + 4.0F + var8);
            if (var12) {
               float var14 = this.client.robotoRegular13px.getStringWidth(EnumChatFormatting.getTextWithoutFormattingCodes(var10.getName()));
               RenderUtil.method_22054(this.x - 10.0F - var14, var11 + 2.0F, this.x - 2.0F, var11 + 14.0F, 6.0, -1895825408);
               this.client.robotoRegular13px.drawString(var10.getName(), this.x - 6.0F - var14, var11 + 4.0F, -1);
               if (Mouse.isButtonDown(0) && this.recoveredField2394 != var10) {
                  this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
                  this.recoveredField2394 = var10;
               }
            }

            var8 += 18;
         }
      }

      this.recoveredField2398.setScrollAmount(var8);
      GL11.glDisable(3089);
      GL11.glPopMatrix();
      this.recoveredField2398.handleElementDraw(var1, var2, var3);
      this.recoveredField2397.handleScrollableMouseClicked(var1, var2, var3);

      try {
         if (CheatBreaker.getInstance().getFriendsManager().method_26532().containsKey(this.recoveredField2394.getPlayerId())) {
            GL11.glPushMatrix();
            GL11.glEnable(3089);
            RenderUtil.method_22061(
               (int)(this.x + 2.0F),
               (int)(this.y + 22.0F),
               (int)(this.x + this.width - 2.0F),
               (int)(this.y + this.height - 17.0F),
               (int)(var6.getResolution().getScaleFactor() * var6.getScaleFactor()),
               (int)var6.getScaledHeight()
            );
            List var19 = CheatBreaker.getInstance().getFriendsManager().method_26532().get(this.recoveredField2394.getPlayerId());
            int var20 = 0;

            for (int var21 = var19.size() - 1; var21 >= 0; var21--) {
               String var22 = (String)var19.get(var21);
               String[] var4 = CheatBreaker.getInstance().playRegular14px.method_03181(var22, this.width - 25.0F).split("\n");
               var20 += var4.length * 10;
               int var23 = 0;

               for (String var17 : var4) {
                  float var10002 = this.x + 31.0F;
                  CheatBreaker.getInstance().playRegular14px.drawString(var17, var10002, this.y + this.height - 19.0F - var20 + var23 * 10, -1);
                  var23++;
               }
            }

            this.recoveredField2397.setScrollAmount(var20 + 4);
            GL11.glDisable(3089);
            GL11.glPopMatrix();
         }
      } catch (Exception var18) {
         var18.printStackTrace();
      }

      this.recoveredField2397.method_12071(var1, var2, var3);
      GL11.glPopMatrix();
      this.recoveredField2395.drawElement(var1, var2, var3);
      this.recoveredField2396.drawElement(var1, var2, var3);
      this.recoveredField2399.drawElement(var1, var2, var3);
   }

   public Friend method_01181() {
      return this.recoveredField2394;
   }

   public void method_01182() {
      String var1 = this.recoveredField2395.getText();
      CheatBreaker.getInstance().getFriendsManager().method_26536(this.recoveredField2394.getPlayerId(), var1);
      CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new WSPacketMessage(this.recoveredField2394.getPlayerId(), var1));
      this.recoveredField2395.setText("");
      this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
   }

   @Override
   public boolean handleMouseClickedInternal(float var1, float var2, int var3) {
      if (!this.recoveredField2395.a_(var1, var2) && this.recoveredField2395.method_06013()) {
         this.recoveredField2395.method_06028(false);
      }

      return false;
   }

   @Override
   public void handleElementKeyTyped(char var1, int var2) {
      if (this.recoveredField2395.method_06013() && !this.recoveredField2395.getText().equals("") && var2 == 28) {
         this.method_01182();
      }

      this.recoveredField2395.handleElementKeyTyped(var1, var2);
      this.recoveredField2396.handleElementKeyTyped(var1, var2);
      this.recoveredField2397.handleElementKeyTyped(var1, var2);
      this.recoveredField2399.handleElementKeyTyped(var1, var2);
   }

   public static String method_01186(byte[] var0) throws java.security.GeneralSecurityException{
      try {
         SecretKeySpec var1 = new SecretKeySpec(CheatBreaker.recoveredField1561, "AES");
         Cipher var2 = Cipher.getInstance("AES");
         var2.init(2, var1);
         return new String(var2.doFinal(var0));
      } catch (Throwable var3) {
         throw var3;
      }
   }

   @Override
   public void handleElementUpdate() {
      this.recoveredField2395.handleElementUpdate();
      this.recoveredField2396.handleElementUpdate();
      this.recoveredField2397.handleElementUpdate();
      this.recoveredField2399.handleElementUpdate();
   }

   @Override
   public void handleElementMouse() {
      this.recoveredField2397.handleElementMouse();
      this.recoveredField2398.handleElementMouse();
   }
}
