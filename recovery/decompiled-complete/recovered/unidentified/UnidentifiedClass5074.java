package recovered.unidentified;

import io.netty.handler.codec.http.cors.CorsHandler;
import io.netty.util.internal.SystemPropertyUtil;
import java.util.Iterator;
import net.minecraft.world.gen.structure.StructureComponent;

public class UnidentifiedClass5074 implements Iterator<Integer> {
   public int field_0003;
   public StructureComponent field_0002;
   public SystemPropertyUtil field_0004;
   public CorsHandler field_0000;
   public int field_0001;

   @Override
   public void remove() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean hasNext() {
      return this.field_0001 < this.field_0003;
   }

   public Integer method_30124() {
      int var1 = this.field_0005.field_0000.codePointAt(this.field_0001);
      this.field_0001 = this.field_0001 + Character.charCount(var1);
      return var1;
   }

   public UnidentifiedClass5074(UnidentifiedClass3568 var1) {
      this.field_0005 = var1;
      super();
      this.field_0001 = 0;
      this.field_0003 = this.field_0005.field_0000.length();
   }
}
