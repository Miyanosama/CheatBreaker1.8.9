package com.cheatbreaker.client.util;

import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.optifine.entity.model.ModelAdapterCaveSpider;
import net.optifine.util.CacheObjectArray;
import org.apache.log4j.pattern.BridgePatternParser;

public enum SessionServer$Status {
   field_0007("yellow"),
   UNKNOWN("unknown"),
   field_0005("red"),
   field_0009("green");
   // $VF: synthetic field
   public static SessionServer$Status[] field_0004 = new SessionServer$Status[]{
      SessionServer$Status.field_0009, SessionServer$Status.field_0005, SessionServer$Status.field_0007, SessionServer$Status.UNKNOWN
   };
   public StructureOceanMonumentPieces field_0003;
   public ModelAdapterCaveSpider field_0006;
   public CacheObjectArray field_0001;
   public String field_0008;
   public BridgePatternParser field_0002;

   public String method_21555() {
      return this.field_0008;
   }

   public static SessionServer$Status getStatusByName(String var0) {
      for (SessionServer$Status var4 : values()) {
         if (var4.method_21555().equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return null;
   }

   public SessionServer$Status(String var3) {
      this.field_0008 = var3;
   }
}
