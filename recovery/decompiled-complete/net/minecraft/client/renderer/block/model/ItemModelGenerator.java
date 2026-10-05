package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity$2;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Vector3f;

public class ItemModelGenerator {
   public static List<String> LAYERS = Lists.newArrayList(new String[]{"layer0", "layer1", "layer2", "layer3", "layer4"});
   public Entity$2 field_0001;

   public List<ItemModelGenerator$Span> func_178393_a(TextureAtlasSprite var1) {
      int var2 = var1.getIconWidth();
      int var3 = var1.getIconHeight();
      ArrayList var4 = Lists.newArrayList();

      for (int var5 = 0; var5 < var1.getFrameCount(); var5++) {
         int[] var6 = var1.getFrameTextureData(var5)[0];

         for (int var7 = 0; var7 < var3; var7++) {
            for (int var8 = 0; var8 < var2; var8++) {
               boolean var9 = !this.func_178391_a(var6, var8, var7, var2, var3);
               this.func_178396_a(ItemModelGenerator$SpanFacing.UP, var4, var6, var8, var7, var2, var3, var9);
               this.func_178396_a(ItemModelGenerator$SpanFacing.DOWN, var4, var6, var8, var7, var2, var3, var9);
               this.func_178396_a(ItemModelGenerator$SpanFacing.LEFT, var4, var6, var8, var7, var2, var3, var9);
               this.func_178396_a(ItemModelGenerator$SpanFacing.RIGHT, var4, var6, var8, var7, var2, var3, var9);
            }
         }
      }

      return var4;
   }

   public ModelBlock makeItemModel(TextureMap var1, ModelBlock var2) {
      HashMap var3 = Maps.newHashMap();
      ArrayList var4 = Lists.newArrayList();

      for (int var5 = 0; var5 < LAYERS.size(); var5++) {
         String var6 = LAYERS.get(var5);
         if (!var2.isTexturePresent(var6)) {
            break;
         }

         String var7 = var2.resolveTextureName(var6);
         var3.put(var6, var7);
         TextureAtlasSprite var8 = var1.getAtlasSprite(new ResourceLocation(var7).toString());
         var4.addAll(this.func_178394_a(var5, var6, var8));
      }

      if (var4.isEmpty()) {
         return null;
      } else {
         var3.put("particle", var2.isTexturePresent("particle") ? var2.resolveTextureName("particle") : (String)var3.get("layer0"));
         return new ModelBlock(var4, var3, false, false, var2.getAllTransforms());
      }
   }

   public void func_178395_a(List<ItemModelGenerator$Span> var1, ItemModelGenerator$SpanFacing var2, int var3, int var4) {
      ItemModelGenerator$Span var5 = null;

      for (ItemModelGenerator$Span var7 : var1) {
         if (var7.func_178383_a() == var2) {
            int var8 = ItemModelGenerator$SpanFacing.access$000(var2) ? var4 : var3;
            if (var7.func_178381_d() == var8) {
               var5 = var7;
               break;
            }
         }
      }

      int var9 = ItemModelGenerator$SpanFacing.access$000(var2) ? var4 : var3;
      int var10 = ItemModelGenerator$SpanFacing.access$000(var2) ? var3 : var4;
      if (var5 == null) {
         var1.add(new ItemModelGenerator$Span(var2, var10, var9));
      } else {
         var5.func_178382_a(var10);
      }
   }

   public List<BlockPart> func_178394_a(int var1, String var2, TextureAtlasSprite var3) {
      HashMap var4 = Maps.newHashMap();
      var4.put(EnumFacing.SOUTH, new BlockPartFace((EnumFacing)null, var1, var2, new BlockFaceUV(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0)));
      var4.put(EnumFacing.NORTH, new BlockPartFace((EnumFacing)null, var1, var2, new BlockFaceUV(new float[]{16.0F, 0.0F, 0.0F, 16.0F}, 0)));
      ArrayList var5 = Lists.newArrayList();
      var5.add(new BlockPart(new Vector3f(0.0F, 0.0F, 7.5F), new Vector3f(16.0F, 16.0F, 8.5F), var4, (BlockPartRotation)null, true));
      var5.addAll(this.func_178397_a(var3, var2, var1));
      return var5;
   }

