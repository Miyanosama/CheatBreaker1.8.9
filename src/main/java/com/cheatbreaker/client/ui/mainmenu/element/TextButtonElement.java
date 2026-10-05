package com.cheatbreaker.client.ui.mainmenu.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;

public class TextButtonElement extends AbstractElement {
   public ColorFade recoveredField293;
   public String recoveredField294;

   public TextButtonElement(String var1) {
      this.recoveredField294 = var1;
      this.recoveredField293 = new ColorFade(-1879048193, -1);
   }

   public ColorFade method_26722() {
      return this.recoveredField293;
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      float var10002 = this.x + 6.0F;
      float var10003 = this.y + 6.0F;
      CheatBreaker.getInstance()
         .robotoBold14px
         .drawString(this.recoveredField294, var10002, var10003, this.recoveredField293.method_25066(this.a_(var1, var2) && var3).getRGB());
   }

   public String method_26721() {
      return this.recoveredField294;
   }
}
