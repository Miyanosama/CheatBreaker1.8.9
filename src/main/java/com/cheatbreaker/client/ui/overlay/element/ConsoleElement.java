package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.websocket.shared.WSPacketConsole;
import com.cheatbreaker.client.util.ConsoleChatPrinter;

public class ConsoleElement extends DraggableElement {
   public InputFieldElement recoveredField3937;
   public FlatButtonElement recoveredField3938;
   public int recoveredField3939;
   public FlatButtonElement recoveredField3940;
   public ScrollableElement recoveredField3941;
   public List<String> recoveredField3942 = new ArrayList<>();

   @Override
   public boolean handleMouseClickedInternal(float var1, float var2, int var3) {
      if (!this.recoveredField3937.a_(var1, var2) && this.recoveredField3937.method_06013()) {
         this.recoveredField3937.method_06028(false);
      }

      return false;
   }

   @Override
   public boolean handleElementMouseRelease(float var1, float var2, int var3, boolean var4) {
      this.recoveredField3937.handleElementMouseRelease(var1, var2, var3, var4);
      this.recoveredField3938.handleElementMouseRelease(var1, var2, var3, var4);
      this.recoveredField3941.handleElementMouseRelease(var1, var2, var3, var4);
      this.recoveredField3940.handleElementMouseRelease(var1, var2, var3, var4);
      return false;
   }

   @Override
   public void handleElementKeyTyped(char var1, int var2) {
      if (this.recoveredField3937.method_06013() && !this.recoveredField3937.getText().equals("") && var2 == 28) {
         this.method_20120();
      }

      try {
         if (this.recoveredField3937.method_06013() && var2 == 208 && this.recoveredField3942.size() != 0) {
            if (this.recoveredField3939 != 0) {
               this.recoveredField3939--;
            }

            this.recoveredField3937.setText(this.recoveredField3942.get(this.recoveredField3939));
         } else if (this.recoveredField3937.method_06013() && var2 == 200 && this.recoveredField3942.size() != 0) {
            if (this.recoveredField3939 != this.recoveredField3942.size() - 1) {
               this.recoveredField3939++;
            }

            this.recoveredField3937.setText(this.recoveredField3942.get(this.recoveredField3939));
         }
      } catch (IndexOutOfBoundsException var4) {
         this.recoveredField3937.setText("");
         this.recoveredField3939 = 0;
      }

      this.recoveredField3937.handleElementKeyTyped(var1, var2);
      this.recoveredField3938.handleElementKeyTyped(var1, var2);
      this.recoveredField3941.handleElementKeyTyped(var1, var2);
      this.recoveredField3940.handleElementKeyTyped(var1, var2);
   }

   public void method_20120() {
      String var1 = this.recoveredField3937.getText();
      if (var1.equals("clear") || var1.equals("cls")) {
         CheatBreaker.getInstance().method_19794().clear();
         CheatBreaker.getInstance().getGlobalSettings().recoveredField485 = false;
      } else if (var1.equalsIgnoreCase("wsReconnect") && CheatBreaker.getInstance().getAssetsWebSocket().isClosed()) {
         try {
            CheatBreaker.getInstance().method_19761();
         } catch (URISyntaxException var3) {
            CheatBreaker.getInstance().method_19794().add("Invalid URL: " + var3.getInput());
            var3.printStackTrace();
         }
      } else if (var1.equalsIgnoreCase("banwave start")) {
         String[] var2 = new String[]{"Tellinq", "Moose1301", "Noxiuam", "98ping", "Serversided", "dollarsignjay", "FreddieJLH", "Decencies"};
         new Thread(() -> {
            for (int var1x = 0; var1x < var2.length; var1x++) {
               try {
                  ConsoleChatPrinter.method_22169(var2[var1x] + " has been CheatBreaker Banned (#000" + var1x + ")");
                  Thread.sleep(500L);
               } catch (InterruptedException var3x) {
                  var3x.printStackTrace();
               }
            }
         }).start();
      } else {
         CheatBreaker.getInstance().method_19794().add(EnumChatFormatting.GRAY + "> " + var1);
         CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new WSPacketConsole(var1));
         this.recoveredField3942.add(var1);
         this.recoveredField3939 = this.recoveredField3942.size();
      }

