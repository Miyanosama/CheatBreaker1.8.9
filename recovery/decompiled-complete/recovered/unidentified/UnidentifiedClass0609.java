package recovered.unidentified;

import javax.swing.JTextField;
import junit.swingui.ProgressBar;
import net.minecraft.client.audio.SoundManager$2$1;
import net.minecraft.client.gui.GuiCustomizeWorldScreen;
import net.minecraft.item.crafting.RecipesWeapons;
import net.minecraft.world.WorldServer$ServerBlockEventList;

public class UnidentifiedClass0609 extends ProgressBar {
   public JTextField field_0002;
   public WorldServer$ServerBlockEventList field_0004;
   public SoundManager$2$1 field_0001;
   public GuiCustomizeWorldScreen field_0003;
   public RecipesWeapons field_0000;

   public void method_04438() {
      this.field_0002.setBackground(this.getStatusColor());
   }

   public UnidentifiedClass0609(JTextField var1) {
      this.field_0002 = var1;
   }
}
