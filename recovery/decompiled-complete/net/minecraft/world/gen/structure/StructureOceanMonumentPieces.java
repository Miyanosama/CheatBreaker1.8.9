package net.minecraft.world.gen.structure;

import recovered.unidentified.UnidentifiedClass1675;

public class StructureOceanMonumentPieces {
   public static void registerOceanMonumentPieces() {
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$MonumentBuilding.class, "OMB");
      MapGenStructureIO.registerStructureComponent(UnidentifiedClass1675.class, "OMCR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$DoubleXRoom.class, "OMDXR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$DoubleXYRoom.class, "OMDXYR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$DoubleYRoom.class, "OMDYR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$DoubleYZRoom.class, "OMDYZR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$DoubleZRoom.class, "OMDZR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$EntryRoom.class, "OMEntry");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$Penthouse.class, "OMPenthouse");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$SimpleRoom.class, "OMSimple");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$SimpleTopRoom.class, "OMSimpleT");
   }
}
