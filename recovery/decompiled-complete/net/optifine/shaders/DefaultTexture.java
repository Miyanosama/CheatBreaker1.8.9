package net.optifine.shaders;

import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.command.CommandServerKick;
import net.minecraft.inventory.InventoryHelper;
import org.apache.log4j.or.sax.AttributesRenderer;

public class DefaultTexture extends AbstractTexture {
   public InventoryHelper field_0001;
   public AttributesRenderer field_0002;
   public CommandServerKick field_0000;

   @Override
   public void loadTexture(IResourceManager var1) {
      int[] var2 = ShadersTex.createAIntImage(1, -1);
      ShadersTex.setupTexture(this.getMultiTexID(), var2, 1, 1, false, false);
   }

   public DefaultTexture() {
      this.loadTexture((IResourceManager)null);
   }
}
