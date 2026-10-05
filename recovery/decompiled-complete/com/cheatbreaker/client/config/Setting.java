package com.cheatbreaker.client.config;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.type.AnimationsModule;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import io.netty.channel.sctp.nio.NioSctpChannel$1;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import recovered.unidentified.UnidentifiedClass4855;

public class Setting {
   public boolean field_0013;
   public BooleanSupplier field_0025;
   public AbstractModule container;
   public NioSctpChannel$1 field_0022;
   public String field_0006;
   public boolean field_0007;
   public boolean field_0026;
   public int[] colorArray;
   public String[] acceptedValues;
   public int field_0027;
   public Object[] field_0005;
   public Object field_0014;
   public String field_0017;
   public Object field_0010;
   public Consumer<Object> valueConsumer;
   public String field_0024;
   public String field_0003 = "";
   public boolean field_0009;
   public boolean field_0015;
   public String field_0023;
   public String field_0002;
   public String field_0001;
   public Object field_0004;
   public Setting parent;
   public Object field_0021;
   public String field_0016;
   public UnidentifiedClass4855 field_0019;
   public Object field_0011;

   public Setting setParent(Setting var1) {
      if (var1.getType() != Setting$Type.BOOLEAN) {
         throw new IllegalStateException("Parent can only be boolean.");
      } else {
         this.parent = var1;
         return this;
      }
   }

   public Setting getParent() {
      return this.parent;
   }

   public Setting(AbstractModule var1, String var2) {
      this.field_0024 = "";
      this.field_0023 = "";
      this.field_0002 = "";
      this.field_0006 = "";
      this.field_0017 = "OFF";
      this.field_0016 = "ON";
      this.field_0007 = true;
      this.container = var1;
      var1.getSettingsList().add(this);
      this.field_0001 = var2;
   }

   public int method_08912() {
      return (Integer)this.field_0014;
   }

   public Setting method_08896(boolean var1) {
      this.field_0007 = var1;
      return this;
   }

   public void method_08918(boolean var1) {
      this.field_0009 = var1;
   }

   public Object[] method_08883() {
      return this.field_0005;
   }

   public boolean method_08908() {
      return !(this.container instanceof AnimationsModule) ? (Boolean)this.field_0014 : this.container.isEnabled() && (Boolean)this.field_0014;
   }

   public Setting acceptedValues(String... var1) {
      this.acceptedValues = var1;
      return this;
   }

   public Setting setValue(Object var1) {
      return this.setValue(var1, true);
   }

   public Setting$Type getType() {
      if (this.field_0014.getClass().isAssignableFrom(Boolean.class)) {
         return Setting$Type.BOOLEAN;
      } else if (this.field_0014.getClass().isAssignableFrom(ArrayList.class) && this.field_0005 != null && this.field_0005.length != 0) {
         return Setting$Type.field_0007;
      } else if (!this.field_0014.getClass().isAssignableFrom(String.class)) {
         if (this.field_0014.getClass().isAssignableFrom(Float.class)) {
            return Setting$Type.field_0001;
         } else if (this.field_0014.getClass().isAssignableFrom(Double.class)) {
            return Setting$Type.field_0006;
         } else if (this.field_0014.getClass().isAssignableFrom(String[].class)) {
            return Setting$Type.field_0003;
         } else {
            return this.field_0014.getClass().isAssignableFrom(Integer.class) ? Setting$Type.field_0002 : null;
         }
      } else {
         return this.acceptedValues != null && this.acceptedValues.length != 0 ? Setting$Type.field_0003 : Setting$Type.field_0013;
      }
   }

   public String method_08875() {
      return this.field_0006;
   }

   public Setting method_08887(int var1) {
      this.field_0027 = var1;
      this.field_0009 = true;
      return this;
   }

   public String method_08919() {
      return this.field_0017;
   }

   public Object method_08904() {
      return this.field_0021;
   }

   public Setting method_08914(Object var1) {
      this.field_0004 = var1;
      return this;
   }

   public Setting method_08897(Object... var1) {
      this.field_0005 = var1;
      return this;
   }

   public boolean method_08876() {
      return this.field_0007;
   }

   public BooleanSupplier method_08921() {
      return this.field_0025;
   }

   public String method_08872() {
      return this.field_0023;
   }

   public Setting method_08892(String var1) {
      this.field_0024 = var1;
      return this;
   }

   public String[] getAcceptedValues() {
      return this.acceptedValues;
   }

   public Setting method_08893(String var1, String var2) {
      this.field_0002 = var1;
      this.field_0006 = var2;
      return this;
   }

   public Consumer<Object> method_08902() {
      return this.valueConsumer;
   }

   public Setting onChange(Consumer<Object> var1) {
      this.valueConsumer = var1;
      return this;
   }

   public boolean method_08880() {
      return this.field_0013;
   }

   public boolean method_08906() {
      return this.parent != null && (Boolean)this.parent.getValue();
   }

   public String method_08911() {
      return this.field_0001;
   }

   public String method_08870() {
      return this.field_0024;
   }

