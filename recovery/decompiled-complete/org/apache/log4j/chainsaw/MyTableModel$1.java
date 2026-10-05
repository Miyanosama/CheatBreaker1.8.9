package org.apache.log4j.chainsaw;

import java.util.Comparator;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.scoreboard.Score;
import net.minecraft.util.ThreadSafeBoundList;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import org.scijava.nativelib.NativeLibraryUtil$Architecture;

public class MyTableModel$1 implements Comparator {
   public WorldGenTaiga2 field_0003;
   public FurnaceRecipes field_0005;
   public ThreadSafeBoundList field_0002;
   public RenderChicken field_0004;
   public Score field_0000;
   public NativeLibraryUtil$Architecture field_0001;

   public int compare(Object var1, Object var2) {
      if (var1 == null && var2 == null) {
         return 0;
      } else if (var1 == null) {
         return -1;
      } else if (var2 == null) {
         return 1;
      } else {
         EventDetails var3 = (EventDetails)var1;
         EventDetails var4 = (EventDetails)var2;
         return var3.getTimeStamp() < var4.getTimeStamp() ? 1 : -1;
      }
   }
}
