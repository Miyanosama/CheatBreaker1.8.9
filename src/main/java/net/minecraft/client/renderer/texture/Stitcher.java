package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.util.MathHelper;

public class Stitcher {
   public int mipmapLevelStitcher;
   public int currentWidth;
   public int maxWidth;
   public int maxTileDimension;
   public int maxHeight;
   public boolean forcePowerOf2;
   public Set<Stitcher.Holder> setStitchHolders = Sets.newHashSetWithExpectedSize(256);
   public List<Stitcher.Slot> stitchSlots = Lists.newArrayListWithCapacity(256);
   public int currentHeight;

   public void addSprite(TextureAtlasSprite var1) {
      Stitcher.Holder var2 = new Stitcher.Holder(var1, this.mipmapLevelStitcher);
      if (this.maxTileDimension > 0) {
         var2.setNewDimension(this.maxTileDimension);
      }

      this.setStitchHolders.add(var2);
   }

   public void doStitch() {
      Stitcher.Holder[] var1 = (Stitcher.Holder[])this.setStitchHolders.toArray(new Stitcher.Holder[this.setStitchHolders.size()]);
      Arrays.sort(var1);

      for (Stitcher.Holder var5 : var1) {
         if (!this.allocateSlot(var5)) {
            String var6 = String.format(
               "Unable to fit: %s, size: %dx%d, atlas: %dx%d, atlasMax: %dx%d - Maybe try a lower resolution resourcepack?",
               var5.getAtlasSprite().getIconName(),
               var5.getAtlasSprite().getIconWidth(),
               var5.getAtlasSprite().getIconHeight(),
               this.currentWidth,
               this.currentHeight,
               this.maxWidth,
               this.maxHeight
            );
            throw new StitcherException(var5, var6);
         }
      }

      if (this.forcePowerOf2) {
         this.currentWidth = MathHelper.roundUpToPowerOfTwo(this.currentWidth);
         this.currentHeight = MathHelper.roundUpToPowerOfTwo(this.currentHeight);
      }
   }

   public static int getMipmapDimension(int var0, int var1) {
      return (var0 >> var1) + ((var0 & (1 << var1) - 1) == 0 ? 0 : 1) << var1;
   }

   public int getCurrentWidth() {
      return this.currentWidth;
   }

   public List<TextureAtlasSprite> getStichSlots() {
      ArrayList var1 = Lists.newArrayList();

      for (Stitcher.Slot var3 : this.stitchSlots) {
         var3.getAllStitchSlots(var1);
      }

      ArrayList var7 = Lists.newArrayList();

      for (Stitcher.Slot var4 : (Iterable<Stitcher.Slot>)(Iterable<?>)(var1)) {
         Stitcher.Holder var5 = var4.getStitchHolder();
         TextureAtlasSprite var6 = var5.getAtlasSprite();
         var6.initSprite(this.currentWidth, this.currentHeight, var4.getOriginX(), var4.getOriginY(), var5.isRotated());
         var7.add(var6);
      }

      return var7;
   }

   public Stitcher(int var1, int var2, boolean var3, int var4, int var5) {
      this.mipmapLevelStitcher = var5;
      this.maxWidth = var1;
      this.maxHeight = var2;
      this.forcePowerOf2 = var3;
      this.maxTileDimension = var4;
   }

   public boolean expandAndAllocateSlot(Stitcher.Holder var1) {
      int var2 = Math.min(var1.getWidth(), var1.getHeight());
      boolean var3 = this.currentWidth == 0 && this.currentHeight == 0;
      boolean var4;
      if (this.forcePowerOf2) {
         int var5 = MathHelper.roundUpToPowerOfTwo(this.currentWidth);
         int var6 = MathHelper.roundUpToPowerOfTwo(this.currentHeight);
         int var7 = MathHelper.roundUpToPowerOfTwo(this.currentWidth + var2);
         int var8 = MathHelper.roundUpToPowerOfTwo(this.currentHeight + var2);
         boolean var9 = var7 <= this.maxWidth;
         boolean var10 = var8 <= this.maxHeight;
         if (!var9 && !var10) {
            return false;
         }

         boolean var11 = var5 != var7;
         boolean var12 = var6 != var8;
         if (var11 ^ var12) {
            var4 = !var11;
         } else {
            var4 = var9 && var5 <= var6;
         }
      } else {
         boolean var13 = this.currentWidth + var2 <= this.maxWidth;
         boolean var15 = this.currentHeight + var2 <= this.maxHeight;
         if (!var13 && !var15) {
            return false;
         }

         var4 = var13 && (var3 || this.currentWidth <= this.currentHeight);
      }

      int var14 = Math.max(var1.getWidth(), var1.getHeight());
      if (MathHelper.roundUpToPowerOfTwo((!var4 ? this.currentHeight : this.currentWidth) + var14) > (!var4 ? this.maxHeight : this.maxWidth)) {
         return false;
      } else {
         Stitcher.Slot var16;
         if (var4) {
            if (var1.getWidth() > var1.getHeight()) {
               var1.rotate();
            }

            if (this.currentHeight == 0) {
               this.currentHeight = var1.getHeight();
            }

            var16 = new Stitcher.Slot(this.currentWidth, 0, var1.getWidth(), this.currentHeight);
            this.currentWidth = this.currentWidth + var1.getWidth();
         } else {
            var16 = new Stitcher.Slot(0, this.currentHeight, this.currentWidth, var1.getHeight());
            this.currentHeight = this.currentHeight + var1.getHeight();
         }

         var16.addSlot(var1);
         this.stitchSlots.add(var16);
         return true;
      }
   }

