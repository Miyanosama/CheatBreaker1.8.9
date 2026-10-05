package net.minecraft.tileentity;

import com.google.gson.JsonParseException;
import io.netty.util.internal.logging.Slf4JLoggerFactory;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent$Action;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IChatComponent$Serializer;

public class TileEntitySign extends TileEntity {
   public Slf4JLoggerFactory field_0002;
   public int lineBeingEdited;
   public EntityPlayer player;
   public IChatComponent[] signText = new IChatComponent[]{
      new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText("")
   };
   public CommandResultStats stats;
   public boolean isEditable;

   public boolean executeCommand(EntityPlayer var1) {
      TileEntitySign$2 var2 = new TileEntitySign$2(this, var1);

      for (int var3 = 0; var3 < this.signText.length; var3++) {
         ChatStyle var4 = this.signText[var3] == null ? null : this.signText[var3].getChatStyle();
         if (var4 != null && var4.getChatClickEvent() != null) {
            ClickEvent var5 = var4.getChatClickEvent();
            if (var5.getAction() == ClickEvent$Action.field_0005) {
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
         String var3 = IChatComponent$Serializer.componentToJson(this.signText[var2]);
         var1.setString("Text" + (var2 + 1), var3);
      }

      this.stats.writeStatsToNBT(var1);
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      this.isEditable = false;
      super.readFromNBT(var1);
      TileEntitySign$1 var2 = new TileEntitySign$1(this);

      for (int var3 = 0; var3 < 4; var3++) {
         String var4 = var1.getString("Text" + (var3 + 1));

         try {
            IChatComponent var5 = IChatComponent$Serializer.jsonToComponent(var4);

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
