package com.cheatbreaker.client.ui.fading;

import io.netty.handler.codec.spdy.DefaultSpdyHeaders$1;

public abstract class AbstractFade {
   public float field_0005;
   public DefaultSpdyHeaders$1 field_0009;
   public long field_0004;
   public boolean field_0008;
   public int field_0001;
   public long field_0002;
   public boolean field_0010 = true;
   public boolean field_0007;
   public long field_0003;
   public long field_0011;
   public int field_0000;
   public float field_0006;

   public int method_21220() {
      return this.field_0000;
   }

   public float method_21235() {
      return this.field_0006;
   }

   public void method_21223(long var1) {
      this.field_0002 = var1;
   }

   public void method_21222(int var1) {
      this.field_0000 = var1;
   }

   public boolean method_21217() {
      return this.field_0003 != (1472958591784725551L & 587419712L);
   }

   public float method_21230() {
      if (this.field_0003 == (-587030097426873720L & 587030095739355395L)) {
         return 0.0F;
      } else {
         return this.method_21241() <= (1225105218497152172L & 707977280L) ? 1.0F : this.getValue();
      }
   }

   public boolean method_21212() {
      return this.field_0007;
   }

   public boolean method_21213() {
      return this.field_0008;
   }

   public long method_21240() {
      return this.field_0004;
   }

   public abstract float getValue();

   public long method_21214() {
      return this.field_0002;
   }

   public long method_21241() {
      return this.field_0003 + this.field_0004 - this.field_0011 - System.currentTimeMillis();
   }

   public boolean method_21211() {
      return this.field_0010;
   }

   public void method_21224(boolean var1) {
      this.field_0007 = var1;
   }

   public void method_21237(int var1) {
      this.field_0001 = var1;
   }

   public int method_21225() {
      return this.field_0001;
   }

   public void method_21238(long var1) {
      this.field_0003 = var1;
   }

   public float method_21227() {
      if (this.field_0003 == (7187635985331520529L & 33759722L)) {
         return 0.0F;
      } else if (!this.method_21210()) {
         return this.field_0010 ? this.getValue() : this.field_0005;
      } else {
         if (this.field_0008 || this.field_0000 >= 1 && this.field_0001 < this.field_0000) {
            this.method_20200();
            this.field_0001++;
         }

         return this.field_0006;
      }
   }

   public void method_21216() {
      this.field_0003 = -4623745062465931239L & 4623745060785094912L;
      this.field_0001 = 1;
   }

   public void method_21228() {
      this.field_0010 = false;
      this.field_0005 = this.getValue();
      this.field_0002 = System.currentTimeMillis() - this.field_0003;
   }

   public void method_21218(long var1) {
      this.field_0004 = var1;
   }

   public void method_21221(float var1) {
      this.field_0003 = System.currentTimeMillis();
      this.field_0011 = var1 == 0.0F ? 1478623792L & 67637449L : (long)((float)this.field_0004 * (1.0F - var1));
      this.field_0010 = true;
   }

   public void method_21239(boolean var1) {
      this.field_0010 = var1;
   }

   public void method_21234() {
      this.field_0008 = true;
   }

   public AbstractFade(long var1, float var3) {
      this.field_0001 = 1;
      this.field_0000 = 1;
      this.field_0004 = var1;
      this.field_0006 = var3;
   }

   public long method_21209() {
      return this.field_0011;
   }

   public void method_21219(boolean var1) {
      this.field_0008 = var1;
   }

   public void method_21236(float var1) {
      this.field_0005 = var1;
   }

   public void method_21215() {
      this.field_0003 = System.currentTimeMillis() - this.field_0002;
      this.field_0010 = true;
   }

   public void method_21231(long var1) {
      this.field_0011 = var1;
   }

   public float method_21226() {
      return this.field_0005;
   }

   public boolean method_21233() {
      return this.field_0003 != (6331547731245662212L & 1610653760L) && this.method_21241() > (177204503053991936L & 751053845L);
   }

   public long method_21208() {
      return this.field_0003;
   }

   public float method_21232(boolean var1) {
      if (var1 && !this.field_0007) {
         this.field_0007 = true;
         this.method_21221(this.method_21230());
      } else if (this.field_0007 && !var1) {
         this.field_0007 = false;
         this.method_21221(this.method_21230());
      }

      if (this.field_0003 == (-3067966542814049152L & 1543930387L)) {
         return 0.0F;
      } else {
         float var2 = this.method_21230();
         return this.field_0007 ? var2 : 1.0F - var2;
      }
   }

   public long method_21207() {
      return this.field_0004 - this.method_21229();
   }

   public boolean method_21210() {
      return this.method_21241() <= (2029716168774124742L & 1482195728L) && this.field_0010;
   }

   public void method_20200() {
      this.field_0003 = System.currentTimeMillis();
      this.field_0010 = true;
      this.field_0011 = -7685340338593930492L & 407765185L;
   }

   public long method_21229() {
      long var1 = this.field_0010 ? this.method_21241() : System.currentTimeMillis() - this.field_0002 + this.field_0004 - System.currentTimeMillis();
      return Math.min(this.field_0004, Math.max(-1544754029605420984L & 615062150L, var1));
   }
}
