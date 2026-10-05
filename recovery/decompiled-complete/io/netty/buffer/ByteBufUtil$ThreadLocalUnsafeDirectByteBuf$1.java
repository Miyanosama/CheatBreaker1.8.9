package io.netty.buffer;

import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Bootstrap$9;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.world.gen.layer.GenLayerEdge$1;

public class ByteBufUtil$ThreadLocalUnsafeDirectByteBuf$1 extends Recycler<ByteBufUtil$ThreadLocalUnsafeDirectByteBuf> {
   public EntityThrowable __junk8236175962395552369;
   public TileEntityCommandBlock __junk5957597401591188641;
   public GenLayerEdge$1 __junk1521850789195701186;
   public Bootstrap$9 __junk6518825920669382684;

   public ByteBufUtil$ThreadLocalUnsafeDirectByteBuf newObject(Recycler$Handle var1) {
      return new ByteBufUtil$ThreadLocalUnsafeDirectByteBuf(var1, null);
   }
}
