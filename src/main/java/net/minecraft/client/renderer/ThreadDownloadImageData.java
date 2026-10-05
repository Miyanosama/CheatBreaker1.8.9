package net.minecraft.client.renderer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.Proxy.Type;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.http.HttpPipeline;
import net.optifine.http.HttpRequest;
import net.optifine.http.HttpResponse;
import net.optifine.player.CapeImageBuffer;
import net.optifine.shaders.ShadersTex;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadDownloadImageData extends SimpleTexture {
   public IImageBuffer imageBuffer;
   public static Logger logger = LogManager.getLogger();
   public File cacheFile;
   public BufferedImage bufferedImage;
   public static AtomicInteger threadDownloadCounter = new AtomicInteger(0);
   public Thread imageThread;
   public static Runtime recoveredField398 = Runtime.getRuntime();
   public boolean pipeline;
   public Boolean imageFound = null;
   public String imageUrl;
   public boolean textureUploaded;

   @Override
   public void loadTexture(IResourceManager var1) throws java.io.IOException {
      if (this.bufferedImage == null && this.f != null) {
         super.loadTexture(var1);
      }

      if (this.imageThread == null) {
         if (this.cacheFile != null && this.cacheFile.isFile()) {
            logger.debug("Loading http texture from local cache ({})", this.cacheFile);

            try {
               this.bufferedImage = ImageIO.read(this.cacheFile);
               if (this.imageBuffer != null) {
                  this.setBufferedImage(this.imageBuffer.parseUserSkin(this.bufferedImage));
               }

               this.loadingFinished();
            } catch (IOException var3) {
               logger.error("Couldn't load skin " + this.cacheFile, var3);
               this.loadTextureFromServer();
            }
         } else {
            this.loadTextureFromServer();
         }
      }
   }

   public void loadingFinished() {
      this.imageFound = this.bufferedImage != null;
      if (this.imageBuffer instanceof CapeImageBuffer) {
         CapeImageBuffer var1 = (CapeImageBuffer)this.imageBuffer;
         var1.cleanup();
      }
   }

   @Override
   public int getGlTextureId() {
      this.checkTextureUploaded();
      return super.getGlTextureId();
   }

   public void checkTextureUploaded() {
      if (!this.textureUploaded && this.bufferedImage != null) {
         this.textureUploaded = true;
         if (this.f != null) {
            this.deleteGlTexture();
         }

         if (Config.isShaders()) {
            ShadersTex.loadSimpleTexture(super.getGlTextureId(), this.bufferedImage, false, false, Config.getResourceManager(), this.f, this.getMultiTexID());
         } else {
            TextureUtil.uploadTextureImage(super.getGlTextureId(), this.bufferedImage);
         }
      }
   }

   public void setBufferedImage(BufferedImage var1) {
      this.bufferedImage = var1;
      if (this.imageBuffer != null) {
         this.imageBuffer.skinAvailable();
      }

      this.imageFound = this.bufferedImage != null;
   }

   public IImageBuffer getImageBuffer() {
      return this.imageBuffer;
   }

   public boolean shouldPipeline() {
      if (!this.pipeline) {
         return false;
      } else {
         Proxy var1 = Minecraft.getMinecraft().getProxy();
         return var1.type() != Type.DIRECT && var1.type() != Type.SOCKS ? false : this.imageUrl.startsWith("http://");
      }
   }

   public ThreadDownloadImageData(File var1, String var2, ResourceLocation var3, IImageBuffer var4) {
      super(var3);
      this.pipeline = false;
      this.cacheFile = var1;
      this.imageUrl = var2;
      this.imageBuffer = var4;
   }

   public void loadTextureFromServer() {
      this.imageThread = new Thread("Texture Downloader #" + threadDownloadCounter.incrementAndGet()) {
         @Override
         public void run() {
            HttpURLConnection var1 = null;
            ThreadDownloadImageData.logger
               .debug("Downloading http texture from {} to {}", ThreadDownloadImageData.this.imageUrl, ThreadDownloadImageData.this.cacheFile);
            if (ThreadDownloadImageData.this.shouldPipeline()) {
               ThreadDownloadImageData.this.loadPipelined();
            } else {
               try {
                  var1 = (HttpURLConnection)new URL(ThreadDownloadImageData.this.imageUrl).openConnection(Minecraft.getMinecraft().getProxy());
                  var1.setDoInput(true);
                  var1.setDoOutput(false);
                  var1.connect();
                  if (var1.getResponseCode() / 100 == 2) {
                     BufferedImage var2;
                     if (ThreadDownloadImageData.this.cacheFile != null) {
                        FileUtils.copyInputStreamToFile(var1.getInputStream(), ThreadDownloadImageData.this.cacheFile);
                        var2 = ImageIO.read(ThreadDownloadImageData.this.cacheFile);
                     } else {
                        var2 = TextureUtil.readBufferedImage(var1.getInputStream());
                     }

                     if (ThreadDownloadImageData.this.imageBuffer != null) {
                        var2 = ThreadDownloadImageData.this.imageBuffer.parseUserSkin(var2);
                     }

                     ThreadDownloadImageData.this.setBufferedImage(var2);
                     return;
                  }

                  if (var1.getErrorStream() != null) {
                     Config.readAll(var1.getErrorStream());
                  }

                  return;
               } catch (Exception var6) {
                  ThreadDownloadImageData.logger.error("Couldn't download http texture: " + var6.getClass().getName() + ": " + var6.getMessage());
               } finally {
                  if (var1 != null) {
                     var1.disconnect();
                  }

                  ThreadDownloadImageData.this.loadingFinished();
               }
            }
         }
      };
      this.imageThread.setDaemon(true);
      this.imageThread.start();
   }

   public void loadPipelined() {
      try {
         HttpRequest var1 = HttpPipeline.makeRequest(this.imageUrl, Minecraft.getMinecraft().getProxy());
         HttpResponse var2 = HttpPipeline.executeRequest(var1);
         if (var2.getStatus() / 100 == 2) {
            byte[] var3 = var2.getBody();
            ByteArrayInputStream var4 = new ByteArrayInputStream(var3);
            BufferedImage var5;
            if (this.cacheFile != null) {
               FileUtils.copyInputStreamToFile(var4, this.cacheFile);
               var5 = ImageIO.read(this.cacheFile);
            } else {
               var5 = TextureUtil.readBufferedImage(var4);
            }

            if (this.imageBuffer != null) {
               var5 = this.imageBuffer.parseUserSkin(var5);
            }

            this.setBufferedImage(var5);
            return;
         }
      } catch (Exception var9) {
         logger.error("Couldn't download http texture: " + var9.getClass().getName() + ": " + var9.getMessage());
         return;
      } finally {
         this.loadingFinished();
      }
   }
}
