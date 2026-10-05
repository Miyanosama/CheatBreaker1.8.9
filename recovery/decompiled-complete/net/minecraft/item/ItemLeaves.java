package net.minecraft.item;

import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.passive.EntityRabbit$AIAvoidEntity;
import net.minecraft.nbt.JsonToNBT$Primitive;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$Penthouse;
import net.minecraft.world.storage.SaveDataMemoryStorage;
import recovered.unidentified.UnidentifiedClass0275;

public class ItemLeaves extends ItemBlock {
   public StructureOceanMonumentPieces$Penthouse field_0000;
   public JsonToNBT$Primitive field_0001;
   public BlockLeaves leaves;
   public UnidentifiedClass0275 field_0005;
   public EntityRabbit$AIAvoidEntity field_0003;
   public SaveDataMemoryStorage field_0004;

   @Override
   public int getColorFromItemStack(ItemStack var1, int var2) {
      return this.leaves.getRenderColor(this.leaves.getStateFromMeta(var1.getMetadata()));
   }

   @Override
   public int getMetadata(int var1) {
      return var1 | 4;
   }

   @Override
   public String getUnlocalizedName(ItemStack var1) {
      return super.getUnlocalizedName() + "." + this.leaves.getWoodType(var1.getMetadata()).getUnlocalizedName();
   }

   public ItemLeaves(BlockLeaves var1) {
      super(var1);
      this.leaves = var1;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }
}
