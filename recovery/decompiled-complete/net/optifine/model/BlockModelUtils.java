package net.optifine.model;

import com.cheatbreaker.client.websocket.server.WSPacketBulkFriends;
import io.netty.handler.ssl.JettyNpnSslEngine$1;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachTransformedKeyTask;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block$EnumOffsetType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.src.Config;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.util.vector.Vector3f;

public class BlockModelUtils {
   public static float field_0002;
   public WSPacketBulkFriends field_0004;
   public ContainerEnchantment field_0001;
   public ConcurrentHashMapV8$ForEachTransformedKeyTask field_0003;
   public JettyNpnSslEngine$1 field_0000;

   public static BakedQuad makeBakedQuad(EnumFacing var0, TextureAtlasSprite var1, int var2) {
      Vector3f var3 = new Vector3f(0.0F, 0.0F, 0.0F);
      Vector3f var4 = new Vector3f(16.0F, 16.0F, 16.0F);
      BlockFaceUV var5 = new BlockFaceUV(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0);
      BlockPartFace var6 = new BlockPartFace(var0, var2, "#" + var0.getName(), var5);
      ModelRotation var7 = ModelRotation.X0_Y0;
      Object var8 = null;
      boolean var9 = false;
      boolean var10 = true;
      FaceBakery var11 = new FaceBakery();
      return var11.makeBakedQuad(var3, var4, var6, var1, var0, var7, (BlockPartRotation)var8, var9, var10);
   }

   public static IBakedModel makeModel(String var0, String var1, String var2) {
      TextureMap var3 = Config.getMinecraft().getTextureMapBlocks();
      TextureAtlasSprite var4 = var3.getSpriteSafe(var1);
      TextureAtlasSprite var5 = var3.getSpriteSafe(var2);
      return makeModel(var0, var4, var5);
   }

   public static IBakedModel joinModelsCube(IBakedModel var0, IBakedModel var1) {
      ArrayList var2 = new ArrayList();
      var2.addAll(var0.getGeneralQuads());
      var2.addAll(var1.getGeneralQuads());
      EnumFacing[] var3 = EnumFacing.VALUES;
      ArrayList var4 = new ArrayList();

      for (int var5 = 0; var5 < var3.length; var5++) {
         EnumFacing var6 = var3[var5];
         ArrayList var7 = new ArrayList();
         var7.addAll(var0.getFaceQuads(var6));
         var7.addAll(var1.getFaceQuads(var6));
         var4.add(var7);
      }

      boolean var10 = var0.isAmbientOcclusion();
      boolean var11 = var0.isBuiltInRenderer();
      TextureAtlasSprite var12 = var0.getParticleTexture();
      ItemCameraTransforms var8 = var0.getItemCameraTransforms();
      return new SimpleBakedModel(var2, var4, var10, var11, var12, var8);
   }

   public static AxisAlignedBB getOffsetBoundingBox(AxisAlignedBB var0, Block$EnumOffsetType var1, BlockPos var2) {
      int var3 = var2.getX();
      int var4 = var2.getZ();
      long var5 = var3 * 3129871 ^ var4 * (-322994077417340939L & 322994077265035255L);
      var5 = var5 * var5 * (1522924837L & 59095613L) + var5 * (-961713134739797813L & 961713134177362971L);
      double var7 = ((float)(var5 >> 16 & 8522511L & -5460367548639338353L) / 15.0F - 0.5) * 0.5;
      double var9 = ((float)(var5 >> 24 & 7679155496104755471L & 184597167L) / 15.0F - 0.5) * 0.5;
      double var11 = 0.0;
      if (var1 == Block$EnumOffsetType.XYZ) {
         var11 = ((float)(var5 >> 20 & 1602357775L & 5629538110001996879L) / 15.0F - 1.0) * 0.2;
      }

      return var0.offset(var7, var11, var9);
   }

   public static float snapVertexCoord(float var0) {
      return var0 > -1.0E-6F && var0 < 1.0E-6F ? 0.0F : (var0 > 0.999999F && var0 < 1.000001F ? 1.0F : var0);
   }

   public static IBakedModel makeModelCube(String var0, int var1) {
      TextureAtlasSprite var2 = Config.getMinecraft().getTextureMapBlocks().getAtlasSprite(var0);
      return makeModelCube(var2, var1);
   }

   public static IBakedModel makeModel(String var0, TextureAtlasSprite var1, TextureAtlasSprite var2) {
      if (var1 != null && var2 != null) {
         ModelManager var3 = Config.getModelManager();
         if (var3 == null) {
            return null;
         } else {
            ModelResourceLocation var4 = new ModelResourceLocation(var0, "normal");
            IBakedModel var5 = var3.getModel(var4);
            if (var5 != null && var5 != var3.getMissingModel()) {
               IBakedModel var6 = ModelUtils.duplicateModel(var5);
               EnumFacing[] var7 = EnumFacing.VALUES;

               for (int var8 = 0; var8 < var7.length; var8++) {
                  EnumFacing var9 = var7[var8];
                  List var10 = var6.getFaceQuads(var9);
                  replaceTexture(var10, var1, var2);
               }

               List var11 = var6.getGeneralQuads();
               replaceTexture(var11, var1, var2);
               return var6;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public static IBakedModel makeModelCube(TextureAtlasSprite var0, int var1) {
      ArrayList var2 = new ArrayList();
      EnumFacing[] var3 = EnumFacing.VALUES;
      ArrayList var4 = new ArrayList();

      for (int var5 = 0; var5 < var3.length; var5++) {
         EnumFacing var6 = var3[var5];
         ArrayList var7 = new ArrayList();
         var7.add(makeBakedQuad(var6, var0, var1));
         var4.add(var7);
      }

      return new SimpleBakedModel(var2, var4, true, true, var0, ItemCameraTransforms.DEFAULT);
   }

   public static void snapVertexPosition(Vector3f var0) {
      var0.setX(snapVertexCoord(var0.getX()));
      var0.setY(snapVertexCoord(var0.getY()));
      var0.setZ(snapVertexCoord(var0.getZ()));
   }

   public static void replaceTexture(List<BakedQuad> var0, TextureAtlasSprite var1, TextureAtlasSprite var2) {
      ArrayList var3 = new ArrayList();

      for (Object var5 : var0) {
         if (((BakedQuad)var5).getSprite() == var1) {
            var5 = new BreakingFour((BakedQuad)var5, var2);
         }

         var3.add(var5);
      }

      var0.clear();
      var0.addAll(var3);
   }
}
