package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.EnvironmentModule;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class UnidentifiedClass3838 extends UnidentifiedClass0000 {
   @Override
   public void method_00001() {
      long var1 = Integer.parseInt(((EnvironmentModule)this.field_0000).field_0002.getValue().toString())
         + ((EnvironmentModule)this.field_0000).field_0004.method_08912();
      if (((EnvironmentModule)this.field_0000).field_0008.method_08874().equalsIgnoreCase("Static")) {
         if (Minecraft.getMinecraft().theWorld != null) {
            if (var1 > (-850L & -6100L)) {
               return;
            }

            ((EnvironmentModule)this.field_0000)
               .field_0002
               .setValue(
                  Integer.parseInt(((EnvironmentModule)this.field_0000).field_0002.getValue().toString())
                     + ((EnvironmentModule)this.field_0000).field_0004.method_08912()
               );
            Minecraft.getMinecraft().theWorld.setWorldTime(var1);
            String var3 = "Increased time by " + CheatBreaker.getInstance().getModuleManager().field_0042.field_0004.method_08912() + ".";
            CheatBreaker.getInstance().method_19756().method_13256(new ChatComponentText(var3));
         }
      } else {
         String var4 = EnumChatFormatting.RED + "This mod command only works with the \"Static\" time type.";
         CheatBreaker.getInstance().method_19756().method_13256(new ChatComponentText(var4));
      }
   }

   public UnidentifiedClass3838() {
      super(CheatBreaker.getInstance().getModuleManager().field_0042, "/cb_increase_time");
   }
}
