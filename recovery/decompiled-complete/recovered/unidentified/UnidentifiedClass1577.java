package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$12;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass1577 extends AbstractModulesGuiElement {
   public LogBrokerMonitor$12 field_0000;
   public EntityIronGolem field_0001;

   public UnidentifiedClass1577(float var1) {
      super(var1);
      this.height = 50;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      Gui.a(this.x + (this.width / 2 - 15) - 41, this.y + 4, this.x + (this.width / 2 - 15) + 41, this.y + 51, -16777216);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.method_22064(
         new ResourceLocation(
            "client/defaults/crosshair_" + CheatBreaker.getInstance().getModuleManager().field_0016.field_0007.method_08874().toLowerCase() + ".png"
         ),
         this.x + (this.width / 2 - 15) - 40,
         this.y + 5,
         80.0F,
         45.0F
      );
      Gui.field_0003 = 0.0F;
      float var10001 = this.x + this.width / 2 - 15;
      CheatBreaker.getInstance().getModuleManager().field_0016.method_28369(var10001, this.y + this.height / 2 + 3, false);
   }
}
