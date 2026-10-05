package io.netty.handler.codec.marshalling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.base64.Base64;
import io.netty.util.Recycler;
import io.netty.util.concurrent.FastThreadLocal;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

public class ThreadLocalUnmarshallerProvider implements UnmarshallerProvider {
   public MarshallerFactory factory;
   public FastThreadLocal<Unmarshaller> unmarshallers = new FastThreadLocal<>();
   public MarshallingConfiguration config;

   @Override
   public Unmarshaller getUnmarshaller(ChannelHandlerContext var1) throws java.lang.Exception {
      Unmarshaller var2 = this.unmarshallers.get();
      if (var2 == null) {
         var2 = this.factory.createUnmarshaller(this.config);
         this.unmarshallers.set(var2);
      }

      return var2;
   }

   public ThreadLocalUnmarshallerProvider(MarshallerFactory var1, MarshallingConfiguration var2) {
      this.factory = var1;
      this.config = var2;
   }
}
