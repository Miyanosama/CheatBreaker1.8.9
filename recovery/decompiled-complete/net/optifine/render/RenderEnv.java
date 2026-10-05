package net.optifine.render;

import com.cheatbreaker.client.util.title.Title$TitleType;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelRenderer$AmbientOcclusionFace;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.BlockPosM;
import net.optifine.model.ListQuadsOverlay;

public class RenderEnv {
   public BakedQuad[] arrayQuadsCtm2;
   public RegionRenderCacheBuilder regionRenderCacheBuilder;
   public C19PacketResourcePackStatus field_0012;
   public int breakingAnimation;
   public BakedQuad[] arrayQuadsCtm1;
   public static int field_0007;
   public int metadata;
   public boolean[] borderFlags2;
   public List<BakedQuad> listQuadsCustomizer;
   public static int field_0027;
   public float[] quadBounds;
   public BlockModelRenderer$AmbientOcclusionFace aoFace;
   public List<BakedQuad> listQuadsCtmMultipass;
   public BlockPos blockPos;
   public static int field_0020;
   public ListQuadsOverlay[] listsQuadsOverlay;
   public int smartLeaves;
   public Title$TitleType field_0009;
   public IBlockState blockState;
   public BlockPosM colorizerBlockPosM;
   public boolean[] borderFlags3;
   public boolean overlaysRendered;
   public BakedQuad[] arrayQuadsCtm3;
   public boolean[] borderFlags;
   public BakedQuad[] arrayQuadsCtm4;
   public EnumFacing[] borderDirections;
   public BitSet boundsFlags;
   public int blockId = -1;

   public RegionRenderCacheBuilder getRegionRenderCacheBuilder() {
      return this.regionRenderCacheBuilder;
   }

   public EnumFacing[] getBorderDirections() {
      if (this.borderDirections == null) {
         this.borderDirections = new EnumFacing[4];
      }

      return this.borderDirections;
   }

   public int getBlockId() {
      if (this.blockId < 0) {
         if (this.blockState instanceof BlockStateBase) {
            BlockStateBase var1 = (BlockStateBase)this.blockState;
            this.blockId = var1.getBlockId();
         } else {
            this.blockId = Block.getIdFromBlock(this.blockState.getBlock());
         }
      }

      return this.blockId;
   }

   public boolean isSmartLeaves() {
      if (this.smartLeaves == -1) {
         if (Config.isTreesSmart() && this.blockState.getBlock() instanceof BlockLeaves) {
            this.smartLeaves = 1;
         } else {
            this.smartLeaves = 0;
         }
      }

      return this.smartLeaves == 1;
   }

   public int getMetadata() {
      if (this.metadata < 0) {
         if (this.blockState instanceof BlockStateBase) {
            BlockStateBase var1 = (BlockStateBase)this.blockState;
            this.metadata = var1.getMetadata();
         } else {
            this.metadata = this.blockState.getBlock().getMetaFromState(this.blockState);
         }
      }

      return this.metadata;
   }

   public boolean[] getBorderFlags3() {
      if (this.borderFlags3 == null) {
         this.borderFlags3 = new boolean[4];
      }

      return this.borderFlags3;
   }

   public boolean[] getBorderFlags2() {
      if (this.borderFlags2 == null) {
         this.borderFlags2 = new boolean[4];
      }

      return this.borderFlags2;
   }

   public void setOverlaysRendered(boolean var1) {
      this.overlaysRendered = var1;
   }

   public void setRegionRenderCacheBuilder(RegionRenderCacheBuilder var1) {
      this.regionRenderCacheBuilder = var1;
   }

   public float[] getQuadBounds() {
      return this.quadBounds;
   }

   public boolean isBreakingAnimation(List var1) {
      if (this.breakingAnimation == -1 && var1.size() > 0) {
         if (var1.get(0) instanceof BreakingFour) {
            this.breakingAnimation = 1;
         } else {
            this.breakingAnimation = 0;
         }
      }

      return this.breakingAnimation == 1;
   }

