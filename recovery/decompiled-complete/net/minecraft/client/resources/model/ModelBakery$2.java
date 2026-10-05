package net.minecraft.client.resources.model;

import java.util.Set;
import net.minecraft.client.renderer.texture.IIconCreator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntitySign$2;
import net.minecraft.util.ResourceLocation;

public class ModelBakery$2 implements IIconCreator {
   public TileEntitySign$2 field_0002;

   @Override
   public void registerSprites(TextureMap var1) {
      for (ResourceLocation var3 : this.val$set) {
         TextureAtlasSprite var4 = var1.registerSprite(var3);
         ModelBakery.access$000(this.this$0).put(var3, var4);
      }
   }

   public ModelBakery$2(ModelBakery var1, Set var2) {
      this.this$0 = var1;
      this.val$set = var2;
      super();
   }
}
