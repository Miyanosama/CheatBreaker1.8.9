package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import net.minecraft.client.gui.Gui;

public class UnidentifiedClass1221 extends AbstractModulesGuiElement {
   public UnidentifiedClass1221(Setting var1, float var2) {
      super(var2);
      this.setting = var1;
      this.height = 12;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      CheatBreaker.getInstance()
         .field_0068
         .drawString(
            ((String)this.setting.getValue()).toUpperCase(),
            this.x + 2,
            this.y + 2,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0017 : UnidentifiedClass5100.field_0015
         );
      Gui.a(
         this.x + 2,
         this.y + this.height - 1,
         this.x + this.width / 2 - 20,
         this.y + this.height,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0025 : UnidentifiedClass5100.field_0022
      );
   }
}
