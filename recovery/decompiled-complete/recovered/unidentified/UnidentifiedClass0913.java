package recovered.unidentified;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.fading.AbstractFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import io.netty.util.internal.SystemPropertyUtil$1;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.util.MovementInputFromOptions;
import net.optifine.shaders.config.EnumShaderOption;
import org.lwjgl.input.Mouse;

public class UnidentifiedClass0913 extends AbstractElement {
   public EnumShaderOption field_0003;
   public AbstractFade field_0005;
   public EntityAIRunAroundLikeCrazy field_0002;
   public Number field_0004;
   public MovementInputFromOptions field_0000;
   public Setting field_0001;
   public SystemPropertyUtil$1 field_0006;

   public Object method_06163(Object var1) {
      try {
         return var1;
      } catch (ClassCastException var3) {
         return null;
      }
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else {
         if (Mouse.isButtonDown(0) && this.a_(var1, var2)) {
            this.field_0005.method_20200();
            this.field_0004 = (Number)this.field_0001.getValue();
            float var5 = ((Number)this.field_0001.method_08904()).floatValue();
            float var6 = ((Number)this.field_0001.method_08878()).floatValue();
            if (var1 - this.x > this.width / 2.0F) {
               var1 += 2.0F;
            }

            float var7 = var5 + (var1 - this.x) * ((var6 - var5) / this.width);
            switch (UnidentifiedClass3368.field_0000[this.field_0001.getType().ordinal()]) {
               case 1:
                  this.field_0001.setValue(this.method_06163(Integer.parseInt((int)var7 + "")));
                  break;
               case 2:
                  this.field_0001.setValue(this.method_06163(var7));
                  break;
               case 3:
                  this.field_0001.setValue(this.method_06163(Double.parseDouble(var7 + "")));
            }
         }

         return super.handleElementMouseClicked(var1, var2, var3, var4);
      }
   }

   public UnidentifiedClass0913(Setting var1) {
      this.field_0001 = var1;
      this.field_0005 = new MinMaxFade(7829148542949163324L & -7829148543816676051L);
      this.field_0004 = (Number)var1.getValue();
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13158601);
      if (!this.field_0005.method_21217()) {
         this.field_0004 = (Number)this.field_0001.getValue();
      }

      float var4 = ((Number)this.field_0001.getValue()).floatValue();
      float var5 = ((Number)this.field_0001.method_08904()).floatValue();
      float var6 = ((Number)this.field_0001.method_08878()).floatValue();
      float var7 = var4 - this.field_0004.floatValue();
      float var8 = 100.0F * ((this.field_0004.floatValue() + var7 * this.field_0005.method_21227() - var5) / (var6 - var5));
      Gui.drawRect(this.x, this.y, this.x + this.width / 100.0F * var8, this.y + this.height, -52429);
   }
}
