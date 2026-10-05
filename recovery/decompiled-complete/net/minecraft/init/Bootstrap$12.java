package net.minecraft.init;

import net.minecraft.client.model.ModelSheep1;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import org.apache.log4j.net.TelnetAppender;

public class Bootstrap$12 implements IBehaviorDispenseItem {
   public TelnetAppender field_0001;
   public ModelSheep1 field_0003;
   public Bootstrap$12 field_0000;
   public BehaviorDefaultDispenseItem field_150843_b = new BehaviorDefaultDispenseItem();

   @Override
   public ItemStack dispense(IBlockSource var1, ItemStack var2) {
      return ItemPotion.isSplash(var2.getMetadata()) ? new Bootstrap$12$1(this, var2).dispense(var1, var2) : this.field_150843_b.dispense(var1, var2);
   }
}
