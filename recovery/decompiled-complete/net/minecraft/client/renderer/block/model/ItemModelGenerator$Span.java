package net.minecraft.client.renderer.block.model;

import recovered.unidentified.UnidentifiedClass3389;

public class ItemModelGenerator$Span {
   public int field_178386_d;
   public UnidentifiedClass3389 field_0004;
   public ItemModelGenerator$SpanFacing spanFacing;
   public int field_178387_b;
   public int field_178388_c;

   public ItemModelGenerator$SpanFacing func_178383_a() {
      return this.spanFacing;
   }

   public int func_178384_c() {
      return this.field_178388_c;
   }

   public void func_178382_a(int var1) {
      if (var1 < this.field_178387_b) {
         this.field_178387_b = var1;
      } else if (var1 > this.field_178388_c) {
         this.field_178388_c = var1;
      }
   }

   public int func_178385_b() {
      return this.field_178387_b;
   }

   public ItemModelGenerator$Span(ItemModelGenerator$SpanFacing var1, int var2, int var3) {
      this.spanFacing = var1;
      this.field_178387_b = var2;
      this.field_178388_c = var2;
      this.field_178386_d = var3;
   }

   public int func_178381_d() {
      return this.field_178386_d;
   }
}
