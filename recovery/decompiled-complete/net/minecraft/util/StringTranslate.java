package net.minecraft.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import io.netty.handler.codec.CorruptedFrameException;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.client.renderer.BlockModelShapes$3;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class StringTranslate {
   public BlockModelShapes$3 field_0003;
   public CorruptedFrameException field_0005;
   public long lastUpdateTimeInMilliseconds;
   public static Pattern numericVariablePattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
   public Map<String, String> languageList = Maps.newHashMap();
   public static Splitter equalSignSplitter = Splitter.on('=').limit(2);
   public static StringTranslate instance = new StringTranslate();

   public String tryTranslateKey(String var1) {
      String var2 = this.languageList.get(var1);
      return var2 == null ? var1 : var2;
   }

   public static StringTranslate getInstance() {
      return instance;
   }

   public synchronized String translateKeyFormat(String var1, Object... var2) {
      String var3 = this.tryTranslateKey(var1);

      try {
         return String.format(var3, var2);
      } catch (IllegalFormatException var5) {
         return "Format error: " + var3;
      }
   }

   public synchronized boolean isKeyTranslated(String var1) {
      return this.languageList.containsKey(var1);
   }

   public long getLastUpdateTimeInMilliseconds() {
      return this.lastUpdateTimeInMilliseconds;
   }

   public synchronized String translateKey(String var1) {
      return this.tryTranslateKey(var1);
   }

   public static synchronized void replaceWith(Map<String, String> var0) {
      instance.languageList.clear();
      instance.languageList.putAll(var0);
      instance.lastUpdateTimeInMilliseconds = System.currentTimeMillis();
   }

   public StringTranslate() {
      try {
         InputStream var1 = StringTranslate.class.getResourceAsStream("/assets/minecraft/lang/en_US.lang");

         for (String var3 : IOUtils.readLines(var1, Charsets.UTF_8)) {
            if (!var3.isEmpty() && var3.charAt(0) != '#') {
               String[] var4 = (String[])Iterables.toArray(equalSignSplitter.split(var3), String.class);
               if (var4 != null && var4.length == 2) {
                  String var5 = var4[0];
                  String var6 = numericVariablePattern.matcher(var4[1]).replaceAll("%$1s");
                  this.languageList.put(var5, var6);
               }
            }
         }

         this.lastUpdateTimeInMilliseconds = System.currentTimeMillis();
      } catch (IOException var7) {
      }
   }
}
