package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Sets;
import java.nio.FloatBuffer;
import java.util.BitSet;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RegionRenderCache;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.optifine.BlockPosM;
import net.optifine.CustomBlockLayers;
import net.optifine.override.ChunkCacheOF;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.render.AabbFrame;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;

public class RenderChunk {
   public int frameIndex;
   public static EnumWorldBlockLayer[] ENUM_WORLD_BLOCK_LAYERS = EnumWorldBlockLayer.values();
   public BlockPos[] positionOffsets16;
   public boolean renderChunksOffset16Updated;
   public boolean renderChunkNeighboursUpated;
   public boolean playerUpdate;
   public EnumMap<EnumFacing, BlockPos> mapEnumFacing;
   public ReentrantLock lockCompileTask;
   public RenderChunk[] renderChunkNeighboursValid;
   public static int renderChunksUpdated;
   public RenderGlobal renderGlobal;
   public boolean fixBlockLayer;
   public ChunkCompileTaskGenerator compileTask;
   public int recoveredField3101;
   public boolean needsUpdate;
   public Chunk chunk;
   public int regionZ;
   public int regionX;
   public CompiledChunk compiledChunk = CompiledChunk.DUMMY;
   public FloatBuffer modelviewMatrix;
   public BlockPos position;
   public boolean isMipmaps;
   public AxisAlignedBB boundingBox;
   public RenderGlobal.ContainerLocalRenderInformation renderInfo;
   public EnumWorldBlockLayer[] recoveredField3102;
   public RenderChunk[] renderChunksOfset16;
   public World world;
   public AabbFrame boundingBoxParent;
   public RenderChunk[] renderChunkNeighbours;
   public VertexBuffer[] vertexBuffers;
   public Set<TileEntity> recoveredField3103;
   public ReentrantLock lockCompiledChunk;

   public CompiledChunk getCompiledChunk() {
      return this.compiledChunk;
   }

   public void postRenderOverlays(RegionRenderCacheBuilder var1, CompiledChunk var2, boolean[] var3) {
      this.postRenderOverlay(EnumWorldBlockLayer.CUTOUT, var1, var2, var3);
      this.postRenderOverlay(EnumWorldBlockLayer.CUTOUT_MIPPED, var1, var2, var3);
      this.postRenderOverlay(EnumWorldBlockLayer.TRANSLUCENT, var1, var2, var3);
   }

   public boolean setFrameIndex(int var1) {
      if (this.frameIndex == var1) {
         return false;
      } else {
         this.frameIndex = var1;
         return true;
      }
   }

   public boolean isWorldPlayerUpdate() {
      if (this.world instanceof WorldClient) {
         WorldClient var1 = (WorldClient)this.world;
         return var1.isPlayerUpdate();
      } else {
         return false;
      }
   }

   public void multModelviewMatrix() {
      GlStateManager.multMatrix(this.modelviewMatrix);
   }

   public void updateRenderChunkNeighboursValid() {
      int var1 = this.getPosition().getX();
      int var2 = this.getPosition().getZ();
      int var3 = EnumFacing.NORTH.ordinal();
      int var4 = EnumFacing.SOUTH.ordinal();
      int var5 = EnumFacing.WEST.ordinal();
      int var6 = EnumFacing.EAST.ordinal();
      this.renderChunkNeighboursValid[var3] = this.renderChunkNeighbours[var3].getPosition().getZ() == var2 - 16 ? this.renderChunkNeighbours[var3] : null;
      this.renderChunkNeighboursValid[var4] = this.renderChunkNeighbours[var4].getPosition().getZ() == var2 + 16 ? this.renderChunkNeighbours[var4] : null;
      this.renderChunkNeighboursValid[var5] = this.renderChunkNeighbours[var5].getPosition().getX() == var1 - 16 ? this.renderChunkNeighbours[var5] : null;
      this.renderChunkNeighboursValid[var6] = this.renderChunkNeighbours[var6].getPosition().getX() == var1 + 16 ? this.renderChunkNeighbours[var6] : null;
      this.renderChunkNeighboursUpated = true;
   }

   public void setNeedsUpdate(boolean var1) {
      this.needsUpdate = var1;
      if (var1) {
         if (this.isWorldPlayerUpdate()) {
            this.playerUpdate = true;
         }
      } else {
         this.playerUpdate = false;
      }
   }

