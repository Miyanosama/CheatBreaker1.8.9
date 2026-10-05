package net.minecraft.block.state.pattern;

import com.google.common.base.Objects;
import com.google.common.cache.LoadingCache;
import io.netty.channel.group.ChannelGroupException;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.optifine.shaders.config.MacroState;

public class BlockPattern$PatternHelper {
   public int field_181122_g;
   public EnumFacing thumb;
   public ChannelGroupException field_0003;
   public BlockLeavesBase field_0006;
   public int field_181120_e;
   public MacroState field_0001;
   public int field_181121_f;
   public LoadingCache<BlockPos, BlockWorldState> lcache;
   public BlockPos pos;
   public EnumFacing finger;

   public BlockWorldState translateOffset(int var1, int var2, int var3) {
      return (BlockWorldState)this.lcache.getUnchecked(BlockPattern.translateOffset(this.pos, this.getFinger(), this.getThumb(), var1, var2, var3));
   }

   public BlockPos getPos() {
      return this.pos;
   }

   public EnumFacing getFinger() {
      return this.finger;
   }

   public BlockPattern$PatternHelper(
      BlockPos var1, EnumFacing var2, EnumFacing var3, LoadingCache<BlockPos, BlockWorldState> var4, int var5, int var6, int var7
   ) {
      this.pos = var1;
      this.finger = var2;
      this.thumb = var3;
      this.lcache = var4;
      this.field_181120_e = var5;
      this.field_181121_f = var6;
      this.field_181122_g = var7;
   }

   public EnumFacing getThumb() {
      return this.thumb;
   }

   public int func_181119_e() {
      return this.field_181121_f;
   }

   public int func_181118_d() {
      return this.field_181120_e;
   }

   @Override
   public String toString() {
      return Objects.toStringHelper(this).add("up", this.thumb).add("forwards", this.finger).add("frontTopLeft", this.pos).toString();
   }
}
