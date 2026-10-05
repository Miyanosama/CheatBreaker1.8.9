package io.netty.bootstrap;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameDecoder;
import io.netty.util.internal.StringUtil;
import net.minecraft.client.renderer.GlStateManager$TexGen;
import net.minecraft.realms.Tezzelator;
import net.minecraft.world.WorldProviderHell$1;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import recovered.unidentified.UnidentifiedClass1776;

public class AbstractBootstrap$BootstrapChannelFactory<T extends Channel> implements ChannelFactory<T> {
   public WebSocket13FrameDecoder __junk3119279280639048844;
   public LogBrokerMonitor __junk1689159438651453446;
   public GlStateManager$TexGen __junk3683852996321784008;
   public Tezzelator __junk2682555699901502581;
   public BasicConfigurator __junk7462003306679399480;
   public Class<? extends T> clazz;
   public ByteBufOutputStream __junk1490061523388914885;
   public WorldProviderHell$1 __junk5994524349430102744;
   public UnidentifiedClass1776 __junk1944223680228969910;

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this.clazz) + ".class";
   }

   public AbstractBootstrap$BootstrapChannelFactory(Class<? extends T> var1) {
      this.clazz = var1;
   }

   @Override
   public T newChannel() {
      try {
         return (T)this.clazz.newInstance();
      } catch (Throwable var2) {
         throw new ChannelException("Unable to create Channel from class " + this.clazz, var2);
      }
   }
}
