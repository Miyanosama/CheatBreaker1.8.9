package net.minecraft.tileentity;

import com.google.gson.JsonParseException;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TileEntitySign extends TileEntity {
   public int lineBeingEdited;
   public EntityPlayer player;
   public IChatComponent[] signText = new IChatComponent[]{
      new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText("")
   };
   public CommandResultStats stats;
   public boolean isEditable;

   public boolean executeCommand(final EntityPlayer var1) {
      ICommandSender var2 = new ICommandSender() {
         @Override
         public BlockPos getPosition() {
            return TileEntitySign.this.c;
         }

         @Override
         public void addChatMessage(IChatComponent var1x) {
         }

         @Override
         public World s_() {
            return var1.s_();
         }

         @Override
         public String z_() {
            return var1.z_();
         }

         @Override
         public Entity p_() {
            return var1;
         }

         @Override
         public IChatComponent getDisplayName() {
            return var1.getDisplayName();
         }

         @Override
         public Vec3 q_() {
            return new Vec3(TileEntitySign.this.c.getX() + 0.5, TileEntitySign.this.c.getY() + 0.5, TileEntitySign.this.c.getZ() + 0.5);
         }

         @Override
         public void setCommandStat(CommandResultStats.Type var1x, int var2x) {
            TileEntitySign.this.stats.setCommandStatScore(this, var1x, var2x);
         }

         @Override
         public boolean canCommandSenderUseCommand(int var1x, String var2x) {
            return var1x <= 2;
         }

         @Override
         public boolean C_() {
            return false;
         }
      };

      for (int var3 = 0; var3 < this.signText.length; var3++) {
         ChatStyle var4 = this.signText[var3] == null ? null : this.signText[var3].getChatStyle();
         if (var4 != null && var4.getChatClickEvent() != null) {
            ClickEvent var5 = var4.getChatClickEvent();
            if (var5.getAction() == ClickEvent.Action.RUN_COMMAND) {
               MinecraftServer.getServer().getCommandManager().executeCommand(var2, var5.getValue());
            }
         }
      }

      return true;
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);

      for (int var2 = 0; var2 < 4; var2++) {
         String var3 = IChatComponent.Serializer.componentToJson(this.signText[var2]);
         var1.setString("Text" + (var2 + 1), var3);
      }

      this.stats.writeStatsToNBT(var1);
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      this.isEditable = false;
      super.readFromNBT(var1);
      ICommandSender var2 = new ICommandSender() {
         @Override
         public boolean C_() {
            return false;
         }

         @Override
         public boolean canCommandSenderUseCommand(int var1, String var2x) {
            return true;
         }

         @Override
         public BlockPos getPosition() {
            return TileEntitySign.this.c;
         }

         @Override
         public void setCommandStat(CommandResultStats.Type var1, int var2x) {
         }

         @Override
         public Entity p_() {
            return null;
         }

         @Override
         public IChatComponent getDisplayName() {
            return new ChatComponentText(this.z_());
         }

         @Override
         public String z_() {
            return "Sign";
         }

         @Override
         public Vec3 q_() {
            return new Vec3(TileEntitySign.this.c.getX() + 0.5, TileEntitySign.this.c.getY() + 0.5, TileEntitySign.this.c.getZ() + 0.5);
         }

         @Override
         public World s_() {
            return TileEntitySign.this.b;
         }

         @Override
         public void addChatMessage(IChatComponent var1) {
         }
      };

      for (int var3 = 0; var3 < 4; var3++) {
         String var4 = var1.getString("Text" + (var3 + 1));

         try {
            IChatComponent var5 = IChatComponent.Serializer.jsonToComponent(var4);

            try {
               this.signText[var3] = ChatComponentProcessor.processComponent(var2, var5, (Entity)null);
            } catch (CommandException var7) {
               this.signText[var3] = var5;
            }
         } catch (JsonParseException var8) {
            this.signText[var3] = new ChatComponentText(var4);
         }
      }

      this.stats.readStatsFromNBT(var1);
   }

   @Override
   public boolean func_183000_F() {
      return true;
   }

   @Override
   public Packet getDescriptionPacket() {
      IChatComponent[] var1 = new IChatComponent[4];
      System.arraycopy(this.signText, 0, var1, 0, 4);
      return new S33PacketUpdateSign(this.b, this.c, var1);
   }

   public EntityPlayer getPlayer() {
      return this.player;
   }

   public TileEntitySign() {
      this.lineBeingEdited = -1;
      this.isEditable = true;
      this.stats = new CommandResultStats();
   }

   public boolean getIsEditable() {
      return this.isEditable;
   }

   public CommandResultStats getStats() {
      return this.stats;
   }

   public void setPlayer(EntityPlayer var1) {
      this.player = var1;
   }

   public void setEditable(boolean var1) {
      this.isEditable = var1;
      if (!var1) {
         this.player = null;
      }
   }
}