   public RenderEnv(IBlockState var1, BlockPos var2) {
      this.metadata = -1;
      this.breakingAnimation = -1;
      this.smartLeaves = -1;
      this.quadBounds = new float[EnumFacing.VALUES.length * 2];
      this.boundsFlags = new BitSet(3);
      this.aoFace = new BlockModelRenderer$AmbientOcclusionFace();
      this.colorizerBlockPosM = null;
      this.borderFlags = null;
      this.borderFlags2 = null;
      this.borderFlags3 = null;
      this.borderDirections = null;
      this.listQuadsCustomizer = new ArrayList<>();
      this.listQuadsCtmMultipass = new ArrayList<>();
      this.arrayQuadsCtm1 = new BakedQuad[1];
      this.arrayQuadsCtm2 = new BakedQuad[2];
      this.arrayQuadsCtm3 = new BakedQuad[3];
      this.arrayQuadsCtm4 = new BakedQuad[4];
      this.regionRenderCacheBuilder = null;
      this.listsQuadsOverlay = new ListQuadsOverlay[EnumWorldBlockLayer.values().length];
      this.overlaysRendered = false;
      this.blockState = var1;
      this.blockPos = var2;
   }

   public EnumFacing[] getBorderDirections(EnumFacing var1, EnumFacing var2, EnumFacing var3, EnumFacing var4) {
      EnumFacing[] var5 = this.getBorderDirections();
      var5[0] = var1;
      var5[1] = var2;
      var5[2] = var3;
      var5[3] = var4;
      return var5;
   }

   public boolean isBreakingAnimation() {
      return this.breakingAnimation == 1;
   }

   public boolean[] getBorderFlags() {
      if (this.borderFlags == null) {
         this.borderFlags = new boolean[4];
      }

      return this.borderFlags;
   }

   public BakedQuad[] getArrayQuadsCtm(BakedQuad var1, BakedQuad var2, BakedQuad var3, BakedQuad var4) {
      this.arrayQuadsCtm4[0] = var1;
      this.arrayQuadsCtm4[1] = var2;
      this.arrayQuadsCtm4[2] = var3;
      this.arrayQuadsCtm4[3] = var4;
      return this.arrayQuadsCtm4;
   }

   public ListQuadsOverlay getListQuadsOverlay(EnumWorldBlockLayer var1) {
      ListQuadsOverlay var2 = this.listsQuadsOverlay[var1.ordinal()];
      if (var2 == null) {
         var2 = new ListQuadsOverlay();
         this.listsQuadsOverlay[var1.ordinal()] = var2;
      }

      return var2;
   }

   public BakedQuad[] getArrayQuadsCtm(BakedQuad var1, BakedQuad var2, BakedQuad var3) {
      this.arrayQuadsCtm3[0] = var1;
      this.arrayQuadsCtm3[1] = var2;
      this.arrayQuadsCtm3[2] = var3;
      return this.arrayQuadsCtm3;
   }

   public IBlockState getBlockState() {
      return this.blockState;
   }

   public boolean isOverlaysRendered() {
      return this.overlaysRendered;
   }

   public List<BakedQuad> getListQuadsCustomizer() {
      return this.listQuadsCustomizer;
   }

   public List<BakedQuad> getListQuadsCtmMultipass(BakedQuad[] var1) {
      this.listQuadsCtmMultipass.clear();
      if (var1 != null) {
         for (int var2 = 0; var2 < var1.length; var2++) {
            BakedQuad var3 = var1[var2];
            this.listQuadsCtmMultipass.add(var3);
         }
      }

      return this.listQuadsCtmMultipass;
   }

   public BlockModelRenderer$AmbientOcclusionFace getAoFace() {
      return this.aoFace;
   }

   public BitSet getBoundsFlags() {
      return this.boundsFlags;
   }

   public void reset(IBlockState var1, BlockPos var2) {
      if (this.blockState != var1 || this.blockPos != var2) {
         this.blockState = var1;
         this.blockPos = var2;
         this.blockId = -1;
         this.metadata = -1;
         this.breakingAnimation = -1;
         this.smartLeaves = -1;
         this.boundsFlags.clear();
      }
   }

   public BlockPosM getColorizerBlockPosM() {
      if (this.colorizerBlockPosM == null) {
         this.colorizerBlockPosM = new BlockPosM(0, 0, 0);
      }

      return this.colorizerBlockPosM;
   }

   public boolean isBreakingAnimation(BakedQuad var1) {
      if (this.breakingAnimation < 0) {
         if (var1 instanceof BreakingFour) {
            this.breakingAnimation = 1;
         } else {
            this.breakingAnimation = 0;
         }
      }

      return this.breakingAnimation == 1;
   }

   public BakedQuad[] getArrayQuadsCtm(BakedQuad var1) {
      this.arrayQuadsCtm1[0] = var1;
      return this.arrayQuadsCtm1;
   }

   public BakedQuad[] getArrayQuadsCtm(BakedQuad var1, BakedQuad var2) {
      this.arrayQuadsCtm2[0] = var1;
      this.arrayQuadsCtm2[1] = var2;
      return this.arrayQuadsCtm2;
   }
}
