package net.minecraft.world.gen.structure;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MapGenStructureIO {
   public static Logger logger = LogManager.getLogger();
   public static Map<Class<? extends StructureStart>, String> startClassToNameMap = Maps.newHashMap();
   public static Map<String, Class<? extends StructureStart>> startNameToClassMap = Maps.newHashMap();
   public ContainerChest field_0004;
   public static Map<Class<? extends StructureComponent>, String> componentClassToNameMap = Maps.newHashMap();
   public static Map<String, Class<? extends StructureComponent>> componentNameToClassMap = Maps.newHashMap();
   public GuiScreenWorking field_0006;

   public static StructureComponent getStructureComponent(NBTTagCompound var0, World var1) {
      StructureComponent var2 = null;

      try {
         Class var3 = componentNameToClassMap.get(var0.getString("id"));
         if (var3 != null) {
            var2 = (StructureComponent)var3.newInstance();
         }
      } catch (Exception var4) {
         logger.warn("Failed Piece with id " + var0.getString("id"));
         var4.printStackTrace();
      }

      if (var2 != null) {
         var2.readStructureBaseNBT(var1, var0);
      } else {
         logger.warn("Skipping Piece with id " + var0.getString("id"));
      }

      return var2;
   }

   public static void registerStructure(Class<? extends StructureStart> var0, String var1) {
      startNameToClassMap.put(var1, var0);
      startClassToNameMap.put(var0, var1);
   }

   public static void registerStructureComponent(Class<? extends StructureComponent> var0, String var1) {
      componentNameToClassMap.put(var1, var0);
      componentClassToNameMap.put(var0, var1);
   }

   public static String getStructureComponentName(StructureComponent var0) {
      return componentClassToNameMap.get(var0.getClass());
   }

   static {
      registerStructure(StructureMineshaftStart.class, "Mineshaft");
      registerStructure(MapGenVillage$Start.class, "Village");
      registerStructure(MapGenNetherBridge$Start.class, "Fortress");
      registerStructure(MapGenStronghold$Start.class, "Stronghold");
      registerStructure(MapGenScatteredFeature$Start.class, "Temple");
      registerStructure(StructureOceanMonument$StartMonument.class, "Monument");
      StructureMineshaftPieces.registerStructurePieces();
      StructureVillagePieces.registerVillagePieces();
      StructureNetherBridgePieces.registerNetherFortressPieces();
      StructureStrongholdPieces.registerStrongholdPieces();
      ComponentScatteredFeaturePieces.registerScatteredFeaturePieces();
      StructureOceanMonumentPieces.registerOceanMonumentPieces();
   }

   public static StructureStart getStructureStart(NBTTagCompound var0, World var1) {
      StructureStart var2 = null;

      try {
         Class var3 = startNameToClassMap.get(var0.getString("id"));
         if (var3 != null) {
            var2 = (StructureStart)var3.newInstance();
         }
      } catch (Exception var4) {
         logger.warn("Failed Start with id " + var0.getString("id"));
         var4.printStackTrace();
      }

      if (var2 != null) {
         var2.readStructureComponentsFromNBT(var1, var0);
      } else {
         logger.warn("Skipping Structure with id " + var0.getString("id"));
      }

      return var2;
   }

   public static String getStructureStartName(StructureStart var0) {
      return startClassToNameMap.get(var0.getClass());
   }
}
