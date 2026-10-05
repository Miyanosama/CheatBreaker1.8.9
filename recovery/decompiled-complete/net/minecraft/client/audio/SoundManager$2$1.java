package net.minecraft.client.audio;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityMinecartContainer;

public class SoundManager$2$1 extends URLConnection {
   public EntityMinecartContainer field_0000;

   @Override
   public InputStream getInputStream() {
      return Minecraft.getMinecraft().getResourceManager().getResource(this.field_0001.field_0000).getInputStream();
   }

   @Override
   public void connect() {
   }

   public SoundManager$2$1(SoundManager$2 var1, URL var2) {
      this.field_0001 = var1;
      super(var2);
   }
}
