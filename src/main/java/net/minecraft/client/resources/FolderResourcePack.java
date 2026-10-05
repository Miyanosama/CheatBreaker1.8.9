package net.minecraft.client.resources;

import com.google.common.collect.Sets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class FolderResourcePack extends AbstractResourcePack {
   @Override
   public boolean hasResourceName(String var1) {
      return new File(this.a, var1).isFile();
   }

   @Override
   public Set<String> getResourceDomains() {
      HashSet var1 = Sets.newHashSet();
      File var2 = new File(this.a, "assets/");
      if (var2.isDirectory()) {
         for (File var6 : var2.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY)) {
            String var7 = getRelativeName(var2, var6);
            if (!var7.equals(var7.toLowerCase())) {
               this.c(var7);
            } else {
               var1.add(var7.substring(0, var7.length() - 1));
            }
         }
      }

      return var1;
   }

   @Override
   public InputStream getInputStreamByName(String var1) throws java.io.IOException {
      return new BufferedInputStream(new FileInputStream(new File(this.a, var1)));
   }

   public FolderResourcePack(File var1) {
      super(var1);
   }
}
