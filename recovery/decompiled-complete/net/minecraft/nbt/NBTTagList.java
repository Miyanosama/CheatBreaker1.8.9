package net.minecraft.nbt;

import com.google.common.collect.Lists;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.List;
import net.minecraft.command.CommandWorldBorder;
import net.minecraft.entity.ai.EntityAIAvoidEntity$1;
import net.optifine.entity.model.CustomModelRegistry;
import net.optifine.expr.Token;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$4;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NBTTagList extends NBTBase {
   public List<NBTBase> tagList = Lists.newArrayList();
   public byte tagType = 0;
   public CustomModelRegistry field_0001;
   public CategoryNodeEditor$4 field_0007;
   public CommandWorldBorder field_0000;
   public EntityAIAvoidEntity$1 field_0003;
   public Token field_0004;
   public static Logger LOGGER = LogManager.getLogger();

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.tagList.hashCode();
   }

   @Override
   public boolean hasNoTags() {
      return this.tagList.isEmpty();
   }

   @Override
   public byte getId() {
      return 9;
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.read(-8470667258071285336L & 8470667256501125419L);
      if (var2 > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.tagType = var1.readByte();
         int var4 = var1.readInt();
         if (this.tagType == 0 && var4 > 0) {
            throw new RuntimeException("Missing type on ListTag");
         } else {
            var3.read((6801231903597906151L & -6801231904050240456L) * var4);
            this.tagList = Lists.newArrayListWithCapacity(var4);

            for (int var5 = 0; var5 < var4; var5++) {
               NBTBase var6 = NBTBase.createNewByType(this.tagType);
               var6.read(var1, var2 + 1, var3);
               this.tagList.add(var6);
            }
         }
      }
   }

   public String getStringTagAt(int var1) {
      if (var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = this.tagList.get(var1);
         return var2.getId() == 8 ? var2.getString() : var2.toString();
      } else {
         return "";
      }
   }

   public NBTBase removeTag(int var1) {
      return this.tagList.remove(var1);
   }

   @Override
   public boolean equals(Object var1) {
      if (super.equals(var1)) {
         NBTTagList var2 = (NBTTagList)var1;
         if (this.tagType == var2.tagType) {
            return this.tagList.equals(var2.tagList);
         }
      }

      return false;
   }

   public void appendTag(NBTBase var1) {
      if (var1.getId() == 0) {
         LOGGER.warn("Invalid TagEnd added to ListTag");
      } else {
         if (this.tagType == 0) {
            this.tagType = var1.getId();
         } else if (this.tagType != var1.getId()) {
            LOGGER.warn("Adding mismatching tag types to tag list");
            return;
         }

         this.tagList.add(var1);
      }
   }

   @Override
   public NBTBase copy() {
      NBTTagList var1 = new NBTTagList();
      var1.tagType = this.tagType;

      for (NBTBase var3 : this.tagList) {
         NBTBase var4 = var3.copy();
         var1.tagList.add(var4);
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("[");

      for (int var2 = 0; var2 < this.tagList.size(); var2++) {
         if (var2 != 0) {
            var1.append(',');
         }

         var1.append(var2).append(':').append(this.tagList.get(var2));
      }

      return var1.append(']').toString();
   }

   public int getTagType() {
      return this.tagType;
   }

   public NBTBase get(int var1) {
      return (NBTBase)(var1 >= 0 && var1 < this.tagList.size() ? this.tagList.get(var1) : new NBTTagEnd());
   }

   public int[] getIntArrayAt(int var1) {
      if (var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = this.tagList.get(var1);
         return var2.getId() == 11 ? ((NBTTagIntArray)var2).getIntArray() : new int[0];
      } else {
         return new int[0];
      }
   }

   public void set(int var1, NBTBase var2) {
      if (var2.getId() == 0) {
         LOGGER.warn("Invalid TagEnd added to ListTag");
      } else if (var1 >= 0 && var1 < this.tagList.size()) {
         if (this.tagType == 0) {
            this.tagType = var2.getId();
         } else if (this.tagType != var2.getId()) {
            LOGGER.warn("Adding mismatching tag types to tag list");
            return;
         }

         this.tagList.set(var1, var2);
      } else {
         LOGGER.warn("index out of bounds to set tag in tag list");
      }
   }

   public float getFloatAt(int var1) {
      if (var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = this.tagList.get(var1);
         return var2.getId() == 5 ? ((NBTTagFloat)var2).getFloat() : 0.0F;
      } else {
         return 0.0F;
      }
   }

   @Override
   public void write(DataOutput var1) {
      if (!this.tagList.isEmpty()) {
         this.tagType = this.tagList.get(0).getId();
      } else {
         this.tagType = 0;
      }

      var1.writeByte(this.tagType);
      var1.writeInt(this.tagList.size());

      for (int var2 = 0; var2 < this.tagList.size(); var2++) {
         this.tagList.get(var2).write(var1);
      }
   }

   public double getDoubleAt(int var1) {
      if (var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = this.tagList.get(var1);
         return var2.getId() == 6 ? ((NBTTagDouble)var2).getDouble() : 0.0;
      } else {
         return 0.0;
      }
   }

   public NBTTagCompound getCompoundTagAt(int var1) {
      if (var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = this.tagList.get(var1);
         return var2.getId() == 10 ? (NBTTagCompound)var2 : new NBTTagCompound();
      } else {
         return new NBTTagCompound();
      }
   }

   public int tagCount() {
      return this.tagList.size();
   }
}
