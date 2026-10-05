package net.minecraft.client.resources;

import java.io.File;
import java.io.FileNotFoundException;
import org.apache.log4j.spi.DefaultRepositorySelector;

public class ResourcePackFileNotFoundException extends FileNotFoundException {
   public DefaultRepositorySelector field_0000;

   public ResourcePackFileNotFoundException(File var1, String var2) {
      super(String.format("'%s' in ResourcePack '%s'", var2, var1));
   }
}
