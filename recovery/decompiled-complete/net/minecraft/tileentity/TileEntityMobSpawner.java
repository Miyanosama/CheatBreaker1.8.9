package net.minecraft.tileentity;

import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.PoolThreadCache$1;
import io.netty.util.concurrent.FastThreadLocal;
import javax.vecmath.Color4b;
import net.minecraft.block.BlockFlowerPot$EnumFlowerType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.gen.structure.MapGenStructure$1;

public class TileEntityMobSpawner extends TileEntity implements ITickable {
   public FastThreadLocal field_0002;
   public MobSpawnerBaseLogic spawnerLogic = new TileEntityMobSpawner$1(this);
   public DuplicatedByteBuf field_0001;
   public BlockFlowerPot$EnumFlowerType field_0004;
   public PoolThreadCache$1 field_0000;
   public MapGenStructure$1 field_0003;
   public Color4b field_0005;

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
