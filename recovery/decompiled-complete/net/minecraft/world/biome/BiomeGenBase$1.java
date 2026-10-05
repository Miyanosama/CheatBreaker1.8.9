package net.minecraft.world.biome;

import io.netty.handler.codec.http.HttpHeaders$Names;
import io.netty.util.internal.UnsafeAtomicReferenceFieldUpdater;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityVillager$ListItemForEmeralds;
import org.newsclub.net.unix.AFUNIXSocket;

// $VF: synthetic class
public class BiomeGenBase$1 {
   public AFUNIXSocket field_0002;
   public HttpHeaders$Names field_0004;
   public UnsafeAtomicReferenceFieldUpdater field_0001;
   public EntityVillager$ListItemForEmeralds field_0003;

   static {
      try {
         field_180275_a[EnumCreatureType.MONSTER.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_180275_a[EnumCreatureType.CREATURE.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_180275_a[EnumCreatureType.WATER_CREATURE.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_180275_a[EnumCreatureType.AMBIENT.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
