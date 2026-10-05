package com.cheatbreaker.client.ui.mainmenu.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class IconButtonElement extends AbstractElement {
   public boolean recoveredField2005;
   public float recoveredField2006 = 4.0F;
   public ColorFade recoveredField2007;
   public ResourceLocation recoveredField2008;
   public ColorFade recoveredField2009;
   public ColorFade recoveredField2010;
   public String recoveredField2011;

   public void method_22182(ResourceLocation var1) {
      this.recoveredField2008 = var1;
   }

   public void method_22181(String var1) {
      this.recoveredField2011 = var1;
   }

   public IconButtonElement(float var1, ResourceLocation var2) {
      this.recoveredField2008 = var2;
      this.recoveredField2006 = var1;
      this.recoveredField2009 = new ColorFade(1342177279, -1353670564);
      this.recoveredField2007 = new ColorFade(444958085, 1063565678);
      this.recoveredField2010 = new ColorFade(444958085, 1062577506);
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      boolean var4 = var3 && this.a_(var1, var2);
      RenderUtil.method_22057(
         this.x,
         this.y,
         this.x + this.width,
         this.y + this.height,
         this.recoveredField2009.method_25066(var4).getRGB(),
         this.recoveredField2007.method_25066(var4).getRGB(),
         this.recoveredField2010.method_25066(var4).getRGB()
      );
      if (this.recoveredField2005) {
         float var10002 = this.x + this.width / 2.0F;
         float var10003 = this.y + 2.0F;
         CheatBreaker.getInstance().robotoRegular13px.drawCenteredString(this.recoveredField2011, var10002, var10003, -1);
      } else {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
         RenderUtil.drawIcon(
            this.recoveredField2008,
            this.recoveredField2006,
            this.x + this.width / 2.0F - this.recoveredField2006,
            this.y + this.height / 2.0F - this.recoveredField2006
         );
      }
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return !var4 ? false : false;
   }

   public IconButtonElement(String var1) {
      this.recoveredField2011 = var1;
      this.recoveredField2005 = true;
      this.recoveredField2009 = new ColorFade(1342177279, -1353670564);
      this.recoveredField2007 = new ColorFade(444958085, 1063565678);
      this.recoveredField2010 = new ColorFade(444958085, 1062577506);
   }

   public IconButtonElement(ResourceLocation var1) {
      this.recoveredField2008 = var1;
      this.recoveredField2006 = 4.0F;
      this.recoveredField2009 = new ColorFade(1342177279, -1353670564);
      this.recoveredField2007 = new ColorFade(444958085, 1063565678);
      this.recoveredField2010 = new ColorFade(444958085, 1062577506);
   }

   public float method_22180() {
      return 22 + CheatBreaker.getInstance().robotoRegular13px.getStringWidth(this.recoveredField2011) + 6;
   }
}
