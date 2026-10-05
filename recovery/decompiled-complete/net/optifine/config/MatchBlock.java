package net.optifine.config;

import javazoom.jl.converter.WaveFile$WaveFormat_Chunk;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.client.particle.EntitySnowShovelFX$Factory;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.item.ItemRecord;
import net.minecraft.src.Config;
import org.java_websocket.exceptions.IncompleteException;

public class MatchBlock {
   public EntitySnowShovelFX$Factory field_0003;
   public int blockId = -1;
   public BlockRendererDispatcher field_0002;
   public ItemRecord field_0004;
   public IncompleteException field_0000;
   public WaveFile$WaveFormat_Chunk field_0001;
   public int[] metadatas = null;

   public boolean matches(int var1, int var2) {
      return var1 != this.blockId ? false : Matches.metadata(var2, this.metadatas);
   }

   public MatchBlock(int var1, int[] var2) {
      this.blockId = var1;
      this.metadatas = var2;
   }

   public boolean matches(BlockStateBase var1) {
      return var1.getBlockId() != this.blockId ? false : Matches.metadata(var1.getMetadata(), this.metadatas);
   }

   public MatchBlock(int var1) {
      this.blockId = var1;
   }

   public int[] getMetadatas() {
      return this.metadatas;
   }

   public void addMetadata(int var1) {
      if (this.metadatas != null && var1 >= 0 && var1 <= 15) {
         for (int var2 = 0; var2 < this.metadatas.length; var2++) {
            if (this.metadatas[var2] == var1) {
               return;
            }
         }

         this.metadatas = Config.addIntToArray(this.metadatas, var1);
      }
   }

   public int getBlockId() {
      return this.blockId;
   }

   public MatchBlock(int var1, int var2) {
      this.blockId = var1;
      if (var2 >= 0 && var2 <= 15) {
         this.metadatas = new int[]{var2};
      }
   }

   @Override
   public String toString() {
      return "" + this.blockId + ":" + Config.arrayToString(this.metadatas);
   }
}
