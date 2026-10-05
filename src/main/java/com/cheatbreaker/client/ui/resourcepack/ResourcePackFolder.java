package com.cheatbreaker.client.ui.resourcepack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.client.resources.ResourcePackRepository;

public class ResourcePackFolder {
   public List<ResourcePackRepository.Entry> recoveredField67;
   public List<ResourcePackRepository.Entry> recoveredField68;
   public String recoveredField69;
   public ResourcePackFolder recoveredField70;
   public List<ResourcePackFolder> recoveredField71;

   public List<ResourcePackRepository.Entry> method_26763() {
      return this.recoveredField68;
   }

   public List<ResourcePackFolder> method_26765() {
      return this.recoveredField71;
   }

   public ResourcePackFolder(File var1, List<ResourcePackRepository.Entry> var2) {
      this(var1, var2, null);
   }

   public ResourcePackFolder(File var1, List<ResourcePackRepository.Entry> var2, ResourcePackFolder var3) {
      this.recoveredField69 = var1.getName();
      this.recoveredField68 = new ArrayList<>();
      this.recoveredField67 = var2;
      this.recoveredField71 = new ArrayList<>();
      this.recoveredField70 = var3;
      this.recoveredField71.add(new ResourcePackFolder(var3));

      for (File var7 : Objects.requireNonNull(var1.listFiles())) {
         if (!ResourcePackFolderScanner.method_26232(var7)) {
            if (ResourcePackFolderScanner.method_26228(var7)) {
               this.recoveredField71.add(new ResourcePackFolder(var7, var2, this));
            }
         } else {
            for (ResourcePackRepository.Entry var9 : this.recoveredField67) {
               if (var9.getResourcePackName().equals(var7.getName())) {
                  Optional var10 = ResourcePackFolderScanner.method_26230(var7);
                  if (var10.isPresent() && ((ResourcePackRepository.Entry)var10.get()).equals(var9)) {
                     break;
                  }
               }
            }

            ResourcePackFolderScanner.method_26230(var7).ifPresent(this.recoveredField68::add);
         }
      }
   }

   public List<ResourcePackRepository.Entry> method_26762() {
      return this.recoveredField67;
   }

   public ResourcePackFolder(ResourcePackFolder var1) {
      this.recoveredField69 = "Back to " + (var1 == null ? "Main Folder" : var1.recoveredField69);
      this.recoveredField68 = new ArrayList<>();
      this.recoveredField67 = new ArrayList<>();
      this.recoveredField71 = new ArrayList<>();
      this.recoveredField70 = var1;
   }

   public String method_26764() {
      return this.recoveredField69;
   }

   public ResourcePackFolder method_26761() {
      return this.recoveredField70;
   }
}
