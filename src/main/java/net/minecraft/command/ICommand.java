package net.minecraft.command;

import java.util.List;
import net.minecraft.util.BlockPos;

public interface ICommand extends Comparable<ICommand> {
   String getCommandName();

   List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3);

   boolean isUsernameIndex(String[] var1, int var2);

   void processCommand(ICommandSender var1, String[] var2) throws net.minecraft.command.CommandException ;

   String getCommandUsage(ICommandSender var1);

   boolean canCommandSenderUseCommand(ICommandSender var1);

   List<String> getCommandAliases();
}
