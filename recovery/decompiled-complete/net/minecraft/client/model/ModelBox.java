package net.minecraft.client.model;

import net.minecraft.client.renderer.WorldRenderer;
import recovered.unidentified.UnidentifiedClass0144;
import recovered.unidentified.UnidentifiedClass4157;

public class ModelBox {
   public TexturedQuad[] quadList;
   public PositionTextureVertex[] vertexPositions;
   public float posZ1;
   public float posX1;
   public float posZ2;
   public float posY2;
   public float posY1;
   public UnidentifiedClass4157 field_0006;
   public String boxName;
   public float posX2;
   public UnidentifiedClass0144 field_0000;

   public void render(WorldRenderer var1, float var2) {
      for (int var3 = 0; var3 < this.quadList.length; var3++) {
         TexturedQuad var4 = this.quadList[var3];
         if (var4 != null) {
            var4.draw(var1, var2);
         }
      }
   }

   public TexturedQuad makeTexturedQuad(PositionTextureVertex[] var1, int[] var2, boolean var3, float var4, float var5) {
      return var2 == null
         ? null
         : (
            var3
               ? new TexturedQuad(var1, var2[2], var2[3], var2[0], var2[1], var4, var5)
               : new TexturedQuad(var1, var2[0], var2[1], var2[2], var2[3], var4, var5)
         );
   }

   public ModelBox(ModelRenderer var1, int var2, int var3, float var4, float var5, float var6, int var7, int var8, int var9, float var10, boolean var11) {
      this.posX1 = var4;
      this.posY1 = var5;
      this.posZ1 = var6;
      this.posX2 = var4 + var7;
      this.posY2 = var5 + var8;
      this.posZ2 = var6 + var9;
      this.vertexPositions = new PositionTextureVertex[8];
      this.quadList = new TexturedQuad[6];
      float var12 = var4 + var7;
      float var13 = var5 + var8;
      float var14 = var6 + var9;
      var4 -= var10;
      var5 -= var10;
      var6 -= var10;
      var12 += var10;
      var13 += var10;
      var14 += var10;
      if (var11) {
         float var15 = var12;
         var12 = var4;
         var4 = var15;
      }

      PositionTextureVertex var30 = new PositionTextureVertex(var4, var5, var6, 0.0F, 0.0F);
      PositionTextureVertex var16 = new PositionTextureVertex(var12, var5, var6, 0.0F, 8.0F);
      PositionTextureVertex var17 = new PositionTextureVertex(var12, var13, var6, 8.0F, 8.0F);
      PositionTextureVertex var18 = new PositionTextureVertex(var4, var13, var6, 8.0F, 0.0F);
      PositionTextureVertex var19 = new PositionTextureVertex(var4, var5, var14, 0.0F, 0.0F);
      PositionTextureVertex var20 = new PositionTextureVertex(var12, var5, var14, 0.0F, 8.0F);
      PositionTextureVertex var21 = new PositionTextureVertex(var12, var13, var14, 8.0F, 8.0F);
      PositionTextureVertex var22 = new PositionTextureVertex(var4, var13, var14, 8.0F, 0.0F);
      this.vertexPositions[0] = var30;
      this.vertexPositions[1] = var16;
      this.vertexPositions[2] = var17;
      this.vertexPositions[3] = var18;
      this.vertexPositions[4] = var19;
      this.vertexPositions[5] = var20;
      this.vertexPositions[6] = var21;
      this.vertexPositions[7] = var22;
      this.quadList[0] = new TexturedQuad(
         new PositionTextureVertex[]{var20, var16, var17, var21},
         var2 + var9 + var7,
         var3 + var9,
         var2 + var9 + var7 + var9,
         var3 + var9 + var8,
         var1.textureWidth,
         var1.textureHeight
      );
      this.quadList[1] = new TexturedQuad(
         new PositionTextureVertex[]{var30, var19, var22, var18}, var2, var3 + var9, var2 + var9, var3 + var9 + var8, var1.textureWidth, var1.textureHeight
      );
      this.quadList[2] = new TexturedQuad(
         new PositionTextureVertex[]{var20, var19, var30, var16}, var2 + var9, var3, var2 + var9 + var7, var3 + var9, var1.textureWidth, var1.textureHeight
      );
      this.quadList[3] = new TexturedQuad(
         new PositionTextureVertex[]{var17, var18, var22, var21},
         var2 + var9 + var7,
         var3 + var9,
         var2 + var9 + var7 + var7,
         var3,
         var1.textureWidth,
         var1.textureHeight
      );
      this.quadList[4] = new TexturedQuad(
         new PositionTextureVertex[]{var16, var30, var18, var17},
         var2 + var9,
         var3 + var9,
         var2 + var9 + var7,
         var3 + var9 + var8,
         var1.textureWidth,
         var1.textureHeight
      );
      this.quadList[5] = new TexturedQuad(
         new PositionTextureVertex[]{var19, var20, var21, var22},
         var2 + var9 + var7 + var9,
         var3 + var9,
         var2 + var9 + var7 + var9 + var7,
         var3 + var9 + var8,
         var1.textureWidth,
         var1.textureHeight
      );
      if (var11) {
         for (int var23 = 0; var23 < this.quadList.length; var23++) {
            this.quadList[var23].flipFace();
         }
      }
   }

