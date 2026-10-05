package net.minecraft.client.resources;

import io.netty.channel.ChannelOutboundBuffer$Entry$1;
import io.netty.handler.stream.ChunkedNioFile;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.world.gen.ChunkProviderServer;
import recovered.unidentified.UnidentifiedClass4638;

public class ResourcePackListEntryFound extends ResourcePackListEntry {
   public TileEntityLockable field_0006;
   public ResourcePackRepository$Entry field_148319_c;
   public ChunkedNioFile field_0002;
   public ChunkProviderServer field_0003;
   public UnidentifiedClass4638 field_0001;
   public EntityMinecartContainer field_0004;
   public ChannelOutboundBuffer$Entry$1 field_0005;

   @Override
   public String func_148312_b() {
      return this.field_148319_c.getResourcePackName();
   }

   @Override
   public void func_148313_c() {
      this.field_148319_c.bindTexturePackIcon(this.a.getTextureManager());
   }

   public ResourcePackRepository$Entry func_148318_i() {
      return this.field_148319_c;
   }

   public ResourcePackListEntryFound(GuiScreenResourcePacks var1, ResourcePackRepository$Entry var2) {
      super(var1);
      this.field_148319_c = var2;
   }

   @Override
   public String func_148311_a() {
      return this.field_148319_c.getTexturePackDescription();
   }

   @Override
   public int func_183019_a() {
      return this.field_148319_c.func_183027_f();
   }
}
