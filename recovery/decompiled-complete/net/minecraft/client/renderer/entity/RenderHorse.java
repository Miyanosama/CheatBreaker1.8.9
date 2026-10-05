package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import io.netty.channel.ThreadPerChannelEventLoopGroup;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World$1;
import org.apache.log4j.chainsaw.Main$1;

public class RenderHorse extends RenderLiving<EntityHorse> {
   public Main$1 field_0002;
   public ThreadPerChannelEventLoopGroup field_0007;
   public static ResourceLocation zombieHorseTextures = new ResourceLocation("textures/entity/horse/horse_zombie.png");
   public static ResourceLocation whiteHorseTextures = new ResourceLocation("textures/entity/horse/horse_white.png");
   public static ResourceLocation donkeyTextures = new ResourceLocation("textures/entity/horse/mule.png");
   public static Map<String, ResourceLocation> field_110852_a = Maps.newHashMap();
   public static ResourceLocation skeletonHorseTextures = new ResourceLocation("textures/entity/horse/horse_skeleton.png");
   public World$1 field_0005;
   public static ResourceLocation muleTextures = new ResourceLocation("textures/entity/horse/donkey.png");

   public ResourceLocation getEntityTexture(EntityHorse var1) {
      if (!var1.func_110239_cn()) {
         switch (var1.getHorseType()) {
            case 0:
            default:
               return whiteHorseTextures;
            case 1:
               return muleTextures;
            case 2:
               return donkeyTextures;
            case 3:
               return zombieHorseTextures;
            case 4:
               return skeletonHorseTextures;
         }
      } else {
         return this.func_110848_b(var1);
      }
   }

   public ResourceLocation func_110848_b(EntityHorse var1) {
      String var2 = var1.getHorseTexture();
      if (!var1.func_175507_cI()) {
         return null;
      } else {
         ResourceLocation var3 = field_110852_a.get(var2);
         if (var3 == null) {
            var3 = new ResourceLocation(var2);
            Minecraft.getMinecraft().getTextureManager().loadTexture(var3, new LayeredTexture(var1.getVariantTexturePaths()));
            field_110852_a.put(var2, var3);
         }

         return var3;
      }
   }

   public RenderHorse(RenderManager var1, ModelHorse var2, float var3) {
      super(var1, var2, var3);
   }

   public void preRenderCallback(EntityHorse var1, float var2) {
      float var3 = 1.0F;
      int var4 = var1.getHorseType();
      if (var4 == 1) {
         var3 *= 0.87F;
      } else if (var4 == 2) {
         var3 *= 0.92F;
      }

      GlStateManager.scale(var3, var3, var3);
      super.preRenderCallback(var1, var2);
   }
}
