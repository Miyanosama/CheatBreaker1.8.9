package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;

public class CompiledChunk {
   public BitSet[] animatedSprites;
   public WorldRenderer.State state;
   public boolean[] layersUsed = new boolean[RenderChunk.ENUM_WORLD_BLOCK_LAYERS.length];
   public boolean empty;
   public List<TileEntity> tileEntities;
   public SetVisibility setVisibility;
   public boolean[] layersStarted = new boolean[RenderChunk.ENUM_WORLD_BLOCK_LAYERS.length];
   public static CompiledChunk DUMMY = new CompiledChunk() {
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
   };

   public List<TileEntity> getTileEntities() {
      return this.tileEntities;
   }

   public boolean isLayerStarted(EnumWorldBlockLayer var1) {
      return this.layersStarted[var1.ordinal()];
   }

   public void addTileEntity(TileEntity var1) {
      this.tileEntities.add(var1);
   }

   public WorldRenderer.State getState() {
      return this.state;
   }

   public void setLayerStarted(EnumWorldBlockLayer var1) {
      this.layersStarted[var1.ordinal()] = true;
   }

   public void setVisibility(SetVisibility var1) {
      this.setVisibility = var1;
   }

   public CompiledChunk() {
      this.empty = true;
      this.tileEntities = Lists.newArrayList();
      this.setVisibility = new SetVisibility();
      this.animatedSprites = new BitSet[RenderChunk.ENUM_WORLD_BLOCK_LAYERS.length];
   }

   public void setLayerUsed(EnumWorldBlockLayer var1) {
      this.empty = false;
      this.layersUsed[var1.ordinal()] = true;
   }

   public boolean isVisible(EnumFacing var1, EnumFacing var2) {
      return this.setVisibility.isVisible(var1, var2);
   }

   public void setState(WorldRenderer.State var1) {
      this.state = var1;
   }

   public boolean isLayerEmpty(EnumWorldBlockLayer var1) {
      return !this.layersUsed[var1.ordinal()];
   }

   public boolean isEmpty() {
      return this.empty;
   }

   public void setAnimatedSprites(EnumWorldBlockLayer var1, BitSet var2) {
      this.animatedSprites[var1.ordinal()] = var2;
   }

   public BitSet getAnimatedSprites(EnumWorldBlockLayer var1) {
      return this.animatedSprites[var1.ordinal()];
   }
}
