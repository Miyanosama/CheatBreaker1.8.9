package net.minecraft.world.chunk;

import com.cheatbreaker.client.nethandler.server.PacketTeammates;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.command.CommandSetSpawnpoint;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EmptyChunk extends Chunk {
   public CommandSetSpawnpoint field_0001;
   public PacketTeammates field_0000;

   public EmptyChunk(World var1, int var2, int var3) {
      super(var1, var2, var3);
   }

   @Override
   public void setLightFor(EnumSkyBlock var1, BlockPos var2, int var3) {
   }

   @Override
   public int getBlockLightOpacity(BlockPos var1) {
      return 255;
   }

   @Override
   public void addTileEntity(BlockPos var1, TileEntity var2) {
   }

   @Override
   public void removeEntityAtIndex(Entity var1, int var2) {
   }

   @Override
   public int getLightSubtracted(BlockPos var1, int var2) {
      return 0;
   }

   @Override
   public boolean getAreLevelsEmpty(int var1, int var2) {
      return true;
   }

   @Override
   public boolean canSeeSky(BlockPos var1) {
      return false;
   }

   @Override
   public <T extends Entity> void getEntitiesOfTypeWithinAAAB(Class<? extends T> var1, AxisAlignedBB var2, List<T> var3, Predicate<? super T> var4) {
   }

   @Override
   public boolean isEmpty() {
      return true;
   }

   @Override
   public TileEntity getTileEntity(BlockPos var1, Chunk$EnumCreateEntityType var2) {
      return null;
   }

   @Override
   public boolean needsSaving(boolean var1) {
      return false;
   }

   @Override
   public Block getBlock(BlockPos var1) {
      return Blocks.air;
   }

   @Override
   public void removeTileEntity(BlockPos var1) {
   }

   @Override
   public Random getRandomWithSeed(long var1) {
      return new Random(
         this.getWorld().J() + this.a * this.a * 4987142 + this.a * 5947611 + this.b * this.b * (291708847L & 1254295463L) + this.b * 389711 ^ var1
      );
   }

   @Override
   public void removeEntity(Entity var1) {
   }

   @Override
   public int getHeightValue(int var1, int var2) {
      return 0;
   }

   @Override
   public void onChunkLoad() {
   }

   @Override
   public boolean isAtLocation(int var1, int var2) {
      return var1 == this.a && var2 == this.b;
   }

   @Override
   public void setChunkModified() {
   }

   @Override
   public void addEntity(Entity var1) {
   }

   @Override
   public void onChunkUnload() {
   }

   @Override
   public int getBlockMetadata(BlockPos var1) {
      return 0;
   }

   @Override
   public int getLightFor(EnumSkyBlock var1, BlockPos var2) {
      return var1.defaultLightValue;
   }

   @Override
   public void generateSkylightMap() {
   }

   @Override
   public void addTileEntity(TileEntity var1) {
   }

   @Override
   public void generateHeightMap() {
   }

   @Override
   public void getEntitiesWithinAABBForEntity(Entity var1, AxisAlignedBB var2, List<Entity> var3, Predicate<? super Entity> var4) {
   }
}
