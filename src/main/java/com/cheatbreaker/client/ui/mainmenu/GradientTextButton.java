package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.util.RenderUtil;

public class GradientTextButton extends AbstractElement {
   public ColorFade recoveredField3458;
   public ColorFade recoveredField3459;
   public int[] recoveredField3460;
   public ColorFade recoveredField3461;
   public String recoveredField3462;

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return !var4 ? false : false;
   }

   public String method_25134() {
      return this.recoveredField3462;
   }

   public void method_25132() {
      this.method_25136(new int[]{-11493284, -11493284, -10176146, -10176146, -11164318, -11164318});
   }

   public GradientTextButton(String var1) {
      this.recoveredField3462 = var1;
      this.recoveredField3458 = new ColorFade(-14277082, -11493284);
      this.recoveredField3461 = new ColorFade(-13487566, -10176146);
      this.recoveredField3459 = new ColorFade(-14013910, -11164318);
   }

   public void method_25133() {
      this.method_25136(new int[]{-11119018, -11493284, -10329502, -10176146, -11579569, -11164318});
   }

   public void method_25138() {
      this.method_25136(new int[]{-14277082, -11493284, -13487566, -10176146, -14013910, -11164318});
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      boolean var4 = var3 && this.a_(var1, var2);
      if (this.recoveredField3460 != null && this.recoveredField3458.method_21210()) {
         this.recoveredField3458.method_25067(this.recoveredField3460[0]);
         this.recoveredField3458.method_25068(this.recoveredField3460[1]);
         this.recoveredField3461.method_25067(this.recoveredField3460[2]);
         this.recoveredField3461.method_25068(this.recoveredField3460[3]);
         this.recoveredField3459.method_25067(this.recoveredField3460[4]);
         this.recoveredField3459.method_25068(this.recoveredField3460[5]);
         this.recoveredField3460 = null;
      }

      RenderUtil.method_22057(
         this.x,
         this.y,
         this.x + this.width,
         this.y + this.height,
         this.recoveredField3458.method_25066(var4).getRGB(),
         this.recoveredField3461.method_25066(var4).getRGB(),
         this.recoveredField3459.method_25066(var4).getRGB()
      );
      float var10002 = this.x + this.width / 2.0F;
      float var10003 = this.y + 2.0F;
      CheatBreaker.getInstance().robotoRegular13px.drawCenteredString(this.recoveredField3462, var10002, var10003, -1);
   }

   public void method_25135(String var1) {
      this.recoveredField3462 = var1;
   }

   public void method_25137(float var1, float var2, boolean var3) {
      this.handleElementDraw(var1, var2, var3);
   }

   public void method_25136(int[] var1) {
      this.recoveredField3460 = var1;
   }
}
