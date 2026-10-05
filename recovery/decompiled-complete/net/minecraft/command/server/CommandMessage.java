package net.minecraft.command.server;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$Traverser;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.renderer.BlockModelShapes$7;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.optifine.entity.model.CustomEntityModel;
import org.apache.log4j.jmx.MethodUnion;

public class CommandMessage extends CommandBase {
   public ConcurrentHashMapV8$Traverser field_0002;
   public CustomEntityModel field_0003;
   public BlockModelShapes$7 field_0000;
   public MethodUnion field_0001;

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length < 2) {
         throw new WrongUsageException("commands.message.usage");
      } else {
         EntityPlayerMP var3 = getPlayer(var1, var2[0]);
         if (var3 == var1) {
            throw new PlayerNotFoundException("commands.message.sameTarget");
         } else {
            IChatComponent var4 = getChatComponentFromNthArg(var1, var2, 1, !(var1 instanceof EntityPlayer));
            ChatComponentTranslation var5 = new ChatComponentTranslation("commands.message.display.incoming", var1.getDisplayName(), var4.createCopy());
            ChatComponentTranslation var6 = new ChatComponentTranslation("commands.message.display.outgoing", var3.getDisplayName(), var4.createCopy());
            var5.getChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(true);
            var6.getChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(true);
            var3.addChatMessage(var5);
            var1.addChatMessage(var6);
         }
      }
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.message.usage";
   }

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }

   @Override
   public String getCommandName() {
      return "tell";
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
   }

   @Override
   public List<String> getCommandAliases() {
      return Arrays.asList("w", "msg");
   }
}
