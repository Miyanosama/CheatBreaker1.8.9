package net.minecraft.util;

import io.netty.buffer.UnsafeDirectSwappedByteBuf;
import java.util.Iterator;
import net.minecraft.client.resources.FolderResourcePack;
import recovered.unidentified.UnidentifiedClass1899;

public class BlockPos$2 implements Iterable<BlockPos$MutableBlockPos> {
   public FolderResourcePack field_0002;
   public UnsafeDirectSwappedByteBuf field_0001;
   public UnidentifiedClass1899 field_0000;

   public BlockPos$2(BlockPos var1, BlockPos var2) {
      this.field_179312_a = var1;
      this.field_179311_b = var2;
      super();
   }

   @Override
   public Iterator<BlockPos$MutableBlockPos> iterator() {
      return new BlockPos$2$1(this);
   }
}
