package net.optifine.config;

import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.util.internal.Cleaner0;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.renderer.GlStateManager$StencilState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.optifine.util.TextureUtils;
import org.apache.log4j.xml.XMLWatchdog;

public class ItemLocator implements IObjectLocator {
   public ObjectDecoder field_0003;
   public XMLWatchdog field_0005;
   public BlockFenceGate field_0002;
   public Cleaner0 field_0004;
   public TextureUtils field_0000;
   public GlStateManager$StencilState field_0001;

   @Override
   public Object getObject(ResourceLocation var1) {
      return Item.getByNameOrId(var1.toString());
   }
}
