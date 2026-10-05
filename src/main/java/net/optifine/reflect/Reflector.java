package net.optifine.reflect;

import com.google.common.base.Optional;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import javax.vecmath.Matrix4f;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.model.ModelLeashKnot;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.client.renderer.tileentity.TileEntityBannerRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityEnderChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.optifine.Log;
import net.optifine.util.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.gui.inventory.GuiBrewingStand;

public class Reflector {
   public static Logger LOGGER = LogManager.getLogger();
   public static boolean logForge = logEntry("*** Reflector Forge ***");
   public static ReflectorClass BetterFoliageClient = new ReflectorClass("mods.betterfoliage.client.BetterFoliageClient");
   public static ReflectorClass BlamingTransformer = new ReflectorClass("net.minecraftforge.fml.common.asm.transformers.BlamingTransformer");
   public static ReflectorMethod BlamingTransformer_onCrash = new ReflectorMethod(Reflector.BlamingTransformer, "onCrash");
   public static ReflectorClass ChunkWatchEvent_UnWatch = new ReflectorClass("net.minecraftforge.event.world.ChunkWatchEvent$UnWatch");
   public static ReflectorConstructor ChunkWatchEvent_UnWatch_Constructor = new ReflectorConstructor(
      ChunkWatchEvent_UnWatch, new Class[]{ChunkCoordIntPair.class, EntityPlayerMP.class}
   );
   public static ReflectorClass CoreModManager = new ReflectorClass("net.minecraftforge.fml.relauncher.CoreModManager");
   public static ReflectorMethod CoreModManager_onCrash = new ReflectorMethod(Reflector.CoreModManager, "onCrash");
   public static ReflectorClass DimensionManager = new ReflectorClass("net.minecraftforge.common.DimensionManager");
   public static ReflectorMethod DimensionManager_createProviderFor = new ReflectorMethod(Reflector.DimensionManager, "createProviderFor");
   public static ReflectorMethod DimensionManager_getStaticDimensionIDs = new ReflectorMethod(Reflector.DimensionManager, "getStaticDimensionIDs");
   public static ReflectorClass DrawScreenEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre");
   public static ReflectorConstructor DrawScreenEvent_Pre_Constructor = new ReflectorConstructor(
      DrawScreenEvent_Pre, new Class[]{GuiScreen.class, int.class, int.class, float.class}
   );
   public static ReflectorClass DrawScreenEvent_Post = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post");
   public static ReflectorConstructor DrawScreenEvent_Post_Constructor = new ReflectorConstructor(
      Reflector.DrawScreenEvent_Post, new Class[]{GuiScreen.class, int.class, int.class, float.class}
   );
   public static ReflectorClass EntityViewRenderEvent_CameraSetup = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup");
   public static ReflectorConstructor EntityViewRenderEvent_CameraSetup_Constructor = new ReflectorConstructor(
      Reflector.EntityViewRenderEvent_CameraSetup,
      new Class[]{EntityRenderer.class, Entity.class, Block.class, double.class, float.class, float.class, float.class}
   );
   public static ReflectorField EntityViewRenderEvent_CameraSetup_yaw = new ReflectorField(Reflector.EntityViewRenderEvent_CameraSetup, "yaw");
   public static ReflectorField EntityViewRenderEvent_CameraSetup_pitch = new ReflectorField(Reflector.EntityViewRenderEvent_CameraSetup, "pitch");
   public static ReflectorField EntityViewRenderEvent_CameraSetup_roll = new ReflectorField(Reflector.EntityViewRenderEvent_CameraSetup, "roll");
   public static ReflectorClass EntityViewRenderEvent_FogColors = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$FogColors");
   public static ReflectorConstructor EntityViewRenderEvent_FogColors_Constructor = new ReflectorConstructor(
      EntityViewRenderEvent_FogColors, new Class[]{EntityRenderer.class, Entity.class, Block.class, double.class, float.class, float.class, float.class}
   );
   public static ReflectorField EntityViewRenderEvent_FogColors_red = new ReflectorField(EntityViewRenderEvent_FogColors, "red");
   public static ReflectorField EntityViewRenderEvent_FogColors_green = new ReflectorField(EntityViewRenderEvent_FogColors, "green");
   public static ReflectorField EntityViewRenderEvent_FogColors_blue = new ReflectorField(EntityViewRenderEvent_FogColors, "blue");
   public static ReflectorClass Event = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.Event");
   public static ReflectorMethod Event_isCanceled = new ReflectorMethod(Event, "isCanceled");
   public static ReflectorClass EventBus = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.EventBus");
   public static ReflectorMethod EventBus_post = new ReflectorMethod(EventBus, "post");
   public static ReflectorClass Event_Result = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.Event$Result");
   public static ReflectorField Event_Result_DENY = new ReflectorField(Event_Result, "DENY");
   public static ReflectorField Event_Result_ALLOW = new ReflectorField(Event_Result, "ALLOW");
   public static ReflectorField Event_Result_DEFAULT = new ReflectorField(Event_Result, "DEFAULT");
   public static ReflectorClass ExtendedBlockState = new ReflectorClass("net.minecraftforge.common.property.ExtendedBlockState");
   public static ReflectorConstructor ExtendedBlockState_Constructor = new ReflectorConstructor(
      ExtendedBlockState, new Class[]{Block.class, IProperty[].class, IUnlistedProperty[].class}
   );
   public static ReflectorClass FMLClientHandler = new ReflectorClass("net.minecraftforge.fml.client.FMLClientHandler");
   public static ReflectorMethod FMLClientHandler_instance = new ReflectorMethod(FMLClientHandler, "instance");
   public static ReflectorMethod FMLClientHandler_handleLoadingScreen = new ReflectorMethod(Reflector.FMLClientHandler, "handleLoadingScreen");
   public static ReflectorMethod FMLClientHandler_isLoading = new ReflectorMethod(Reflector.FMLClientHandler, "isLoading");
   public static ReflectorMethod FMLClientHandler_trackBrokenTexture = new ReflectorMethod(FMLClientHandler, "trackBrokenTexture");
   public static ReflectorMethod FMLClientHandler_trackMissingTexture = new ReflectorMethod(Reflector.FMLClientHandler, "trackMissingTexture");
   public static ReflectorClass FMLCommonHandler = new ReflectorClass("net.minecraftforge.fml.common.FMLCommonHandler");
   public static ReflectorMethod FMLCommonHandler_callFuture = new ReflectorMethod(FMLCommonHandler, "callFuture");
   public static ReflectorMethod FMLCommonHandler_enhanceCrashReport = new ReflectorMethod(FMLCommonHandler, "enhanceCrashReport");
   public static ReflectorMethod FMLCommonHandler_getBrandings = new ReflectorMethod(Reflector.FMLCommonHandler, "getBrandings");
   public static ReflectorMethod FMLCommonHandler_handleServerAboutToStart = new ReflectorMethod(Reflector.FMLCommonHandler, "handleServerAboutToStart");
   public static ReflectorMethod FMLCommonHandler_handleServerStarting = new ReflectorMethod(FMLCommonHandler, "handleServerStarting");
   public static ReflectorMethod FMLCommonHandler_instance = new ReflectorMethod(FMLCommonHandler, "instance");
   public static ReflectorClass ForgeBiome = new ReflectorClass(BiomeGenBase.class);
   public static ReflectorMethod ForgeBiome_getWaterColorMultiplier = new ReflectorMethod(Reflector.ForgeBiome, "getWaterColorMultiplier");
   public static ReflectorClass ForgeBlock = new ReflectorClass(Block.class);
   public static ReflectorMethod ForgeBlock_addDestroyEffects = new ReflectorMethod(Reflector.ForgeBlock, "addDestroyEffects");
   public static ReflectorMethod ForgeBlock_addHitEffects = new ReflectorMethod(Reflector.ForgeBlock, "addHitEffects");
   public static ReflectorMethod ForgeBlock_canCreatureSpawn = new ReflectorMethod(Reflector.ForgeBlock, "canCreatureSpawn");
   public static ReflectorMethod ForgeBlock_canRenderInLayer = new ReflectorMethod(
      Reflector.ForgeBlock, "canRenderInLayer", new Class[]{EnumWorldBlockLayer.class}
   );
   public static ReflectorMethod ForgeBlock_doesSideBlockRendering = new ReflectorMethod(Reflector.ForgeBlock, "doesSideBlockRendering");
   public static ReflectorMethod ForgeBlock_getBedDirection = new ReflectorMethod(Reflector.ForgeBlock, "getBedDirection");
   public static ReflectorMethod ForgeBlock_getExtendedState = new ReflectorMethod(Reflector.ForgeBlock, "getExtendedState");
   public static ReflectorMethod ForgeBlock_getLightOpacity = new ReflectorMethod(
      Reflector.ForgeBlock, "getLightOpacity", new Class[]{IBlockAccess.class, BlockPos.class}
   );
   public static ReflectorMethod ForgeBlock_getLightValue = new ReflectorMethod(
      Reflector.ForgeBlock, "getLightValue", new Class[]{IBlockAccess.class, BlockPos.class}
   );
   public static ReflectorMethod ForgeBlock_hasTileEntity = new ReflectorMethod(Reflector.ForgeBlock, "hasTileEntity", new Class[]{IBlockState.class});
   public static ReflectorMethod ForgeBlock_isAir = new ReflectorMethod(Reflector.ForgeBlock, "isAir");
   public static ReflectorMethod ForgeBlock_isBed = new ReflectorMethod(ForgeBlock, "isBed");
   public static ReflectorMethod ForgeBlock_isBedFoot = new ReflectorMethod(Reflector.ForgeBlock, "isBedFoot");
   public static ReflectorMethod ForgeBlock_isSideSolid = new ReflectorMethod(Reflector.ForgeBlock, "isSideSolid");
   public static ReflectorClass ForgeChunkCache = new ReflectorClass(ChunkCache.class);
   public static ReflectorMethod ForgeChunkCache_isSideSolid = new ReflectorMethod(ForgeChunkCache, "isSideSolid");
   public static ReflectorClass ForgeEntity = new ReflectorClass(Entity.class);
   public static ReflectorMethod ForgeEntity_canRiderInteract = new ReflectorMethod(ForgeEntity, "canRiderInteract");
   public static ReflectorField ForgeEntity_captureDrops = new ReflectorField(Reflector.ForgeEntity, "captureDrops");
   public static ReflectorField ForgeEntity_capturedDrops = new ReflectorField(Reflector.ForgeEntity, "capturedDrops");
   public static ReflectorMethod ForgeEntity_shouldRenderInPass = new ReflectorMethod(ForgeEntity, "shouldRenderInPass");
   public static ReflectorMethod ForgeEntity_shouldRiderSit = new ReflectorMethod(Reflector.ForgeEntity, "shouldRiderSit");
   public static ReflectorClass ForgeEventFactory = new ReflectorClass("net.minecraftforge.event.ForgeEventFactory");
   public static ReflectorMethod ForgeEventFactory_canEntityDespawn = new ReflectorMethod(ForgeEventFactory, "canEntityDespawn");
   public static ReflectorMethod ForgeEventFactory_canEntitySpawn = new ReflectorMethod(ForgeEventFactory, "canEntitySpawn");
   public static ReflectorMethod ForgeEventFactory_doSpecialSpawn = new ReflectorMethod(
      ForgeEventFactory, "doSpecialSpawn", new Class[]{EntityLiving.class, World.class, float.class, float.class, float.class}
   );
   public static ReflectorMethod ForgeEventFactory_getMaxSpawnPackSize = new ReflectorMethod(ForgeEventFactory, "getMaxSpawnPackSize");
   public static ReflectorMethod ForgeEventFactory_renderBlockOverlay = new ReflectorMethod(ForgeEventFactory, "renderBlockOverlay");
   public static ReflectorMethod ForgeEventFactory_renderFireOverlay = new ReflectorMethod(ForgeEventFactory, "renderFireOverlay");
   public static ReflectorMethod ForgeEventFactory_renderWaterOverlay = new ReflectorMethod(ForgeEventFactory, "renderWaterOverlay");
   public static ReflectorClass ForgeHooks = new ReflectorClass("net.minecraftforge.common.ForgeHooks");
   public static ReflectorMethod ForgeHooks_onLivingAttack = new ReflectorMethod(Reflector.ForgeHooks, "onLivingAttack");
   public static ReflectorMethod ForgeHooks_onLivingDeath = new ReflectorMethod(Reflector.ForgeHooks, "onLivingDeath");
   public static ReflectorMethod ForgeHooks_onLivingDrops = new ReflectorMethod(ForgeHooks, "onLivingDrops");
   public static ReflectorMethod ForgeHooks_onLivingFall = new ReflectorMethod(ForgeHooks, "onLivingFall");
   public static ReflectorMethod ForgeHooks_onLivingHurt = new ReflectorMethod(Reflector.ForgeHooks, "onLivingHurt");
   public static ReflectorMethod ForgeHooks_onLivingJump = new ReflectorMethod(ForgeHooks, "onLivingJump");
   public static ReflectorMethod ForgeHooks_onLivingSetAttackTarget = new ReflectorMethod(ForgeHooks, "onLivingSetAttackTarget");
   public static ReflectorMethod ForgeHooks_onLivingUpdate = new ReflectorMethod(ForgeHooks, "onLivingUpdate");
   public static ReflectorClass ForgeHooksClient = new ReflectorClass("net.minecraftforge.client.ForgeHooksClient");
   public static ReflectorMethod ForgeHooksClient_applyTransform = new ReflectorMethod(
      ForgeHooksClient, "applyTransform", new Class[]{Matrix4f.class, Optional.class}
   );
   public static ReflectorMethod ForgeHooksClient_dispatchRenderLast = new ReflectorMethod(ForgeHooksClient, "dispatchRenderLast");
   public static ReflectorMethod ForgeHooksClient_drawScreen = new ReflectorMethod(ForgeHooksClient, "drawScreen");
   public static ReflectorMethod ForgeHooksClient_fillNormal = new ReflectorMethod(ForgeHooksClient, "fillNormal");
   public static ReflectorMethod ForgeHooksClient_handleCameraTransforms = new ReflectorMethod(ForgeHooksClient, "handleCameraTransforms");
   public static ReflectorMethod ForgeHooksClient_getArmorModel = new ReflectorMethod(ForgeHooksClient, "getArmorModel");
   public static ReflectorMethod ForgeHooksClient_getArmorTexture = new ReflectorMethod(ForgeHooksClient, "getArmorTexture");
   public static ReflectorMethod ForgeHooksClient_getFogDensity = new ReflectorMethod(ForgeHooksClient, "getFogDensity");
   public static ReflectorMethod ForgeHooksClient_getFOVModifier = new ReflectorMethod(ForgeHooksClient, "getFOVModifier");
   public static ReflectorMethod ForgeHooksClient_getMatrix = new ReflectorMethod(ForgeHooksClient, "getMatrix", new Class[]{ModelRotation.class});
   public static ReflectorMethod ForgeHooksClient_getOffsetFOV = new ReflectorMethod(ForgeHooksClient, "getOffsetFOV");
   public static ReflectorMethod ForgeHooksClient_loadEntityShader = new ReflectorMethod(ForgeHooksClient, "loadEntityShader");
   public static ReflectorMethod ForgeHooksClient_onDrawBlockHighlight = new ReflectorMethod(Reflector.ForgeHooksClient, "onDrawBlockHighlight");
   public static ReflectorMethod ForgeHooksClient_onFogRender = new ReflectorMethod(ForgeHooksClient, "onFogRender");
   public static ReflectorMethod ForgeHooksClient_onTextureStitchedPre = new ReflectorMethod(Reflector.ForgeHooksClient, "onTextureStitchedPre");
   public static ReflectorMethod ForgeHooksClient_onTextureStitchedPost = new ReflectorMethod(ForgeHooksClient, "onTextureStitchedPost");
   public static ReflectorMethod ForgeHooksClient_orientBedCamera = new ReflectorMethod(ForgeHooksClient, "orientBedCamera");
   public static ReflectorMethod ForgeHooksClient_putQuadColor = new ReflectorMethod(ForgeHooksClient, "putQuadColor");
   public static ReflectorMethod ForgeHooksClient_renderFirstPersonHand = new ReflectorMethod(ForgeHooksClient, "renderFirstPersonHand");
   public static ReflectorMethod ForgeHooksClient_renderMainMenu = new ReflectorMethod(ForgeHooksClient, "renderMainMenu");
   public static ReflectorMethod ForgeHooksClient_setRenderLayer = new ReflectorMethod(ForgeHooksClient, "setRenderLayer");
   public static ReflectorMethod ForgeHooksClient_setRenderPass = new ReflectorMethod(Reflector.ForgeHooksClient, "setRenderPass");
   public static ReflectorMethod ForgeHooksClient_transform = new ReflectorMethod(ForgeHooksClient, "transform");
   public static ReflectorClass ForgeItem = new ReflectorClass(Item.class);
   public static ReflectorField ForgeItem_delegate = new ReflectorField(ForgeItem, "delegate");
   public static ReflectorMethod ForgeItem_getDurabilityForDisplay = new ReflectorMethod(ForgeItem, "getDurabilityForDisplay");
   public static ReflectorMethod ForgeItem_getModel = new ReflectorMethod(Reflector.ForgeItem, "getModel");
   public static ReflectorMethod ForgeItem_onEntitySwing = new ReflectorMethod(ForgeItem, "onEntitySwing");
   public static ReflectorMethod ForgeItem_shouldCauseReequipAnimation = new ReflectorMethod(ForgeItem, "shouldCauseReequipAnimation");
   public static ReflectorMethod ForgeItem_showDurabilityBar = new ReflectorMethod(ForgeItem, "showDurabilityBar");
   public static ReflectorClass ForgeModContainer = new ReflectorClass("net.minecraftforge.common.ForgeModContainer");
   public static ReflectorField ForgeModContainer_forgeLightPipelineEnabled = new ReflectorField(ForgeModContainer, "forgeLightPipelineEnabled");
   public static ReflectorClass ForgePotionEffect = new ReflectorClass(PotionEffect.class);
   public static ReflectorMethod ForgePotionEffect_isCurativeItem = new ReflectorMethod(ForgePotionEffect, "isCurativeItem");
   public static ReflectorClass ForgeTileEntity = new ReflectorClass(TileEntity.class);
   public static ReflectorMethod ForgeTileEntity_canRenderBreaking = new ReflectorMethod(Reflector.ForgeTileEntity, "canRenderBreaking");
   public static ReflectorMethod ForgeTileEntity_getRenderBoundingBox = new ReflectorMethod(Reflector.ForgeTileEntity, "getRenderBoundingBox");
   public static ReflectorMethod ForgeTileEntity_hasFastRenderer = new ReflectorMethod(ForgeTileEntity, "hasFastRenderer");
   public static ReflectorMethod ForgeTileEntity_shouldRenderInPass = new ReflectorMethod(Reflector.ForgeTileEntity, "shouldRenderInPass");
   public static ReflectorClass ForgeVertexFormatElementEnumUseage = new ReflectorClass(VertexFormatElement.EnumUsage.class);
   public static ReflectorMethod ForgeVertexFormatElementEnumUseage_preDraw = new ReflectorMethod(Reflector.ForgeVertexFormatElementEnumUseage, "preDraw");
   public static ReflectorMethod ForgeVertexFormatElementEnumUseage_postDraw = new ReflectorMethod(ForgeVertexFormatElementEnumUseage, "postDraw");
   public static ReflectorClass ForgeWorld = new ReflectorClass(World.class);
   public static ReflectorMethod ForgeWorld_countEntities = new ReflectorMethod(
      Reflector.ForgeWorld, "countEntities", new Class[]{EnumCreatureType.class, boolean.class}
   );
   public static ReflectorMethod ForgeWorld_getPerWorldStorage = new ReflectorMethod(Reflector.ForgeWorld, "getPerWorldStorage");
   public static ReflectorClass ForgeWorldProvider = new ReflectorClass(WorldProvider.class);
   public static ReflectorMethod ForgeWorldProvider_getCloudRenderer = new ReflectorMethod(Reflector.ForgeWorldProvider, "getCloudRenderer");
   public static ReflectorMethod ForgeWorldProvider_getSkyRenderer = new ReflectorMethod(ForgeWorldProvider, "getSkyRenderer");
   public static ReflectorMethod ForgeWorldProvider_getWeatherRenderer = new ReflectorMethod(Reflector.ForgeWorldProvider, "getWeatherRenderer");
   public static ReflectorMethod ForgeWorldProvider_getSaveFolder = new ReflectorMethod(Reflector.ForgeWorldProvider, "getSaveFolder");
   public static ReflectorClass GuiModList = new ReflectorClass("net.minecraftforge.fml.client.GuiModList");
   public static ReflectorConstructor GuiModList_Constructor = new ReflectorConstructor(Reflector.GuiModList, new Class[]{GuiScreen.class});
   public static ReflectorClass IColoredBakedQuad = new ReflectorClass("net.minecraftforge.client.model.IColoredBakedQuad");
   public static ReflectorClass IExtendedBlockState = new ReflectorClass("net.minecraftforge.common.property.IExtendedBlockState");
   public static ReflectorMethod IExtendedBlockState_getClean = new ReflectorMethod(IExtendedBlockState, "getClean");
   public static ReflectorClass IModel = new ReflectorClass("net.minecraftforge.client.model.IModel");
   public static ReflectorMethod IModel_getTextures = new ReflectorMethod(IModel, "getTextures");
   public static ReflectorClass IRenderHandler = new ReflectorClass("net.minecraftforge.client.IRenderHandler");
   public static ReflectorMethod IRenderHandler_render = new ReflectorMethod(Reflector.IRenderHandler, "render");
   public static ReflectorClass ItemModelMesherForge = new ReflectorClass("net.minecraftforge.client.ItemModelMesherForge");
   public static ReflectorConstructor ItemModelMesherForge_Constructor = new ReflectorConstructor(
      Reflector.ItemModelMesherForge, new Class[]{ModelManager.class}
   );
   public static ReflectorClass Launch = new ReflectorClass("net.minecraft.launchwrapper.Launch");
   public static ReflectorField Launch_blackboard = new ReflectorField(Reflector.Launch, "blackboard");
   public static ReflectorClass LightUtil = new ReflectorClass("net.minecraftforge.client.model.pipeline.LightUtil");
   public static ReflectorField LightUtil_itemConsumer = new ReflectorField(Reflector.LightUtil, "itemConsumer");
   public static ReflectorMethod LightUtil_putBakedQuad = new ReflectorMethod(Reflector.LightUtil, "putBakedQuad");
   public static ReflectorMethod LightUtil_renderQuadColor = new ReflectorMethod(Reflector.LightUtil, "renderQuadColor");
   public static ReflectorField LightUtil_tessellator = new ReflectorField(Reflector.LightUtil, "tessellator");
   public static ReflectorClass Loader = new ReflectorClass("net.minecraftforge.fml.common.Loader");
   public static ReflectorMethod Loader_getActiveModList = new ReflectorMethod(Reflector.Loader, "getActiveModList");
   public static ReflectorMethod Loader_instance = new ReflectorMethod(Reflector.Loader, "instance");
   public static ReflectorClass MinecraftForge = new ReflectorClass("net.minecraftforge.common.MinecraftForge");
   public static ReflectorField MinecraftForge_EVENT_BUS = new ReflectorField(Reflector.MinecraftForge, "EVENT_BUS");
   public static ReflectorClass MinecraftForgeClient = new ReflectorClass("net.minecraftforge.client.MinecraftForgeClient");
   public static ReflectorMethod MinecraftForgeClient_getRenderPass = new ReflectorMethod(MinecraftForgeClient, "getRenderPass");
   public static ReflectorMethod MinecraftForgeClient_onRebuildChunk = new ReflectorMethod(Reflector.MinecraftForgeClient, "onRebuildChunk");
   public static ReflectorClass ModContainer = new ReflectorClass("net.minecraftforge.fml.common.ModContainer");
   public static ReflectorMethod ModContainer_getModId = new ReflectorMethod(Reflector.ModContainer, "getModId");
   public static ReflectorClass ModelLoader = new ReflectorClass("net.minecraftforge.client.model.ModelLoader");
   public static ReflectorField ModelLoader_stateModels = new ReflectorField(Reflector.ModelLoader, "stateModels");
   public static ReflectorMethod ModelLoader_onRegisterItems = new ReflectorMethod(Reflector.ModelLoader, "onRegisterItems");
   public static ReflectorMethod ModelLoader_getInventoryVariant = new ReflectorMethod(Reflector.ModelLoader, "getInventoryVariant");
   public static ReflectorField ModelLoader_textures = new ReflectorField(Reflector.ModelLoader, "textures");
   public static ReflectorClass ModelLoader_VanillaLoader = new ReflectorClass("net.minecraftforge.client.model.ModelLoader$VanillaLoader");
   public static ReflectorField ModelLoader_VanillaLoader_INSTANCE = new ReflectorField(Reflector.ModelLoader_VanillaLoader, "instance");
   public static ReflectorMethod ModelLoader_VanillaLoader_loadModel = new ReflectorMethod(Reflector.ModelLoader_VanillaLoader, "loadModel");
   public static ReflectorClass RenderBlockOverlayEvent_OverlayType = new ReflectorClass("net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType");
   public static ReflectorField RenderBlockOverlayEvent_OverlayType_BLOCK = new ReflectorField(Reflector.RenderBlockOverlayEvent_OverlayType, "BLOCK");
   public static ReflectorClass RenderingRegistry = new ReflectorClass("net.minecraftforge.fml.client.registry.RenderingRegistry");
   public static ReflectorMethod RenderingRegistry_loadEntityRenderers = new ReflectorMethod(
      RenderingRegistry, "loadEntityRenderers", new Class[]{RenderManager.class, Map.class}
   );
   public static ReflectorClass RenderItemInFrameEvent = new ReflectorClass("net.minecraftforge.client.event.RenderItemInFrameEvent");
   public static ReflectorConstructor RenderItemInFrameEvent_Constructor = new ReflectorConstructor(
      Reflector.RenderItemInFrameEvent, new Class[]{EntityItemFrame.class, RenderItemFrame.class}
   );
   public static ReflectorClass RenderLivingEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Pre");
   public static ReflectorConstructor RenderLivingEvent_Pre_Constructor = new ReflectorConstructor(
      RenderLivingEvent_Pre, new Class[]{EntityLivingBase.class, RendererLivingEntity.class, double.class, double.class, double.class}
   );
   public static ReflectorClass RenderLivingEvent_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Post");
   public static ReflectorConstructor RenderLivingEvent_Post_Constructor = new ReflectorConstructor(
      RenderLivingEvent_Post, new Class[]{EntityLivingBase.class, RendererLivingEntity.class, double.class, double.class, double.class}
   );
   public static ReflectorClass RenderLivingEvent_Specials_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre");
   public static ReflectorConstructor RenderLivingEvent_Specials_Pre_Constructor = new ReflectorConstructor(
      RenderLivingEvent_Specials_Pre, new Class[]{EntityLivingBase.class, RendererLivingEntity.class, double.class, double.class, double.class}
   );
   public static ReflectorClass RenderLivingEvent_Specials_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Post");
   public static ReflectorConstructor RenderLivingEvent_Specials_Post_Constructor = new ReflectorConstructor(
      RenderLivingEvent_Specials_Post, new Class[]{EntityLivingBase.class, RendererLivingEntity.class, double.class, double.class, double.class}
   );
   public static ReflectorClass SplashScreen = new ReflectorClass("net.minecraftforge.fml.client.SplashProgress");
   public static ReflectorClass WorldEvent_Load = new ReflectorClass("net.minecraftforge.event.world.WorldEvent$Load");
   public static ReflectorConstructor WorldEvent_Load_Constructor = new ReflectorConstructor(WorldEvent_Load, new Class[]{World.class});
   public static boolean logVanilla = logEntry("*** Reflector Vanilla ***");
   public static ReflectorClass ChunkProviderClient = new ReflectorClass(ChunkProviderClient.class);
   public static ReflectorField ChunkProviderClient_chunkMapping = new ReflectorField(ChunkProviderClient, LongHashMap.class);
   public static ReflectorClass EntityVillager = new ReflectorClass(EntityVillager.class);
   public static ReflectorField EntityVillager_careerId = new ReflectorField(
      new FieldLocatorTypes(
         EntityVillager.class, new Class[0], int.class, new Class[]{int.class, boolean.class, boolean.class, InventoryBasic.class}, "EntityVillager.careerId"
      )
   );
   public static ReflectorField EntityVillager_careerLevel = new ReflectorField(
      new FieldLocatorTypes(
         EntityVillager.class, new Class[]{int.class}, int.class, new Class[]{boolean.class, boolean.class, InventoryBasic.class}, "EntityVillager.careerLevel"
      )
   );
   public static ReflectorClass GuiBeacon = new ReflectorClass(GuiBeacon.class);
   public static ReflectorField GuiBeacon_tileBeacon = new ReflectorField(Reflector.GuiBeacon, IInventory.class);
   public static ReflectorClass GuiBrewingStand = new ReflectorClass(GuiBrewingStand.class);
   public static ReflectorField GuiBrewingStand_tileBrewingStand = new ReflectorField(Reflector.GuiBrewingStand, IInventory.class);
   public static ReflectorClass GuiChest = new ReflectorClass(GuiChest.class);
   public static ReflectorField GuiChest_lowerChestInventory = new ReflectorField(Reflector.GuiChest, IInventory.class, 1);
   public static ReflectorClass GuiEnchantment = new ReflectorClass(GuiEnchantment.class);
   public static ReflectorField GuiEnchantment_nameable = new ReflectorField(Reflector.GuiEnchantment, IWorldNameable.class);
   public static ReflectorClass GuiFurnace = new ReflectorClass(GuiFurnace.class);
   public static ReflectorField GuiFurnace_tileFurnace = new ReflectorField(GuiFurnace, IInventory.class);
   public static ReflectorClass GuiHopper = new ReflectorClass(GuiHopper.class);
   public static ReflectorField GuiHopper_hopperInventory = new ReflectorField(GuiHopper, IInventory.class, 1);
   public static ReflectorClass GuiMainMenu = new ReflectorClass(GuiMainMenu.class);
   public static ReflectorField GuiMainMenu_splashText = new ReflectorField(Reflector.GuiMainMenu, String.class);
   public static ReflectorClass Minecraft = new ReflectorClass(Minecraft.class);
   public static ReflectorField Minecraft_defaultResourcePack = new ReflectorField(Reflector.Minecraft, DefaultResourcePack.class);
   public static ReflectorClass ModelHumanoidHead = new ReflectorClass(ModelHumanoidHead.class);
   public static ReflectorField ModelHumanoidHead_head = new ReflectorField(ModelHumanoidHead, ModelRenderer.class);
   public static ReflectorClass ModelBat = new ReflectorClass(ModelBat.class);
   public static ReflectorFields ModelBat_ModelRenderers = new ReflectorFields(Reflector.ModelBat, ModelRenderer.class, 6);
   public static ReflectorClass ModelBlaze = new ReflectorClass(ModelBlaze.class);
   public static ReflectorField ModelBlaze_blazeHead = new ReflectorField(Reflector.ModelBlaze, ModelRenderer.class);
   public static ReflectorField ModelBlaze_blazeSticks = new ReflectorField(Reflector.ModelBlaze, ModelRenderer[].class);
   public static ReflectorClass ModelBlock = new ReflectorClass(ModelBlock.class);
   public static ReflectorField ModelBlock_parentLocation = new ReflectorField(Reflector.ModelBlock, ResourceLocation.class);
   public static ReflectorField ModelBlock_textures = new ReflectorField(Reflector.ModelBlock, Map.class);
   public static ReflectorClass ModelDragon = new ReflectorClass(ModelDragon.class);
   public static ReflectorFields ModelDragon_ModelRenderers = new ReflectorFields(Reflector.ModelDragon, ModelRenderer.class, 12);
   public static ReflectorClass ModelEnderCrystal = new ReflectorClass(ModelEnderCrystal.class);
   public static ReflectorFields ModelEnderCrystal_ModelRenderers = new ReflectorFields(Reflector.ModelEnderCrystal, ModelRenderer.class, 3);
   public static ReflectorClass RenderEnderCrystal = new ReflectorClass(RenderEnderCrystal.class);
   public static ReflectorField RenderEnderCrystal_modelEnderCrystal = new ReflectorField(Reflector.RenderEnderCrystal, ModelBase.class, 0);
   public static ReflectorClass ModelEnderMite = new ReflectorClass(ModelEnderMite.class);
   public static ReflectorField ModelEnderMite_bodyParts = new ReflectorField(Reflector.ModelEnderMite, ModelRenderer[].class);
   public static ReflectorClass ModelGhast = new ReflectorClass(ModelGhast.class);
   public static ReflectorField ModelGhast_body = new ReflectorField(Reflector.ModelGhast, ModelRenderer.class);
   public static ReflectorField ModelGhast_tentacles = new ReflectorField(ModelGhast, ModelRenderer[].class);
   public static ReflectorClass ModelGuardian = new ReflectorClass(ModelGuardian.class);
   public static ReflectorField ModelGuardian_body = new ReflectorField(ModelGuardian, ModelRenderer.class, 0);
   public static ReflectorField ModelGuardian_eye = new ReflectorField(ModelGuardian, ModelRenderer.class, 1);
   public static ReflectorField ModelGuardian_spines = new ReflectorField(Reflector.ModelGuardian, ModelRenderer[].class, 0);
   public static ReflectorField ModelGuardian_tail = new ReflectorField(ModelGuardian, ModelRenderer[].class, 1);
   public static ReflectorClass ModelHorse = new ReflectorClass(ModelHorse.class);
   public static ReflectorFields ModelHorse_ModelRenderers = new ReflectorFields(ModelHorse, ModelRenderer.class, 39);
   public static ReflectorClass RenderLeashKnot = new ReflectorClass(RenderLeashKnot.class);
   public static ReflectorField RenderLeashKnot_leashKnotModel = new ReflectorField(Reflector.RenderLeashKnot, ModelLeashKnot.class);
   public static ReflectorClass ModelMagmaCube = new ReflectorClass(ModelMagmaCube.class);
   public static ReflectorField ModelMagmaCube_core = new ReflectorField(ModelMagmaCube, ModelRenderer.class);
   public static ReflectorField ModelMagmaCube_segments = new ReflectorField(ModelMagmaCube, ModelRenderer[].class);
   public static ReflectorClass ModelOcelot = new ReflectorClass(ModelOcelot.class);
   public static ReflectorFields ModelOcelot_ModelRenderers = new ReflectorFields(Reflector.ModelOcelot, ModelRenderer.class, 8);
   public static ReflectorClass ModelRabbit = new ReflectorClass(ModelRabbit.class);
   public static ReflectorFields ModelRabbit_renderers = new ReflectorFields(Reflector.ModelRabbit, ModelRenderer.class, 12);
   public static ReflectorClass ModelSilverfish = new ReflectorClass(ModelSilverfish.class);
   public static ReflectorField ModelSilverfish_bodyParts = new ReflectorField(Reflector.ModelSilverfish, ModelRenderer[].class, 0);
   public static ReflectorField ModelSilverfish_wingParts = new ReflectorField(ModelSilverfish, ModelRenderer[].class, 1);
   public static ReflectorClass ModelSlime = new ReflectorClass(ModelSlime.class);
   public static ReflectorFields ModelSlime_ModelRenderers = new ReflectorFields(Reflector.ModelSlime, ModelRenderer.class, 4);
   public static ReflectorClass ModelSquid = new ReflectorClass(ModelSquid.class);
   public static ReflectorField ModelSquid_body = new ReflectorField(Reflector.ModelSquid, ModelRenderer.class);
   public static ReflectorField ModelSquid_tentacles = new ReflectorField(Reflector.ModelSquid, ModelRenderer[].class);
   public static ReflectorClass ModelWitch = new ReflectorClass(ModelWitch.class);
   public static ReflectorField ModelWitch_mole = new ReflectorField(ModelWitch, ModelRenderer.class, 0);
   public static ReflectorField ModelWitch_hat = new ReflectorField(Reflector.ModelWitch, ModelRenderer.class, 1);
   public static ReflectorClass ModelWither = new ReflectorClass(ModelWither.class);
   public static ReflectorField ModelWither_bodyParts = new ReflectorField(Reflector.ModelWither, ModelRenderer[].class, 0);
   public static ReflectorField ModelWither_heads = new ReflectorField(ModelWither, ModelRenderer[].class, 1);
   public static ReflectorClass ModelWolf = new ReflectorClass(ModelWolf.class);
   public static ReflectorField ModelWolf_tail = new ReflectorField(ModelWolf, ModelRenderer.class, 6);
   public static ReflectorField ModelWolf_mane = new ReflectorField(ModelWolf, ModelRenderer.class, 7);
   public static ReflectorClass OptiFineClassTransformer = new ReflectorClass("optifine.OptiFineClassTransformer");
   public static ReflectorField OptiFineClassTransformer_instance = new ReflectorField(Reflector.OptiFineClassTransformer, "instance");
   public static ReflectorMethod OptiFineClassTransformer_getOptiFineResource = new ReflectorMethod(Reflector.OptiFineClassTransformer, "getOptiFineResource");
   public static ReflectorClass RenderBoat = new ReflectorClass(RenderBoat.class);
   public static ReflectorField RenderBoat_modelBoat = new ReflectorField(RenderBoat, ModelBase.class);
   public static ReflectorClass RenderMinecart = new ReflectorClass(RenderMinecart.class);
   public static ReflectorField RenderMinecart_modelMinecart = new ReflectorField(Reflector.RenderMinecart, ModelBase.class);
   public static ReflectorClass RenderWitherSkull = new ReflectorClass(RenderWitherSkull.class);
   public static ReflectorField RenderWitherSkull_model = new ReflectorField(RenderWitherSkull, ModelSkeletonHead.class);
   public static ReflectorClass TileEntityBannerRenderer = new ReflectorClass(TileEntityBannerRenderer.class);
   public static ReflectorField TileEntityBannerRenderer_bannerModel = new ReflectorField(Reflector.TileEntityBannerRenderer, ModelBanner.class);
   public static ReflectorClass TileEntityBeacon = new ReflectorClass(TileEntityBeacon.class);
   public static ReflectorField TileEntityBeacon_customName = new ReflectorField(TileEntityBeacon, String.class);
   public static ReflectorClass TileEntityBrewingStand = new ReflectorClass(TileEntityBrewingStand.class);
   public static ReflectorField TileEntityBrewingStand_customName = new ReflectorField(Reflector.TileEntityBrewingStand, String.class);
   public static ReflectorClass TileEntityChestRenderer = new ReflectorClass(TileEntityChestRenderer.class);
   public static ReflectorField TileEntityChestRenderer_simpleChest = new ReflectorField(Reflector.TileEntityChestRenderer, ModelChest.class, 0);
   public static ReflectorField TileEntityChestRenderer_largeChest = new ReflectorField(Reflector.TileEntityChestRenderer, ModelChest.class, 1);
   public static ReflectorClass TileEntityEnchantmentTable = new ReflectorClass(TileEntityEnchantmentTable.class);
   public static ReflectorField TileEntityEnchantmentTable_customName = new ReflectorField(Reflector.TileEntityEnchantmentTable, String.class);
   public static ReflectorClass TileEntityEnchantmentTableRenderer = new ReflectorClass(TileEntityEnchantmentTableRenderer.class);
   public static ReflectorField TileEntityEnchantmentTableRenderer_modelBook = new ReflectorField(TileEntityEnchantmentTableRenderer, ModelBook.class);
   public static ReflectorClass TileEntityEnderChestRenderer = new ReflectorClass(TileEntityEnderChestRenderer.class);
   public static ReflectorField TileEntityEnderChestRenderer_modelChest = new ReflectorField(Reflector.TileEntityEnderChestRenderer, ModelChest.class);
   public static ReflectorClass TileEntityFurnace = new ReflectorClass(TileEntityFurnace.class);
   public static ReflectorField TileEntityFurnace_customName = new ReflectorField(Reflector.TileEntityFurnace, String.class);
   public static ReflectorClass TileEntitySignRenderer = new ReflectorClass(TileEntitySignRenderer.class);
   public static ReflectorField TileEntitySignRenderer_model = new ReflectorField(TileEntitySignRenderer, ModelSign.class);
   public static ReflectorClass TileEntitySkullRenderer = new ReflectorClass(TileEntitySkullRenderer.class);
   public static ReflectorField TileEntitySkullRenderer_skeletonHead = new ReflectorField(TileEntitySkullRenderer, ModelSkeletonHead.class, 0);
   public static ReflectorField TileEntitySkullRenderer_humanoidHead = new ReflectorField(TileEntitySkullRenderer, ModelSkeletonHead.class, 1);

