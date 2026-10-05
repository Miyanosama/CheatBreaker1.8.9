package net.minecraft.realms;

import com.google.common.collect.Lists;
import io.netty.util.internal.chmv8.ForkJoinPool$2;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.SaveFormatComparator;

public class RealmsAnvilLevelStorageSource {
   public ForkJoinPool$2 field_0000;
   public ISaveFormat levelStorageSource;

   public List<RealmsLevelSummary> getLevelList() {
      ArrayList var1 = Lists.newArrayList();

      for (SaveFormatComparator var3 : this.levelStorageSource.getSaveList()) {
         var1.add(new RealmsLevelSummary(var3));
      }

      return var1;
   }

   public boolean method_23227(String var1) {
      return this.levelStorageSource.canLoadWorld(var1);
   }

   public boolean method_23232(String var1) {
      return this.levelStorageSource.deleteWorldDirectory(var1);
   }

   public boolean method_23225(String var1) {
      return this.levelStorageSource.isOldMapFormat(var1);
   }

   public String getName() {
      return this.levelStorageSource.getName();
   }

   public boolean convertLevel(String var1, IProgressUpdate var2) {
      return this.levelStorageSource.convertMapFormat(var1, var2);
   }

   public RealmsAnvilLevelStorageSource(ISaveFormat var1) {
      this.levelStorageSource = var1;
   }

   public void clearAll() {
      this.levelStorageSource.flushCache();
   }

   public boolean method_23230(String var1) {
      return this.levelStorageSource.method_24361(var1);
   }

   public boolean method_23223(String var1) {
      return this.levelStorageSource.method_02874(var1);
   }

   public void renameLevel(String var1, String var2) {
      this.levelStorageSource.renameWorld(var1, var2);
   }
}
