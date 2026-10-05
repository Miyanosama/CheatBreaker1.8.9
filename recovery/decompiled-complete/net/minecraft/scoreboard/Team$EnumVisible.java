package net.minecraft.scoreboard;

import com.google.common.collect.Maps;
import java.util.Map;
import javazoom.jl.decoder.BitReserve;
import javazoom.jl.decoder.Crc16;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$XYDoubleRoomFitHelper;

public enum Team$EnumVisible {
   ALWAYS("always", 0),
   HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3),
   HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
   NEVER("never", 1);
   public String internalName;
   public Crc16 field_0009;
   public StructureOceanMonumentPieces$XYDoubleRoomFitHelper field_0004;
   public BitReserve field_0001;
   public int id;
   public EntityAuraFX field_0010;
   public static Map<String, Team$EnumVisible> field_178828_g = Maps.newHashMap();
   // $VF: synthetic field
   public static Team$EnumVisible[] $VALUES = new Team$EnumVisible[]{ALWAYS, Team$EnumVisible.NEVER, HIDE_FOR_OTHER_TEAMS, HIDE_FOR_OWN_TEAM};

   public Team$EnumVisible(String var3, int var4) {
      this.internalName = var3;
      this.id = var4;
   }

   static {
      for (Team$EnumVisible var3 : values()) {
         field_178828_g.put(var3.internalName, var3);
      }
   }

   public static String[] func_178825_a() {
      return field_178828_g.keySet().toArray(new String[field_178828_g.size()]);
   }

   public static Team$EnumVisible func_178824_a(String var0) {
      return field_178828_g.get(var0);
   }
}
