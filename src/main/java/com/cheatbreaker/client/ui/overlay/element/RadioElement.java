package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.dash.DashUtil;
import com.cheatbreaker.client.util.dash.Station;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.overlay.element.RadioVolumeSlider;

public class RadioElement extends DraggableElement {
   public float recoveredField257;
   public MinMaxFade recoveredField258;
   public ResourceLocation recoveredField259;
   public ScrollableElement scrollableContainer;
   public InputFieldElement filter;
   public boolean hovered;
   public RadioVolumeSlider recoveredField260;
   public List<RadioStationElement> recoveredField261;
   public FlatButtonElement pin;
   public ResourceLocation recoveredField262 = new ResourceLocation("client/dash-logo-54.png");

   @Override
   public void handleElementClose() {
      this.filter.handleElementClose();
      this.pin.handleElementClose();
   }

   @Override
   public void handleElementUpdate() {
      this.filter.handleElementUpdate();
      this.pin.handleElementUpdate();
   }

   public void method_28939() {
      this.setElementSize(this.x, this.y, this.width, this.height);
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      this.filter.handleElementMouseClicked(var1, var2, var3, var4);
      if (this.filter.method_06013() && var3 == 1 && this.filter.getText().equals("")) {
         this.method_28939();
      }

      if (!var4) {
         return false;
      } else {
         boolean var5 = this.a_(var1, var2) && var1 > this.x + 34.0F && var1 < this.x + 44.0F && var2 < this.y + this.recoveredField257;
         if (var5) {
            if (!DashUtil.isPlayerNotNull()) {
               CheatBreaker.getInstance().getRadioManager().getCurrentStation().endStream();
            } else {
               DashUtil.end();
            }
         }

         float var6 = this.recoveredField258.method_21232(this.a_(var1, var2) && var4);
         if (this.recoveredField258.method_21212()) {
            this.recoveredField260.handleElementMouseClicked(var1, var2, var3, var4);
            this.scrollableContainer.handleElementMouseClicked(var1, var2, var3, var4);
            this.filter.handleElementMouseClicked(var1, var2, var3, var4);
            this.pin.handleElementMouseClicked(var1, var2, var3, var4);
            boolean var7 = var1 > (int)this.x
               && var1 < (int)(this.x + this.width)
               && var2 > (int)(this.y + this.recoveredField257 + 21.0F)
               && var2 < (int)(this.y + this.recoveredField257 + 21.0F + (this.height - this.recoveredField257 - 21.0F) * var6);
            if (var7) {
               for (RadioStationElement var9 : this.recoveredField261) {
                  if (this.isFilterMatch(var9) && var9.handleElementMouseClicked(var1, var2 - this.scrollableContainer.method_12074(), var3, var4)) {
                     break;
                  }
               }
            }

            if (this.pin.a_(var1, var2)) {
               this.client.getGlobalSettings().recoveredField510.setValue(!(Boolean)this.client.getGlobalSettings().recoveredField510.getValue());
               this.pin.method_23820((Boolean)this.client.getGlobalSettings().recoveredField510.getValue() ? "Unpin" : "Pin");
            }
         }

         if (this.a_(var1, var2)
            && var2 < this.y + this.recoveredField257
            && !var5
            && !this.recoveredField260.a_(var1, var2)
            && !this.scrollableContainer.a_(var1, var2)) {
            this.updateDraggingPosition(var1, var2);
         }

         return super.handleElementMouseClicked(var1, var2, var3, var4);
      }
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
      if (this.recoveredField257 == 0.0F) {
         this.recoveredField257 = var4;
      }

      this.recoveredField261.sort((var0, var1x) -> {
         if (var0.getStation().isFavourite() && !var1x.getStation().isFavourite()) {
            return -1;
         } else {
            return !var0.getStation().isFavourite() && var1x.getStation().isFavourite() ? 1 : 0;
         }
      });
      this.recoveredField260.setElementSize(var1, var2 + this.recoveredField257, var3, 8.0F);
      this.filter.setElementSize(var1, var2 + this.recoveredField257 + 8.0F, var3 - 30.0F, 13.0F);
      this.pin.setElementSize(var1 + var3 - 30.0F, var2 + this.recoveredField257 + 8.0F, 30.0F, 13.0F);
      this.scrollableContainer.setElementSize(var1 + var3 - 5.0F, var2 + this.recoveredField257 + 21.0F, 5.0F, 99.0F);
      int var5 = 0;
      boolean var6 = true;

      for (RadioStationElement var8 : this.recoveredField261) {
         if (this.isFilterMatch(var8)) {
            float var9 = var2 + 21.0F + this.recoveredField257 + var5;
            var8.setElementSize(var1, var9, var3 - 5.0F, 20.0F);
            var5 += 20;
         }
      }

      this.scrollableContainer.setScrollAmount(var5);
   }

