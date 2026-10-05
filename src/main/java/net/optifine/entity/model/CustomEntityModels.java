package net.optifine.entity.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.anim.ModelResolver;
import net.optifine.entity.model.anim.ModelUpdater;

public class CustomEntityModels {
   public static boolean active = false;
   public static Map<Class, Render> originalEntityRenderMap = null;
   public static Map<Class, TileEntitySpecialRenderer> originalTileEntityRenderMap = null;

   public static boolean modifyModel(ModelAdapter var0, ModelBase var1, CustomModelRenderer var2, ModelResolver var3) {
      String var4 = var2.getModelPart();
      ModelRenderer var5 = var0.getModelRenderer(var1, var4);
      if (var5 == null) {
         Config.warn("Model part not found: " + var4 + ", model: " + var1);
         return false;
      } else {
         if (!var2.isAttach()) {
            if (var5.cubeList != null) {
               var5.cubeList.clear();
            }

            if (var5.spriteList != null) {
               var5.spriteList.clear();
            }

            if (var5.childModels != null) {
               ModelRenderer[] var6 = var0.getModelRenderers(var1);
               Set var7 = Collections.newSetFromMap(new IdentityHashMap());
               var7.addAll(Arrays.asList(var6));
               List var8 = var5.childModels;
               Iterator var9 = var8.iterator();

               while (var9.hasNext()) {
                  ModelRenderer var10 = (ModelRenderer)var9.next();
                  if (!var7.contains(var10)) {
                     var9.remove();
                  }
               }
            }
         }

         var5.addChild(var2.getModelRenderer());
         ModelUpdater var11 = var2.getModelUpdater();
         if (var11 != null) {
            var3.setThisModelRenderer(var2.getModelRenderer());
            var3.setPartModelRenderer(var5);
            if (!var11.initialize(var3)) {
               return false;
            }

            var2.getModelRenderer().setModelUpdater(var11);
         }

         return true;
      }
   }

   public static ResourceLocation[] getModelLocations() {
      String var0 = "optifine/cem/";
      String var1 = ".jem";
      ArrayList var2 = new ArrayList();
      String[] var3 = CustomModelRegistry.getModelNames();

      for (int var4 = 0; var4 < var3.length; var4++) {
         String var5 = var3[var4];
         String var6 = var0 + var5 + var1;
         ResourceLocation var7 = new ResourceLocation(var6);
         if (Config.hasResource(var7)) {
            var2.add(var7);
         }
      }

      return (net.minecraft.util.ResourceLocation[])var2.toArray(new ResourceLocation[var2.size()]);
   }

   public static IEntityRenderer makeEntityRender(ModelAdapter var0, CustomEntityRenderer var1) {
      ResourceLocation var2 = var1.getTextureLocation();
      CustomModelRenderer[] var3 = var1.getCustomModelRenderers();
      float var4 = var1.getShadowSize();
      if (var4 < 0.0F) {
         var4 = var0.getShadowSize();
      }

      ModelBase var5 = var0.makeModel();
      if (var5 == null) {
         return null;
      } else {
         ModelResolver var6 = new ModelResolver(var0, var5, var3);
         if (!modifyModel(var0, var5, var3, var6)) {
            return null;
         } else {
            IEntityRenderer var7 = var0.makeEntityRender(var5, var4);
            if (var7 == null) {
               throw new JsonParseException("Entity renderer is null, model: " + var0.getName() + ", adapter: " + var0.getClass().getName());
            } else {
               if (var2 != null) {
                  var7.setLocationTextureCustom(var2);
               }

               return var7;
            }
         }
      }
   }

   public static Map<Class, Render> getEntityRenderMap() {
      RenderManager var0 = Minecraft.getMinecraft().getRenderManager();
      Map var1 = var0.getEntityRenderMap();
      if (var1 == null) {
         return null;
      } else {
         if (originalEntityRenderMap == null) {
            originalEntityRenderMap = new HashMap<>(var1);
         }

         return var1;
      }
   }

   public static boolean modifyModel(ModelAdapter var0, ModelBase var1, CustomModelRenderer[] var2, ModelResolver var3) {
      for (int var4 = 0; var4 < var2.length; var4++) {
         CustomModelRenderer var5 = var2[var4];
         if (!modifyModel(var0, var1, var5, var3)) {
            return false;
         }
      }

      return true;
   }

   public static void update() {
      Map var0 = getEntityRenderMap();
      Map var1 = getTileEntityRenderMap();
      if (var0 == null) {
         Config.warn("Entity render map not found, custom entity models are DISABLED.");
      } else if (var1 == null) {
         Config.warn("Tile entity render map not found, custom entity models are DISABLED.");
      } else {
         active = false;
         var0.clear();
         var1.clear();
         var0.putAll(originalEntityRenderMap);
         var1.putAll(originalTileEntityRenderMap);
         if (Config.isCustomEntityModels()) {
            ResourceLocation[] var2 = getModelLocations();

            for (int var3 = 0; var3 < var2.length; var3++) {
               ResourceLocation var4 = var2[var3];
               Config.dbg("CustomEntityModel: " + var4.getResourcePath());
               IEntityRenderer var5 = parseEntityRender(var4);
               if (var5 != null) {
                  Class var6 = var5.getEntityClass();
                  if (var6 != null) {
                     if (var5 instanceof Render) {
                        var0.put(var6, (Render)var5);
                     } else if (var5 instanceof TileEntitySpecialRenderer) {
                        var1.put(var6, (TileEntitySpecialRenderer)var5);
                     } else {
                        Config.warn("Unknown renderer type: " + var5.getClass().getName());
                     }

                     active = true;
                  }
               }
            }
         }
      }
   }

   public static IEntityRenderer parseEntityRender(JsonObject var0, String var1) {
      CustomEntityRenderer var2 = CustomEntityModelParser.parseEntityRender(var0, var1);
      String var3 = var2.getName();
      ModelAdapter var4 = CustomModelRegistry.getModelAdapter(var3);
      checkNull(var4, "Entity not found: " + var3);
      Class var5 = var4.getEntityClass();
      checkNull(var5, "Entity class not found: " + var3);
      IEntityRenderer var6 = makeEntityRender(var4, var2);
      if (var6 == null) {
         return null;
      } else {
         var6.setEntityClass(var5);
         return var6;
      }
   }

   public static boolean isActive() {
      return active;
   }

   public static Map<Class, TileEntitySpecialRenderer> getTileEntityRenderMap() {
      Map var0 = TileEntityRendererDispatcher.instance.mapSpecialRenderers;
      if (originalTileEntityRenderMap == null) {
         originalTileEntityRenderMap = new HashMap<>(var0);
      }

      return var0;
   }

   public static void checkNull(Object var0, String var1) {
      if (var0 == null) {
         throw new JsonParseException(var1);
      }
   }

   public static IEntityRenderer parseEntityRender(ResourceLocation var0) {
      try {
         JsonObject var1 = CustomEntityModelParser.loadJson(var0);
         return parseEntityRender(var1, var0.getResourcePath());
      } catch (IOException var3) {
         Config.error("" + var3.getClass().getName() + ": " + var3.getMessage());
         return null;
      } catch (JsonParseException var4) {
         Config.error("" + var4.getClass().getName() + ": " + var4.getMessage());
         return null;
      } catch (Exception var5) {
         var5.printStackTrace();
         return null;
      }
   }
}
