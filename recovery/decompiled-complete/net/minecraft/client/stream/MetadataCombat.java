package net.minecraft.client.stream;

import io.netty.util.IllegalReferenceCountException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart$EnumMinecartType;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$1;
import org.java_websocket.framing.FramedataImpl1;

public class MetadataCombat extends Metadata {
   public EntityMinecart$EnumMinecartType field_0002;
   public IllegalReferenceCountException field_0001;
   public ComponentScatteredFeaturePieces$1 field_0003;
   public FramedataImpl1 field_0000;

   public MetadataCombat(EntityLivingBase var1, EntityLivingBase var2) {
      super("player_combat");
      this.func_152808_a("player", var1.z_());
      if (var2 != null) {
         this.func_152808_a("primary_opponent", var2.z_());
      }

      if (var2 != null) {
         this.func_152807_a("Combat between " + var1.z_() + " and " + var2.z_());
      } else {
         this.func_152807_a("Combat between " + var1.z_() + " and others");
      }
   }
}
