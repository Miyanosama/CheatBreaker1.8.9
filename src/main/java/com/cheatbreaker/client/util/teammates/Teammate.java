package com.cheatbreaker.client.util.teammates;

import java.awt.Color;
import net.minecraft.util.Vec3;

public class Teammate {
   public long recoveredField2382;
   public Vec3 recoveredField2383;
   public Color recoveredField2384;
   public boolean recoveredField2385 = false;
   public long recoveredField2386;
   public String recoveredField2387;

   public void method_04984(double var1, double var3, double var5, long var7) {
      this.recoveredField2383 = new Vec3(var1, var3, var5);
      this.recoveredField2382 = System.currentTimeMillis();
      this.recoveredField2386 = var7;
   }

   public Teammate(String var1, boolean var2, Vec3 var3, long var4, Color var6, long var7) {
      this.recoveredField2387 = var1;
      this.recoveredField2385 = var2;
      this.recoveredField2383 = var3;
      this.recoveredField2382 = var4;
      this.recoveredField2384 = var6;
      this.recoveredField2386 = var7;
   }

   public String method_04983() {
      return this.recoveredField2387;
   }

   public long method_04991() {
      return this.recoveredField2382;
   }

   public long method_04982() {
      return this.recoveredField2386;
   }

   public void method_04985(long var1) {
      this.recoveredField2382 = var1;
   }

   public void method_04989(boolean var1) {
      this.recoveredField2385 = var1;
   }

   public Vec3 getVector3D() {
      return this.recoveredField2383;
   }

   public void method_04986(Color var1) {
      this.recoveredField2384 = var1;
   }

   public Teammate(String var1, boolean var2) {
      this.recoveredField2387 = var1;
      this.recoveredField2385 = var2;
      this.recoveredField2382 = System.currentTimeMillis();
   }

   public Color method_04980() {
      return this.recoveredField2384;
   }

   public boolean method_04981() {
      return this.recoveredField2385;
   }

   public void method_04987(String var1) {
      this.recoveredField2387 = var1;
   }

   public void method_04992(long var1) {
      this.recoveredField2386 = var1;
   }

   public void method_04988(Vec3 var1) {
      this.recoveredField2383 = var1;
   }
}
