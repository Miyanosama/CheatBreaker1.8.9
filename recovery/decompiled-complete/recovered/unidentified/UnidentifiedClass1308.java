package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenSwamp;

public class UnidentifiedClass1308 {
   public String field_0003;
   public ResourceLocation field_0005;
   public WorldGenSwamp field_0002;
   public String field_0004;
   public String field_0000;
   public String field_0001;
   public String field_0006;

   public void method_09051(String var1) {
      this.field_0000 = var1;
   }

   public String method_09050() {
      return this.field_0000;
   }

   public String method_09053() {
      return this.field_0003;
   }

   public String method_09049() {
      return this.field_0004;
   }

   public UnidentifiedClass1308(String var1, String var2, String var3, String var4, String var5, ResourceLocation var6) {
      this.field_0003 = var1;
      this.field_0001 = var2;
      this.field_0000 = var3;
      this.field_0006 = var4;
      this.field_0004 = var5;
      this.field_0005 = var6;
   }

   public ResourceLocation method_09052() {
      return this.field_0005;
   }

   public UnidentifiedClass1308(String var1, String var2, String var3, String var4, String var5) {
      this.field_0001 = var1;
      this.field_0003 = var2;
      this.field_0000 = var3;
      this.field_0006 = var4;
      this.field_0004 = var5;
      this.field_0005 = CheatBreaker.getInstance().method_19810(var4);
   }

   public String method_09047() {
      return this.field_0006;
   }

   public String method_09048() {
      return this.field_0001;
   }
}
