package net.minecraft.entity;

import io.netty.channel.DefaultFileRegion;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.ssl.SslHandler$5;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiStreamIndicator;
import net.minecraft.client.stream.IngestServerTester;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecart$EnumMinecartType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.world.World;
import net.optifine.shaders.ProgramStack;

public class EntityMinecartCommandBlock extends EntityMinecart {
   public ProgramStack field_0004;
   public CommandBlockLogic commandBlockLogic = new EntityMinecartCommandBlock$1(this);
   public IngestServerTester field_0003;
   public int activatorRailCooldown = 0;
   public SslHandler$5 field_0000;
   public GuiStreamIndicator field_0001;
   public DefaultFileRegion field_0008;
   public ContainerMerchant field_0005;
   public ContinuationWebSocketFrame field_0002;

   public EntityMinecartCommandBlock(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   @Override
   public IBlockState getDefaultDisplayTile() {
      return Blocks.command_block.getDefaultState();
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      this.commandBlockLogic.writeDataToNBT(var1);
   }

   @Override
   public void onDataWatcherUpdate(int var1) {
      super.onDataWatcherUpdate(var1);
      if (var1 == 24) {
         try {
            this.commandBlockLogic.setLastOutput(IChatComponent$Serializer.jsonToComponent(this.H().getWatchableObjectString(24)));
         } catch (Throwable var3) {
         }
      } else if (var1 == 23) {
         this.commandBlockLogic.setCommand(this.H().getWatchableObjectString(23));
      }
   }

   @Override
   public void onActivatorRailPass(int var1, int var2, int var3, boolean var4) {
      if (var4 && this.W - this.activatorRailCooldown >= 4) {
         this.getCommandBlockLogic().trigger(this.o);
         this.activatorRailCooldown = this.W;
      }
   }

   @Override
   public EntityMinecart$EnumMinecartType getMinecartType() {
      return EntityMinecart$EnumMinecartType.COMMAND_BLOCK;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.commandBlockLogic.readDataFromNBT(var1);
      this.H().updateObject(23, this.getCommandBlockLogic().getCommand());
      this.H().updateObject(24, IChatComponent$Serializer.componentToJson(this.getCommandBlockLogic().getLastOutput()));
   }

   @Override
   public boolean a_(EntityPlayer var1) {
      this.commandBlockLogic.tryOpenEditCommandBlock(var1);
      return false;
   }

   @Override
   public void k_() {
      super.k_();
      this.H().addObject(23, "");
      this.H().addObject(24, "");
   }

   public EntityMinecartCommandBlock(World var1) {
      super(var1);
   }

   public CommandBlockLogic getCommandBlockLogic() {
      return this.commandBlockLogic;
   }
}
