package com.cheatbreaker.client.ui.resourcepack;

import com.cheatbreaker.client.ui.resourcepack.RecursiveDirectoryWatcher;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackFolderScanner;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryRegistrationVisitor extends SimpleFileVisitor<Path> {
   public RecursiveDirectoryWatcher recoveredField3934;

   public FileVisitResult preVisitDirectory(Path var1, BasicFileAttributes var2) throws java.io.IOException {
      File var3 = var1.toFile();
      if (ResourcePackFolderScanner.method_26228(var3)) {
         var1.register(
            RecursiveDirectoryWatcher.method_28979(this.recoveredField3934),
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_MODIFY,
            StandardWatchEventKinds.ENTRY_DELETE
         );
         return FileVisitResult.CONTINUE;
      } else {
         return ResourcePackFolderScanner.method_26232(var3) ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
      }
   }

   public DirectoryRegistrationVisitor(RecursiveDirectoryWatcher var1) {
      this.recoveredField3934 = var1;
   }
}
