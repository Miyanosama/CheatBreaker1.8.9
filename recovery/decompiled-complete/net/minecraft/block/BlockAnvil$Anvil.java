package net.minecraft.block;

import com.cheatbreaker.client.network.CustomPayloadSender;
import io.netty.util.internal.NativeLibraryLoader;
import io.netty.util.internal.RecyclableMpscLinkedQueueNode;
import net.minecraft.command.PlayerSelector$7;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.optifine.shaders.ShaderPackDefault;
import recovered.unidentified.UnidentifiedClass0921;

public class BlockAnvil$Anvil implements IInteractionObject {
   public RecyclableMpscLinkedQueueNode field_0003;
   public NativeLibraryLoader field_0006;
   public BlockPos position;
   public CustomPayloadSender field_0005;
   public UnidentifiedClass0921 field_0000;
   public PlayerSelector$7 field_0001;
   public ShaderPackDefault field_0007;
   public World world;

   @Override
   public String z_() {
      return "anvil";
   }

   @Override
   public String getGuiID() {
      return "minecraft:anvil";
   }

   @Override
   public boolean u_() {
      return false;
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      return new ContainerRepair(var1, this.world, this.position, var2);
   }

   @Override
   public IChatComponent getDisplayName() {
      return new ChatComponentTranslation(Blocks.anvil.getUnlocalizedName() + ".name");
   }

   public BlockAnvil$Anvil(World var1, BlockPos var2) {
      this.world = var1;
      this.position = var2;
   }
}
