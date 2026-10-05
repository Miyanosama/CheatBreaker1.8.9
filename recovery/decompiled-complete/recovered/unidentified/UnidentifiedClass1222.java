package recovered.unidentified;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.ssl.util.SimpleTrustManagerFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.BlockTallGrass$EnumType;
import net.minecraft.client.main.IIllIlIlIlllIIlllllIlIlIl;
import net.minecraft.util.Cartesian$GetList;

public class UnidentifiedClass1222 {
   public SimpleTrustManagerFactory field_0001;
   public BlockTallGrass$EnumType field_0003;
   public ProtobufDecoder field_0000;
   public IIllIlIlIlllIIlllllIlIlIl field_0002;

   public static <T> T[] method_08278(Class<? super T> var0, Iterable<? extends T> var1) {
      ArrayList var2 = Lists.newArrayList();

      for (Object var4 : var1) {
         var2.add(var4);
      }

      return (T[])var2.toArray(method_08280(var0, var2.size()));
   }

   public static <T> Iterable<List<T>> method_08279(Iterable<? extends Iterable<? extends T>> var0) {
      return method_08282(method_08281(Object.class, var0));
   }

   public static <T> T[] method_08280(Class<? super T> var0, int var1) {
      return (T[])((Object[])Array.newInstance(var0, var1));
   }

   public static <T> Iterable<T[]> method_08281(Class<T> var0, Iterable<? extends Iterable<? extends T>> var1) {
      return new UnidentifiedClass4330<>(var0, method_08278(Iterable.class, var1), null);
   }

   public static <T> Iterable<List<T>> method_08282(Iterable<Object[]> var0) {
      return Iterables.transform(var0, new Cartesian$GetList(null));
   }
}
