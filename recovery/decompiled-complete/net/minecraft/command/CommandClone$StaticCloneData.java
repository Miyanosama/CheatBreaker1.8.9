package net.minecraft.command;

import io.netty.handler.codec.socks.SocksInitResponseDecoder$1;
import javax.vecmath.Point4d;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.gen.GeneratorBushFeature;

public class CommandClone$StaticCloneData {
   public BlockPos field_0003;
   public SocksInitResponseDecoder$1 field_0005;
   public NBTTagCompound field_0002;
   public IBlockState field_0004;
   public GeneratorBushFeature field_0000;
   public BlockHugeMushroom field_0001;
   public Point4d field_0006;

   public CommandClone$StaticCloneData(BlockPos var1, IBlockState var2, NBTTagCompound var3) {
      this.field_0003 = var1;
      this.field_0004 = var2;
      this.field_0002 = var3;
   }
}
