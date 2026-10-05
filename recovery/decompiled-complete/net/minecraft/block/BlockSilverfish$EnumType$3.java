package net.minecraft.block;

import io.netty.channel.DefaultChannelPipeline$2;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.SoundHandler$1;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.renderer.entity.layers.LayerEnderDragonDeath;
import net.minecraft.command.CommandHelp;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.init.Blocks;

public enum BlockSilverfish$EnumType$3 {
   public SoundHandler$1 field_0003;
   public LayerEnderDragonDeath field_0005;
   public ChatLine field_0000;
   public DefaultChannelPipeline$2 field_0001;
   public SyntaxErrorException field_0002;
   public CommandHelp field_0004;

   @Override
   public IBlockState getModelBlock() {
      return Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick$EnumType.DEFAULT);
   }

   public BlockSilverfish$EnumType$3(int var3, String var4, String var5) {
   }
}
