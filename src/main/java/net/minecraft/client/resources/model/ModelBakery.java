package net.minecraft.client.resources.model;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.renderer.texture.IIconCreator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ITransformation;
import net.minecraftforge.client.model.TRSRTransformation;
import net.minecraftforge.fml.common.registry.RegistryDelegate;
import net.optifine.CustomItems;
import net.optifine.reflect.Reflector;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelBakery {
   public IResourceManager resourceManager;
   public Map<String, ResourceLocation> itemLocations;
   public ItemModelGenerator itemModelGenerator;
   public Map<Item, List<String>> variantNames;
   public RegistrySimple<ModelResourceLocation, IBakedModel> bakedRegistry;
   public static Set<ResourceLocation> LOCATIONS_BUILTIN_TEXTURES = Sets.newHashSet(
      new ResourceLocation("blocks/water_flow"),
      new ResourceLocation("blocks/water_still"),
      new ResourceLocation("blocks/lava_flow"),
      new ResourceLocation("blocks/lava_still"),
      new ResourceLocation("blocks/destroy_stage_0"),
      new ResourceLocation("blocks/destroy_stage_1"),
      new ResourceLocation("blocks/destroy_stage_2"),
      new ResourceLocation("blocks/destroy_stage_3"),
      new ResourceLocation("blocks/destroy_stage_4"),
      new ResourceLocation("blocks/destroy_stage_5"),
      new ResourceLocation("blocks/destroy_stage_6"),
      new ResourceLocation("blocks/destroy_stage_7"),
      new ResourceLocation("blocks/destroy_stage_8"),
      new ResourceLocation("blocks/destroy_stage_9"),
      new ResourceLocation("items/empty_armor_slot_helmet"),
      new ResourceLocation("items/empty_armor_slot_chestplate"),
      new ResourceLocation("items/empty_armor_slot_leggings"),
      new ResourceLocation("items/empty_armor_slot_boots")
   );
   public TextureMap textureMap;
   public BlockModelShapes blockModelShapes;
   public Map<ResourceLocation, ModelBlockDefinition> blockDefinitions;
   public static Logger LOGGER = LogManager.getLogger();
   public Map<ResourceLocation, ModelBlock> models;
   public Map<ModelResourceLocation, ModelBlockDefinition.Variants> variants;
   public static ModelResourceLocation MODEL_MISSING = new ModelResourceLocation("builtin/missing", "missing");
   public static Map<String, String> recoveredField3500 = Maps.newHashMap();
   public Map<ResourceLocation, TextureAtlasSprite> sprites = Maps.newHashMap();
   public static Joiner JOINER = Joiner.on(" -> ");
   public static ModelBlock MODEL_GENERATED = ModelBlock.deserialize(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   public static ModelBlock MODEL_COMPASS = ModelBlock.deserialize(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   public static ModelBlock MODEL_CLOCK = ModelBlock.deserialize(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   public FaceBakery faceBakery;
   public static ModelBlock MODEL_ENTITY = ModelBlock.deserialize(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   public static Map<RegistryDelegate<Item>, Set<String>> customVariantNames = Maps.newHashMap();

   public Set<ResourceLocation> getItemsTextureLocations() {
      HashSet var1 = Sets.newHashSet();

      for (ResourceLocation var3 : this.itemLocations.values()) {
         ModelBlock var4 = this.models.get(var3);
         if (var4 != null) {
            var1.add(new ResourceLocation(var4.resolveTextureName("particle")));
            if (this.hasItemModel(var4)) {
               for (String var11 : ItemModelGenerator.LAYERS) {
                  ResourceLocation var12 = new ResourceLocation(var4.resolveTextureName(var11));
                  if (var4.getRootModel() == MODEL_COMPASS && !TextureMap.LOCATION_MISSING_TEXTURE.equals(var12)) {
                     TextureAtlasSprite.setLocationNameCompass(var12.toString());
                  } else if (var4.getRootModel() == MODEL_CLOCK && !TextureMap.LOCATION_MISSING_TEXTURE.equals(var12)) {
                     TextureAtlasSprite.setLocationNameClock(var12.toString());
                  }

                  var1.add(var12);
               }
            } else if (!this.isCustomRenderer(var4)) {
               for (BlockPart var6 : var4.getElements()) {
                  for (BlockPartFace var8 : var6.mapFaces.values()) {
                     ResourceLocation var9 = new ResourceLocation(var4.resolveTextureName(var8.texture));
                     var1.add(var9);
                  }
               }
            }
         }
      }

      return var1;
   }

   public static String fixResourcePath(String var0, String var1) {
      var0 = TextureUtils.fixResourcePath(var0, var1);
      var0 = StrUtils.removeSuffix(var0, ".json");
      return StrUtils.removeSuffix(var0, ".png");
   }

   public Set<ResourceLocation> getTextureLocations(ModelBlock var1) {
      HashSet var2 = Sets.newHashSet();

      for (BlockPart var4 : var1.getElements()) {
         for (BlockPartFace var6 : var4.mapFaces.values()) {
            ResourceLocation var7 = new ResourceLocation(var1.resolveTextureName(var6.texture));
            var2.add(var7);
         }
      }

      var2.add(new ResourceLocation(var1.resolveTextureName("particle")));
      return var2;
   }

   public static void fixModelLocations(ModelBlock var0, String var1) {
      ResourceLocation var2 = fixModelLocation(var0.getParentLocation(), var1);
      if (var2 != var0.getParentLocation()) {
         Reflector.setFieldValue(var0, Reflector.ModelBlock_parentLocation, var2);
      }

      Map var3 = (Map)Reflector.getFieldValue(var0, Reflector.ModelBlock_textures);
      if (var3 != null) {
         for (Entry var5 : (Iterable<Entry>)(Iterable<?>)(var3.entrySet())) {
            String var6 = (String)var5.getValue();
            String var7 = fixResourcePath(var6, var1);
            if (var7 != var6) {
               var5.setValue(var7);
            }
         }
      }
   }

   public Set<ResourceLocation> getVariantsTextureLocations() {
      HashSet var1 = Sets.newHashSet();
      ArrayList var2 = Lists.newArrayList(this.variants.keySet());
      Collections.sort(var2, new Comparator<ModelResourceLocation>() {
         public int compare(ModelResourceLocation var1, ModelResourceLocation var2x) {
            return var1.toString().compareTo(var2x.toString());
         }
      });

      for (ModelResourceLocation var4 : (Iterable<ModelResourceLocation>)(Iterable<?>)(var2)) {
         ModelBlockDefinition.Variants var5 = this.variants.get(var4);

         for (ModelBlockDefinition.Variant var7 : var5.getVariants()) {
            ModelBlock var8 = this.models.get(var7.getModelLocation());
            if (var8 == null) {
               LOGGER.warn("Missing model for: " + var4);
            } else {
               var1.addAll(this.getTextureLocations(var8));
            }
         }
      }

      var1.addAll(LOCATIONS_BUILTIN_TEXTURES);
      return var1;
   }

   public void loadSprites() {
      final Set var1 = this.getVariantsTextureLocations();
      var1.addAll(this.getItemsTextureLocations());
      var1.remove(TextureMap.LOCATION_MISSING_TEXTURE);
      IIconCreator var2 = new IIconCreator() {
         @Override
         public void registerSprites(TextureMap var1x) {
            for (ResourceLocation var3 : (Iterable<ResourceLocation>)(Iterable<?>)(var1)) {
               TextureAtlasSprite var4 = var1x.registerSprite(var3);
               ModelBakery.this.sprites.put(var3, var4);
            }
         }
      };
      this.textureMap.loadSprites(this.resourceManager, var2);
      this.sprites.put(new ResourceLocation("missingno"), this.textureMap.getMissingSprite());
   }

   public List<ResourceLocation> getParentPath(ResourceLocation var1) {
      ArrayList var2 = Lists.newArrayList(var1);
      ResourceLocation var3 = var1;

      while ((var3 = this.getParentLocation(var3)) != null) {
         var2.add(0, var3);
      }

      return var2;
   }

   public void loadModelsCheck() {
      this.loadModels();

      for (ModelBlock var2 : this.models.values()) {
         var2.getParentFromMap(this.models);
      }

      ModelBlock.checkModelHierarchy(this.models);
   }

   public ModelBlock loadModel(ResourceLocation var1) throws java.io.IOException {
      String var2 = var1.getResourcePath();
      if ("builtin/generated".equals(var2)) {
         return MODEL_GENERATED;
      } else if ("builtin/compass".equals(var2)) {
         return MODEL_COMPASS;
      } else if ("builtin/clock".equals(var2)) {
         return MODEL_CLOCK;
      } else if ("builtin/entity".equals(var2)) {
         return MODEL_ENTITY;
      } else {
         java.io.Closeable var3;
         if (var2.startsWith("builtin/")) {
            String var4 = var2.substring("builtin/".length());
            String var5 = recoveredField3500.get(var4);
            if (var5 == null) {
               throw new FileNotFoundException(var1.toString());
            }

            var3 = new StringReader(var5);
         } else {
            var1 = this.getModelLocation(var1);
            IResource var10 = this.resourceManager.getResource(var1);
            var3 = new InputStreamReader(var10.getInputStream(), Charsets.UTF_8);
         }

         ModelBlock var11;
         try {
            ModelBlock var12 = ModelBlock.deserialize((Reader)var3);
            var12.name = var1.toString();
            var11 = var12;
            String var6 = TextureUtils.getBasePath(var1.getResourcePath());
            fixModelLocations(var12, var6);
         } finally {
            var3.close();
         }

         return var11;
      }
   }

   public void bakeItemModels() {
      for (ResourceLocation var2 : this.itemLocations.values()) {
         ModelBlock var3 = this.models.get(var2);
         if (this.hasItemModel(var3)) {
            ModelBlock var4 = this.makeItemModel(var3);
            if (var4 != null) {
               var4.name = var2.toString();
            }

            this.models.put(var2, var4);
         } else if (this.isCustomRenderer(var3)) {
            this.models.put(var2, var3);
         }
      }

      for (TextureAtlasSprite var6 : this.sprites.values()) {
         if (!var6.hasAnimationMetadata()) {
            var6.clearFramesTextureData();
         }
      }
   }

   static {
      recoveredField3500.put(
         "missing",
         "{ \"textures\": {   \"particle\": \"missingno\",   \"missingno\": \"missingno\"}, \"elements\": [ {     \"from\": [ 0, 0, 0 ],     \"to\": [ 16, 16, 16 ],     \"faces\": {         \"down\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"down\", \"texture\": \"#missingno\" },         \"up\":    { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"up\", \"texture\": \"#missingno\" },         \"north\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"north\", \"texture\": \"#missingno\" },         \"south\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"south\", \"texture\": \"#missingno\" },         \"west\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"west\", \"texture\": \"#missingno\" },         \"east\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"east\", \"texture\": \"#missingno\" }    }}]}"
      );
      MODEL_GENERATED.name = "generation marker";
      MODEL_COMPASS.name = "compass generation marker";
      MODEL_CLOCK.name = "class generation marker";
      MODEL_ENTITY.name = "block entity marker";
   }

   public ModelBlock makeItemModel(ModelBlock var1) {
      return this.itemModelGenerator.makeItemModel(this.textureMap, var1);
   }

   public BakedQuad makeBakedQuad(BlockPart var1, BlockPartFace var2, TextureAtlasSprite var3, EnumFacing var4, ITransformation var5, boolean var6) {
      return this.faceBakery.makeBakedQuad(var1.positionFrom, var1.positionTo, var2, var3, var4, var5, var1.partRotation, var6, var1.shade);
   }

   public void registerVariant(ModelBlockDefinition var1, ModelResourceLocation var2) {
      this.variants.put(var2, var1.getVariants(var2.getVariant()));
   }

   public BakedQuad makeBakedQuad(BlockPart var1, BlockPartFace var2, TextureAtlasSprite var3, EnumFacing var4, ModelRotation var5, boolean var6) {
      return Reflector.ForgeHooksClient.exists()
         ? this.makeBakedQuad(var1, var2, var3, var4, var5, var6)
         : this.faceBakery.makeBakedQuad(var1.positionFrom, var1.positionTo, var2, var3, var4, var5, var1.partRotation, var6, var1.shade);
   }

   public ResourceLocation getBlockStateLocation(ResourceLocation var1) {
      return new ResourceLocation(var1.getResourceDomain(), "blockstates/" + var1.getResourcePath() + ".json");
   }

   public ResourceLocation getModelLocation(ResourceLocation var1) {
      ResourceLocation var2 = var1;
      String var3 = var1.getResourcePath();
      if (!var3.startsWith("mcpatcher") && !var3.startsWith("optifine")) {
         return new ResourceLocation(var1.getResourceDomain(), "models/" + var1.getResourcePath() + ".json");
      } else {
         if (!var3.endsWith(".json")) {
            var2 = new ResourceLocation(var1.getResourceDomain(), var3 + ".json");
         }

         return var2;
      }
   }

   public void loadItemModels() {
      this.registerVariantNames();

      for (Item var2 : Item.itemRegistry) {
         for (String var4 : this.getVariantNames(var2)) {
            ResourceLocation var5 = this.getItemLocation(var4);
            this.itemLocations.put(var4, var5);
            if (this.models.get(var5) == null) {
               try {
                  ModelBlock var6 = this.loadModel(var5);
                  this.models.put(var5, var6);
               } catch (Exception var7) {
                  LOGGER.warn("Unable to load item model: '" + var5 + "' for item: '" + Item.itemRegistry.getNameForObject(var2) + "'", var7);
               }
            }
         }
      }
   }

   public boolean isCustomRenderer(ModelBlock var1) {
      if (var1 == null) {
         return false;
      } else {
         ModelBlock var2 = var1.getRootModel();
         return var2 == MODEL_ENTITY;
      }
   }

   public static void addVariantName(Item var0, String... var1) {
      RegistryDelegate var2 = (RegistryDelegate)Reflector.getFieldValue(var0, Reflector.ForgeItem_delegate);
      if (customVariantNames.containsKey(var2)) {
         customVariantNames.get(var2).addAll(Lists.newArrayList(var1));
      } else {
         customVariantNames.put(var2, Sets.newHashSet(var1));
      }
   }

   public IBakedModel bakeModel(ModelBlock var1, ModelRotation var2, boolean var3) {
      return this.bakeModel(var1, (ITransformation)var2, var3);
   }

   public IRegistry<ModelResourceLocation, IBakedModel> setupModelRegistry() {
      this.loadVariantItemModels();
      this.loadModelsCheck();
      this.loadSprites();
      this.bakeItemModels();
      this.bakeBlockModels();
      return this.bakedRegistry;
   }

   public void loadVariantModels() {
      for (ModelResourceLocation var2 : this.variants.keySet()) {
         for (ModelBlockDefinition.Variant var4 : this.variants.get(var2).getVariants()) {
            ResourceLocation var5 = var4.getModelLocation();
            if (this.models.get(var5) == null) {
               try {
                  ModelBlock var6 = this.loadModel(var5);
                  this.models.put(var5, var6);
               } catch (Exception var7) {
                  LOGGER.warn("Unable to load block model: '" + var5 + "' for variant: '" + var2 + "'", var7);
               }
            }
         }
      }
   }

   public ResourceLocation getItemLocation(String var1) {
      ResourceLocation var2 = new ResourceLocation(var1);
      if (Reflector.ForgeHooksClient.exists()) {
         var2 = new ResourceLocation(var1.replaceAll("#.*", ""));
      }

      return new ResourceLocation(var2.getResourceDomain(), "item/" + var2.getResourcePath());
   }

   public void loadVariantItemModels() {
      this.loadVariants(this.blockModelShapes.getBlockStateMapper().putAllStateModelLocations().values());
      this.variants
         .put(
            MODEL_MISSING,
            new ModelBlockDefinition.Variants(
               MODEL_MISSING.getVariant(),
               Lists.newArrayList(new ModelBlockDefinition.Variant(new ResourceLocation(MODEL_MISSING.getResourcePath()), ModelRotation.X0_Y0, false, 1))
            )
         );
      ResourceLocation var1 = new ResourceLocation("item_frame");
      ModelBlockDefinition var2 = this.getModelBlockDefinition(var1);
      this.registerVariant(var2, new ModelResourceLocation(var1, "normal"));
      this.registerVariant(var2, new ModelResourceLocation(var1, "map"));
      this.loadVariantModels();
      this.loadItemModels();
   }

   public void registerVariantNames() {
      this.variantNames.clear();
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stone),
            Lists.newArrayList("stone", "granite", "granite_smooth", "diorite", "diorite_smooth", "andesite", "andesite_smooth")
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.dirt), Lists.newArrayList("dirt", "coarse_dirt", "podzol"));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.planks),
            Lists.newArrayList("oak_planks", "spruce_planks", "birch_planks", "jungle_planks", "acacia_planks", "dark_oak_planks")
         );
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.sapling),
            Lists.newArrayList("oak_sapling", "spruce_sapling", "birch_sapling", "jungle_sapling", "acacia_sapling", "dark_oak_sapling")
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.sand), Lists.newArrayList("sand", "red_sand"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.log), Lists.newArrayList("oak_log", "spruce_log", "birch_log", "jungle_log"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.leaves), Lists.newArrayList("oak_leaves", "spruce_leaves", "birch_leaves", "jungle_leaves"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.sponge), Lists.newArrayList("sponge", "sponge_wet"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.sandstone), Lists.newArrayList("sandstone", "chiseled_sandstone", "smooth_sandstone"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.red_sandstone), Lists.newArrayList("red_sandstone", "chiseled_red_sandstone", "smooth_red_sandstone"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.tallgrass), Lists.newArrayList("dead_bush", "tall_grass", "fern"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.deadbush), Lists.newArrayList("dead_bush"));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.wool),
            Lists.newArrayList(
               "black_wool",
               "red_wool",
               "green_wool",
               "brown_wool",
               "blue_wool",
               "purple_wool",
               "cyan_wool",
               "silver_wool",
               "gray_wool",
               "pink_wool",
               "lime_wool",
               "yellow_wool",
               "light_blue_wool",
               "magenta_wool",
               "orange_wool",
               "white_wool"
            )
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.yellow_flower), Lists.newArrayList("dandelion"));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.red_flower),
            Lists.newArrayList("poppy", "blue_orchid", "allium", "houstonia", "red_tulip", "orange_tulip", "white_tulip", "pink_tulip", "oxeye_daisy")
         );
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stone_slab),
            Lists.newArrayList("stone_slab", "sandstone_slab", "cobblestone_slab", "brick_slab", "stone_brick_slab", "nether_brick_slab", "quartz_slab")
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.stone_slab2), Lists.newArrayList("red_sandstone_slab"));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stained_glass),
            Lists.newArrayList(
               "black_stained_glass",
               "red_stained_glass",
               "green_stained_glass",
               "brown_stained_glass",
               "blue_stained_glass",
               "purple_stained_glass",
               "cyan_stained_glass",
               "silver_stained_glass",
               "gray_stained_glass",
               "pink_stained_glass",
               "lime_stained_glass",
               "yellow_stained_glass",
               "light_blue_stained_glass",
               "magenta_stained_glass",
               "orange_stained_glass",
               "white_stained_glass"
            )
         );
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.monster_egg),
            Lists.newArrayList(
               "stone_monster_egg",
               "cobblestone_monster_egg",
               "stone_brick_monster_egg",
               "mossy_brick_monster_egg",
               "cracked_brick_monster_egg",
               "chiseled_brick_monster_egg"
            )
         );
      this.variantNames
         .put(Item.getItemFromBlock(Blocks.stonebrick), Lists.newArrayList("stonebrick", "mossy_stonebrick", "cracked_stonebrick", "chiseled_stonebrick"));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.wooden_slab),
            Lists.newArrayList("oak_slab", "spruce_slab", "birch_slab", "jungle_slab", "acacia_slab", "dark_oak_slab")
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.cobblestone_wall), Lists.newArrayList("cobblestone_wall", "mossy_cobblestone_wall"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.anvil), Lists.newArrayList("anvil_intact", "anvil_slightly_damaged", "anvil_very_damaged"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.quartz_block), Lists.newArrayList("quartz_block", "chiseled_quartz_block", "quartz_column"));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stained_hardened_clay),
            Lists.newArrayList(
               "black_stained_hardened_clay",
               "red_stained_hardened_clay",
               "green_stained_hardened_clay",
               "brown_stained_hardened_clay",
               "blue_stained_hardened_clay",
               "purple_stained_hardened_clay",
               "cyan_stained_hardened_clay",
               "silver_stained_hardened_clay",
               "gray_stained_hardened_clay",
               "pink_stained_hardened_clay",
               "lime_stained_hardened_clay",
               "yellow_stained_hardened_clay",
               "light_blue_stained_hardened_clay",
               "magenta_stained_hardened_clay",
               "orange_stained_hardened_clay",
               "white_stained_hardened_clay"
            )
         );
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stained_glass_pane),
            Lists.newArrayList(
               "black_stained_glass_pane",
               "red_stained_glass_pane",
               "green_stained_glass_pane",
               "brown_stained_glass_pane",
               "blue_stained_glass_pane",
               "purple_stained_glass_pane",
               "cyan_stained_glass_pane",
               "silver_stained_glass_pane",
               "gray_stained_glass_pane",
               "pink_stained_glass_pane",
               "lime_stained_glass_pane",
               "yellow_stained_glass_pane",
               "light_blue_stained_glass_pane",
               "magenta_stained_glass_pane",
               "orange_stained_glass_pane",
               "white_stained_glass_pane"
            )
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.leaves2), Lists.newArrayList("acacia_leaves", "dark_oak_leaves"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.log2), Lists.newArrayList("acacia_log", "dark_oak_log"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.prismarine), Lists.newArrayList("prismarine", "prismarine_bricks", "dark_prismarine"));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.carpet),
            Lists.newArrayList(
               "black_carpet",
               "red_carpet",
               "green_carpet",
               "brown_carpet",
               "blue_carpet",
               "purple_carpet",
               "cyan_carpet",
               "silver_carpet",
               "gray_carpet",
               "pink_carpet",
               "lime_carpet",
               "yellow_carpet",
               "light_blue_carpet",
               "magenta_carpet",
               "orange_carpet",
               "white_carpet"
            )
         );
      this.variantNames
         .put(Item.getItemFromBlock(Blocks.double_plant), Lists.newArrayList("sunflower", "syringa", "double_grass", "double_fern", "double_rose", "paeonia"));
      this.variantNames.put(Items.bow, Lists.newArrayList("bow", "bow_pulling_0", "bow_pulling_1", "bow_pulling_2"));
      this.variantNames.put(Items.coal, Lists.newArrayList("coal", "charcoal"));
      this.variantNames.put(Items.fishing_rod, Lists.newArrayList("fishing_rod", "fishing_rod_cast"));
      this.variantNames.put(Items.fish, Lists.newArrayList("cod", "salmon", "clownfish", "pufferfish"));
      this.variantNames.put(Items.cooked_fish, Lists.newArrayList("cooked_cod", "cooked_salmon"));
      this.variantNames
         .put(
            Items.dye,
            Lists.newArrayList(
               "dye_black",
               "dye_red",
               "dye_green",
               "dye_brown",
               "dye_blue",
               "dye_purple",
               "dye_cyan",
               "dye_silver",
               "dye_gray",
               "dye_pink",
               "dye_lime",
               "dye_yellow",
               "dye_light_blue",
               "dye_magenta",
               "dye_orange",
               "dye_white"
            )
         );
      this.variantNames.put(Items.potionitem, Lists.newArrayList("bottle_drinkable", "bottle_splash"));
      this.variantNames.put(Items.skull, Lists.newArrayList("skull_skeleton", "skull_wither", "skull_zombie", "skull_char", "skull_creeper"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.oak_fence_gate), Lists.newArrayList("oak_fence_gate"));
      this.variantNames.put(Item.getItemFromBlock(Blocks.oak_fence), Lists.newArrayList("oak_fence"));
      this.variantNames.put(Items.oak_door, Lists.newArrayList("oak_door"));

      for (Entry var2 : customVariantNames.entrySet()) {
         this.variantNames.put((Item)((RegistryDelegate)var2.getKey()).get(), Lists.newArrayList(((Set)var2.getValue()).iterator()));
      }

      CustomItems.method_05228();
      CustomItems.loadModels(this);
   }

   public boolean hasItemModel(ModelBlock var1) {
      if (var1 == null) {
         return false;
      } else {
         ModelBlock var2 = var1.getRootModel();
         return var2 == MODEL_GENERATED || var2 == MODEL_COMPASS || var2 == MODEL_CLOCK;
      }
   }

   public List<String> getVariantNames(Item var1) {
      List var2 = this.variantNames.get(var1);
      if (var2 == null) {
         var2 = Collections.singletonList(Item.itemRegistry.getNameForObject(var1).toString());
      }

      return var2;
   }

   public ModelBlock getModelBlock(ResourceLocation var1) {
      return this.models.get(var1);
   }

   public ResourceLocation getParentLocation(ResourceLocation var1) {
      for (Entry var3 : this.models.entrySet()) {
         ModelBlock var4 = (ModelBlock)var3.getValue();
         if (var4 != null && var1.equals(var4.getParentLocation())) {
            return (ResourceLocation)var3.getKey();
         }
      }

      return null;
   }

   public void loadVariants(Collection<ModelResourceLocation> var1) {
      for (ModelResourceLocation var3 : var1) {
         try {
            ModelBlockDefinition var4 = this.getModelBlockDefinition(var3);

            try {
               this.registerVariant(var4, var3);
            } catch (Exception var6) {
               LOGGER.warn("Unable to load variant: " + var3.getVariant() + " from " + var3, var6);
            }
         } catch (Exception var7) {
            LOGGER.warn("Unable to load definition " + var3, var7);
         }
      }
   }

   public void loadItemModel(String var1, ResourceLocation var2, ResourceLocation var3) {
      this.itemLocations.put(var1, var2);
      if (this.models.get(var2) == null) {
         try {
            ModelBlock var4 = this.loadModel(var2);
            this.models.put(var2, var4);
         } catch (Exception var5) {
            LOGGER.warn("Unable to load item model: '{}' for item: '{}'", var2, var3);
            LOGGER.warn(var5.getClass().getName() + ": " + var5.getMessage());
         }
      }
   }

   public IBakedModel bakeModel(ModelBlock var1, ITransformation var2, boolean var3) {
      TextureAtlasSprite var4 = this.sprites.get(new ResourceLocation(var1.resolveTextureName("particle")));
      SimpleBakedModel.Builder var5 = new SimpleBakedModel.Builder(var1).setTexture(var4);

      for (BlockPart var7 : var1.getElements()) {
         for (EnumFacing var9 : var7.mapFaces.keySet()) {
            BlockPartFace var10 = var7.mapFaces.get(var9);
            TextureAtlasSprite var11 = this.sprites.get(new ResourceLocation(var1.resolveTextureName(var10.texture)));
            boolean var12 = true;
            if (Reflector.ForgeHooksClient.exists()) {
               var12 = TRSRTransformation.isInteger(var2.getMatrix());
            }

            if (var10.cullFace != null && var12) {
               var5.addFaceQuad(var2.rotate(var10.cullFace), this.makeBakedQuad(var7, var10, var11, var9, var2, var3));
            } else {
               var5.addGeneralQuad(this.makeBakedQuad(var7, var10, var11, var9, var2, var3));
            }
         }
      }

      return var5.makeBakedModel();
   }

   public ModelBlockDefinition getModelBlockDefinition(ResourceLocation var1) {
      ResourceLocation var2 = this.getBlockStateLocation(var1);
      ModelBlockDefinition var3 = this.blockDefinitions.get(var2);
      if (var3 == null) {
         ArrayList var4 = Lists.newArrayList();

         try {
            for (IResource var6 : this.resourceManager.getAllResources(var2)) {
               InputStream var7 = null;

               try {
                  var7 = var6.getInputStream();
                  ModelBlockDefinition var8 = ModelBlockDefinition.parseFromReader(new InputStreamReader(var7, Charsets.UTF_8));
                  var4.add(var8);
               } catch (Exception var13) {
                  throw new RuntimeException(
                     "Encountered an exception when loading model definition of '"
                        + var1
                        + "' from: '"
                        + var6.getResourceLocation()
                        + "' in resourcepack: '"
                        + var6.getResourcePackName()
                        + "'",
                     var13
                  );
               } finally {
                  IOUtils.closeQuietly(var7);
               }
            }
         } catch (IOException var15) {
            throw new RuntimeException("Encountered an exception when loading model definition of model " + var2.toString(), var15);
         }

         var3 = new ModelBlockDefinition((List<ModelBlockDefinition>)var4);
         this.blockDefinitions.put(var2, var3);
      }

      return var3;
   }

   public static ResourceLocation fixModelLocation(ResourceLocation var0, String var1) {
      if (var0 != null && var1 != null) {
         if (!var0.getResourceDomain().equals("minecraft")) {
            return var0;
         } else {
            String var2 = var0.getResourcePath();
            String var3 = fixResourcePath(var2, var1);
            if (var3 != var2) {
               var0 = new ResourceLocation(var0.getResourceDomain(), var3);
            }

            return var0;
         }
      } else {
         return var0;
      }
   }

   public static <T extends ResourceLocation> void registerItemVariants(Item var0, T... var1) {
      RegistryDelegate var2 = (RegistryDelegate)Reflector.getFieldValue(var0, Reflector.ForgeItem_delegate);
      if (!customVariantNames.containsKey(var2)) {
         customVariantNames.put(var2, Sets.newHashSet());
      }

      for (ResourceLocation var6 : var1) {
         customVariantNames.get(var2).add(var6.toString());
      }
   }

   public void bakeBlockModels() {
      for (ModelResourceLocation var2 : this.variants.keySet()) {
         WeightedBakedModel.Builder var3 = new WeightedBakedModel.Builder();
         int var4 = 0;

         for (ModelBlockDefinition.Variant var6 : this.variants.get(var2).getVariants()) {
            ModelBlock var7 = this.models.get(var6.getModelLocation());
            if (var7 != null && var7.isResolved()) {
               var4++;
               var3.add(this.bakeModel(var7, var6.getRotation(), var6.isUvLocked()), var6.getWeight());
            } else {
               LOGGER.warn("Missing model for: " + var2);
            }
         }

         if (var4 == 0) {
            LOGGER.warn("No weighted models for: " + var2);
         } else if (var4 == 1) {
            this.bakedRegistry.putObject(var2, var3.first());
         } else {
            this.bakedRegistry.putObject(var2, var3.build());
         }
      }

      for (Entry var9 : this.itemLocations.entrySet()) {
         ResourceLocation var10 = (ResourceLocation)var9.getValue();
         ModelResourceLocation var11 = new ModelResourceLocation((String)var9.getKey(), "inventory");
         if (Reflector.ModelLoader_getInventoryVariant.exists()) {
            var11 = (ModelResourceLocation)Reflector.call(Reflector.ModelLoader_getInventoryVariant, var9.getKey());
         }

         ModelBlock var12 = this.models.get(var10);
         if (var12 == null || !var12.isResolved()) {
            LOGGER.warn("Missing model for: " + var10);
         } else if (this.isCustomRenderer(var12)) {
            this.bakedRegistry.putObject(var11, new BuiltInModel(var12.getAllTransforms()));
         } else {
            this.bakedRegistry.putObject(var11, this.bakeModel(var12, ModelRotation.X0_Y0, false));
         }
      }
   }

   public ModelBakery(IResourceManager var1, TextureMap var2, BlockModelShapes var3) {
      this.models = Maps.newLinkedHashMap();
      this.variants = Maps.newLinkedHashMap();
      this.faceBakery = new FaceBakery();
      this.itemModelGenerator = new ItemModelGenerator();
      this.bakedRegistry = new RegistrySimple<>();
      this.itemLocations = Maps.newLinkedHashMap();
      this.blockDefinitions = Maps.newHashMap();
      this.variantNames = Maps.newIdentityHashMap();
      this.resourceManager = var1;
      this.textureMap = var2;
      this.blockModelShapes = var3;
   }

   public void loadModels() {
      ArrayDeque var1 = Queues.newArrayDeque();
      HashSet var2 = Sets.newHashSet();

      for (ResourceLocation var4 : this.models.keySet()) {
         var2.add(var4);
         ResourceLocation var5 = this.models.get(var4).getParentLocation();
         if (var5 != null) {
            var1.add(var5);
         }
      }

      while (!var1.isEmpty()) {
         ResourceLocation var7 = (ResourceLocation)var1.pop();

         try {
            if (this.models.get(var7) != null) {
               continue;
            }

            ModelBlock var8 = this.loadModel(var7);
            this.models.put(var7, var8);
            ResourceLocation var9 = var8.getParentLocation();
            if (var9 != null && !var2.contains(var9)) {
               var1.add(var9);
            }
         } catch (Exception var6) {
            LOGGER.warn("In parent chain: " + JOINER.join(this.getParentPath(var7)) + "; unable to load model: '" + var7 + "'");
         }

         var2.add(var7);
      }
   }
}
