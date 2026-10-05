package net.minecraft.block;

import com.cheatbreaker.client.nethandler.server.PacketCooldown;
import io.netty.util.concurrent.DefaultThreadFactory$DefaultRunnableDecorator;
import net.minecraft.client.renderer.EnumFaceDirection$Constants;
import net.minecraft.entity.passive.EntitySquid$AIMoveRandom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass1774;

public class BlockWorkbench$InterfaceCraftingTable implements IInteractionObject {
   public BlockPos position;
   public EnumFaceDirection$Constants field_0007;
   public World world;
   public BlockTallGrass field_0006;
   public DefaultThreadFactory$DefaultRunnableDecorator field_0000;
   public PacketCooldown field_0001;
   public C09PacketHeldItemChange field_0008;
   public UnidentifiedClass1774 field_0005;
   public EntitySquid$AIMoveRandom field_0002;

   @Override
   public boolean u_() {
      return false;
   }

   @Override
   public IChatComponent getDisplayName() {
      return new ChatComponentTranslation(Blocks.crafting_table.getUnlocalizedName() + ".name");
   }

   @Override
   public String getGuiID() {
      return "minecraft:crafting_table";
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      return new ContainerWorkbench(var1, this.world, this.position);
   }

   public BlockWorkbench$InterfaceCraftingTable(World var1, BlockPos var2) {
      this.world = var1;
      this.position = var2;
   }

   @Override
   public String z_() {
      return null;
   }
}
