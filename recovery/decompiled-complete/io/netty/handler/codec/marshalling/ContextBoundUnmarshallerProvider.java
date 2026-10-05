package io.netty.handler.codec.marshalling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ReservationNode;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$Traverser;
import net.minecraft.client.renderer.ChestRenderer;
import net.minecraft.realms.RealmsMth;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$End;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

public class ContextBoundUnmarshallerProvider extends DefaultUnmarshallerProvider {
   public ConcurrentHashMapV8$Traverser __junk4992893309295872883;
   public StructureNetherBridgePieces$End __junk3966202553189138545;
   public ChestRenderer __junk4116050868594689521;
   public static AttributeKey<Unmarshaller> UNMARSHALLER = AttributeKey.valueOf(ContextBoundUnmarshallerProvider.class.getName() + ".UNMARSHALLER");
   public RealmsMth __junk1933152101472584120;
   public ConcurrentHashMapV8$ReservationNode __junk6407998646894173502;

   public ContextBoundUnmarshallerProvider(MarshallerFactory var1, MarshallingConfiguration var2) {
      super(var1, var2);
   }

   @Override
   public Unmarshaller getUnmarshaller(ChannelHandlerContext var1) {
      Attribute var2 = var1.attr(UNMARSHALLER);
      Unmarshaller var3 = (Unmarshaller)var2.get();
      if (var3 == null) {
         var3 = super.getUnmarshaller(var1);
         var2.set(var3);
      }

      return var3;
   }
}
