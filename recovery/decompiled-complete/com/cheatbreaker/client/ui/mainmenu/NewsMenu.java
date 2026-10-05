package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker08;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0882;
import recovered.unidentified.UnidentifiedEnum3640;

public class NewsMenu extends MainMenuBase {
   public UnidentifiedEnum3640 field_0004;
   public GradientTextButton field_0003;
   public String field_0002;
   public HandshakeImpl1Server field_0001;
   public String field_0000;
   public RenderItem field_0008;
   public WebSocketServerHandshaker08 field_0006;
   public String field_0007;
   public ScrollableElement field_0005;

   @Override
   public void initGui() {
      super.initGui();
      float var1 = Math.min(240.0F, this.getScaledWidth() - 10.0F);
      float var2 = this.getScaledWidth() / 2.0F + var1 / 2.0F;
      float var3 = 40.0F;
      float var4 = this.getScaledHeight() - 40.0F;
      this.field_0005.setElementSize(var2 - 8.0F, var3 + 18.0F, 4.0F, var4 - var3 - 28.0F);
   }

   public NewsMenu(String var1, String var2, String var3) {
      this.field_0007 = var1;
      this.field_0000 = var2;
      this.field_0002 = var3;
      this.field_0003 = new GradientTextButton("BACK");
      this.field_0005 = new ScrollableElement(null);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      super.onMouseClicked(var1, var2, var3);
      if (this.field_0003.a_(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(CheatBreaker.getInstance().field_0020);
      } else if (this.field_0005.a_(var1, var2)) {
         this.field_0005.handleElementMouseClicked(var1, var2, var3, true);
      }
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.field_0005.handleElementMouse();
   }

   @Override
   public void drawMenu(float var1, float var2) {
      super.drawMenu(var1, var2);
      float var3 = Math.min(240.0F, this.getScaledWidth() - 10.0F);
      float var4 = this.getScaledWidth() / 2.0F - var3 / 2.0F;
      float var5 = this.getScaledWidth() / 2.0F + var3 / 2.0F;
      float var6 = 40.0F;
      float var7 = this.getScaledHeight() - 40.0F;
      Gui.drawRect(var4, var6, var5, var7, 788529152);
      Gui.drawRect(var4 + 8.0F, var6 + 22.0F, var5 - 8.0F, var6 + 22.5F, 452984831);
      CheatBreaker.getInstance().field_0036.drawString(this.field_0007, var4 + 8.0F, var6 + 5.0F, -1);
      CheatBreaker.getInstance()
         .playRegular14px
         .drawString(EnumChatFormatting.ITALIC + "Posted by " + EnumChatFormatting.BOLD + this.field_0000, var4 + 8.0F, var6 + 13.0F, -1);
      String var8 = UnidentifiedClass0882.method_05899(this.field_0002).method_29697(60).method_29703(false).method_29693();
      String[] var9 = var8.split("\n");
      int var10 = 0;
      this.field_0003.setElementSize(this.getScaledWidth() / 2.0F - 30.0F, this.getScaledHeight() - 35.0F, 60.0F, 12.0F);
      this.field_0003.drawElement(var1, var2, true);
      this.field_0005.drawScrollable(var1, var2, true);
      GL11.glPushMatrix();
      GL11.glEnable(3089);
      RenderUtil.method_22061(
         (int)var4, (int)var6 + 18, (int)var5, (int)var7 - 8, (int)(this.getResolution().getScaleFactor() * this.getScaleFactor()), (int)this.getScaledHeight()
      );

      for (String var14 : var9) {
         CheatBreaker.getInstance()
            .playRegular14px
            .drawString(var14, var4 + 8.0F, var6 + 24.0F + var10 * (CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F), -1);
         var10++;
      }

      this.field_0005.setScrollAmount(var10 * (CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F) + 2.0F);
      GL11.glDisable(3089);
      GL11.glPopMatrix();
      this.field_0005.drawElement(var1, var2, true);
   }
}
