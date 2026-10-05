package io.netty.channel.local;

import io.netty.channel.Channel;
import java.net.SocketAddress;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.network.play.server.S48PacketResourcePackSend;

public class LocalAddress extends SocketAddress implements Comparable<LocalAddress> {
   public String id;
   public String strVal;
   public static final long serialVersionUID = 4644331421130916435L;
   public static LocalAddress ANY = new LocalAddress("ANY");

   public String id() {
      return this.id;
   }

   @Override
   public int hashCode() {
      return this.id.hashCode();
   }

   public LocalAddress(Channel var1) {
      StringBuilder var2 = new StringBuilder(16);
      var2.append("local:E");
      var2.append(Long.toHexString(var1.hashCode() & 4294967295L | 4294967296L));
      var2.setCharAt(7, ':');
      this.id = var2.substring(6);
      this.strVal = var2.toString();
   }

   @Override
   public String toString() {
      return this.strVal;
   }

   public int compareTo(LocalAddress var1) {
      return this.id.compareTo(var1.id);
   }

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof LocalAddress) ? false : this.id.equals(((LocalAddress)var1).id);
   }

   public LocalAddress(String var1) {
      if (var1 == null) {
         throw new NullPointerException("id");
      } else {
         var1 = var1.trim().toLowerCase();
         if (var1.isEmpty()) {
            throw new IllegalArgumentException("empty id");
         } else {
            this.id = var1;
            this.strVal = "local:" + var1;
         }
      }
   }
}
