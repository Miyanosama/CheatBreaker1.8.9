package io.netty.util.internal;

import com.cheatbreaker.client.ui.overlay.element.RadioVolumeSlider;

import io.netty.handler.timeout.TimeoutException;
import java.util.Arrays;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.crafting.RecipesMapExtending;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import com.cheatbreaker.client.ui.overlay.element.RadioVolumeSlider$EnumSwitch;

public class AppendableCharSequence implements Appendable, CharSequence {
   public char[] chars;
   public int pos;

   @Override
   public String toString() {
      return new String(this.chars, 0, this.pos);
   }

   public AppendableCharSequence append(CharSequence var1, int var2, int var3) {
      if (var1.length() < var3) {
         throw new IndexOutOfBoundsException();
      } else {
         int var4 = var3 - var2;
         if (var4 > this.chars.length - this.pos) {
            this.chars = expand(this.chars, this.pos + var4, this.pos);
         }

         if (var1 instanceof AppendableCharSequence) {
            AppendableCharSequence var7 = (AppendableCharSequence)var1;
            char[] var6 = var7.chars;
            System.arraycopy(var6, var2, this.chars, this.pos, var4);
            this.pos += var4;
            return this;
         } else {
            for (int var5 = var2; var5 < var3; var5++) {
               this.chars[this.pos++] = var1.charAt(var5);
            }

            return this;
         }
      }
   }

   public AppendableCharSequence append(CharSequence var1) {
      return this.append(var1, 0, var1.length());
   }

   public AppendableCharSequence subSequence(int var1, int var2) {
      return new AppendableCharSequence(Arrays.copyOfRange(this.chars, var1, var2));
   }

   public void reset() {
      this.pos = 0;
   }

   public String substring(int var1, int var2) {
      int var3 = var2 - var1;
      if (var1 <= this.pos && var3 <= this.pos) {
         return new String(this.chars, var1, var3);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public int length() {
      return this.pos;
   }

   @Override
   public char charAt(int var1) {
      if (var1 > this.pos) {
         throw new IndexOutOfBoundsException();
      } else {
         return this.chars[var1];
      }
   }

   public static char[] expand(char[] var0, int var1, int var2) {
      int var3 = var0.length;

      do {
         var3 <<= 1;
         if (var3 < 0) {
            throw new IllegalStateException();
         }
      } while (var1 > var3);

      char[] var4 = new char[var3];
      System.arraycopy(var0, 0, var4, 0, var2);
      return var4;
   }

   public AppendableCharSequence append(char var1) {
      if (this.pos == this.chars.length) {
         char[] var2 = this.chars;
         int var3 = var2.length << 1;
         if (var3 < 0) {
            throw new IllegalStateException();
         }

         this.chars = new char[var3];
         System.arraycopy(var2, 0, this.chars, 0, var2.length);
      }

      this.chars[this.pos++] = var1;
      return this;
   }

   public AppendableCharSequence(int var1) {
      if (var1 < 1) {
         throw new IllegalArgumentException("length: " + var1 + " (length: >= 1)");
      } else {
         this.chars = new char[var1];
      }
   }

   public AppendableCharSequence(char[] var1) {
      this.chars = var1;
      this.pos = var1.length;
   }
}
