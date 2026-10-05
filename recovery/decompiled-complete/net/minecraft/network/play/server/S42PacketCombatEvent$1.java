package net.minecraft.network.play.server;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityList$EntityEggInfo;
import net.minecraft.entity.ai.EntityAILeapAtTarget;

// $VF: synthetic class
public class S42PacketCombatEvent$1 {
   public I18n field_0001;
   public EntityAILeapAtTarget field_0003;
   public EntityList$EntityEggInfo field_0000;

   static {
      try {
         field_179944_a[S42PacketCombatEvent$Event.END_COMBAT.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_179944_a[S42PacketCombatEvent$Event.ENTITY_DIED.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
