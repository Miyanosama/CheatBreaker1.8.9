package com.cheatbreaker.client.util.worldborder;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.Vec2d;
import java.awt.Color;
import net.minecraft.entity.Entity;
import org.apache.log4j.LogXF;

public class WorldBorder {
   public String world;
   public Vec2d field_0012;
   public boolean field_0005;
   public Vec2d field_0011;
   public int field_0001;
   public Vec2d field_0002;
   public LogXF field_0013;
   public String field_0009;
   public boolean field_0003;
   public Vec2d field_0014;
   public Vec2d field_0000;
   public int field_0007;
   public Vec2d field_0008;
   public Color field_0004;
   public WorldBorderManager field_0010;

   public boolean method_20883() {
      return this.field_0005 && this.field_0001 != 0 && this.field_0007 < this.field_0001;
   }

   public double method_20897() {
      return this.field_0008.y;
   }

   public double method_20881() {
      return this.field_0000.y;
   }

   public WorldBorder(
      WorldBorderManager var1, String var2, String var3, int var4, double var5, double var7, double var9, double var11, boolean var13, boolean var14
   ) {
      this.field_0010 = var1;
      this.field_0009 = var2;
      this.world = var3;
      this.field_0004 = new Color(var4, true);
      this.field_0005 = var13;
      this.field_0003 = var14;
      this.field_0000 = this.field_0014 = new Vec2d(var5, var7);
      this.field_0008 = this.field_0012 = new Vec2d(var9, var11);
   }

   public String method_20894() {
      return this.world;
   }

   public static String getPlayer(WorldBorder var0) {
      return var0.field_0009;
   }

   public int method_20876() {
      return this.field_0001;
   }

   public static int method_20887(WorldBorder var0, int var1) {
      var0.field_0007 = var1;
      return var0.field_0007;
   }

   public boolean method_20877() {
      return this.field_0003;
   }

   public boolean method_20884(double var1, double var3) {
      return !this.field_0003
         || !this.world.equals(CheatBreaker.getInstance().getNetHandler().getWorld())
         || var1 + 1.0 > this.method_20890() && var1 < this.method_20889() && var3 + 1.0 > this.method_20881() && var3 < this.method_20897();
   }

   public boolean worldEqualsWorld() {
      return CheatBreaker.getInstance().getNetHandler().getWorld().equals(this.world);
   }

   public Color method_20892() {
      return this.field_0004;
   }

   public static Color method_20899(WorldBorder var0) {
      return var0.field_0004;
   }

   public boolean method_20878() {
      return this.field_0005;
   }

   public void ting() {
      if (this.method_20883()) {
         double var1 = this.field_0014.x - this.field_0002.x;
         double var3 = this.field_0012.x - this.field_0011.x;
         double var5 = this.field_0014.y - this.field_0002.y;
         double var7 = this.field_0012.y - this.field_0011.y;
         double var9 = (float)this.field_0007 / this.field_0001;
         double var11 = this.field_0014.x - var1 * var9;
         double var13 = this.field_0012.x - var3 * var9;
         double var15 = this.field_0014.y - var5 * var9;
         double var17 = this.field_0012.y - var7 * var9;
         this.field_0000 = new Vec2d(var11, var15);
         this.field_0008 = new Vec2d(var13, var17);
         this.field_0007++;
      } else if (this.field_0012 != this.field_0008 || this.field_0014 != this.field_0000) {
         this.field_0014 = this.field_0000;
         this.field_0012 = this.field_0008;
         this.field_0008 = this.field_0011;
         this.field_0000 = this.field_0002;
         this.field_0001 = 0;
         this.field_0007 = 0;
      }
   }

   public static Vec2d method_20888(WorldBorder var0, Vec2d var1) {
      var0.field_0002 = var1;
      return var0.field_0002;
   }

   public double method_20885(Entity var1) {
      return this.method_20898(var1.s, var1.u);
   }

   public Vec2d method_20875() {
      return this.field_0012;
   }

   public double method_20889() {
      return this.field_0008.x;
   }

   public Vec2d method_20891() {
      return this.field_0008;
   }

   public static boolean method_20882(WorldBorder var0) {
      return var0.field_0005;
   }

   public static Vec2d method_20901(WorldBorder var0, Vec2d var1) {
      var0.field_0011 = var1;
      return var0.field_0011;
   }

   public Vec2d method_20880() {
      return this.field_0014;
   }

   public int method_20893() {
      return this.field_0007;
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
      return this.field_0000;
   }

   public Vec2d method_20874() {
      return this.field_0002;
   }

   public static int method_20900(WorldBorder var0, int var1) {
      var0.field_0001 = var1;
      return var0.field_0001;
   }

   public String method_20879() {
      return this.field_0009;
   }

   public double method_20890() {
      return this.field_0000.x;
   }

   public Vec2d method_20895() {
      return this.field_0011;
   }
}
