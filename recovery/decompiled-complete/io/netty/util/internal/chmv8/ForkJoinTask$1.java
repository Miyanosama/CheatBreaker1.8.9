package io.netty.util.internal.chmv8;

import io.netty.channel.local.LocalChannel$4;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.renderer.block.model.ModelBlock$Bookkeep;
import net.minecraft.network.NetworkManager$3;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import sun.misc.Unsafe;

public class ForkJoinTask$1 implements PrivilegedExceptionAction<Unsafe> {
   public BlockPattern __junk6684589084390582463;
   public S2CPacketSpawnGlobalEntity __junk8751781893590736798;
   public LocalChannel$4 __junk5023766392685068277;
   public MarshallingDecoder __junk7339199042948135388;
   public ModelBlock$Bookkeep __junk7532606443892079062;
   public NetworkManager$3 __junk8293956092832284996;
   public ModelBox __junk2714426159216514170;

   public Unsafe run() {
      Class<Unsafe> var1 = Unsafe.class;

      for (Field var5 : var1.getDeclaredFields()) {
         var5.setAccessible(true);
         Object var6 = var5.get(null);
         if (var1.isInstance(var6)) {
            return var1.cast(var6);
         }
      }

      throw new NoSuchFieldError("the Unsafe");
   }
}
