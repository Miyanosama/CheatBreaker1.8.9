package recovered.unidentified;

import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulePosition;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.util.ArrayList;
import java.util.List;
import net.optifine.util.TileEntityUtils;

public class UnidentifiedClass0347 {
   public List<Float> field_0003;
   public TileEntityUtils field_0005;
   public List<Object> field_0002;
   public CBModulesGui field_0004;
   public List<Float> field_0000;
   public List<AbstractModule> field_0001;
   public List<CBGuiAnchor> field_0006;

   public UnidentifiedClass0347(CBModulesGui var1, List<CBModulePosition> var2) {
      this.field_0004 = var1;
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      ArrayList var6 = new ArrayList();
      ArrayList var7 = new ArrayList();

      for (CBModulePosition var9 : var2) {
         if (var9.module.getGuiAnchor() != null) {
            var3.add(var9.module);
            var4.add(var9.module.getGuiAnchor());
            var5.add(var9.module.getXTranslation());
            var6.add(var9.module.getYTranslation());
            var7.add(var9.module.method_28770());
         }
      }

      this.field_0001 = var3;
      this.field_0006 = var4;
      this.field_0000 = var5;
      this.field_0003 = var6;
      this.field_0002 = var7;
   }
}
