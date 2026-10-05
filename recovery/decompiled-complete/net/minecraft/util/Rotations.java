package net.minecraft.util;

import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import net.minecraft.block.BlockRedstoneComparator$Mode;
import net.minecraft.item.ItemCarrotOnAStick;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;

public class Rotations {
   public BlockRedstoneComparator$Mode field_0003;
   public float y;
   public ItemCarrotOnAStick field_0002;
   public CBGuiAnchor field_0004;
   public float x;
   public float z;

   public float getY() {
      return this.y;
   }

   public float getX() {
      return this.x;
   }

   public NBTTagList writeToNBT() {
      NBTTagList var1 = new NBTTagList();
      var1.appendTag(new NBTTagFloat(this.x));
      var1.appendTag(new NBTTagFloat(this.y));
      var1.appendTag(new NBTTagFloat(this.z));
      return var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof Rotations)) {
         return false;
      } else {
         Rotations var2 = (Rotations)var1;
         return this.x == var2.x && this.y == var2.y && this.z == var2.z;
      }
   }

   public Rotations(float var1, float var2, float var3) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
   }

   public float getZ() {
      return this.z;
   }

   public Rotations(NBTTagList var1) {
      this.x = var1.getFloatAt(0);
      this.y = var1.getFloatAt(1);
      this.z = var1.getFloatAt(2);
   }
}
