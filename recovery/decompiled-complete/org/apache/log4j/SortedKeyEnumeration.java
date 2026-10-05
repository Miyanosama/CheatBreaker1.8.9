package org.apache.log4j;

import io.netty.handler.ssl.util.InsecureTrustManagerFactory$1;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import net.minecraft.block.BlockRailBase$1;
import net.minecraft.block.BlockStaticLiquid;
import org.java_websocket.SSLSocketChannel2;

public class SortedKeyEnumeration implements Enumeration {
   public SSLSocketChannel2 field_0002;
   public BlockRailBase$1 field_0004;
   public InsecureTrustManagerFactory$1 field_0001;
   public Enumeration e;
   public BlockStaticLiquid field_0000;

   public Object nextElement() {
      return this.e.nextElement();
   }

   public boolean hasMoreElements() {
      return this.e.hasMoreElements();
   }

   public SortedKeyEnumeration(Hashtable var1) {
      Enumeration var2 = var1.keys();
      Vector var3 = new Vector(var1.size());

      for (int var5 = 0; var2.hasMoreElements(); var5++) {
         String var6 = (String)var2.nextElement();

         int var4;
         for (var4 = 0; var4 < var5; var4++) {
            String var7 = (String)var3.get(var4);
            if (var6.compareTo(var7) <= 0) {
               break;
            }
         }

         var3.add(var4, var6);
      }

      this.e = var3.elements();
   }
}
