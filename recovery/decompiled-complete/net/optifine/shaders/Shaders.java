package net.optifine.shaders;

import com.google.common.base.Charsets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.profiler.Profiler;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.optifine.CustomBlockLayers;
import net.optifine.CustomColors;
import net.optifine.GlErrors;
import net.optifine.Lang;
import net.optifine.config.ConnectedParser;
import net.optifine.expr.IExpressionBool;
import net.optifine.reflect.Reflector;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.config.EnumShaderOption;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.shaders.config.MacroState;
import net.optifine.shaders.config.PropertyDefaultFastFancyOff;
import net.optifine.shaders.config.PropertyDefaultTrueFalse;
import net.optifine.shaders.config.RenderScale;
import net.optifine.shaders.config.ScreenShaderOptions;
import net.optifine.shaders.config.ShaderLine;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderPackParser;
import net.optifine.shaders.config.ShaderParser;
import net.optifine.shaders.config.ShaderProfile;
import net.optifine.shaders.uniform.CustomUniforms;
import net.optifine.shaders.uniform.ShaderUniform1f;
import net.optifine.shaders.uniform.ShaderUniform1i;
import net.optifine.shaders.uniform.ShaderUniform2i;
import net.optifine.shaders.uniform.ShaderUniform3f;
import net.optifine.shaders.uniform.ShaderUniform4f;
import net.optifine.shaders.uniform.ShaderUniform4i;
import net.optifine.shaders.uniform.ShaderUniformM4;
import net.optifine.shaders.uniform.ShaderUniforms;
import net.optifine.shaders.uniform.Smoother;
import net.optifine.texture.InternalFormat;
import net.optifine.texture.PixelFormat;
import net.optifine.texture.PixelType;
import net.optifine.texture.TextureType;
import net.optifine.util.EntityUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;
import net.optifine.util.TimedEvent;
import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBGeometryShader4;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector4f;
import recovered.unidentified.UnidentifiedClass1227;
import recovered.unidentified.UnidentifiedClass1534;
import recovered.unidentified.UnidentifiedClass1595;
import recovered.unidentified.UnidentifiedClass4671;

public class Shaders {
   public static float field_0203 = 0.0F;
   public static long field_0350 = 525926L & -5287475054529740800L;
   public static Program ProgramSkyBasic = Shaders.programs.makeGbuffers("gbuffers_skybasic", Shaders.ProgramBasic);
   public static ShaderUniform1i field_0330 = Shaders.shaderUniforms.make1i("specular");
   public static float[] moonPosition = new float[4];
   public static long field_0082 = -8260162985003056896L & 8260162983209422890L;
   public static PropertyDefaultTrueFalse field_0351 = new PropertyDefaultTrueFalse("rain.depth", "Rain Depth", 0);
   public static String[] formatNames = new String[]{
      "R8",
      "RG8",
      "RGB8",
      "RGBA8",
      "R8_SNORM",
      "RG8_SNORM",
      "RGB8_SNORM",
      "RGBA8_SNORM",
      "R16",
      "RG16",
      "RGB16",
      "RGBA16",
      "R16_SNORM",
      "RG16_SNORM",
      "RGB16_SNORM",
      "RGBA16_SNORM",
      "R16F",
      "RG16F",
      "RGB16F",
      "RGBA16F",
      "R32F",
      "RG32F",
      "RGB32F",
      "RGBA32F",
      "R32I",
      "RG32I",
      "RGB32I",
      "RGBA32I",
      "R32UI",
      "RG32UI",
      "RGB32UI",
      "RGBA32UI",
      "R3_G3_B2",
      "RGB5_A1",
      "RGB10_A2",
      "R11F_G11F_B10F",
      "RGB9_E5"
   };
   public static ShaderUniform1i field_0083 = Shaders.shaderUniforms.make1i("colortex4");
   public static ShaderUniform1i field_0376 = Shaders.shaderUniforms.make1i("colortex7");
   public static World currentWorld = null;
   public static boolean field_0204 = false;
   public static PropertyDefaultTrueFalse field_0238 = new PropertyDefaultTrueFalse("vignette", "Vignette", 0);
   public static boolean field_0129;
   public static ShaderUniform1i uniform_heldItemId2 = Shaders.shaderUniforms.make1i("heldItemId2");
   public static ShaderUniform1i field_0342 = Shaders.shaderUniforms.make1i("lightmap");
   public static PropertyDefaultTrueFalse configOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
   public static int field_0120;
   public static ShaderUniform1i uniform_instanceId = Shaders.shaderUniforms.make1i("instanceId");
   public static PropertyDefaultTrueFalse field_0340 = new PropertyDefaultTrueFalse("separateAo", "Separate AO", 0);
   public static ShaderUniform1i field_0028 = Shaders.shaderUniforms.make1i("colortex6");
   public static int field_0018 = Shaders.programs.getCount();
   public static boolean configSpecularMap = true;
   public static ShaderUniform1i field_0006 = Shaders.shaderUniforms.make1i("depthtex1");
   public static ShaderUniform1i field_0324 = Shaders.shaderUniforms.make1i("gdepthtex");
   public static ShaderUniform1i field_0228 = Shaders.shaderUniforms.make1i("gaux4");
   public static ShaderUniform1i uniform_worldTime = Shaders.shaderUniforms.make1i("worldTime");
   public static ShaderUniform1i uniform_isEyeInWater = Shaders.shaderUniforms.make1i("isEyeInWater");
   public static Program ProgramBasic = Shaders.programs.makeGbuffers("gbuffers_basic", Shaders.ProgramNone);
   public static float field_0243 = 160.0F;
   public static int activeProgramID = 0;
   public static ShaderUniform1f uniform_centerDepthSmooth = Shaders.shaderUniforms.make1f("centerDepthSmooth");
   public static PropertyDefaultTrueFalse shaderPackOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
   public static float field_0113 = 10.0F;
   public static Vector4f[] gbuffersClearColor = new Vector4f[8];
   public static ShaderUniform1i field_0097 = Shaders.shaderUniforms.make1i("shadow");
   public static Program ProgramBlock = Shaders.programs.makeGbuffers("gbuffers_block", Shaders.ProgramTerrain);
   public static ShaderUniforms shaderUniforms = new ShaderUniforms();
   public static float configHandDepthMul = 0.125F;
   public static Program ProgramWeather = Shaders.programs.makeGbuffers("gbuffers_weather", Shaders.ProgramTexturedLit);
   public static int usedDepthBuffers = 0;
   public static IntBuffer sfbDrawBuffers = nextIntBuffer(8);
   public static Program ProgramClouds = Shaders.programs.makeGbuffers("gbuffers_clouds", Shaders.ProgramTextured);
   public static ShaderUniform1i uniform_heldBlockLightValue2 = shaderUniforms.make1i("heldBlockLightValue2");
   public static ShaderUniform3f uniform_sunPosition = shaderUniforms.make3f("sunPosition");
   public static boolean field_0087;
   public static float field_0230 = 0.0F;
   public static IntBuffer dfbDepthTextures = nextIntBuffer(3);
   public static PropertyDefaultTrueFalse field_0072 = new PropertyDefaultTrueFalse("frustum.culling", "Frustum Culling", 0);
   public static String field_0090;
   public static float skyColorB;
   public static float[] moonPosModelView = new float[]{0.0F, -100.0F, 0.0F, 0.0F};
   public static int shadowMapWidth = 1024;
   public static float clearColorR;
   public static String[] field_0002 = new String[]{"Nearest", "Nearest-Nearest", "Nearest-Linear"};
   public static String noiseTexturePath = null;
   public static boolean isShadowPass = false;
   public static int configTexMagFilB = 0;
   public static float[] faModelViewInverse = new float[16];
   public static ShaderUniformM4 uniform_gbufferPreviousProjection = shaderUniforms.makeM4("gbufferPreviousProjection");
   public static Map<String, String> shaderPackResources = new HashMap<>();
   public static String glVersionString;
   public static ShaderUniform1i field_0163 = shaderUniforms.make1i("noisetex");
   public static int activeCompositeMipmapSetting = 0;
   public static float[] field_0000 = new float[16];
   public static Program ProgramFinal = Shaders.programs.makeComposite("final");
   public static ShaderOption[] shaderPackOptions = null;
   public static Program ProgramHand = Shaders.programs.makeGbuffers("gbuffers_hand", Shaders.ProgramTexturedLit);
   public static int field_0389;
   public static int atlasSizeX = 0;
   public static Program[] field_0304 = Shaders.programs.method_03396("deferred", 16);
   public static ShaderUniform1f uniform_eyeAltitude = shaderUniforms.make1f("eyeAltitude");
   public static FloatBuffer tempMatrixDirectBuffer = nextFloatBuffer(16);
   public static double cameraPositionX;
   public UnidentifiedClass1595 field_0384;
   public static ICustomTexture noiseTexture;
   public static String field_0130;
   public static boolean waterShadowEnabled = false;
   public static ShaderUniform3f uniform_fogColor = shaderUniforms.make3f("fogColor");
   public static int field_0369 = 0;
   public static ShaderUniform1f uniform_wetness = shaderUniforms.make1f("wetness");
   public static boolean[] shadowColorFilterNearest = new boolean[8];
   public static PropertyDefaultFastFancyOff shaderPackClouds = new PropertyDefaultFastFancyOff("clouds", "Clouds", 0);
   public static Program field_0316 = Shaders.programs.makeGbuffers("gbuffers_item", Shaders.ProgramTexturedLit);
   public static PropertyDefaultTrueFalse shaderPackBackFaceTranslucent = new PropertyDefaultTrueFalse("backFace.translucent", "Back-face Translucent", 0);
   public static int tangentAttrib = 12;
   public static boolean field_0348 = false;
   public static Program field_0219 = Shaders.programs.makeShadow("shadow_cutout", Shaders.ProgramShadow);
   public static ShaderUniform1i uniform_blockEntityId = shaderUniforms.make1i("blockEntityId");
   public static int field_0283 = 0;
   public static float field_0223 = 0.0F;
   public static FloatBuffer tempDirectFloatBuffer = nextFloatBuffer(16);
   public static ShaderUniformM4 uniform_shadowModelView = shaderUniforms.makeM4("shadowModelView");
   public static int field_0152 = 16;
   public static int sfb = 0;
   public static boolean field_0085 = false;
   public static ShaderUniform1i field_0066 = shaderUniforms.make1i("normals");
   public static ShaderUniform1f uniform_rainStrength = shaderUniforms.make1f("rainStrength");
   public static IntBuffer dfbDrawBuffers = nextIntBuffer(8);
   public static ShaderUniform1f uniform_near = shaderUniforms.make1f("near");
   public static boolean renderItemKeepDepthMask = false;
   public static float fogColorB;
   public static PropertyDefaultTrueFalse configOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
   public static ShaderUniformM4 uniform_shadowProjectionInverse = shaderUniforms.makeM4("shadowProjectionInverse");
   public static ShaderUniform1i field_0100 = shaderUniforms.make1i("gaux1");
   public static Program activeProgram = Shaders.ProgramNone;
   public static boolean isRenderingDfb = false;
   public static int configTexMinFilN = 0;
   public static ShaderUniform1i field_0347 = shaderUniforms.make1i("texture");
   public static ShaderUniform1i uniform_frameCounter = shaderUniforms.make1i("frameCounter");
   public static ShaderUniformM4 uniform_gbufferProjectionInverse = shaderUniforms.makeM4("gbufferProjectionInverse");
   public static ShaderUniform1f uniform_viewHeight = shaderUniforms.make1f("viewHeight");
   public static int[] gbuffersFormat = new int[8];
   public static FloatBuffer modelView = nextFloatBuffer(16);
   public static int[] terrainTextureSize = new int[2];
   public static float sunAngle = 0.0F;
   public static boolean[] field_0136 = new boolean[8];
   public static int field_0322;
   public static Program ProgramTextured = Shaders.programs.makeGbuffers("gbuffers_textured", ProgramBasic);
   public static int midTexCoordAttrib = 11;
   public static int fogMode = 0;
   public static PropertyDefaultTrueFalse field_0019 = new PropertyDefaultTrueFalse("sun", "Sun", 0);
   public static boolean[] shadowFilterNearest = new boolean[2];
   public static boolean isRenderingWorld = false;
   public static PropertyDefaultTrueFalse field_0307 = new PropertyDefaultTrueFalse("shadowTranslucent", "Shadow Translucent", 0);
   public static Program field_0262 = Shaders.programs.makeShadow("shadow_solid", Shaders.ProgramShadow);
   public static String glRendererString;
   public static boolean hasGlGenMipmap = false;
   public static FloatBuffer field_0336 = nextFloatBuffer(16);
   public static int[] texMagFilValue = new int[]{9728, 9729};
   public static float[] sunPosition = new float[4];
   public static double cameraPositionZ;
   public static float clearColorG;
   public static float[] tempMat = new float[16];
   public static float clearColorB;
   public static IntBuffer drawBuffersNone = (IntBuffer)((Buffer)nextIntBuffer(8)).limit(0);
   public static int field_0279 = 0;
   public static ShaderUniform1i field_0337 = shaderUniforms.make1i("gaux3");
   public static List<Integer> shaderPackDimensions = new ArrayList<>();
   public static float configShadowResMul = 1.0F;
   public static ShaderProfile[] shaderPackProfiles = null;
   public static int field_0366;
   public static float[] faModelView = new float[16];
   public static boolean isHandRenderedMain;
   public static float field_0288 = 0.0F;
   public static ShaderUniformM4 uniform_gbufferModelView = shaderUniforms.makeM4("gbufferModelView");
   public static long diffSystemTime = -405421908896710544L & 545266703L;
   public static int configAntialiasingLevel = 0;
   public static FloatBuffer projectionInverse = nextFloatBuffer(16);
   public static Program ProgramNone = Shaders.programs.getProgramNone();
   public static ContextCapabilities capabilities;
   public static ITextureObject defaultTexture = null;
   public static ICustomTexture[] customTexturesComposite = null;
   public static String field_0051;
   public static int shadowMapHeight = 1024;
   public static PropertyDefaultTrueFalse shaderPackBackFaceCutout = new PropertyDefaultTrueFalse("backFace.cutout", "Back-face Cutout", 0);
   public static ShaderUniform1i field_0108 = shaderUniforms.make1i("shadowcolor0");
   public static float shadowAngle = 0.0F;
   public static ShaderUniformM4 uniform_gbufferPreviousModelView = shaderUniforms.makeM4("gbufferPreviousModelView");
   public static boolean field_0027 = false;
   public static boolean field_0395 = false;
   public static Programs programs = new Programs();
   public static ShaderUniform1i field_0252 = shaderUniforms.make1i("shadowtex1");
   public static int usedColorBuffers = 0;
   public static boolean configNormalMap = true;
   public static EntityRenderer entityRenderer;
   public static boolean isRenderingFirstPersonHand;
   public static ShaderUniform1f uniform_frameTime = shaderUniforms.make1f("frameTime");
   public static Program field_0303 = programs.makeGbuffers("gbuffers_armor_glint", ProgramTextured);
   public static boolean field_0096 = false;
   public static ShaderUniform1i uniform_moonPhase = shaderUniforms.make1i("moonPhase");
   public static boolean needResizeShadow = false;
   public static double previousCameraPositionZ;
   public static ShaderUniform1i uniform_fogMode = shaderUniforms.make1i("fogMode");
   public static int cameraOffsetX;
   public static ShaderUniform1i field_0064 = shaderUniforms.make1i("colortex2");
   public static float field_0011 = 0.0F;
   public static int[] formatIds = new int[]{
      33321,
      33323,
      32849,
      32856,
      36756,
      36757,
      36758,
      36759,
      33322,
      33324,
      32852,
      32859,
      36760,
      36761,
      36762,
      36763,
      33325,
      33327,
      34843,
      34842,
      33326,
      33328,
      34837,
      34836,
      33333,
      33339,
      36227,
      36226,
      33334,
      33340,
      36209,
      36208,
      10768,
      32855,
      32857,
      35898,
      35901
   };
   public static boolean skipRenderHandMain;
   public static boolean skipRenderHandOff;
   public static Program field_0162 = programs.makeGbuffers("gbuffers_terrain_cutout_mip", Shaders.ProgramTerrain);
   public static ShaderUniform1i field_0063 = shaderUniforms.make1i("composite");
   public static int dfb = 0;
   public static ShaderUniform1i field_0057 = shaderUniforms.make1i("depthtex2");
   public static ByteBuffer bigBuffer = (ByteBuffer)((Buffer)BufferUtils.createByteBuffer(Shaders.field_0270)).limit(0);
   public static FloatBuffer modelViewInverse = nextFloatBuffer(16);
   public static Program ProgramWater = programs.makeGbuffers("gbuffers_water", Shaders.ProgramTerrain);
   public static IntBuffer sfbColorTextures = nextIntBuffer(8);
   public static float blockLightLevel05 = 0.5F;
   public static ShaderUniform1f uniform_screenBrightness = shaderUniforms.make1f("screenBrightness");
   public static float field_0079 = -1.0F;
   public static int usedColorAttachs = 0;
   public static ShaderUniformM4 uniform_gbufferProjection = shaderUniforms.makeM4("gbufferProjection");
   public static ShaderUniform1i field_0241 = shaderUniforms.make1i("colortex0");
   public static int[] colorTextureImageUnit = new int[]{0, 1, 2, 3, 7, 8, 9, 10};
   public static ShaderUniform1i field_0346 = shaderUniforms.make1i("depthtex0");
   public static boolean[] field_0217 = new boolean[2];
   public static long field_0124 = 2154365130530557008L & 135319682L;
   public static ShaderUniform1i field_0067 = shaderUniforms.make1i("colortex1");
   public static int renderDisplayHeight = 0;
   public static FloatBuffer field_0294 = nextFloatBuffer(16);
   public static int renderWidth = 0;
   public static float[] upPosition = new float[4];
   public static ShaderUniform1i field_0026 = shaderUniforms.make1i("gdepth");
   public static float[] field_0353 = new float[16];
   public static int renderDisplayWidth = 0;
   public static long field_0143 = -6906988009379312123L & 1082261706L;
   public static PropertyDefaultTrueFalse shaderPackBackFaceCutoutMipped = new PropertyDefaultTrueFalse("backFace.cutoutMipped", "Back-face Cutout Mipped", 0);
   public static Program field_0078 = programs.makeGbuffers("gbuffers_entities_glowing", Shaders.ProgramEntities);
   public static Program ProgramHandWater = programs.makeGbuffers("gbuffers_hand_water", ProgramHand);
   public static float field_0265 = 200.0F;
   public static Program ProgramTerrain = programs.makeGbuffers("gbuffers_terrain", Shaders.ProgramTexturedLit);
   public static Program field_0280 = programs.makeVirtual("deferred_pre");
   public static ShaderUniform1i field_0180 = shaderUniforms.make1i("shadowcolor1");
   public static File shaderPacksDir = new File(Minecraft.getMinecraft().mcDataDir, "shaderpacks");
   public static boolean configTweakBlockDamage = false;
   public static IntBuffer activeDrawBuffers = null;
   public static boolean shaderPackLoaded = false;
   public static int shadowPassInterval = 0;
   public static int field_0145;
   public static boolean[] shadowColorMipmapEnabled = new boolean[8];
   public static ShaderUniform4f uniform_entityColor = shaderUniforms.make4f("entityColor");
   public static boolean noiseTextureEnabled = false;
   public static float fogDensity = 0.0F;
   public static boolean field_0338 = false;
   public static ShaderUniform1f uniform_far = shaderUniforms.make1f("far");
   public static boolean centerDepthSmoothEnabled = false;
   public static float fogColorG;
   public static int atlasSizeY = 0;
   public static ShaderUniform1f uniform_viewWidth = shaderUniforms.make1f("viewWidth");
   public static ProgramStack programStack = new ProgramStack();
   public UnidentifiedClass1227 field_0164;
   public static int configTexMinFilB = 0;
   public static int[] texMinFilValue = new int[]{9728, 9984, 9986};
   public static boolean saveFinalShaders = System.getProperty("shaders.debug.save", "false").equals("true");
   public static ShaderUniform1i field_0378 = shaderUniforms.make1i("tex");
   public static float field_0036 = 0.0F;
   public static ShaderUniform1i uniform_entityId = shaderUniforms.make1i("entityId");
   public static float field_0326 = 600.0F;
   public static ShaderUniform3f uniform_shadowLightPosition = shaderUniforms.make3f("shadowLightPosition");
   public static ShaderUniform1i field_0374 = shaderUniforms.make1i("shadowtex0");
   public static int configTexMagFilS = 0;
   public static float field_0268 = -1.0F;
   public static int field_0201;
   public static FloatBuffer field_0095 = nextFloatBuffer(16);
   public static float[] faProjectionInverse = new float[16];
   public static IntBuffer drawBuffersColorAtt0 = (IntBuffer)((Buffer)nextIntBuffer(8).put(36064)).position(0).limit(1);
   public static Properties loadedShaders = null;
   public static ShaderUniform1i field_0141 = shaderUniforms.make1i("gaux2");
   public static ShaderUniform1i uniform_heldBlockLightValue = shaderUniforms.make1i("heldBlockLightValue");
   public static int field_0086 = 3;
   public static ShaderUniform1i field_0109 = shaderUniforms.make1i("gcolor");
   public Profiler field_0278;
   public static float field_0043 = 90.0F;
   public static ShaderUniform1i field_0263 = shaderUniforms.make1i("colortex5");
   public static float[] shadowLightPositionVector = new float[4];
   public static int noiseTextureResolution = 256;
   public static PropertyDefaultTrueFalse shaderPackBackFaceSolid = new PropertyDefaultTrueFalse("backFace.solid", "Back-face Solid", 0);
   public static ShaderUniform1i uniform_worldDay = shaderUniforms.make1i("worldDay");
   public static float[] field_0149 = new float[16];
   public static int field_0282;
   public static ShaderUniform1i field_0093 = shaderUniforms.make1i("watershadow");
   public static float[] sunPosModelView = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
   public static boolean field_0390 = false;
   public static float fogColorR;
   public static ShaderUniform3f uniform_moonPosition = shaderUniforms.make3f("moonPosition");
   public static Program field_0171 = programs.makeGbuffers("gbuffers_terrain_solid", ProgramTerrain);
   public static int field_0134;
   public static double previousCameraPositionY;
   public static PropertyDefaultTrueFalse shaderPackOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
   public static long field_0255 = 82953240L & 2106644226455635974L;
   public static PropertyDefaultTrueFalse field_0156 = new PropertyDefaultTrueFalse("moon", "Moon", 0);
   public static String field_0114;
   public static IntBuffer dfbColorTextures = nextIntBuffer(16);
   public static ICustomTexture[] customTexturesGbuffers = null;
   public static Program field_0301 = programs.makeVirtual("composite_pre");
   public static Map<String, IExpressionBool> shaderPackProgramConditions = new HashMap<>();
   public static int field_0137;
   public static boolean field_0107 = false;
   public static float[] faProjection = new float[16];
   public static int field_0234;
   public static float skyColorR;
   public static Program[] ProgramsAll = programs.getPrograms();
   public static String currentShaderName;
   public static FloatBuffer projection = nextFloatBuffer(16);
   public static int spShadowMapHeight = 1024;
   public static Pattern field_0246 = Pattern.compile("\\s*([\\w:]+)\\s*=\\s*([-]?\\d+)\\s*");
   public static ShaderUniform4i uniform_blendFunc = shaderUniforms.make4i("blendFunc");
   public static FloatBuffer field_0127 = nextFloatBuffer(16);
   public static String[] field_0302 = new String[]{"Nearest", "Linear"};
   public static ShaderUniform2i uniform_eyeBrightnessSmooth = shaderUniforms.make2i("eyeBrightnessSmooth");
   public static int field_0144 = 0;
   public static IntBuffer sfbDepthTextures = nextIntBuffer(2);
   public static boolean configCloudShadow = false;
   public static Map<Block, Integer> mapBlockToEntityData;
   public static boolean configShadowClipFrustrum = true;
   public static ShaderUniform3f uniform_skyColor = shaderUniforms.make3f("skyColor");
   public static double cameraPositionY;
   public static CustomUniforms customUniforms = null;
   public static int configTexMinFilS = 0;
   public static ShaderUniform3f uniform_previousCameraPosition = shaderUniforms.make3f("previousCameraPosition");
   public static ShaderUniform1f uniform_shadowAngle = shaderUniforms.make1f("shadowAngle");
   public static ShaderUniform2i uniform_eyeBrightness = shaderUniforms.make2i("eyeBrightness");
   public static ShaderUniform1i field_0323 = shaderUniforms.make1i("colortex3");
   public static String[] field_0189 = new String[]{"gbuffers", "composite", "deferred"};
   public static int cameraOffsetZ;
   public static Program ProgramShadow = programs.makeShadow("shadow", ProgramNone);
   public static boolean isRenderingSky = false;
   public static ShaderUniformM4 uniform_shadowModelViewInverse = shaderUniforms.makeM4("shadowModelViewInverse");
   public static boolean field_0055 = false;
   public static boolean field_0154 = false;
   public static Program ProgramDamagedBlock = programs.makeGbuffers("gbuffers_damagedblock", ProgramTerrain);
   public static ShaderUniformM4 uniform_shadowProjection = shaderUniforms.makeM4("shadowProjection");
   public static ShaderUniform1f uniform_blindness = shaderUniforms.make1f("blindness");
   public static ShaderUniform3f uniform_upPosition = shaderUniforms.make3f("upPosition");
   public static float field_0218 = 0.0F;
   public static int spShadowMapWidth = 1024;
   public static ShaderUniform1f uniform_frameTimeCounter = shaderUniforms.make1f("frameTimeCounter");
   public static boolean field_0329 = false;
   public static float skyColorG;
   public static int shadowPassCounter = 0;
   public static double previousCameraPositionX;
   public static int renderHeight = 0;
   public static ShaderUniform2i uniform_atlasSize = shaderUniforms.make2i("atlasSize");
   public static float blockLightLevel06 = 0.6F;
   public static int entityAttrib = 10;
   public static int countResetDisplayLists = 0;
   public static int entityDataIndex = 0;
   public static boolean fogEnabled = true;
   public static float centerDepthSmooth = 0.0F;
   public static Program field_0352 = programs.makeGbuffers("gbuffers_terrain_cutout", ProgramTerrain);
   public static boolean field_0205 = false;
   public static float[] field_0212 = new float[16];
   public static Program field_0328 = programs.makeGbuffers("gbuffers_spidereyes", ProgramTextured);
   public static FloatBuffer field_0314 = nextFloatBuffer(16);
   public static Program ProgramTexturedLit = programs.makeGbuffers("gbuffers_textured_lit", ProgramTextured);
   public static ShaderUniform1i uniform_heldItemId = shaderUniforms.make1i("heldItemId");
   public static float[] upPosModelView = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
   public static Program ProgramSkyTextured = programs.makeGbuffers("gbuffers_skytextured", ProgramTextured);
   public static PropertyDefaultTrueFalse shaderPackDynamicHandLight = new PropertyDefaultTrueFalse("dynamicHandLight", "Dynamic Hand Light", 0);
   public static ICustomTexture[] customTexturesDeferred = null;
   public static Program field_0050 = programs.makeGbuffers("gbuffers_beaconbeam", ProgramTextured);
   public static float configRenderResMul = 1.0F;
   public static boolean field_0158 = false;
   public static int usedShadowColorBuffers = 0;
   public static boolean field_0367 = false;
   public static int configTexMagFilN = 0;
   public static ShaderUniform1i field_0155 = shaderUniforms.make1i("gnormal");
   public static ShaderUniform1i uniform_terrainIconSize = shaderUniforms.make1i("terrainIconSize");
   public static PropertyDefaultTrueFalse field_0186 = new PropertyDefaultTrueFalse("beacon.beam.depth", "Rain Depth", 0);
   public static int field_0151 = 0;
   public static float field_0359 = 2.0F;
   public static int field_0270 = (285 + 8 * field_0018) * 4;
   public static int[] entityData = new int[32];
   public static Program[] field_0053 = programs.method_03388("composite", 16);
   public static String glVendorString;
   public static boolean field_0173 = true;
   public static ShaderUniform3f uniform_cameraPosition = shaderUniforms.make3f("cameraPosition");
   public static float centerDepth = 0.0F;
   public static ShaderUniform1f uniform_aspectRatio = shaderUniforms.make1f("aspectRatio");
   public static float field_0327 = 0.0F;
   public static Minecraft mc;
   public static int field_0182 = 1;
   public static int preShadowPassThirdPersonView;
   public static ShaderUniform1f uniform_fogDensity = shaderUniforms.make1f("fogDensity");
   public static float centerDepthSmoothHalflife = 1.0F;
   public static boolean isEntitiesGlowing = false;
   public static ShaderUniform1i field_0208 = shaderUniforms.make1i("shadowcolor");
   public static FlipTextures dfbColorTexturesFlip = new FlipTextures(dfbColorTextures, 8);
   public static Program field_0349 = field_0053[0];
   public static IShaderPack shaderPack = null;
   public static ShaderUniform1f uniform_sunAngle = shaderUniforms.make1f("sunAngle");
   public static boolean lightmapEnabled = false;
   public static String field_0138;
   public static float field_0344 = 0.0F;
   public static Program field_0146 = field_0304[0];
   public static boolean field_0091 = false;
   public static int usedShadowDepthBuffers = 0;
   public static FloatBuffer field_0023 = nextFloatBuffer(16);
   public static float[] shadowLightPosition = new float[4];
   public static float field_0178 = 0.0F;
   public static Properties shadersConfig = null;
   public static ShaderUniform2i uniform_terrainTextureSize = shaderUniforms.make2i("terrainTextureSize");
   public static ShaderUniform1f uniform_nightVision = shaderUniforms.make1f("nightVision");
   public static Program ProgramEntities = programs.makeGbuffers("gbuffers_entities", ProgramTexturedLit);
   public static ShaderUniform1i uniform_hideGUI = shaderUniforms.make1i("hideGUI");
   public static Set<String> shaderPackOptionSliders = null;
   public static float blockLightLevel08 = 0.8F;
   public static Map<String, ScreenShaderOptions> shaderPackGuiScreens = null;
   public static int field_0225 = 0;
   public static File configFile = new File(Minecraft.getMinecraft().mcDataDir, "optionsshaders.txt");
   public static PropertyDefaultTrueFalse field_0032 = new PropertyDefaultTrueFalse("underwaterOverlay", "Underwater Overlay", 0);
   public static ShaderUniformM4 uniform_gbufferModelViewInverse = shaderUniforms.makeM4("gbufferModelViewInverse");
   public static boolean isHandRenderedOff;
   public static float field_0033 = 0.0F;
   public static float sunPathRotation = 0.0F;
   public static boolean[] shadowMipmapEnabled = new boolean[2];
   public static boolean field_0047;

