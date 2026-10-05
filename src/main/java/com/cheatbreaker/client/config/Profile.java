package com.cheatbreaker.client.config;

public class Profile {
   public int index = 0;
   public boolean editable;
   public String name;

   public void method_08391(boolean var1) {
      this.editable = var1;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public String getName() {
      return this.name;
   }

   public Profile(String var1, boolean var2) {
      this.name = var1;
      this.editable = var2;
   }

   public void setIndex(int var1) {
      this.index = var1;
   }

   public int getIndex() {
      return this.index;
   }

   public boolean isEditable() {
      return this.editable;
   }
}
