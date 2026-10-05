package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelBlock {
   public ItemCameraTransforms cameraTransforms;
   public ResourceLocation parentLocation;
   public Map<String, String> textures;
   public String name = "";
   public boolean ambientOcclusion;
   public static Logger LOGGER = LogManager.getLogger();
   public ModelBlock parent;
   public static Gson SERIALIZER = new GsonBuilder()
      .registerTypeAdapter(ModelBlock.class, new ModelBlock.Deserializer())
      .registerTypeAdapter(BlockPart.class, new BlockPart.Deserializer())
      .registerTypeAdapter(BlockPartFace.class, new BlockPartFace.Deserializer())
      .registerTypeAdapter(BlockFaceUV.class, new BlockFaceUV.Deserializer())
      .registerTypeAdapter(ItemTransformVec3f.class, new ItemTransformVec3f.Deserializer())
      .registerTypeAdapter(ItemCameraTransforms.class, new ItemCameraTransforms.Deserializer())
      .create();
   public boolean gui3d;
   public List<BlockPart> elements;

   public ModelBlock(ResourceLocation var1, List<BlockPart> var2, Map<String, String> var3, boolean var4, boolean var5, ItemCameraTransforms var6) {
      this.elements = var2;
      this.ambientOcclusion = var4;
      this.gui3d = var5;
      this.textures = var3;
      this.parentLocation = var1;
      this.cameraTransforms = var6;
   }

   public boolean isResolved() {
      return this.parentLocation == null || this.parent != null && this.parent.isResolved();
   }

   public static void checkModelHierarchy(Map<ResourceLocation, ModelBlock> var0) {
      for (ModelBlock var2 : var0.values()) {
         try {
            ModelBlock var3 = var2.parent;

            for (ModelBlock var4 = var3.parent; var3 != var4; var4 = var4.parent.parent) {
               var3 = var3.parent;
            }

            throw new ModelBlock.LoopException();
         } catch (NullPointerException var5) {
         }
      }
   }

   public ModelBlock(List<BlockPart> var1, Map<String, String> var2, boolean var3, boolean var4, ItemCameraTransforms var5) {
      this((ResourceLocation)null, var1, var2, var3, var4, var5);
   }

   public boolean isAmbientOcclusion() {
      return this.hasParent() ? this.parent.isAmbientOcclusion() : this.ambientOcclusion;
   }

   public boolean isGui3d() {
      return this.gui3d;
   }

   public ItemCameraTransforms getAllTransforms() {
      ItemTransformVec3f var1 = this.getTransform(ItemCameraTransforms.TransformType.THIRD_PERSON);
      ItemTransformVec3f var2 = this.getTransform(ItemCameraTransforms.TransformType.FIRST_PERSON);
      ItemTransformVec3f var3 = this.getTransform(ItemCameraTransforms.TransformType.HEAD);
      ItemTransformVec3f var4 = this.getTransform(ItemCameraTransforms.TransformType.GUI);
      ItemTransformVec3f var5 = this.getTransform(ItemCameraTransforms.TransformType.GROUND);
      ItemTransformVec3f var6 = this.getTransform(ItemCameraTransforms.TransformType.FIXED);
      return new ItemCameraTransforms(var1, var2, var3, var4, var5, var6);
   }

   public String resolveTextureName(String var1, ModelBlock.Bookkeep var2) {
      if (this.startsWithHash(var1)) {
         if (this == var2.modelExt) {
            LOGGER.warn("Unable to resolve texture due to upward reference: " + var1 + " in " + this.name);
            return "missingno";
         } else {
            String var3 = this.textures.get(var1.substring(1));
            if (var3 == null && this.hasParent()) {
               var3 = this.parent.resolveTextureName(var1, var2);
            }

            var2.modelExt = this;
            if (var3 != null && this.startsWithHash(var3)) {
               var3 = var2.model.resolveTextureName(var3, var2);
            }

            return var3 != null && !this.startsWithHash(var3) ? var3 : "missingno";
         }
      } else {
         return var1;
      }
   }

   public static ModelBlock deserialize(Reader var0) {
      return SERIALIZER.fromJson(var0, ModelBlock.class);
   }

   public String resolveTextureName(String var1) {
      if (!this.startsWithHash(var1)) {
         var1 = '#' + var1;
      }

      return this.resolveTextureName(var1, new ModelBlock.Bookkeep(this));
   }

   public ModelBlock(ResourceLocation var1, Map<String, String> var2, boolean var3, boolean var4, ItemCameraTransforms var5) {
      this(var1, Collections.emptyList(), var2, var3, var4, var5);
   }

   public ItemTransformVec3f getTransform(ItemCameraTransforms.TransformType var1) {
      return this.parent != null && !this.cameraTransforms.func_181687_c(var1) ? this.parent.getTransform(var1) : this.cameraTransforms.getTransform(var1);
   }

   public ModelBlock getRootModel() {
      return this.hasParent() ? this.parent.getRootModel() : this;
   }

   public void getParentFromMap(Map<ResourceLocation, ModelBlock> var1) {
      if (this.parentLocation != null) {
         this.parent = (ModelBlock)var1.get(this.parentLocation);
      }
   }

   public List<BlockPart> getElements() {
      return this.hasParent() ? this.parent.getElements() : this.elements;
   }

   public boolean startsWithHash(String var1) {
      return var1.charAt(0) == '#';
   }

   public static ModelBlock deserialize(String var0) {
      return deserialize(new StringReader(var0));
   }

   public boolean isTexturePresent(String var1) {
      return !"missingno".equals(this.resolveTextureName(var1));
   }

   public ResourceLocation getParentLocation() {
      return this.parentLocation;
   }

   public boolean hasParent() {
      return this.parent != null;
   }

   public static final class Bookkeep {
      public ModelBlock model;
      public ModelBlock modelExt;

      public Bookkeep(ModelBlock var1) {
         this.model = var1;
      }
   }

   public static class Deserializer implements JsonDeserializer<ModelBlock> {
      public ModelBlock deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws com.google.gson.JsonParseException {
         JsonObject var4 = var1.getAsJsonObject();
         List var5 = this.getModelElements(var3, var4);
         String var6 = this.getParent(var4);
         boolean var7 = StringUtils.isEmpty(var6);
         boolean var8 = var5.isEmpty();
         if (var8 && var7) {
            throw new JsonParseException("BlockModel requires either elements or parent, found neither");
         } else if (!var7 && !var8) {
            throw new JsonParseException("BlockModel requires either elements or parent, found both");
         } else {
            Map var9 = this.getTextures(var4);
            boolean var10 = this.getAmbientOcclusionEnabled(var4);
            ItemCameraTransforms var11 = ItemCameraTransforms.DEFAULT;
            if (var4.has("display")) {
               JsonObject var12 = JsonUtils.getJsonObject(var4, "display");
               var11 = var3.deserialize(var12, ItemCameraTransforms.class);
            }

            return var8 ? new ModelBlock(new ResourceLocation(var6), var9, var10, true, var11) : new ModelBlock(var5, var9, var10, true, var11);
         }
      }

      public boolean getAmbientOcclusionEnabled(JsonObject var1) {
         return JsonUtils.getBoolean(var1, "ambientocclusion", true);
      }

      public List<BlockPart> getModelElements(JsonDeserializationContext var1, JsonObject var2) {
         ArrayList var3 = Lists.newArrayList();
         if (var2.has("elements")) {
            for (JsonElement var5 : JsonUtils.getJsonArray(var2, "elements")) {
               var3.add((BlockPart)var1.deserialize(var5, BlockPart.class));
            }
         }

         return var3;
      }

      public Map<String, String> getTextures(JsonObject var1) {
         HashMap var2 = Maps.newHashMap();
         if (var1.has("textures")) {
            JsonObject var3 = var1.getAsJsonObject("textures");

            for (Entry var5 : var3.entrySet()) {
               var2.put(var5.getKey(), ((JsonElement)var5.getValue()).getAsString());
            }
         }

         return var2;
      }

      public String getParent(JsonObject var1) {
         return JsonUtils.getString(var1, "parent", "");
      }
   }

   public static class LoopException extends RuntimeException {
   }
}
