package org.java_websocket.extensions;

import io.netty.channel.epoll.Native;
import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.channel.udt.DefaultUdtServerChannelConfig;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import net.minecraft.client.stream.Metadata;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.Framedata;

public class DefaultExtension implements IExtension {
   public Native field_0003;
   public DefaultMarshallerProvider field_0000;
   public OioDatagramChannel field_0001;
   public Metadata field_0004;
   public DefaultUdtServerChannelConfig field_0002;

   @Override
   public IExtension copyInstance() {
      return new DefaultExtension();
   }

   @Override
   public void reset() {
   }

   @Override
   public void isFrameValid(Framedata var1) {
      if (var1.isRSV1() || var1.isRSV2() || var1.isRSV3()) {
         throw new InvalidFrameException("bad rsv RSV1: " + var1.isRSV1() + " RSV2: " + var1.isRSV2() + " RSV3: " + var1.isRSV3());
      }
   }

   @Override
   public void decodeFrame(Framedata var1) {
   }

   @Override
   public int hashCode() {
      return this.getClass().hashCode();
   }

   @Override
   public String getProvidedExtensionAsServer() {
      return "";
   }

   @Override
   public boolean acceptProvidedExtensionAsServer(String var1) {
      return true;
   }

   @Override
   public void encodeFrame(Framedata var1) {
   }

   @Override
   public String getProvidedExtensionAsClient() {
      return "";
   }

   @Override
   public boolean acceptProvidedExtensionAsClient(String var1) {
      return true;
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1 || var1 != null && this.getClass() == var1.getClass();
   }
}
