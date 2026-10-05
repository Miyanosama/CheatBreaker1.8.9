package recovered.unidentified;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.entity.ai.EntityAIWander;

public class UnidentifiedClass1095 implements ActionListener {
   public UnidentifiedClass1440 field_0000;
   public EntityAIWander field_0001;

   public void actionPerformed(ActionEvent var1) {
      this.field_0000.dispose();
   }

   public UnidentifiedClass1095(UnidentifiedClass1440 var1) {
      this.field_0000 = var1;
   }
}
