package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import net.minecraft.client.gui.Gui;

public class HoverBackgroundElement extends AbstractElement {
   public boolean recoveredField3349;
   public ColorFade recoveredField3350;
   public ColorFade recoveredField3351 = new ColorFade(520093696, 1056964608);
   public MinMaxFade recoveredField3352;

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return !var4 ? false : false;
   }

   public HoverBackgroundElement() {
      this.recoveredField3350 = new ColorFade(0, -1073741825);
      this.recoveredField3352 = new MinMaxFade(750L);
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.recoveredField3351.method_25066(this.a_(var1, var2) && var3).getRGB());
   }
}
