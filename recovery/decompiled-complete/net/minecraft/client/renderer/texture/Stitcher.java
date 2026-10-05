package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.entity.passive.EntityWolf$1;
import net.minecraft.util.MathHelper;
import org.apache.log4j.config.PropertyGetter;

public class Stitcher {
   public int mipmapLevelStitcher;
   public int currentWidth;
   public int maxWidth;
   public int maxTileDimension;
   public PropertyGetter field_0001;
   public int maxHeight;
   public boolean forcePowerOf2;
   public Set<Stitcher$Holder> setStitchHolders = Sets.newHashSetWithExpectedSize(256);
   public EntityWolf$1 field_0003;
   public List<Stitcher$Slot> stitchSlots = Lists.newArrayListWithCapacity(256);
   public int currentHeight;

   public void addSprite(TextureAtlasSprite var1) {
      Stitcher$Holder var2 = new Stitcher$Holder(var1, this.mipmapLevelStitcher);
      if (this.maxTileDimension > 0) {
         var2.setNewDimension(this.maxTileDimension);
      }

      this.setStitchHolders.add(var2);
   }

   public void doStitch() {
      Stitcher$Holder[] var1 = this.setStitchHolders.toArray(new Stitcher$Holder[this.setStitchHolders.size()]);
      Arrays.sort(var1);

      for (Stitcher$Holder var5 : var1) {
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

      for (Stitcher$Slot var3 : this.stitchSlots) {
         var3.getAllStitchSlots(var1);
      }

      ArrayList var7 = Lists.newArrayList();

      for (Stitcher$Slot var4 : var1) {
         Stitcher$Holder var5 = var4.getStitchHolder();
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

   public boolean expandAndAllocateSlot(Stitcher$Holder var1) {
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
         Stitcher$Slot var16;
         if (var4) {
            if (var1.getWidth() > var1.getHeight()) {
               var1.rotate();
            }

            if (this.currentHeight == 0) {
               this.currentHeight = var1.getHeight();
            }

            var16 = new Stitcher$Slot(this.currentWidth, 0, var1.getWidth(), this.currentHeight);
            this.currentWidth = this.currentWidth + var1.getWidth();
         } else {
            var16 = new Stitcher$Slot(0, this.currentHeight, this.currentWidth, var1.getHeight());
            this.currentHeight = this.currentHeight + var1.getHeight();
         }

         var16.addSlot(var1);
         this.stitchSlots.add(var16);
         return true;
      }
   }

   public boolean allocateSlot(Stitcher$Holder var1) {
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
}
