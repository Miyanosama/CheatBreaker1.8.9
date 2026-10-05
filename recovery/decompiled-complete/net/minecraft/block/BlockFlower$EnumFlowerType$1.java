package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.client.Minecraft$2;
import org.apache.log4j.net.JMSSink;

public class BlockFlower$EnumFlowerType$1 implements Predicate<BlockFlower$EnumFlowerType> {
   public JMSSink field_0001;
   public Minecraft$2 field_0002;

   public boolean method_24198(BlockFlower$EnumFlowerType var1) {
      return var1.getBlockType() == this.field_0000;
   }

   public BlockFlower$EnumFlowerType$1(BlockFlower$EnumFlowerColor var1) {
      this.field_0000 = var1;
      super();
   }
}
