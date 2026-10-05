package recovered.unidentified;

import com.cheatbreaker.client.util.SessionServer$Status;
import java.io.File;
import java.io.IOException;
import net.minecraft.client.renderer.culling.Frustum;
import org.scijava.nativelib.BaseJniExtractor;

public class UnidentifiedClass4584 extends BaseJniExtractor {
   public Frustum field_0001;
   public SessionServer$Status field_0000;
   public File field_0002;

   public void method_27584(String var1) {
      this.field_0002 = new File(System.getProperty("java.library.tmpdir", var1));
      this.field_0002.mkdirs();
      if (!this.field_0002.isDirectory()) {
         throw new IOException("Unable to create native library working directory " + this.field_0002);
      }
   }

   public UnidentifiedClass4584(Class var1, String var2) {
      super(var1);
      this.method_27584(var2);
   }

   public UnidentifiedClass4584() {
      super(null);
      this.method_27584("tmplib");
   }

   @Override
   public File method_12332() {
      return this.field_0002;
   }

   @Override
   public File method_12334() {
      return this.field_0002;
   }
}
