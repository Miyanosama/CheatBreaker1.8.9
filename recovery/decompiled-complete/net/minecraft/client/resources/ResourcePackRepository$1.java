package net.minecraft.client.resources;

import io.netty.channel.AbstractChannelHandlerContext$13;
import java.io.File;
import java.io.FileFilter;
import net.minecraft.item.ItemFishFood$FishType;

public class ResourcePackRepository$1 implements FileFilter {
   public AbstractChannelHandlerContext$13 field_0000;
   public ItemFishFood$FishType field_0001;

   @Override
   public boolean accept(File var1) {
      boolean var2 = var1.isFile() && var1.getName().endsWith(".zip");
      boolean var3 = var1.isDirectory() && new File(var1, "pack.mcmeta").isFile();
      return var2 || var3;
   }
}
