package net.minecraft.world;

import net.minecraft.stats.StatCrafting;
import net.minecraft.village.VillageDoorInfo;
import net.minecraft.world.border.IBorderListener;
import net.minecraft.world.border.WorldBorder;
import org.apache.log4j.rewrite.PropertyRewritePolicy;

public class WorldServerMulti$1 implements IBorderListener {
   public StatCrafting field_0001;
   public PropertyRewritePolicy field_0000;
   public VillageDoorInfo field_0002;

   @Override
   public void onSizeChanged(WorldBorder var1, double var2) {
      this.field_177698_a.af().setTransition(var2);
   }

   @Override
   public void onTransitionStarted(WorldBorder var1, double var2, double var4, long var6) {
      this.field_177698_a.af().setTransition(var2, var4, var6);
   }

   @Override
   public void onWarningDistanceChanged(WorldBorder var1, int var2) {
      this.field_177698_a.af().setWarningDistance(var2);
   }

   public WorldServerMulti$1(WorldServerMulti var1) {
      this.field_177698_a = var1;
      super();
   }

   @Override
   public void onCenterChanged(WorldBorder var1, double var2, double var4) {
      this.field_177698_a.af().setCenter(var2, var4);
   }

   @Override
   public void method_02434(WorldBorder var1, double var2) {
      this.field_177698_a.af().setDamageAmount(var2);
   }

   @Override
   public void onWarningTimeChanged(WorldBorder var1, int var2) {
      this.field_177698_a.af().setWarningTime(var2);
   }

   @Override
   public void method_02429(WorldBorder var1, double var2) {
      this.field_177698_a.af().setDamageBuffer(var2);
   }
}
