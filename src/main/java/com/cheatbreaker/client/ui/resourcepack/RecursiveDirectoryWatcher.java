package com.cheatbreaker.client.ui.resourcepack;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackFolderScanner;

import com.cheatbreaker.client.CheatBreaker;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class RecursiveDirectoryWatcher {
   public WatchService recoveredField3554;

   public boolean method_28978() {
      boolean var2 = false;
      WatchKey var1;
      if (this.recoveredField3554 != null && (var1 = this.recoveredField3554.poll()) != null) {
         for (WatchEvent var4 : var1.pollEvents()) {
            File var5 = ((Path)var1.watchable()).resolve(var4.context().toString()).toFile();
            if (ResourcePackFolderScanner.method_26232(var5) || ResourcePackFolderScanner.method_26228(var5)) {
               var2 = true;
               break;
            }
         }

         var1.reset();
      }

      return var2;
   }

   public RecursiveDirectoryWatcher(Path var1) {
      try {
         this.recoveredField3554 = FileSystems.getDefault().newWatchService();
         Files.walkFileTree(var1, new DirectoryRegistrationVisitor(this));
      } catch (IOException var3) {
         CheatBreaker.getInstance().method_19789().error("Failed to create watch service", var3);
      }
   }

   // $VF: synthetic method
   public static WatchService method_28979(RecursiveDirectoryWatcher var0) {
      return var0.recoveredField3554;
   }
}
