package net.minecraft.client.resources;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class SkinManager {
   public MinecraftSessionService sessionService;
   public File skinCacheDir;
   public TextureManager textureManager;
   public static ExecutorService THREAD_POOL = new ThreadPoolExecutor(0, 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
   public LoadingCache<GameProfile, Map<Type, MinecraftProfileTexture>> skinCacheLoader;

   public Map<Type, MinecraftProfileTexture> loadSkinFromCache(GameProfile var1) {
      return this.skinCacheLoader.getUnchecked(var1);
   }

   public SkinManager(TextureManager var1, File var2, MinecraftSessionService var3) {
      this.textureManager = var1;
      this.skinCacheDir = var2;
      this.sessionService = var3;
      this.skinCacheLoader = CacheBuilder.newBuilder()
         .expireAfterAccess(15L, TimeUnit.SECONDS)
         .build(new CacheLoader<GameProfile, Map<Type, MinecraftProfileTexture>>() {
            public Map<Type, MinecraftProfileTexture> load(GameProfile var1) throws java.lang.Exception {
               return Minecraft.getMinecraft().getSessionService().getTextures(var1, false);
            }
         });
   }

   public ResourceLocation loadSkin(final MinecraftProfileTexture var1, final Type var2, final SkinManager.SkinAvailableCallback var3) {
      final ResourceLocation var4 = new ResourceLocation("skins/" + var1.getHash());
      ITextureObject var5 = this.textureManager.getTexture(var4);
      if (var5 != null) {
         if (var3 != null) {
            var3.skinAvailable(var2, var4, var1);
         }
      } else {
         File var6 = new File(this.skinCacheDir, var1.getHash().length() > 2 ? var1.getHash().substring(0, 2) : "xx");
         File var7 = new File(var6, var1.getHash());
         final ImageBufferDownload var8 = var2 == Type.SKIN ? new ImageBufferDownload() : null;
         ThreadDownloadImageData var9 = new ThreadDownloadImageData(var7, var1.getUrl(), DefaultPlayerSkin.getDefaultSkinLegacy(), new IImageBuffer() {
            @Override
            public BufferedImage parseUserSkin(BufferedImage var1x) {
               if (var8 != null) {
                  var1x = var8.parseUserSkin(var1x);
               }

               return var1x;
            }

            @Override
            public void skinAvailable() {
               if (var8 != null) {
                  var8.skinAvailable();
               }

               if (var3 != null) {
                  var3.skinAvailable(var2, var4, var1);
               }
            }
         });
         this.textureManager.loadTexture(var4, var9);
      }

      return var4;
   }

   public ResourceLocation loadSkin(MinecraftProfileTexture var1, Type var2) {
      return this.loadSkin(var1, var2, (SkinManager.SkinAvailableCallback)null);
   }

   public void loadProfileTextures(final GameProfile var1, final SkinManager.SkinAvailableCallback var2, final boolean var3) {
      THREAD_POOL.submit(new Runnable() {
         @Override
         public void run() {
            final HashMap var1x = Maps.newHashMap();

            try {
               var1x.putAll(SkinManager.this.sessionService.getTextures(var1, var3));
            } catch (InsecureTextureException var3x) {
            }

            if (var1x.isEmpty() && var1.getId().equals(Minecraft.getMinecraft().getSession().getProfile().getId())) {
               var1.getProperties().clear();
               var1.getProperties().putAll(Minecraft.getMinecraft().getProfileProperties());
               var1x.putAll(SkinManager.this.sessionService.getTextures(var1, false));
            }

            Minecraft.getMinecraft().addScheduledTask(new Runnable() {
               @Override
               public void run() {
                  if (var1x.containsKey(Type.SKIN)) {
                     SkinManager.this.loadSkin((MinecraftProfileTexture)var1x.get(Type.SKIN), Type.SKIN, var2);
                  }

                  if (var1x.containsKey(Type.CAPE)) {
                     SkinManager.this.loadSkin((MinecraftProfileTexture)var1x.get(Type.CAPE), Type.CAPE, var2);
                  }
               }
            });
         }
      });
   }

   public interface SkinAvailableCallback {
      void skinAvailable(Type var1, ResourceLocation var2, MinecraftProfileTexture var3);
   }
}