   public boolean allocateSlot(Stitcher.Holder var1) {
      for (int var2 = 0; var2 < this.stitchSlots.size(); var2++) {
         if (this.stitchSlots.get(var2).addSlot(var1)) {
            return true;
         }

         var1.rotate();
         if (this.stitchSlots.get(var2).addSlot(var1)) {
            return true;
         }

         var1.rotate();
      }

      return this.expandAndAllocateSlot(var1);
   }

   public int getCurrentHeight() {
      return this.currentHeight;
   }

   public static class Holder implements Comparable<Stitcher.Holder> {
      public int width;
      public TextureAtlasSprite theTexture;
      public boolean rotated;
      public int mipmapLevelHolder;
      public int height;
      public float scaleFactor = 1.0F;

      public Holder(TextureAtlasSprite var1, int var2) {
         this.theTexture = var1;
         this.width = var1.getIconWidth();
         this.height = var1.getIconHeight();
         this.mipmapLevelHolder = var2;
         this.rotated = Stitcher.getMipmapDimension(this.height, var2) > Stitcher.getMipmapDimension(this.width, var2);
      }

      public TextureAtlasSprite getAtlasSprite() {
         return this.theTexture;
      }

      public boolean isRotated() {
         return this.rotated;
      }

      @Override
      public String toString() {
         return "Holder{width=" + this.width + ", height=" + this.height + '}';
      }

      public void rotate() {
         this.rotated = !this.rotated;
      }

      public int getHeight() {
         return this.rotated
            ? Stitcher.getMipmapDimension((int)(this.width * this.scaleFactor), this.mipmapLevelHolder)
            : Stitcher.getMipmapDimension((int)(this.height * this.scaleFactor), this.mipmapLevelHolder);
      }

      public void setNewDimension(int var1) {
         if (this.width > var1 && this.height > var1) {
            this.scaleFactor = (float)var1 / Math.min(this.width, this.height);
         }
      }

      public int compareTo(Stitcher.Holder var1) {
         int var2;
         if (this.getHeight() == var1.getHeight()) {
            if (this.getWidth() == var1.getWidth()) {
               if (this.theTexture.getIconName() == null) {
                  return var1.theTexture.getIconName() == null ? 0 : -1;
               }

               return this.theTexture.getIconName().compareTo(var1.theTexture.getIconName());
            }

            var2 = this.getWidth() < var1.getWidth() ? 1 : -1;
         } else {
            var2 = this.getHeight() < var1.getHeight() ? 1 : -1;
         }

         return var2;
      }

      public int getWidth() {
         return this.rotated
            ? Stitcher.getMipmapDimension((int)(this.height * this.scaleFactor), this.mipmapLevelHolder)
            : Stitcher.getMipmapDimension((int)(this.width * this.scaleFactor), this.mipmapLevelHolder);
      }
   }

   public static class Slot {
      public int height;
      public int originX;
      public int width;
      public Stitcher.Holder holder;
      public List<Stitcher.Slot> subSlots;
      public int originY;

      public Stitcher.Holder getStitchHolder() {
         return this.holder;
      }

      @Override
      public String toString() {
         return "Slot{originX="
            + this.originX
            + ", originY="
            + this.originY
            + ", width="
            + this.width
            + ", height="
            + this.height
            + ", texture="
            + this.holder
            + ", subSlots="
            + this.subSlots
            + '}';
      }

      public boolean addSlot(Stitcher.Holder var1) {
         if (this.holder != null) {
            return false;
         } else {
            int var2 = var1.getWidth();
            int var3 = var1.getHeight();
            if (var2 <= this.width && var3 <= this.height) {
               if (var2 == this.width && var3 == this.height) {
                  this.holder = var1;
                  return true;
               } else {
                  if (this.subSlots == null) {
                     this.subSlots = Lists.newArrayListWithCapacity(1);
                     this.subSlots.add(new Stitcher.Slot(this.originX, this.originY, var2, var3));
                     int var4 = this.width - var2;
                     int var5 = this.height - var3;
                     if (var5 > 0 && var4 > 0) {
                        int var6 = Math.max(this.height, var4);
                        int var7 = Math.max(this.width, var5);
                        if (var6 >= var7) {
                           this.subSlots.add(new Stitcher.Slot(this.originX, this.originY + var3, var2, var5));
                           this.subSlots.add(new Stitcher.Slot(this.originX + var2, this.originY, var4, this.height));
                        } else {
                           this.subSlots.add(new Stitcher.Slot(this.originX + var2, this.originY, var4, var3));
                           this.subSlots.add(new Stitcher.Slot(this.originX, this.originY + var3, this.width, var5));
                        }
                     } else if (var4 == 0) {
                        this.subSlots.add(new Stitcher.Slot(this.originX, this.originY + var3, var2, var5));
                     } else if (var5 == 0) {
                        this.subSlots.add(new Stitcher.Slot(this.originX + var2, this.originY, var4, var3));
                     }
                  }

                  for (Stitcher.Slot var9 : this.subSlots) {
                     if (var9.addSlot(var1)) {
                        return true;
                     }
                  }

                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public Slot(int var1, int var2, int var3, int var4) {
         this.originX = var1;
         this.originY = var2;
         this.width = var3;
         this.height = var4;
      }

      public int getOriginX() {
         return this.originX;
      }

      public int getOriginY() {
         return this.originY;
      }

      public void getAllStitchSlots(List<Stitcher.Slot> var1) {
         if (this.holder != null) {
            var1.add(this);
         } else if (this.subSlots != null) {
            for (Stitcher.Slot var3 : this.subSlots) {
               var3.getAllStitchSlots(var1);
            }
         }
      }
   }
}
