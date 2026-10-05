package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.FloatFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.gui.GuiFlatPresets$LayerItem;
import net.minecraft.network.NetworkManager$1;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor;

public class UnidentifiedClass3249 extends FloatFade {
   public PropertyDirection field_0004;
   public UnidentifiedClass0557 field_0005;
   public NetworkManager$1 field_0007;
   public CategoryNodeEditor field_0000;
   public MinMaxFade field_0001;
   public MinMaxFade field_0003;
   public GuiFlatPresets$LayerItem field_0006;

   public boolean method_20201() {
      return this.field_0001.method_21210();
   }

   public float method_20202() {
      if (!this.field_0001.method_21217()) {
         this.field_0001.method_20200();
      }

      if (this.field_0001.method_21233()) {
         return Math.max(1.0F * this.field_0001.method_21227(), 0.15F);
      } else if (this.method_21229() <= this.field_0003.method_21240()) {
         if (!this.field_0003.method_21217()) {
            this.field_0003.method_20200();
         }

         return 1.0F - 0.85F * this.field_0003.method_21227();
      } else {
         return 1.0F;
      }
   }

   public UnidentifiedClass3249(UnidentifiedClass0557 var1, UnidentifiedClass0557 var2, long var3) {
      this.field_0002 = var1;
      super(var3);
      this.field_0005 = var2;
      this.field_0001 = new MinMaxFade(Math.min((long)Math.min((float)var3 * 0.2F, 3000.0F), 2102750L & 1280402940L));
      this.field_0003 = new MinMaxFade(Math.min((long)((float)var3 * 0.4F), 83891081L & 733895535909188494L));
   }

   @Override
   public void method_20200() {
      super.method_20200();
      if (!this.field_0001.method_21217()) {
         this.field_0001.method_20200();
      }
   }
}
