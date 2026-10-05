package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.EnvironmentModule;
import io.netty.channel.rxtx.RxtxChannel$RxtxUnsafe$1;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.optifine.reflect.ReflectorForge;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$20;

public class UnidentifiedClass4432 extends UnidentifiedClass0000 {
   public LogBrokerMonitor$20 field_0002;
   public UnidentifiedClass1944 field_0003;
   public ReflectorForge field_0000;
   public EntityAISit field_0001;
   public RxtxChannel$RxtxUnsafe$1 field_0004;

   @Override
   public void method_00001() {
      long var1 = Integer.parseInt(((EnvironmentModule)this.field_0000).field_0002.getValue().toString())
         - ((EnvironmentModule)this.field_0000).field_0004.method_08912();
      if (((EnvironmentModule)this.field_0000).field_0008.method_08874().equalsIgnoreCase("Static")) {
         if (Minecraft.getMinecraft().theWorld != null) {
            if (var1 < (-20766L & -6496L)) {
               return;
            }

            ((EnvironmentModule)this.field_0000)
               .field_0002
               .setValue(
                  Integer.parseInt(((EnvironmentModule)this.field_0000).field_0002.getValue().toString())
                     - ((EnvironmentModule)this.field_0000).field_0004.method_08912()
               );
            Minecraft.getMinecraft().theWorld.setWorldTime(var1);
            String var3 = "Decreased time by " + CheatBreaker.getInstance().getModuleManager().field_0042.field_0004.method_08912() + ".";
            CheatBreaker.getInstance().method_19756().method_13256(new ChatComponentText(var3));
         }
      } else {
         String var4 = EnumChatFormatting.RED + "This mod command only works with the \"Static\" time type.";
         CheatBreaker.getInstance().method_19756().method_13256(new ChatComponentText(var4));
      }
   }

   public UnidentifiedClass4432() {
      super(CheatBreaker.getInstance().getModuleManager().field_0042, "/cb_decrease_time");
   }
}
