package recovered.unidentified;

import java.util.List;
import net.minecraft.world.WorldProviderEnd;
import org.apache.log4j.xml.DOMConfigurator$4;

public class UnidentifiedClass1421 implements UnidentifiedInterface4518 {
   public UnidentifiedClass0890 field_0005;
   public WorldProviderEnd field_0002;
   public DOMConfigurator$4 field_0000;

   @Override
   public void method_09732(char[] var1, int var2, int var3) {
      this.field_0006[0] = true;
      this.field_0003.append(var1, var2, var3);
   }

   @Override
   public void method_09731() {
      this.field_0004.add(this.field_0003.toString());
      this.field_0003.setLength(0);
      this.field_0006[0] = false;
   }

   public UnidentifiedClass1421(UnidentifiedClass4984 var1, boolean[] var2, StringBuilder var3, List var4) {
      this.field_0001 = var1;
      this.field_0006 = var2;
      this.field_0003 = var3;
      this.field_0004 = var4;
      super();
   }
}
