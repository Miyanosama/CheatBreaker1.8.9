package net.minecraft.scoreboard;

import io.netty.util.concurrent.SingleThreadEventExecutor;
import java.util.Collection;
import javax.vecmath.GMatrix;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$RoomDefinition;
import recovered.unidentified.UnidentifiedClass4396;

public abstract class Team {
   public GMatrix field_0002;
   public SingleThreadEventExecutor field_0003;
   public StructureOceanMonumentPieces$RoomDefinition field_0000;
   public UnidentifiedClass4396 field_0001;

   public abstract String getRegisteredName();

   public abstract String formatString(String var1);

   public abstract boolean getSeeFriendlyInvisiblesEnabled();

   public abstract boolean getAllowFriendlyFire();

   public abstract Team$EnumVisible getDeathMessageVisibility();

   public boolean isSameTeam(Team var1) {
      return var1 == null ? false : this == var1;
   }

   public abstract Collection<String> getMembershipCollection();

   public abstract Team$EnumVisible getNameTagVisibility();
}
