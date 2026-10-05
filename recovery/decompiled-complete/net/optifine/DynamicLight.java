package net.optifine;

import io.netty.channel.sctp.oio.OioSctpChannel;
import io.netty.handler.codec.socks.SocksAuthResponseDecoder$1;
import io.netty.util.concurrent.FastThreadLocalThread;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.optifine.render.GlBlendState;

public class DynamicLight {
   public FastThreadLocalThread field_0006;
   public SocksAuthResponseDecoder$1 field_0011;
   public double field_0005;
   public Set<BlockPos> setLitChunkPos;
   public double field_0001;
   public long timeCheckMs;
   public double offsetY;
   public OioSctpChannel field_0009;
   public boolean underwater;
   public Entity entity = null;
   public BlockPos$MutableBlockPos blockPosMutable;
   public double field_0007;
   public int lastLightLevel;
   public GlBlendState field_0004;

   public Entity getEntity() {
      return this.entity;
   }

   public boolean isUnderwater() {
      return this.underwater;
   }

   @Override
   public String toString() {
      return "Entity: " + this.entity + ", offsetY: " + this.offsetY;
   }

   public double method_08349() {
      return this.field_0005;
   }

   public double method_08354() {
      return this.field_0001;
   }

   public BlockPos getChunkPos(RenderChunk var1, BlockPos var2, EnumFacing var3) {
      return var1 != null ? var1.getBlockPosOffset16(var3) : var2.a(var3, 16);
   }

   public void updateChunkLight(RenderChunk var1, Set<BlockPos> var2, Set<BlockPos> var3) {
      if (var1 != null) {
         CompiledChunk var4 = var1.getCompiledChunk();
         if (var4 != null && !var4.isEmpty()) {
            var1.setNeedsUpdate(true);
         }

         BlockPos var5 = var1.getPosition();
         if (var2 != null) {
            var2.remove(var5);
         }

         if (var3 != null) {
            var3.add(var5);
         }
      }
   }

   public DynamicLight(Entity var1) {
      this.offsetY = 0.0;
      this.field_0007 = -2.1474836E9F;
      this.field_0001 = -2.1474836E9F;
      this.field_0005 = -2.1474836E9F;
      this.lastLightLevel = 0;
      this.underwater = false;
      this.timeCheckMs = -4747516630881665016L & 4747516630271027475L;
      this.setLitChunkPos = new HashSet<>();
      this.blockPosMutable = new BlockPos$MutableBlockPos();
      this.entity = var1;
      this.offsetY = var1.getEyeHeight();
   }

   public double getOffsetY() {
      return this.offsetY;
   }

   public int getLastLightLevel() {
      return this.lastLightLevel;
   }

   public void updateLitChunks(RenderGlobal var1) {
      for (BlockPos var3 : this.setLitChunkPos) {
         RenderChunk var4 = var1.getRenderChunk(var3);
         this.updateChunkLight(var4, (Set<BlockPos>)null, (Set<BlockPos>)null);
      }
   }

   public double method_08357() {
      return this.field_0007;
   }

   public void method_08356(RenderGlobal var1) {
      if (Config.isDynamicLightsFast()) {
         long var2 = System.currentTimeMillis();
         if (var2 < this.timeCheckMs + (927089148L & 1085800948L)) {
            return;
         }

         this.timeCheckMs = var2;
      }

      double var38 = this.entity.s - 0.5;
      double var4 = this.entity.t - 0.5 + this.offsetY;
      double var6 = this.entity.u - 0.5;
      int var8 = DynamicLights.getLightLevel(this.entity);
      double var9 = var38 - this.field_0007;
      double var11 = var4 - this.field_0001;
      double var13 = var6 - this.field_0005;
      double var15 = 0.1;
      if (Math.abs(var9) > var15 || Math.abs(var11) > var15 || Math.abs(var13) > var15 || this.lastLightLevel != var8) {
         this.field_0007 = var38;
         this.field_0001 = var4;
         this.field_0005 = var6;
         this.lastLightLevel = var8;
         this.underwater = false;
         WorldClient var17 = var1.getWorld();
         if (var17 != null) {
            this.blockPosMutable.set(MathHelper.floor_double(var38), MathHelper.floor_double(var4), MathHelper.floor_double(var6));
            IBlockState var18 = var17.getBlockState(this.blockPosMutable);
            Block var19 = var18.getBlock();
            this.underwater = var19 == Blocks.water;
         }

         HashSet var39 = new HashSet();
         if (var8 > 0) {
            EnumFacing var40 = (MathHelper.floor_double(var38) & 15) >= 8 ? EnumFacing.EAST : EnumFacing.WEST;
            EnumFacing var20 = (MathHelper.floor_double(var4) & 15) >= 8 ? EnumFacing.UP : EnumFacing.DOWN;
            EnumFacing var21 = (MathHelper.floor_double(var6) & 15) >= 8 ? EnumFacing.SOUTH : EnumFacing.NORTH;
            BlockPos var22 = new BlockPos(var38, var4, var6);
            RenderChunk var23 = var1.getRenderChunk(var22);
            BlockPos var24 = this.getChunkPos(var23, var22, var40);
            RenderChunk var25 = var1.getRenderChunk(var24);
            BlockPos var26 = this.getChunkPos(var23, var22, var21);
            RenderChunk var27 = var1.getRenderChunk(var26);
            BlockPos var28 = this.getChunkPos(var25, var24, var21);
            RenderChunk var29 = var1.getRenderChunk(var28);
            BlockPos var30 = this.getChunkPos(var23, var22, var20);
            RenderChunk var31 = var1.getRenderChunk(var30);
            BlockPos var32 = this.getChunkPos(var31, var30, var40);
            RenderChunk var33 = var1.getRenderChunk(var32);
            BlockPos var34 = this.getChunkPos(var31, var30, var21);
            RenderChunk var35 = var1.getRenderChunk(var34);
            BlockPos var36 = this.getChunkPos(var33, var32, var21);
            RenderChunk var37 = var1.getRenderChunk(var36);
            this.updateChunkLight(var23, this.setLitChunkPos, var39);
            this.updateChunkLight(var25, this.setLitChunkPos, var39);
            this.updateChunkLight(var27, this.setLitChunkPos, var39);
            this.updateChunkLight(var29, this.setLitChunkPos, var39);
            this.updateChunkLight(var31, this.setLitChunkPos, var39);
            this.updateChunkLight(var33, this.setLitChunkPos, var39);
            this.updateChunkLight(var35, this.setLitChunkPos, var39);
            this.updateChunkLight(var37, this.setLitChunkPos, var39);
         }

         this.updateLitChunks(var1);
         this.setLitChunkPos = var39;
      }
   }
}
