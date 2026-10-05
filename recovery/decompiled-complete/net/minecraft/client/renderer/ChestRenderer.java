package net.minecraft.client.renderer;

import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.optifine.reflect.FieldLocatorName;
import org.apache.log4j.lf5.LogRecord;

public class ChestRenderer {
   public FieldLocatorName field_0002;
   public LogRecord field_0004;
   public BlockModelRenderer$EnumNeighborInfo field_0001;
   public BlockNetherWart field_0003;
   public ScrollableElement field_0000;

   public void renderChestBrightness(Block var1, float var2) {
      GlStateManager.color(var2, var2, var2, 1.0F);
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      TileEntityItemStackRenderer.instance.renderByItem(new ItemStack(var1));
   }
}
