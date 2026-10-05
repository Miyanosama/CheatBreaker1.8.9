package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import javazoom.jl.player.PlayerApplet;
import net.minecraft.client.gui.Gui;
import net.minecraft.tileentity.TileEntity$2;

public class UnidentifiedClass1394 extends AbstractElement {
   public PlayerApplet field_0003;
   public boolean field_0005;
   public TileEntity$2 field_0002;
   public ColorFade field_0004;
   public ColorFade field_0000 = new ColorFade(520093696, 1056964608);
   public MinMaxFade field_0001;

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return !var4 ? false : false;
   }

   public UnidentifiedClass1394() {
      this.field_0004 = new ColorFade(0, -1073741825);
      this.field_0001 = new MinMaxFade(5225741010586848238L & 192946942L);
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.field_0000.method_25066(this.a_(var1, var2) && var3).getRGB());
   }
}
