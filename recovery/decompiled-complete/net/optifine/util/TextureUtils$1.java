package net.optifine.util;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.stream.ChatController$ChatChannelListener;

public class TextureUtils$1 implements IResourceManagerReloadListener {
   public FrameEvent field_0000;
   public ChatController$ChatChannelListener field_0001;

   @Override
   public void onResourceManagerReload(IResourceManager var1) {
      TextureUtils.resourcesReloaded(var1);
   }
}