   public static void pushEntity(int var0) {
      entityDataIndex++;
      entityData[entityDataIndex * 2] = var0 & 65535;
      entityData[entityDataIndex * 2 + 1] = 0;
   }

   public static void startup(Minecraft var0) {
      checkShadersModInstalled();
      mc = var0;
      mc = Minecraft.getMinecraft();
      capabilities = GLContext.getCapabilities();
      glVersionString = GL11.glGetString(7938);
      glVendorString = GL11.glGetString(7936);
      glRendererString = GL11.glGetString(7937);
      SMCLog.info("OpenGL Version: " + glVersionString);
      SMCLog.info("Vendor:  " + glVendorString);
      SMCLog.info("Renderer: " + glRendererString);
      SMCLog.info(
         "Capabilities: "
            + (capabilities.OpenGL20 ? " 2.0 " : " - ")
            + (capabilities.OpenGL21 ? " 2.1 " : " - ")
            + (capabilities.OpenGL30 ? " 3.0 " : " - ")
            + (capabilities.OpenGL32 ? " 3.2 " : " - ")
            + (capabilities.OpenGL40 ? " 4.0 " : " - ")
      );
      SMCLog.info("GL_MAX_DRAW_BUFFERS: " + GL11.glGetInteger(34852));
      SMCLog.info("GL_MAX_COLOR_ATTACHMENTS_EXT: " + GL11.glGetInteger(36063));
      SMCLog.info("GL_MAX_TEXTURE_IMAGE_UNITS: " + GL11.glGetInteger(34930));
      hasGlGenMipmap = capabilities.OpenGL30;
      loadConfig();
   }

   public static void disableTexture2D() {
      if (isRenderingSky) {
         useProgram(ProgramSkyBasic);
      } else if (activeProgram == ProgramTextured || activeProgram == ProgramTexturedLit) {
         useProgram(ProgramBasic);
      }
   }

   public static String getEnumShaderOption(EnumShaderOption var0) {
      switch (Shaders$1.$SwitchMap$net$optifine$shaders$config$EnumShaderOption[var0.ordinal()]) {
         case 1:
            return Integer.toString(configAntialiasingLevel);
         case 2:
            return Boolean.toString(configNormalMap);
         case 3:
            return Boolean.toString(configSpecularMap);
         case 4:
            return Float.toString(configRenderResMul);
         case 5:
            return Float.toString(configShadowResMul);
         case 6:
            return Float.toString(configHandDepthMul);
         case 7:
            return Boolean.toString(configCloudShadow);
         case 8:
            return configOldHandLight.getPropertyValue();
         case 9:
            return configOldLighting.getPropertyValue();
         case 10:
            return currentShaderName;
         case 11:
            return Boolean.toString(configTweakBlockDamage);
         case 12:
            return Boolean.toString(configShadowClipFrustrum);
         case 13:
            return Integer.toString(configTexMinFilB);
         case 14:
            return Integer.toString(configTexMinFilN);
         case 15:
            return Integer.toString(configTexMinFilS);
         case 16:
            return Integer.toString(configTexMagFilB);
         case 17:
            return Integer.toString(configTexMagFilB);
         case 18:
            return Integer.toString(configTexMagFilB);
         default:
            throw new IllegalArgumentException("Unknown option: " + var0);
      }
   }

   public static void glDisableWrapper(int var0) {
      GL11.glDisable(var0);
      if (var0 == 3553) {
         disableTexture2D();
      } else if (var0 == 2912) {
         disableFog();
      }
   }

   public static void setProgramUniform2i(ShaderUniform2i var0, int var1, int var2) {
      var0.setValue(var1, var2);
   }

   public static void beginSky() {
      isRenderingSky = true;
      fogEnabled = true;
      setDrawBuffers(dfbDrawBuffers);
      useProgram(ProgramSkyTextured);
      pushEntity(-2, 0);
   }

   public static void resizeShadow() {
      needResizeShadow = false;
      shadowMapWidth = Math.round(spShadowMapWidth * configShadowResMul);
      shadowMapHeight = Math.round(spShadowMapHeight * configShadowResMul);
      setupShadowFrameBuffer();
   }

   public static void method_02324() {
      if (isShadowPass) {
         checkGLError("shadow endRender");
      } else {
         if (!field_0107) {
            method_02178();
         }

         isRenderingWorld = false;
         GlStateManager.colorMask(true, true, true, true);
         useProgram(ProgramNone);
         RenderHelper.disableStandardItemLighting();
         checkGLError("endRender end");
      }
   }

   public static ICustomTexture loadCustomTexture(int var0, String var1) {
      if (var1 == null) {
         return null;
      } else {
         var1 = var1.trim();
         return var1.indexOf(58) >= 0
            ? loadCustomTextureLocation(var0, var1)
            : (var1.indexOf(32) >= 0 ? method_02184(var0, var1) : loadCustomTextureShaders(var0, var1));
      }
   }

   public static void setClearColor(float var0, float var1, float var2, float var3) {
      GlStateManager.clearColor(var0, var1, var2, var3);
      clearColorR = var0;
      clearColorG = var1;
      clearColorB = var2;
   }

   public static boolean isOldLighting() {
      return !configOldLighting.isDefault() ? configOldLighting.isTrue() : (!shaderPackOldLighting.isDefault() ? shaderPackOldLighting.isTrue() : true);
   }

   public static void method_02182() {
      useProgram(ProgramTexturedLit);
   }

   public static void storeConfig() {
      SMCLog.info("Save shaders configuration.");
      if (shadersConfig == null) {
         shadersConfig = new PropertiesOrdered();
      }

      EnumShaderOption[] var0 = EnumShaderOption.values();

      for (int var1 = 0; var1 < var0.length; var1++) {
         EnumShaderOption var2 = var0[var1];
         String var3 = var2.getPropertyKey();
         String var4 = getEnumShaderOption(var2);
         shadersConfig.setProperty(var3, var4);
      }

      try {
         FileWriter var6 = new FileWriter(configFile);
         shadersConfig.store(var6, (String)null);
         var6.close();
      } catch (Exception var5) {
         SMCLog.severe("Error saving configuration: " + var5.getClass().getName() + ": " + var5.getMessage());
      }
   }

   public static ShaderOption[] method_02279(ShaderOption[] var0) {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var2 < var0.length; var2++) {
         ShaderOption var3 = var0[var2];
         if (var3.isVisible()) {
            var1.add(var3);
         }
      }