   public ChunkCacheOF makeChunkCacheOF(BlockPos var1) {
      BlockPos var2 = var1.add(-1, -1, -1);
      BlockPos var3 = var1.add(16, 16, 16);
      RegionRenderCache var4 = this.createRegionRenderCache(this.world, var2, var3, 1);
      if (Reflector.MinecraftForgeClient_onRebuildChunk.exists()) {
         Reflector.call(Reflector.MinecraftForgeClient_onRebuildChunk, this.world, var1, var4);
      }

      return new ChunkCacheOF(var4, var2, var3, 1);
   }

   public BlockPos getBlockPosOffset16(EnumFacing var1) {
      return this.getPositionOffset16(var1);
   }

   public void postRenderOverlay(EnumWorldBlockLayer var1, RegionRenderCacheBuilder var2, CompiledChunk var3, boolean[] var4) {
      WorldRenderer var5 = var2.getWorldRendererByLayer(var1);
      if (var5.isDrawing()) {
         var3.setLayerStarted(var1);
         var4[var1.ordinal()] = true;
      }
   }

   public Chunk getChunk() {
      return this.getChunk(this.position);
   }

   public Chunk getChunk(BlockPos var1) {
      Chunk var2 = this.chunk;
      if (var2 != null && var2.isLoaded()) {
         return var2;
      } else {
         var2 = this.world.getChunkFromBlockCoords(var1);
         this.chunk = var2;
         return var2;
      }
   }

   public boolean isChunkRegionEmpty() {
      return this.isChunkRegionEmpty(this.position);
   }

   public void setCompiledChunk(CompiledChunk var1) {
      this.lockCompiledChunk.lock();

      try {
         this.compiledChunk = var1;
      } finally {
         this.lockCompiledChunk.unlock();
      }
   }

   public void preRenderBlocks(WorldRenderer var1, BlockPos var2) {
      var1.begin(7, DefaultVertexFormats.BLOCK);
      if (Config.isRenderRegions()) {
         byte var3 = 8;
         int var4 = var2.getX() >> var3 << var3;
         int var5 = var2.getY() >> var3 << var3;
         int var6 = var2.getZ() >> var3 << var3;
         var4 = this.regionX;
         var6 = this.regionZ;
         var1.setTranslation(-var4, -var5, -var6);
      } else {
         var1.setTranslation(-var2.getX(), -var2.getY(), -var2.getZ());
      }
   }

   public boolean isNeedsUpdate() {
      return this.needsUpdate;
   }

   public void setRenderChunkNeighbour(EnumFacing var1, RenderChunk var2) {
      this.renderChunkNeighbours[var1.ordinal()] = var2;
      this.renderChunkNeighboursValid[var1.ordinal()] = var2;
   }

   public void resortTransparency(float var1, float var2, float var3, ChunkCompileTaskGenerator var4) {
      CompiledChunk var5 = var4.getCompiledChunk();
      if (var5.getState() != null && !var5.isLayerEmpty(EnumWorldBlockLayer.TRANSLUCENT)) {
         WorldRenderer var6 = var4.getRegionRenderCacheBuilder().getWorldRendererByLayer(EnumWorldBlockLayer.TRANSLUCENT);
         this.preRenderBlocks(var6, this.position);
         var6.setVertexState(var5.getState());
         this.postRenderBlocks(EnumWorldBlockLayer.TRANSLUCENT, var1, var2, var3, var6, var5);
      }
   }

   public void finishCompileTask() {
      this.lockCompileTask.lock();

      try {
         if (this.compileTask != null && this.compileTask.getStatus() != ChunkCompileTaskGenerator.Status.DONE) {
            this.compileTask.finish();
            this.compileTask = null;
         }
      } finally {
         this.lockCompileTask.unlock();
      }
   }

   public BlockPos getPositionOffset16(EnumFacing var1) {
      int var2 = var1.getIndex();
      BlockPos var3 = this.positionOffsets16[var2];
      if (var3 == null) {
         var3 = this.getPosition().a(var1, 16);
         this.positionOffsets16[var2] = var3;
      }

      return var3;
   }

