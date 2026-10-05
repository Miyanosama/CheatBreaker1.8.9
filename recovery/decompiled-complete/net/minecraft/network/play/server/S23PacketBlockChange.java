package net.minecraft.network.play.server;

import javax.vecmath.TexCoord4f;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.optifine.shaders.BlockAliases;
import net.optifine.shaders.ShadersTex;

public class S23PacketBlockChange implements Packet<INetHandlerPlayClient> {
   public TexCoord4f field_0003;
   public BlockPos blockPosition;
   public IBlockState blockState;
   public ShadersTex field_0004;
   public MapGenScatteredFeature field_0000;
   public BlockAliases field_0001;

   public S23PacketBlockChange() {
   }

   public IBlockState getBlockState() {
      return this.blockState;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.blockPosition = var1.readBlockPos();
      this.blockState = Block.BLOCK_STATE_IDS.getByValue(var1.readVarIntFromBuffer());
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeBlockPos(this.blockPosition);
      var1.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.blockState));
   }

   public BlockPos getBlockPosition() {
      return this.blockPosition;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleBlockChange(this);
   }

   public S23PacketBlockChange(World var1, BlockPos var2) {
      this.blockPosition = var2;
      this.blockState = var1.getBlockState(var2);
   }
}
