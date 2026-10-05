package net.optifine.http;

import io.netty.util.internal.chmv8.ForkJoinTask;
import java.util.Map;
import net.minecraft.block.state.BlockState$1;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.passive.EntityRabbit;

public class FileUploadThread extends Thread {
   public Map headers;
   public IFileUploadListener listener;
   public ForkJoinTask field_0002;
   public I18n field_0005;
   public byte[] content;
   public EntityRabbit field_0001;
   public String urlString;
   public BlockState$1 field_0004;

   public FileUploadThread(String var1, Map var2, byte[] var3, IFileUploadListener var4) {
      this.urlString = var1;
      this.headers = var2;
      this.content = var3;
      this.listener = var4;
   }

   @Override
   public void run() {
      try {
         HttpUtils.post(this.urlString, this.headers, this.content);
         this.listener.fileUploadFinished(this.urlString, this.content, (Throwable)null);
      } catch (Exception var2) {
         this.listener.fileUploadFinished(this.urlString, this.content, var2);
      }
   }

   public String getUrlString() {
      return this.urlString;
   }

   public IFileUploadListener getListener() {
      return this.listener;
   }

   public byte[] getContent() {
      return this.content;
   }
}
