package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemStack {
   public int animationsToGo;
   public EntityItemFrame itemFrame;
   public int itemDamage;
   public NBTTagCompound stackTagCompound;
   public boolean canDestroyCacheResult;
   public boolean canPlaceOnCacheResult;
   public Block canPlaceOnCacheBlock;
   public int stackSize;
   public Block canDestroyCacheBlock = null;
   public static DecimalFormat DECIMALFORMAT = new DecimalFormat("#.###");
   public Item item;

   public void onBlockDestroyed(World var1, Block var2, BlockPos var3, EntityPlayer var4) {
      boolean var5 = this.item.onBlockDestroyed(this, var1, var2, var3, var4);
      if (var5) {
         var4.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this.item)]);
      }
   }

   public boolean hasEffect() {
      return this.getItem().hasEffect(this);
   }

   public float getStrVsBlock(Block var1) {
      return this.getItem().getStrVsBlock(this, var1);
   }

   public void damageItem(int var1, EntityLivingBase var2) {
      if ((!(var2 instanceof EntityPlayer) || !((EntityPlayer)var2).bA.isCreativeMode)
         && this.isItemStackDamageable()
         && this.attemptDamageItem(var1, var2.getRNG())) {
         var2.renderBrokenItemStack(this);
         this.stackSize--;
         if (var2 instanceof EntityPlayer) {
            EntityPlayer var3 = (EntityPlayer)var2;
            var3.triggerAchievement(StatList.objectBreakStats[Item.getIdFromItem(this.item)]);
            if (this.stackSize == 0 && this.getItem() instanceof ItemBow) {
               var3.ca();
            }
         }

         if (this.stackSize < 0) {
            this.stackSize = 0;
         }

         this.itemDamage = 0;
      }
   }

   public Item getItem() {
      return this.item;
   }

   public void setTagInfo(String var1, NBTBase var2) {
      if (this.stackTagCompound == null) {
         this.setTagCompound(new NBTTagCompound());
      }

      this.stackTagCompound.setTag(var1, var2);
   }

   public EnumRarity getRarity() {
      return this.getItem().getRarity(this);
   }

   public void readFromNBT(NBTTagCompound var1) {
      if (var1.hasKey("id", 8)) {
         this.item = Item.getByNameOrId(var1.getString("id"));
      } else {
         this.item = Item.getItemById(var1.getShort("id"));
      }

      this.stackSize = var1.getByte("Count");
      this.itemDamage = var1.getShort("Damage");
      if (this.itemDamage < 0) {
         this.itemDamage = 0;
      }

      if (var1.hasKey("tag", 10)) {
         this.stackTagCompound = var1.getCompoundTag("tag");
         if (this.item != null) {
            this.item.updateItemStackNBT(this.stackTagCompound);
         }
      }
   }

   public ItemStack(Item var1, int var2) {
      this(var1, var2, 0);
   }

   public String getDisplayName() {
      String var1 = this.getItem().getItemStackDisplayName(this);
      if (this.stackTagCompound != null && this.stackTagCompound.hasKey("display", 10)) {
         NBTTagCompound var2 = this.stackTagCompound.getCompoundTag("display");
         if (var2.hasKey("Name", 8)) {
            var1 = var2.getString("Name");
         }
      }

      return var1;
   }

   public boolean onItemUse(EntityPlayer var1, World var2, BlockPos var3, EnumFacing var4, float var5, float var6, float var7) {
      boolean var8 = this.getItem().onItemUse(this, var1, var2, var3, var4, var5, var6, var7);
      if (var8) {
         var1.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this.item)]);
      }

      return var8;
   }

   public ItemStack(Block var1, int var2, int var3) {
      this(Item.getItemFromBlock(var1), var2, var3);
   }

   public boolean getHasSubtypes() {
      return this.item.getHasSubtypes();
   }

   public ItemStack(Item var1) {
      this(var1, 1);
   }

   public Multimap<String, AttributeModifier> getAttributeModifiers() {
      com.google.common.collect.Multimap var1;
      if (this.hasTagCompound() && this.stackTagCompound.hasKey("AttributeModifiers", 9)) {
         var1 = HashMultimap.create();
         NBTTagList var2 = this.stackTagCompound.getTagList("AttributeModifiers", 10);

         for (int var3 = 0; var3 < var2.tagCount(); var3++) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            AttributeModifier var5 = SharedMonsterAttributes.readAttributeModifierFromNBT(var4);
            if (var5 != null && var5.getID().getLeastSignificantBits() != 0L && var5.getID().getMostSignificantBits() != 0L) {
               var1.put(var4.getString("AttributeName"), var5);
            }
         }
      } else {
         var1 = this.getItem().getItemAttributeModifiers();
      }

      return (Multimap<String, AttributeModifier>)var1;
   }

   public boolean isOnItemFrame() {
      return this.itemFrame != null;
   }

   public ItemStack copy() {
      ItemStack var1 = new ItemStack(this.item, this.stackSize, this.itemDamage);
      if (this.stackTagCompound != null) {
         var1.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
      }

      return var1;
   }

   public void setRepairCost(int var1) {
      if (!this.hasTagCompound()) {
         this.stackTagCompound = new NBTTagCompound();
      }

      this.stackTagCompound.setInteger("RepairCost", var1);
   }

   public static boolean areItemsEqual(ItemStack var0, ItemStack var1) {
      return var0 == null && var1 == null ? true : (var0 != null && var1 != null ? var0.isItemEqual(var1) : false);
   }

   public int method_27845() {
      return this.hasTagCompound() && this.stackTagCompound.hasKey("RepairCost", 3) ? this.stackTagCompound.getInteger("RepairCost") : 0;
   }

   public IChatComponent getChatComponent() {
      ChatComponentText var1 = new ChatComponentText(this.getDisplayName());
      if (this.hasDisplayName()) {
         var1.getChatStyle().setItalic(true);
      }

      IChatComponent var2 = new ChatComponentText("[").appendSibling(var1).appendText("]");
      if (this.item != null) {
         NBTTagCompound var3 = new NBTTagCompound();
         this.writeToNBT(var3);
         var2.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new ChatComponentText(var3.toString())));
         var2.getChatStyle().setColor(this.getRarity().rarityColor);
      }

      return var2;
   }

   public void setItemDamage(int var1) {
      this.itemDamage = var1;
      if (this.itemDamage < 0) {
         this.itemDamage = 0;
      }
   }

   public static ItemStack loadItemStackFromNBT(NBTTagCompound var0) {
      ItemStack var1 = new ItemStack();
      var1.readFromNBT(var0);
      return var1.getItem() != null ? var1 : null;
   }

   public void addEnchantment(Enchantment var1, int var2) {
      if (this.stackTagCompound == null) {
         this.setTagCompound(new NBTTagCompound());
      }

      if (!this.stackTagCompound.hasKey("ench", 9)) {
         this.stackTagCompound.setTag("ench", new NBTTagList());
      }

      NBTTagList var3 = this.stackTagCompound.getTagList("ench", 10);
      NBTTagCompound var4 = new NBTTagCompound();
      var4.setShort("id", (short)var1.effectId);
      var4.setShort("lvl", (byte)var2);
      var3.appendTag(var4);
   }

   public static ItemStack copyItemStack(ItemStack var0) {
      return var0 == null ? null : var0.copy();
   }

   public NBTTagCompound getTagCompound() {
      return this.stackTagCompound;
   }

   public ItemStack(Block var1) {
      this(var1, 1);
   }

   public boolean hasTagCompound() {
      return this.stackTagCompound != null;
   }

   public boolean canEditBlocks() {
      return this.getItem().canItemEditBlocks();
   }

   public boolean method_27847() {
      return this.stackTagCompound != null && this.stackTagCompound.hasKey("ench", 9);
   }

   public boolean canPlaceOn(Block var1) {
      if (var1 == this.canPlaceOnCacheBlock) {
         return this.canPlaceOnCacheResult;
      } else {
         this.canPlaceOnCacheBlock = var1;
         if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanPlaceOn", 9)) {
            NBTTagList var2 = this.stackTagCompound.getTagList("CanPlaceOn", 8);

            for (int var3 = 0; var3 < var2.tagCount(); var3++) {
               Block var4 = Block.getBlockFromName(var2.getStringTagAt(var3));
               if (var4 == var1) {
                  this.canPlaceOnCacheResult = true;
                  return true;
               }
            }
         }

         this.canPlaceOnCacheResult = false;
         return false;
      }
   }

   public ItemStack useItemRightClick(World var1, EntityPlayer var2) {
      return this.getItem().onItemRightClick(this, var1, var2);
   }

   public NBTTagList getEnchantmentTagList() {
      return this.stackTagCompound == null ? null : this.stackTagCompound.getTagList("ench", 10);
   }

   public ItemStack(Item var1, int var2, int var3) {
      this.canDestroyCacheResult = false;
      this.canPlaceOnCacheBlock = null;
      this.canPlaceOnCacheResult = false;
      this.item = var1;
      this.stackSize = var2;
      this.itemDamage = var3;
      if (this.itemDamage < 0) {
         this.itemDamage = 0;
      }
   }

   public boolean getIsItemStackEqual(ItemStack var1) {
      return this.isItemStackEqual(var1);
   }

   public void onPlayerStoppedUsing(World var1, EntityPlayer var2, int var3) {
      this.getItem().onPlayerStoppedUsing(this, var1, var2, var3);
   }

   public ItemStack() {
      this.canDestroyCacheResult = false;
      this.canPlaceOnCacheBlock = null;
      this.canPlaceOnCacheResult = false;
   }

   public boolean attemptDamageItem(int var1, Random var2) {
      if (!this.isItemStackDamageable()) {
         return false;
      } else {
         if (var1 > 0) {
            int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, this);
            int var4 = 0;

            for (int var5 = 0; var3 > 0 && var5 < var1; var5++) {
               if (EnchantmentDurability.negateDamage(this, var3, var2)) {
                  var4++;
               }
            }

            var1 -= var4;
            if (var1 <= 0) {
               return false;
            }
         }

         this.itemDamage += var1;
         return this.itemDamage > this.getMaxDamage();
      }
   }

   public NBTTagCompound writeToNBT(NBTTagCompound var1) {
      ResourceLocation var2 = Item.itemRegistry.getNameForObject(this.item);
      var1.setString("id", var2 == null ? "minecraft:air" : var2.toString());
      var1.setByte("Count", (byte)this.stackSize);
      var1.setShort("Damage", (short)this.itemDamage);
      if (this.stackTagCompound != null) {
         var1.setTag("tag", this.stackTagCompound);
      }

      return var1;
   }

   public EnumAction getItemUseAction() {
      return this.getItem().getItemUseAction(this);
   }

   public void updateAnimation(World var1, Entity var2, int var3, boolean var4) {
      if (this.animationsToGo > 0) {
         this.animationsToGo--;
      }

      this.item.onUpdate(this, var1, var2, var3, var4);
   }

   @Override
   public String toString() {
      return this.stackSize + "x" + this.item.getUnlocalizedName() + "@" + this.itemDamage;
   }

   public int getMaxDamage() {
      return this.item.getMaxDamage();
   }

   public static boolean areItemStacksEqual(ItemStack var0, ItemStack var1) {
      return var0 == null && var1 == null ? true : (var0 != null && var1 != null ? var0.isItemStackEqual(var1) : false);
   }

   public void setTagCompound(NBTTagCompound var1) {
      this.stackTagCompound = var1;
   }

   public boolean isItemEqual(ItemStack var1) {
      return var1 != null && this.item == var1.item && this.itemDamage == var1.itemDamage;
   }

   public ItemStack setStackDisplayName(String var1) {
      if (this.stackTagCompound == null) {
         this.stackTagCompound = new NBTTagCompound();
      }

      if (!this.stackTagCompound.hasKey("display", 10)) {
         this.stackTagCompound.setTag("display", new NBTTagCompound());
      }

      this.stackTagCompound.getCompoundTag("display").setString("Name", var1);
      return this;
   }

   public String getUnlocalizedName() {
      return this.item.getUnlocalizedName(this);
   }

   public boolean isItemDamaged() {
      return this.isItemStackDamageable() && this.itemDamage > 0;
   }

   public static boolean areItemStackTagsEqual(ItemStack var0, ItemStack var1) {
      return var0 == null && var1 == null
         ? true
         : (
            var0 == null || var1 == null
               ? false
               : (
                  var0.stackTagCompound == null && var1.stackTagCompound != null
                     ? false
                     : var0.stackTagCompound == null || var0.stackTagCompound.equals(var1.stackTagCompound)
               )
         );
   }

   public ItemStack splitStack(int var1) {
      ItemStack var2 = new ItemStack(this.item, var1, this.itemDamage);
      if (this.stackTagCompound != null) {
         var2.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
      }

      this.stackSize -= var1;
      return var2;
   }

   public int getMaxStackSize() {
      return this.getItem().getItemStackLimit();
   }

   public List<String> getTooltip(EntityPlayer var1, boolean var2) {
      ArrayList var3 = Lists.newArrayList();
      String var4 = this.getDisplayName();
      if (this.hasDisplayName()) {
         var4 = EnumChatFormatting.ITALIC + var4;
      }

      var4 = var4 + EnumChatFormatting.RESET;
      if (var2) {
         String var5 = "";
         if (var4.length() > 0) {
            var4 = var4 + " (";
            var5 = ")";
         }

         int var6 = Item.getIdFromItem(this.item);
         if (this.getHasSubtypes()) {
            var4 = var4 + String.format("#%04d/%d%s", var6, this.itemDamage, var5);
         } else {
            var4 = var4 + String.format("#%04d%s", var6, var5);
         }
      } else if (!this.hasDisplayName() && this.item == Items.filled_map) {
         var4 = var4 + " #" + this.itemDamage;
      }

      var3.add(var4);
      int var15 = 0;
      if (this.hasTagCompound() && this.stackTagCompound.hasKey("HideFlags", 99)) {
         var15 = this.stackTagCompound.getInteger("HideFlags");
      }

      if ((var15 & 32) == 0) {
         this.item.addInformation(this, var1, var3, var2);
      }

      if (this.hasTagCompound()) {
         if ((var15 & 1) == 0) {
            NBTTagList var16 = this.getEnchantmentTagList();
            if (var16 != null) {
               for (int var7 = 0; var7 < var16.tagCount(); var7++) {
                  short var8 = var16.getCompoundTagAt(var7).getShort("id");
                  short var9 = var16.getCompoundTagAt(var7).getShort("lvl");
                  if (Enchantment.getEnchantmentById(var8) != null) {
                     var3.add(Enchantment.getEnchantmentById(var8).getTranslatedName(var9));
                  }
               }
            }
         }

         if (this.stackTagCompound.hasKey("display", 10)) {
            NBTTagCompound var17 = this.stackTagCompound.getCompoundTag("display");
            if (var17.hasKey("color", 3)) {
               if (var2) {
                  var3.add("Color: #" + Integer.toHexString(var17.getInteger("color")).toUpperCase());
               } else {
                  var3.add(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("item.dyed"));
               }
            }

            if (var17.method_26604("Lore") == 9) {
               NBTTagList var19 = var17.getTagList("Lore", 8);
               if (var19.tagCount() > 0) {
                  for (int var23 = 0; var23 < var19.tagCount(); var23++) {
                     var3.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC + var19.getStringTagAt(var23));
                  }
               }
            }
         }
      }

      Multimap var18 = this.getAttributeModifiers();
      if (!var18.isEmpty() && (var15 & 2) == 0) {
         var3.add("");

         for (Entry var24 : (Iterable<Entry>)(Iterable<?>)(var18.entries())) {
            AttributeModifier var27 = (AttributeModifier)var24.getValue();
            double var10 = var27.getAmount();
            if (var27.getID() == Item.f) {
               var10 += EnchantmentHelper.getModifierForCreature(this, EnumCreatureAttribute.UNDEFINED);
            }

            double var12;
            if (var27.getOperation() != 1 && var27.getOperation() != 2) {
               var12 = var10;
            } else {
               var12 = var10 * 100.0;
            }

            if (var10 > 0.0) {
               var3.add(
                  EnumChatFormatting.BLUE
                     + StatCollector.translateToLocalFormatted(
                        "attribute.modifier.plus." + var27.getOperation(),
                        DECIMALFORMAT.format(var12),
                        StatCollector.translateToLocal("attribute.name." + (String)var24.getKey())
                     )
               );
            } else if (var10 < 0.0) {
               var12 *= -1.0;
               var3.add(
                  EnumChatFormatting.RED
                     + StatCollector.translateToLocalFormatted(
                        "attribute.modifier.take." + var27.getOperation(),
                        DECIMALFORMAT.format(var12),
                        StatCollector.translateToLocal("attribute.name." + (String)var24.getKey())
                     )
               );
            }
         }
      }

      if (this.hasTagCompound() && this.getTagCompound().getBoolean("Unbreakable") && (var15 & 4) == 0) {
         var3.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("item.unbreakable"));
      }

      if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanDestroy", 9) && (var15 & 8) == 0) {
         NBTTagList var21 = this.stackTagCompound.getTagList("CanDestroy", 8);
         if (var21.tagCount() > 0) {
            var3.add("");
            var3.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("item.canBreak"));

            for (int var25 = 0; var25 < var21.tagCount(); var25++) {
               Block var28 = Block.getBlockFromName(var21.getStringTagAt(var25));
               if (var28 != null) {
                  var3.add(EnumChatFormatting.DARK_GRAY + var28.getLocalizedName());
               } else {
                  var3.add(EnumChatFormatting.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanPlaceOn", 9) && (var15 & 16) == 0) {
         NBTTagList var22 = this.stackTagCompound.getTagList("CanPlaceOn", 8);
         if (var22.tagCount() > 0) {
            var3.add("");
            var3.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("item.canPlace"));

            for (int var26 = 0; var26 < var22.tagCount(); var26++) {
               Block var29 = Block.getBlockFromName(var22.getStringTagAt(var26));
               if (var29 != null) {
                  var3.add(EnumChatFormatting.DARK_GRAY + var29.getLocalizedName());
               } else {
                  var3.add(EnumChatFormatting.DARK_GRAY + "missingno");
               }
            }
         }
      }

      if (var2) {
         if (this.isItemDamaged()) {
            var3.add("Durability: " + (this.getMaxDamage() - this.getItemDamage()) + " / " + this.getMaxDamage());
         }

         var3.add(EnumChatFormatting.DARK_GRAY + Item.itemRegistry.getNameForObject(this.item).toString());
         if (this.hasTagCompound()) {
            var3.add(EnumChatFormatting.DARK_GRAY + "NBT: " + this.getTagCompound().getKeySet().size() + " tag(s)");
         }
      }

      return var3;
   }

   public void setItem(Item var1) {
      this.item = var1;
   }

   public int getMetadata() {
      return this.itemDamage;
   }

   public int getMaxItemUseDuration() {
      return this.getItem().getMaxItemUseDuration(this);
   }

   public boolean canDestroy(Block var1) {
      if (var1 == this.canDestroyCacheBlock) {
         return this.canDestroyCacheResult;
      } else {
         this.canDestroyCacheBlock = var1;
         if (this.hasTagCompound() && this.stackTagCompound.hasKey("CanDestroy", 9)) {
            NBTTagList var2 = this.stackTagCompound.getTagList("CanDestroy", 8);

            for (int var3 = 0; var3 < var2.tagCount(); var3++) {
               Block var4 = Block.getBlockFromName(var2.getStringTagAt(var3));
               if (var4 == var1) {
                  this.canDestroyCacheResult = true;
                  return true;
               }
            }
         }

         this.canDestroyCacheResult = false;
         return false;
      }
   }

   public ItemStack(Block var1, int var2) {
      this(var1, var2, 0);
   }

   public void clearCustomName() {
      if (this.stackTagCompound != null && this.stackTagCompound.hasKey("display", 10)) {
         NBTTagCompound var1 = this.stackTagCompound.getCompoundTag("display");
         var1.removeTag("Name");
         if (var1.hasNoTags()) {
            this.stackTagCompound.removeTag("display");
            if (this.stackTagCompound.hasNoTags()) {
               this.setTagCompound((NBTTagCompound)null);
            }
         }
      }
   }

   public void onCrafting(World var1, EntityPlayer var2, int var3) {
      var2.addStat(StatList.objectCraftStats[Item.getIdFromItem(this.item)], var3);
      this.item.onCreated(this, var1, var2);
   }

   public boolean hasDisplayName() {
      return this.stackTagCompound == null
         ? false
         : (!this.stackTagCompound.hasKey("display", 10) ? false : this.stackTagCompound.getCompoundTag("display").hasKey("Name", 8));
   }

   public void setItemFrame(EntityItemFrame var1) {
      this.itemFrame = var1;
   }

   public void hitEntity(EntityLivingBase var1, EntityPlayer var2) {
      boolean var3 = this.item.hitEntity(this, var1, var2);
      if (var3) {
         var2.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this.item)]);
      }
   }

   public NBTTagCompound getSubCompound(String var1, boolean var2) {
      if (this.stackTagCompound != null && this.stackTagCompound.hasKey(var1, 10)) {
         return this.stackTagCompound.getCompoundTag(var1);
      } else if (var2) {
         NBTTagCompound var3 = new NBTTagCompound();
         this.setTagInfo(var1, var3);
         return var3;
      } else {
         return null;
      }
   }

   public boolean method_27884() {
      return !this.getItem().isItemTool(this) ? false : !this.method_27847();
   }

   public EntityItemFrame getItemFrame() {
      return this.itemFrame;
   }

   public boolean isItemStackDamageable() {
      return this.item == null ? false : (this.item.getMaxDamage() <= 0 ? false : !this.hasTagCompound() || !this.getTagCompound().getBoolean("Unbreakable"));
   }

   public boolean isStackable() {
      return this.getMaxStackSize() > 1 && (!this.isItemStackDamageable() || !this.isItemDamaged());
   }

   public int getItemDamage() {
      return this.itemDamage;
   }

   public boolean interactWithEntity(EntityPlayer var1, EntityLivingBase var2) {
      return this.item.itemInteractionForEntity(this, var1, var2);
   }

   public ItemStack onItemUseFinish(World var1, EntityPlayer var2) {
      return this.getItem().onItemUseFinish(this, var1, var2);
   }

   public boolean isItemStackEqual(ItemStack var1) {
      return this.stackSize != var1.stackSize
         ? false
         : (
            this.item != var1.item
               ? false
               : (
                  this.itemDamage != var1.itemDamage
                     ? false
                     : (
                        this.stackTagCompound == null && var1.stackTagCompound != null
                           ? false
                           : this.stackTagCompound == null || this.stackTagCompound.equals(var1.stackTagCompound)
                     )
               )
         );
   }

   public boolean canHarvestBlock(Block var1) {
      return this.item.canHarvestBlock(var1);
   }
}
