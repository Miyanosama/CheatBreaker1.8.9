package net.optifine.http;

import net.minecraft.client.Minecraft;

public class FileDownloadThread extends Thread {
   public IFileDownloadListener listener;
   public String urlString = null;

   public FileDownloadThread(String var1, IFileDownloadListener var2) {
      this.listener = null;
      this.urlString = var1;
      this.listener = var2;
   }

   @Override
   public void run() {
      try {
         byte[] var1 = HttpPipeline.get(this.urlString, Minecraft.getMinecraft().getProxy());
         this.listener.fileDownloadFinished(this.urlString, var1, (Throwable)null);
      } catch (Exception var2) {
         this.listener.fileDownloadFinished(this.urlString, (byte[])null, var2);
      }
   }

   public IFileDownloadListener getListener() {
      return this.listener;
   }

   public String getUrlString() {
      return this.urlString;
   }
}
