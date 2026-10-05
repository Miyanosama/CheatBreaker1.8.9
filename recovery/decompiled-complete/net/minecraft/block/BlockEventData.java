package net.minecraft.block;

import io.netty.channel.local.LocalChannel$2;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Deserializer;
import net.minecraft.item.crafting.RecipesArmor;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.BlockPos;

public class BlockEventData {
   public Block blockType;
   public RecipesArmor field_0007;
   public BlockPos position;
   public ShapedRecipes field_0006;
   public LocalChannel$2 field_0000;
   public BlockSilverfish$EnumType$4 field_0001;
   public int eventParameter;
   public ModelBlockDefinition$Deserializer field_0005;
   public int eventID;

   public Block getBlock() {
      return this.blockType;
   }

   public int getEventID() {
      return this.eventID;
   }

   public int getEventParameter() {
      return this.eventParameter;
   }

   @Override
   public String toString() {
      return "TE(" + this.position + ")," + this.eventID + "," + this.eventParameter + "," + this.blockType;
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof BlockEventData)) {
         return false;
      } else {
         BlockEventData var2 = (BlockEventData)var1;
         return this.position.equals(var2.position)
            && this.eventID == var2.eventID
            && this.eventParameter == var2.eventParameter
            && this.blockType == var2.blockType;
      }
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public BlockEventData(BlockPos var1, Block var2, int var3, int var4) {
      this.position = var1;
      this.eventID = var3;
      this.eventParameter = var4;
      this.blockType = var2;
   }
}
