package net.optifine;

import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.util.internal.JavassistTypeParameterMatcherGenerator;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemHoe;
import net.minecraft.network.NetworkSystem$3;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.WorldInfo$9;
import net.optifine.shaders.gui.GuiShaders$1;
import recovered.unidentified.UnidentifiedClass4690;

public class RandomEntity implements IRandomEntity {
   public JavassistTypeParameterMatcherGenerator field_0003;
   public NetworkSystem$3 field_0006;
   public Entity entity;
   public OioDatagramChannel field_0005;
   public ItemHoe field_0000;
   public UnidentifiedClass4690 field_0001;
   public GuiShaders$1 field_0007;
   public WorldInfo$9 field_0004;

   @Override
   public int getMaxHealth() {
      if (!(this.entity instanceof EntityLiving)) {
         return 0;
      } else {
         EntityLiving var1 = (EntityLiving)this.entity;
         return (int)var1.getMaxHealth();
      }
   }

   @Override
   public BlockPos getSpawnPosition() {
      return this.entity.H().spawnPosition;
   }

   public void setEntity(Entity var1) {
      this.entity = var1;
   }

   @Override
   public int getId() {
      UUID var1 = this.entity.aK();
      long var2 = var1.getLeastSignificantBits();
      return (int)(var2 & 2147483647L & 2147483647L);
   }

   @Override
   public int getHealth() {
      if (!(this.entity instanceof EntityLiving)) {
         return 0;
      } else {
         EntityLiving var1 = (EntityLiving)this.entity;
         return (int)var1.getHealth();
      }
   }

   public Entity getEntity() {
      return this.entity;
   }

   @Override
   public BiomeGenBase getSpawnBiome() {
      return this.entity.H().spawnBiome;
   }

   @Override
   public String getName() {
      return this.entity.u_() ? this.entity.aM() : null;
   }
}
