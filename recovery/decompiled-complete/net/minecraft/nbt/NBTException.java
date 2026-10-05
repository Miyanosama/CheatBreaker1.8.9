package net.minecraft.nbt;

import io.netty.buffer.UnreleasableByteBuf;
import io.netty.util.concurrent.SucceededFuture;
import junit.swingui.DefaultFailureDetailView;
import net.minecraft.block.BlockSand;
import net.minecraft.creativetab.CreativeTabs$11;

public class NBTException extends Exception {
   public UnreleasableByteBuf field_0002;
   public BlockSand field_0004;
   public CreativeTabs$11 field_0001;
   public DefaultFailureDetailView field_0003;
   public SucceededFuture field_0000;

   public NBTException(String var1) {
      super(var1);
   }
}
