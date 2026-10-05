package net.minecraft.item;

import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms$Deserializer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

public class ItemMapBase extends Item {
   public GuiConfirmOpenLink field_0001;
   public ItemCameraTransforms$Deserializer field_0000;

   public Packet createMapDataPacket(ItemStack var1, World var2, EntityPlayer var3) {
      return null;
   }

   @Override
   public boolean isMap() {
      return true;
   }
}
