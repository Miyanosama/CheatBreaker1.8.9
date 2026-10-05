package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.Setting$Type;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.channel.socket.oio.DefaultOioSocketChannelConfig;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass1388 extends AbstractModulesGuiElement {
   public DefaultOioSocketChannelConfig field_0001;
   public float field_0002 = -1.0F;
   public boolean field_0000 = false;
   public float field_0003;
   public Setting field_0004;

   public UnidentifiedClass1388(Setting var1, float var2) {
      super(var2);
      this.field_0004 = var1;
      this.height = 14;
      this.field_0002 = Float.parseFloat("" + var1.getValue());
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      short var6 = 148;
      CheatBreaker.getInstance().field_0068.drawString(this.field_0004.method_08911().toUpperCase(), this.x + 10, this.y + 2, -1895825408);
      if (this.field_0000 && !Mouse.isButtonDown(0)) {
         this.field_0000 = false;
      }

      String var7 = this.field_0004.getValue().toString();
      CheatBreaker.getInstance()
         .field_0068
         .drawString(var7, (float)(this.x + 169) - CheatBreaker.getInstance().field_0068.getStringWidth(var7), this.y + 2, -1895825408);
      boolean var8 = var1 > (this.x + 172) * this.scale
         && var1 < (this.x + 172 + var6 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + 10 + this.yOffset) * this.scale;
      RenderUtil.method_22054(this.x + 174, this.y + 6, this.x + 170 + var6 - 4, this.y + 8, 1.0, var8 ? -1895825408 : 1862270976);
      double var9 = var6 - 18;
      float var11 = Float.parseFloat("" + this.field_0004.method_08904());
      float var12 = Float.parseFloat("" + this.field_0004.method_08878());
      if (this.field_0000) {
         this.field_0003 = (float)Math.round((var11 + (var1 - (this.x + 180) * this.scale) * ((var12 - var11) / (var9 * this.scale))) * 100.0) / 100.0F;
         if (this.field_0004.getType().equals(Setting$Type.field_0002) || Keyboard.isKeyDown(42)) {
            this.field_0003 = Math.round(this.field_0003);
         }

         if (this.field_0003 < var11) {
            this.field_0003 = var11;
         } else if (this.field_0003 > var12) {
            this.field_0003 = var12;
         }

         switch (UnidentifiedClass1092.field_0000[this.field_0004.getType().ordinal()]) {
            case 1:
               this.field_0004.setValue(Integer.parseInt((int)this.field_0003 + ""));
               break;
            case 2:
               this.field_0004.setValue(this.field_0003);
               break;
            case 3:
               this.field_0004.setValue(Double.parseDouble(this.field_0003 + ""));
         }

         CheatBreaker.getInstance().getModuleManager().keyStrokes.initialize();
         Minecraft.getMinecraft().ingameGUI.getChatGUI().refreshChat();
      }

      float var5;
      float var16;
      var5 = (var5 = Float.parseFloat(this.field_0004.getValue() + "")) < this.field_0002 ? this.field_0002 - var5 : (var16 = var5 - this.field_0002);
      float var13 = ((var12 - var11) / 20.0F + var5 * 8.0F) / (Minecraft.debugFPS + 1);
      if (var13 < 1.0E-4) {
         var13 = 1.0E-4F;
      }

      float var4;
      if (this.field_0002 < (var4 = Float.parseFloat(this.field_0004.getValue() + ""))) {
         this.field_0002 = Math.min(this.field_0002 + var13, var4);
      } else if (this.field_0002 > var4) {
         this.field_0002 = Math.max(this.field_0002 - var13, var4);
      }

      double var14 = 100.0F * ((this.field_0002 - var11) / (var12 - var11));
      RenderUtil.method_22054(this.x + 174, this.y + 6, this.x + 180 + var9 * var14 / 100.0, this.y + 8, 4.0, -12418828);
      GL11.glColor4f(0.25F, 0.45F, 1.0F, 1.0F);
      RenderUtil.method_22052(this.x + 181.25F + var9 * var14 / 100.0, this.y + 7.25F, 4.5);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.method_22052(this.x + 181.25F + var9 * var14 / 100.0, this.y + 7.25F, 2.7F);
      this.method_23220(this.field_0004, var1, var2);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      boolean var4 = var1 > (this.x + 170) * this.scale
         && var1 < (this.x + 170 + 170 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + 10 + this.yOffset) * this.scale;
      if (var3 == 0 && var4) {
         this.field_0000 = true;
      }
   }
}
