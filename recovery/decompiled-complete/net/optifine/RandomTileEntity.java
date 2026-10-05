package net.optifine;

import com.cheatbreaker.client.ui.module.CBAnchorHelper$1;
import io.netty.handler.codec.socks.SocksResponseType;
import net.minecraft.entity.ai.EntityAIFindEntityNearest$1;
import net.minecraft.nbt.JsonToNBT$Compound;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.util.TileEntityUtils;

public class RandomTileEntity implements IRandomEntity {
   public JsonToNBT$Compound field_0002;
   public CBAnchorHelper$1 field_0004;
   public TileEntity tileEntity;
   public EntityAIFindEntityNearest$1 field_0003;
   public SocksResponseType field_0000;

   @Override
   public int getMaxHealth() {
      return -1;
   }

   @Override
   public BiomeGenBase getSpawnBiome() {
      return this.tileEntity.z().getBiomeGenForCoords(this.tileEntity.v());
   }

   @Override
   public String getName() {
      return TileEntityUtils.getTileEntityName(this.tileEntity);
   }

   @Override
   public int getId() {
      return Config.getRandom(this.tileEntity.v(), this.tileEntity.u());
   }

   public void setTileEntity(TileEntity var1) {
      this.tileEntity = var1;
   }

   public TileEntity getTileEntity() {
      return this.tileEntity;
   }

   @Override
   public int getHealth() {
      return -1;
   }

   @Override
   public BlockPos getSpawnPosition() {
      return this.tileEntity.v();
   }
}
