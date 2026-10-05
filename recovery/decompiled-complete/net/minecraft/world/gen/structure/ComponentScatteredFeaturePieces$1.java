package net.minecraft.world.gen.structure;

import net.minecraft.client.gui.spectator.categories.SpectatorDetails;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class ComponentScatteredFeaturePieces$1 {
   public SpectatorDetails field_0001;
   public ModelBlockDefinition field_0003;
   public PlayerManager field_0002;

   static {
      try {
         field_175956_a[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_175956_a[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
