package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBufUtil$ThreadLocalUnsafeDirectByteBuf$1;
import io.netty.util.HashedWheelTimer$HashedWheelTimeout$1;
import java.util.List;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityBanner extends TileEntity {
   public int baseColor;
   public HashedWheelTimer$HashedWheelTimeout$1 field_0009;
   public ByteBufUtil$ThreadLocalUnsafeDirectByteBuf$1 field_0004;
   public List<EnumDyeColor> colorList;
   public List<TileEntityBanner$EnumBannerPattern> patternList;
   public ScreenChatOptions field_0006;
   public boolean field_175119_g;
   public String patternResourceLocation;
   public EntityPainting field_0000;
   public NBTTagList patterns;

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      setBaseColorAndPatterns(var1, this.baseColor, this.patterns);
   }

   public List<TileEntityBanner$EnumBannerPattern> getPatternList() {
      this.initializeBannerData();
      return this.patternList;
   }

   public static int method_24268(ItemStack var0) {
      NBTTagCompound var1 = var0.getSubCompound("BlockEntityTag", false);
      return var1 != null && var1.hasKey("Base") ? var1.getInteger("Base") : var0.getMetadata();
   }

   public static int getPatterns(ItemStack var0) {
      NBTTagCompound var1 = var0.getSubCompound("BlockEntityTag", false);
      return var1 != null && var1.hasKey("Patterns") ? var1.getTagList("Patterns", 10).tagCount() : 0;
   }

   public static void removeBannerData(ItemStack var0) {
      NBTTagCompound var1 = var0.getSubCompound("BlockEntityTag", false);
      if (var1 != null && var1.hasKey("Patterns", 9)) {
         NBTTagList var2 = var1.getTagList("Patterns", 10);
         if (var2.tagCount() > 0) {
            var2.removeTag(var2.tagCount() - 1);
            if (var2.hasNoTags()) {
               var0.getTagCompound().removeTag("BlockEntityTag");
               if (var0.getTagCompound().hasNoTags()) {
                  var0.setTagCompound((NBTTagCompound)null);
               }
            }
         }
      }
   }

   public List<EnumDyeColor> getColorList() {
      this.initializeBannerData();
      return this.colorList;
   }

   public NBTTagList getPatterns() {
      return this.patterns;
   }

   public static void setBaseColorAndPatterns(NBTTagCompound var0, int var1, NBTTagList var2) {
      var0.setInteger("Base", var1);
      if (var2 != null) {
         var0.setTag("Patterns", var2);
      }
   }

   public void setItemValues(ItemStack var1) {
      this.patterns = null;
      if (var1.hasTagCompound() && var1.getTagCompound().hasKey("BlockEntityTag", 10)) {
         NBTTagCompound var2 = var1.getTagCompound().getCompoundTag("BlockEntityTag");
         if (var2.hasKey("Patterns")) {
            this.patterns = (NBTTagList)var2.getTagList("Patterns", 10).copy();
         }

         if (var2.hasKey("Base", 99)) {
            this.baseColor = var2.getInteger("Base");
         } else {
            this.baseColor = var1.getMetadata() & 15;
         }
      } else {
         this.baseColor = var1.getMetadata() & 15;
      }

      this.patternList = null;
      this.colorList = null;
      this.patternResourceLocation = "";
      this.field_175119_g = true;
   }

   public String getPatternResourceLocation() {
      this.initializeBannerData();
      return this.patternResourceLocation;
   }

   public void initializeBannerData() {
      if (this.patternList == null || this.colorList == null || this.patternResourceLocation == null) {
         if (!this.field_175119_g) {
            this.patternResourceLocation = "";
         } else {
            this.patternList = Lists.newArrayList();
            this.colorList = Lists.newArrayList();
            this.patternList.add(TileEntityBanner$EnumBannerPattern.BASE);
            this.colorList.add(EnumDyeColor.byDyeDamage(this.baseColor));
            this.patternResourceLocation = "b" + this.baseColor;
            if (this.patterns != null) {
               for (int var1 = 0; var1 < this.patterns.tagCount(); var1++) {
                  NBTTagCompound var2 = this.patterns.getCompoundTagAt(var1);
                  TileEntityBanner$EnumBannerPattern var3 = TileEntityBanner$EnumBannerPattern.getPatternByID(var2.getString("Pattern"));
                  if (var3 != null) {
                     this.patternList.add(var3);
                     int var4 = var2.getInteger("Color");
                     this.colorList.add(EnumDyeColor.byDyeDamage(var4));
                     this.patternResourceLocation = this.patternResourceLocation + var3.getPatternID() + var4;
                  }
               }
            }
         }
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.baseColor = var1.getInteger("Base");
      this.patterns = var1.getTagList("Patterns", 10);
      this.patternList = null;
      this.colorList = null;
      this.patternResourceLocation = null;
      this.field_175119_g = true;
   }

   @Override
   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      return new S35PacketUpdateTileEntity(this.c, 6, var1);
   }

   public int getBaseColor() {
      return this.baseColor;
   }
}
