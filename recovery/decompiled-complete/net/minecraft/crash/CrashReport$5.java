package net.minecraft.crash;

import io.netty.channel.sctp.SctpChannelOption;
import java.util.concurrent.Callable;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.server.management.UserListBans;

public class CrashReport$5 implements Callable<String> {
   public GuiKeyBindingList field_0001;
   public UserListBans field_0003;
   public SctpChannelOption field_0002;

   public CrashReport$5(CrashReport var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      Runtime var1 = Runtime.getRuntime();
      long var2 = var1.maxMemory();
      long var4 = var1.totalMemory();
      long var6 = var1.freeMemory();
      long var8 = var2 / (8241798196706214945L & -8241798198196280238L) / (3436783487662449666L & -3436783489404232247L);
      long var10 = var4 / (1174471744L & 3622337724947781424L) / (-8903630269466801103L & 472426242L);
      long var12 = var6 / (603989144L & 21070945L) / (2089832413805937812L & 1089340419L);
      return var6 + " bytes (" + var12 + " MB) / " + var4 + " bytes (" + var10 + " MB) up to " + var2 + " bytes (" + var8 + " MB)";
   }
}
