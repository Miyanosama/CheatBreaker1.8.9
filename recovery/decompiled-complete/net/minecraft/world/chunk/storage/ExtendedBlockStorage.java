package net.minecraft.world.chunk.storage;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$TreeNode;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.NibbleArray;
import net.optifine.reflect.Reflector;

public class ExtendedBlockStorage {
   public NibbleArray blocklightArray;
   public int yBase;
   public NibbleArray skylightArray;
   public int tickRefCount;
   public int blockRefCount;
   public ConcurrentHashMapV8$TreeNode field_0001;
   public char[] data;

   public void set(int var1, int var2, int var3, IBlockState var4) {
      if (Reflector.IExtendedBlockState.isInstance(var4)) {
         var4 = (IBlockState)Reflector.call(var4, Reflector.IExtendedBlockState_getClean);
      }

      IBlockState var5 = this.get(var1, var2, var3);
      Block var6 = var5.getBlock();
      Block var7 = var4.getBlock();
      if (var6 != Blocks.air) {
         this.blockRefCount--;
         if (var6.getTickRandomly()) {
            this.tickRefCount--;
         }
      }

      if (var7 != Blocks.air) {
         this.blockRefCount++;
         if (var7.getTickRandomly()) {
            this.tickRefCount++;
         }
      }

      this.data[var2 << 8 | var3 << 4 | var1] = (char)Block.BLOCK_STATE_IDS.get(var4);
   }

   public void setExtSkylightValue(int var1, int var2, int var3, int var4) {
      this.skylightArray.set(var1, var2, var3, var4);
   }

   public NibbleArray getBlocklightArray() {
      return this.blocklightArray;
   }

   public int getExtBlocklightValue(int var1, int var2, int var3) {
      return this.blocklightArray.get(var1, var2, var3);
   }

   public boolean isEmpty() {
      return this.blockRefCount == 0;
   }

   public NibbleArray getSkylightArray() {
      return this.skylightArray;
   }

   public Block getBlockByExtId(int var1, int var2, int var3) {
      return this.get(var1, var2, var3).getBlock();
   }

   public ExtendedBlockStorage(int var1, boolean var2) {
      this.yBase = var1;
      this.data = new char[4096];
      this.blocklightArray = new NibbleArray();
      if (var2) {
         this.skylightArray = new NibbleArray();
      }
   }

   public int getYLocation() {
      return this.yBase;
   }

   public char[] getData() {
      return this.data;
   }

   public void removeInvalidBlocks() {
      IBlockState var1 = Blocks.air.getDefaultState();
      int var2 = 0;
      int var3 = 0;

      for (int var4 = 0; var4 < 16; var4++) {
         for (int var5 = 0; var5 < 16; var5++) {
            for (int var6 = 0; var6 < 16; var6++) {
               Block var7 = this.getBlockByExtId(var6, var4, var5);
               if (var7 != Blocks.air) {
                  var2++;
                  if (var7.getTickRandomly()) {
                     var3++;
                  }
               }
            }
         }
      }

      this.blockRefCount = var2;
      this.tickRefCount = var3;
   }

   public void setBlocklightArray(NibbleArray var1) {
      this.blocklightArray = var1;
   }

   public IBlockState get(int var1, int var2, int var3) {
      IBlockState var4 = Block.BLOCK_STATE_IDS.getByValue(this.data[var2 << 8 | var3 << 4 | var1]);
      return var4 != null ? var4 : Blocks.air.getDefaultState();
   }

   public boolean getNeedsRandomTick() {
      return this.tickRefCount > 0;
   }

   public int getExtSkylightValue(int var1, int var2, int var3) {
      return this.skylightArray.get(var1, var2, var3);
   }

   public int getExtBlockMetadata(int var1, int var2, int var3) {
      IBlockState var4 = this.get(var1, var2, var3);
      return var4.getBlock().getMetaFromState(var4);
   }

   public void setData(char[] var1) {
      this.data = var1;
   }

   public void setSkylightArray(NibbleArray var1) {
      this.skylightArray = var1;
   }

   public void setExtBlocklightValue(int var1, int var2, int var3, int var4) {
      this.blocklightArray.set(var1, var2, var3, var4);
   }

   public int getBlockRefCount() {
      return this.blockRefCount;
   }
}
