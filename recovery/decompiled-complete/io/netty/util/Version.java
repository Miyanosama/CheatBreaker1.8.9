package io.netty.util;

import io.netty.channel.socket.nio.ProtocolFamilyConverter$1;
import io.netty.util.internal.PlatformDependent;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import net.minecraft.client.renderer.block.model.BlockPart$Deserializer;

public class Version {
   public String artifactId;
   public static String PROP_COMMIT_DATE;
   public static String PROP_REPO_STATUS;
   public static String PROP_LONG_COMMIT_HASH;
   public long buildTimeMillis;
   public long commitTimeMillis;
   public static String PROP_VERSION;
   public String longCommitHash;
   public String repositoryStatus;
   public static String PROP_SHORT_COMMIT_HASH;
   public static String PROP_BUILD_DATE;
   public BlockPart$Deserializer __junk6369113700094211202;
   public ProtocolFamilyConverter$1 __junk7797317917339087231;
   public String shortCommitHash;
   public String artifactVersion;

   public String repositoryStatus() {
      return this.repositoryStatus;
   }

   public static void main(String[] var0) {
      for (Version var2 : identify().values()) {
         System.err.println(var2);
      }
   }

   public long commitTimeMillis() {
      return this.commitTimeMillis;
   }

   public static Map<String, Version> identify(ClassLoader var0) {
      if (var0 == null) {
         var0 = PlatformDependent.getContextClassLoader();
      }

      Properties var1 = new Properties();

      try {
         Enumeration var2 = var0.getResources("META-INF/io.netty.versions.properties");

         while (var2.hasMoreElements()) {
            URL var3 = (URL)var2.nextElement();
            InputStream var4 = var3.openStream();

            try {
               var1.load(var4);
            } finally {
               try {
                  var4.close();
               } catch (Exception var12) {
               }
            }
         }
      } catch (Exception var14) {
      }

      HashSet var15 = new HashSet();

      for (Object var18 : var1.keySet()) {
         String var5 = (String)var18;
         int var6 = var5.indexOf(46);
         if (var6 > 0) {
            String var7 = var5.substring(0, var6);
            if (var1.containsKey(var7 + ".version")
               && var1.containsKey(var7 + ".buildDate")
               && var1.containsKey(var7 + ".commitDate")
               && var1.containsKey(var7 + ".shortCommitHash")
               && var1.containsKey(var7 + ".longCommitHash")
               && var1.containsKey(var7 + ".repoStatus")) {
               var15.add(var7);
            }
         }
      }

      TreeMap var17 = new TreeMap();

      for (String var20 : var15) {
         var17.put(
            var20,
            new Version(
               var20,
               var1.getProperty(var20 + ".version"),
               parseIso8601(var1.getProperty(var20 + ".buildDate")),
               parseIso8601(var1.getProperty(var20 + ".commitDate")),
               var1.getProperty(var20 + ".shortCommitHash"),
               var1.getProperty(var20 + ".longCommitHash"),
               var1.getProperty(var20 + ".repoStatus")
            )
         );
      }

      return var17;
   }

   public String shortCommitHash() {
      return this.shortCommitHash;
   }

   public String longCommitHash() {
      return this.longCommitHash;
   }

   public String artifactVersion() {
      return this.artifactVersion;
   }

   @Override
   public String toString() {
      return this.artifactId
         + '-'
         + this.artifactVersion
         + '.'
         + this.shortCommitHash
         + ("clean".equals(this.repositoryStatus) ? "" : " (repository: " + this.repositoryStatus + ')');
   }

   public Version(String var1, String var2, long var3, long var5, String var7, String var8, String var9) {
      this.artifactId = var1;
      this.artifactVersion = var2;
      this.buildTimeMillis = var3;
      this.commitTimeMillis = var5;
      this.shortCommitHash = var7;
      this.longCommitHash = var8;
      this.repositoryStatus = var9;
   }

   public static long parseIso8601(String var0) {
      try {
         return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").parse(var0).getTime();
      } catch (ParseException var2) {
         return 1212200002L & 8964144490376022708L;
      }
   }

   public static Map<String, Version> identify() {
      return identify(null);
   }

   public String artifactId() {
      return this.artifactId;
   }

   public long buildTimeMillis() {
      return this.buildTimeMillis;
   }
}
