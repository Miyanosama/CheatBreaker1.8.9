package net.minecraft.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block$1;
import net.minecraft.item.ItemFishFood$FishType;
import net.minecraft.item.ItemMonsterPlacer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class HttpUtil$1 implements Runnable {
   public ItemMonsterPlacer field_0004;
   public ItemFishFood$FishType field_0000;
   public Block$1 field_0001;

   @Override
   public void run() {
      HttpURLConnection var1 = null;
      InputStream var2 = null;
      DataOutputStream var3 = null;
      if (this.field_151199_a != null) {
         this.field_151199_a.resetProgressAndMessage("Downloading Resource Pack");
         this.field_151199_a.displayLoadingString("Making Request...");
      }

      try {
         byte[] var4 = new byte[4096];
         URL var18 = new URL(this.field_151197_b);
         var1 = (HttpURLConnection)var18.openConnection(this.field_151198_c);
         float var6 = 0.0F;
         float var7 = this.field_151195_d.entrySet().size();

         for (Entry var9 : this.field_151195_d.entrySet()) {
            var1.setRequestProperty((String)var9.getKey(), (String)var9.getValue());
            if (this.field_151199_a != null) {
               this.field_151199_a.setLoadingProgress((int)(++var6 / var7 * 100.0F));
            }
         }

         var2 = var1.getInputStream();
         var7 = var1.getContentLength();
         int var20 = var1.getContentLength();
         if (this.field_151199_a != null) {
            this.field_151199_a.displayLoadingString(String.format("Downloading file (%.2f MB)...", var7 / 1000.0F / 1000.0F));
         }

         if (this.field_151196_e.exists()) {
            long var21 = this.field_151196_e.length();
            if (var21 == var20) {
               if (this.field_151199_a != null) {
                  this.field_151199_a.setDoneWorking();
               }

               return;
            }

            HttpUtil.access$000()
               .warn("Deleting " + this.field_151196_e + " as it does not match what we currently have (" + var20 + " vs our " + var21 + ").");
            FileUtils.deleteQuietly(this.field_151196_e);
         } else if (this.field_151196_e.getParentFile() != null) {
            this.field_151196_e.getParentFile().mkdirs();
         }

         var3 = new DataOutputStream(new FileOutputStream(this.field_151196_e));
         if (this.field_151194_g > 0 && var7 > this.field_151194_g) {
            if (this.field_151199_a != null) {
               this.field_151199_a.setDoneWorking();
            }

            throw new IOException("Filesize is bigger than maximum allowed (file is " + var6 + ", limit is " + this.field_151194_g + ")");
         }

         int var22 = 0;

         while (true) {
            if ((var22 = var2.read(var4)) < 0) {
               if (this.field_151199_a == null) {
                  return;
               }

               this.field_151199_a.setDoneWorking();
               return;
            }

            var6 += var22;
            if (this.field_151199_a != null) {
               this.field_151199_a.setLoadingProgress((int)(var6 / var7 * 100.0F));
            }

            if (this.field_151194_g > 0 && var6 > this.field_151194_g) {
               if (this.field_151199_a != null) {
                  this.field_151199_a.setDoneWorking();
               }

               throw new IOException("Filesize was bigger than maximum allowed (got >= " + var6 + ", limit was " + this.field_151194_g + ")");
            }

            if (Thread.interrupted()) {
               HttpUtil.access$000().error("INTERRUPTED");
               if (this.field_151199_a != null) {
                  this.field_151199_a.setDoneWorking();
               }
               break;
            }

            var3.write(var4, 0, var22);
         }
      } catch (Throwable var16) {
         var16.printStackTrace();
         if (var1 != null) {
            InputStream var5 = var1.getErrorStream();

            try {
               HttpUtil.access$000().error(IOUtils.toString(var5));
            } catch (IOException var15) {
               var15.printStackTrace();
            }
         }

         if (this.field_151199_a != null) {
            this.field_151199_a.setDoneWorking();
            return;
         }

         return;
      } finally {
         IOUtils.closeQuietly(var2);
         IOUtils.closeQuietly(var3);
      }
   }

   public HttpUtil$1(IProgressUpdate var1, String var2, Proxy var3, Map var4, File var5, int var6) {
      this.field_151199_a = var1;
      this.field_151197_b = var2;
      this.field_151198_c = var3;
      this.field_151195_d = var4;
      this.field_151196_e = var5;
      this.field_151194_g = var6;
      super();
   }
}
