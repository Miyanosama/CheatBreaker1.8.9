package net.minecraft.client.model;

import com.google.common.collect.Lists;
import io.netty.channel.DefaultChannelPipeline$1;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.client.network.NetHandlerLoginClient$1;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.anim.ModelUpdater;
import net.optifine.model.ModelSprite;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class ModelRenderer {
   public float offsetY;
   public float rotationPointY;
   public float rotationPointX;
   public int textureOffsetY;
   public String id;
   public float rotateAngleX;
   public float offsetZ;
   public float rotationPointZ;
   public String boxName;
   public float scaleZ;
   public float scaleX;
   public float textureHeight;
   public List<ModelBox> cubeList;
   public int displayList;
   public ResourceLocation textureLocation;
   public float textureWidth;
   public NetHandlerLoginClient$1 field_0003;
   public float scaleY;
   public boolean compiled;
   public int textureOffsetX;
   public boolean isHidden;
   public ModelUpdater modelUpdater;
   public int countResetDisplayList;
   public float rotateAngleY;
   public RenderGlobal renderGlobal;
   public MaterialTransparent field_0021;
   public boolean mirrorV;
   public boolean mirror;
   public DefaultChannelPipeline$1 field_0033;
   public ModelBase baseModel;
   public boolean showModel;
   public List<ModelRenderer> childModels;
   public float offsetX;
   public List spriteList = new ArrayList();
   public float rotateAngleZ;

   public void compileDisplayList(float var1) {
      if (this.displayList == 0) {
         this.displayList = GLAllocation.generateDisplayLists(1);
      }

      GL11.glNewList(this.displayList, 4864);
      WorldRenderer var2 = Tessellator.getInstance().getWorldRenderer();

      for (int var3 = 0; var3 < this.cubeList.size(); var3++) {
         this.cubeList.get(var3).render(var2, var1);
      }

      for (int var5 = 0; var5 < this.spriteList.size(); var5++) {
         ModelSprite var4 = (ModelSprite)this.spriteList.get(var5);
         var4.render(Tessellator.getInstance(), var1);
      }

      GL11.glEndList();
      this.compiled = true;
   }

   public void setRotationPoint(float var1, float var2, float var3) {
      this.rotationPointX = var1;
      this.rotationPointY = var2;
      this.rotationPointZ = var3;
   }

   public ModelRenderer addBox(String var1, float var2, float var3, float var4, int var5, int var6, int var7) {
      var1 = this.boxName + "." + var1;
      TextureOffset var8 = this.baseModel.getTextureOffset(var1);
      this.setTextureOffset(var8.textureOffsetX, var8.textureOffsetY);
      this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, var2, var3, var4, var5, var6, var7, 0.0F).setBoxName(var1));
      return this;
   }

   public ModelRenderer setTextureSize(int var1, int var2) {
      this.textureWidth = var1;
      this.textureHeight = var2;
      return this;
   }

   public void setModelUpdater(ModelUpdater var1) {
      this.modelUpdater = var1;
   }

   public void checkResetDisplayList() {
      if (this.countResetDisplayList != Shaders.countResetDisplayLists) {
         this.compiled = false;
         this.countResetDisplayList = Shaders.countResetDisplayLists;
      }
   }

   public ModelRenderer addBox(float var1, float var2, float var3, int var4, int var5, int var6) {
      this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, var1, var2, var3, var4, var5, var6, 0.0F));
      return this;
   }

   public void postRender(float var1) {
      if (!this.isHidden && this.showModel) {
         this.checkResetDisplayList();
         if (!this.compiled) {
            this.compileDisplayList(var1);
         }

         if (this.rotateAngleX != 0.0F || this.rotateAngleY != 0.0F || this.rotateAngleZ != 0.0F) {
            GlStateManager.translate(this.rotationPointX * var1, this.rotationPointY * var1, this.rotationPointZ * var1);
            if (this.rotateAngleZ != 0.0F) {
               GlStateManager.rotate(this.rotateAngleZ * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
            }

            if (this.rotateAngleY != 0.0F) {
               GlStateManager.rotate(this.rotateAngleY * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            }

            if (this.rotateAngleX != 0.0F) {
               GlStateManager.rotate(this.rotateAngleX * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            }
         } else if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
            GlStateManager.translate(this.rotationPointX * var1, this.rotationPointY * var1, this.rotationPointZ * var1);
         }
      }
   }

   public ModelRenderer(ModelBase var1, int var2, int var3) {
      this(var1);
      this.setTextureOffset(var2, var3);
   }

   public ResourceLocation getTextureLocation() {
      return this.textureLocation;
   }

   public void addBox(int[][] var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.cubeList.add(new ModelBox(this, var1, var2, var3, var4, var5, var6, var7, var8, this.mirror));
   }

   public void render(float var1) {
      if (!this.isHidden && this.showModel) {
         this.checkResetDisplayList();
         if (!this.compiled) {
            this.compileDisplayList(var1);
         }

         int var2 = 0;
         if (this.textureLocation != null && !this.renderGlobal.renderOverlayDamaged) {
            if (this.renderGlobal.renderOverlayEyes) {
               return;
            }

            var2 = GlStateManager.getBoundTexture();
            Config.getTextureManager().bindTexture(this.textureLocation);
         }

         if (this.modelUpdater != null) {
            this.modelUpdater.update();
         }

         boolean var3 = this.scaleX != 1.0F || this.scaleY != 1.0F || this.scaleZ != 1.0F;
         GlStateManager.translate(this.offsetX, this.offsetY, this.offsetZ);
         if (this.rotateAngleX != 0.0F || this.rotateAngleY != 0.0F || this.rotateAngleZ != 0.0F) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(this.rotationPointX * var1, this.rotationPointY * var1, this.rotationPointZ * var1);
            if (this.rotateAngleZ != 0.0F) {
               GlStateManager.rotate(this.rotateAngleZ * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
            }

            if (this.rotateAngleY != 0.0F) {
               GlStateManager.rotate(this.rotateAngleY * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            }

            if (this.rotateAngleX != 0.0F) {
               GlStateManager.rotate(this.rotateAngleX * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            }

            if (var3) {
               GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
            }

            GlStateManager.callList(this.displayList);
            if (this.childModels != null) {
               for (int var7 = 0; var7 < this.childModels.size(); var7++) {
                  this.childModels.get(var7).render(var1);
               }
            }

            GlStateManager.popMatrix();
         } else if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F) {
            if (var3) {
               GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
            }

            GlStateManager.callList(this.displayList);
            if (this.childModels != null) {
               for (ModelRenderer var5 : this.childModels) {
                  var5.render(var1);
               }
            }

            if (var3) {
               GlStateManager.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
            }
         } else {
            GlStateManager.translate(this.rotationPointX * var1, this.rotationPointY * var1, this.rotationPointZ * var1);
            if (var3) {
               GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
            }

            GlStateManager.callList(this.displayList);
            if (this.childModels != null) {
               for (int var4 = 0; var4 < this.childModels.size(); var4++) {
                  this.childModels.get(var4).render(var1);
               }
            }

            if (var3) {
               GlStateManager.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
            }

            GlStateManager.translate(-this.rotationPointX * var1, -this.rotationPointY * var1, -this.rotationPointZ * var1);
         }

         GlStateManager.translate(-this.offsetX, -this.offsetY, -this.offsetZ);
         if (var2 != 0) {
            GlStateManager.bindTexture(var2);
         }
      }
   }

   public ModelRenderer setTextureOffset(int var1, int var2) {
      this.textureOffsetX = var1;
      this.textureOffsetY = var2;
      return this;
   }

   public void addBox(float var1, float var2, float var3, int var4, int var5, int var6, float var7) {
      this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, var1, var2, var3, var4, var5, var6, var7));
   }

   public void addChild(ModelRenderer var1) {
      if (this.childModels == null) {
         this.childModels = Lists.newArrayList();
      }

      this.childModels.add(var1);
   }

   public ModelRenderer getChild(String var1) {
      if (var1 == null) {
         return null;
      } else {
         if (this.childModels != null) {
            for (int var2 = 0; var2 < this.childModels.size(); var2++) {
               ModelRenderer var3 = this.childModels.get(var2);
               if (var1.equals(var3.getId())) {
                  return var3;
               }
            }
         }

         return null;
      }
   }

   public ModelRenderer getChildDeep(String var1) {
      if (var1 == null) {
         return null;
      } else {
         ModelRenderer var2 = this.getChild(var1);
         if (var2 != null) {
            return var2;
         } else {
            if (this.childModels != null) {
               for (int var3 = 0; var3 < this.childModels.size(); var3++) {
                  ModelRenderer var4 = this.childModels.get(var3);
                  ModelRenderer var5 = var4.getChildDeep(var1);
                  if (var5 != null) {
                     return var5;
                  }
               }
            }

            return null;
         }
      }
   }

   public boolean getCompiled() {
      return this.compiled;
   }

   public void setId(String var1) {
      this.id = var1;
   }

   public void setTextureLocation(ResourceLocation var1) {
      this.textureLocation = var1;
   }

   public int getDisplayList() {
      return this.displayList;
   }

   public ModelRenderer(ModelBase var1, String var2) {
      this.mirrorV = false;
      this.scaleX = 1.0F;
      this.scaleY = 1.0F;
      this.scaleZ = 1.0F;
      this.textureLocation = null;
      this.id = null;
      this.renderGlobal = Config.getRenderGlobal();
      this.textureWidth = 64.0F;
      this.textureHeight = 32.0F;
      this.showModel = true;
      this.cubeList = Lists.newArrayList();
      this.baseModel = var1;
      var1.boxList.add(this);
      this.boxName = var2;
      this.setTextureSize(var1.t, var1.u);
   }

   public ModelRenderer addBox(float var1, float var2, float var3, int var4, int var5, int var6, boolean var7) {
      this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, var1, var2, var3, var4, var5, var6, 0.0F, var7));
      return this;
   }

   public String getId() {
      return this.id;
   }

   public void addSprite(float var1, float var2, float var3, int var4, int var5, int var6, float var7) {
      this.spriteList.add(new ModelSprite(this, this.textureOffsetX, this.textureOffsetY, var1, var2, var3, var4, var5, var6, var7));
   }

   public ModelRenderer(ModelBase var1) {
      this(var1, null);
   }

   public void renderWithRotation(float var1) {
      if (!this.isHidden && this.showModel) {
         this.checkResetDisplayList();
         if (!this.compiled) {
            this.compileDisplayList(var1);
         }

         int var2 = 0;
         if (this.textureLocation != null && !this.renderGlobal.renderOverlayDamaged) {
            if (this.renderGlobal.renderOverlayEyes) {
               return;
            }

            var2 = GlStateManager.getBoundTexture();
            Config.getTextureManager().bindTexture(this.textureLocation);
         }

         if (this.modelUpdater != null) {
            this.modelUpdater.update();
         }

         boolean var3 = this.scaleX != 1.0F || this.scaleY != 1.0F || this.scaleZ != 1.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translate(this.rotationPointX * var1, this.rotationPointY * var1, this.rotationPointZ * var1);
         if (this.rotateAngleY != 0.0F) {
            GlStateManager.rotate(this.rotateAngleY * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         }

         if (this.rotateAngleX != 0.0F) {
            GlStateManager.rotate(this.rotateAngleX * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         }

         if (this.rotateAngleZ != 0.0F) {
            GlStateManager.rotate(this.rotateAngleZ * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
         }

         if (var3) {
            GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
         }

         GlStateManager.callList(this.displayList);
         if (this.childModels != null) {
            for (int var4 = 0; var4 < this.childModels.size(); var4++) {
               this.childModels.get(var4).render(var1);
            }
         }

         GlStateManager.popMatrix();
         if (var2 != 0) {
            GlStateManager.bindTexture(var2);
         }
      }
   }

   @Override
   public String toString() {
      StringBuffer var1 = new StringBuffer();
      var1.append(
         "id: "
            + this.id
            + ", boxes: "
            + (this.cubeList != null ? this.cubeList.size() : null)
            + ", submodels: "
            + (this.childModels != null ? this.childModels.size() : null)
      );
      return var1.toString();
   }
}
