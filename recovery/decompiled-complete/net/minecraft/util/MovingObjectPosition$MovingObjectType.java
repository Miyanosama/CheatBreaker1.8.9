package net.minecraft.util;

import io.netty.handler.codec.compression.SnappyFramedDecoder;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.world.biome.BiomeGenBase;

public enum MovingObjectPosition$MovingObjectType {
   ENTITY,
   MISS,
   BLOCK;

   public BiomeGenBase field_0002;
   public BlockModelRenderer field_0004;
   public SnappyFramedDecoder field_0001;
}
