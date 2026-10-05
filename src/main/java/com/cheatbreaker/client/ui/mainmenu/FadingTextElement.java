package com.cheatbreaker.client.ui.mainmenu;

import org.davidmoten.text.utils.WordWrap;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;

public class FadingTextElement extends AbstractElement {
   public String recoveredField203;
   public boolean recoveredField204 = false;
   public ColorFade recoveredField205;
   public int recoveredField206;

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      if (this.recoveredField204) {
         String var4 = WordWrap.method_05899(this.recoveredField203).method_29697(50).method_29703(false).method_29693();
         String[] var5 = var4.split("\n");
         int var6 = 0;

         for (String var10 : var5) {
            CheatBreaker.getInstance()
               .playRegular14px
               .drawStringWithShadow(
                  var10,
                  this.x,
                  this.y
                     - CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F
                     + var6 * CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F,
                  this.recoveredField205.method_25066(this.a_(var1, var2) && var3).getRGB()
               );
            var6++;
         }
      } else {
         double var10002 = this.x;
         double var10003 = this.y;
         CheatBreaker.getInstance()
            .recoveredField1557
            .drawStringWithShadow(this.recoveredField203, var10002, var10003, this.recoveredField205.method_25066(this.a_(var1, var2) && var3).getRGB());
      }
   }

   public FadingTextElement(String var1, int var2) {
      this(var1);
      this.recoveredField204 = true;
      this.recoveredField206 = var2;
   }

   public FadingTextElement(String var1) {
      this.recoveredField203 = var1;
      this.recoveredField205 = new ColorFade(-1879048193, -806424850);
   }
}
