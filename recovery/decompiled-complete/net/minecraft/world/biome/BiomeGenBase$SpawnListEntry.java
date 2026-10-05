package net.minecraft.world.biome;

import net.minecraft.entity.EntityLiving;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.util.WeightedRandom$Item;
import net.optifine.player.PlayerConfiguration;

public class BiomeGenBase$SpawnListEntry extends WeightedRandom$Item {
   public int maxGroupCount;
   public PlayerConfiguration field_0004;
   public int minGroupCount;
   public S20PacketEntityProperties field_0003;
   public Class<? extends EntityLiving> entityClass;

   @Override
   public String toString() {
      return this.entityClass.getSimpleName() + "*(" + this.minGroupCount + "-" + this.maxGroupCount + "):" + this.a;
   }

   public BiomeGenBase$SpawnListEntry(Class<? extends EntityLiving> var1, int var2, int var3, int var4) {
      super(var2);
      this.entityClass = var1;
      this.minGroupCount = var3;
      this.maxGroupCount = var4;
   }
}
