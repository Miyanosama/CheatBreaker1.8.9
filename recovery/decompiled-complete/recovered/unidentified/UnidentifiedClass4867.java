package recovered.unidentified;

import java.io.Writer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Corridor3;

public class UnidentifiedClass4867 implements UnidentifiedInterface4518 {
   public StructureNetherBridgePieces$Corridor3 field_0001;
   public ServerData field_0000;

   @Override
   public void method_09731() {
      this.field_0002.write(this.field_0003);
   }

   @Override
   public void method_27155(String var1) {
      this.field_0002.write(var1);
   }

   public UnidentifiedClass4867(Writer var1, String var2) {
      this.field_0002 = var1;
      this.field_0003 = var2;
      super();
   }

   @Override
   public void method_09732(char[] var1, int var2, int var3) {
      this.field_0002.write(var1, var2, var3);
   }
}
