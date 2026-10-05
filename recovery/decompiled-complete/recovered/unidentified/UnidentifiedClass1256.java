package recovered.unidentified;

import com.jagrosh.discordipc.entities.Callback;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.world.gen.ChunkProviderDebug;

public class UnidentifiedClass1256 implements CharSequence {
   public ChunkProviderDebug field_0003;
   public Callback field_0004;
   public UnidentifiedClass4217 field_0000;
   public WorldRenderer field_0001;

   @Override
   public char charAt(int var1) {
      return this.field_0002.charAt(this.field_0006 + var1);
   }

   public UnidentifiedClass1256(UnidentifiedClass3204 var1, int var2, int var3) {
      this.field_0002 = var1;
      this.field_0005 = var2;
      this.field_0006 = var3;
      super();
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      StringBuilder var3 = new StringBuilder(var2 - var1);

      for (int var4 = var1; var4 < var2; var4++) {
         var3.append(this.charAt(var4));
      }

      return var3;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = this.length();

      for (int var3 = 0; var3 < var2; var3++) {
         var1.append(this.charAt(var3));
      }

      return var1.toString();
   }

   @Override
   public int length() {
      return this.field_0005 - this.field_0006;
   }
}
