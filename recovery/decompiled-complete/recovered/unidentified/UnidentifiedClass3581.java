package recovered.unidentified;

import io.netty.channel.AbstractChannelHandlerContext$8;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.command.CommandTitle;
import net.minecraft.init.Blocks;

public class UnidentifiedClass3581 {
   public static ExecutorService field_0002 = Executors.newSingleThreadExecutor();
   public AbstractChannelHandlerContext$8 field_0004;
   public GuiSlot field_0001;
   public CommandTitle field_0003;
   public Blocks field_0000;

   public static void method_22037(Runnable var0) {
      field_0002.execute(var0);
   }

   public static void method_22036() {
      Minecraft.getMinecraft().gameSettings.saveOptions();
      field_0002.shutdown();
   }
}