   public static boolean setFieldValueInt(ReflectorField var0, int var1) {
      return setFieldValueInt(null, var0, var1);
   }

   public static Object getFieldValue(Object var0, ReflectorFields var1, int var2) {
      ReflectorField var3 = var1.getReflectorField(var2);
      return var3 == null ? null : getFieldValue(var0, var3);
   }

   public static boolean postForgeBusEvent(Object var0) {
      if (var0 == null) {
         return false;
      } else {
         Object var1 = getFieldValue(MinecraftForge_EVENT_BUS);
         if (var1 == null) {
            return false;
         } else {
            Object var2 = call(var1, EventBus_post, var0);
            if (!(var2 instanceof Boolean)) {
               return false;
            } else {
               Boolean var3 = (Boolean)var2;
               return var3;
            }
         }
      }
   }

   public static boolean getFieldValueBoolean(Object var0, ReflectorField var1, boolean var2) {
      try {
         Field var3 = var1.getTargetField();
         return var3 == null ? var2 : var3.getBoolean(var0);
      } catch (Throwable var5) {
         Log.error("", var5);
         return var2;
      }
   }

   public static void dbgFieldValue(boolean var0, String var1, ReflectorField var2, Object var3) {
      String var4 = var2.getTargetField().getDeclaringClass().getName();
      String var5 = var2.getTargetField().getName();
      String var6 = "";
      if (var0) {
         var6 = " static";
      }

      Log.dbg(var1 + var6 + " " + var4 + "." + var5 + " => " + var3);
   }

