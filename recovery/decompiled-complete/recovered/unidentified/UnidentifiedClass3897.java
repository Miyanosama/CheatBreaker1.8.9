package recovered.unidentified;

import io.netty.handler.codec.Delimiters;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.EntityRenderer$4;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.creativetab.CreativeTabs;

public class UnidentifiedClass3897 extends Block {
   public EntityRenderer$4 field_0001;
   public Delimiters field_0002;
   public Framebuffer field_0000;

   public UnidentifiedClass3897() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return MapColor.netherrackColor;
   }
}
