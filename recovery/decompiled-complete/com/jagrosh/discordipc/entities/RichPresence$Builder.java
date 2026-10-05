package com.jagrosh.discordipc.entities;

import io.netty.util.concurrent.AbstractEventExecutor;
import java.time.OffsetDateTime;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$7;
import recovered.unidentified.UnidentifiedClass0334;
import recovered.unidentified.UnidentifiedClass3833;

public class RichPresence$Builder {
   public String field_0008;
   public LogBrokerMonitor$7 field_0016;
   public String field_0007;
   public String field_0014;
   public String field_0002;
   public String field_0003;
   public UnidentifiedClass0334 field_0017;
   public String field_0012;
   public String field_0004;
   public String field_0018;
   public int field_0001;
   public String field_0009;
   public boolean field_0011;
   public String field_0006;
   public int field_0013;
   public OffsetDateTime field_0015;
   public OffsetDateTime field_0000;
   public UnidentifiedClass3833 field_0005;
   public AbstractEventExecutor field_0010;

   public RichPresence$Builder method_13374(String var1, int var2, int var3) {
      this.field_0007 = var1;
      this.field_0001 = var2;
      this.field_0013 = var3;
      return this;
   }

   public RichPresence$Builder method_13373(String var1) {
      this.field_0006 = var1;
      return this;
   }

   public RichPresence$Builder method_13379(String var1) {
      this.field_0009 = var1;
      return this;
   }

   public RichPresence$Builder method_13377(boolean var1) {
      this.field_0011 = var1;
      return this;
   }

   public RichPresence$Builder method_13375(String var1, String var2) {
      this.field_0018 = var1;
      this.field_0014 = var2;
      return this;
   }

   public RichPresence$Builder method_13376(OffsetDateTime var1) {
      this.field_0000 = var1;
      return this;
   }

   public RichPresence$Builder method_13380(String var1, String var2) {
      this.field_0002 = var1;
      this.field_0012 = var2;
      return this;
   }

   public RichPresence$Builder method_13371(String var1) {
      this.field_0004 = var1;
      return this;
   }

   public RichPresence$Builder method_13381(OffsetDateTime var1) {
      this.field_0015 = var1;
      return this;
   }

   public RichPresence method_13372() {
      return new RichPresence(
         this.field_0003,
         this.field_0009,
         this.field_0000,
         this.field_0015,
         this.field_0002,
         this.field_0012,
         this.field_0018,
         this.field_0014,
         this.field_0007,
         this.field_0001,
         this.field_0013,
         this.field_0004,
         this.field_0006,
         this.field_0008,
         this.field_0011
      );
   }

   public RichPresence$Builder method_13378(String var1) {
      this.field_0008 = var1;
      return this;
   }

   public RichPresence$Builder method_13369(String var1) {
      this.field_0003 = var1;
      return this;
   }

   public RichPresence$Builder method_13370(String var1) {
      return this.method_13380(var1, null);
   }

   public RichPresence$Builder method_13382(String var1) {
      return this.method_13375(var1, null);
   }
}
