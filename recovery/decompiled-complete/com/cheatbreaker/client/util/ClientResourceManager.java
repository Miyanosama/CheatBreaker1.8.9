package com.cheatbreaker.client.util;

import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import io.netty.bootstrap.AbstractBootstrap$BootstrapChannelFactory;
import junit.swingui.TestRunner;
import net.minecraft.block.BlockDoor$EnumHingePosition;
import net.minecraft.util.ResourceLocation;
import net.optifine.BetterSnow;
import org.json.JSONException;

public class ClientResourceManager {
   public AbstractBootstrap$BootstrapChannelFactory field_0006;
   public CosmeticType field_0011;
   public int field_0005;
   public JSONException field_0010;
   public boolean field_0001;
   public ResourceLocation field_0002;
   public BlockDoor$EnumHingePosition field_0012;
   public long field_0009;
   public ResourceLocation field_0003;
   public float field_0013;
   public String field_0000;
   public TestRunner field_0007;
   public BetterSnow field_0008;
   public String field_0004;

   public ResourceLocation method_20850() {
      return this.field_0002;
   }

   public long method_20860() {
      return this.field_0009;
   }

   public boolean method_20849() {
      return this.field_0001;
   }

   public void method_20854(CosmeticType var1) {
      this.field_0011 = var1;
   }

   public void method_20857(boolean var1) {
      this.field_0001 = var1;
   }

   public ResourceLocation method_20859() {
      return this.field_0003;
   }

   public void method_20855(String var1) {
      this.field_0004 = var1;
   }

   public void method_20856(ResourceLocation var1) {
      this.field_0002 = var1;
   }

   public void method_20852(int var1) {
      this.field_0005 = var1;
   }

   public float method_20846() {
      return this.field_0013;
   }

   public ClientResourceManager(String var1, int var2, CosmeticType var3) {
      this.field_0004 = var1;
      this.field_0005 = var2;
      this.field_0011 = var3;
   }

   public void method_20853(long var1) {
      this.field_0009 = var1;
   }

   public void method_20861(String var1) {
      this.field_0000 = var1;
   }

   public ClientResourceManager(long var1, String var3, String var4, CosmeticType var5, float var6, boolean var7, String var8) {
      this.field_0009 = var1;
      this.field_0004 = var3;
      this.field_0000 = var4;
      this.field_0011 = var5;
      this.field_0013 = var6;
      this.field_0001 = var7;
      this.field_0003 = new ResourceLocation(var8);
      this.field_0002 = new ResourceLocation("client/preview/" + var8.replaceAll("client/", ""));
   }

   public void method_20851(float var1) {
      this.field_0013 = var1;
   }

   public int method_20847() {
      return this.field_0005;
   }

   public String method_20863() {
      return this.field_0004;
   }

   public void method_20862(ResourceLocation var1) {
      this.field_0003 = var1;
   }

   public String method_20858() {
      return this.field_0000;
   }

   public ClientResourceManager(String var1, String var2, CosmeticType var3, float var4, boolean var5, String var6) {
      this.field_0004 = var1;
      this.field_0000 = var2;
      this.field_0011 = var3;
      this.field_0013 = var4;
      this.field_0001 = var5;
      this.field_0003 = new ResourceLocation(var6);
      this.field_0002 = new ResourceLocation("client/preview/" + var6.replace("client/", ""));
   }

   public CosmeticType method_20848() {
      return this.field_0011;
   }
}
