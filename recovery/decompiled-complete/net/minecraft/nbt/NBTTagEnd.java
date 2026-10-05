package net.minecraft.nbt;

import io.netty.buffer.PoolThreadCache$SubPageMemoryRegionCache;
import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.command.server.CommandBlockLogic$2;
import net.minecraft.network.status.client.C00PacketServerQuery;

public class NBTTagEnd extends NBTBase {
   public CommandBlockLogic$2 field_0002;
   public NBTTagByteArray field_0001;
   public PoolThreadCache$SubPageMemoryRegionCache field_0000;
   public C00PacketServerQuery field_0003;

   @Override
   public String toString() {
      return "END";
   }

   @Override
   public void write(DataOutput var1) {
   }

   @Override
   public byte getId() {
      return 0;
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.read(-4410342973580980151L & 4410342972407817332L);
   }

   @Override
   public NBTBase copy() {
      return new NBTTagEnd();
   }
}
