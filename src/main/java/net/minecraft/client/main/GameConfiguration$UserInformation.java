package net.minecraft.client.main;

import com.mojang.authlib.properties.PropertyMap;
import java.net.Proxy;
import net.minecraft.util.Session;

public class GameConfiguration$UserInformation {
   public PropertyMap userProperties;
   public Proxy proxy;
   public Session session;
   public PropertyMap profileProperties;

   public GameConfiguration$UserInformation(Session var1, PropertyMap var2, PropertyMap var3, Proxy var4) {
      this.session = var1;
      this.userProperties = var2;
      this.profileProperties = var3;
      this.proxy = var4;
   }
}