   public void rebuildChunk(float var1, float var2, float var3, ChunkCompileTaskGenerator var4) {
      CompiledChunk var5 = new CompiledChunk();
      boolean var6 = true;
      BlockPos var7 = new BlockPos(this.position);
      BlockPos var8 = var7.add(15, 15, 15);
      var4.getLock().lock();

      try {
         if (var4.getStatus() != ChunkCompileTaskGenerator.Status.COMPILING) {
            return;
         }

         var4.setCompiledChunk(var5);
      } finally {
         var4.getLock().unlock();
      }

      VisGraph var9 = new VisGraph();
      HashSet var10 = Sets.newHashSet();
      if (!this.isChunkRegionEmpty(var7)) {
         renderChunksUpdated++;
         ChunkCacheOF var11 = this.makeChunkCacheOF(var7);
         var11.renderStart();
         boolean[] var12 = new boolean[ENUM_WORLD_BLOCK_LAYERS.length];
         BlockRendererDispatcher var13 = Minecraft.getMinecraft().getBlockRendererDispatcher();
         boolean var14 = Reflector.ForgeBlock_canRenderInLayer.exists();
         boolean var15 = Reflector.ForgeHooksClient_setRenderLayer.exists();

         for (Object var17 : BlockPosM.getAllInBoxMutable(var7, var8)) {
            BlockPosM var18 = (BlockPosM)var17;
            IBlockState var19 = var11.getBlockState(var18);
            Block var20 = var19.getBlock();
            if (var20.isOpaqueCube()) {
               var9.func_178606_a(var18);
            }

            if (ReflectorForge.blockHasTileEntity(var19)) {
               TileEntity var21 = var11.getTileEntity(new BlockPos(var18));
               TileEntitySpecialRenderer var22 = TileEntityRendererDispatcher.instance.getSpecialRenderer(var21);
               if (var21 != null && var22 != null) {
                  var5.addTileEntity(var21);
                  if (var22.forceTileEntityRender()) {
                     var10.add(var21);
                  }
               }
            }

            EnumWorldBlockLayer[] var41;
            if (var14) {
               var41 = ENUM_WORLD_BLOCK_LAYERS;
            } else {
               var41 = this.recoveredField3102;
               var41[0] = var20.getBlockLayer();
            }

            for (int var42 = 0; var42 < var41.length; var42++) {
               EnumWorldBlockLayer var23 = var41[var42];
               if (var14) {
                  boolean var24 = Reflector.callBoolean(var20, Reflector.ForgeBlock_canRenderInLayer, var23);
                  if (!var24) {
                     continue;
                  }
               }

               if (var15) {
                  Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, var23);
               }

               var23 = this.fixBlockLayer(var19, var23);
               int var44 = var23.ordinal();
               if (var20.getRenderType() != -1) {
                  WorldRenderer var25 = var4.getRegionRenderCacheBuilder().getWorldRendererByLayerId(var44);
                  var25.setBlockLayer(var23);
                  RenderEnv var26 = var25.getRenderEnv(var19, var18);
                  var26.setRegionRenderCacheBuilder(var4.getRegionRenderCacheBuilder());
                  if (!var5.isLayerStarted(var23)) {
                     var5.setLayerStarted(var23);
                     this.preRenderBlocks(var25, var7);
                  }

                  var12[var44] |= var13.renderBlock(var19, var18, var11, var25);
                  if (var26.isOverlaysRendered()) {
                     this.postRenderOverlays(var4.getRegionRenderCacheBuilder(), var5, var12);
                     var26.setOverlaysRendered(false);
                  }
               }
            }

            if (var15) {
               Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, null);
            }
         }

         for (EnumWorldBlockLayer var39 : ENUM_WORLD_BLOCK_LAYERS) {
            if (var12[var39.ordinal()]) {
               var5.setLayerUsed(var39);
            }

            if (var5.isLayerStarted(var39)) {
               if (Config.isShaders()) {
                  SVertexBuilder.calcNormalChunkLayer(var4.getRegionRenderCacheBuilder().getWorldRendererByLayer(var39));
               }

               WorldRenderer var40 = var4.getRegionRenderCacheBuilder().getWorldRendererByLayer(var39);
               this.postRenderBlocks(var39, var1, var2, var3, var40, var5);
               if (var40.animatedSprites != null) {
                  var5.setAnimatedSprites(var39, (BitSet)var40.animatedSprites.clone());
               }
            } else {
               var5.setAnimatedSprites(var39, (BitSet)null);
            }
         }

