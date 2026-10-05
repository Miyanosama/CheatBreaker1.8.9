package net.minecraft.network.play.client;

import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.realms.RealmsAnvilLevelStorageSource;
import net.minecraft.world.chunk.NibbleArray;

public enum C07PacketPlayerDigging$Action {
   DROP_ALL_ITEMS,
   DROP_ITEM,
   START_DESTROY_BLOCK,
   RELEASE_USE_ITEM,
   ABORT_DESTROY_BLOCK,
   STOP_DESTROY_BLOCK;

   public NibbleArray field_0000;
   public ItemFlintAndSteel field_0001;
   // $VF: synthetic field
   public static C07PacketPlayerDigging$Action[] $VALUES = new C07PacketPlayerDigging$Action[]{
      START_DESTROY_BLOCK, ABORT_DESTROY_BLOCK, C07PacketPlayerDigging$Action.STOP_DESTROY_BLOCK, DROP_ALL_ITEMS, DROP_ITEM, RELEASE_USE_ITEM
   };
   public RealmsAnvilLevelStorageSource field_0009;
}
