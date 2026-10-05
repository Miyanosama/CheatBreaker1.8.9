package io.netty.handler.codec.marshalling;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.world.biome.BiomeGenSwamp;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

public class DefaultUnmarshallerProvider implements UnmarshallerProvider {
   public MarshallerFactory factory;
   public MarshallingConfiguration config;

   public DefaultUnmarshallerProvider(MarshallerFactory var1, MarshallingConfiguration var2) {
      this.factory = var1;
      this.config = var2;
   }

   @Override
   public Unmarshaller getUnmarshaller(ChannelHandlerContext var1) throws java.lang.Exception {
      return this.factory.createUnmarshaller(this.config);
   }
}