      this.recoveredField3937.setText("");
      this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
   }

   @Override
   public void handleElementClose() {
      this.recoveredField3937.handleElementClose();
      this.recoveredField3938.handleElementClose();
      this.recoveredField3941.handleElementClose();
      this.recoveredField3940.handleElementClose();
   }

   public ConsoleElement() {
      this.recoveredField3939 = 0;
      this.recoveredField3937 = new InputFieldElement(CheatBreaker.getInstance().playRegular14px, "", 805306367, 1879048191);
      this.recoveredField3937.trimToLength(256);
      this.recoveredField3938 = new FlatButtonElement("SEND");
      this.recoveredField3941 = new ScrollableElement(this);
      this.recoveredField3940 = new FlatButtonElement("X");
   }

   @Override
   public void handleElementMouse() {
      this.recoveredField3941.handleElementMouse();
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      this.recoveredField3937.handleElementMouseClicked(var1, var2, var3, var4);
      this.recoveredField3941.handleElementMouseClicked(var1, var2, var3, var4);
      if (!var4) {
         return false;
      } else {
         if (!this.recoveredField3937.getText().equals("") && this.recoveredField3938.a_(var1, var2)) {
            this.method_20120();
         }

         this.recoveredField3938.handleElementMouseClicked(var1, var2, var3, var4);
         if (this.a_(var1, var2) && var2 < this.y + 12.0F) {
            this.updateDraggingPosition(var1, var2);
         }

         if (this.recoveredField3940.a_(var1, var2)) {
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            OverlayGui.getInstance().removeElements(this);
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      this.drag(var1, var2);
      Gui.drawBoxWithOutLine(this.x, this.y, this.x + this.width, this.y + this.height, 0.5F, -16777216, -15395563);
      GL11.glPushMatrix();
      Gui.drawRect(this.x, this.y - 0.5F, this.x + this.width, this.y, -1357572843);
      Gui.drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + 0.5F, -1357572843);
      float var10002 = this.x + 4.0F;
      float var10003 = this.y + 3.0F;
      CheatBreaker.getInstance().playRegular14px.drawString("Console", var10002, var10003, -1);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.drawRect(this.x + 2.0F, this.y + 12.0F + 3.0F, this.x + this.width - 2.0F, this.y + this.height - 17.0F, -1356783327);
      this.recoveredField3941.handleScrollableMouseClicked(var1, var2, var3);

      try {
         if (CheatBreaker.getInstance().method_19793()) {
            GL11.glPushMatrix();
            GL11.glEnable(3089);
            OverlayGui var4 = OverlayGui.getInstance();
            RenderUtil.method_22061(
               (int)(this.x + 2.0F),
               (int)(this.y + 12.0F + 3.0F),
               (int)(this.x + this.width - 2.0F),
               (int)(this.y + this.height - 17.0F),
               (int)(var4.getResolution().getScaleFactor() * var4.getScaleFactor()),
               (int)var4.getScaledHeight()
            );
            List var5 = CheatBreaker.getInstance().method_19794();
            int var6 = 0;

            for (int var7 = var5.size() - 1; var7 >= 0; var7--) {
               String var8 = (String)var5.get(var7);
               String[] var9 = CheatBreaker.getInstance().playRegular14px.method_03181(var8, this.width - 10.0F).split("\n");
               var6 += var9.length * 10;
               int var10 = 0;

               for (String var14 : var9) {
                  var10002 = this.x + 6.0F;
                  CheatBreaker.getInstance().playRegular14px.drawString(var14, var10002, this.y + this.height - 19.0F - var6 + var10 * 10, -1);
                  var10++;
               }
            }

            this.recoveredField3941.setScrollAmount(var6 + 4);
            GL11.glDisable(3089);
            GL11.glPopMatrix();
         }
      } catch (Exception var15) {
         var15.printStackTrace();
      }

      this.recoveredField3941.drawElement(var1, var2, var3);
      GL11.glPopMatrix();
      this.recoveredField3937.drawElement(var1, var2, var3);
      this.recoveredField3938.drawElement(var1, var2, var3);
      this.recoveredField3940.drawElement(var1, var2, var3);
   }

   @Override
   public void handleElementUpdate() {
      this.recoveredField3937.handleElementUpdate();
      this.recoveredField3938.handleElementUpdate();
      this.recoveredField3941.handleElementUpdate();
      this.recoveredField3940.handleElementUpdate();
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
      this.recoveredField3937.setElementSize(var1 + 2.0F, var2 + var4 - 15.0F, var3 - 40.0F, 13.0F);
      this.recoveredField3938.setElementSize(var1 + var3 - 37.0F, var2 + var4 - 15.0F, 35.0F, 13.0F);
      this.recoveredField3941.setElementSize(var1 + var3 - 6.0F, var2 + 12.0F + 3.0F, 4.0F, var4 - 32.0F);
      this.recoveredField3940.setElementSize(var1 + var3 - 12.0F, var2 + 2.0F, 10.0F, 10.0F);
   }
}
