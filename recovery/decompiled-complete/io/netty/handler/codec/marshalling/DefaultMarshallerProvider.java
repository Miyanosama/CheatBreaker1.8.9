package io.netty.handler.codec.marshalling;

import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.socks.SocksInitResponse;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;

public class DefaultMarshallerProvider implements MarshallerProvider {
   public RenderSilverfish __junk2860009582910205363;
   public MarshallingConfiguration config;
   public AbstractScrollableElement __junk6925408334814055989;
   public SocksInitResponse __junk7630923437215825944;
   public MarshallerFactory factory;

   @Override
   public Marshaller getMarshaller(ChannelHandlerContext var1) {
      return this.factory.createMarshaller(this.config);
   }

   public DefaultMarshallerProvider(MarshallerFactory var1, MarshallingConfiguration var2) {
      this.factory = var1;
      this.config = var2;
   }
}
