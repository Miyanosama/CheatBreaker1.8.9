package net.minecraft.client.resources;

import net.minecraft.client.gui.GuiScreenResourcePacks;

public class ResourcePackListEntryFound extends ResourcePackListEntry {
   public ResourcePackRepository.Entry field_148319_c;

   @Override
   public String func_148312_b() {
      return this.field_148319_c.getResourcePackName();
   }

   @Override
   public void func_148313_c() {
      this.field_148319_c.bindTexturePackIcon(this.a.getTextureManager());
   }

   public ResourcePackRepository.Entry func_148318_i() {
      return this.field_148319_c;
   }

   public ResourcePackListEntryFound(GuiScreenResourcePacks var1, ResourcePackRepository.Entry var2) {
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
