package com.cheatbreaker.client.util.worldborder;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.Vec2d;
import java.awt.Color;
import net.minecraft.entity.Entity;

public class WorldBorder {
   public String world;
   public Vec2d recoveredField3485;
   public boolean recoveredField3486;
   public Vec2d recoveredField3487;
   public int recoveredField3488;
   public Vec2d recoveredField3489;
   public String recoveredField3490;
   public boolean recoveredField3491;
   public Vec2d recoveredField3492;
   public Vec2d recoveredField3493;
   public int recoveredField3494;
   public Vec2d recoveredField3495;
   public Color recoveredField3496;
   public WorldBorderManager recoveredField3497;

   public boolean method_20883() {
      return this.recoveredField3486 && this.recoveredField3488 != 0 && this.recoveredField3494 < this.recoveredField3488;
   }

   public double method_20897() {
      return this.recoveredField3495.y;
   }

   public double method_20881() {
      return this.recoveredField3493.y;
   }

   public WorldBorder(
      WorldBorderManager var1, String var2, String var3, int var4, double var5, double var7, double var9, double var11, boolean var13, boolean var14
   ) {
      this.recoveredField3497 = var1;
      this.recoveredField3490 = var2;
      this.world = var3;
      this.recoveredField3496 = new Color(var4, true);
      this.recoveredField3486 = var13;
      this.recoveredField3491 = var14;
      this.recoveredField3493 = this.recoveredField3492 = new Vec2d(var5, var7);
      this.recoveredField3495 = this.recoveredField3485 = new Vec2d(var9, var11);
   }

   public String method_20894() {
      return this.world;
   }

   public static String getPlayer(WorldBorder var0) {
      return var0.recoveredField3490;
   }

   public int method_20876() {
      return this.recoveredField3488;
   }

   public static int method_20887(WorldBorder var0, int var1) {
      var0.recoveredField3494 = var1;
      return var0.recoveredField3494;
   }

   public boolean method_20877() {
      return this.recoveredField3491;
   }

   public boolean method_20884(double var1, double var3) {
      return !this.recoveredField3491
         || !this.world.equals(CheatBreaker.getInstance().getNetHandler().getWorld())
         || var1 + 1.0 > this.method_20890() && var1 < this.method_20889() && var3 + 1.0 > this.method_20881() && var3 < this.method_20897();
   }

   public boolean worldEqualsWorld() {
      return CheatBreaker.getInstance().getNetHandler().getWorld().equals(this.world);
   }

   public Color method_20892() {
      return this.recoveredField3496;
   }

   public static Color method_20899(WorldBorder var0) {
      return var0.recoveredField3496;
   }

   public boolean method_20878() {
      return this.recoveredField3486;
   }

   public void ting() {
      if (this.method_20883()) {
         double var1 = this.recoveredField3492.x - this.recoveredField3489.x;
         double var3 = this.recoveredField3485.x - this.recoveredField3487.x;
         double var5 = this.recoveredField3492.y - this.recoveredField3489.y;
         double var7 = this.recoveredField3485.y - this.recoveredField3487.y;
         double var9 = (float)this.recoveredField3494 / this.recoveredField3488;
         double var11 = this.recoveredField3492.x - var1 * var9;
         double var13 = this.recoveredField3485.x - var3 * var9;
         double var15 = this.recoveredField3492.y - var5 * var9;
         double var17 = this.recoveredField3485.y - var7 * var9;
         this.recoveredField3493 = new Vec2d(var11, var15);
         this.recoveredField3495 = new Vec2d(var13, var17);
         this.recoveredField3494++;
      } else if (this.recoveredField3485 != this.recoveredField3495 || this.recoveredField3492 != this.recoveredField3493) {
         this.recoveredField3492 = this.recoveredField3493;
         this.recoveredField3485 = this.recoveredField3495;
         this.recoveredField3495 = this.recoveredField3487;
         this.recoveredField3493 = this.recoveredField3489;
         this.recoveredField3488 = 0;
         this.recoveredField3494 = 0;
      }
   }

   public static Vec2d method_20888(WorldBorder var0, Vec2d var1) {
      var0.recoveredField3489 = var1;
      return var0.recoveredField3489;
   }

   public double method_20885(Entity var1) {
      return this.method_20898(var1.s, var1.u);
   }

   public Vec2d method_20875() {
      return this.recoveredField3485;
   }

   public double method_20889() {
      return this.recoveredField3495.x;
   }

   public Vec2d method_20891() {
      return this.recoveredField3495;
   }

   public static boolean method_20882(WorldBorder var0) {
      return var0.recoveredField3486;
   }

   public static Vec2d method_20901(WorldBorder var0, Vec2d var1) {
      var0.recoveredField3487 = var1;
      return var0.recoveredField3487;
   }

   public Vec2d method_20880() {
      return this.recoveredField3492;
   }

   public int method_20893() {
      return this.recoveredField3494;
   }

   public double method_20898(double var1, double var3) {
      double var5 = var3 - this.method_20881();
      double var7 = this.method_20897() - var3;
      double var9 = var1 - this.method_20890();
      double var11 = this.method_20889() - var1;
      double var13 = Math.min(var9, var11);
      var13 = Math.min(var13, var5);
      return Math.min(var13, var7);
   }

   public Vec2d method_20896() {
      return this.recoveredField3493;
   }

   public Vec2d method_20874() {
      return this.recoveredField3489;
   }

   public static int method_20900(WorldBorder var0, int var1) {
      var0.recoveredField3488 = var1;
      return var0.recoveredField3488;
   }

   public String method_20879() {
      return this.recoveredField3490;
   }

   public double method_20890() {
      return this.recoveredField3493.x;
   }

   public Vec2d method_20895() {
      return this.recoveredField3487;
   }
}