   @Override
   public boolean handleMouseClickedInternal(float var1, float var2, int var3) {
      if (!this.filter.a_(var1, var2) && this.filter.method_06013()) {
         this.filter.method_06028(false);
      }

      return false;
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      this.drag(var1, var2);
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.recoveredField257, -14540254);
      Gui.drawRect(this.x, this.y, this.x + this.recoveredField257, this.y + this.recoveredField257, -1);
      Station var4 = CheatBreaker.getInstance().getRadioManager().getCurrentStation();
      if (var4 != null) {
         if (var4.currentResource == null && !var4.getCoverURL().equals("")) {
            if (var4.previousResource != null) {
               this.mc.getTextureManager().deleteTexture(var4.previousResource);
               var4.previousResource = null;
            }

            var4.currentResource = new ResourceLocation("client/songs/" + var4.getTitle());
            ThreadDownloadImageData var5 = new ThreadDownloadImageData(null, var4.getCoverURL(), this.recoveredField262, null);
            Minecraft.getMinecraft().renderEngine.loadTexture(var4.currentResource, var5);
         }

         ResourceLocation var9 = var4.currentResource == null ? this.recoveredField262 : var4.currentResource;
         RenderUtil.drawIcon(var9, this.recoveredField257 / 2.0F, this.x, this.y);
         float var6 = this.x + 50.0F;
         if (this.mc.currentScreen != OverlayGui.getInstance()) {
            var6 = this.x + 34.0F;
         } else {
            boolean var7 = this.a_(var1, var2) && var1 > this.x + 34.0F && var1 < this.x + 44.0F && var2 < this.y + this.recoveredField257;
            if (!DashUtil.isPlayerNotNull()) {
               GL11.glColor4f(1.0F, 1.0F, 1.0F, var7 ? 1.0F : 0.8F);
               RenderUtil.drawIcon(this.recoveredField259, 6.0F, this.x + 34.0F, this.y + 7.5F);
            } else {
               Gui.drawRect(this.x + 36.0F, this.y + 9.0F, this.x + 38.0F, this.y + this.recoveredField257 - 11.0F, var7 ? -1 : -1342177281);
               Gui.drawRect(this.x + 40.0F, this.y + 9.0F, this.x + 42.0F, this.y + this.recoveredField257 - 11.0F, var7 ? -1 : -1342177281);
            }
         }

         String var12 = var4.getTitle();
         if (CheatBreaker.getInstance().recoveredField1548.getStringWidth(var12) > this.width - 52.0F) {
            float var8 = this.y + 4.0F;
            CheatBreaker.getInstance().recoveredField1554.drawString(var12, var6, var8, -1);
         } else {
            float var14 = this.y + 4.0F;
            CheatBreaker.getInstance().recoveredField1548.drawString(var12, var6, var14, -1);
         }

         CheatBreaker.getInstance().recoveredField1554.drawString(var4.method_05593(), var6, this.y + 14.0F, -1342177281);
      }

      float var10 = this.recoveredField258.method_21232(this.a_(var1, var2) && var3);
      if (this.recoveredField258.method_21233()) {
         this.setElementSize(this.x, this.y, this.width, this.recoveredField257 + 120.0F * var10);
         this.hovered = true;
      } else if (!this.recoveredField258.method_21233() && !this.a_(var1, var2)) {
         this.hovered = false;
      }

      if (this.hovered) {
         GL11.glPushMatrix();
         GL11.glEnable(3089);
         OverlayGui var11 = OverlayGui.getInstance();
         RenderUtil.method_22061(
            (int)this.x,
            (int)(this.y + this.recoveredField257),
            (int)(this.x + this.width),
            (int)(this.y + this.recoveredField257 + (this.height - this.recoveredField257) * var10),
            (int)(var11.getResolution().getScaleFactor() * var11.getScaleFactor()),
            (int)var11.getScaledHeight()
         );
         Gui.drawRect(this.x, this.y + this.recoveredField257, this.x + this.width, this.y + this.height, -14540254);
         this.scrollableContainer.drawScrollable(var1, var2, var3);

         for (RadioStationElement var15 : this.recoveredField261) {
            if (this.isFilterMatch(var15)) {
               var15.handleElementDraw(
                  var1,
                  var2 - this.scrollableContainer.method_12074(),
                  var3 && !this.scrollableContainer.isDragClick() && !this.scrollableContainer.a_(var1, var2)
               );
            }
         }

         this.scrollableContainer.handleElementDraw(var1, var2, var3);
         this.recoveredField260.drawElement(var1, var2, var3);
         this.filter.handleElementDraw(var1, var2, var3);
         this.pin.handleElementDraw(var1, var2, var3);
         GL11.glDisable(3089);
         GL11.glPopMatrix();
      }
   }

   @Override
   public void handleElementKeyTyped(char var1, int var2) {
      this.filter.handleElementKeyTyped(var1, var2);
      this.pin.handleElementKeyTyped(var1, var2);
      this.scrollableContainer.handleElementKeyTyped(var1, var2);
      if (this.filter.method_06013()) {
         this.method_28939();
      }
   }

   public boolean isFilterMatch(RadioStationElement var1) {
      return this.filter.getText().equals("")
         || var1.getStation().getName().toLowerCase().startsWith(this.filter.getText().toLowerCase())
         || var1.getStation().getGenre().toLowerCase().startsWith(this.filter.getText().toLowerCase());
   }

   @Override
   public void handleElementMouse() {
      this.scrollableContainer.handleElementMouse();
   }

   public RadioElement() {
      this.recoveredField259 = new ResourceLocation("client/icons/play-24.png");
      this.recoveredField261 = new ArrayList<>();
      this.recoveredField258 = new MinMaxFade(300L);
      this.recoveredField260 = new RadioVolumeSlider(CheatBreaker.getInstance().getGlobalSettings().recoveredField546);
      this.scrollableContainer = new ScrollableElement(this);
      this.filter = new InputFieldElement(this.client.playRegular14px, "Filter", -11842741, -11842741);
      this.pin = new FlatButtonElement((Boolean)this.client.getGlobalSettings().recoveredField510.getValue() ? "Unpin" : "Pin");

      for (Station var2 : CheatBreaker.getInstance().getRadioManager().getStations()) {
         this.recoveredField261.add(new RadioStationElement(this, var2));
      }
   }
}
