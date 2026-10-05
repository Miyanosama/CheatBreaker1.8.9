package io.netty.handler.codec.marshalling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import net.minecraft.client.renderer.ChestRenderer;
import net.minecraft.realms.RealmsMth;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

public class ContextBoundUnmarshallerProvider extends DefaultUnmarshallerProvider {
   public static AttributeKey<Unmarshaller> UNMARSHALLER = AttributeKey.valueOf(ContextBoundUnmarshallerProvider.class.getName() + ".UNMARSHALLER");

   public ContextBoundUnmarshallerProvider(MarshallerFactory var1, MarshallingConfiguration var2) {
      super(var1, var2);
   }

   @Override
   public Unmarshaller getUnmarshaller(ChannelHandlerContext var1) throws java.lang.Exception {
      Attribute var2 = var1.attr(UNMARSHALLER);
      Unmarshaller var3 = (Unmarshaller)var2.get();
      if (var3 == null) {
         var3 = super.getUnmarshaller(var1);
         var2.set(var3);
      }

      return var3;
   }
}