   public ModelBox setBoxName(String var1) {
      this.boxName = var1;
      return this;
   }

   public ModelBox(ModelRenderer var1, int[][] var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, boolean var10) {
      this.posX1 = var3;
      this.posY1 = var4;
      this.posZ1 = var5;
      this.posX2 = var3 + var6;
      this.posY2 = var4 + var7;
      this.posZ2 = var5 + var8;
      this.vertexPositions = new PositionTextureVertex[8];
      this.quadList = new TexturedQuad[6];
      float var11 = var3 + var6;
      float var12 = var4 + var7;
      float var13 = var5 + var8;
      var3 -= var9;
      var4 -= var9;
      var5 -= var9;
      var11 += var9;
      var12 += var9;
      var13 += var9;
      if (var10) {
         float var14 = var11;
         var11 = var3;
         var3 = var14;
      }

      PositionTextureVertex var32 = new PositionTextureVertex(var3, var4, var5, 0.0F, 0.0F);
      PositionTextureVertex var15 = new PositionTextureVertex(var11, var4, var5, 0.0F, 8.0F);
      PositionTextureVertex var16 = new PositionTextureVertex(var11, var12, var5, 8.0F, 8.0F);
      PositionTextureVertex var17 = new PositionTextureVertex(var3, var12, var5, 8.0F, 0.0F);
      PositionTextureVertex var18 = new PositionTextureVertex(var3, var4, var13, 0.0F, 0.0F);
      PositionTextureVertex var19 = new PositionTextureVertex(var11, var4, var13, 0.0F, 8.0F);
      PositionTextureVertex var20 = new PositionTextureVertex(var11, var12, var13, 8.0F, 8.0F);
      PositionTextureVertex var21 = new PositionTextureVertex(var3, var12, var13, 8.0F, 0.0F);
      this.vertexPositions[0] = var32;
      this.vertexPositions[1] = var15;
      this.vertexPositions[2] = var16;
      this.vertexPositions[3] = var17;
      this.vertexPositions[4] = var18;
      this.vertexPositions[5] = var19;
      this.vertexPositions[6] = var20;
      this.vertexPositions[7] = var21;
      this.quadList[0] = this.makeTexturedQuad(new PositionTextureVertex[]{var19, var15, var16, var20}, var2[4], false, var1.textureWidth, var1.textureHeight);
      this.quadList[1] = this.makeTexturedQuad(new PositionTextureVertex[]{var32, var18, var21, var17}, var2[5], false, var1.textureWidth, var1.textureHeight);
      this.quadList[2] = this.makeTexturedQuad(new PositionTextureVertex[]{var19, var18, var32, var15}, var2[1], true, var1.textureWidth, var1.textureHeight);
      this.quadList[3] = this.makeTexturedQuad(new PositionTextureVertex[]{var16, var17, var21, var20}, var2[0], true, var1.textureWidth, var1.textureHeight);
      this.quadList[4] = this.makeTexturedQuad(new PositionTextureVertex[]{var15, var32, var17, var16}, var2[2], false, var1.textureWidth, var1.textureHeight);
      this.quadList[5] = this.makeTexturedQuad(new PositionTextureVertex[]{var18, var19, var20, var21}, var2[3], false, var1.textureWidth, var1.textureHeight);
      if (var10) {
         for (TexturedQuad var25 : this.quadList) {
            var25.flipFace();
         }
      }
   }

   public ModelBox(ModelRenderer var1, int var2, int var3, float var4, float var5, float var6, int var7, int var8, int var9, float var10) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var1.mirror);
   }
}