      return var1.toArray(new ShaderOption[var1.size()]);
   }

   public static void resetDisplayLists() {
      SMCLog.info("Reset model renderers");
      countResetDisplayLists++;
      SMCLog.info("Reset world renderers");
      mc.renderGlobal.loadRenderers();
   }

   public static String getShaderPackName() {
      return shaderPack == null ? null : (shaderPack instanceof ShaderPackNone ? null : shaderPack.getName());
   }

   public static int getDrawBuffer(Program var0, String var1, int var2) {
      int var3 = 0;
      if (var2 >= var1.length()) {
         return var3;
      } else {
         int var4 = var1.charAt(var2) - '0';
         if (var0 == ProgramShadow) {
            if (var4 >= 0 && var4 <= 1) {
               var3 = var4 + 36064;
               usedShadowColorBuffers = Math.max(usedShadowColorBuffers, var4);
            }

            return var3;
         } else {
            if (var4 >= 0 && var4 <= 7) {
               var0.getToggleColorTextures()[var4] = true;
               var3 = var4 + 36064;
               usedColorAttachs = Math.max(usedColorAttachs, var4);
               usedColorBuffers = Math.max(usedColorBuffers, var4);
            }

            return var3;
         }
      }
   }

   public static void setCameraOffset(Entity var0) {
      if (var0 == null) {
         cameraOffsetX = 0;
         cameraOffsetZ = 0;
      } else {
         cameraOffsetX = (int)var0.s / 1000 * 1000;
         cameraOffsetZ = (int)var0.u / 1000 * 1000;
      }
   }

   public static FloatBuffer nextFloatBuffer(int var0) {
      ByteBuffer var1 = bigBuffer;
      int var2 = var1.limit();
      ((Buffer)var1).position(var2).limit(var2 + var0 * 4);
      return var1.asFloatBuffer();
   }

   public static void setEntityColor(float var0, float var1, float var2, float var3) {
      if (isRenderingWorld && !isShadowPass) {
         uniform_entityColor.setValue(var0, var1, var2, var3);
      }
   }

   public static String applyOptions(String var0, ShaderOption[] var1) {
      if (var1 != null && var1.length > 0) {
         for (int var2 = 0; var2 < var1.length; var2++) {
            ShaderOption var3 = var1[var2];
            if (var3.matchesLine(var0)) {
               var0 = var3.getSourceLine();
               break;
            }
         }

         return var0;
      } else {
         return var0;
      }
   }

   public static String translate(String var0, String var1) {
      String var2 = shaderPackResources.get(var0);
      return var2 == null ? var1 : var2;
   }

   public static void enableFog() {
      fogEnabled = true;
      setProgramUniform1i(uniform_fogMode, fogMode);
      setProgramUniform1f(uniform_fogDensity, fogDensity);
   }

   public static boolean shouldRenderClouds(GameSettings var0) {
      if (!shaderPackLoaded) {
         return true;
      } else {
         checkGLError("shouldRenderClouds");
         return isShadowPass ? configCloudShadow : var0.clouds > 0;
      }
   }

   public static void printChatAndLogError(String var0) {
      SMCLog.severe(var0);
      mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(var0));
   }

   public static IntBuffer fillIntBufferZero(IntBuffer var0) {
      int var1 = var0.limit();

      for (int var2 = var0.position(); var2 < var1; var2++) {
         var0.put(var2, 0);
      }

      return var0;
   }

   public static void setFogMode(int var0) {
      fogMode = var0;
      if (fogEnabled) {
         setProgramUniform1i(uniform_fogMode, var0);
      }
   }

   public static boolean printShaderLogInfo(int var0, String var1, List<String> var2) {
      IntBuffer var3 = BufferUtils.createIntBuffer(1);
      int var4 = GL20.glGetShaderi(var0, 35716);
      if (var4 <= 1) {
         return true;
      } else {
         for (int var5 = 0; var5 < var2.size(); var5++) {
            String var6 = (String)var2.get(var5);
            SMCLog.info("File: " + (var5 + 1) + " = " + var6);
         }

         String var7 = GL20.glGetShaderInfoLog(var0, var4);
         var7 = StrUtils.trim(var7, " \n\r\t");
         SMCLog.info("Shader info log: " + var1 + "\n" + var7);
         return false;
      }
   }

   public static void setEnumShaderOption(EnumShaderOption var0, String var1) {
      if (var1 == null) {
         var1 = var0.getValueDefault();
      }

      switch (Shaders$1.$SwitchMap$net$optifine$shaders$config$EnumShaderOption[var0.ordinal()]) {
         case 1:
            configAntialiasingLevel = Config.parseInt(var1, 0);
            break;
         case 2:
            configNormalMap = Config.parseBoolean(var1, true);
            break;
         case 3:
            configSpecularMap = Config.parseBoolean(var1, true);
            break;
         case 4:
            configRenderResMul = Config.parseFloat(var1, 1.0F);
            break;
         case 5:
            configShadowResMul = Config.parseFloat(var1, 1.0F);
            break;
         case 6:
            configHandDepthMul = Config.parseFloat(var1, 0.125F);
            break;
         case 7:
            configCloudShadow = Config.parseBoolean(var1, true);
            break;
         case 8:
            configOldHandLight.setPropertyValue(var1);
            break;
         case 9:
            configOldLighting.setPropertyValue(var1);
            break;
         case 10:
            currentShaderName = var1;
            break;
         case 11:
            configTweakBlockDamage = Config.parseBoolean(var1, true);
            break;
         case 12:
            configShadowClipFrustrum = Config.parseBoolean(var1, true);
            break;
         case 13:
            configTexMinFilB = Config.parseInt(var1, 0);
            break;
         case 14:
            configTexMinFilN = Config.parseInt(var1, 0);
            break;
         case 15:
            configTexMinFilS = Config.parseInt(var1, 0);
            break;
         case 16:
            configTexMagFilB = Config.parseInt(var1, 0);
            break;
         case 17:
            configTexMagFilB = Config.parseInt(var1, 0);
            break;
         case 18:
            configTexMagFilB = Config.parseInt(var1, 0);
            break;
         default:
            throw new IllegalArgumentException("Unknown option: " + var0);
      }
   }

   public static void setFogDensity(float var0) {
      fogDensity = var0;
      if (fogEnabled) {
         setProgramUniform1f(uniform_fogDensity, var0);
      }
   }

   public static void setCamera(float var0) {
      Entity var1 = mc.getRenderViewEntity();
      double var2 = var1.P + (var1.s - var1.P) * var0;
      double var4 = var1.Q + (var1.t - var1.Q) * var0;
      double var6 = var1.R + (var1.u - var1.R) * var0;
      updateCameraOffset(var1);
      cameraPositionX = var2 - cameraOffsetX;
      cameraPositionY = var4;
      cameraPositionZ = var6 - cameraOffsetZ;
      GL11.glGetFloat(2983, (FloatBuffer)((Buffer)projection).position(0));
      SMath.invertMat4FBFA(
         (FloatBuffer)((Buffer)projectionInverse).position(0), (FloatBuffer)((Buffer)projection).position(0), faProjectionInverse, faProjection
      );
      ((Buffer)projection).position(0);
      ((Buffer)projectionInverse).position(0);
      GL11.glGetFloat(2982, (FloatBuffer)((Buffer)modelView).position(0));
      SMath.invertMat4FBFA((FloatBuffer)((Buffer)modelViewInverse).position(0), (FloatBuffer)((Buffer)modelView).position(0), faModelViewInverse, faModelView);
      ((Buffer)modelView).position(0);
      ((Buffer)modelViewInverse).position(0);
      checkGLError("setCamera");
   }

   public static int setEntityData1(int var0) {
      entityData[entityDataIndex * 2] = entityData[entityDataIndex * 2] & 65535 | var0 << 16;
      return var0;
   }

   public static void nextAntialiasingLevel(boolean var0) {
      if (var0) {
         configAntialiasingLevel += 2;
         if (configAntialiasingLevel > 4) {
            configAntialiasingLevel = 0;
         }
      } else {
         configAntialiasingLevel -= 2;
         if (configAntialiasingLevel < 0) {
            configAntialiasingLevel = 4;
         }
      }

      configAntialiasingLevel = configAntialiasingLevel / 2 * 2;
      configAntialiasingLevel = Config.limit(configAntialiasingLevel, 0, 4);
   }

   public static void beginWeather() {
      if (!isShadowPass) {
         if (usedDepthBuffers >= 3) {
            GlStateManager.setActiveTexture(33996);
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
            GlStateManager.setActiveTexture(33984);
         }

         GlStateManager.enableDepth();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.enableAlpha();
         useProgram(ProgramWeather);
      }
   }

   public static void enableLightmap() {
      lightmapEnabled = true;
      if (activeProgram == ProgramTextured) {
         useProgram(ProgramTexturedLit);
      }
   }

   public static Program getProgram(String var0) {
      return programs.getProgram(var0);
   }

   public static void setupFrameBuffer() {
      if (dfb != 0) {
         EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
         GlStateManager.deleteTextures(dfbDepthTextures);
         GlStateManager.deleteTextures(dfbColorTextures);
      }

      dfb = EXTFramebufferObject.glGenFramebuffersEXT();
      GL11.glGenTextures((IntBuffer)((Buffer)dfbDepthTextures).clear().limit(usedDepthBuffers));
      GL11.glGenTextures((IntBuffer)((Buffer)dfbColorTextures).clear().limit(16));
      ((Buffer)dfbDepthTextures).position(0);
      ((Buffer)dfbColorTextures).position(0);
      EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
      GL20.glDrawBuffers(0);
      GL11.glReadBuffer(0);

      for (int var0 = 0; var0 < usedDepthBuffers; var0++) {
         GlStateManager.bindTexture(dfbDepthTextures.get(var0));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
         GL11.glTexParameteri(3553, 34891, 6409);
         GL11.glTexImage2D(3553, 0, 6402, renderWidth, renderHeight, 0, 6402, 5126, (FloatBuffer)null);
      }

      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
      GL20.glDrawBuffers(dfbDrawBuffers);
      GL11.glReadBuffer(0);
      checkGLError("FT d");

      for (int var2 = 0; var2 < usedColorBuffers; var2++) {
         GlStateManager.bindTexture(dfbColorTexturesFlip.getA(var2));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexImage2D(3553, 0, gbuffersFormat[var2], renderWidth, renderHeight, 0, getPixelFormat(gbuffersFormat[var2]), 33639, (ByteBuffer)null);
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var2, 3553, dfbColorTexturesFlip.getA(var2), 0);
         checkGLError("FT c");
      }

      for (int var3 = 0; var3 < usedColorBuffers; var3++) {
         GlStateManager.bindTexture(dfbColorTexturesFlip.getB(var3));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexImage2D(3553, 0, gbuffersFormat[var3], renderWidth, renderHeight, 0, getPixelFormat(gbuffersFormat[var3]), 33639, (ByteBuffer)null);
         checkGLError("FT ca");
      }

      int var4 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
      if (var4 == 36058) {
         printChatAndLogError("[Shaders] Error: Failed framebuffer incomplete formats");

         for (int var1 = 0; var1 < usedColorBuffers; var1++) {
            GlStateManager.bindTexture(dfbColorTexturesFlip.getA(var1));
            GL11.glTexImage2D(3553, 0, 6408, renderWidth, renderHeight, 0, 32993, 33639, (ByteBuffer)null);
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var1, 3553, dfbColorTexturesFlip.getA(var1), 0);
            checkGLError("FT c");
         }

         var4 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         if (var4 == 36053) {
            SMCLog.info("complete");
         }
      }

      GlStateManager.bindTexture(0);
      if (var4 != 36053) {
         printChatAndLogError("[Shaders] Error: Failed creating framebuffer! (Status " + var4 + ")");
      } else {
         SMCLog.info("Framebuffer created.");
      }
   }

   public static void beginRender(Minecraft var0, float var1, long var2) {
      checkGLError("pre beginRender");
      checkWorldChanged(mc.theWorld);
      mc = var0;
      mc.mcProfiler.startSection("init");
      entityRenderer = mc.entityRenderer;
      if (!field_0055) {
         try {
            method_02361();
         } catch (IllegalStateException var11) {
            if (Config.normalize(var11.getMessage()).equals("Function is not supported")) {
               printChatAndLogError("[Shaders] Error: " + var11.getMessage());
               var11.printStackTrace();
               setShaderPack("OFF");
               return;
            }
         }
      }

      if (mc.displayWidth != renderDisplayWidth || mc.displayHeight != renderDisplayHeight) {
         resize();
      }

      if (needResizeShadow) {
         resizeShadow();
      }

      field_0143 = mc.theWorld.L();
      field_0124 = (field_0143 - field_0350) % (-8314629291516830272L & 8314629290847264215L);
      if (field_0124 < (7615846047209900176L & 33588228L)) {
         field_0124 += 21323200L & -3804395462232342576L;
      }

      field_0350 = field_0143;
      field_0369 = mc.theWorld.getMoonPhase();
      field_0144++;
      if (field_0144 >= 720720) {
         field_0144 = 0;
      }

      field_0082 = System.currentTimeMillis();
      if (field_0255 == (4205169L & 1813483652L)) {
         field_0255 = field_0082;
      }

      diffSystemTime = field_0082 - field_0255;
      field_0255 = field_0082;
      field_0011 = (float)diffSystemTime / 1000.0F;
      field_0344 = field_0344 + field_0011;
      field_0344 %= 3600.0F;
      field_0223 = var0.theWorld.j(var1);
      float var4 = (float)diffSystemTime * 0.01F;
      float var5 = (float)Math.exp(Math.log(0.5) * var4 / (field_0036 < field_0223 ? field_0265 : field_0326));
      field_0036 = field_0036 * var5 + field_0223 * (1.0F - var5);
      Entity var6 = mc.getRenderViewEntity();
      if (var6 != null) {
         field_0129 = var6 instanceof EntityLivingBase && ((EntityLivingBase)var6).bJ();
         field_0203 = (float)var6.t * var1 + (float)var6.Q * (1.0F - var1);
         field_0151 = var6.b_(var1);
         var5 = (float)diffSystemTime * 0.01F;
         float var7 = (float)Math.exp(Math.log(0.5) * var5 / field_0113);
         field_0230 = field_0230 * var7 + (field_0151 & 65535) * (1.0F - var7);
         field_0033 = field_0033 * var7 + (field_0151 >> 16) * (1.0F - var7);
         Block var8 = ActiveRenderInfo.getBlockAtEntityViewpoint(mc.theWorld, var6, var1);
         Material var9 = var8.getMaterial();
         if (var9 == Material.water) {
            field_0283 = 1;
         } else if (var9 == Material.lava) {
            field_0283 = 2;
         } else {
            field_0283 = 0;
         }

         if (mc.thePlayer != null) {
            field_0288 = 0.0F;
            if (mc.thePlayer.isPotionActive(Potion.nightVision)) {
               field_0288 = Config.getMinecraft().entityRenderer.getNightVisionBrightness(mc.thePlayer, var1);
            }

            field_0178 = 0.0F;
            if (mc.thePlayer.isPotionActive(Potion.blindness)) {
               int var10 = mc.thePlayer.getActivePotionEffect(Potion.blindness).getDuration();
               field_0178 = Config.limit(var10 / 20.0F, 0.0F, 1.0F);
            }
         }

         Vec3 var14 = mc.theWorld.getSkyColor(var6, var1);
         var14 = CustomColors.getWorldSkyColor(var14, currentWorld, var6, var1);
         skyColorR = (float)var14.xCoord;
         skyColorG = (float)var14.yCoord;
         skyColorB = (float)var14.zCoord;
      }

      isRenderingWorld = true;
      field_0107 = false;
      isShadowPass = false;
      isHandRenderedMain = false;
      isHandRenderedOff = false;
      skipRenderHandMain = false;
      skipRenderHandOff = false;
      bindGbuffersTextures();
      previousCameraPositionX = cameraPositionX;
      previousCameraPositionY = cameraPositionY;
      previousCameraPositionZ = cameraPositionZ;
      ((Buffer)field_0336).position(0);
      ((Buffer)projection).position(0);
      field_0336.put(projection);
      ((Buffer)field_0336).position(0);
      ((Buffer)projection).position(0);
      ((Buffer)field_0127).position(0);
      ((Buffer)modelView).position(0);
      field_0127.put(modelView);
      ((Buffer)field_0127).position(0);
      ((Buffer)modelView).position(0);
      checkGLError("beginRender");
      ShadersRender.renderShadowMap(entityRenderer, 0, var1, var2);
      mc.mcProfiler.endSection();
      EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);

      for (int var13 = 0; var13 < usedColorBuffers; var13++) {
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var13, 3553, dfbColorTexturesFlip.getA(var13), 0);
      }

      checkGLError("end beginRender");
   }

   public static void endSky() {
      isRenderingSky = false;
      setDrawBuffers(dfbDrawBuffers);
      useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      popEntity();
   }

   public static ICustomTexture loadCustomTextureLocation(int var0, String var1) {
      String var2 = var1.trim();
      byte var3 = 0;
      if (var2.startsWith("minecraft:textures/")) {
         var2 = StrUtils.addSuffixCheck(var2, ".png");
         if (var2.endsWith("_n.png")) {
            var2 = StrUtils.replaceSuffix(var2, "_n.png", ".png");
            var3 = 1;
         } else if (var2.endsWith("_s.png")) {
            var2 = StrUtils.replaceSuffix(var2, "_s.png", ".png");
            var3 = 2;
         }
      }

      ResourceLocation var4 = new ResourceLocation(var2);
      return new CustomTextureLocation(var0, var4, var3);
   }

   public static void method_02314() {
      mapBlockToEntityData = new IdentityHashMap<>(300);
      if (mapBlockToEntityData.isEmpty()) {
         for (ResourceLocation var1 : Block.blockRegistry.getKeys()) {
            Block var2 = Block.blockRegistry.getObject(var1);
            int var3 = Block.blockRegistry.getIDForObject(var2);
            mapBlockToEntityData.put(var2, var3);
         }
      }

      BufferedReader var10 = null;

      try {
         var10 = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream("/mc_Entity_x.txt")));
      } catch (Exception var8) {
      }

      if (var10 != null) {
         String var11;
         try {
            while ((var11 = var10.readLine()) != null) {
               Matcher var12 = field_0246.matcher(var11);
               if (var12.matches()) {
                  String var13 = var12.group(1);
                  String var4 = var12.group(2);
                  int var5 = Integer.parseInt(var4);
                  Block var6 = Block.getBlockFromName(var13);
                  if (var6 != null) {
                     mapBlockToEntityData.put(var6, var5);
                  } else {
                     SMCLog.warning("Unknown block name %s", var13);
                  }
               } else {
                  SMCLog.warning("unmatched %s\n", var11);
               }
            }
         } catch (Exception var9) {
            SMCLog.warning("Error parsing mc_Entity_x.txt");
         }
      }

      if (var10 != null) {
         try {
            var10.close();
         } catch (Exception var7) {
         }
      }
   }

   public static void clearDirectory(File var0) {
      if (var0.exists() && var0.isDirectory()) {
         File[] var1 = var0.listFiles();
         if (var1 != null) {
            for (int var2 = 0; var2 < var1.length; var2++) {
               File var3 = var1[var2];
               if (var3.isDirectory()) {
                  clearDirectory(var3);
               }

               var3.delete();
            }
         }
      }
   }

   public static void setBlockEntityId(TileEntity var0) {
      if (uniform_blockEntityId.isDefined()) {
         int var1 = getBlockEntityId(var0);
         uniform_blockEntityId.setValue(var1);
      }
   }

   public static boolean isRenderingFirstPersonHand() {
      return isRenderingFirstPersonHand;
   }

   public static IntBuffer nextIntBuffer(int var0) {
      ByteBuffer var1 = bigBuffer;
      int var2 = var1.limit();
      ((Buffer)var1).position(var2).limit(var2 + var0 * 4);
      return var1.asIntBuffer();
   }

   public static void uninit() {
      if (field_0055) {
         checkGLError("Shaders.uninit pre");

         for (int var0 = 0; var0 < ProgramsAll.length; var0++) {
            Program var1 = ProgramsAll[var0];
            if (var1.getRef() != 0) {
               ARBShaderObjects.glDeleteObjectARB(var1.getRef());
               checkGLError("del programRef");
            }

            var1.setRef(0);
            var1.setId(0);
            var1.setDrawBufSettings((String)null);
            var1.setDrawBuffers((IntBuffer)null);
            var1.setCompositeMipmapSetting(0);
         }

         field_0348 = false;
         if (dfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
            dfb = 0;
            checkGLError("del dfb");
         }

         if (sfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
            sfb = 0;
            checkGLError("del sfb");
         }

         if (dfbDepthTextures != null) {
            GlStateManager.deleteTextures(dfbDepthTextures);
            fillIntBufferZero(dfbDepthTextures);
            checkGLError("del dfbDepthTextures");
         }

         if (dfbColorTextures != null) {
            GlStateManager.deleteTextures(dfbColorTextures);
            fillIntBufferZero(dfbColorTextures);
            checkGLError("del dfbTextures");
         }

         if (sfbDepthTextures != null) {
            GlStateManager.deleteTextures(sfbDepthTextures);
            fillIntBufferZero(sfbDepthTextures);
            checkGLError("del shadow depth");
         }

         if (sfbColorTextures != null) {
            GlStateManager.deleteTextures(sfbColorTextures);
            fillIntBufferZero(sfbColorTextures);
            checkGLError("del shadow color");
         }

         if (dfbDrawBuffers != null) {
            fillIntBufferZero(dfbDrawBuffers);
         }

         if (noiseTexture != null) {
            noiseTexture.deleteTexture();
            noiseTexture = null;
         }

         SMCLog.info("Uninit");
         shadowPassInterval = 0;
         field_0367 = false;
         field_0055 = false;
         checkGLError("Shaders.uninit");
      }
   }

   public static int getTextureIndex(int var0, String var1) {
      if (var0 == 0) {
         if (var1.equals("texture")) {
            return 0;
         }

         if (var1.equals("lightmap")) {
            return 1;
         }

         if (var1.equals("normals")) {
            return 2;
         }

         if (var1.equals("specular")) {
            return 3;
         }

         if (var1.equals("shadowtex0") || var1.equals("watershadow")) {
            return 4;
         }

         if (var1.equals("shadow")) {
            return waterShadowEnabled ? 5 : 4;
         }

         if (var1.equals("shadowtex1")) {
            return 5;
         }

         if (var1.equals("depthtex0")) {
            return 6;
         }

         if (var1.equals("gaux1")) {
            return 7;
         }

         if (var1.equals("gaux2")) {
            return 8;
         }

         if (var1.equals("gaux3")) {
            return 9;
         }

         if (var1.equals("gaux4")) {
            return 10;
         }

         if (var1.equals("depthtex1")) {
            return 12;
         }

         if (var1.equals("shadowcolor0") || var1.equals("shadowcolor")) {
            return 13;
         }

         if (var1.equals("shadowcolor1")) {
            return 14;
         }

         if (var1.equals("noisetex")) {
            return 15;
         }
      }

      if (var0 == 1 || var0 == 2) {
         if (var1.equals("colortex0") || var1.equals("colortex0")) {
            return 0;
         }

         if (var1.equals("colortex1") || var1.equals("gdepth")) {
            return 1;
         }

         if (var1.equals("colortex2") || var1.equals("gnormal")) {
            return 2;
         }

         if (var1.equals("colortex3") || var1.equals("composite")) {
            return 3;
         }

         if (var1.equals("shadowtex0") || var1.equals("watershadow")) {
            return 4;
         }

         if (var1.equals("shadow")) {
            return waterShadowEnabled ? 5 : 4;
         }

         if (var1.equals("shadowtex1")) {
            return 5;
         }

         if (var1.equals("depthtex0") || var1.equals("gdepthtex")) {
            return 6;
         }

         if (var1.equals("colortex4") || var1.equals("gaux1")) {
            return 7;
         }

         if (var1.equals("colortex5") || var1.equals("gaux2")) {
            return 8;
         }

         if (var1.equals("colortex6") || var1.equals("gaux3")) {
            return 9;
         }

         if (var1.equals("colortex7") || var1.equals("gaux4")) {
            return 10;
         }

         if (var1.equals("depthtex1")) {
            return 11;
         }

         if (var1.equals("depthtex2")) {
            return 12;
         }

         if (var1.equals("shadowcolor0") || var1.equals("shadowcolor")) {
            return 13;
         }

         if (var1.equals("shadowcolor1")) {
            return 14;
         }

         if (var1.equals("noisetex")) {
            return 15;
         }
      }

      return -1;
   }

   public static void enableTexture2D() {
      if (isRenderingSky) {
         useProgram(ProgramSkyTextured);
      } else if (activeProgram == ProgramBasic) {
         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      }
   }

   public static Program getProgramById(int var0) {
      for (int var1 = 0; var1 < ProgramsAll.length; var1++) {
         Program var2 = ProgramsAll[var1];
         if (var2.getId() == var0) {
            return var2;
         }
      }

      return ProgramNone;
   }

   public static boolean method_02291() {
      return field_0351.isTrue();
   }

   public static boolean isItemToRenderOffTranslucent() {
      return field_0158;
   }

   public static void drawHorizon() {
      WorldRenderer var0 = Tessellator.getInstance().getWorldRenderer();
      float var1 = mc.gameSettings.renderDistanceChunks * 16;
      double var2 = var1 * 0.9238;
      double var4 = var1 * 0.3826;
      double var6 = -var4;
      double var8 = -var2;
      double var10 = 16.0;
      double var12 = -cameraPositionY;
      var0.begin(7, DefaultVertexFormats.POSITION);
      var0.pos(var6, var12, var8).endVertex();
      var0.pos(var6, var10, var8).endVertex();
      var0.pos(var8, var10, var6).endVertex();
      var0.pos(var8, var12, var6).endVertex();
      var0.pos(var8, var12, var6).endVertex();
      var0.pos(var8, var10, var6).endVertex();
      var0.pos(var8, var10, var4).endVertex();
      var0.pos(var8, var12, var4).endVertex();
      var0.pos(var8, var12, var4).endVertex();
      var0.pos(var8, var10, var4).endVertex();
      var0.pos(var6, var10, var2).endVertex();
      var0.pos(var6, var12, var2).endVertex();
      var0.pos(var6, var12, var2).endVertex();
      var0.pos(var6, var10, var2).endVertex();
      var0.pos(var4, var10, var2).endVertex();
      var0.pos(var4, var12, var2).endVertex();
      var0.pos(var4, var12, var2).endVertex();
      var0.pos(var4, var10, var2).endVertex();
      var0.pos(var2, var10, var4).endVertex();
      var0.pos(var2, var12, var4).endVertex();
      var0.pos(var2, var12, var4).endVertex();
      var0.pos(var2, var10, var4).endVertex();
      var0.pos(var2, var10, var6).endVertex();
      var0.pos(var2, var12, var6).endVertex();
      var0.pos(var2, var12, var6).endVertex();
      var0.pos(var2, var10, var6).endVertex();
      var0.pos(var4, var10, var8).endVertex();
      var0.pos(var4, var12, var8).endVertex();
      var0.pos(var4, var12, var8).endVertex();
      var0.pos(var4, var10, var8).endVertex();
      var0.pos(var6, var10, var8).endVertex();
      var0.pos(var6, var12, var8).endVertex();
      var0.pos(var8, var12, var8).endVertex();
      var0.pos(var8, var12, var2).endVertex();
      var0.pos(var2, var12, var2).endVertex();
      var0.pos(var2, var12, var8).endVertex();
      Tessellator.getInstance().draw();
   }

   public static void endClouds() {
      disableFog();
      popEntity();
      useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
   }

   public static void setupNoiseTexture() {
      if (noiseTexture == null && noiseTexturePath != null) {
         noiseTexture = loadCustomTexture(15, noiseTexturePath);
      }

      if (noiseTexture == null) {
         noiseTexture = new HFNoiseTexture(noiseTextureResolution, noiseTextureResolution);
      }
   }

   public static int setEntityData2(int var0) {
      entityData[entityDataIndex * 2 + 1] = entityData[entityDataIndex * 2 + 1] & -65536 | var0 & 65535;
      return var0;
   }

   public static ByteBuffer nextByteBuffer(int var0) {
      ByteBuffer var1 = bigBuffer;
      int var2 = var1.limit();
      ((Buffer)var1).position(var2).limit(var2 + var0);
      return var1.slice();
   }

   public static ArrayList listOfShaders() {
      ArrayList var0 = new ArrayList();
      var0.add("OFF");
      var0.add("(internal)");
      int var1 = var0.size();

      try {
         if (!shaderPacksDir.exists()) {
            shaderPacksDir.mkdir();
         }

         File[] var2 = shaderPacksDir.listFiles();

         for (int var3 = 0; var3 < var2.length; var3++) {
            File var4 = var2[var3];
            String var5 = var4.getName();
            if (var4.isDirectory()) {
               if (!var5.equals("debug")) {
                  File var6 = new File(var4, "shaders");
                  if (var6.exists() && var6.isDirectory()) {
                     var0.add(var5);
                  }
               }
            } else if (var4.isFile() && var5.toLowerCase().endsWith(".zip")) {
               var0.add(var5);
            }
         }
      } catch (Exception var7) {
      }

      List var8 = var0.subList(var1, var0.size());
      Collections.sort(var8, String.CASE_INSENSITIVE_ORDER);
      return var0;
   }

   public static void endWeather() {
      GlStateManager.disableBlend();
      useProgram(ProgramTexturedLit);
   }

   public static ShaderOption[] method_02355(ShaderOption[] var0) {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var2 < var0.length; var2++) {
         ShaderOption var3 = var0[var2];
         if (var3.isEnabled() && var3.isChanged()) {
            var1.add(var3);
         }
      }

      return var1.toArray(new ShaderOption[var1.size()]);
   }

   public static String getErrorInfo(int var0, String var1) {
      StringBuilder var2 = new StringBuilder();
      if (var0 == 1286) {
         int var3 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         String var4 = getFramebufferStatusText(var3);
         String var5 = ", fbStatus: " + var3 + " (" + var4 + ")";
         var2.append(var5);
      }

      String var6 = activeProgram.getName();
      if (var6.isEmpty()) {
         var6 = "none";
      }

      var2.append(", program: " + var6);
      Program var7 = getProgramById(activeProgramID);
      if (var7 != activeProgram) {
         String var8 = var7.getName();
         if (var8.isEmpty()) {
            var8 = "none";
         }

         var2.append(" (" + var8 + ")");
      }

      if (var1.equals("setDrawBuffers")) {
         var2.append(", drawBuffers: " + activeProgram.getDrawBufSettings());
      }

      return var2.toString();
   }

   public static boolean isItemToRenderMainTranslucent() {
      return field_0205;
   }

   public static void beginEntities() {
      if (isRenderingWorld) {
         useProgram(ProgramEntities);
      }
   }

   public static void mcProfilerEndSection() {
      mc.mcProfiler.endSection();
   }

   public static void endBeacon() {
      if (isRenderingWorld) {
         useProgram(ProgramBlock);
      }
   }

   public static void setEntityId(Entity var0) {
      if (uniform_entityId.isDefined()) {
         int var1 = EntityUtils.getEntityIdByClass(var0);
         int var2 = EntityAliases.getEntityAliasId(var1);
         if (var2 >= 0) {
            var1 = var2;
         }

         uniform_entityId.setValue(var1);
      }
   }

   public static boolean method_02300() {
      return !field_0072.isFalse();
   }

   public static void renderComposites(Program[] var0, boolean var1) {
      if (!isShadowPass) {
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glMatrixMode(5889);
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0, 1.0, 0.0, 1.0, 0.0, 1.0);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableTexture2D();
         GlStateManager.disableAlpha();
         GlStateManager.disableBlend();
         GlStateManager.enableDepth();
         GlStateManager.depthFunc(519);
         GlStateManager.depthMask(false);
         GlStateManager.disableLighting();
         if (usedShadowDepthBuffers >= 1) {
            GlStateManager.setActiveTexture(33988);
            GlStateManager.bindTexture(sfbDepthTextures.get(0));
            if (usedShadowDepthBuffers >= 2) {
               GlStateManager.setActiveTexture(33989);
               GlStateManager.bindTexture(sfbDepthTextures.get(1));
            }
         }

         for (int var2 = 0; var2 < usedColorBuffers; var2++) {
            GlStateManager.setActiveTexture(33984 + colorTextureImageUnit[var2]);
            GlStateManager.bindTexture(dfbColorTexturesFlip.getA(var2));
         }

         GlStateManager.setActiveTexture(33990);
         GlStateManager.bindTexture(dfbDepthTextures.get(0));
         if (usedDepthBuffers >= 2) {
            GlStateManager.setActiveTexture(33995);
            GlStateManager.bindTexture(dfbDepthTextures.get(1));
            if (usedDepthBuffers >= 3) {
               GlStateManager.setActiveTexture(33996);
               GlStateManager.bindTexture(dfbDepthTextures.get(2));
            }
         }

         for (int var5 = 0; var5 < usedShadowColorBuffers; var5++) {
            GlStateManager.setActiveTexture(33997 + var5);
            GlStateManager.bindTexture(sfbColorTextures.get(var5));
         }

         if (noiseTextureEnabled) {
            GlStateManager.setActiveTexture(33984 + noiseTexture.getTextureUnit());
            GlStateManager.bindTexture(noiseTexture.getTextureId());
         }

         if (var1) {
            bindCustomTextures(customTexturesComposite);
         } else {
            bindCustomTextures(customTexturesDeferred);
         }

         GlStateManager.setActiveTexture(33984);

         for (int var6 = 0; var6 < usedColorBuffers; var6++) {
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var6, 3553, dfbColorTexturesFlip.getB(var6), 0);
         }

         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
         GL20.glDrawBuffers(dfbDrawBuffers);
         checkGLError("pre-composite");

         for (int var7 = 0; var7 < var0.length; var7++) {
            Program var3 = var0[var7];
            if (var3.getId() != 0) {
               useProgram(var3);
               checkGLError(var3.getName());
               if (activeCompositeMipmapSetting != 0) {
                  genCompositeMipmap();
               }

               preDrawComposite();
               drawComposite();
               postDrawComposite();

               for (int var4 = 0; var4 < usedColorBuffers; var4++) {
                  if (var3.getToggleColorTextures()[var4]) {
                     dfbColorTexturesFlip.flip(var4);
                     GlStateManager.setActiveTexture(33984 + colorTextureImageUnit[var4]);
                     GlStateManager.bindTexture(dfbColorTexturesFlip.getA(var4));
                     EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var4, 3553, dfbColorTexturesFlip.getB(var4), 0);
                  }
               }

               GlStateManager.setActiveTexture(33984);
            }
         }

         checkGLError("composite");
         if (var1) {
            renderFinal();
            field_0107 = true;
         }

         GlStateManager.enableLighting();
         GlStateManager.enableTexture2D();
         GlStateManager.enableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.depthFunc(515);
         GlStateManager.depthMask(true);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5888);
         GL11.glPopMatrix();
         useProgram(ProgramNone);
      }
   }

   public static int method_02248(Program var0, String var1) {
      int var2 = ARBShaderObjects.glCreateShaderObjectARB(35633);
      if (var2 == 0) {
         return 0;
      } else {
         StringBuilder var3 = new StringBuilder(131072);
         BufferedReader var4 = null;

         try {
            var4 = new BufferedReader(getShaderReader(var1));
         } catch (Exception var10) {
            ARBShaderObjects.glDeleteObjectARB(var2);
            return 0;
         }

         ShaderOption[] var5 = method_02355(shaderPackOptions);
         ArrayList var6 = new ArrayList();
         if (var4 != null) {
            try {
               var4 = ShaderPackParser.resolveIncludes((BufferedReader)var4, var1, shaderPack, 0, var6, 0);
               MacroState var7 = new MacroState();

               while (true) {
                  String var8 = var4.readLine();
                  if (var8 == null) {
                     var4.close();
                     break;
                  }

                  var8 = applyOptions(var8, var5);
                  var3.append(var8).append('\n');
                  if (var7.processLine(var8)) {
                     ShaderLine var9 = ShaderParser.parseLine(var8);
                     if (var9 != null) {
                        if (var9.method_09531("mc_Entity")) {
                           field_0154 = true;
                           field_0338 = true;
                        } else if (var9.method_09531("mc_midTexCoord")) {
                           field_0204 = true;
                           field_0027 = true;
                        } else if (var9.method_09531("at_tangent")) {
                           field_0096 = true;
                           field_0395 = true;
                        }

                        if (var9.method_09536("countInstances")) {
                           var0.setCountInstances(var9.getValueInt());
                           SMCLog.info("countInstances: " + var0.getCountInstances());
                        }
                     }
                  }
               }
            } catch (Exception var11) {
               SMCLog.severe("Couldn't read " + var1 + "!");
               var11.printStackTrace();
               ARBShaderObjects.glDeleteObjectARB(var2);
               return 0;
            }
         }

         if (saveFinalShaders) {
            saveShader(var1, var3.toString());
         }

         ARBShaderObjects.glShaderSourceARB(var2, var3);
         ARBShaderObjects.glCompileShaderARB(var2);
         if (GL20.glGetShaderi(var2, 35713) != 1) {
            SMCLog.severe("Error compiling vertex shader: " + var1);
         }

         printShaderLogInfo(var2, var1, var6);
         return var2;
      }
   }

   public static int getPixelFormat(int var0) {
      switch (var0) {
         case 33333:
         case 33334:
         case 33339:
         case 33340:
         case 36208:
         case 36209:
         case 36226:
         case 36227:
            return 36251;
         default:
            return 32993;
      }
   }

   public static void popProgram() {
      Program var0 = programStack.pop();
      useProgram(var0);
   }

   public static void beginRenderPass(int var0, float var1, long var2) {
      if (!isShadowPass) {
         EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
         GL11.glViewport(0, 0, renderWidth, renderHeight);
         activeDrawBuffers = null;
         ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
         useProgram(ProgramTextured);
         checkGLError("end beginRenderPass");
      }
   }

   public static void deleteCustomTextures(ICustomTexture[] var0) {
      if (var0 != null) {
         for (int var1 = 0; var1 < var0.length; var1++) {
            ICustomTexture var2 = var0[var1];
            var2.deleteTexture();
         }
      }
   }

   public static int method_02345(Program var0, String var1) {
      int var2 = ARBShaderObjects.glCreateShaderObjectARB(36313);
      if (var2 == 0) {
         return 0;
      } else {
         StringBuilder var3 = new StringBuilder(131072);
         BufferedReader var4 = null;

         try {
            var4 = new BufferedReader(getShaderReader(var1));
         } catch (Exception var11) {
            ARBShaderObjects.glDeleteObjectARB(var2);
            return 0;
         }

         ShaderOption[] var5 = method_02355(shaderPackOptions);
         ArrayList var6 = new ArrayList();
         field_0329 = false;
         field_0086 = 3;
         if (var4 != null) {
            try {
               var4 = ShaderPackParser.resolveIncludes((BufferedReader)var4, var1, shaderPack, 0, var6, 0);
               MacroState var7 = new MacroState();

               while (true) {
                  String var8 = var4.readLine();
                  if (var8 == null) {
                     var4.close();
                     break;
                  }

                  var8 = applyOptions(var8, var5);
                  var3.append(var8).append('\n');
                  if (var7.processLine(var8)) {
                     ShaderLine var9 = ShaderParser.parseLine(var8);
                     if (var9 != null) {
                        if (var9.method_09517("GL_ARB_geometry_shader4")) {
                           String var10 = Config.normalize(var9.getValue());
                           if (var10.equals("enable") || var10.equals("require") || var10.equals("warn")) {
                              field_0329 = true;
                           }
                        }

                        if (var9.method_09536("maxVerticesOut")) {
                           field_0086 = var9.getValueInt();
                        }
                     }
                  }
               }
            } catch (Exception var12) {
               SMCLog.severe("Couldn't read " + var1 + "!");
               var12.printStackTrace();
               ARBShaderObjects.glDeleteObjectARB(var2);
               return 0;
            }
         }

         if (saveFinalShaders) {
            saveShader(var1, var3.toString());
         }

         ARBShaderObjects.glShaderSourceARB(var2, var3);
         ARBShaderObjects.glCompileShaderARB(var2);
         if (GL20.glGetShaderi(var2, 35713) != 1) {
            SMCLog.severe("Error compiling geometry shader: " + var1);
         }

         printShaderLogInfo(var2, var1, var6);
         return var2;
      }
   }

   public static ICustomTexture loadCustomTextureShaders(int var0, String var1) {
      var1 = var1.trim();
      if (var1.indexOf(46) < 0) {
         var1 = var1 + ".png";
      }

      try {
         String var2 = "shaders/" + StrUtils.removePrefix(var1, "/");
         InputStream var3 = shaderPack.getResourceAsStream(var2);
         if (var3 == null) {
            SMCLog.warning("Texture not found: " + var1);
            return null;
         } else {
            IOUtils.closeQuietly(var3);
            SimpleShaderTexture var4 = new SimpleShaderTexture(var2);
            var4.loadTexture(mc.getResourceManager());
            return new CustomTexture(var0, var2, var4);
         }
      } catch (IOException var6) {
         SMCLog.warning("Error loading texture: " + var1);
         SMCLog.warning("" + var6.getClass().getName() + ": " + var6.getMessage());
         return null;
      }
   }

   public static void method_02274(ItemStack var0) {
      field_0158 = isTranslucentBlock(var0);
   }

   public static void beginParticles() {
      useProgram(ProgramTextured);
   }

   public static void scheduleResize() {
      renderDisplayHeight = 0;
   }

   public static Properties loadOptionProperties(IShaderPack var0) {
      PropertiesOrdered var1 = new PropertiesOrdered();
      String var2 = "shaderpacks/" + var0.getName() + ".txt";
      File var3 = new File(Minecraft.getMinecraft().mcDataDir, var2);
      if (var3.exists() && var3.isFile() && var3.canRead()) {
         FileInputStream var4 = new FileInputStream(var3);
         var1.load(var4);
         var4.close();
         return var1;
      } else {
         return var1;
      }
   }

   public static void pushProgram() {
      programStack.push(activeProgram);
   }

   public static String getFramebufferStatusText(int var0) {
      switch (var0) {
         case 33305:
            return "Undefined";
         case 36053:
            return "Complete";
         case 36054:
            return "Incomplete attachment";
         case 36055:
            return "Incomplete missing attachment";
         case 36059:
            return "Incomplete draw buffer";
         case 36060:
            return "Incomplete read buffer";
         case 36061:
            return "Unsupported";
         case 36182:
            return "Incomplete multisample";
         case 36264:
            return "Incomplete layer targets";
         default:
            return "Unknown";
      }
   }

   public static void saveOptionProperties(IShaderPack var0, Properties var1) {
      String var2 = "shaderpacks/" + var0.getName() + ".txt";
      File var3 = new File(Minecraft.getMinecraft().mcDataDir, var2);
      if (var1.isEmpty()) {
         var3.delete();
      } else {
         FileOutputStream var4 = new FileOutputStream(var3);
         var1.store(var4, (String)null);
         var4.flush();
         var4.close();
      }
   }

   public static void genCompositeMipmap() {
      if (hasGlGenMipmap) {
         for (int var0 = 0; var0 < usedColorBuffers; var0++) {
            if ((activeCompositeMipmapSetting & 1 << var0) != 0) {
               GlStateManager.setActiveTexture(33984 + colorTextureImageUnit[var0]);
               GL11.glTexParameteri(3553, 10241, 9987);
               GL30.glGenerateMipmap(3553);
            }
         }

         GlStateManager.setActiveTexture(33984);
      }
   }

   public static void beginLeash() {
      pushProgram();
      useProgram(ProgramBasic);
   }

   public static void method_02283(int var0) {
      GL11.glDisable(var0);
      disableTexture2D();
   }

   public static void method_02158() {
      useProgram(ProgramTexturedLit);
   }

   public static boolean isSkipRenderHand() {
      return skipRenderHandMain;
   }

   public static void useProgram(Program var0) {
      checkGLError("pre-useProgram");
      if (isShadowPass) {
         var0 = ProgramShadow;
      } else if (isEntitiesGlowing) {
         var0 = field_0078;
      }

      if (activeProgram != var0) {
         updateAlphaBlend(activeProgram, var0);
         activeProgram = var0;
         int var1 = var0.getId();
         activeProgramID = var1;
         ARBShaderObjects.glUseProgramObjectARB(var1);
         if (checkGLError("useProgram") != 0) {
            var0.setId(0);
            var1 = var0.getId();
            activeProgramID = var1;
            ARBShaderObjects.glUseProgramObjectARB(var1);
         }

         shaderUniforms.setProgram(var1);
         if (customUniforms != null) {
            customUniforms.setProgram(var1);
         }

         if (var1 != 0) {
            IntBuffer var2 = var0.getDrawBuffers();
            if (isRenderingDfb) {
               setDrawBuffers(var2);
            }

            activeCompositeMipmapSetting = var0.getCompositeMipmapSetting();
            switch (Shaders$1.$SwitchMap$net$optifine$shaders$ProgramStage[var0.getProgramStage().ordinal()]) {
               case 1:
                  setProgramUniform1i(field_0347, 0);
                  setProgramUniform1i(field_0342, 1);
                  setProgramUniform1i(field_0066, 2);
                  setProgramUniform1i(field_0330, 3);
                  setProgramUniform1i(field_0097, waterShadowEnabled ? 5 : 4);
                  setProgramUniform1i(field_0093, 4);
                  setProgramUniform1i(field_0374, 4);
                  setProgramUniform1i(field_0252, 5);
                  setProgramUniform1i(field_0346, 6);
                  if (customTexturesGbuffers != null || field_0348) {
                     setProgramUniform1i(field_0100, 7);
                     setProgramUniform1i(field_0141, 8);
                     setProgramUniform1i(field_0337, 9);
                     setProgramUniform1i(field_0228, 10);
                  }

                  setProgramUniform1i(field_0006, 11);
                  setProgramUniform1i(field_0208, 13);
                  setProgramUniform1i(field_0108, 13);
                  setProgramUniform1i(field_0180, 14);
                  setProgramUniform1i(field_0163, 15);
                  break;
               case 2:
               case 3:
                  setProgramUniform1i(field_0109, 0);
                  setProgramUniform1i(field_0026, 1);
                  setProgramUniform1i(field_0155, 2);
                  setProgramUniform1i(field_0063, 3);
                  setProgramUniform1i(field_0100, 7);
                  setProgramUniform1i(field_0141, 8);
                  setProgramUniform1i(field_0337, 9);
                  setProgramUniform1i(field_0228, 10);
                  setProgramUniform1i(field_0241, 0);
                  setProgramUniform1i(field_0067, 1);
                  setProgramUniform1i(field_0064, 2);
                  setProgramUniform1i(field_0323, 3);
                  setProgramUniform1i(field_0083, 7);
                  setProgramUniform1i(field_0263, 8);
                  setProgramUniform1i(field_0028, 9);
                  setProgramUniform1i(field_0376, 10);
                  setProgramUniform1i(field_0097, waterShadowEnabled ? 5 : 4);
                  setProgramUniform1i(field_0093, 4);
                  setProgramUniform1i(field_0374, 4);
                  setProgramUniform1i(field_0252, 5);
                  setProgramUniform1i(field_0324, 6);
                  setProgramUniform1i(field_0346, 6);
                  setProgramUniform1i(field_0006, 11);
                  setProgramUniform1i(field_0057, 12);
                  setProgramUniform1i(field_0208, 13);
                  setProgramUniform1i(field_0108, 13);
                  setProgramUniform1i(field_0180, 14);
                  setProgramUniform1i(field_0163, 15);
                  break;
               case 4:
                  setProgramUniform1i(field_0378, 0);
                  setProgramUniform1i(field_0347, 0);
                  setProgramUniform1i(field_0342, 1);
                  setProgramUniform1i(field_0066, 2);
                  setProgramUniform1i(field_0330, 3);
                  setProgramUniform1i(field_0097, waterShadowEnabled ? 5 : 4);
                  setProgramUniform1i(field_0093, 4);
                  setProgramUniform1i(field_0374, 4);
                  setProgramUniform1i(field_0252, 5);
                  if (customTexturesGbuffers != null) {
                     setProgramUniform1i(field_0100, 7);
                     setProgramUniform1i(field_0141, 8);
                     setProgramUniform1i(field_0337, 9);
                     setProgramUniform1i(field_0228, 10);
                  }

                  setProgramUniform1i(field_0208, 13);
                  setProgramUniform1i(field_0108, 13);
                  setProgramUniform1i(field_0180, 14);
                  setProgramUniform1i(field_0163, 15);
            }

            ItemStack var3 = mc.thePlayer != null ? mc.thePlayer.getHeldItem() : null;
            Item var4 = var3 != null ? var3.getItem() : null;
            int var5 = -1;
            Block var6 = null;
            if (var4 != null) {
               var5 = Item.itemRegistry.getIDForObject(var4);
               var6 = Block.blockRegistry.getObjectById(var5);
               var5 = ItemAliases.getItemAliasId(var5);
            }

            int var7 = var6 != null ? var6.getLightValue() : 0;
            setProgramUniform1i(uniform_heldItemId, var5);
            setProgramUniform1i(uniform_heldBlockLightValue, var7);
            setProgramUniform1i(uniform_fogMode, fogEnabled ? fogMode : 0);
            setProgramUniform1f(uniform_fogDensity, fogEnabled ? fogDensity : 0.0F);
            setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
            setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
            setProgramUniform1i(uniform_worldTime, (int)(field_0143 % (141581762L & 7210046162332704704L)));
            setProgramUniform1i(uniform_worldDay, (int)(field_0143 / (1325424064L & -8366432639052653116L)));
            setProgramUniform1i(uniform_moonPhase, field_0369);
            setProgramUniform1i(uniform_frameCounter, field_0144);
            setProgramUniform1f(uniform_frameTime, field_0011);
            setProgramUniform1f(uniform_frameTimeCounter, field_0344);
            setProgramUniform1f(uniform_sunAngle, sunAngle);
            setProgramUniform1f(uniform_shadowAngle, shadowAngle);
            setProgramUniform1f(uniform_rainStrength, field_0223);
            setProgramUniform1f(uniform_aspectRatio, (float)renderWidth / renderHeight);
            setProgramUniform1f(uniform_viewWidth, renderWidth);
            setProgramUniform1f(uniform_viewHeight, renderHeight);
            setProgramUniform1f(uniform_near, 0.05F);
            setProgramUniform1f(uniform_far, mc.gameSettings.renderDistanceChunks * 16);
            setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
            setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
            setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
            setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
            setProgramUniform3f(uniform_previousCameraPosition, (float)previousCameraPositionX, (float)previousCameraPositionY, (float)previousCameraPositionZ);
            setProgramUniform3f(uniform_cameraPosition, (float)cameraPositionX, (float)cameraPositionY, (float)cameraPositionZ);
            setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
            setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
            setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, field_0336);
            setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
            setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
            setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, field_0127);
            if (usedShadowDepthBuffers > 0) {
               setProgramUniformMatrix4ARB(uniform_shadowProjection, false, field_0095);
               setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, field_0023);
               setProgramUniformMatrix4ARB(uniform_shadowModelView, false, field_0314);
               setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, field_0294);
            }

            setProgramUniform1f(uniform_wetness, field_0036);
            setProgramUniform1f(uniform_eyeAltitude, field_0203);
            setProgramUniform2i(uniform_eyeBrightness, field_0151 & 65535, field_0151 >> 16);
            setProgramUniform2i(uniform_eyeBrightnessSmooth, Math.round(field_0230), Math.round(field_0033));
            setProgramUniform2i(uniform_terrainTextureSize, terrainTextureSize[0], terrainTextureSize[1]);
            setProgramUniform1i(uniform_terrainIconSize, field_0152);
            setProgramUniform1i(uniform_isEyeInWater, field_0283);
            setProgramUniform1f(uniform_nightVision, field_0288);
            setProgramUniform1f(uniform_blindness, field_0178);
            setProgramUniform1f(uniform_screenBrightness, mc.gameSettings.gammaSetting);
            setProgramUniform1i(uniform_hideGUI, mc.gameSettings.hideGUI ? 1 : 0);
            setProgramUniform1f(uniform_centerDepthSmooth, centerDepthSmooth);
            setProgramUniform2i(uniform_atlasSize, atlasSizeX, atlasSizeY);
            if (customUniforms != null) {
               customUniforms.update();
            }

            checkGLError("end useProgram");
         }
      }
   }

   public static boolean method_02285() {
      return !shaderPackDynamicHandLight.isDefault() ? shaderPackDynamicHandLight.isTrue() : true;
   }

   public static void popEntity() {
      entityData[entityDataIndex * 2] = 0;
      entityData[entityDataIndex * 2 + 1] = 0;
      entityDataIndex--;
   }

   public static void method_02352(ItemStack var0) {
      field_0205 = isTranslucentBlock(var0);
   }

   public static void setProgramUniformMatrix4ARB(ShaderUniformM4 var0, boolean var1, FloatBuffer var2) {
      var0.setValue(var1, var2);
   }

   public static void beginHand(boolean var0) {
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5888);
      if (var0) {
         useProgram(ProgramHandWater);
      } else {
         useProgram(ProgramHand);
      }

      checkGLError("beginHand");
      checkFramebufferStatus("beginHand");
   }

   public static ShaderOption[] getShaderOptionsRest(Map<String, ScreenShaderOptions> var0, ShaderOption[] var1) {
      HashSet var2 = new HashSet();

      for (String var4 : var0.keySet()) {
         ScreenShaderOptions var5 = (ScreenShaderOptions)var0.get(var4);
         ShaderOption[] var6 = var5.getShaderOptions();

         for (int var7 = 0; var7 < var6.length; var7++) {
            ShaderOption var8 = var6[var7];
            if (var8 != null) {
               var2.add(var8.getName());
            }
         }
      }

      ArrayList var9 = new ArrayList();

      for (int var10 = 0; var10 < var1.length; var10++) {
         ShaderOption var11 = var1[var10];
         if (var11.isVisible()) {
            String var12 = var11.getName();
            if (!var2.contains(var12)) {
               var9.add(var11);
            }
         }
      }

      return var9.toArray(new ShaderOption[var9.size()]);
   }

   public static void readCenterDepth() {
      if (!isShadowPass && centerDepthSmoothEnabled) {
         ((Buffer)tempDirectFloatBuffer).clear();
         GL11.glReadPixels(renderWidth / 2, renderHeight / 2, 1, 1, 6402, 5126, tempDirectFloatBuffer);
         centerDepth = tempDirectFloatBuffer.get(0);
         float var0 = (float)diffSystemTime * 0.01F;
         float var1 = (float)Math.exp(Math.log(0.5) * var0 / centerDepthSmoothHalflife);
         centerDepthSmooth = centerDepthSmooth * var1 + centerDepth * (1.0F - var1);
      }
   }

   public static int getEntityData() {
      return entityData[entityDataIndex * 2];
   }

   public static void beginWater() {
      if (isRenderingWorld) {
         if (!isShadowPass) {
            renderDeferred();
            useProgram(ProgramWater);
            GlStateManager.enableBlend();
            GlStateManager.depthMask(true);
         } else {
            GlStateManager.depthMask(true);
         }
      }
   }

   public static void setupShadowFrameBuffer() {
      if (usedShadowDepthBuffers != 0) {
         if (sfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
            GlStateManager.deleteTextures(sfbDepthTextures);
            GlStateManager.deleteTextures(sfbColorTextures);
         }

         sfb = EXTFramebufferObject.glGenFramebuffersEXT();
         EXTFramebufferObject.glBindFramebufferEXT(36160, sfb);
         GL11.glDrawBuffer(0);
         GL11.glReadBuffer(0);
         GL11.glGenTextures((IntBuffer)((Buffer)sfbDepthTextures).clear().limit(usedShadowDepthBuffers));
         GL11.glGenTextures((IntBuffer)((Buffer)sfbColorTextures).clear().limit(usedShadowColorBuffers));
         ((Buffer)sfbDepthTextures).position(0);
         ((Buffer)sfbColorTextures).position(0);

         for (int var0 = 0; var0 < usedShadowDepthBuffers; var0++) {
            GlStateManager.bindTexture(sfbDepthTextures.get(var0));
            GL11.glTexParameterf(3553, 10242, 33071.0F);
            GL11.glTexParameterf(3553, 10243, 33071.0F);
            int var1 = shadowFilterNearest[var0] ? 9728 : 9729;
            GL11.glTexParameteri(3553, 10241, var1);
            GL11.glTexParameteri(3553, 10240, var1);
            if (field_0217[var0]) {
               GL11.glTexParameteri(3553, 34892, 34894);
            }

            GL11.glTexImage2D(3553, 0, 6402, shadowMapWidth, shadowMapHeight, 0, 6402, 5126, (FloatBuffer)null);
         }

         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
         checkGLError("FT sd");

         for (int var2 = 0; var2 < usedShadowColorBuffers; var2++) {
            GlStateManager.bindTexture(sfbColorTextures.get(var2));
            GL11.glTexParameterf(3553, 10242, 33071.0F);
            GL11.glTexParameterf(3553, 10243, 33071.0F);
            int var4 = shadowColorFilterNearest[var2] ? 9728 : 9729;
            GL11.glTexParameteri(3553, 10241, var4);
            GL11.glTexParameteri(3553, 10240, var4);
            GL11.glTexImage2D(3553, 0, 6408, shadowMapWidth, shadowMapHeight, 0, 32993, 33639, (ByteBuffer)null);
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var2, 3553, sfbColorTextures.get(var2), 0);
            checkGLError("FT sc");
         }

         GlStateManager.bindTexture(0);
         if (usedShadowColorBuffers > 0) {
            GL20.glDrawBuffers(sfbDrawBuffers);
         }

         int var3 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         if (var3 != 36053) {
            printChatAndLogError("[Shaders] Error: Failed creating shadow framebuffer! (Status " + var3 + ")");
         } else {
            SMCLog.info("Shadow framebuffer created.");
         }
      }
   }

   public static boolean isShaderPackOptionSlider(String var0) {
      return shaderPackOptionSliders == null ? false : shaderPackOptionSliders.contains(var0);
   }

   public static void printIntBuffer(String var0, IntBuffer var1) {
      StringBuilder var2 = new StringBuilder(128);
      var2.append(var0).append(" [pos ").append(var1.position()).append(" lim ").append(var1.limit()).append(" cap ").append(var1.capacity()).append(" :");
      int var3 = var1.limit();

      for (int var4 = 0; var4 < var3; var4++) {
         var2.append(" ").append(var1.get(var4));
      }

      var2.append("]");
      SMCLog.info(var2.toString());
   }

   public static void checkWorldChanged(World var0) {
      if (currentWorld != var0) {
         World var1 = currentWorld;
         currentWorld = var0;
         setCameraOffset(mc.getRenderViewEntity());
         int var2 = getDimensionId(var1);
         int var3 = getDimensionId(var0);
         if (var3 != var2) {
            boolean var4 = shaderPackDimensions.contains(var2);
            boolean var5 = shaderPackDimensions.contains(var3);
            if (var4 || var5) {
               uninit();
            }
         }

         Smoother.resetValues();
      }
   }

   public static IntBuffer[] nextIntBufferArray(int var0, int var1) {
      IntBuffer[] var2 = new IntBuffer[var0];

      for (int var3 = 0; var3 < var0; var3++) {
         var2[var3] = nextIntBuffer(var1);
      }

      return var2;
   }

   public static int method_02329(String var0) {
      return var0.equals("colortex0") || var0.equals("gcolor")
         ? 0
         : (
            var0.equals("colortex1") || var0.equals("gdepth")
               ? 1
               : (
                  var0.equals("colortex2") || var0.equals("gnormal")
                     ? 2
                     : (
                        var0.equals("colortex3") || var0.equals("composite")
                           ? 3
                           : (
                              var0.equals("colortex4") || var0.equals("gaux1")
                                 ? 4
                                 : (
                                    var0.equals("colortex5") || var0.equals("gaux2")
                                       ? 5
                                       : (
                                          !var0.equals("colortex6") && !var0.equals("gaux3")
                                             ? (!var0.equals("colortex7") && !var0.equals("gaux4") ? -1 : 7)
                                             : 6
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public static void method_02344(Program var0) {
      int var1 = GL11.glGetInteger(34852);
      Arrays.fill(var0.getToggleColorTextures(), false);
      if (var0 == ProgramFinal) {
         var0.setDrawBuffers((IntBuffer)null);
      } else if (var0.getId() == 0) {
         if (var0 == ProgramShadow) {
            var0.setDrawBuffers(drawBuffersNone);
         } else {
            var0.setDrawBuffers(drawBuffersColorAtt0);
         }
      } else {
         String var2 = var0.getDrawBufSettings();
         if (var2 == null) {
            if (var0 != ProgramShadow && var0 != field_0262 && var0 != field_0219) {
               var0.setDrawBuffers(dfbDrawBuffers);
               field_0225 = usedColorBuffers;
               Arrays.fill(var0.getToggleColorTextures(), 0, usedColorBuffers, true);
            } else {
               var0.setDrawBuffers(sfbDrawBuffers);
            }
         } else {
            IntBuffer var3 = var0.getDrawBuffersBuffer();
            int var4 = var2.length();
            field_0225 = Math.max(field_0225, var4);
            var4 = Math.min(var4, var1);
            var0.setDrawBuffers(var3);
            ((Buffer)var3).limit(var4);

            for (int var5 = 0; var5 < var4; var5++) {
               int var6 = getDrawBuffer(var0, var2, var5);
               var3.put(var5, var6);
            }
         }
      }
   }

   public static void loadShaderPackResources() {
      shaderPackResources = new HashMap<>();
      if (shaderPackLoaded) {
         ArrayList var0 = new ArrayList();
         String var1 = "/shaders/lang/";
         String var2 = "en_US";
         String var3 = ".lang";
         var0.add(var1 + var2 + var3);
         if (!Config.getGameSettings().language.equals(var2)) {
            var0.add(var1 + Config.getGameSettings().language + var3);
         }

         try {
            for (String var5 : var0) {
               InputStream var6 = shaderPack.getResourceAsStream(var5);
               if (var6 != null) {
                  PropertiesOrdered var7 = new PropertiesOrdered();
                  Lang.loadLocaleData(var6, var7);
                  var6.close();

                  for (Object var9 : var7.keySet()) {
                     String var10 = (String)var9;
                     String var11 = var7.getProperty(var10);
                     shaderPackResources.put(var10, var11);
                  }
               }
            }
         } catch (IOException var12) {
            var12.printStackTrace();
         }
      }
   }

   public static void method_02190() {
      if (isShadowPass) {
         checkGLError("shadow clear pre");
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
         GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
         GL20.glDrawBuffers(ProgramShadow.getDrawBuffers());
         checkFramebufferStatus("shadow clear");
         GL11.glClear(16640);
         checkGLError("shadow clear");
      } else {
         checkGLError("clear pre");
         if (field_0136[0]) {
            Vector4f var0 = gbuffersClearColor[0];
            if (var0 != null) {
               GL11.glClearColor(var0.getX(), var0.getY(), var0.getZ(), var0.getW());
            }

            if (dfbColorTexturesFlip.isChanged(0)) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, dfbColorTexturesFlip.getB(0), 0);
               GL20.glDrawBuffers(36064);
               GL11.glClear(16384);
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, dfbColorTexturesFlip.getA(0), 0);
            }

            GL20.glDrawBuffers(36064);
            GL11.glClear(16384);
         }

         if (field_0136[1]) {
            GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
            Vector4f var2 = gbuffersClearColor[1];
            if (var2 != null) {
               GL11.glClearColor(var2.getX(), var2.getY(), var2.getZ(), var2.getW());
            }

            if (dfbColorTexturesFlip.isChanged(1)) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36065, 3553, dfbColorTexturesFlip.getB(1), 0);
               GL20.glDrawBuffers(36065);
               GL11.glClear(16384);
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36065, 3553, dfbColorTexturesFlip.getA(1), 0);
            }

            GL20.glDrawBuffers(36065);
            GL11.glClear(16384);
         }

         for (int var3 = 2; var3 < usedColorBuffers; var3++) {
            if (field_0136[var3]) {
               GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
               Vector4f var1 = gbuffersClearColor[var3];
               if (var1 != null) {
                  GL11.glClearColor(var1.getX(), var1.getY(), var1.getZ(), var1.getW());
               }

               if (dfbColorTexturesFlip.isChanged(var3)) {
                  EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var3, 3553, dfbColorTexturesFlip.getB(var3), 0);
                  GL20.glDrawBuffers(36064 + var3);
                  GL11.glClear(16384);
                  EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var3, 3553, dfbColorTexturesFlip.getA(var3), 0);
               }

               GL20.glDrawBuffers(36064 + var3);
               GL11.glClear(16384);
            }
         }

         setDrawBuffers(dfbDrawBuffers);
         checkFramebufferStatus("clear");
         checkGLError("clear");
      }
   }

   public static void preDrawComposite() {
      RenderScale var0 = activeProgram.getRenderScale();
      if (var0 != null) {
         int var1 = (int)(renderWidth * var0.getOffsetX());
         int var2 = (int)(renderHeight * var0.getOffsetY());
         int var3 = (int)(renderWidth * var0.getScale());
         int var4 = (int)(renderHeight * var0.getScale());
         GL11.glViewport(var1, var2, var3, var4);
      }
   }

   public static int method_02223(Program var0, String var1) {
      int var2 = ARBShaderObjects.glCreateShaderObjectARB(35632);
      if (var2 == 0) {
         return 0;
      } else {
         StringBuilder var3 = new StringBuilder(131072);
         BufferedReader var4 = null;

         try {
            var4 = new BufferedReader(getShaderReader(var1));
         } catch (Exception var14) {
            ARBShaderObjects.glDeleteObjectARB(var2);
            return 0;
         }

         ShaderOption[] var5 = method_02355(shaderPackOptions);
         ArrayList var6 = new ArrayList();
         if (var4 != null) {
            try {
               var4 = ShaderPackParser.resolveIncludes((BufferedReader)var4, var1, shaderPack, 0, var6, 0);
               MacroState var7 = new MacroState();

               while (true) {
                  String var8 = var4.readLine();
                  if (var8 == null) {
                     var4.close();
                     break;
                  }

                  var8 = applyOptions(var8, var5);
                  var3.append(var8).append('\n');
                  if (var7.processLine(var8)) {
                     ShaderLine var9 = ShaderParser.parseLine(var8);
                     if (var9 != null) {
                        if (var9.isUniform()) {
                           String var10 = var9.getName();
                           int var11;
                           if ((var11 = ShaderParser.method_00223(var10)) >= 0) {
                              usedShadowDepthBuffers = Math.max(usedShadowDepthBuffers, var11 + 1);
                           } else if ((var11 = ShaderParser.method_00217(var10)) >= 0) {
                              usedShadowColorBuffers = Math.max(usedShadowColorBuffers, var11 + 1);
                           } else if ((var11 = ShaderParser.getDepthIndex(var10)) >= 0) {
                              usedDepthBuffers = Math.max(usedDepthBuffers, var11 + 1);
                           } else if (var10.equals("gdepth") && gbuffersFormat[1] == 6408) {
                              gbuffersFormat[1] = 34836;
                           } else if ((var11 = ShaderParser.method_00225(var10)) >= 0) {
                              usedColorBuffers = Math.max(usedColorBuffers, var11 + 1);
                           } else if (var10.equals("centerDepthSmooth")) {
                              centerDepthSmoothEnabled = true;
                           }
                        } else if (var9.method_09536("shadowMapResolution") || var9.isProperty("SHADOWRES")) {
                           spShadowMapWidth = spShadowMapHeight = var9.getValueInt();
                           shadowMapWidth = shadowMapHeight = Math.round(spShadowMapWidth * configShadowResMul);
                           SMCLog.info("Shadow map resolution: " + spShadowMapWidth);
                        } else if (var9.method_09542("shadowMapFov") || var9.isProperty("SHADOWFOV")) {
                           field_0043 = var9.getValueFloat();
                           field_0173 = false;
                           SMCLog.info("Shadow map field of view: " + field_0043);
                        } else if (var9.method_09542("shadowDistance") || var9.isProperty("SHADOWHPL")) {
                           field_0243 = var9.getValueFloat();
                           field_0173 = true;
                           SMCLog.info("Shadow map distance: " + field_0243);
                        } else if (var9.method_09542("shadowDistanceRenderMul")) {
                           field_0268 = var9.getValueFloat();
                           SMCLog.info("Shadow distance render mul: " + field_0268);
                        } else if (var9.method_09542("shadowIntervalSize")) {
                           field_0359 = var9.getValueFloat();
                           SMCLog.info("Shadow map interval size: " + field_0359);
                        } else if (var9.method_09538("generateShadowMipmap", true)) {
                           Arrays.fill(shadowMipmapEnabled, true);
                           SMCLog.info("Generate shadow mipmap");
                        } else if (var9.method_09538("generateShadowColorMipmap", true)) {
                           Arrays.fill(shadowColorMipmapEnabled, true);
                           SMCLog.info("Generate shadow color mipmap");
                        } else if (var9.method_09538("shadowHardwareFiltering", true)) {
                           Arrays.fill(field_0217, true);
                           SMCLog.info("Hardware shadow filtering enabled.");
                        } else if (var9.method_09538("shadowHardwareFiltering0", true)) {
                           field_0217[0] = true;
                           SMCLog.info("shadowHardwareFiltering0");
                        } else if (var9.method_09538("shadowHardwareFiltering1", true)) {
                           field_0217[1] = true;
                           SMCLog.info("shadowHardwareFiltering1");
                        } else if (var9.isConstBool("shadowtex0Mipmap", "shadowtexMipmap", true)) {
                           shadowMipmapEnabled[0] = true;
                           SMCLog.info("shadowtex0Mipmap");
                        } else if (var9.method_09538("shadowtex1Mipmap", true)) {
                           shadowMipmapEnabled[1] = true;
                           SMCLog.info("shadowtex1Mipmap");
                        } else if (var9.isConstBool("shadowcolor0Mipmap", "shadowColor0Mipmap", true)) {
                           shadowColorMipmapEnabled[0] = true;
                           SMCLog.info("shadowcolor0Mipmap");
                        } else if (var9.isConstBool("shadowcolor1Mipmap", "shadowColor1Mipmap", true)) {
                           shadowColorMipmapEnabled[1] = true;
                           SMCLog.info("shadowcolor1Mipmap");
                        } else if (var9.isConstBool("shadowtex0Nearest", "shadowtexNearest", "shadow0MinMagNearest", true)) {
                           shadowFilterNearest[0] = true;
                           SMCLog.info("shadowtex0Nearest");
                        } else if (var9.isConstBool("shadowtex1Nearest", "shadow1MinMagNearest", true)) {
                           shadowFilterNearest[1] = true;
                           SMCLog.info("shadowtex1Nearest");
                        } else if (var9.isConstBool("shadowcolor0Nearest", "shadowColor0Nearest", "shadowColor0MinMagNearest", true)) {
                           shadowColorFilterNearest[0] = true;
                           SMCLog.info("shadowcolor0Nearest");
                        } else if (var9.isConstBool("shadowcolor1Nearest", "shadowColor1Nearest", "shadowColor1MinMagNearest", true)) {
                           shadowColorFilterNearest[1] = true;
                           SMCLog.info("shadowcolor1Nearest");
                        } else if (var9.method_09542("wetnessHalflife") || var9.isProperty("WETNESSHL")) {
                           field_0326 = var9.getValueFloat();
                           SMCLog.info("Wetness halflife: " + field_0326);
                        } else if (var9.method_09542("drynessHalflife") || var9.isProperty("DRYNESSHL")) {
                           field_0265 = var9.getValueFloat();
                           SMCLog.info("Dryness halflife: " + field_0265);
                        } else if (var9.method_09542("eyeBrightnessHalflife")) {
                           field_0113 = var9.getValueFloat();
                           SMCLog.info("Eye brightness halflife: " + field_0113);
                        } else if (var9.method_09542("centerDepthHalflife")) {
                           centerDepthSmoothHalflife = var9.getValueFloat();
                           SMCLog.info("Center depth halflife: " + centerDepthSmoothHalflife);
                        } else if (var9.method_09542("sunPathRotation")) {
                           sunPathRotation = var9.getValueFloat();
                           SMCLog.info("Sun path rotation: " + sunPathRotation);
                        } else if (var9.method_09542("ambientOcclusionLevel")) {
                           field_0079 = Config.limit(var9.getValueFloat(), 0.0F, 1.0F);
                           SMCLog.info("AO Level: " + field_0079);
                        } else if (var9.method_09536("superSamplingLevel")) {
                           int var19 = var9.getValueInt();
                           if (var19 > 1) {
                              SMCLog.info("Super sampling level: " + var19 + "x");
                              field_0182 = var19;
                           } else {
                              field_0182 = 1;
                           }
                        } else if (var9.method_09536("noiseTextureResolution")) {
                           noiseTextureResolution = var9.getValueInt();
                           noiseTextureEnabled = true;
                           SMCLog.info("Noise texture enabled");
                           SMCLog.info("Noise texture resolution: " + noiseTextureResolution);
                        } else if (var9.method_09513("Format")) {
                           String var20 = StrUtils.removeSuffix(var9.getName(), "Format");
                           String var28 = var9.getValue();
                           int var12 = method_02329(var20);
                           int var13 = getTextureFormatFromString(var28);
                           if (var12 >= 0 && var13 != 0) {
                              gbuffersFormat[var12] = var13;
                              SMCLog.info("%s format: %s", var20, var28);
                           }
                        } else if (var9.method_09527("Clear", false)) {
                           if (ShaderParser.isComposite(var1) || ShaderParser.isDeferred(var1)) {
                              String var21 = StrUtils.removeSuffix(var9.getName(), "Clear");
                              int var29 = method_02329(var21);
                              if (var29 >= 0) {
                                 field_0136[var29] = false;
                                 SMCLog.info("%s clear disabled", var21);
                              }
                           }
                        } else if (var9.method_09520("ClearColor")) {
                           if (ShaderParser.isComposite(var1) || ShaderParser.isDeferred(var1)) {
                              String var22 = StrUtils.removeSuffix(var9.getName(), "ClearColor");
                              int var30 = method_02329(var22);
                              if (var30 >= 0) {
                                 Vector4f var32 = var9.getValueVec4();
                                 if (var32 != null) {
                                    gbuffersClearColor[var30] = var32;
                                    SMCLog.info("%s clear color: %s %s %s %s", var22, var32.getX(), var32.getY(), var32.getZ(), var32.getW());
                                 } else {
                                    SMCLog.warning("Invalid color value: " + var9.getValue());
                                 }
                              }
                           }
                        } else if (var9.isProperty("GAUX4FORMAT", "RGBA32F")) {
                           gbuffersFormat[7] = 34836;
                           SMCLog.info("gaux4 format : RGB32AF");
                        } else if (var9.isProperty("GAUX4FORMAT", "RGB32F")) {
                           gbuffersFormat[7] = 34837;
                           SMCLog.info("gaux4 format : RGB32F");
                        } else if (var9.isProperty("GAUX4FORMAT", "RGB16")) {
                           gbuffersFormat[7] = 32852;
                           SMCLog.info("gaux4 format : RGB16");
                        } else if (var9.method_09527("MipmapEnabled", true)) {
                           if (ShaderParser.isComposite(var1) || ShaderParser.isDeferred(var1) || ShaderParser.isFinal(var1)) {
                              String var23 = StrUtils.removeSuffix(var9.getName(), "MipmapEnabled");
                              int var31 = method_02329(var23);
                              if (var31 >= 0) {
                                 int var33 = var0.getCompositeMipmapSetting();
                                 var33 |= 1 << var31;
                                 var0.setCompositeMipmapSetting(var33);
                                 SMCLog.info("%s mipmap enabled", var23);
                              }
                           }
                        } else if (var9.isProperty("DRAWBUFFERS")) {
                           String var24 = var9.getValue();
                           if (ShaderParser.isValidDrawBuffers(var24)) {
                              var0.setDrawBufSettings(var24);
                           } else {
                              SMCLog.warning("Invalid draw buffers: " + var24);
                           }
                        }
                     }
                  }
               }
            } catch (Exception var15) {
               SMCLog.severe("Couldn't read " + var1 + "!");
               var15.printStackTrace();
               ARBShaderObjects.glDeleteObjectARB(var2);
               return 0;
            }
         }

         if (saveFinalShaders) {
            saveShader(var1, var3.toString());
         }

         ARBShaderObjects.glShaderSourceARB(var2, var3);
         ARBShaderObjects.glCompileShaderARB(var2);
         if (GL20.glGetShaderi(var2, 35713) != 1) {
            SMCLog.severe("Error compiling fragment shader: " + var1);
         }

         printShaderLogInfo(var2, var1, var6);
         return var2;
      }
   }

   public static void setProgramUniform1i(ShaderUniform1i var0, int var1) {
      var0.setValue(var1);
   }

   public static void beginBlockEntities() {
      if (isRenderingWorld) {
         checkGLError("beginBlockEntities");
         useProgram(ProgramBlock);
      }
   }

   public static void setShaderPack(String var0) {
      currentShaderName = var0;
      shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), var0);
      loadShaderPack();
   }

   public static void method_02178() {
      if (!isShadowPass) {
         checkBufferFlip(field_0301);
         checkGLError("pre-render CompositeFinal");
         renderComposites(field_0053, true);
      }
   }

   public static int getEntityData2() {
      return entityData[entityDataIndex * 2 + 1];
   }

   public static void method_02298(int var0) {
      GL11.glEnable(var0);
      enableFog();
   }

   public static void scheduleResizeShadow() {
      needResizeShadow = true;
   }

   public static void nextBlockEntity(TileEntity var0) {
      if (isRenderingWorld) {
         checkGLError("nextBlockEntity");
         useProgram(ProgramBlock);
         setBlockEntityId(var0);
      }
   }

   public static ICustomTexture loadCustomTextureRaw(
      int var0, String var1, String var2, TextureType var3, InternalFormat var4, int var5, int var6, int var7, PixelFormat var8, PixelType var9
   ) {
      try {
         String var10 = "shaders/" + StrUtils.removePrefix(var2, "/");
         InputStream var11 = shaderPack.getResourceAsStream(var10);
         if (var11 == null) {
            SMCLog.warning("Raw texture not found: " + var2);
            return null;
         } else {
            byte[] var12 = Config.readAll(var11);
            IOUtils.closeQuietly(var11);
            ByteBuffer var13 = GLAllocation.createDirectByteBuffer(var12.length);
            var13.put(var12);
            ((Buffer)var13).flip();
            TextureMetadataSection var14 = SimpleShaderTexture.loadTextureMetadataSection(var10, new TextureMetadataSection(true, true, new ArrayList<>()));
            return new CustomTextureRaw(var3, var4, var5, var6, var7, var8, var9, var13, var0, var14.getTextureBlur(), var14.getTextureClamp());
         }
      } catch (IOException var16) {
         SMCLog.warning("Error loading raw texture: " + var2);
         SMCLog.warning("" + var16.getClass().getName() + ": " + var16.getMessage());
         return null;
      }
   }

   public static void bindCustomTextures(ICustomTexture[] var0) {
      if (var0 != null) {
         for (int var1 = 0; var1 < var0.length; var1++) {
            ICustomTexture var2 = var0[var1];
            GlStateManager.setActiveTexture(33984 + var2.getTextureUnit());
            int var3 = var2.getTextureId();
            int var4 = var2.getTarget();
            if (var4 == 3553) {
               GlStateManager.bindTexture(var3);
            } else {
               GL11.glBindTexture(var4, var3);
            }
         }
      }
   }

   public static void postDrawComposite() {
      RenderScale var0 = activeProgram.getRenderScale();
      if (var0 != null) {
         GL11.glViewport(0, 0, renderWidth, renderHeight);
      }
   }

   public static void applyHandDepth() {
      if (configHandDepthMul != 1.0) {
         GL11.glScaled(1.0, 1.0, configHandDepthMul);
      }
   }

   public static int getBlockEntityId(TileEntity var0) {
      if (var0 == null) {
         return -1;
      } else {
         Block var1 = var0.w();
         if (var1 == null) {
            return 0;
         } else {
            int var2 = Block.getIdFromBlock(var1);
            int var3 = var0.u();
            int var4 = BlockAliases.getBlockAliasId(var2, var3);
            if (var4 >= 0) {
               var2 = var4;
            }

            return var2;
         }
      }
   }

   public static void loadShaderPackDimensions() {
      shaderPackDimensions.clear();

      for (int var0 = -128; var0 <= 128; var0++) {
         String var1 = "/shaders/world" + var0;
         if (shaderPack.hasDirectory(var1)) {
            shaderPackDimensions.add(var0);
         }
      }

      if (shaderPackDimensions.size() > 0) {
         Integer[] var2 = shaderPackDimensions.toArray(new Integer[shaderPackDimensions.size()]);
         Config.dbg("[Shaders] Worlds: " + Config.arrayToString(var2));
      }
   }

   public static void renderDeferred() {
      if (!isShadowPass) {
         boolean var0 = checkBufferFlip(field_0280);
         if (field_0348) {
            checkGLError("pre-render Deferred");
            renderComposites(field_0304, false);
            var0 = true;
         }

         if (var0) {
            bindGbuffersTextures();

            for (int var1 = 0; var1 < usedColorBuffers; var1++) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var1, 3553, dfbColorTexturesFlip.getA(var1), 0);
            }

            if (ProgramWater.getDrawBuffers() != null) {
               setDrawBuffers(ProgramWater.getDrawBuffers());
            } else {
               setDrawBuffers(dfbDrawBuffers);
            }

            GlStateManager.setActiveTexture(33984);
            mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         }
      }
   }

   public static void endHand() {
      checkGLError("pre endHand");
      checkFramebufferStatus("pre endHand");
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      GlStateManager.blendFunc(770, 771);
      checkGLError("endHand");
   }

   public static void preCelestialRotate() {
      GL11.glRotatef(sunPathRotation * 1.0F, 0.0F, 0.0F, 1.0F);
      checkGLError("preCelestialRotate");
   }

   public static void beginLivingDamage() {
      if (isRenderingWorld) {
         ShadersTex.bindTexture(defaultTexture);
         if (!isShadowPass) {
            setDrawBuffers(drawBuffersColorAtt0);
         }
      }
   }

   public static void saveShaderPackOptions(ShaderOption[] var0, IShaderPack var1) {
      PropertiesOrdered var2 = new PropertiesOrdered();
      if (shaderPackOptions != null) {
         for (int var3 = 0; var3 < var0.length; var3++) {
            ShaderOption var4 = var0[var3];
            if (var4.isChanged() && var4.isEnabled()) {
               var2.setProperty(var4.getName(), var4.getValue());
            }
         }
      }

      try {
         saveOptionProperties(var1, var2);
      } catch (IOException var5) {
         Config.warn("[Shaders] Error saving configuration for " + shaderPack.getName());
         var5.printStackTrace();
      }
   }

   public static void resetCustomTextures() {
      deleteCustomTextures(customTexturesGbuffers);
      deleteCustomTextures(customTexturesComposite);
      deleteCustomTextures(customTexturesDeferred);
      customTexturesGbuffers = null;
      customTexturesComposite = null;
      customTexturesDeferred = null;
   }

   public static void saveShaderPackOptions() {
      saveShaderPackOptions(shaderPackOptions, shaderPack);
   }

   public static int getTextureFormatFromString(String var0) {
      var0 = var0.trim();

      for (int var1 = 0; var1 < formatNames.length; var1++) {
         String var2 = formatNames[var1];
         if (var0.equals(var2)) {
            return formatIds[var1];
         }
      }

      return 0;
   }

   public static int getDimensionId(World var0) {
      return var0 == null ? Integer.MIN_VALUE : var0.t.getDimensionId();
   }

   public static void beginSpiderEyes() {
      if (isRenderingWorld && field_0328.getId() != ProgramNone.getId()) {
         useProgram(field_0328);
         GlStateManager.enableAlpha();
         GlStateManager.alphaFunc(516, 0.0F);
         GlStateManager.blendFunc(770, 771);
      }
   }

   public static void pushEntity(int var0, int var1) {
      entityDataIndex++;
      entityData[entityDataIndex * 2] = var0 & 65535 | var1 << 16;
      entityData[entityDataIndex * 2 + 1] = 0;
   }

   public static void setCameraShadow(float var0) {
      Entity var1 = mc.getRenderViewEntity();
      double var2 = var1.P + (var1.s - var1.P) * var0;
      double var4 = var1.Q + (var1.t - var1.Q) * var0;
      double var6 = var1.R + (var1.u - var1.R) * var0;
      updateCameraOffset(var1);
      cameraPositionX = var2 - cameraOffsetX;
      cameraPositionY = var4;
      cameraPositionZ = var6 - cameraOffsetZ;
      GL11.glGetFloat(2983, (FloatBuffer)((Buffer)projection).position(0));
      SMath.invertMat4FBFA(
         (FloatBuffer)((Buffer)projectionInverse).position(0), (FloatBuffer)((Buffer)projection).position(0), faProjectionInverse, faProjection
      );
      ((Buffer)projection).position(0);
      ((Buffer)projectionInverse).position(0);
      GL11.glGetFloat(2982, (FloatBuffer)((Buffer)modelView).position(0));
      SMath.invertMat4FBFA((FloatBuffer)((Buffer)modelViewInverse).position(0), (FloatBuffer)((Buffer)modelView).position(0), faModelViewInverse, faModelView);
      ((Buffer)modelView).position(0);
      ((Buffer)modelViewInverse).position(0);
      GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      if (field_0173) {
         GL11.glOrtho(-field_0243, field_0243, -field_0243, field_0243, 0.05F, 256.0);
      } else {
         GLU.gluPerspective(field_0043, (float)shadowMapWidth / shadowMapHeight, 0.05F, 256.0F);
      }

      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glTranslatef(0.0F, 0.0F, -100.0F);
      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      field_0218 = mc.theWorld.getCelestialAngle(var0);
      sunAngle = field_0218 < 0.75F ? field_0218 + 0.25F : field_0218 - 0.75F;
      float var8 = field_0218 * -360.0F;
      float var9 = field_0327 > 0.0F ? var8 % field_0327 - field_0327 * 0.5F : 0.0F;
      if (sunAngle <= 0.5) {
         GL11.glRotatef(var8 - var9, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
         shadowAngle = sunAngle;
      } else {
         GL11.glRotatef(var8 + 180.0F - var9, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
         shadowAngle = sunAngle - 0.5F;
      }

      if (field_0173) {
         float var10 = field_0359;
         float var11 = var10 / 2.0F;
         GL11.glTranslatef((float)var2 % var10 - var11, (float)var4 % var10 - var11, (float)var6 % var10 - var11);
      }

      float var17 = sunAngle * (float) (Math.PI * 2);
      float var18 = (float)Math.cos(var17);
      float var12 = (float)Math.sin(var17);
      float var13 = sunPathRotation * (float) (Math.PI * 2);
      float var14 = var18;
      float var15 = var12 * (float)Math.cos(var13);
      float var16 = var12 * (float)Math.sin(var13);
      if (sunAngle > 0.5) {
         var14 = -var18;
         var15 = -var15;
         var16 = -var16;
      }

      shadowLightPositionVector[0] = var14;
      shadowLightPositionVector[1] = var15;
      shadowLightPositionVector[2] = var16;
      shadowLightPositionVector[3] = 0.0F;
      GL11.glGetFloat(2983, (FloatBuffer)((Buffer)field_0095).position(0));
      SMath.invertMat4FBFA((FloatBuffer)((Buffer)field_0023).position(0), (FloatBuffer)((Buffer)field_0095).position(0), field_0353, field_0000);
      ((Buffer)field_0095).position(0);
      ((Buffer)field_0023).position(0);
      GL11.glGetFloat(2982, (FloatBuffer)((Buffer)field_0314).position(0));
      SMath.invertMat4FBFA((FloatBuffer)((Buffer)field_0294).position(0), (FloatBuffer)((Buffer)field_0314).position(0), field_0212, field_0149);
      ((Buffer)field_0314).position(0);
      ((Buffer)field_0294).position(0);
      setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
      setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
      setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, field_0336);
      setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
      setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
      setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, field_0127);
      setProgramUniformMatrix4ARB(uniform_shadowProjection, false, field_0095);
      setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, field_0023);
      setProgramUniformMatrix4ARB(uniform_shadowModelView, false, field_0314);
      setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, field_0294);
      mc.gameSettings.thirdPersonView = 1;
      checkGLError("setCamera");
   }

   public static ICustomTexture method_02184(int var0, String var1) {
      ConnectedParser var2 = new ConnectedParser("Shaders");
      String[] var3 = Config.tokenize(var1, " ");
      ArrayDeque var4 = new ArrayDeque<>(Arrays.asList(var3));
      String var5 = (String)var4.poll();
      TextureType var6 = (TextureType)var2.parseEnum((String)var4.poll(), TextureType.values(), "texture type");
      if (var6 == null) {
         SMCLog.warning("Invalid raw texture type: " + var1);
         return null;
      } else {
         InternalFormat var7 = (InternalFormat)var2.parseEnum((String)var4.poll(), InternalFormat.values(), "internal format");
         if (var7 == null) {
            SMCLog.warning("Invalid raw texture internal format: " + var1);
            return null;
         } else {
            int var8 = 0;
            int var9 = 0;
            int var10 = 0;
            switch (Shaders$1.$SwitchMap$net$optifine$texture$TextureType[var6.ordinal()]) {
               case 1:
                  var8 = var2.parseInt((String)var4.poll(), -1);
                  break;
               case 2:
                  var8 = var2.parseInt((String)var4.poll(), -1);
                  var9 = var2.parseInt((String)var4.poll(), -1);
                  break;
               case 3:
                  var8 = var2.parseInt((String)var4.poll(), -1);
                  var9 = var2.parseInt((String)var4.poll(), -1);
                  var10 = var2.parseInt((String)var4.poll(), -1);
                  break;
               case 4:
                  var8 = var2.parseInt((String)var4.poll(), -1);
                  var9 = var2.parseInt((String)var4.poll(), -1);
                  break;
               default:
                  SMCLog.warning("Invalid raw texture type: " + var6);
                  return null;
            }

            if (var8 >= 0 && var9 >= 0 && var10 >= 0) {
               PixelFormat var11 = (PixelFormat)var2.parseEnum((String)var4.poll(), PixelFormat.values(), "pixel format");
               if (var11 == null) {
                  SMCLog.warning("Invalid raw texture pixel format: " + var1);
                  return null;
               } else {
                  PixelType var12 = (PixelType)var2.parseEnum((String)var4.poll(), PixelType.values(), "pixel type");
                  if (var12 == null) {
                     SMCLog.warning("Invalid raw texture pixel type: " + var1);
                     return null;
                  } else if (!var4.isEmpty()) {
                     SMCLog.warning("Invalid raw texture, too many parameters: " + var1);
                     return null;
                  } else {
                     return loadCustomTextureRaw(var0, var1, var5, var6, var7, var8, var9, var10, var11, var12);
                  }
               }
            } else {
               SMCLog.warning("Invalid raw texture size: " + var1);
               return null;
            }
         }
      }
   }

   public static boolean method_02367() {
      return !field_0032.isFalse();
   }

   public static ShaderOption getShaderOption(String var0) {
      return ShaderUtils.getShaderOption(var0, shaderPackOptions);
   }

   public static void endFPOverlay() {
   }

   public static void beginUpdateChunks() {
      checkGLError("beginUpdateChunks1");
      checkFramebufferStatus("beginUpdateChunks1");
      if (!isShadowPass) {
         useProgram(ProgramTerrain);
      }

      checkGLError("beginUpdateChunks2");
      checkFramebufferStatus("beginUpdateChunks2");
   }

   public static void disableLightmap() {
      lightmapEnabled = false;
      if (activeProgram == ProgramTexturedLit) {
         useProgram(ProgramTextured);
      }
   }

   public static int checkGLError(String var0) {
      int var1 = GlStateManager.glGetError();
      if (var1 != 0 && GlErrors.isEnabled(var1)) {
         String var2 = Config.getGlErrorString(var1);
         String var3 = getErrorInfo(var1, var0);
         String var4 = String.format("OpenGL error: %s (%s)%s, at: %s", var1, var2, var3, var0);
         SMCLog.severe(var4);
         if (Config.isShowGlErrors() && TimedEvent.isActive("ShowGlErrorShaders", -4320897591697133766L & 4320897589821093840L)) {
            String var5 = I18n.format("of.message.openglError", var1, var2);
            printChat(var5);
         }
      }

      return var1;
   }

   public static void sglFogi(int var0, int var1) {
      GL11.glFogi(var0, var1);
      if (var0 == 2917) {
         fogMode = var1;
         if (fogEnabled) {
            setProgramUniform1i(uniform_fogMode, fogMode);
         }
      }
   }

   public static void preWater() {
      if (usedDepthBuffers >= 2) {
         GlStateManager.setActiveTexture(33995);
         checkGLError("pre copy depth");
         GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
         checkGLError("copy depth");
         GlStateManager.setActiveTexture(33984);
      }

      ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
   }

   public static void drawComposite() {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      drawCompositeQuad();
      int var0 = activeProgram.getCountInstances();
      if (var0 > 1) {
         for (int var1 = 1; var1 < var0; var1++) {
            uniform_instanceId.setValue(var1);
            drawCompositeQuad();
         }

         uniform_instanceId.setValue(0);
      }
   }

   public static boolean isRenderBackFace(EnumWorldBlockLayer var0) {
      switch (Shaders$1.$SwitchMap$net$minecraft$util$EnumWorldBlockLayer[var0.ordinal()]) {
         case 1:
            return shaderPackBackFaceSolid.isTrue();
         case 2:
            return shaderPackBackFaceCutout.isTrue();
         case 3:
            return shaderPackBackFaceCutoutMipped.isTrue();
         case 4:
            return shaderPackBackFaceTranslucent.isTrue();
         default:
            return false;
      }
   }

   public static ShaderOption[] loadShaderPackOptions() {
      try {
         String[] var0 = programs.getProgramNames();
         ShaderOption[] var1 = ShaderPackParser.parseShaderPackOptions(shaderPack, var0, shaderPackDimensions);
         Properties var2 = loadOptionProperties(shaderPack);

         for (int var3 = 0; var3 < var1.length; var3++) {
            ShaderOption var4 = var1[var3];
            String var5 = var2.getProperty(var4.getName());
            if (var5 != null) {
               var4.resetValue();
               if (!var4.setValue(var5)) {
                  Config.warn("[Shaders] Invalid value, option: " + var4.getName() + ", value: " + var5);
               }
            }
         }

         return var1;
      } catch (IOException var6) {
         Config.warn("[Shaders] Error reading configuration for " + shaderPack.getName());
         var6.printStackTrace();
         return null;
      }
   }

   public static void endEntitiesGlowing() {
      if (isRenderingWorld) {
         isEntitiesGlowing = false;
      }
   }

   public static IShaderPack getShaderPack(String var0) {
      if (var0 == null) {
         return null;
      } else {
         var0 = var0.trim();
         if (var0.isEmpty() || var0.equals("OFF")) {
            return null;
         } else if (var0.equals("(internal)")) {
            return new ShaderPackDefault();
         } else {
            try {
               File var1 = new File(shaderPacksDir, var0);
               return (IShaderPack)(var1.isDirectory()
                  ? new UnidentifiedClass4671(var0, var1)
                  : (var1.isFile() && var0.toLowerCase().endsWith(".zip") ? new ShaderPackZip(var0, var1) : null));
            } catch (Exception var2) {
               var2.printStackTrace();
               return null;
            }
         }
      }
   }

   public static float getShadowRenderDistance() {
      return field_0268 < 0.0F ? -1.0F : field_0243 * field_0268;
   }

   public static boolean method_02317() {
      return field_0390 ? capabilities.GL_NV_geometry_shader4 : true;
   }

   public static boolean isSeparateAo() {
      return field_0340.isTrue();
   }

   public static void setProgramUniform3f(ShaderUniform3f var0, float var1, float var2, float var3) {
      var0.setValue(var1, var2, var3);
   }

   public static World getCurrentWorld() {
      return currentWorld;
   }

   public static boolean method_02318() {
      return field_0186.isTrue();
   }

   public static boolean isTranslucentBlock(ItemStack var0) {
      if (var0 == null) {
         return false;
      } else {
         Item var1 = var0.getItem();
         if (var1 == null) {
            return false;
         } else if (!(var1 instanceof ItemBlock)) {
            return false;
         } else {
            ItemBlock var2 = (ItemBlock)var1;
            Block var3 = var2.getBlock();
            if (var3 == null) {
               return false;
            } else {
               EnumWorldBlockLayer var4 = var3.getBlockLayer();
               return var4 == EnumWorldBlockLayer.TRANSLUCENT;
            }
         }
      }
   }

   public static int checkFramebufferStatus(String var0) {
      int var1 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
      if (var1 != 36053) {
         System.err.format("FramebufferStatus 0x%04X at %s\n", var1, var0);
      }

      return var1;
   }

   public static BlockPos getCameraPosition() {
      return new BlockPos(cameraPositionX, cameraPositionY, cameraPositionZ);
   }

   public static void setDrawBuffers(IntBuffer var0) {
      if (var0 == null) {
         var0 = drawBuffersNone;
      }

      if (activeDrawBuffers != var0) {
         activeDrawBuffers = var0;
         GL20.glDrawBuffers(var0);
         checkGLError("setDrawBuffers");
      }
   }

   public static boolean checkBufferFlip(Program var0) {
      boolean var1 = false;
      Boolean[] var2 = var0.getBuffersFlip();

      for (int var3 = 0; var3 < usedColorBuffers; var3++) {
         if (Config.isTrue(var2[var3])) {
            dfbColorTexturesFlip.flip(var3);
            var1 = true;
         }
      }

      return var1;
   }

   public static void updateCameraOffset(Entity var0) {
      double var1 = Math.abs(cameraPositionX - previousCameraPositionX);
      double var3 = Math.abs(cameraPositionZ - previousCameraPositionZ);
      double var5 = Math.abs(cameraPositionX);
      double var7 = Math.abs(cameraPositionZ);
      if (var1 > 1000.0 || var3 > 1000.0 || var5 > 1000000.0 || var7 > 1000000.0) {
         setCameraOffset(var0);
      }
   }

   public static void setHandsRendered(boolean var0, boolean var1) {
      isHandRenderedMain = var0;
      isHandRenderedOff = var1;
   }

   public static void checkShadersModInstalled() {
      try {
         Class var0 = Class.forName("shadersmod.transform.SMCClassTransformer");
      } catch (Throwable var1) {
         return;
      }

      throw new RuntimeException("Shaders Mod detected. Please remove it, OptiFine has built-in support for shaders.");
   }

   public static void updateAlphaBlend(Program var0, Program var1) {
      if (var0.getAlphaState() != null) {
         GlStateManager.unlockAlpha();
      }

      if (var0.getBlendState() != null) {
         GlStateManager.unlockBlend();
      }

      GlAlphaState var2 = var1.getAlphaState();
      if (var2 != null) {
         GlStateManager.lockAlpha(var2);
      }

      GlBlendState var3 = var1.getBlendState();
      if (var3 != null) {
         GlStateManager.lockBlend(var3);
      }
   }

   public static InputStream getShaderPackResourceStream(String var0) {
      return shaderPack == null ? null : shaderPack.getResourceAsStream(var0);
   }

   public static void setupProgram(Program var0, String var1, String var2, String var3) {
      checkGLError("pre setupProgram");
      int var4 = ARBShaderObjects.glCreateProgramObjectARB();
      checkGLError("create");
      if (var4 != 0) {
         field_0338 = false;
         field_0027 = false;
         field_0395 = false;
         int var5 = method_02248(var0, var1);
         int var6 = method_02345(var0, var2);
         int var7 = method_02223(var0, var3);
         checkGLError("create");
         if (var5 == 0 && var6 == 0 && var7 == 0) {
            ARBShaderObjects.glDeleteObjectARB(var4);
            boolean var11 = false;
            var0.resetId();
         } else {
            if (var5 != 0) {
               ARBShaderObjects.glAttachObjectARB(var4, var5);
               checkGLError("attach");
            }

            if (var6 != 0) {
               ARBShaderObjects.glAttachObjectARB(var4, var6);
               checkGLError("attach");
               if (field_0329) {
                  ARBGeometryShader4.glProgramParameteriARB(var4, 36315, 4);
                  ARBGeometryShader4.glProgramParameteriARB(var4, 36316, 5);
                  ARBGeometryShader4.glProgramParameteriARB(var4, 36314, field_0086);
                  checkGLError("arbGeometryShader4");
               }

               field_0390 = true;
            }

            if (var7 != 0) {
               ARBShaderObjects.glAttachObjectARB(var4, var7);
               checkGLError("attach");
            }

            if (field_0338) {
               ARBVertexShader.glBindAttribLocationARB(var4, entityAttrib, "mc_Entity");
               checkGLError("mc_Entity");
            }

            if (field_0027) {
               ARBVertexShader.glBindAttribLocationARB(var4, midTexCoordAttrib, "mc_midTexCoord");
               checkGLError("mc_midTexCoord");
            }

            if (field_0395) {
               ARBVertexShader.glBindAttribLocationARB(var4, tangentAttrib, "at_tangent");
               checkGLError("at_tangent");
            }

            ARBShaderObjects.glLinkProgramARB(var4);
            if (GL20.glGetProgrami(var4, 35714) != 1) {
               SMCLog.severe("Error linking program: " + var4 + " (" + var0.getName() + ")");
            }

            printLogInfo(var4, var0.getName());
            if (var5 != 0) {
               ARBShaderObjects.glDetachObjectARB(var4, var5);
               ARBShaderObjects.glDeleteObjectARB(var5);
            }

            if (var6 != 0) {
               ARBShaderObjects.glDetachObjectARB(var4, var6);
               ARBShaderObjects.glDeleteObjectARB(var6);
            }

            if (var7 != 0) {
               ARBShaderObjects.glDetachObjectARB(var4, var7);
               ARBShaderObjects.glDeleteObjectARB(var7);
            }

            var0.setId(var4);
            var0.setRef(var4);
            useProgram(var0);
            ARBShaderObjects.glValidateProgramARB(var4);
            useProgram(ProgramNone);
            printLogInfo(var4, var0.getName());
            int var8 = GL20.glGetProgrami(var4, 35715);
            if (var8 != 1) {
               String var9 = "\"";
               printChatAndLogError("[Shaders] Error: Invalid program " + var9 + var0.getName() + var9);
               ARBShaderObjects.glDeleteObjectARB(var4);
               boolean var10 = false;
               var0.resetId();
            }
         }
      }
   }

   public static void beginClouds() {
      fogEnabled = true;
      pushEntity(-3, 0);
      useProgram(ProgramClouds);
   }

   public static void method_02361() {
      boolean var0;
      if (!field_0085) {
         field_0085 = true;
         var0 = true;
      } else {
         var0 = false;
      }

      if (!field_0055) {
         checkGLError("Shaders.init pre");
         if (getShaderPackName() != null) {
         }

         if (!capabilities.OpenGL20) {
            printChatAndLogError("No OpenGL 2.0");
         }

         if (!capabilities.GL_EXT_framebuffer_object) {
            printChatAndLogError("No EXT_framebuffer_object");
         }

         ((Buffer)dfbDrawBuffers).position(0).limit(8);
         ((Buffer)dfbColorTextures).position(0).limit(16);
         ((Buffer)dfbDepthTextures).position(0).limit(3);
         ((Buffer)sfbDrawBuffers).position(0).limit(8);
         ((Buffer)sfbDepthTextures).position(0).limit(2);
         ((Buffer)sfbColorTextures).position(0).limit(8);
         usedColorBuffers = 4;
         usedDepthBuffers = 1;
         usedShadowColorBuffers = 0;
         usedShadowDepthBuffers = 0;
         usedColorAttachs = 1;
         field_0225 = 1;
         Arrays.fill(gbuffersFormat, 6408);
         Arrays.fill(field_0136, true);
         Arrays.fill(gbuffersClearColor, null);
         Arrays.fill(field_0217, false);
         Arrays.fill(shadowMipmapEnabled, false);
         Arrays.fill(shadowFilterNearest, false);
         Arrays.fill(shadowColorMipmapEnabled, false);
         Arrays.fill(shadowColorFilterNearest, false);
         centerDepthSmoothEnabled = false;
         noiseTextureEnabled = false;
         sunPathRotation = 0.0F;
         field_0359 = 2.0F;
         shadowMapWidth = 1024;
         shadowMapHeight = 1024;
         spShadowMapWidth = 1024;
         spShadowMapHeight = 1024;
         field_0043 = 90.0F;
         field_0243 = 160.0F;
         field_0173 = true;
         field_0268 = -1.0F;
         field_0079 = -1.0F;
         field_0154 = false;
         field_0204 = false;
         field_0096 = false;
         waterShadowEnabled = false;
         field_0390 = false;
         updateBlockLightLevel();
         Smoother.resetValues();
         shaderUniforms.reset();
         if (customUniforms != null) {
            customUniforms.reset();
         }

         ShaderProfile var1 = ShaderUtils.detectProfile(shaderPackProfiles, shaderPackOptions, false);
         String var2 = "";
         if (currentWorld != null) {
            int var3 = currentWorld.t.getDimensionId();
            if (shaderPackDimensions.contains(var3)) {
               var2 = "world" + var3 + "/";
            }
         }

         for (int var13 = 0; var13 < ProgramsAll.length; var13++) {
            Program var4 = ProgramsAll[var13];
            var4.resetId();
            var4.resetConfiguration();
            if (var4.getProgramStage() != ProgramStage.NONE) {
               String var5 = var4.getName();
               String var6 = var2 + var5;
               boolean var7 = true;
               if (shaderPackProgramConditions.containsKey(var6)) {
                  var7 = var7 && shaderPackProgramConditions.get(var6).eval();
               }

               if (var1 != null) {
                  var7 = var7 && !var1.isProgramDisabled(var6);
               }

               if (!var7) {
                  SMCLog.info("Program disabled: " + var6);
                  var5 = "<disabled>";
                  var6 = var2 + var5;
               }

               String var8 = "/shaders/" + var6;
               String var9 = var8 + ".vsh";
               String var10 = var8 + ".gsh";
               String var11 = var8 + ".fsh";
               setupProgram(var4, var9, var10, var11);
               int var12 = var4.getId();
               if (var12 > 0) {
                  SMCLog.info("Program loaded: " + var6);
               }

               method_02344(var4);
               method_02327(var4);
            }
         }

         field_0348 = false;

         for (int var14 = 0; var14 < field_0304.length; var14++) {
            if (field_0304[var14].getId() != 0) {
               field_0348 = true;
               break;
            }
         }

         usedColorAttachs = usedColorBuffers;
         shadowPassInterval = usedShadowDepthBuffers > 0 ? 1 : 0;
         field_0367 = usedShadowDepthBuffers > 0;
         SMCLog.info("usedColorBuffers: " + usedColorBuffers);
         SMCLog.info("usedDepthBuffers: " + usedDepthBuffers);
         SMCLog.info("usedShadowColorBuffers: " + usedShadowColorBuffers);
         SMCLog.info("usedShadowDepthBuffers: " + usedShadowDepthBuffers);
         SMCLog.info("usedColorAttachs: " + usedColorAttachs);
         SMCLog.info("usedDrawBuffers: " + field_0225);
         ((Buffer)dfbDrawBuffers).position(0).limit(field_0225);
         ((Buffer)dfbColorTextures).position(0).limit(usedColorBuffers * 2);
         dfbColorTexturesFlip.reset();

         for (int var15 = 0; var15 < field_0225; var15++) {
            dfbDrawBuffers.put(var15, 36064 + var15);
         }

         int var16 = GL11.glGetInteger(34852);
         if (field_0225 > var16) {
            printChatAndLogError("[Shaders] Error: Not enough draw buffers, needed: " + field_0225 + ", available: " + var16);
         }

         ((Buffer)sfbDrawBuffers).position(0).limit(usedShadowColorBuffers);

         for (int var17 = 0; var17 < usedShadowColorBuffers; var17++) {
            sfbDrawBuffers.put(var17, 36064 + var17);
         }

         for (int var18 = 0; var18 < ProgramsAll.length; var18++) {
            Program var20 = ProgramsAll[var18];
            Program var21 = var20;

            while (var21.getId() == 0 && var21.getProgramBackup() != var21) {
               var21 = var21.getProgramBackup();
            }

            if (var21 != var20 && var20 != ProgramShadow) {
               var20.copyFrom(var21);
            }
         }

         resize();
         resizeShadow();
         if (noiseTextureEnabled) {
            setupNoiseTexture();
         }

         if (defaultTexture == null) {
            defaultTexture = ShadersTex.createDefaultTexture();
         }

         GlStateManager.pushMatrix();
         GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
         preCelestialRotate();
         postCelestialRotate();
         GlStateManager.popMatrix();
         field_0055 = true;
         method_02314();
         resetDisplayLists();
         if (!var0) {
         }

         checkGLError("Shaders.init");
      }
   }

   public static boolean isRenderShadowTranslucent() {
      return !field_0307.isFalse();
   }

   public static void resourcesReloaded() {
      loadShaderPackResources();
      if (shaderPackLoaded) {
         BlockAliases.resourcesReloaded();
         ItemAliases.resourcesReloaded();
         EntityAliases.resourcesReloaded();
      }
   }

   public static boolean isHandRenderedMain() {
      return isHandRenderedMain;
   }

   public static void setSkyColor(Vec3 var0) {
      skyColorR = (float)var0.xCoord;
      skyColorG = (float)var0.yCoord;
      skyColorB = (float)var0.zCoord;
      setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
   }

   public static void beginFPOverlay() {
      GlStateManager.disableLighting();
      GlStateManager.disableBlend();
   }

   public static boolean isHandRenderedOff() {
      return isHandRenderedOff;
   }

   public static void method_02203(int var0) {
      GL11.glDisable(var0);
      disableFog();
   }

   public static void renderFinal() {
      isRenderingDfb = false;
      mc.getFramebuffer().bindFramebuffer(true);
      OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, 3553, mc.getFramebuffer().framebufferTexture, 0);
      GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
      if (EntityRenderer.anaglyphEnable) {
         boolean var0 = EntityRenderer.anaglyphField != 0;
         GlStateManager.colorMask(var0, !var0, !var0, true);
      }

      GlStateManager.depthMask(true);
      GL11.glClearColor(clearColorR, clearColorG, clearColorB, 1.0F);
      GL11.glClear(16640);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableAlpha();
      GlStateManager.disableBlend();
      GlStateManager.enableDepth();
      GlStateManager.depthFunc(519);
      GlStateManager.depthMask(false);
      checkGLError("pre-final");
      useProgram(ProgramFinal);
      checkGLError("final");
      if (activeCompositeMipmapSetting != 0) {
         genCompositeMipmap();
      }

      drawComposite();
      checkGLError("renderCompositeFinal");
   }

   public static void setViewport(int var0, int var1, int var2, int var3) {
      GlStateManager.colorMask(true, true, true, true);
      if (isShadowPass) {
         GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
      } else {
         GL11.glViewport(0, 0, renderWidth, renderHeight);
         EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
         isRenderingDfb = true;
         GlStateManager.enableCull();
         GlStateManager.enableDepth();
         setDrawBuffers(drawBuffersNone);
         useProgram(ProgramTextured);
         checkGLError("beginRenderPass");
      }
   }

   public static void endLeash() {
      popProgram();
   }

   public static void pushEntity(Block var0) {
      entityDataIndex++;
      int var1 = var0.getRenderType();
      entityData[entityDataIndex * 2] = Block.blockRegistry.getIDForObject(var0) & 65535 | var1 << 16;
      entityData[entityDataIndex * 2 + 1] = 0;
   }

   public static void setProgramUniform1f(ShaderUniform1f var0, float var1) {
      var0.setValue(var1);
   }

   public static void drawCompositeQuad() {
      if (!method_02317()) {
         GL11.glBegin(5);
         GL11.glTexCoord2f(0.0F, 0.0F);
         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex3f(1.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex3f(0.0F, 1.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 1.0F);
         GL11.glVertex3f(1.0F, 1.0F, 0.0F);
         GL11.glEnd();
      } else {
         GL11.glBegin(7);
         GL11.glTexCoord2f(0.0F, 0.0F);
         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex3f(1.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 1.0F);
         GL11.glVertex3f(1.0F, 1.0F, 0.0F);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex3f(0.0F, 1.0F, 0.0F);
         GL11.glEnd();
      }
   }

   public static boolean printLogInfo(int var0, String var1) {
      IntBuffer var2 = BufferUtils.createIntBuffer(1);
      ARBShaderObjects.glGetObjectParameterARB(var0, 35716, var2);
      int var3 = var2.get();
      if (var3 > 1) {
         ByteBuffer var4 = BufferUtils.createByteBuffer(var3);
         ((Buffer)var2).flip();
         ARBShaderObjects.glGetInfoLogARB(var0, var2, var4);
         byte[] var5 = new byte[var3];
         var4.get(var5);
         if (var5[var3 - 1] == 0) {
            var5[var3 - 1] = 10;
         }

         String var6 = new String(var5, Charsets.US_ASCII);
         var6 = StrUtils.trim(var6, " \n\r\t");
         SMCLog.info("Info log: " + var1 + "\n" + var6);
         return false;
      } else {
         return true;
      }
   }

   public static Reader getShaderReader(String var0) {
      return new InputStreamReader(shaderPack.getResourceAsStream(var0));
   }

   public static void bindGbuffersTextures() {
      if (usedShadowDepthBuffers >= 1) {
         GlStateManager.setActiveTexture(33988);
         GlStateManager.bindTexture(sfbDepthTextures.get(0));
         if (usedShadowDepthBuffers >= 2) {
            GlStateManager.setActiveTexture(33989);
            GlStateManager.bindTexture(sfbDepthTextures.get(1));
         }
      }

      GlStateManager.setActiveTexture(33984);

      for (int var0 = 0; var0 < usedColorBuffers; var0++) {
         GlStateManager.bindTexture(dfbColorTexturesFlip.getA(var0));
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10241, 9729);
         GlStateManager.bindTexture(dfbColorTexturesFlip.getB(var0));
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10241, 9729);
      }

      GlStateManager.bindTexture(0);

      for (int var1 = 0; var1 < 4 && 4 + var1 < usedColorBuffers; var1++) {
         GlStateManager.setActiveTexture(33991 + var1);
         GlStateManager.bindTexture(dfbColorTexturesFlip.getA(4 + var1));
      }

      GlStateManager.setActiveTexture(33990);
      GlStateManager.bindTexture(dfbDepthTextures.get(0));
      if (usedDepthBuffers >= 2) {
         GlStateManager.setActiveTexture(33995);
         GlStateManager.bindTexture(dfbDepthTextures.get(1));
         if (usedDepthBuffers >= 3) {
            GlStateManager.setActiveTexture(33996);
            GlStateManager.bindTexture(dfbDepthTextures.get(2));
         }
      }

      for (int var2 = 0; var2 < usedShadowColorBuffers; var2++) {
         GlStateManager.setActiveTexture(33997 + var2);
         GlStateManager.bindTexture(sfbColorTextures.get(var2));
      }

      if (noiseTextureEnabled) {
         GlStateManager.setActiveTexture(33984 + noiseTexture.getTextureUnit());
         GlStateManager.bindTexture(noiseTexture.getTextureId());
      }

      bindCustomTextures(customTexturesGbuffers);
      GlStateManager.setActiveTexture(33984);
   }

   public static void loadConfig() {
      SMCLog.info("Load shaders configuration.");

      try {
         if (!shaderPacksDir.exists()) {
            shaderPacksDir.mkdir();
         }
      } catch (Exception var8) {
         SMCLog.severe("Failed to open the shaderpacks directory: " + shaderPacksDir);
      }

      shadersConfig = new PropertiesOrdered();
      shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), "");
      if (configFile.exists()) {
         try {
            FileReader var0 = new FileReader(configFile);
            shadersConfig.load(var0);
            var0.close();
         } catch (Exception var7) {
         }
      }

      if (!configFile.exists()) {
         try {
            storeConfig();
         } catch (Exception var6) {
         }
      }

      EnumShaderOption[] var9 = EnumShaderOption.values();

      for (int var1 = 0; var1 < var9.length; var1++) {
         EnumShaderOption var2 = var9[var1];
         String var3 = var2.getPropertyKey();
         String var4 = var2.getValueDefault();
         String var5 = shadersConfig.getProperty(var3, var4);
         setEnumShaderOption(var2, var5);
      }

      loadShaderPack();
   }

   public static boolean method_02200() {
      return !field_0019.isFalse();
   }

   public static void postCelestialRotate() {
      FloatBuffer var0 = tempMatrixDirectBuffer;
      ((Buffer)var0).clear();
      GL11.glGetFloat(2982, var0);
      var0.get(tempMat, 0, 16);
      SMath.multiplyMat4xVec4(sunPosition, tempMat, sunPosModelView);
      SMath.multiplyMat4xVec4(moonPosition, tempMat, moonPosModelView);
      System.arraycopy(shadowAngle == sunAngle ? sunPosition : moonPosition, 0, shadowLightPosition, 0, 3);
      setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
      setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
      setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
      if (customUniforms != null) {
         customUniforms.update();
      }

      checkGLError("postCelestialRotate");
   }

   public static boolean isRenderBothHands() {
      return !skipRenderHandMain && !skipRenderHandOff;
   }

   public static void updateBlockLightLevel() {
      if (isOldLighting()) {
         blockLightLevel05 = 0.5F;
         blockLightLevel06 = 0.6F;
         blockLightLevel08 = 0.8F;
      } else {
         blockLightLevel05 = 1.0F;
         blockLightLevel06 = 1.0F;
         blockLightLevel08 = 1.0F;
      }
   }

   public static boolean isCustomUniforms() {
      return customUniforms != null;
   }

   public static void method_02315(int var0) {
      GL11.glEnable(var0);
      enableTexture2D();
   }

   public static void endLivingDamage() {
      if (isRenderingWorld && !isShadowPass) {
         setDrawBuffers(ProgramEntities.getDrawBuffers());
      }
   }

   public static void setFogColor(float var0, float var1, float var2) {
      fogColorR = var0;
      fogColorG = var1;
      fogColorB = var2;
      setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
   }

   public static ShaderOption[] getShaderPackOptions(String var0) {
      ShaderOption[] var1 = (ShaderOption[])shaderPackOptions.clone();
      if (shaderPackGuiScreens == null) {
         if (shaderPackProfiles != null) {
            ShaderOptionProfile var9 = new ShaderOptionProfile(shaderPackProfiles, var1);
            var1 = (ShaderOption[])Config.addObjectToArray(var1, var9, 0);
         }

         return method_02279(var1);
      } else {
         String var2 = var0 != null ? "screen." + var0 : "screen";
         ScreenShaderOptions var3 = shaderPackGuiScreens.get(var2);
         if (var3 == null) {
            return new ShaderOption[0];
         } else {
            ShaderOption[] var4 = var3.getShaderOptions();
            ArrayList var5 = new ArrayList();

            for (int var6 = 0; var6 < var4.length; var6++) {
               ShaderOption var7 = var4[var6];
               if (var7 == null) {
                  var5.add((ShaderOption)null);
               } else if (var7 instanceof UnidentifiedClass1534) {
                  ShaderOption[] var8 = getShaderOptionsRest(shaderPackGuiScreens, var1);
                  var5.addAll(Arrays.asList(var8));
               } else {
                  var5.add(var7);
               }
            }

            return var5.toArray(new ShaderOption[var5.size()]);
         }
      }
   }

   public static boolean isOldHandLight() {
      return !configOldHandLight.isDefault() ? configOldHandLight.isTrue() : (!shaderPackOldHandLight.isDefault() ? shaderPackOldHandLight.isTrue() : true);
   }

   public static boolean isBothHandsRendered() {
      return isHandRenderedMain && isHandRenderedOff;
   }

   public static void saveShader(String var0, String var1) {
      try {
         File var2 = new File(shaderPacksDir, "debug/" + var0);
         var2.getParentFile().mkdirs();
         Config.writeFile(var2, var1);
      } catch (IOException var3) {
         Config.warn("Error saving: " + var0);
         var3.printStackTrace();
      }
   }

   public static int getShaderPackColumns(String var0, int var1) {
      String var2 = var0 != null ? "screen." + var0 : "screen";
      if (shaderPackGuiScreens == null) {
         return var1;
      } else {
         ScreenShaderOptions var3 = shaderPackGuiScreens.get(var2);
         return var3 == null ? var1 : var3.getColumns();
      }
   }

   public static void preSkyList() {
      setUpPosition();
      GL11.glColor3f(fogColorR, fogColorG, fogColorB);
      drawHorizon();
      GL11.glColor3f(skyColorR, skyColorG, skyColorB);
   }

   public static void printChat(String var0) {
      mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(var0));
   }

   public static void glEnableWrapper(int var0) {
      GL11.glEnable(var0);
      if (var0 == 3553) {
         enableTexture2D();
      } else if (var0 == 2912) {
         enableFog();
      }
   }

   public static void method_02327(Program var0) {
      boolean[] var1 = var0.getToggleColorTextures();
      Boolean[] var2 = var0.getBuffersFlip();

      for (int var3 = 0; var3 < var2.length; var3++) {
         Boolean var4 = var2[var3];
         if (var4 != null) {
            var1[var3] = var4;
         }
      }
   }

   public static void method_02193() {
      if (isRenderingWorld) {
         useProgram(field_0050);
      }
   }

   public static void setRenderingFirstPersonHand(boolean var0) {
      isRenderingFirstPersonHand = var0;
   }

   public static void nextEntity(Entity var0) {
      if (isRenderingWorld) {
         useProgram(ProgramEntities);
         setEntityId(var0);
      }
   }

   public static ICustomTexture[] loadCustomTextures(Properties var0, int var1) {
      String var2 = "texture." + field_0189[var1] + ".";
      Set var3 = var0.keySet();
      ArrayList var4 = new ArrayList();

      for (Object var6 : var3) {
         String var7 = (String)var6;
         if (var7.startsWith(var2)) {
            String var8 = StrUtils.removePrefix(var7, var2);
            var8 = StrUtils.removeSuffix(var8, new String[]{".0", ".1", ".2", ".3", ".4", ".5", ".6", ".7", ".8", ".9"});
            String var9 = var0.getProperty(var7).trim();
            int var10 = getTextureIndex(var1, var8);
            if (var10 < 0) {
               SMCLog.warning("Invalid texture name: " + var7);
            } else {
               ICustomTexture var11 = loadCustomTexture(var10, var9);
               if (var11 != null) {
                  SMCLog.info("Custom texture: " + var7 + " = " + var9);
                  var4.add(var11);
               }
            }
         }
      }

      return var4.size() <= 0 ? null : var4.toArray(new ICustomTexture[var4.size()]);
   }

   public static void setUpPosition() {
      FloatBuffer var0 = tempMatrixDirectBuffer;
      ((Buffer)var0).clear();
      GL11.glGetFloat(2982, var0);
      var0.get(tempMat, 0, 16);
      SMath.multiplyMat4xVec4(upPosition, tempMat, upPosModelView);
      setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
      if (customUniforms != null) {
         customUniforms.update();
      }
   }

   public static void endWater() {
      if (isRenderingWorld) {
         if (isShadowPass) {
         }

         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      }
   }

   public static ShaderOption[] getShaderPackOptions() {
      return shaderPackOptions;
   }

   public static boolean isProgramPath(String var0) {
      if (var0 == null) {
         return false;
      } else if (var0.length() <= 0) {
         return false;
      } else {
         int var1 = var0.lastIndexOf("/");
         if (var1 >= 0) {
            var0 = var0.substring(var1 + 1);
         }

         Program var2 = getProgram(var0);
         return var2 != null;
      }
   }

   public static void setSkipRenderHands(boolean var0, boolean var1) {
      skipRenderHandMain = var0;
      skipRenderHandOff = var1;
   }

   public static void endSpiderEyes() {
      if (isRenderingWorld && field_0328.getId() != ProgramNone.getId()) {
         useProgram(ProgramEntities);
         GlStateManager.disableAlpha();
      }
   }

   public static void resize() {
      renderDisplayWidth = mc.displayWidth;
      renderDisplayHeight = mc.displayHeight;
      renderWidth = Math.round(renderDisplayWidth * configRenderResMul);
      renderHeight = Math.round(renderDisplayHeight * configRenderResMul);
      setupFrameBuffer();
   }

   public static void method_02206() {
      if (isRenderingWorld) {
         checkGLError("endBlockEntities");
         setBlockEntityId((TileEntity)null);
         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
         ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
      }
   }

   public static void disableFog() {
      fogEnabled = false;
      setProgramUniform1i(uniform_fogMode, 0);
   }

   public static void beginEntitiesGlowing() {
      if (isRenderingWorld) {
         isEntitiesGlowing = true;
      }
   }

   public static void method_02199() {
      if (isRenderingWorld) {
         setEntityId((Entity)null);
         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      }
   }

   public static void loadShaderPack() {
      boolean var0 = shaderPackLoaded;
      boolean var1 = isOldLighting();
      if (mc.renderGlobal != null) {
         mc.renderGlobal.pauseChunkUpdates();
      }

      shaderPackLoaded = false;
      if (shaderPack != null) {
         shaderPack.close();
         shaderPack = null;
         shaderPackResources.clear();
         shaderPackDimensions.clear();
         shaderPackOptions = null;
         shaderPackOptionSliders = null;
         shaderPackProfiles = null;
         shaderPackGuiScreens = null;
         shaderPackProgramConditions.clear();
         shaderPackClouds.resetValue();
         shaderPackOldHandLight.resetValue();
         shaderPackDynamicHandLight.resetValue();
         shaderPackOldLighting.resetValue();
         resetCustomTextures();
         noiseTexturePath = null;
      }

      boolean var2 = false;
      if (Config.isAntialiasing()) {
         SMCLog.info("Shaders can not be loaded, Antialiasing is enabled: " + Config.getAntialiasingLevel() + "x");
         var2 = true;
      }

      if (Config.isAnisotropicFiltering()) {
         SMCLog.info("Shaders can not be loaded, Anisotropic Filtering is enabled: " + Config.getAnisotropicFilterLevel() + "x");
         var2 = true;
      }

      if (Config.isFastRender()) {
         SMCLog.info("Shaders can not be loaded, Fast Render is enabled.");
         var2 = true;
      }

      String var3 = shadersConfig.getProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), "(internal)");
      if (!var2) {
         shaderPack = getShaderPack(var3);
         shaderPackLoaded = shaderPack != null;
      }

      if (shaderPackLoaded) {
         SMCLog.info("Loaded shaderpack: " + getShaderPackName());
      } else {
         SMCLog.info("No shaderpack loaded.");
         shaderPack = new ShaderPackNone();
      }

      if (saveFinalShaders) {
         clearDirectory(new File(shaderPacksDir, "debug"));
      }

      loadShaderPackResources();
      loadShaderPackDimensions();
      shaderPackOptions = loadShaderPackOptions();
      loadShaderPackProperties();
      boolean var4 = shaderPackLoaded != var0;
      boolean var5 = isOldLighting() != var1;
      if (var4 || var5) {
         DefaultVertexFormats.updateVertexFormats();
         if (Reflector.LightUtil.exists()) {
            Reflector.LightUtil_itemConsumer.setValue(null);
            Reflector.LightUtil_tessellator.setValue(null);
         }

         updateBlockLightLevel();
      }

      if (mc.getResourcePackRepository() != null) {
         CustomBlockLayers.update();
      }

      if (mc.renderGlobal != null) {
         mc.renderGlobal.resumeChunkUpdates();
      }

      if ((var4 || var5) && mc.getResourceManager() != null) {
         mc.scheduleResourcesRefresh();
      }
   }

   public static IShaderPack getShaderPack() {
      return shaderPack;
   }

   public static boolean method_02330() {
      return !field_0238.isFalse();
   }

   public static void endUpdateChunks() {
      checkGLError("endUpdateChunks1");
      checkFramebufferStatus("endUpdateChunks1");
      if (!isShadowPass) {
         useProgram(ProgramTerrain);
      }

      checkGLError("endUpdateChunks2");
      checkFramebufferStatus("endUpdateChunks2");
   }

   public static void loadShaderPackProperties() {
      shaderPackClouds.resetValue();
      shaderPackOldHandLight.resetValue();
      shaderPackDynamicHandLight.resetValue();
      shaderPackOldLighting.resetValue();
      field_0307.resetValue();
      field_0032.resetValue();
      field_0019.resetValue();
      field_0156.resetValue();
      field_0238.resetValue();
      shaderPackBackFaceSolid.resetValue();
      shaderPackBackFaceCutout.resetValue();
      shaderPackBackFaceCutoutMipped.resetValue();
      shaderPackBackFaceTranslucent.resetValue();
      field_0351.resetValue();
      field_0186.resetValue();
      field_0340.resetValue();
      field_0072.resetValue();
      BlockAliases.method_07078();
      ItemAliases.method_26379();
      EntityAliases.method_22569();
      customUniforms = null;

      for (int var0 = 0; var0 < ProgramsAll.length; var0++) {
         Program var1 = ProgramsAll[var0];
         var1.resetProperties();
      }

      if (shaderPack != null) {
         BlockAliases.update(shaderPack);
         ItemAliases.update(shaderPack);
         EntityAliases.update(shaderPack);
         String var4 = "/shaders/shaders.properties";

         try {
            InputStream var5 = shaderPack.getResourceAsStream(var4);
            if (var5 == null) {
               return;
            }

            var5 = MacroProcessor.process(var5, var4);
            PropertiesOrdered var2 = new PropertiesOrdered();
            var2.load(var5);
            var5.close();
            shaderPackClouds.loadFrom(var2);
            shaderPackOldHandLight.loadFrom(var2);
            shaderPackDynamicHandLight.loadFrom(var2);
            shaderPackOldLighting.loadFrom(var2);
            field_0307.loadFrom(var2);
            field_0032.loadFrom(var2);
            field_0019.loadFrom(var2);
            field_0238.loadFrom(var2);
            field_0156.loadFrom(var2);
            shaderPackBackFaceSolid.loadFrom(var2);
            shaderPackBackFaceCutout.loadFrom(var2);
            shaderPackBackFaceCutoutMipped.loadFrom(var2);
            shaderPackBackFaceTranslucent.loadFrom(var2);
            field_0351.loadFrom(var2);
            field_0186.loadFrom(var2);
            field_0340.loadFrom(var2);
            field_0072.loadFrom(var2);
            shaderPackOptionSliders = ShaderPackParser.parseOptionSliders(var2, shaderPackOptions);
            shaderPackProfiles = ShaderPackParser.parseProfiles(var2, shaderPackOptions);
            shaderPackGuiScreens = ShaderPackParser.parseGuiScreens(var2, shaderPackProfiles, shaderPackOptions);
            shaderPackProgramConditions = ShaderPackParser.parseProgramConditions(var2, shaderPackOptions);
            customTexturesGbuffers = loadCustomTextures(var2, 0);
            customTexturesComposite = loadCustomTextures(var2, 1);
            customTexturesDeferred = loadCustomTextures(var2, 2);
            noiseTexturePath = var2.getProperty("texture.noise");
            if (noiseTexturePath != null) {
               noiseTextureEnabled = true;
            }

            customUniforms = ShaderPackParser.parseCustomUniforms(var2);
            ShaderPackParser.method_28419(var2);
            ShaderPackParser.method_28407(var2);
            ShaderPackParser.method_28395(var2);
            ShaderPackParser.method_28392(var2);
         } catch (IOException var3) {
            Config.warn("[Shaders] Error reading: " + var4);
         }
      }
   }

   public static boolean method_02321() {
      return !field_0156.isFalse();
   }
}
