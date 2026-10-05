package net.minecraft.util;

import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder;
import net.minecraft.client.renderer.RegionRenderCache;
import net.minecraft.client.renderer.WorldRenderer$State;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.world.WorldProviderHell;

public class LongHashMap$Entry<V> {
   public WebSocket08FrameDecoder field_0004;
   public S47PacketPlayerListHeaderFooter field_0007;
   public long key;
   public int hash;
   public LongHashMap$Entry<V> nextEntry;
   public WorldProviderHell field_0001;
   public V value;
   public RegionRenderCache field_0005;
   public WorldRenderer$State field_0002;

   @Override
   public String toString() {
      return this.getKey() + "=" + this.getValue();
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof LongHashMap$Entry)) {
         return false;
      } else {
         LongHashMap$Entry var2 = (LongHashMap$Entry)var1;
         Long var3 = this.getKey();
         Long var4 = var2.getKey();
         if (var3 == var4 || var3 != null && var3.equals(var4)) {
            Object var5 = this.getValue();
            Object var6 = var2.getValue();
            if (var5 == var6 || var5 != null && var5.equals(var6)) {
               return true;
            }
         }

         return false;
      }
   }

   public LongHashMap$Entry(int var1, long var2, V var4, LongHashMap$Entry<V> var5) {
      this.value = (V)var4;
      this.nextEntry = var5;
      this.key = var2;
      this.hash = var1;
   }

   public V getValue() {
      return this.value;
   }

   @Override
   public int hashCode() {
      return LongHashMap.access$000(this.key);
   }

   public long getKey() {
      return this.key;
   }
}
