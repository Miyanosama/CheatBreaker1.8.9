package net.optifine.entity.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.src.Config;

public class CustomModelRegistry {
   public static Map<String, ModelAdapter> mapModelAdapters = makeMapModelAdapters();

   public static ModelAdapter getModelAdapter(String var0) {
      return mapModelAdapters.get(var0);
   }

   public static Map<String, ModelAdapter> makeMapModelAdapters() {
      LinkedHashMap var0 = new LinkedHashMap();
      addModelAdapter(var0, new ModelAdapterArmorStand());
      addModelAdapter(var0, new ModelAdapterBat());
      addModelAdapter(var0, new ModelAdapterBlaze());
      addModelAdapter(var0, new ModelAdapterBoat());
      addModelAdapter(var0, new ModelAdapterCaveSpider());
      addModelAdapter(var0, new ModelAdapterChicken());
      addModelAdapter(var0, new ModelAdapterCow());
      addModelAdapter(var0, new ModelAdapterCreeper());
      addModelAdapter(var0, new ModelAdapterDragon());
      addModelAdapter(var0, new ModelAdapterEnderCrystal());
      addModelAdapter(var0, new ModelAdapterEnderman());
      addModelAdapter(var0, new ModelAdapterEndermite());
      addModelAdapter(var0, new ModelAdapterGhast());
      addModelAdapter(var0, new ModelAdapterGuardian());
      addModelAdapter(var0, new ModelAdapterHorse());
      addModelAdapter(var0, new ModelAdapterIronGolem());
      addModelAdapter(var0, new ModelAdapterLeadKnot());
      addModelAdapter(var0, new ModelAdapterMagmaCube());
      addModelAdapter(var0, new ModelAdapterMinecart());
      addModelAdapter(var0, new ModelAdapterMinecartTnt());
      addModelAdapter(var0, new ModelAdapterMinecartMobSpawner());
      addModelAdapter(var0, new ModelAdapterMooshroom());
      addModelAdapter(var0, new ModelAdapterOcelot());
      addModelAdapter(var0, new ModelAdapterPig());
      addModelAdapter(var0, new ModelAdapterPigZombie());
      addModelAdapter(var0, new ModelAdapterRabbit());
      addModelAdapter(var0, new ModelAdapterSheep());
      addModelAdapter(var0, new ModelAdapterSilverfish());
      addModelAdapter(var0, new ModelAdapterSkeleton());
      addModelAdapter(var0, new ModelAdapterSlime());
      addModelAdapter(var0, new ModelAdapterSnowman());
      addModelAdapter(var0, new ModelAdapterSpider());
      addModelAdapter(var0, new ModelAdapterSquid());
      addModelAdapter(var0, new ModelAdapterVillager());
      addModelAdapter(var0, new ModelAdapterWitch());
      addModelAdapter(var0, new ModelAdapterWither());
      addModelAdapter(var0, new ModelAdapterWitherSkull());
      addModelAdapter(var0, new ModelAdapterWolf());
      addModelAdapter(var0, new ModelAdapterZombie());
      addModelAdapter(var0, new ModelAdapterSheepWool());
      addModelAdapter(var0, new ModelAdapterBanner());
      addModelAdapter(var0, new ModelAdapterBook());
      addModelAdapter(var0, new ModelAdapterChest());
      addModelAdapter(var0, new ModelAdapterChestLarge());
      addModelAdapter(var0, new ModelAdapterEnderChest());
      addModelAdapter(var0, new ModelAdapterHeadHumanoid());
      addModelAdapter(var0, new ModelAdapterHeadSkeleton());
      addModelAdapter(var0, new ModelAdapterSign());
      return var0;
   }

   public static void addModelAdapter(Map<String, ModelAdapter> var0, ModelAdapter var1, String var2) {
      if (var0.containsKey(var2)) {
         Config.warn("Model adapter already registered for id: " + var2 + ", class: " + var1.getEntityClass().getName());
      }

      var0.put(var2, var1);
   }

   public static void addModelAdapter(Map<String, ModelAdapter> var0, ModelAdapter var1) {
      addModelAdapter(var0, var1, var1.getName());
      String[] var2 = var1.getAliases();
      if (var2 != null) {
         for (int var3 = 0; var3 < var2.length; var3++) {
            String var4 = var2[var3];
            addModelAdapter(var0, var1, var4);
         }
      }

      ModelBase var8 = var1.makeModel();
      String[] var9 = var1.getModelRendererNames();

      for (int var5 = 0; var5 < var9.length; var5++) {
         String var6 = var9[var5];
         ModelRenderer var7 = var1.getModelRenderer(var8, var6);
         if (var7 == null) {
            Config.warn("Model renderer not found, model: " + var1.getName() + ", name: " + var6);
         }
      }
   }

   public static String[] getModelNames() {
      Set var0 = mapModelAdapters.keySet();
      return (java.lang.String[])var0.toArray(new String[var0.size()]);
   }
}
