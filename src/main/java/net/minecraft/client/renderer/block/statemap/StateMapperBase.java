package net.minecraft.client.renderer.block.statemap;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;

public abstract class StateMapperBase implements IStateMapper {
   public Map<IBlockState, ModelResourceLocation> mapStateModelLocations = Maps.newLinkedHashMap();

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
      for (IBlockState var3 : var1.P().getValidStates()) {
         this.mapStateModelLocations.put(var3, this.getModelResourceLocation(var3));
      }

      return this.mapStateModelLocations;
   }
}
