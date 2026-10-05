package net.minecraft.entity.passive;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySheep extends EntityAnimal {
   public Vec3i field_0002;
   public int sheepTimer;
   public InventoryCrafting inventoryCrafting = new InventoryCrafting(new EntitySheep$1(this), 2, 1);
   public static Map<EnumDyeColor, float[]> DYE_TO_RGB = Maps.newEnumMap(EnumDyeColor.class);
   public EntityAIEatGrass entityAIEatGrass = new EntityAIEatGrass(this);

   @Override
   public void dropFewItems(boolean var1, int var2) {
      if (!this.getSheared()) {
         this.a(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, this.getFleeceColor().getMetadata()), 0.0F);
      }

      int var3 = this.V.nextInt(2) + 1 + this.V.nextInt(1 + var2);

      for (int var4 = 0; var4 < var3; var4++) {
         if (this.isBurning()) {
            this.dropItem(Items.cooked_mutton, 1);
         } else {
            this.dropItem(Items.mutton, 1);
         }
      }
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance var1, IEntityLivingData var2) {
      var2 = super.onInitialSpawn(var1, var2);
      this.setFleeceColor(getRandomSheepColor(this.o.s));
      return var2;
   }

   @Override
   public void onLivingUpdate() {
      if (this.o.D) {
         this.sheepTimer = Math.max(0, this.sheepTimer - 1);
      }

      super.onLivingUpdate();
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setBoolean("Sheared", this.getSheared());
      var1.setByte("Color", (byte)this.getFleeceColor().getMetadata());
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (var2 != null && var2.getItem() == Items.shears && !this.getSheared() && !this.o_()) {
         if (!this.o.D) {
            this.setSheared(true);
            int var3 = 1 + this.V.nextInt(3);

            for (int var4 = 0; var4 < var3; var4++) {
               EntityItem var5 = this.a(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, this.getFleeceColor().getMetadata()), 1.0F);
               var5.w = var5.w + this.V.nextFloat() * 0.05F;
               var5.v = var5.v + (this.V.nextFloat() - this.V.nextFloat()) * 0.1F;
               var5.x = var5.x + (this.V.nextFloat() - this.V.nextFloat()) * 0.1F;
            }
         }

         var2.damageItem(1, var1);
         this.playSound("mob.sheep.shear", 1.0F, 1.0F);
      }

      return super.interact(var1);
   }

   public float getHeadRotationPointY(float var1) {
      return this.sheepTimer <= 0
         ? 0.0F
         : (
            this.sheepTimer >= 4 && this.sheepTimer <= 36
               ? 1.0F
               : (this.sheepTimer < 4 ? (this.sheepTimer - var1) / 4.0F : -(this.sheepTimer - 40 - var1) / 4.0F)
         );
   }

   public boolean getSheared() {
      return (this.ac.getWatchableObjectByte(16) & 16) != 0;
   }

   @Override
   public void eatGrassBonus() {
      this.setSheared(false);
      if (this.o_()) {
         this.addGrowth(60);
      }
   }

   public EnumDyeColor getFleeceColor() {
      return EnumDyeColor.byMetadata(this.ac.getWatchableObjectByte(16) & 15);
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 10) {
         this.sheepTimer = 40;
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public String getHurtSound() {
      return "mob.sheep.say";
   }

   @Override
   public Item getDropItem() {
      return Item.getItemFromBlock(Blocks.wool);
   }

   static {
      DYE_TO_RGB.put(EnumDyeColor.WHITE, new float[]{1.0F, 1.0F, 1.0F});
      DYE_TO_RGB.put(EnumDyeColor.ORANGE, new float[]{0.85F, 0.5F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.MAGENTA, new float[]{0.7F, 0.3F, 0.85F});
      DYE_TO_RGB.put(EnumDyeColor.LIGHT_BLUE, new float[]{0.4F, 0.6F, 0.85F});
      DYE_TO_RGB.put(EnumDyeColor.YELLOW, new float[]{0.9F, 0.9F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.LIME, new float[]{0.5F, 0.8F, 0.1F});
      DYE_TO_RGB.put(EnumDyeColor.PINK, new float[]{0.95F, 0.5F, 0.65F});
      DYE_TO_RGB.put(EnumDyeColor.GRAY, new float[]{0.3F, 0.3F, 0.3F});
      DYE_TO_RGB.put(EnumDyeColor.SILVER, new float[]{0.6F, 0.6F, 0.6F});
      DYE_TO_RGB.put(EnumDyeColor.CYAN, new float[]{0.3F, 0.5F, 0.6F});
      DYE_TO_RGB.put(EnumDyeColor.PURPLE, new float[]{0.5F, 0.25F, 0.7F});
      DYE_TO_RGB.put(EnumDyeColor.BLUE, new float[]{0.2F, 0.3F, 0.7F});
      DYE_TO_RGB.put(EnumDyeColor.BROWN, new float[]{0.4F, 0.3F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.GREEN, new float[]{0.4F, 0.5F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.RED, new float[]{0.6F, 0.2F, 0.2F});
      DYE_TO_RGB.put(EnumDyeColor.BLACK, new float[]{0.1F, 0.1F, 0.1F});
   }

   public EntitySheep createChild(EntityAgeable var1) {
      EntitySheep var2 = (EntitySheep)var1;
      EntitySheep var3 = new EntitySheep(this.o);
      var3.setFleeceColor(this.getDyeColorMixFromParents(this, var2));
      return var3;
   }

   public void setFleeceColor(EnumDyeColor var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      this.ac.updateObject(16, (byte)(var2 & 240 | var1.getMetadata() & 15));
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23F);
   }

   @Override
   public String getDeathSound() {
      return "mob.sheep.say";
   }

   public static float[] getDyeRgb(EnumDyeColor var0) {
      return DYE_TO_RGB.get(var0);
   }

   public EntitySheep(World var1) {
      super(var1);
      this.setSize(0.9F, 1.3F);
      ((PathNavigateGround)this.s()).setAvoidsWater(true);
      this.i.addTask(0, new EntityAISwimming(this));
      this.i.addTask(1, new EntityAIPanic(this, 1.25));
      this.i.addTask(2, new EntityAIMate(this, 1.0));
      this.i.addTask(3, new EntityAITempt(this, 1.1, Items.wheat, false));
      this.i.addTask(4, new EntityAIFollowParent(this, 1.1));
      this.i.addTask(5, this.entityAIEatGrass);
      this.i.addTask(6, new EntityAIWander(this, 1.0));
      this.i.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.i.addTask(8, new EntityAILookIdle(this));
      this.inventoryCrafting.setInventorySlotContents(0, new ItemStack(Items.dye, 1, 0));
      this.inventoryCrafting.setInventorySlotContents(1, new ItemStack(Items.dye, 1, 0));
   }

   @Override
   public void k_() {
      super.k_();
      this.ac.addObject(16, new Byte((byte)0));
   }

   @Override
   public void playStepSound(BlockPos var1, Block var2) {
      this.playSound("mob.sheep.step", 0.15F, 1.0F);
   }

   public float getHeadRotationAngleX(float var1) {
      if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
         float var2 = (this.sheepTimer - 4 - var1) / 32.0F;
         return (float) (Math.PI / 5) + 0.2199115F * MathHelper.sin(var2 * 28.7F);
      } else {
         return this.sheepTimer > 0 ? (float) (Math.PI / 5) : this.z / (180.0F / (float)Math.PI);
      }
   }

   @Override
   public String getLivingSound() {
      return "mob.sheep.say";
   }

   public void setSheared(boolean var1) {
      byte var2 = this.ac.getWatchableObjectByte(16);
      if (var1) {
         this.ac.updateObject(16, (byte)(var2 | 16));
      } else {
         this.ac.updateObject(16, (byte)(var2 & -17));
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setSheared(var1.getBoolean("Sheared"));
      this.setFleeceColor(EnumDyeColor.byMetadata(var1.getByte("Color")));
   }

   @Override
   public float getEyeHeight() {
      return 0.95F * this.K;
   }

   public static EnumDyeColor getRandomSheepColor(Random var0) {
      int var1 = var0.nextInt(100);
      return var1 < 5
         ? EnumDyeColor.BLACK
         : (
            var1 < 10
               ? EnumDyeColor.GRAY
               : (var1 < 15 ? EnumDyeColor.SILVER : (var1 < 18 ? EnumDyeColor.BROWN : (var0.nextInt(500) == 0 ? EnumDyeColor.PINK : EnumDyeColor.WHITE)))
         );
   }

   public EnumDyeColor getDyeColorMixFromParents(EntityAnimal var1, EntityAnimal var2) {
      int var3 = ((EntitySheep)var1).getFleeceColor().getDyeDamage();
      int var4 = ((EntitySheep)var2).getFleeceColor().getDyeDamage();
      this.inventoryCrafting.getStackInSlot(0).setItemDamage(var3);
      this.inventoryCrafting.getStackInSlot(1).setItemDamage(var4);
      ItemStack var5 = CraftingManager.getInstance().findMatchingRecipe(this.inventoryCrafting, ((EntitySheep)var1).o);
      int var6;
      if (var5 != null && var5.getItem() == Items.dye) {
         var6 = var5.getMetadata();
      } else {
         var6 = this.o.s.nextBoolean() ? var3 : var4;
      }

      return EnumDyeColor.byDyeDamage(var6);
   }

   @Override
   public void updateAITasks() {
      this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
      super.updateAITasks();
   }
}
