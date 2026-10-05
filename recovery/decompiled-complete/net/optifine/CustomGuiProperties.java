package net.optifine;

import io.netty.util.ResourceLeakDetector;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.ConnectedParser;
import net.optifine.config.Matches;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeListInt;
import net.optifine.config.VillagerProfession;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorField;
import net.optifine.util.MathUtils;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import recovered.unidentified.UnidentifiedClass1216;

public class CustomGuiProperties {
   public static ResourceLocation DISPENSER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");
   public static ResourceLocation HORSE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/horse.png");
   public static CustomGuiProperties$EnumVariant[] VARIANTS_DISPENSER = new CustomGuiProperties$EnumVariant[]{
      CustomGuiProperties$EnumVariant.DISPENSER, CustomGuiProperties$EnumVariant.DROPPER
   };
   public VillagerProfession[] professions;
   public Boolean field_0006;
   public JsonToNBT field_0008;
   public RangeListInt levels;
   public static ResourceLocation FURNACE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");
   public Boolean field_0009;
   public String basePath;
   public static ResourceLocation ENCHANTMENT_TABLE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/enchanting_table.png");
   public static ResourceLocation BREWING_STAND_GUI_TEXTURE = new ResourceLocation("textures/gui/container/brewing_stand.png");
   public BiomeGenBase[] biomes;
   public Boolean field_0014;
   public String fileName = null;
   public ResourceLeakDetector field_0030;
   public static ResourceLocation HOPPER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/hopper.png");
   public static ResourceLocation VILLAGER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/villager.png");
   public static CustomGuiProperties$EnumVariant[] VARIANTS_INVALID = new CustomGuiProperties$EnumVariant[0];
   public RangeListInt heights;
   public Boolean field_0002;
   public NbtTagValue nbtName;
   public CustomGuiProperties$EnumContainer container;
   public EnumDyeColor[] colors;
   public static ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
   public static ResourceLocation INVENTORY_GUI_TEXTURE = new ResourceLocation("textures/gui/container/inventory.png");
   public static ResourceLocation CRAFTING_TABLE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/crafting_table.png");
   public static ResourceLocation ANVIL_GUI_TEXTURE = new ResourceLocation("textures/gui/container/anvil.png");
   public MathUtils field_0033;
   public CustomGuiProperties$EnumVariant[] variants;
   public static CustomGuiProperties$EnumVariant[] VARIANTS_HORSE = new CustomGuiProperties$EnumVariant[]{
      CustomGuiProperties$EnumVariant.HORSE,
      CustomGuiProperties$EnumVariant.DONKEY,
      CustomGuiProperties$EnumVariant.MULE,
      CustomGuiProperties$EnumVariant.LLAMA
   };
   public static ResourceLocation SHULKER_BOX_GUI_TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");
   public Map<ResourceLocation, ResourceLocation> textureLocations;
   public static EnumDyeColor[] COLORS_INVALID = new EnumDyeColor[0];
   public static ResourceLocation BEACON_GUI_TEXTURE = new ResourceLocation("textures/gui/container/beacon.png");

   public CustomGuiProperties$EnumVariant getHorseVariant(EntityHorse var1) {
      int var2 = var1.getHorseType();
      switch (var2) {
         case 0:
            return CustomGuiProperties$EnumVariant.HORSE;
         case 1:
            return CustomGuiProperties$EnumVariant.DONKEY;
         case 2:
            return CustomGuiProperties$EnumVariant.MULE;
         default:
            return null;
      }
   }

   public CustomGuiProperties(Properties var1, String var2) {
      this.basePath = null;
      this.container = null;
      this.textureLocations = null;
      this.nbtName = null;
      this.biomes = null;
      this.heights = null;
      this.field_0006 = null;
      this.field_0009 = null;
      this.field_0014 = null;
      this.field_0002 = null;
      this.levels = null;
      this.professions = null;
      this.variants = null;
      this.colors = null;
      ConnectedParser var3 = new ConnectedParser("CustomGuis");
      this.fileName = var3.parseName(var2);
      this.basePath = var3.parseBasePath(var2);
      this.container = (CustomGuiProperties$EnumContainer)var3.parseEnum(var1.getProperty("container"), CustomGuiProperties$EnumContainer.values(), "container");
      this.textureLocations = parseTextureLocations(var1, "texture", this.container, "textures/gui/", this.basePath);
      this.nbtName = var3.parseNbtTagValue("name", var1.getProperty("name"));
      this.biomes = var3.parseBiomes(var1.getProperty("biomes"));
      this.heights = var3.parseRangeListInt(var1.getProperty("heights"));
      this.field_0006 = var3.parseBooleanObject(var1.getProperty("large"));
      this.field_0009 = var3.parseBooleanObject(var1.getProperty("trapped"));
      this.field_0014 = var3.parseBooleanObject(var1.getProperty("christmas"));
      this.field_0002 = var3.parseBooleanObject(var1.getProperty("ender"));
      this.levels = var3.parseRangeListInt(var1.getProperty("levels"));
      this.professions = var3.parseProfessions(var1.getProperty("professions"));
      CustomGuiProperties$EnumVariant[] var4 = getContainerVariants(this.container);
      this.variants = (CustomGuiProperties$EnumVariant[])var3.parseEnums(var1.getProperty("variants"), var4, "variants", VARIANTS_INVALID);
      this.colors = parseEnumDyeColors(var1.getProperty("colors"));
   }

