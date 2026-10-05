package recovered.unidentified;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import net.minecraft.client.particle.EntityFirework$OverlayFX;
import net.minecraft.scoreboard.IScoreObjectiveCriteria$EnumRenderType;
import net.optifine.entity.model.ModelAdapterMooshroom;

public class UnidentifiedClass3389 extends WindowAdapter {
   public IScoreObjectiveCriteria$EnumRenderType field_0002;
   public UnidentifiedClass1675 field_0004;
   public UnidentifiedClass1440 field_0001;
   public EntityFirework$OverlayFX field_0003;
   public ModelAdapterMooshroom field_0000;

   public void windowClosing(WindowEvent var1) {
      this.field_0001.dispose();
   }

   public UnidentifiedClass3389(UnidentifiedClass1440 var1) {
      this.field_0001 = var1;
   }
}
