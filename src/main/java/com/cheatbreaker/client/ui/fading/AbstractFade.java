package com.cheatbreaker.client.ui.fading;

public abstract class AbstractFade {
   public float recoveredField1960;
   public long recoveredField1961;
   public boolean recoveredField1962;
   public int recoveredField1963;
   public long recoveredField1964;
   public boolean recoveredField1965 = true;
   public boolean recoveredField1966;
   public long recoveredField1967;
   public long recoveredField1968;
   public int recoveredField1969;
   public float recoveredField1970;

   public int method_21220() {
      return this.recoveredField1969;
   }

   public float method_21235() {
      return this.recoveredField1970;
   }

   public void method_21223(long var1) {
      this.recoveredField1964 = var1;
   }

   public void method_21222(int var1) {
      this.recoveredField1969 = var1;
   }

   public boolean method_21217() {
      return this.recoveredField1967 != 0L;
   }

   public float method_21230() {
      if (this.recoveredField1967 == 0L) {
         return 0.0F;
      } else {
         return this.method_21241() <= 0L ? 1.0F : this.getValue();
      }
   }

   public boolean method_21212() {
      return this.recoveredField1966;
   }

   public boolean method_21213() {
      return this.recoveredField1962;
   }

   public long method_21240() {
      return this.recoveredField1961;
   }

   public abstract float getValue();

   public long method_21214() {
      return this.recoveredField1964;
   }

   public long method_21241() {
      return this.recoveredField1967 + this.recoveredField1961 - this.recoveredField1968 - System.currentTimeMillis();
   }

   public boolean method_21211() {
      return this.recoveredField1965;
   }

   public void method_21224(boolean var1) {
      this.recoveredField1966 = var1;
   }

   public void method_21237(int var1) {
      this.recoveredField1963 = var1;
   }

   public int method_21225() {
      return this.recoveredField1963;
   }

   public void method_21238(long var1) {
      this.recoveredField1967 = var1;
   }

   public float method_21227() {
      if (this.recoveredField1967 == 0L) {
         return 0.0F;
      } else if (!this.method_21210()) {
         return this.recoveredField1965 ? this.getValue() : this.recoveredField1960;
      } else {
         if (this.recoveredField1962 || this.recoveredField1969 >= 1 && this.recoveredField1963 < this.recoveredField1969) {
            this.method_20200();
            this.recoveredField1963++;
         }

         return this.recoveredField1970;
      }
   }

   public void method_21216() {
      this.recoveredField1967 = 0L;
      this.recoveredField1963 = 1;
   }

   public void method_21228() {
      this.recoveredField1965 = false;
      this.recoveredField1960 = this.getValue();
      this.recoveredField1964 = System.currentTimeMillis() - this.recoveredField1967;
   }

   public void method_21218(long var1) {
      this.recoveredField1961 = var1;
   }

   public void method_21221(float var1) {
      this.recoveredField1967 = System.currentTimeMillis();
      this.recoveredField1968 = var1 == 0.0F ? 0L : (long)((float)this.recoveredField1961 * (1.0F - var1));
      this.recoveredField1965 = true;
   }

   public void method_21239(boolean var1) {
      this.recoveredField1965 = var1;
   }

   public void method_21234() {
      this.recoveredField1962 = true;
   }

   public AbstractFade(long var1, float var3) {
      this.recoveredField1963 = 1;
      this.recoveredField1969 = 1;
      this.recoveredField1961 = var1;
      this.recoveredField1970 = var3;
   }

   public long method_21209() {
      return this.recoveredField1968;
   }

   public void method_21219(boolean var1) {
      this.recoveredField1962 = var1;
   }

   public void method_21236(float var1) {
      this.recoveredField1960 = var1;
   }

   public void method_21215() {
      this.recoveredField1967 = System.currentTimeMillis() - this.recoveredField1964;
      this.recoveredField1965 = true;
   }

   public void method_21231(long var1) {
      this.recoveredField1968 = var1;
   }

   public float method_21226() {
      return this.recoveredField1960;
   }

   public boolean method_21233() {
      return this.recoveredField1967 != 0L && this.method_21241() > 0L;
   }

   public long method_21208() {
      return this.recoveredField1967;
   }

   public float method_21232(boolean var1) {
      if (var1 && !this.recoveredField1966) {
         this.recoveredField1966 = true;
         this.method_21221(this.method_21230());
      } else if (this.recoveredField1966 && !var1) {
         this.recoveredField1966 = false;
         this.method_21221(this.method_21230());
      }

      if (this.recoveredField1967 == 0L) {
         return 0.0F;
      } else {
         float var2 = this.method_21230();
         return this.recoveredField1966 ? var2 : 1.0F - var2;
      }
   }

   public long method_21207() {
      return this.recoveredField1961 - this.method_21229();
   }

   public boolean method_21210() {
      return this.method_21241() <= 0L && this.recoveredField1965;
   }

   public void method_20200() {
      this.recoveredField1967 = System.currentTimeMillis();
      this.recoveredField1965 = true;
      this.recoveredField1968 = 0L;
   }

   public long method_21229() {
      long var1 = this.recoveredField1965
         ? this.method_21241()
         : System.currentTimeMillis() - this.recoveredField1964 + this.recoveredField1961 - System.currentTimeMillis();
      return Math.min(this.recoveredField1961, Math.max(0L, var1));
   }
}
