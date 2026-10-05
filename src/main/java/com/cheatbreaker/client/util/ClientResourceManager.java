package com.cheatbreaker.client.util;

import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import net.minecraft.util.ResourceLocation;

public class ClientResourceManager {
   public CosmeticType recoveredField149;
   public int recoveredField150;
   public boolean recoveredField151;
   public ResourceLocation recoveredField152;
   public long recoveredField153;
   public ResourceLocation recoveredField154;
   public float recoveredField155;
   public String recoveredField156;
   public String recoveredField157;

   public ResourceLocation method_20850() {
      return this.recoveredField152;
   }

   public long method_20860() {
      return this.recoveredField153;
   }

   public boolean method_20849() {
      return this.recoveredField151;
   }

   public void method_20854(CosmeticType var1) {
      this.recoveredField149 = var1;
   }

   public void method_20857(boolean var1) {
      this.recoveredField151 = var1;
   }

   public ResourceLocation method_20859() {
      return this.recoveredField154;
   }

   public void method_20855(String var1) {
      this.recoveredField157 = var1;
   }

   public void method_20856(ResourceLocation var1) {
      this.recoveredField152 = var1;
   }

   public void method_20852(int var1) {
      this.recoveredField150 = var1;
   }

   public float method_20846() {
      return this.recoveredField155;
   }

   public ClientResourceManager(String var1, int var2, CosmeticType var3) {
      this.recoveredField157 = var1;
      this.recoveredField150 = var2;
      this.recoveredField149 = var3;
   }

   public void method_20853(long var1) {
      this.recoveredField153 = var1;
   }

   public void method_20861(String var1) {
      this.recoveredField156 = var1;
   }

   public ClientResourceManager(long var1, String var3, String var4, CosmeticType var5, float var6, boolean var7, String var8) {
      this.recoveredField153 = var1;
      this.recoveredField157 = var3;
      this.recoveredField156 = var4;
      this.recoveredField149 = var5;
      this.recoveredField155 = var6;
      this.recoveredField151 = var7;
      this.recoveredField154 = new ResourceLocation(var8);
      this.recoveredField152 = new ResourceLocation("client/preview/" + var8.replaceAll("client/", ""));
   }

   public void method_20851(float var1) {
      this.recoveredField155 = var1;
   }

   public int method_20847() {
      return this.recoveredField150;
   }

   public String method_20863() {
      return this.recoveredField157;
   }

   public void method_20862(ResourceLocation var1) {
      this.recoveredField154 = var1;
   }

   public String method_20858() {
      return this.recoveredField156;
   }

   public ClientResourceManager(String var1, String var2, CosmeticType var3, float var4, boolean var5, String var6) {
      this.recoveredField157 = var1;
      this.recoveredField156 = var2;
      this.recoveredField149 = var3;
      this.recoveredField155 = var4;
      this.recoveredField151 = var5;
      this.recoveredField154 = new ResourceLocation(var6);
      this.recoveredField152 = new ResourceLocation("client/preview/" + var6.replace("client/", ""));
   }

   public CosmeticType method_20848() {
      return this.recoveredField149;
   }
}
