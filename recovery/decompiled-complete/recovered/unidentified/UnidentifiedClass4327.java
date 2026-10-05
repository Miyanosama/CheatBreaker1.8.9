package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import junit.swingui.TestRunner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.entity.passive.EntitySquid$AIMoveRandom;

public class UnidentifiedClass4327 {
   public static Minecraft field_0001 = Minecraft.getMinecraft();
   public TestRunner field_0002;
   public EntitySquid$AIMoveRandom field_0000;

   public static Optional<ResourcePackRepository$Entry> method_26230(File var0) {
      Optional var1 = Optional.empty();

      try {
         Constructor var2 = ResourcePackRepository$Entry.class.getDeclaredConstructor(ResourcePackRepository.class, File.class);
         var2.setAccessible(true);
         var1 = Optional.of(var2.newInstance(field_0001.getResourcePackRepository(), var0));
         ((ResourcePackRepository$Entry)var1.get()).updateResourcePack();
      } catch (NoSuchMethodException var3) {
         CheatBreaker.getInstance().method_19789().error("Failed to find constructor", var3);
         var1 = Optional.empty();
      } catch (InstantiationException | InvocationTargetException | IllegalAccessException var4) {
         CheatBreaker.getInstance().method_19789().error("Failed to create entry", var4);
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      return var1;
   }

   public static List<ResourcePackRepository$Entry> method_26231(File var0, String var1) {
      ArrayList var2 = new ArrayList();

      for (File var6 : Objects.requireNonNull(var0.listFiles())) {
         if (method_26232(var6)) {
            if (var6.getName().equals(var1)) {
               method_26230(var6).ifPresent(var2::add);
            }
         } else if (method_26228(var6)) {
            var2.addAll(method_26231(var6, var1));
         }
      }

      return var2;
   }

   public static boolean method_26232(File var0) {
      try {
         if (var0.isFile() && var0.getName().endsWith(".zip")) {
            try {
               ZipFile var1 = new ZipFile(var0);
               ZipEntry var2 = var1.getEntry("pack.mcmeta");
               if (var2 == null) {
                  System.out.println("Not found in: " + var0.getName());
                  return false;
               } else {
                  return true;
               }
            } catch (Exception var3) {
               return false;
            }
         } else {
            return var0.isDirectory() && new File(var0, "pack.mcmeta").isFile();
         }
      } catch (Throwable var4) {
         throw var4;
      }
   }

   public static List<ResourcePackRepository$Entry> method_26229() {
      ArrayList var0 = new ArrayList();
      ResourcePackRepository var1 = field_0001.getResourcePackRepository();

      for (String var3 : field_0001.gameSettings.resourcePacks) {
         for (ResourcePackRepository$Entry var5 : var1.getRepositoryEntriesAll()) {
            if (!var5.getResourcePackName().equals(var3)) {
            }
         }

         for (File var7 : Objects.requireNonNull(var1.getDirResourcepacks().listFiles(UnidentifiedClass4327::method_26228))) {
            var0.addAll(method_26231(var7, var3));
         }
      }

      return var0;
   }

   public static boolean method_26228(File var0) {
      return var0.isDirectory() && !new File(var0, "pack.mcmeta").isFile();
   }
}
