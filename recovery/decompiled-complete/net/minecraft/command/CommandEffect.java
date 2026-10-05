package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MessageDeserializer;
import net.optifine.shaders.gui.GuiSliderShaderOption;
import org.apache.log4j.chainsaw.MyTableModel;
import org.java_websocket.AbstractWrappedByteChannel;

public class CommandEffect extends CommandBase {
   public MessageDeserializer field_0002;
   public AbstractWrappedByteChannel field_0003;
   public MyTableModel field_0000;
   public GuiSliderShaderOption field_0001;

   @Override
   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }

   @Override
   public String getCommandName() {
      return "effect";
   }

   @Override
   public String getCommandUsage(ICommandSender var1) {
      return "commands.effect.usage";
   }

   @Override
   public void processCommand(ICommandSender var1, String[] var2) {
      if (var2.length < 2) {
         throw new WrongUsageException("commands.effect.usage");
      } else {
         EntityLivingBase var3 = getEntity(var1, var2[0], EntityLivingBase.class);
         if (var2[1].equals("clear")) {
            if (var3.getActivePotionEffects().isEmpty()) {
               throw new CommandException("commands.effect.failure.notActive.all", var3.z_());
            }

            var3.clearActivePotions();
            notifyOperators(var1, this, "commands.effect.success.removed.all", var3.z_());
         } else {
            int var4;
            try {
               var4 = parseInt(var2[1], 1);
            } catch (NumberInvalidException var11) {
               Potion var6 = Potion.getPotionFromResourceLocation(var2[1]);
               if (var6 == null) {
                  throw var11;
               }

               var4 = var6.id;
            }

            int var5 = 600;
            int var12 = 30;
            int var7 = 0;
            if (var4 < 0 || var4 >= Potion.potionTypes.length || Potion.potionTypes[var4] == null) {
               throw new NumberInvalidException("commands.effect.notFound", var4);
            }

            Potion var8 = Potion.potionTypes[var4];
            if (var2.length >= 3) {
               var12 = parseInt(var2[2], 0, 1000000);
               if (var8.isInstant()) {
                  var5 = var12;
               } else {
                  var5 = var12 * 20;
               }
            } else if (var8.isInstant()) {
               var5 = 1;
            }

            if (var2.length >= 4) {
               var7 = parseInt(var2[3], 0, 255);
            }

            boolean var9 = true;
            if (var2.length >= 5 && "true".equalsIgnoreCase(var2[4])) {
               var9 = false;
            }

            if (var12 > 0) {
               PotionEffect var10 = new PotionEffect(var4, var5, var7, false, var9);
               var3.c(var10);
               notifyOperators(var1, this, "commands.effect.success", new ChatComponentTranslation(var10.getEffectName()), var4, var7, var3.z_(), var12);
            } else {
               if (!var3.isPotionActive(var4)) {
                  throw new CommandException("commands.effect.failure.notActive", new ChatComponentTranslation(var8.getName()), var3.z_());
               }

               var3.removePotionEffect(var4);
               notifyOperators(var1, this, "commands.effect.success.removed", new ChatComponentTranslation(var8.getName()), var3.z_());
            }
         }
      }
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 2;
   }

   @Override
   public List<String> addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos var3) {
      return var2.length == 1
         ? getListOfStringsMatchingLastWord(var2, this.getAllUsernames())
         : (
            var2.length == 2
               ? getListOfStringsMatchingLastWord(var2, Potion.getPotionLocations())
               : (var2.length == 5 ? getListOfStringsMatchingLastWord(var2, "true", "false") : null)
         );
   }

   public String[] getAllUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }
}
