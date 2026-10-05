package net.minecraft.world.border;

public interface IBorderListener {
   void onTransitionStarted(WorldBorder var1, double var2, double var4, long var6);

   void onCenterChanged(WorldBorder var1, double var2, double var4);

   void onWarningDistanceChanged(WorldBorder var1, int var2);

   void onWarningTimeChanged(WorldBorder var1, int var2);

   void method_02434(WorldBorder var1, double var2);

   void onSizeChanged(WorldBorder var1, double var2);

   void method_02429(WorldBorder var1, double var2);
}
