package net.minecraft.client.renderer.chunk;

import com.cheatbreaker.client.util.worldborder.WorldBorder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.crafting.RecipeBookCloning;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces;

public class ListedRenderChunk extends RenderChunk {
   public TileEntityPiston field_0003;
   public RecipeBookCloning field_0005;
   public WorldBorder field_0002;
   public Tuple field_0004;
   public StructureNetherBridgePieces field_0000;
   public int baseDisplayList = GLAllocation.generateDisplayLists(EnumWorldBlockLayer.values().length);

   public int getDisplayList(EnumWorldBlockLayer var1, CompiledChunk var2) {
      return !var2.isLayerEmpty(var1) ? this.baseDisplayList + var1.ordinal() : -1;
   }

   @Override
   public void deleteGlResources() {
      super.deleteGlResources();
      GLAllocation.deleteDisplayLists(this.baseDisplayList, EnumWorldBlockLayer.values().length);
   }

   public ListedRenderChunk(World var1, RenderGlobal var2, BlockPos var3, int var4) {
      super(var1, var2, var3, var4);
   }
}