   public static int getFieldValueInt(Object var0, ReflectorField var1, int var2) {
      try {
         Field var3 = var1.getTargetField();
         return var3 == null ? var2 : var3.getInt(var0);
      } catch (Throwable var5) {
         Log.error("", var5);
         return var2;
      }
   }

   public static double callDouble(ReflectorMethod var0, Object... var1) {
      try {
         Method var2 = var0.getTargetMethod();
         if (var2 == null) {
            return 0.0;
         } else {
            Double var3 = (Double)var2.invoke(null, var1);
            return var3;
         }
      } catch (Throwable var4) {
         handleException(var4, null, var0, var1);
         return 0.0;
      }
   }

   public static boolean getFieldValueBoolean(ReflectorField var0, boolean var1) {
      try {
         Field var2 = var0.getTargetField();
         return var2 == null ? var1 : var2.getBoolean(null);
      } catch (Throwable var4) {
         Log.error("", var4);
         return var1;
      }
   }

   public static void handleException(Throwable var0, ReflectorConstructor var1, Object[] var2) {
      if (var0 instanceof InvocationTargetException) {
         Log.error("", var0);
      } else {
         Log.warn("*** Exception outside of constructor ***");
         Log.warn("Constructor deactivated: " + var1.getTargetConstructor());
         var1.deactivate();
         if (var0 instanceof IllegalArgumentException) {
            Log.warn("*** IllegalArgumentException ***");
            Log.warn("Constructor: " + var1.getTargetConstructor());
            Log.warn("Parameter classes: " + ArrayUtils.arrayToString(getClasses(var2)));
            Log.warn("Parameters: " + ArrayUtils.arrayToString(var2));
         }

         Log.warn("", var0);
      }
   }

