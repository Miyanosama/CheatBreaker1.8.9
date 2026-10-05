package net.minecraft.command.server;

import io.netty.bootstrap.Bootstrap;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$ChestCorridor;
import net.optifine.entity.model.ModelAdapterSilverfish;
import net.optifine.gui.GuiMessage;

public class CommandEmote extends CommandBase {
   public Bootstrap field_0002;
   public StructureStrongholdPieces$ChestCorridor field_0003;
   public ModelAdapterSilverfish field_0000;
   public GuiMessage field_0001;

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length <= 0) {
         throw new WrongUsageException("commands.me.usage");
      } else {
         IChatComponent var3 = getChatComponentFromNthArg(var1, var2, 0, !(var1 instanceof EntityPlayer));
         MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentTranslation("chat.type.emote", var1.getDisplayName(), var3));
      }
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.me.usage";
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public String getCommandName() {
      return "me";
   }
}
