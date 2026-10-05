package net.minecraft.client.renderer.block.statemap;

import com.cheatbreaker.client.nethandler.server.PacketAddHologram;
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.NetHandlerPlayServer$3;
import recovered.unidentified.UnidentifiedClass0520;

public class StateMap extends StateMapperBase {
   public PacketAddHologram field_0003;
   public List<IProperty<?>> ignored;
   public String suffix;
   public NetHandlerPlayServer$3 field_0004;
   public IProperty<?> name;
   public UnidentifiedClass0520 field_0001;

   public StateMap(IProperty<?> var1, String var2, List<IProperty<?>> var3) {
      this.name = var1;
      this.suffix = var2;
      this.ignored = var3;
   }

   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState var1) {
      LinkedHashMap var2 = Maps.newLinkedHashMap(var1.getProperties());
      String var3;
      if (this.name == null) {
         var3 = Block.blockRegistry.getNameForObject(var1.getBlock()).toString();
      } else {
         var3 = ((IProperty<Object>)this.name).getName((Comparable)var2.remove(this.name));
      }

      if (this.suffix != null) {
         var3 = var3 + this.suffix;
      }

      for (IProperty var5 : this.ignored) {
         var2.remove(var5);
      }

      return new ModelResourceLocation(var3, this.getPropertyString(var2));
   }
}
