package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.Setting$Type;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.awt.Color;
import net.minecraft.block.BlockLever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.projectile.EntityPotion;
import org.apache.log4j.chainsaw.LoadXMLAction;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass3584 extends AbstractModulesGuiElement {
   public float field_0003;
   public EntityPotion field_0004;
   public float field_0002 = -1.0F;
   public BlockLever field_0005;
   public LoadXMLAction field_0006;
   public Setting field_0000;
   public boolean field_0001 = false;

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      short var4 = 170;
      boolean var5 = var1 > (this.x + 170) * this.scale
         && var1 < (this.x + 170 + var4 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + 10 + this.yOffset) * this.scale;
      if (var3 == 0 && var5) {
         this.field_0001 = true;
      }
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      short var6 = 148;
      float var7 = var6 - 18;
      CheatBreaker.getInstance().field_0068.drawString(this.field_0000.method_08911().toUpperCase(), this.x + 10, this.y + 2, -1895825408);
      if (this.field_0001 && !Mouse.isButtonDown(0)) {
         this.field_0001 = false;
      }

      Gui.drawRect(this.x + 179.5F, this.y + 5.5F, this.x + 180.5F + var7, this.y + 8.5F, -822083584);

      for (int var8 = 0; var8 < var7; var8++) {
         int var9 = Color.HSBtoRGB(var8 / var7, 1.0F, 1.0F);
         Gui.a(this.x + 180 + var8, this.y + 6, this.x + 181 + var8, this.y + 8, var9);
      }

      float var18 = Float.parseFloat("" + this.field_0000.method_08904());
      float var10 = Float.parseFloat("" + this.field_0000.method_08878());
      if (this.field_0001) {
         this.field_0003 = (float)((var18 + (var1 - (this.x + 180) * this.scale) * ((var10 - var18) / (var7 * this.scale))) * 100.0) / 100.0F;
         if (this.field_0000.getType().equals(Setting$Type.field_0002)) {
            this.field_0003 = Math.round(this.field_0003);
         }

         if (this.field_0003 < var18) {
            this.field_0003 = var18;
         } else if (this.field_0003 > var10) {
            this.field_0003 = var10;
         }

         switch (UnidentifiedClass1096.field_0001[this.field_0000.getType().ordinal()]) {
            case 1:
               this.field_0000.setValue(Integer.parseInt((int)this.field_0003 + ""));
               break;
            case 2:
               this.field_0000.setValue(this.field_0003);
               break;
            case 3:
               this.field_0000.setValue(Double.parseDouble(this.field_0003 + ""));
         }
      }

      float var5;
      float var16;
      var5 = (var5 = Float.parseFloat(this.field_0000.getValue() + "")) < this.field_0002 ? this.field_0002 - var5 : (var16 = var5 - this.field_0002);
      float var11 = ((var10 - var18) / 20.0F + var5 * 8.0F) / (Minecraft.debugFPS + 1);
      if (var11 < 1.0E-4) {
         var11 = 1.0E-4F;
      }

      float var4;
      if (this.field_0002 < (var4 = Float.parseFloat(this.field_0000.getValue() + ""))) {
         this.field_0002 = this.field_0002 + var11 <= var4 ? (this.field_0002 += var11) : var4;
      } else if (this.field_0002 > var4) {
         this.field_0002 = this.field_0002 - var11 >= var4 ? (this.field_0002 -= var11) : var4;
      }

      double var12 = 100.0F * ((this.field_0002 - var18) / (var10 - var18));
      GL11.glColor4f(0.25F, 0.45F, 1.0F, 1.0F);
      RenderUtil.method_22052(this.x + 181.25F + var7 * var12 / 100.0, this.y + 7.25F, 4.5);
      float var14 = (Float)this.field_0000.getValue();
      int var15 = var14 == 0.0F ? -1 : Color.HSBtoRGB(var14, 1.0F, 1.0F);
      GL11.glColor4f((var15 >> 16 & 0xFF) / 255.0F, (var15 >> 8 & 0xFF) / 255.0F, (var15 & 0xFF) / 255.0F, 1.0F);
      RenderUtil.method_22052(this.x + 181.25F + var7 * var12 / 100.0, this.y + 7.25F, 2.7F);
   }

   public UnidentifiedClass3584(Setting var1, float var2) {
      super(var2);
      this.field_0000 = var1;
      this.height = 14;
      this.field_0002 = Float.parseFloat("" + var1.getValue());
   }
}
