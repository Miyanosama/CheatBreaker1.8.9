package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.dash.DashUtil;
import com.cheatbreaker.client.util.dash.Station;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RadioStationElement extends AbstractElement {
   public ResourceLocation starIcon = new ResourceLocation("client/icons/star-21.png");
   public RadioElement parent;
   public ResourceLocation startFilledIcon = new ResourceLocation("client/icons/star-filled-21.png");
   public Station station;

   public boolean isMouseInsideElement(float var1, float var2) {
      return this.a_(var1, var2) && var1 < this.x + 22.0F;
   }

   public RadioStationElement(RadioElement var1, Station var2) {
      this.parent = var1;
      this.station = var2;
   }

   public Station getStation() {
      return this.station;
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      if (this.isMouseInsideElement(var1, var2) && var3) {
         Gui.drawRect(this.x, this.y, this.x + 22.0F, this.y + this.height, -13158601);
      } else if (this.a_(var1, var2) && var3) {
         Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13158601);
      }

      boolean var4 = this.station.isFavourite();
      if (var4) {
         GL11.glColor4f(0.95F, 0.72F, 0.15F, 1.0F);
      } else {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }

      boolean var5 = CheatBreaker.getInstance().getRadioManager().getCurrentStation() == this.station;
      RenderUtil.drawIcon(var4 ? this.startFilledIcon : this.starIcon, 5.0F, this.x + 6.0F, this.y + 5.0F);
      CheatBreaker.getInstance().playRegular14px.drawString(this.station.getName(), this.x + 24.0F, this.y + 1.5F, var5 ? -13369549 : -1);
      CheatBreaker.getInstance().playRegular14px.drawString(this.station.getGenre(), this.x + 24.0F, this.y + 9.5F, -1342177281);
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else if (this.isMouseInsideElement(var1, var2) && var4) {
         this.station.setFavourite(!this.station.isFavourite());
         this.parent.method_28939();
         return true;
      } else {
         if (this.a_(var1, var2) && var4) {
            if (DashUtil.isPlayerNotNull()) {
               DashUtil.end();
            }

            this.station.play = true;
            CheatBreaker.getInstance().getRadioManager().method_05562().method_19837(this.station);
            CheatBreaker.getInstance().getRadioManager().setCurrentStation(this.station);
         }

         return false;
      }
   }
}
