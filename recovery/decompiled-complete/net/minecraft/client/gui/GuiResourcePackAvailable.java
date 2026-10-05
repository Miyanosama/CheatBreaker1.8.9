package net.minecraft.client.gui;

import java.util.List;
import javax.vecmath.SingularMatrixException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.stream.BroadcastController$1;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;

public class GuiResourcePackAvailable extends GuiResourcePackList {
   public BroadcastController$1 field_0001;
   public SingularMatrixException field_0003;
   public C01PacketEncryptionResponse field_0000;
   public AnvilChunkLoader field_0002;

   public GuiResourcePackAvailable(Minecraft var1, int var2, int var3, List<ResourcePackListEntry> var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   public String getListHeader() {
      return I18n.format("resourcePack.available.title");
   }
}
