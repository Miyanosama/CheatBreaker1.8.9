package io.netty.buffer;

import io.netty.handler.stream.ChunkedStream;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerHorseInventory;
import net.optifine.ConnectedTexturesCompact;
import net.optifine.entity.model.ModelAdapterCow;
import net.optifine.shaders.ShaderProgramData;

public class PooledUnsafeDirectByteBuf$1 extends Recycler<PooledUnsafeDirectByteBuf> {
   public Blocks __junk5857337324368435544;
   public ModelAdapterCow __junk8426831681050355402;
   public WrongUsageException __junk6761389673157164234;
   public ConnectedTexturesCompact __junk3665573850195997441;
   public ContainerHorseInventory __junk1820449476442372884;
   public ChunkedStream __junk667555089115561464;
   public ShaderProgramData __junk3360527757369157407;

   public PooledUnsafeDirectByteBuf newObject(Recycler$Handle var1) {
      return new PooledUnsafeDirectByteBuf(var1, 0, null);
   }
}
