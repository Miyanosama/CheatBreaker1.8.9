package net.minecraft.client.renderer.block.model;

import com.cheatbreaker.client.ui.util.RenderUtil;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.minecraft.command.CommandPlaySound;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.server.management.ServerConfigurationManager$1;

public class ModelBlockDefinition {
   public Map<String, ModelBlockDefinition$Variants> mapVariants = Maps.newHashMap();
   public EntityLightningBolt field_0005;
   public static Gson GSON = new GsonBuilder()
      .registerTypeAdapter(ModelBlockDefinition.class, new ModelBlockDefinition$Deserializer())
      .registerTypeAdapter(ModelBlockDefinition$Variant.class, new ModelBlockDefinition$Variant$Deserializer())
      .create();
   public ServerConfigurationManager$1 field_0004;
   public RenderUtil field_0000;
   public CommandPlaySound field_0001;

   public ModelBlockDefinition(List<ModelBlockDefinition> var1) {
      for (ModelBlockDefinition var3 : var1) {
         this.mapVariants.putAll(var3.mapVariants);
      }
   }

   public ModelBlockDefinition$Variants getVariants(String var1) {
      ModelBlockDefinition$Variants var2 = this.mapVariants.get(var1);
      if (var2 == null) {
         throw new ModelBlockDefinition$MissingVariantException(this);
      } else {
         return var2;
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof ModelBlockDefinition) {
         ModelBlockDefinition var2 = (ModelBlockDefinition)var1;
         return this.mapVariants.equals(var2.mapVariants);
      } else {
         return false;
      }
   }

   public ModelBlockDefinition(Collection<ModelBlockDefinition$Variants> var1) {
      for (ModelBlockDefinition$Variants var3 : var1) {
         this.mapVariants.put(ModelBlockDefinition$Variants.access$000(var3), var3);
      }
   }

   @Override
   public int hashCode() {
      return this.mapVariants.hashCode();
   }

   public static ModelBlockDefinition parseFromReader(Reader var0) {
      return (ModelBlockDefinition)GSON.fromJson(var0, ModelBlockDefinition.class);
   }
}