   public boolean method_08879() {
      return this.field_0015;
   }

   public Setting method_08915(String var1) {
      this.field_0023 = var1;
      return this;
   }

   public Setting method_08917(Consumer<Object> var1) {
      if (Minecraft.getMinecraft().isFullScreen() && Minecraft.getMinecraft().currentScreen instanceof CBModulesGui) {
         this.valueConsumer = var1;
      }

      return this;
   }

   public Setting setMinMax(Number var1, Number var2) {
      this.field_0021 = var1;
      this.field_0011 = var2;
      return this;
   }

   public Setting(List<Setting> var1, String var2, String var3) {
      this.field_0024 = "";
      this.field_0023 = "";
      this.field_0002 = "";
      this.field_0006 = "";
      this.field_0017 = "OFF";
      this.field_0016 = "ON";
      this.field_0007 = true;
      var1.add(this);
      this.field_0001 = var2;
      this.field_0003 = var3;
   }

   public Object getValue() {
      return this.field_0014;
   }

   public int[] method_08910() {
      return this.colorArray;
   }

   public Setting method_08884(Object var1) {
      this.field_0010 = var1;
      return this;
   }

   public Setting(AbstractModule var1, String var2, String var3) {
      this.field_0024 = "";
      this.field_0023 = "";
      this.field_0002 = "";
      this.field_0006 = "";
      this.field_0017 = "OFF";
      this.field_0016 = "ON";
      this.field_0007 = true;
      this.container = var1;
      var1.getSettingsList().add(this);
      this.field_0001 = var2;
      this.field_0003 = var3;
   }

   public ArrayList method_08869() {
      return (ArrayList)this.field_0014;
   }

   public Setting(List<Setting> var1, String var2) {
      this.field_0024 = "";
      this.field_0023 = "";
      this.field_0002 = "";
      this.field_0006 = "";
      this.field_0017 = "OFF";
      this.field_0016 = "ON";
      this.field_0007 = true;
      var1.add(this);
      this.field_0001 = var2;
   }

   public String method_08868() {
      return this.field_0003;
   }

   public void method_08885(boolean var1) {
      this.field_0015 = var1;
   }

   public String method_08871() {
      return this.field_0016;
   }

   public boolean method_08867() {
      return this.field_0009;
   }

   public boolean method_08907() {
      return this.field_0026;
   }

   public int method_08901() {
      if (this.field_0013) {
         Integer var1 = (Integer)this.field_0014;
         int var2 = var1 >> 24 & 0xFF;
         float var3 = (float)System.nanoTime() / 1.0E10F % 1.0F;
         return this.field_0026
            ? var2 << 24 | Color.HSBtoRGB((float)(System.currentTimeMillis() % (285230057L & 3419114L)) / 1000.0F, 1.0F, 1.0F) & 16777215
            : var2 << 24 | Color.HSBtoRGB(var3, 1.0F, 1.0F) & 16777215;
      } else {
         return (Integer)this.field_0014;
      }
   }

   public float method_08905() {
      return (Float)this.field_0014;
   }

   public String method_08882() {
      return this.field_0002;
   }

   public AbstractModule method_08920() {
      return this.container;
   }

   public Object method_08903() {
      return this.field_0004;
   }

   public Object method_08878() {
      return this.field_0011;
   }

   public Setting(String var1) {
      this.field_0024 = "";
      this.field_0023 = "";
      this.field_0002 = "";
      this.field_0006 = "";
      this.field_0017 = "OFF";
      this.field_0016 = "ON";
      this.field_0007 = true;
      this.field_0001 = var1;
   }

   public String method_08874() {
      return (String)this.field_0014;
   }

   public Setting method_08916(String var1, String var2) {
      this.field_0017 = var1;
      this.field_0016 = var2;
      return this;
   }

   public Setting method_08909(boolean var1) {
      this.field_0015 = var1;
      return this;
   }

   public Setting method_08894(BooleanSupplier var1) {
      this.field_0025 = var1;
      return this;
   }

   public Setting setValue(Object var1, boolean var2) {
      if (CheatBreaker.getInstance().getConfigManager().field_0009 != null
         && CheatBreaker.getInstance().getConfigManager().field_0009.getName().equals("default")) {
         if (var2) {
            CheatBreaker.getInstance().getConfigManager().method_25097();
         }
      } else if (this.container != null) {
         this.container.method_28782().add(var1);
      }

      this.field_0014 = var1;
      if (this.valueConsumer != null) {
         this.valueConsumer.accept(var1);
      }

      return this;
   }

   public Object method_08881() {
      return this.field_0010;
   }

   public Color method_08913(int var1) {
      float var2 = (var1 >> 24 & 0xFF) / 255.0F;
      float var3 = (var1 >> 16 & 0xFF) / 255.0F;
      float var4 = (var1 >> 8 & 0xFF) / 255.0F;
      float var5 = (var1 & 0xFF) / 255.0F;
      return new Color(var3, var4, var5, var2);
   }

   public int method_08877() {
      return this.field_0027;
   }
}