   public boolean func_178391_a(int[] var1, int var2, int var3, int var4, int var5) {
      return var2 >= 0 && var3 >= 0 && var2 < var4 && var3 < var5 ? (var1[var3 * var4 + var2] >> 24 & 0xFF) == 0 : true;
   }

   public List<BlockPart> func_178397_a(TextureAtlasSprite var1, String var2, int var3) {
      float var4 = var1.getIconWidth();
      float var5 = var1.getIconHeight();
      ArrayList var6 = Lists.newArrayList();

      for (ItemModelGenerator$Span var8 : this.func_178393_a(var1)) {
         float var9 = 0.0F;
         float var10 = 0.0F;
         float var11 = 0.0F;
         float var12 = 0.0F;
         float var13 = 0.0F;
         float var14 = 0.0F;
         float var15 = 0.0F;
         float var16 = 0.0F;
         float var17 = 0.0F;
         float var18 = 0.0F;
         float var19 = var8.func_178385_b();
         float var20 = var8.func_178384_c();
         float var21 = var8.func_178381_d();
         ItemModelGenerator$SpanFacing var22 = var8.func_178383_a();
         switch (ItemModelGenerator$1.field_0003[var22.ordinal()]) {
            case 1:
               var13 = var19;
               var9 = var19;
               var11 = var14 = var20 + 1.0F;
               var15 = var21;
               var10 = var21;
               var16 = var21;
               var12 = var21;
               var17 = 16.0F / var4;
               var18 = 16.0F / (var5 - 1.0F);
               break;
            case 2:
               var16 = var21;
               var15 = var21;
               var13 = var19;
               var9 = var19;
               var11 = var14 = var20 + 1.0F;
               var10 = var21 + 1.0F;
               var12 = var21 + 1.0F;
               var17 = 16.0F / var4;
               var18 = 16.0F / (var5 - 1.0F);
               break;
            case 3:
               var13 = var21;
               var9 = var21;
               var14 = var21;
               var11 = var21;
               var16 = var19;
               var10 = var19;
               var12 = var15 = var20 + 1.0F;
               var17 = 16.0F / (var4 - 1.0F);
               var18 = 16.0F / var5;
               break;
            case 4:
               var14 = var21;
               var13 = var21;
               var9 = var21 + 1.0F;
               var11 = var21 + 1.0F;
               var16 = var19;
               var10 = var19;
               var12 = var15 = var20 + 1.0F;
               var17 = 16.0F / (var4 - 1.0F);
               var18 = 16.0F / var5;
         }

         float var23 = 16.0F / var4;
         float var24 = 16.0F / var5;
         var9 *= var23;
         var11 *= var23;
         var10 *= var24;
         var12 *= var24;
         var10 = 16.0F - var10;
         var12 = 16.0F - var12;
         var13 *= var17;
         var14 *= var17;
         var15 *= var18;
         var16 *= var18;
         HashMap var25 = Maps.newHashMap();
         var25.put(var22.getFacing(), new BlockPartFace((EnumFacing)null, var3, var2, new BlockFaceUV(new float[]{var13, var15, var14, var16}, 0)));
         switch (ItemModelGenerator$1.field_0003[var22.ordinal()]) {
            case 1:
               var6.add(new BlockPart(new Vector3f(var9, var10, 7.5F), new Vector3f(var11, var10, 8.5F), var25, (BlockPartRotation)null, true));
               break;
            case 2:
               var6.add(new BlockPart(new Vector3f(var9, var12, 7.5F), new Vector3f(var11, var12, 8.5F), var25, (BlockPartRotation)null, true));
               break;
            case 3:
               var6.add(new BlockPart(new Vector3f(var9, var10, 7.5F), new Vector3f(var9, var12, 8.5F), var25, (BlockPartRotation)null, true));
               break;
            case 4:
               var6.add(new BlockPart(new Vector3f(var11, var10, 7.5F), new Vector3f(var11, var12, 8.5F), var25, (BlockPartRotation)null, true));
         }
      }

      return var6;
   }

   public void func_178396_a(
      ItemModelGenerator$SpanFacing var1, List<ItemModelGenerator$Span> var2, int[] var3, int var4, int var5, int var6, int var7, boolean var8
   ) {
      boolean var9 = this.func_178391_a(var3, var4 + var1.func_178372_b(), var5 + var1.func_178371_c(), var6, var7) && var8;
      if (var9) {
         this.func_178395_a(var2, var1, var4, var5);
      }
   }
}
