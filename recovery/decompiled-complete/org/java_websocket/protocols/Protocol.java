package org.java_websocket.protocols;

import io.netty.handler.codec.spdy.DefaultSpdyWindowUpdateFrame;
import java.util.regex.Pattern;
import junit.framework.ComparisonFailure;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.gen.feature.WorldGenSpikes;
import net.optifine.reflect.ReflectorRaw;

public class Protocol implements IProtocol {
   public static Pattern patternSpace = Pattern.compile(" ");
   public EntityPigZombie field_0006;
   public ComparisonFailure field_0002;
   public String providedProtocol;
   public ReflectorRaw field_0000;
   public DefaultSpdyWindowUpdateFrame field_0001;
   public WorldGenSpikes field_0007;
   public static Pattern patternComma = Pattern.compile(",");

   @Override
   public String toString() {
      return this.getProvidedProtocol();
   }

   @Override
   public boolean acceptProvidedProtocol(String var1) {
      String var2 = patternSpace.matcher(var1).replaceAll("");
      String[] var3 = patternComma.split(var2);

      for (String var7 : var3) {
         if (this.providedProtocol.equals(var7)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public int hashCode() {
      return this.providedProtocol.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Protocol var2 = (Protocol)var1;
         return this.providedProtocol.equals(var2.providedProtocol);
      } else {
         return false;
      }
   }

   @Override
   public String getProvidedProtocol() {
      return this.providedProtocol;
   }

   @Override
   public IProtocol copyInstance() {
      return new Protocol(this.getProvidedProtocol());
   }

   public Protocol(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.providedProtocol = var1;
      }
   }
}
