package net.minecraft.client.resources;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import recovered.unidentified.UnidentifiedClass1675;

public class SkinManager {
   public MinecraftSessionService sessionService;
   public File skinCacheDir;
   public TextureManager textureManager;
   public static ExecutorService THREAD_POOL = new ThreadPoolExecutor(0, 2, -4311959031657704959L & 269485097L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
   public LoadingCache<GameProfile, Map<Type, MinecraftProfileTexture>> skinCacheLoader;
   public UnidentifiedClass1675 field_0001;

   public Map<Type, MinecraftProfileTexture> loadSkinFromCache(GameProfile var1) {
      return (Map<Type, MinecraftProfileTexture>)this.skinCacheLoader.getUnchecked(var1);
   }

   public SkinManager(TextureManager var1, File var2, MinecraftSessionService var3) {
      this.textureManager = var1;
      this.skinCacheDir = var2;
      this.sessionService = var3;
      this.skinCacheLoader = CacheBuilder.newBuilder()
         .expireAfterAccess(-5790089148984966641L & 5790089147658282015L, TimeUnit.SECONDS)
         .build(new SkinManager$1(this));
   }

   public ResourceLocation loadSkin(MinecraftProfileTexture var1, Type var2, SkinManager$SkinAvailableCallback var3) {
      ResourceLocation var4 = new ResourceLocation("skins/" + var1.getHash());
      ITextureObject var5 = this.textureManager.getTexture(var4);
      if (var5 != null) {
         if (var3 != null) {
            var3.skinAvailable(var2, var4, var1);
         }
      } else {
         File var6 = new File(this.skinCacheDir, var1.getHash().length() > 2 ? var1.getHash().substring(0, 2) : "xx");
         File var7 = new File(var6, var1.getHash());
         ImageBufferDownload var8 = var2 == Type.SKIN ? new ImageBufferDownload() : null;
         ThreadDownloadImageData var9 = new ThreadDownloadImageData(
            var7, var1.getUrl(), DefaultPlayerSkin.getDefaultSkinLegacy(), new SkinManager$2(this, var8, var3, var2, var4, var1)
         );
         this.textureManager.loadTexture(var4, var9);
      }

      return var4;
   }

   public ResourceLocation loadSkin(MinecraftProfileTexture var1, Type var2) {
      return this.loadSkin(var1, var2, (SkinManager$SkinAvailableCallback)null);
   }

   public void loadProfileTextures(GameProfile var1, SkinManager$SkinAvailableCallback var2, boolean var3) {
      THREAD_POOL.submit(new SkinManager$3(this, var1, var3, var2));
   }
}
