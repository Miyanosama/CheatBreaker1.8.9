package net.minecraft.crash;

import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$RoomCrossing;

public class CrashReportCategory$Entry {
   public StructureStrongholdPieces$RoomCrossing field_0001;
   public String value;
   public EntityDamageSource field_0000;
   public String key;

   public String getKey() {
      return this.key;
   }

   public String getValue() {
      return this.value;
   }

   public CrashReportCategory$Entry(String var1, Object var2) {
      this.key = var1;
      if (var2 == null) {
         this.value = "~~NULL~~";
      } else if (var2 instanceof Throwable) {
         Throwable var3 = (Throwable)var2;
         this.value = "~~ERROR~~ " + var3.getClass().getSimpleName() + ": " + var3.getMessage();
      } else {
         this.value = var2.toString();
      }
   }
}
