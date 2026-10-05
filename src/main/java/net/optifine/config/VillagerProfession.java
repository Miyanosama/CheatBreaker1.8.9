package net.optifine.config;

import net.minecraft.src.Config;

public class VillagerProfession {
   public int profession;
   public int[] careers;

   public VillagerProfession(int var1, int var2) {
      this(var1, new int[]{var2});
   }

   public VillagerProfession(int var1) {
      this(var1, (int[])null);
   }

   public boolean addCareer(int var1) {
      if (this.careers == null) {
         this.careers = new int[]{var1};
         return true;
      } else if (this.hasCareer(var1)) {
         return false;
      } else {
         this.careers = Config.addIntToArray(this.careers, var1);
         return true;
      }
   }

   public VillagerProfession(int var1, int[] var2) {
      this.profession = var1;
      this.careers = var2;
   }

   @Override
   public String toString() {
      return this.careers == null ? "" + this.profession : "" + this.profession + ":" + Config.arrayToString(this.careers);
   }

   public int getProfession() {
      return this.profession;
   }

   public int[] getCareers() {
      return this.careers;
   }

   public boolean matches(int var1, int var2) {
      return this.profession != var1 ? false : this.careers == null || Config.equalsOne(var2, this.careers);
   }

   public boolean hasCareer(int var1) {
      return this.careers == null ? false : Config.equalsOne(var1, this.careers);
   }
}
