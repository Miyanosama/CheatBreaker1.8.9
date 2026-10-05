package net.minecraft.client.renderer.block.statemap;

import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager$StencilFunc;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.resources.model.ModelResourceLocation;

public abstract class StateMapperBase implements IStateMapper {
   public Map<IBlockState, ModelResourceLocation> mapStateModelLocations = Maps.newLinkedHashMap();
   public LayerBipedArmor field_0000;
   public GlStateManager$StencilFunc field_0002;

   public abstract ModelResourceLocation getModelResourceLocation(IBlockState var1);

   public String getPropertyString(Map<IProperty, Comparable> var1) {
      StringBuilder var2 = new StringBuilder();

      for (Entry var4 : var1.entrySet()) {
         if (var2.length() != 0) {
            var2.append(",");
         }

         IProperty var5 = (IProperty)var4.getKey();
         Comparable var6 = (Comparable)var4.getValue();
         var2.append(var5.getName());
         var2.append("=");
         var2.append(var5.getName(var6));
      }

      if (var2.length() == 0) {
         var2.append("normal");
      }

      return var2.toString();
   }

   @Override
   public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block var1) {
      UnmodifiableIterator var2 = var1.P().getValidStates().iterator();

      while (var2.hasNext()) {
         IBlockState var3 = (IBlockState)var2.next();
         this.mapStateModelLocations.put(var3, this.getModelResourceLocation(var3));
      }

      return this.mapStateModelLocations;
   }
}
