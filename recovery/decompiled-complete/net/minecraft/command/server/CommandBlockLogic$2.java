package net.minecraft.command.server;

import java.util.concurrent.Callable;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.item.crafting.RecipesBanners$RecipeAddPattern;

public class CommandBlockLogic$2 implements Callable<String> {
   public GuiUtilRenderComponents field_0001;
   public RecipesBanners$RecipeAddPattern field_0000;

   public CommandBlockLogic$2(CommandBlockLogic var1) {
      this.field_180327_a = var1;
      super();
   }

   public String call() {
      return this.field_180327_a.z_();
   }
}
