package net.minecraft.client.main;

import java.io.File;

public class GameConfiguration$FolderInformation {
   public File assetsDir;
   public File mcDataDir;
   public File resourcePacksDir;
   public String assetIndex;

   public GameConfiguration$FolderInformation(File var1, File var2, File var3, String var4) {
      this.mcDataDir = var1;
      this.resourcePacksDir = var2;
      this.assetsDir = var3;
      this.assetIndex = var4;
   }
}
