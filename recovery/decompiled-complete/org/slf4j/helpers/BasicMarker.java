package org.slf4j.helpers;

import com.cheatbreaker.client.util.UuidParser;
import io.netty.handler.codec.MessageToMessageCodec$1;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.entity.passive.EntityHorse$GroupData;
import net.optifine.LightMapPack;
import org.slf4j.Marker;

public class BasicMarker implements Marker {
   public static long field_0004;
   public EntityHorse$GroupData field_0007;
   public UuidParser field_0001;
   public String name;
   public MessageToMessageCodec$1 field_0008;
   public LightMapPack field_0006;
   public List<Marker> referenceList = new CopyOnWriteArrayList<>();
   public static String OPEN = "[ ";
   public static String CLOSE = " ]";
   public static String SEP = ", ";

   @Override
   public boolean hasReferences() {
      return this.referenceList.size() > 0;
   }

   @Override
   public int hashCode() {
      return this.name.hashCode();
   }

   @Override
   public boolean contains(Marker var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Other cannot be null");
      } else if (this.equals(var1)) {
         return true;
      } else {
         if (this.hasReferences()) {
            for (Marker var3 : this.referenceList) {
               if (var3.contains(var1)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public boolean hasChildren() {
      return this.hasReferences();
   }

   @Override
   public boolean remove(Marker var1) {
      return this.referenceList.remove(var1);
   }

   @Override
   public Iterator<Marker> iterator() {
      return this.referenceList.iterator();
   }

   @Override
   public void add(Marker var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
      } else if (!this.contains(var1)) {
         if (!var1.contains(this)) {
            this.referenceList.add(var1);
         }
      }
   }

   @Override
   public String toString() {
      if (!this.hasReferences()) {
         return this.getName();
      } else {
         Iterator var1 = this.iterator();
         StringBuilder var3 = new StringBuilder(this.getName());
         var3.append(' ').append(OPEN);

         while (var1.hasNext()) {
            Marker var2 = (Marker)var1.next();
            var3.append(var2.getName());
            if (var1.hasNext()) {
               var3.append(SEP);
            }
         }

         var3.append(CLOSE);
         return var3.toString();
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (!(var1 instanceof Marker)) {
         return false;
      } else {
         Marker var2 = (Marker)var1;
         return this.name.equals(var2.getName());
      }
   }

   @Override
   public boolean contains(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Other cannot be null");
      } else if (this.name.equals(var1)) {
         return true;
      } else {
         if (this.hasReferences()) {
            for (Marker var3 : this.referenceList) {
               if (var3.contains(var1)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public BasicMarker(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("A marker name cannot be null");
      } else {
         this.name = var1;
      }
   }

   @Override
   public String getName() {
      return this.name;
   }
}
