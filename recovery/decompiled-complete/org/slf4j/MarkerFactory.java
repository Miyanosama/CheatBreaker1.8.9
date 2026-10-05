package org.slf4j;

import com.cheatbreaker.client.util.UuidParser;
import io.netty.handler.codec.http.HttpMethod;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Variant$Deserializer;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.optifine.entity.model.CustomEntityRenderer;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMarkerBinder;

public class MarkerFactory {
   public C18PacketSpectate field_0003;
   public static IMarkerFactory MARKER_FACTORY;
   public ModelBlockDefinition$Variant$Deserializer field_0002;
   public HttpMethod field_0004;
   public UuidParser field_0000;
   public CustomEntityRenderer field_0001;

   public static Marker method_11619(String var0) {
      return MARKER_FACTORY.getDetachedMarker(var0);
   }

   public static Marker method_11621(String var0) {
      return MARKER_FACTORY.getMarker(var0);
   }

   public static IMarkerFactory bwCompatibleGetMarkerFactoryFromBinder() {
      try {
         return StaticMarkerBinder.getSingleton().getMarkerFactory();
      } catch (NoSuchMethodError var1) {
         return StaticMarkerBinder.SINGLETON.getMarkerFactory();
      }
   }

   static {
      try {
         MARKER_FACTORY = bwCompatibleGetMarkerFactoryFromBinder();
      } catch (NoClassDefFoundError var1) {
         MARKER_FACTORY = new BasicMarkerFactory();
      } catch (Exception var2) {
         Util.report("Unexpected failure while binding MarkerFactory", var2);
      }
   }

   public static IMarkerFactory getIMarkerFactory() {
      return MARKER_FACTORY;
   }
}
