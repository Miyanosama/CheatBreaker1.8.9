package io.netty.handler.codec.http.multipart;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.vecmath.Tuple4i;
import net.minecraft.world.gen.ChunkProviderSettings;

public class DefaultHttpDataFactory implements HttpDataFactory {
   public long minSize;
   public boolean checkSize;
   public Map<HttpRequest, List<HttpData>> requestFileDeleteMap = PlatformDependent.newConcurrentHashMap();
   public static final long MINSIZE = 16384L;
   public boolean useDisk;

   @Override
   public Attribute createAttribute(HttpRequest var1, String var2, String var3) {
      if (this.useDisk) {
         Object var8;
         try {
            var8 = new DiskAttribute(var2, var3);
         } catch (IOException var6) {
            var8 = new MixedAttribute(var2, var3, this.minSize);
         }

         List var9 = this.getList(var1);
         var9.add(var8);
         return (Attribute)var8;
      } else if (this.checkSize) {
         MixedAttribute var4 = new MixedAttribute(var2, var3, this.minSize);
         List var5 = this.getList(var1);
         var5.add(var4);
         return var4;
      } else {
         try {
            return new MemoryAttribute(var2, var3);
         } catch (IOException var7) {
            throw new IllegalArgumentException(var7);
         }
      }
   }

   public DefaultHttpDataFactory(boolean var1) {
      this.useDisk = var1;
      this.checkSize = false;
   }

   public DefaultHttpDataFactory() {
      this.useDisk = false;
      this.checkSize = true;
      this.minSize = 16384L;
   }

   @Override
   public FileUpload createFileUpload(HttpRequest var1, String var2, String var3, String var4, String var5, Charset var6, long var7) {
      if (this.useDisk) {
         DiskFileUpload var11 = new DiskFileUpload(var2, var3, var4, var5, var6, var7);
         List var12 = this.getList(var1);
         var12.add(var11);
         return var11;
      } else if (this.checkSize) {
         MixedFileUpload var9 = new MixedFileUpload(var2, var3, var4, var5, var6, var7, this.minSize);
         List var10 = this.getList(var1);
         var10.add(var9);
         return var9;
      } else {
         return new MemoryFileUpload(var2, var3, var4, var5, var6, var7);
      }
   }

   @Override
   public void cleanAllHttpDatas() {
      Iterator var1 = this.requestFileDeleteMap.entrySet().iterator();

      while (var1.hasNext()) {
         Entry var2 = (Entry)var1.next();
         var1.remove();
         List var3 = (List)var2.getValue();
         if (var3 != null) {
            for (HttpData var5 : (Iterable<HttpData>)(Iterable<?>)(var3)) {
               var5.delete();
            }

            var3.clear();
         }
      }
   }

   @Override
   public void removeHttpDataFromClean(HttpRequest var1, InterfaceHttpData var2) {
      if (var2 instanceof HttpData) {
         List var3 = this.getList(var1);
         var3.remove(var2);
      }
   }

   public DefaultHttpDataFactory(long var1) {
      this.useDisk = false;
      this.checkSize = true;
      this.minSize = var1;
   }

   @Override
   public Attribute createAttribute(HttpRequest var1, String var2) {
      if (this.useDisk) {
         DiskAttribute var5 = new DiskAttribute(var2);
         List var6 = this.getList(var1);
         var6.add(var5);
         return var5;
      } else if (this.checkSize) {
         MixedAttribute var3 = new MixedAttribute(var2, this.minSize);
         List var4 = this.getList(var1);
         var4.add(var3);
         return var3;
      } else {
         return new MemoryAttribute(var2);
      }
   }

   public List<HttpData> getList(HttpRequest var1) {
      Object var2 = this.requestFileDeleteMap.get(var1);
      if (var2 == null) {
         var2 = new ArrayList();
         this.requestFileDeleteMap.put(var1, (List<HttpData>)var2);
      }

      return (List<HttpData>)var2;
   }

   @Override
   public void cleanRequestHttpDatas(HttpRequest var1) {
      List var2 = this.requestFileDeleteMap.remove(var1);
      if (var2 != null) {
         for (HttpData var4 : (Iterable<HttpData>)(Iterable<?>)(var2)) {
            var4.delete();
         }

         var2.clear();
      }
   }
}