         var11.renderFinish();
      }

      var5.setVisibility(var9.computeVisibility());
      this.lockCompileTask.lock();

      try {
         HashSet var34 = Sets.newHashSet(var10);
         HashSet var35 = Sets.newHashSet(this.recoveredField3103);
         var34.removeAll(this.recoveredField3103);
         var35.removeAll(var10);
         this.recoveredField3103.clear();
         this.recoveredField3103.addAll(var10);
         this.renderGlobal.updateTileEntities(var35, var34);
      } finally {
         this.lockCompileTask.unlock();
      }
   }

   public RegionRenderCache createRegionRenderCache(World var1, BlockPos var2, BlockPos var3, int var4) {
      return new RegionRenderCache(var1, var2, var3, var4);
   }

   public boolean isChunkRegionEmpty(BlockPos var1) {
      int var2 = var1.getY();
      int var3 = var2 + 15;
      return this.getChunk(var1).getAreLevelsEmpty(var2, var3);
   }

   public void initModelviewMatrix() {
      GlStateManager.pushMatrix();
      GlStateManager.loadIdentity();
      float var1 = 1.000001F;
      GlStateManager.translate(-8.0F, -8.0F, -8.0F);
      GlStateManager.scale(var1, var1, var1);
      GlStateManager.translate(8.0F, 8.0F, 8.0F);
      GlStateManager.getFloat(2982, this.modelviewMatrix);
      GlStateManager.popMatrix();
   }

   public void postRenderBlocks(EnumWorldBlockLayer var1, float var2, float var3, float var4, WorldRenderer var5, CompiledChunk var6) {
      if (var1 == EnumWorldBlockLayer.TRANSLUCENT && !var6.isLayerEmpty(var1)) {
         var5.sortVertexData(var2, var3, var4);
         var6.setState(var5.getVertexState());
      }

      var5.finishDrawing();
   }

   public void stopCompileTask() {
      this.finishCompileTask();
      this.compiledChunk = CompiledChunk.DUMMY;
   }

   public RenderChunk getRenderChunkNeighbour(EnumFacing var1) {
      if (!this.renderChunkNeighboursUpated) {
         this.updateRenderChunkNeighboursValid();
      }

      return this.renderChunkNeighboursValid[var1.ordinal()];
   }

   public VertexBuffer getVertexBufferByLayer(int var1) {
      return this.vertexBuffers[var1];
   }

   public AabbFrame getBoundingBoxParent() {
      if (this.boundingBoxParent == null) {
         BlockPos var1 = this.getPosition();
         int var2 = var1.getX();
         int var3 = var1.getY();
         int var4 = var1.getZ();
         byte var5 = 5;
         int var6 = var2 >> var5 << var5;
         int var7 = var3 >> var5 << var5;
         int var8 = var4 >> var5 << var5;
         if (var6 != var2 || var7 != var3 || var8 != var4) {
            AabbFrame var9 = this.renderGlobal.getRenderChunk(new BlockPos(var6, var7, var8)).getBoundingBoxParent();
            if (var9 != null && var9.a == var6 && var9.b == var7 && var9.c == var8) {
               this.boundingBoxParent = var9;
            }
         }

         if (this.boundingBoxParent == null) {
            int var10 = 1 << var5;
            this.boundingBoxParent = new AabbFrame(var6, var7, var8, var6 + var10, var7 + var10, var8 + var10);
         }
      }

      return this.boundingBoxParent;
   }

   public void deleteGlResources() {
      this.stopCompileTask();

      for (int var1 = 0; var1 < EnumWorldBlockLayer.values().length; var1++) {
         if (this.vertexBuffers[var1] != null) {
            this.vertexBuffers[var1].deleteGlBuffers();
         }
      }
   }

   public RenderChunk getRenderChunkOffset16(ViewFrustum var1, EnumFacing var2) {
      if (!this.renderChunksOffset16Updated) {
         for (int var3 = 0; var3 < EnumFacing.VALUES.length; var3++) {
            EnumFacing var4 = EnumFacing.VALUES[var3];
            BlockPos var5 = this.getBlockPosOffset16(var4);
            this.renderChunksOfset16[var3] = var1.getRenderChunk(var5);
         }

         this.renderChunksOffset16Updated = true;
      }

      return this.renderChunksOfset16[var2.ordinal()];
   }

   public RenderGlobal.ContainerLocalRenderInformation getRenderInfo() {
      return this.renderInfo;
   }

   public EnumWorldBlockLayer fixBlockLayer(IBlockState var1, EnumWorldBlockLayer var2) {
      if (CustomBlockLayers.isActive()) {
         EnumWorldBlockLayer var3 = CustomBlockLayers.getRenderLayer(var1);
         if (var3 != null) {
            return var3;
         }
      }

      if (!this.fixBlockLayer) {
         return var2;
      } else {
         if (this.isMipmaps) {
            if (var2 == EnumWorldBlockLayer.CUTOUT) {
               Block var4 = var1.getBlock();
               if (var4 instanceof BlockRedstoneWire) {
                  return var2;
               }

               if (var4 instanceof BlockCactus) {
                  return var2;
               }

               return EnumWorldBlockLayer.CUTOUT_MIPPED;
            }
         } else if (var2 == EnumWorldBlockLayer.CUTOUT_MIPPED) {
            return EnumWorldBlockLayer.CUTOUT;
         }

         return var2;
      }
   }

   public RenderChunk(World var1, RenderGlobal var2, BlockPos var3, int var4) {
      this.lockCompileTask = new ReentrantLock();
      this.lockCompiledChunk = new ReentrantLock();
      this.compileTask = null;
      this.recoveredField3103 = Sets.newHashSet();
      this.modelviewMatrix = GLAllocation.createDirectFloatBuffer(16);
      this.vertexBuffers = new VertexBuffer[EnumWorldBlockLayer.values().length];
      this.frameIndex = -1;
      this.needsUpdate = true;
      this.mapEnumFacing = null;
      this.positionOffsets16 = new BlockPos[EnumFacing.VALUES.length];
      this.recoveredField3102 = new EnumWorldBlockLayer[1];
      this.isMipmaps = Config.isMipmaps();
      this.fixBlockLayer = !Reflector.BetterFoliageClient.exists();
      this.playerUpdate = false;
      this.renderChunksOfset16 = new RenderChunk[6];
      this.renderChunksOffset16Updated = false;
      this.renderChunkNeighbours = new RenderChunk[EnumFacing.VALUES.length];
      this.renderChunkNeighboursValid = new RenderChunk[EnumFacing.VALUES.length];
      this.renderChunkNeighboursUpated = false;
      this.renderInfo = new RenderGlobal.ContainerLocalRenderInformation(this, (EnumFacing)null, 0);
      this.world = var1;
      this.renderGlobal = var2;
      this.recoveredField3101 = var4;
      if (!var3.equals(this.getPosition())) {
         this.setPosition(var3);
      }

      if (OpenGlHelper.useVbo()) {
         for (int var5 = 0; var5 < EnumWorldBlockLayer.values().length; var5++) {
            this.vertexBuffers[var5] = new VertexBuffer(DefaultVertexFormats.BLOCK);
         }
      }
   }

   public boolean isBoundingBoxInFrustum(ICamera var1, int var2) {
      return this.getBoundingBoxParent().isBoundingBoxInFrustumFully(var1, var2) ? true : var1.isBoundingBoxInFrustum(this.boundingBox);
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public void setPosition(BlockPos var1) {
      this.stopCompileTask();
      this.position = var1;
      byte var2 = 8;
      this.regionX = var1.getX() >> var2 << var2;
      this.regionZ = var1.getZ() >> var2 << var2;
      this.boundingBox = new AxisAlignedBB(var1, var1.add(16, 16, 16));
      this.initModelviewMatrix();

      for (int var3 = 0; var3 < this.positionOffsets16.length; var3++) {
         this.positionOffsets16[var3] = null;
      }

      this.renderChunksOffset16Updated = false;
      this.renderChunkNeighboursUpated = false;

      for (int var5 = 0; var5 < this.renderChunkNeighbours.length; var5++) {
         RenderChunk var4 = this.renderChunkNeighbours[var5];
         if (var4 != null) {
            var4.renderChunkNeighboursUpated = false;
         }
      }

      this.chunk = null;
      this.boundingBoxParent = null;
   }

   public ChunkCompileTaskGenerator makeCompileTaskTransparency() {
      this.lockCompileTask.lock();

      Object var3;
      try {
         if (this.compileTask == null || this.compileTask.getStatus() != ChunkCompileTaskGenerator.Status.PENDING) {
            if (this.compileTask != null && this.compileTask.getStatus() != ChunkCompileTaskGenerator.Status.DONE) {
               this.compileTask.finish();
               this.compileTask = null;
            }

            this.compileTask = new ChunkCompileTaskGenerator(this, ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY);
            this.compileTask.setCompiledChunk(this.compiledChunk);
            return this.compileTask;
         }

         Object var2 = null;
         var3 = var2;
      } finally {
         this.lockCompileTask.unlock();
      }

      return (ChunkCompileTaskGenerator)var3;
   }

   public ChunkCompileTaskGenerator makeCompileTaskChunk() {
      this.lockCompileTask.lock();

      ChunkCompileTaskGenerator var1;
      try {
         this.finishCompileTask();
         this.compileTask = new ChunkCompileTaskGenerator(this, ChunkCompileTaskGenerator.Type.REBUILD_CHUNK);
         var1 = this.compileTask;
      } finally {
         this.lockCompileTask.unlock();
      }

      return var1;
   }

   public ReentrantLock getLockCompileTask() {
      return this.lockCompileTask;
   }

   @Override
   public String toString() {
      return "pos: " + this.getPosition() + ", frameIndex: " + this.frameIndex;
   }

   public boolean isPlayerUpdate() {
      return this.playerUpdate;
   }
}
