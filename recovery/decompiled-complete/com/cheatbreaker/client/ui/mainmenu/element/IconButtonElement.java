package com.cheatbreaker.client.ui.mainmenu.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase$TempCategory;
import net.optifine.shaders.gui.GuiButtonEnumShaderOption;
import org.lwjgl.opengl.GL11;
import org.slf4j.helpers.BasicMDCAdapter;

public class IconButtonElement extends AbstractElement {
   public boolean field_0005;
   public EntityRenderer field_0008;
   public GuiButtonEnumShaderOption field_0004;
   public float field_0007 = 4.0F;
   public ColorFade field_0001;
   public ResourceLocation field_0002;
   public BasicMDCAdapter field_0009;
   public ColorFade field_0006;
   public ColorFade field_0003;
   public String field_0010;
   public BiomeGenBase$TempCategory field_0000;

   public void method_22182(ResourceLocation var1) {
      this.field_0002 = var1;
   }

   public void method_22181(String var1) {
      this.field_0010 = var1;
   }

   public IconButtonElement(float var1, ResourceLocation var2) {
      this.field_0002 = var2;
      this.field_0007 = var1;
      this.field_0006 = new ColorFade(1342177279, -1353670564);
      this.field_0001 = new ColorFade(444958085, 1063565678);
      this.field_0003 = new ColorFade(444958085, 1062577506);
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      boolean var4 = var3 && this.a_(var1, var2);
      RenderUtil.method_22057(
         this.x,
         this.y,
         this.x + this.width,
         this.y + this.height,
         this.field_0006.method_25066(var4).getRGB(),
         this.field_0001.method_25066(var4).getRGB(),
         this.field_0003.method_25066(var4).getRGB()
      );
      if (this.field_0005) {
         float var10002 = this.x + this.width / 2.0F;
         float var10003 = this.y + 2.0F;
         CheatBreaker.getInstance().robotoRegular13px.drawCenteredString(this.field_0010, var10002, var10003, -1);
      } else {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
         RenderUtil.drawIcon(this.field_0002, this.field_0007, this.x + this.width / 2.0F - this.field_0007, this.y + this.height / 2.0F - this.field_0007);
      }
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return !var4 ? false : false;
   }

   public IconButtonElement(String var1) {
      this.field_0010 = var1;
      this.field_0005 = true;
      this.field_0006 = new ColorFade(1342177279, -1353670564);
      this.field_0001 = new ColorFade(444958085, 1063565678);
      this.field_0003 = new ColorFade(444958085, 1062577506);
   }

   public IconButtonElement(ResourceLocation var1) {
      this.field_0002 = var1;
      this.field_0007 = 4.0F;
      this.field_0006 = new ColorFade(1342177279, -1353670564);
      this.field_0001 = new ColorFade(444958085, 1063565678);
      this.field_0003 = new ColorFade(444958085, 1062577506);
   }

   public float method_22180() {
      return 22 + CheatBreaker.getInstance().robotoRegular13px.getStringWidth(this.field_0010) + 6;
   }
}
