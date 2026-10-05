package io.netty.handler.codec.marshalling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.ssl.JettyNpnSslEngine$1;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.world.biome.BiomeGenSwamp;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

public class DefaultUnmarshallerProvider implements UnmarshallerProvider {
   public MarshallerFactory factory;
   public EntityBoat __junk6974934920676541559;
   public JettyNpnSslEngine$1 __junk3366284643809819185;
   public BiomeGenSwamp __junk1493324266633144185;
   public MarshallingConfiguration config;

   public DefaultUnmarshallerProvider(MarshallerFactory var1, MarshallingConfiguration var2) {
      this.factory = var1;
      this.config = var2;
   }

   @Override
   public Unmarshaller getUnmarshaller(ChannelHandlerContext var1) {
      return this.factory.createUnmarshaller(this.config);
   }
}
