package net.minecraft.client.main;

public class GameConfiguration {
   public GameConfiguration$GameInformation gameInfo;
   public GameConfiguration$ServerInformation serverInfo;
   public GameConfiguration$DisplayInformation displayInfo;
   public GameConfiguration$UserInformation userInfo;
   public GameConfiguration$FolderInformation folderInfo;

   public GameConfiguration(
      GameConfiguration$UserInformation var1,
      GameConfiguration$DisplayInformation var2,
      GameConfiguration$FolderInformation var3,
      GameConfiguration$GameInformation var4,
      GameConfiguration$ServerInformation var5
   ) {
      this.userInfo = var1;
      this.displayInfo = var2;
      this.folderInfo = var3;
      this.gameInfo = var4;
      this.serverInfo = var5;
   }
}
