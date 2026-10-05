package net.minecraft.util;

import com.google.common.collect.Iterators;
import io.netty.handler.codec.http.HttpHeaderDateFormat$HttpHeaderDateFormatObsolete2;
import java.util.Iterator;
import java.util.List;
import net.optifine.entity.model.ModelAdapterCreeper;

public class ClassInheritanceMultiMap$1 implements Iterable<S> {
   public HttpHeaderDateFormat$HttpHeaderDateFormatObsolete2 field_0000;
   public ModelAdapterCreeper field_0002;

   @Override
   public Iterator<S> iterator() {
      List var1 = (List)ClassInheritanceMultiMap.access$000(this.this$0).get(this.this$0.initializeClassLookup(this.val$clazz));
      if (var1 == null) {
         return Iterators.emptyIterator();
      } else {
         Iterator var2 = var1.iterator();
         return Iterators.filter(var2, this.val$clazz);
      }
   }

   public ClassInheritanceMultiMap$1(ClassInheritanceMultiMap var1, Class var2) {
      this.this$0 = var1;
      this.val$clazz = var2;
      super();
   }
}
