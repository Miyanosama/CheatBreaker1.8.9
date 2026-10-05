package com.jagrosh.discordipc.entities;

import java.time.OffsetDateTime;

public class RichPresence$Builder {
   public String recoveredField3751;
   public String recoveredField3752;
   public String recoveredField3753;
   public String recoveredField3754;
   public String recoveredField3755;
   public String recoveredField3756;
   public String recoveredField3757;
   public String recoveredField3758;
   public int recoveredField3759;
   public String recoveredField3760;
   public boolean recoveredField3761;
   public String recoveredField3762;
   public int recoveredField3763;
   public OffsetDateTime recoveredField3764;
   public OffsetDateTime recoveredField3765;

   public RichPresence$Builder method_13374(String var1, int var2, int var3) {
      this.recoveredField3752 = var1;
      this.recoveredField3759 = var2;
      this.recoveredField3763 = var3;
      return this;
   }

   public RichPresence$Builder method_13373(String var1) {
      this.recoveredField3762 = var1;
      return this;
   }

   public RichPresence$Builder method_13379(String var1) {
      this.recoveredField3760 = var1;
      return this;
   }

   public RichPresence$Builder method_13377(boolean var1) {
      this.recoveredField3761 = var1;
      return this;
   }

   public RichPresence$Builder method_13375(String var1, String var2) {
      this.recoveredField3758 = var1;
      this.recoveredField3753 = var2;
      return this;
   }

   public RichPresence$Builder method_13376(OffsetDateTime var1) {
      this.recoveredField3765 = var1;
      return this;
   }

   public RichPresence$Builder method_13380(String var1, String var2) {
      this.recoveredField3754 = var1;
      this.recoveredField3756 = var2;
      return this;
   }

   public RichPresence$Builder method_13371(String var1) {
      this.recoveredField3757 = var1;
      return this;
   }

   public RichPresence$Builder method_13381(OffsetDateTime var1) {
      this.recoveredField3764 = var1;
      return this;
   }

   public RichPresence method_13372() {
      return new RichPresence(
         this.recoveredField3755,
         this.recoveredField3760,
         this.recoveredField3765,
         this.recoveredField3764,
         this.recoveredField3754,
         this.recoveredField3756,
         this.recoveredField3758,
         this.recoveredField3753,
         this.recoveredField3752,
         this.recoveredField3759,
         this.recoveredField3763,
         this.recoveredField3757,
         this.recoveredField3762,
         this.recoveredField3751,
         this.recoveredField3761
      );
   }

   public RichPresence$Builder method_13378(String var1) {
      this.recoveredField3751 = var1;
      return this;
   }

   public RichPresence$Builder method_13369(String var1) {
      this.recoveredField3755 = var1;
      return this;
   }

   public RichPresence$Builder method_13370(String var1) {
      return this.method_13380(var1, null);
   }

   public RichPresence$Builder method_13382(String var1) {
      return this.method_13375(var1, null);
   }
}
