package net.minecraft.client.renderer.tileentity;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.DamageTintModule;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import recovered.unidentified.UnidentifiedClass4343;

public class TileEntitySkullRenderer extends TileEntitySpecialRenderer<TileEntitySkull> {
   public DamageTintModule field_0003;
   public static ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");
   public static ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   public static TileEntitySkullRenderer instance;
   public ModelSkeletonHead humanoidHead;
   public static ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");
   public static ResourceLocation WITHER_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
   public ModelSkeletonHead skeletonHead = new ModelSkeletonHead(0, 0, 64, 32);

   public void renderTileEntityAt(TileEntitySkull var1, double var2, double var4, double var6, float var8, int var9) {
      if (!CheatBreaker.getInstance().getGlobalSettings().field_0046.method_08908()) {
         EnumFacing var10 = EnumFacing.getFront(var1.u() & 7);
         this.renderSkull(
            (float)var2, (float)var4, (float)var6, var10, var1.getSkullRotation() * 360 / 16.0F, var1.getSkullType(), var1.getPlayerProfile(), var9
         );
      }
   }

   public TileEntitySkullRenderer() {
      this.humanoidHead = new ModelHumanoidHead();
   }

   @Override
   public void setRendererDispatcher(TileEntityRendererDispatcher var1) {
      super.setRendererDispatcher(var1);
      instance = this;
   }

   public void renderSkull(float var1, float var2, float var3, EnumFacing var4, float var5, int var6, GameProfile var7, int var8) {
      ModelSkeletonHead var9 = this.skeletonHead;
      if (var8 >= 0) {
         this.bindTexture(a[var8]);
         GlStateManager.matrixMode(5890);
         GlStateManager.pushMatrix();
         GlStateManager.scale(4.0F, 2.0F, 1.0F);
         GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.matrixMode(5888);
      } else {
         switch (var6) {
            case 0:
            default:
               this.bindTexture(SKELETON_TEXTURES);
               break;
            case 1:
               this.bindTexture(WITHER_SKELETON_TEXTURES);
               break;
            case 2:
               this.bindTexture(ZOMBIE_TEXTURES);
               var9 = this.humanoidHead;
               break;
            case 3:
               var9 = this.humanoidHead;
               ResourceLocation var10 = DefaultPlayerSkin.getDefaultSkinLegacy();
               if (var7 != null) {
                  Minecraft var11 = Minecraft.getMinecraft();
                  Map var12 = var11.getSkinManager().loadSkinFromCache(var7);
                  if (var12.containsKey(Type.SKIN)) {
                     var10 = var11.getSkinManager().loadSkin((MinecraftProfileTexture)var12.get(Type.SKIN), Type.SKIN);
                  } else {
                     UUID var13 = EntityPlayer.getUUID(var7);
                     var10 = DefaultPlayerSkin.getDefaultSkin(var13);
                  }
               }

               this.bindTexture(var10);
               break;
            case 4:
               this.bindTexture(CREEPER_TEXTURES);
         }
      }

      GlStateManager.pushMatrix();
      GlStateManager.disableCull();
      if (var4 != EnumFacing.UP) {
         switch (UnidentifiedClass4343.field_0003[var4.ordinal()]) {
            case 1:
               GlStateManager.translate(var1 + 0.5F, var2 + 0.25F, var3 + 0.74F);
               break;
            case 2:
               GlStateManager.translate(var1 + 0.5F, var2 + 0.25F, var3 + 0.26F);
               var5 = 180.0F;
               break;
            case 3:
               GlStateManager.translate(var1 + 0.74F, var2 + 0.25F, var3 + 0.5F);
               var5 = 270.0F;
               break;
            case 4:
            default:
               GlStateManager.translate(var1 + 0.26F, var2 + 0.25F, var3 + 0.5F);
               var5 = 90.0F;
         }
      } else {
         GlStateManager.translate(var1 + 0.5F, var2, var3 + 0.5F);
      }

      float var14 = 0.0625F;
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      GlStateManager.enableAlpha();
      var9.render((Entity)null, 0.0F, 0.0F, 0.0F, var5, 0.0F, var14);
      GlStateManager.popMatrix();
      if (var8 >= 0) {
         GlStateManager.matrixMode(5890);
         GlStateManager.popMatrix();
         GlStateManager.matrixMode(5888);
      }
   }
}
