package io.netty.handler.codec.marshalling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.util.concurrent.FastThreadLocal;
import net.minecraft.client.renderer.block.model.BakedQuad;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;

public class ThreadLocalMarshallerProvider implements MarshallerProvider {
   public FastThreadLocal<Marshaller> marshallers = new FastThreadLocal<>();
   public MarshallingConfiguration config;
   public BakedQuad __junk5171491348539040442;
   public MarshallerFactory factory;
   public ChannelTrafficShapingHandler __junk2327539681889131268;

   @Override
   public Marshaller getMarshaller(ChannelHandlerContext var1) {
      Marshaller var2 = this.marshallers.get();
      if (var2 == null) {
         var2 = this.factory.createMarshaller(this.config);
         this.marshallers.set(var2);
      }

      return var2;
   }

   public ThreadLocalMarshallerProvider(MarshallerFactory var1, MarshallingConfiguration var2) {
      this.factory = var1;
      this.config = var2;
   }
}
