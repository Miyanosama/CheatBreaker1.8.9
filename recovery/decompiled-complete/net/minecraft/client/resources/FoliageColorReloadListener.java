package net.minecraft.client.resources;

import com.cheatbreaker.client.ui.element.type.ColorPickerElement;
import java.io.IOException;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.scoreboard.GoalColor;
import net.minecraft.server.network.NetHandlerLoginServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ColorizerFoliage;

public class FoliageColorReloadListener implements IResourceManagerReloadListener {
   public GoalColor field_0001;
   public ColorPickerElement field_0003;
   public static ResourceLocation LOC_FOLIAGE_PNG = new ResourceLocation("textures/colormap/foliage.png");
   public NetHandlerLoginServer field_0002;

   @Override
   public void onResourceManagerReload(IResourceManager var1) {
      try {
         ColorizerFoliage.setFoliageBiomeColorizer(TextureUtil.readImageData(var1, LOC_FOLIAGE_PNG));
      } catch (IOException var3) {
      }
   }
}
