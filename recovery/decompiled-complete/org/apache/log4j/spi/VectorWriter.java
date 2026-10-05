package org.apache.log4j.spi;

import java.io.PrintWriter;
import java.util.Vector;
import net.minecraft.item.ItemSword;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.EnumSkyBlock;

public class VectorWriter extends PrintWriter {
   public Team field_0001;
   public EnumSkyBlock field_0003;
   public ItemSword field_0000;
   public Vector v = new Vector();

   public void println(String var1) {
      this.v.addElement(var1);
   }

   public void write(String var1, int var2, int var3) {
      this.v.addElement(var1.substring(var2, var2 + var3));
   }

   public void println(char[] var1) {
      this.v.addElement(new String(var1));
   }

   public void println(Object var1) {
      this.v.addElement(String.valueOf(var1));
   }

   public void print(char[] var1) {
      this.v.addElement(new String(var1));
   }

   public void write(char[] var1, int var2, int var3) {
      this.v.addElement(new String(var1, var2, var3));
   }

   public void print(String var1) {
      this.v.addElement(var1);
   }

   public VectorWriter() {
      super(new NullWriter());
   }

   public String[] toStringArray() {
      int var1 = this.v.size();
      String[] var2 = new String[var1];

      for (int var3 = 0; var3 < var1; var3++) {
         var2[var3] = (String)this.v.elementAt(var3);
      }

      return var2;
   }

   public void write(String var1) {
      this.v.addElement(var1);
   }

   public void write(char[] var1) {
      this.v.addElement(new String(var1));
   }

   public void print(Object var1) {
      this.v.addElement(String.valueOf(var1));
   }
}
