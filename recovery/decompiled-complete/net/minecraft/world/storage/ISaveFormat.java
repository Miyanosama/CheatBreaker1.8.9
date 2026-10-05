package net.minecraft.world.storage;

import java.util.List;
import net.minecraft.util.IProgressUpdate;

public interface ISaveFormat {
   boolean method_02874(String var1);

   WorldInfo getWorldInfo(String var1);

   void flushCache();

   List<SaveFormatComparator> getSaveList();

   boolean deleteWorldDirectory(String var1);

   boolean canLoadWorld(String var1);

   ISaveHandler getSaveLoader(String var1, boolean var2);

   boolean method_24361(String var1);

   boolean convertMapFormat(String var1, IProgressUpdate var2);

   String getName();

   boolean isOldMapFormat(String var1);

   void renameWorld(String var1, String var2);
}
