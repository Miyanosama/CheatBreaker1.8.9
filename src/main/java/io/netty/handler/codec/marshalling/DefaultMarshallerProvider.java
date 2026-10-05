package io.netty.handler.codec.marshalling;

import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.socks.SocksInitResponse;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;

public class DefaultMarshallerProvider implements MarshallerProvider {
   public MarshallingConfiguration config;
   public MarshallerFactory factory;

   @Override
   public Marshaller getMarshaller(ChannelHandlerContext var1) throws java.lang.Exception {
      return this.factory.createMarshaller(this.config);
   }

   public DefaultMarshallerProvider(MarshallerFactory var1, MarshallingConfiguration var2) {
      this.factory = var1;
      this.config = var2;
   }
}
