package net.minecraft.client.renderer.block.statemap;

import io.netty.channel.ChannelFlushPromiseNotifier$DefaultFlushCheckpoint;
import io.netty.handler.codec.http.ClientCookieEncoder;
import io.netty.handler.ssl.util.FingerprintTrustManagerFactory$1;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.particle.EntitySuspendFX;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class DefaultStateMapper extends StateMapperBase {
   public BlockGrass field_0003;
   public SoundRegistry field_0005;
   public ClientCookieEncoder field_0002;
   public EntitySuspendFX field_0004;
   public FingerprintTrustManagerFactory$1 field_0000;
   public ChannelFlushPromiseNotifier$DefaultFlushCheckpoint field_0001;
   public TexturedQuad field_0006;

   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState var1) {
      return new ModelResourceLocation(Block.blockRegistry.getNameForObject(var1.getBlock()), this.getPropertyString(var1.getProperties()));
   }
}
