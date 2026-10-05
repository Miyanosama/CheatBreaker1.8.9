package net.minecraft.client.renderer.chunk;

import io.netty.handler.codec.serialization.ObjectEncoder;
import java.util.BitSet;
import net.minecraft.util.ChatComponentScore;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.pathfinder.WalkNodeProcessor;

public class CompiledChunk$1 extends CompiledChunk {
   public ObjectEncoder field_0001;
   public WorldGenDesertWells field_0002;
   public WalkNodeProcessor field_0000;
   public ChatComponentScore field_0003;

   @Override
   public void setLayerUsed(EnumWorldBlockLayer var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setAnimatedSprites(EnumWorldBlockLayer var1, BitSet var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setLayerStarted(EnumWorldBlockLayer var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isVisible(EnumFacing var1, EnumFacing var2) {
      return false;
   }
}