   public static Object call(ReflectorMethod var0, Object... var1) {
      try {
         Method var2 = var0.getTargetMethod();
         return var2 == null ? null : var2.invoke(null, var1);
      } catch (Throwable var4) {
         handleException(var4, null, var0, var1);
         return null;
      }
   }

   public static boolean matchesTypes(Class[] var0, Class[] var1) {
      if (var0.length != var1.length) {
         return false;
      } else {
         for (int var2 = 0; var2 < var1.length; var2++) {
            Class var3 = var0[var2];
            Class var4 = var1[var2];
            if (var3 != var4) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean setFieldValueInt(Object var0, ReflectorField var1, int var2) {
      try {
         Field var3 = var1.getTargetField();
         if (var3 == null) {
            return false;
         } else {
            var3.setInt(var0, var2);
            return true;
         }
      } catch (Throwable var4) {
         Log.error("", var4);
         return false;
      }
   }

   public static void callVoid(Object var0, ReflectorMethod var1, Object... var2) {
      try {
         if (var0 == null) {
            return;
         }

         Method var3 = var1.getTargetMethod();
         if (var3 == null) {
            return;
         }

         var3.invoke(var0, var2);
      } catch (Throwable var4) {
         handleException(var4, var0, var1, var2);
      }
   }

   public static void handleException(Throwable var0, Object var1, ReflectorMethod var2, Object[] var3) {
      if (var0 instanceof InvocationTargetException) {
         Throwable var4 = var0.getCause();
         if (var4 instanceof RuntimeException) {
            RuntimeException var5 = (RuntimeException)var4;
            throw var5;
         }

         Log.error("", var0);
      } else {
         Log.warn("*** Exception outside of method ***");
         Log.warn("Method deactivated: " + var2.getTargetMethod());
         var2.deactivate();
         if (var0 instanceof IllegalArgumentException) {
            Log.warn("*** IllegalArgumentException ***");
            Log.warn("Method: " + var2.getTargetMethod());
            Log.warn("Object: " + var1);
            Log.warn("Parameter classes: " + ArrayUtils.arrayToString(getClasses(var3)));
            Log.warn("Parameters: " + ArrayUtils.arrayToString(var3));
         }

         Log.warn("", var0);
      }
   }

   public static long getFieldValueLong(Object var0, ReflectorField var1, long var2) {
      try {
         Field var4 = var1.getTargetField();
         return var4 == null ? var2 : var4.getLong(var0);
      } catch (Throwable var7) {
         Log.error("", var7);
         return var2;
      }
   }

   public static double callDouble(Object var0, ReflectorMethod var1, Object... var2) {
      try {
         Method var3 = var1.getTargetMethod();
         if (var3 == null) {
            return 0.0;
         } else {
            Double var4 = (Double)var3.invoke(var0, var2);
            return var4;
         }
      } catch (Throwable var5) {
         handleException(var5, var0, var1, var2);
         return 0.0;
      }
   }

   public static ReflectorField[] getReflectorFields(ReflectorClass var0, Class var1, int var2) {
      ReflectorField[] var3 = new ReflectorField[var2];

      for (int var4 = 0; var4 < var3.length; var4++) {
         var3[var4] = new ReflectorField(var0, var1, var4);
      }

      return var3;
   }

   public static Object call(Object var0, ReflectorMethod var1, Object... var2) {
      try {
         Method var3 = var1.getTargetMethod();
         return var3 == null ? null : var3.invoke(var0, var2);
      } catch (Throwable var5) {
         handleException(var5, var0, var1, var2);
         return null;
      }
   }

   public static String callString(ReflectorMethod var0, Object... var1) {
      try {
         Method var2 = var0.getTargetMethod();
         return var2 == null ? null : (String)var2.invoke(null, var1);
      } catch (Throwable var4) {
         handleException(var4, null, var0, var1);
         return null;
      }
   }

   public static Object getFieldValue(ReflectorFields var0, int var1) {
      ReflectorField var2 = var0.getReflectorField(var1);
      return var2 == null ? null : getFieldValue(var2);
   }

   public static void dbgCall(boolean var0, String var1, ReflectorMethod var2, Object[] var3, Object var4) {
      String var5 = var2.getTargetMethod().getDeclaringClass().getName();
      String var6 = var2.getTargetMethod().getName();
      String var7 = "";
      if (var0) {
         var7 = " static";
      }

      Log.dbg(var1 + var7 + " " + var5 + "." + var6 + "(" + ArrayUtils.arrayToString(var3) + ") => " + var4);
   }

   public static int callInt(ReflectorMethod var0, Object... var1) {
      try {
         Method var2 = var0.getTargetMethod();
         if (var2 == null) {
            return 0;
         } else {
            Integer var3 = (Integer)var2.invoke(null, var1);
            return var3;
         }
      } catch (Throwable var4) {
         handleException(var4, null, var0, var1);
         return 0;
      }
   }

   public static int callInt(Object var0, ReflectorMethod var1, Object... var2) {
      try {
         Method var3 = var1.getTargetMethod();
         if (var3 == null) {
            return 0;
         } else {
            Integer var4 = (Integer)var3.invoke(var0, var2);
            return var4;
         }
      } catch (Throwable var5) {
         handleException(var5, var0, var1, var2);
         return 0;
      }
   }

   public static float getFieldValueFloat(Object var0, ReflectorField var1, float var2) {
      try {
         Field var3 = var1.getTargetField();
         return var3 == null ? var2 : var3.getFloat(var0);
      } catch (Throwable var5) {
         Log.error("", var5);
         return var2;
      }
   }

   public static boolean setFieldValue(ReflectorField var0, Object var1) {
      return setFieldValue(null, var0, var1);
   }

   public static boolean logEntry(String var0) {
      LOGGER.info("[OptiFine] " + var0);
      return true;
   }

   public static boolean callBoolean(Object var0, ReflectorMethod var1, Object... var2) {
      try {
         Method var3 = var1.getTargetMethod();
         if (var3 == null) {
            return false;
         } else {
            Boolean var4 = (Boolean)var3.invoke(var0, var2);
            return var4;
         }
      } catch (Throwable var5) {
         handleException(var5, var0, var1, var2);
         return false;
      }
   }

   public static void callVoid(ReflectorMethod var0, Object... var1) {
      try {
         Method var2 = var0.getTargetMethod();
         if (var2 == null) {
            return;
         }

         var2.invoke(null, var1);
      } catch (Throwable var3) {
         handleException(var3, null, var0, var1);
      }
   }

   public static float callFloat(Object var0, ReflectorMethod var1, Object... var2) {
      try {
         Method var3 = var1.getTargetMethod();
         if (var3 == null) {
            return 0.0F;
         } else {
            Float var4 = (Float)var3.invoke(var0, var2);
            return var4;
         }
      } catch (Throwable var5) {
         handleException(var5, var0, var1, var2);
         return 0.0F;
      }
   }

   public static void dbgCallVoid(boolean var0, String var1, ReflectorMethod var2, Object[] var3) {
      String var4 = var2.getTargetMethod().getDeclaringClass().getName();
      String var5 = var2.getTargetMethod().getName();
      String var6 = "";
      if (var0) {
         var6 = " static";
      }

      Log.dbg(var1 + var6 + " " + var4 + "." + var5 + "(" + ArrayUtils.arrayToString(var3) + ")");
   }

   public static boolean postForgeBusEvent(ReflectorConstructor var0, Object... var1) {
      Object var2 = newInstance(var0, var1);
      return var2 == null ? false : postForgeBusEvent(var2);
   }

   public static float callFloat(ReflectorMethod var0, Object... var1) {
      try {
         Method var2 = var0.getTargetMethod();
         if (var2 == null) {
            return 0.0F;
         } else {
            Float var3 = (Float)var2.invoke(null, var1);
            return var3;
         }
      } catch (Throwable var4) {
         handleException(var4, null, var0, var1);
         return 0.0F;
      }
   }

   public static Object newInstance(ReflectorConstructor var0, Object... var1) {
      Constructor var2 = var0.getTargetConstructor();
      if (var2 == null) {
         return null;
      } else {
         try {
            return var2.newInstance(var1);
         } catch (Throwable var4) {
            handleException(var4, var0, var1);
            return null;
         }
      }
   }

   public static boolean setFieldValue(Object var0, ReflectorField var1, Object var2) {
      try {
         Field var3 = var1.getTargetField();
         if (var3 == null) {
            return false;
         } else {
            var3.set(var0, var2);
            return true;
         }
      } catch (Throwable var4) {
         Log.error("", var4);
         return false;
      }
   }

   public static boolean registerResolvable(final String var0) {
      IResolvable var1 = new IResolvable() {
         @Override
         public void resolve() {
            Reflector.LOGGER.info("[OptiFine] " + var0);
         }
      };
      ReflectorResolver.register(var1);
      return true;
   }

   public static String callString(Object var0, ReflectorMethod var1, Object... var2) {
      try {
         Method var3 = var1.getTargetMethod();
         return var3 == null ? null : (String)var3.invoke(var0, var2);
      } catch (Throwable var5) {
         handleException(var5, var0, var1, var2);
         return null;
      }
   }

   public static boolean callBoolean(ReflectorMethod var0, Object... var1) {
      try {
         Method var2 = var0.getTargetMethod();
         if (var2 == null) {
            return false;
         } else {
            Boolean var3 = (Boolean)var2.invoke(null, var1);
            return var3;
         }
      } catch (Throwable var4) {
         handleException(var4, null, var0, var1);
         return false;
      }
   }

   public static Object getFieldValue(ReflectorField var0) {
      return getFieldValue(null, var0);
   }

   public static Object getFieldValue(Object var0, ReflectorField var1) {
      try {
         Field var2 = var1.getTargetField();
         return var2 == null ? null : var2.get(var0);
      } catch (Throwable var4) {
         Log.error("", var4);
         return null;
      }
   }

   public static Object[] getClasses(Object[] var0) {
      if (var0 == null) {
         return new Class[0];
      } else {
         Class[] var1 = new Class[var0.length];

         for (int var2 = 0; var2 < var1.length; var2++) {
            Object var3 = var0[var2];
            if (var3 != null) {
               var1[var2] = var3.getClass();
            }
         }

         return var1;
      }
   }
}
