package net.minecraft.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityMobSpawner extends TileEntity implements ITickable {
   public MobSpawnerBaseLogic spawnerLogic = new MobSpawnerBaseLogic() {
      @Override
      public void func_98267_a(int var1) {
         TileEntityMobSpawner.this.b.addBlockEvent(TileEntityMobSpawner.this.c, Blocks.mob_spawner, var1, 0);
      }

      @Override
      public BlockPos getSpawnerPosition() {
         return TileEntityMobSpawner.this.c;
      }

      @Override
      public void setRandomEntity(MobSpawnerBaseLogic.WeightedRandomMinecart var1) {
         super.setRandomEntity(var1);
         if (this.getSpawnerWorld() != null) {
            this.getSpawnerWorld().h(TileEntityMobSpawner.this.c);
         }
      }

      @Override
      public World getSpawnerWorld() {
         return TileEntityMobSpawner.this.b;
      }
   };

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      var1.removeTag("SpawnPotentials");
      return new S35PacketUpdateTileEntity(this.c, 1, var1);
   }

   @Override
   public void update() {
      this.spawnerLogic.updateSpawner();
   }

   @Override
   public boolean func_183000_F() {
      return true;
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.spawnerLogic.method_06497(var1);
   }

   public MobSpawnerBaseLogic getSpawnerBaseLogic() {
      return this.spawnerLogic;
   }

   @Override
   public boolean receiveClientEvent(int var1, int var2) {
      return this.spawnerLogic.setDelayToMin(var1) ? true : super.receiveClientEvent(var1, var2);
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      this.spawnerLogic.method_06493(var1);
   }
}
