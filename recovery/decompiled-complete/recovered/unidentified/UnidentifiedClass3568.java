package recovered.unidentified;

import java.util.Iterator;
import net.minecraft.world.chunk.Chunk$EnumCreateEntityType;

public class UnidentifiedClass3568 implements Iterable<Integer> {
   public Chunk$EnumCreateEntityType field_0001;

   public UnidentifiedClass3568(String var1) {
      this.field_0000 = var1;
      super();
   }

   @Override
   public Iterator<Integer> iterator() {
      return new UnidentifiedClass5074(this);
   }
}
