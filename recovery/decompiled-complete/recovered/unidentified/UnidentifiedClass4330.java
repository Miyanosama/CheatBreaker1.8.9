package recovered.unidentified;

import io.netty.buffer.ByteBufUtil$ThreadLocalDirectByteBuf$1;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.command.server.CommandSaveOff;
import net.minecraft.util.Cartesian$Product$ProductIterator;

public class UnidentifiedClass4330<T> implements Iterable<T[]> {
   public BlockNewLeaf field_0002;
   public CommandSaveOff field_0004;
   public Class<T> field_0001;
   public Iterable<? extends T>[] field_0003;
   public ByteBufUtil$ThreadLocalDirectByteBuf$1 field_0000;

   public UnidentifiedClass4330(Class<T> var1, Iterable<? extends T>[] var2) {
      this.field_0001 = var1;
      this.field_0003 = var2;
   }

   @Override
   public Iterator<T[]> iterator() {
      return (Iterator<T[]>)(this.field_0003.length <= 0
         ? Collections.singletonList(UnidentifiedClass1222.method_08277(this.field_0001, 0)).iterator()
         : new Cartesian$Product$ProductIterator(this.field_0001, this.field_0003, null));
   }
}
