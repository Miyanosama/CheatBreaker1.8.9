package net.minecraft.client.renderer;

import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.src.Config;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleXRoom;
import net.optifine.CustomItems;
import org.apache.commons.io.FileUtils;

public class ThreadDownloadImageData$1 extends Thread {
   public CustomItems field_0001;
   public StructureOceanMonumentPieces$DoubleXRoom field_0000;

   @Override
   public void run() {
      HttpURLConnection var1 = null;
      ThreadDownloadImageData.access$200()
         .debug(
            "Downloading http texture from {} to {}",
            new Object[]{ThreadDownloadImageData.access$000(this.this$0), ThreadDownloadImageData.access$100(this.this$0)}
         );
      if (ThreadDownloadImageData.access$300(this.this$0)) {
         ThreadDownloadImageData.method_22782(this.this$0);
      } else {
         try {
            var1 = (HttpURLConnection)new URL(ThreadDownloadImageData.access$000(this.this$0)).openConnection(Minecraft.getMinecraft().getProxy());
            var1.setDoInput(true);
            var1.setDoOutput(false);
            var1.connect();
            if (var1.getResponseCode() / 100 == 2) {
               BufferedImage var2;
               if (ThreadDownloadImageData.access$100(this.this$0) != null) {
                  FileUtils.copyInputStreamToFile(var1.getInputStream(), ThreadDownloadImageData.access$100(this.this$0));
                  var2 = ImageIO.read(ThreadDownloadImageData.access$100(this.this$0));
               } else {
                  var2 = TextureUtil.readBufferedImage(var1.getInputStream());
               }

               if (ThreadDownloadImageData.access$300(this.this$0) != null) {
                  var2 = ThreadDownloadImageData.access$300(this.this$0).parseUserSkin(var2);
               }

               this.this$0.setBufferedImage(var2);
               return;
            }

            if (var1.getErrorStream() != null) {
               Config.readAll(var1.getErrorStream());
            }

            return;
         } catch (Exception var6) {
            ThreadDownloadImageData.access$200().error("Couldn't download http texture: " + var6.getClass().getName() + ": " + var6.getMessage());
         } finally {
            if (var1 != null) {
               var1.disconnect();
            }

            ThreadDownloadImageData.method_22789(this.this$0);
         }
      }
   }

   public ThreadDownloadImageData$1(ThreadDownloadImageData var1, String var2) {
      this.this$0 = var1;
      super(var2);
   }
}
