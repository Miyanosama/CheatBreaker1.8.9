package org.slf4j.helpers;

import com.cheatbreaker.client.util.teammates.Teammate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;

public class BasicMarkerFactory implements IMarkerFactory {
   public Teammate field_0001;
   public ConcurrentMap<String, Marker> markerMap = new ConcurrentHashMap<>();
   public EntityAIFindEntityNearest field_0000;

   @Override
   public boolean detachMarker(String var1) {
      return var1 == null ? false : this.markerMap.remove(var1) != null;
   }

   @Override
   public boolean exists(String var1) {
      return var1 == null ? false : this.markerMap.containsKey(var1);
   }

   @Override
   public Marker getMarker(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Marker name cannot be null");
      } else {
         Object var2 = this.markerMap.get(var1);
         if (var2 == null) {
            var2 = new BasicMarker(var1);
            Marker var3 = this.markerMap.putIfAbsent(var1, (Marker)var2);
            if (var3 != null) {
               var2 = var3;
            }
         }

         return (Marker)var2;
      }
   }

   @Override
   public Marker getDetachedMarker(String var1) {
      return new BasicMarker(var1);
   }
}
