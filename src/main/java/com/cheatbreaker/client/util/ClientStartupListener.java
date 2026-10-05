package com.cheatbreaker.client.util;

import com.cheatbreaker.client.event.type.ClientInitializedEvent;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackFolderScanner;

import com.cheatbreaker.client.util.AsyncExecutor;

import com.cheatbreaker.client.CheatBreaker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ResourcePackRepository;

public class ClientStartupListener {
   public Minecraft recoveredField3563 = Minecraft.getMinecraft();

   public ClientStartupListener() {
      CheatBreaker.getInstance().method_19817().method_21938(ClientInitializedEvent.class, this::method_28652);
   }

   public void method_28652(ClientInitializedEvent var1) {
      HashMap var2 = new HashMap();

      for (String var4 : this.recoveredField3563.gameSettings.resourcePacks) {
         var2.put(var4, null);
      }

      for (ResourcePackRepository.Entry var7 : this.recoveredField3563.getResourcePackRepository().getRepositoryEntries()) {
         var2.put(var7.getResourcePackName(), var7);
      }

      for (ResourcePackRepository.Entry var8 : ResourcePackFolderScanner.method_26229()) {
         var2.put(var8.getResourcePackName(), var8);
      }

      var2.values().removeIf(Objects::isNull);
      this.recoveredField3563.getResourcePackRepository().setRepositories(new ArrayList<>(var2.values()));
      this.recoveredField3563.refreshResources();
      Runtime.getRuntime().addShutdownHook(new Thread(AsyncExecutor::method_22036));
   }
}