   public ResourceLocation getTextureLocation(ResourceLocation var1) {
      ResourceLocation var2 = this.textureLocations.get(var1);
      return var2 == null ? var1 : var2;
   }

   public CustomGuiProperties$EnumContainer getContainer() {
      return this.container;
   }

   public static ResourceLocation parseTextureLocation(String var0, String var1) {
      if (var0 == null) {
         return null;
      } else {
         var0 = var0.trim();
         String var2 = TextureUtils.fixResourcePath(var0, var1);
         if (!var2.endsWith(".png")) {
            var2 = var2 + ".png";
         }

         return new ResourceLocation(var1 + "/" + var2);
      }
   }

   public boolean matchesHorse(Entity var1, IBlockAccess var2) {
      if (!(var1 instanceof EntityHorse)) {
         return false;
      } else {
         EntityHorse var3 = (EntityHorse)var1;
         if (this.variants != null) {
            CustomGuiProperties$EnumVariant var4 = this.getHorseVariant(var3);
            if (!Config.equalsOne(var4, this.variants)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean matchesBeacon(BlockPos var1, IBlockAccess var2) {
      TileEntity var3 = var2.getTileEntity(var1);
      if (!(var3 instanceof TileEntityBeacon)) {
         return false;
      } else {
         TileEntityBeacon var4 = (TileEntityBeacon)var3;
         if (this.levels != null) {
            NBTTagCompound var5 = new NBTTagCompound();
            var4.writeToNBT(var5);
            int var6 = var5.getInteger("Levels");
            if (!this.levels.isInRange(var6)) {
               return false;
            }
         }

         return true;
      }
   }

   public CustomGuiProperties$EnumVariant getDispenserVariant(TileEntityDispenser var1) {
      return var1 instanceof TileEntityDropper ? CustomGuiProperties$EnumVariant.DROPPER : CustomGuiProperties$EnumVariant.DISPENSER;
   }

   public static ResourceLocation getGuiTextureLocation(CustomGuiProperties$EnumContainer var0) {
      if (var0 == null) {
         return null;
      } else {
         switch (CustomGuiProperties$1.$SwitchMap$net$optifine$CustomGuiProperties$EnumContainer[var0.ordinal()]) {
            case 1:
               return ANVIL_GUI_TEXTURE;
            case 2:
               return BEACON_GUI_TEXTURE;
            case 3:
               return BREWING_STAND_GUI_TEXTURE;
            case 4:
               return CHEST_GUI_TEXTURE;
            case 5:
               return CRAFTING_TABLE_GUI_TEXTURE;
            case 6:
               return null;
            case 7:
               return DISPENSER_GUI_TEXTURE;
            case 8:
               return ENCHANTMENT_TABLE_GUI_TEXTURE;
            case 9:
               return FURNACE_GUI_TEXTURE;
            case 10:
               return HOPPER_GUI_TEXTURE;
            case 11:
               return HORSE_GUI_TEXTURE;
            case 12:
               return INVENTORY_GUI_TEXTURE;
            case 13:
               return SHULKER_BOX_GUI_TEXTURE;
            case 14:
               return VILLAGER_GUI_TEXTURE;
            default:
               return null;
         }
      }
   }

   public static EnumDyeColor parseEnumDyeColor(String var0) {
      if (var0 == null) {
         return null;
      } else {
         EnumDyeColor[] var1 = EnumDyeColor.values();

         for (int var2 = 0; var2 < var1.length; var2++) {
            EnumDyeColor var3 = var1[var2];
            if (var3.getName().equals(var0)) {
               return var3;
            }

            if (var3.getUnlocalizedName().equals(var0)) {
               return var3;
            }
         }

         return null;
      }
   }

   public boolean matchesPos(CustomGuiProperties$EnumContainer var1, BlockPos var2, IBlockAccess var3, GuiScreen var4) {
      if (!this.matchesGeneral(var1, var2, var3)) {
         return false;
      } else {
         if (this.nbtName != null) {
            String var5 = getName(var4);
            if (!this.nbtName.matchesValue(var5)) {
               return false;
            }
         }

         switch (CustomGuiProperties$1.$SwitchMap$net$optifine$CustomGuiProperties$EnumContainer[var1.ordinal()]) {
            case 2:
               return this.matchesBeacon(var2, var3);
            case 4:
               return this.matchesChest(var2, var3);
            case 7:
               return this.matchesDispenser(var2, var3);
            default:
               return true;
         }
      }
   }

   public static String getName(GuiScreen var0) {
      IWorldNameable var1 = getWorldNameable(var0);
      return var1 == null ? null : var1.getDisplayName().getUnformattedText();
   }

   public boolean isValid(String var1) {
      if (this.fileName == null || this.fileName.length() <= 0) {
         warn("No name found: " + var1);
         return false;
      } else if (this.basePath == null) {
         warn("No base path found: " + var1);
         return false;
      } else if (this.container == null) {
         warn("No container found: " + var1);
         return false;
      } else if (this.textureLocations.isEmpty()) {
         warn("No texture found: " + var1);
         return false;
      } else if (this.professions == ConnectedParser.PROFESSIONS_INVALID) {
         warn("Invalid professions or careers: " + var1);
         return false;
      } else if (this.variants == VARIANTS_INVALID) {
         warn("Invalid variants: " + var1);
         return false;
      } else if (this.colors == COLORS_INVALID) {
         warn("Invalid colors: " + var1);
         return false;
      } else {
         return true;
      }
   }

   public boolean matchesVillager(Entity var1, IBlockAccess var2) {
      if (!(var1 instanceof EntityVillager)) {
         return false;
      } else {
         EntityVillager var3 = (EntityVillager)var1;
         if (this.professions != null) {
            int var4 = var3.getProfession();
            int var5 = Reflector.getFieldValueInt(var3, Reflector.EntityVillager_careerId, -1);
            if (var5 < 0) {
               return false;
            }

            boolean var6 = false;

            for (int var7 = 0; var7 < this.professions.length; var7++) {
               VillagerProfession var8 = this.professions[var7];
               if (var8.matches(var4, var5)) {
                  var6 = true;
                  break;
               }
            }

            if (!var6) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "name: " + this.fileName + ", container: " + this.container + ", textures: " + this.textureLocations;
   }

   public static Map<ResourceLocation, ResourceLocation> parseTextureLocations(
      Properties var0, String var1, CustomGuiProperties$EnumContainer var2, String var3, String var4
   ) {
      HashMap var5 = new HashMap();
      String var6 = var0.getProperty(var1);
      if (var6 != null) {
         ResourceLocation var7 = getGuiTextureLocation(var2);
         ResourceLocation var8 = parseTextureLocation(var6, var4);
         if (var7 != null && var8 != null) {
            var5.put(var7, var8);
         }
      }

      String var16 = var1 + ".";

      for (Object var9 : var0.keySet()) {
         String var10 = (String)var9;
         if (var10.startsWith(var16)) {
            String var11 = var10.substring(var16.length());
            var11 = var11.replace('\\', '/');
            var11 = StrUtils.removePrefixSuffix(var11, "/", ".png");
            String var12 = var3 + var11 + ".png";
            String var13 = var0.getProperty(var10);
            ResourceLocation var14 = new ResourceLocation(var12);
            ResourceLocation var15 = parseTextureLocation(var13, var4);
            var5.put(var14, var15);
         }
      }

      return var5;
   }

   public static CustomGuiProperties$EnumVariant[] getContainerVariants(CustomGuiProperties$EnumContainer var0) {
      return var0 == CustomGuiProperties$EnumContainer.HORSE
         ? VARIANTS_HORSE
         : (var0 == CustomGuiProperties$EnumContainer.DISPENSER ? VARIANTS_DISPENSER : new CustomGuiProperties$EnumVariant[0]);
   }

   public boolean matchesChest(BlockPos var1, IBlockAccess var2) {
      TileEntity var3 = var2.getTileEntity(var1);
      if (var3 instanceof TileEntityChest) {
         TileEntityChest var5 = (TileEntityChest)var3;
         return this.matchesChest(var5, var1, var2);
      } else if (var3 instanceof TileEntityEnderChest) {
         TileEntityEnderChest var4 = (TileEntityEnderChest)var3;
         return this.matchesEnderChest(var4, var1, var2);
      } else {
         return false;
      }
   }

   public static IWorldNameable getWorldNameable(GuiScreen var0, ReflectorField var1) {
      Object var2 = Reflector.getFieldValue(var0, var1);
      return !(var2 instanceof IWorldNameable) ? null : (IWorldNameable)var2;
   }

   public boolean matchesGeneral(CustomGuiProperties$EnumContainer var1, BlockPos var2, IBlockAccess var3) {
      if (this.container != var1) {
         return false;
      } else {
         if (this.biomes != null) {
            BiomeGenBase var4 = var3.getBiomeGenForCoords(var2);
            if (!Matches.biome(var4, this.biomes)) {
               return false;
            }
         }

         return this.heights == null || this.heights.isInRange(var2.getY());
      }
   }

   public boolean matchesChest(boolean var1, boolean var2, boolean var3, boolean var4) {
      return this.field_0006 != null && this.field_0006 != var1
         ? false
         : (
            this.field_0009 != null && this.field_0009 != var2
               ? false
               : (this.field_0014 != null && this.field_0014 != var3 ? false : this.field_0002 == null || this.field_0002 == var4)
         );
   }

   public static IWorldNameable getWorldNameable(GuiScreen var0) {
      return (IWorldNameable)(var0 instanceof GuiBeacon
         ? getWorldNameable(var0, Reflector.GuiBeacon_tileBeacon)
         : (
            var0 instanceof UnidentifiedClass1216
               ? getWorldNameable(var0, Reflector.GuiBrewingStand_tileBrewingStand)
               : (
                  var0 instanceof GuiChest
                     ? getWorldNameable(var0, Reflector.GuiChest_lowerChestInventory)
                     : (
                        var0 instanceof GuiDispenser
                           ? ((GuiDispenser)var0).dispenserInventory
                           : (
                              var0 instanceof GuiEnchantment
                                 ? getWorldNameable(var0, Reflector.GuiEnchantment_nameable)
                                 : (
                                    var0 instanceof GuiFurnace
                                       ? getWorldNameable(var0, Reflector.GuiFurnace_tileFurnace)
                                       : (var0 instanceof GuiHopper ? getWorldNameable(var0, Reflector.GuiHopper_hopperInventory) : null)
                                 )
                           )
                     )
               )
         ));
   }

   public boolean matchesEntity(CustomGuiProperties$EnumContainer var1, Entity var2, IBlockAccess var3) {
      if (!this.matchesGeneral(var1, var2.getPosition(), var3)) {
         return false;
      } else {
         if (this.nbtName != null) {
            String var4 = var2.z_();
            if (!this.nbtName.matchesValue(var4)) {
               return false;
            }
         }

         switch (CustomGuiProperties$1.$SwitchMap$net$optifine$CustomGuiProperties$EnumContainer[var1.ordinal()]) {
            case 11:
               return this.matchesHorse(var2, var3);
            case 14:
               return this.matchesVillager(var2, var3);
            default:
               return true;
         }
      }
   }

   public boolean matchesEnderChest(TileEntityEnderChest var1, BlockPos var2, IBlockAccess var3) {
      return this.matchesChest(false, false, false, true);
   }

   public static void warn(String var0) {
      Config.warn("[CustomGuis] " + var0);
   }

   public static EnumDyeColor[] parseEnumDyeColors(String var0) {
      if (var0 == null) {
         return null;
      } else {
         var0 = var0.toLowerCase();
         String[] var1 = Config.tokenize(var0, " ");
         EnumDyeColor[] var2 = new EnumDyeColor[var1.length];

         for (int var3 = 0; var3 < var1.length; var3++) {
            String var4 = var1[var3];
            EnumDyeColor var5 = parseEnumDyeColor(var4);
            if (var5 == null) {
               warn("Invalid color: " + var4);
               return COLORS_INVALID;
            }

            var2[var3] = var5;
         }

         return var2;
      }
   }

   public boolean matchesDispenser(BlockPos var1, IBlockAccess var2) {
      TileEntity var3 = var2.getTileEntity(var1);
      if (!(var3 instanceof TileEntityDispenser)) {
         return false;
      } else {
         TileEntityDispenser var4 = (TileEntityDispenser)var3;
         if (this.variants != null) {
            CustomGuiProperties$EnumVariant var5 = this.getDispenserVariant(var4);
            if (!Config.equalsOne(var5, this.variants)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean matchesChest(TileEntityChest var1, BlockPos var2, IBlockAccess var3) {
      boolean var4 = var1.adjacentChestXNeg != null || var1.adjacentChestXPos != null || var1.adjacentChestZNeg != null || var1.adjacentChestZPos != null;
      boolean var5 = var1.getChestType() == 1;
      boolean var6 = CustomGuis.isChristmas;
      boolean var7 = false;
      return this.matchesChest(var4, var5, var6, var7);
   }
}
