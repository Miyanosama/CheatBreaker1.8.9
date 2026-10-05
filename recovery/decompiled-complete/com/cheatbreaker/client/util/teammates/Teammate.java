package com.cheatbreaker.client.util.teammates;

import io.netty.channel.ChannelOutboundBuffer$1;
import io.netty.channel.local.LocalChannel$LocalUnsafe;
import io.netty.channel.oio.AbstractOioChannel$DefaultOioUnsafe;
import java.awt.Color;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import recovered.unidentified.UnidentifiedClass4880;

public class Teammate {
   public long field_0005;
   public ResourceLocation field_0008;
   public Vec3 field_0004;
   public ChannelOutboundBuffer$1 field_0007;
   public UnidentifiedClass4880 field_0001;
   public Color field_0002;
   public AbstractOioChannel$DefaultOioUnsafe field_0009;
   public boolean field_0006 = false;
   public long field_0003;
   public String field_0010;
   public LocalChannel$LocalUnsafe field_0000;

   public void method_04984(double var1, double var3, double var5, long var7) {
      this.field_0004 = new Vec3(var1, var3, var5);
      this.field_0005 = System.currentTimeMillis();
      this.field_0003 = var7;
   }

   public Teammate(String var1, boolean var2, Vec3 var3, long var4, Color var6, long var7) {
      this.field_0010 = var1;
      this.field_0006 = var2;
      this.field_0004 = var3;
      this.field_0005 = var4;
      this.field_0002 = var6;
      this.field_0003 = var7;
   }

   public String method_04983() {
      return this.field_0010;
   }

   public long method_04991() {
      return this.field_0005;
   }

   public long method_04982() {
      return this.field_0003;
   }

   public void method_04985(long var1) {
      this.field_0005 = var1;
   }

   public void method_04989(boolean var1) {
      this.field_0006 = var1;
   }

   public Vec3 getVector3D() {
      return this.field_0004;
   }

   public void method_04986(Color var1) {
      this.field_0002 = var1;
   }

   public Teammate(String var1, boolean var2) {
      this.field_0010 = var1;
      this.field_0006 = var2;
      this.field_0005 = System.currentTimeMillis();
   }

   public Color method_04980() {
      return this.field_0002;
   }

   public boolean method_04981() {
      return this.field_0006;
   }

   public void method_04987(String var1) {
      this.field_0010 = var1;
   }

   public void method_04992(long var1) {
      this.field_0003 = var1;
   }

   public void method_04988(Vec3 var1) {
      this.field_0004 = var1;
   }
}
