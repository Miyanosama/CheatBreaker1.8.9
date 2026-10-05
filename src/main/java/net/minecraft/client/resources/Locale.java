package net.minecraft.client.resources;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class Locale {
   public static Splitter splitter = Splitter.on('=').limit(2);
   public static Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
   public Map<String, String> properties = Maps.newHashMap();
   public boolean unicode;

   public void loadLocaleData(InputStream var1) throws java.io.IOException {
      for (String var3 : IOUtils.readLines(var1, Charsets.UTF_8)) {
         if (!var3.isEmpty() && var3.charAt(0) != '#') {
            String[] var4 = Iterables.toArray(splitter.split(var3), String.class);
            if (var4 != null && var4.length == 2) {
               String var5 = var4[0];
               String var6 = pattern.matcher(var4[1]).replaceAll("%$1s");
               this.properties.put(var5, var6);
            }
         }
      }
   }

   public String translateKeyPrivate(String var1) {
      String var2 = this.properties.get(var1);
      return var2 == null ? var1 : var2;
   }

   public synchronized void loadLocaleDataFiles(IResourceManager var1, List<String> var2) {
      this.properties.clear();

      for (String var4 : var2) {
         String var5 = String.format("lang/%s.lang", var4);

         for (String var7 : var1.getResourceDomains()) {
            try {
               this.loadLocaleData(var1.getAllResources(new ResourceLocation(var7, var5)));
            } catch (IOException var9) {
            }
         }
      }

      this.checkUnicode();
   }

   public void loadLocaleData(List<IResource> var1) throws java.io.IOException {
      for (IResource var3 : var1) {
         InputStream var4 = var3.getInputStream();

         try {
            this.loadLocaleData(var4);
         } finally {
            IOUtils.closeQuietly(var4);
         }
      }
   }

   public boolean isUnicode() {
      return this.unicode;
   }

   public String formatMessage(String var1, Object[] var2) {
      String var3 = this.translateKeyPrivate(var1);

      try {
         return String.format(var3, var2);
      } catch (IllegalFormatException var5) {
         return "Format error: " + var3;
      }
   }

   public void checkUnicode() {
      this.unicode = false;
      int var1 = 0;
      int var2 = 0;

      for (String var4 : this.properties.values()) {
         int var5 = var4.length();
         var2 += var5;

         for (int var6 = 0; var6 < var5; var6++) {
            if (var4.charAt(var6) >= 256) {
               var1++;
            }
         }
      }

      float var7 = (float)var1 / var2;
      this.unicode = var7 > 0.1;
   }
}
