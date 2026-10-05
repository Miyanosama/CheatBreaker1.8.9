package com.cheatbreaker.client.util.friend;

import io.netty.util.Recycler$2;
import net.minecraft.block.BlockFlower$EnumFlowerType;
import net.minecraft.item.Item$4;
import net.minecraft.tileentity.MobSpawnerBaseLogic$WeightedRandomMinecart;
import net.optifine.entity.model.ModelAdapterHorse;
import net.optifine.gui.GuiButtonOF;

// $VF: synthetic class
public class Friend$1 {
   public MobSpawnerBaseLogic$WeightedRandomMinecart field_0003;
   public Item$4 field_0005;
   public GuiButtonOF field_0002;
   public BlockFlower$EnumFlowerType field_0004;
   public Recycler$2 field_0000;
   public ModelAdapterHorse field_0001;

   static {
      try {
         $SwitchMap$com$cheatbreaker$client$util$friend$Status[Status.AWAY.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$com$cheatbreaker$client$util$friend$Status[Status.BUSY.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$com$cheatbreaker$client$util$friend$Status[Status.HIDDEN.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
