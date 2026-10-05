package net.minecraft.client.resources;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ValuesView;
import java.util.List;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.model.ModelQuadruped;

public class ResourcePackListEntry$1 implements GuiYesNoCallback {
   public ModelQuadruped field_0003;
   public FallbackResourceManager field_0000;
   public ConcurrentHashMapV8$ValuesView field_0002;

   @Override
   public void confirmClicked(boolean var1, int var2) {
      List var3 = this.field_183004_a.resourcePacksGUI.getListContaining(this.field_183004_a);
      this.field_183004_a.a.displayGuiScreen(this.field_183004_a.resourcePacksGUI);
      if (var1) {
         var3.remove(this.field_183004_a);
         this.field_183004_a.resourcePacksGUI.getSelectedResourcePacks().add(0, this.field_183004_a);
      }
   }

   public ResourcePackListEntry$1(ResourcePackListEntry var1) {
      this.field_183004_a = var1;
      super();
   }
}
