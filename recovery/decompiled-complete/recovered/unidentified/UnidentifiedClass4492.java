package recovered.unidentified;

import com.cheatbreaker.client.util.ClientResourceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.spectator.SpectatorMenu$EndSpectatorObject;
import net.minecraft.entity.EntityTracker$1;
import net.minecraft.util.ChatComponentStyle$2;

public class UnidentifiedClass4492 {
   public EntityTracker$1 field_0002;
   public List<ClientResourceManager> field_0004;
   public List<ClientResourceManager> field_0001 = new ArrayList<>();
   public ChatComponentStyle$2 field_0003;
   public SpectatorMenu$EndSpectatorObject field_0000;

   public List<ClientResourceManager> method_27042() {
      return this.field_0001;
   }

   public List<ClientResourceManager> method_27046() {
      ArrayList var1 = new ArrayList();

      for (ClientResourceManager var3 : this.field_0004) {
         if (var3.method_20863().equals(Minecraft.getMinecraft().getSession().getPlayerID())) {
            var1.add(var3);
         }
      }

      for (ClientResourceManager var5 : this.field_0001) {
         if (var5.method_20863().equals(Minecraft.getMinecraft().getSession().getPlayerID())) {
            var1.add(var5);
         }
      }

      return var1;
   }

   public ClientResourceManager method_27045(UUID var1) {
      for (ClientResourceManager var3 : this.method_27042()) {
         if (var3.method_20849() && var1.toString().equals(var3.method_20863())) {
            return var3;
         }
      }

      return null;
   }

   public UnidentifiedClass4492() {
      this.field_0004 = new ArrayList<>();
   }

   public ClientResourceManager method_27048(UUID var1) {
      for (ClientResourceManager var3 : this.method_27041()) {
         if (var3.method_20849() && var1.toString().equals(var3.method_20863())) {
            return var3;
         }
      }

      return null;
   }

   public void method_27043(String var1) {
      this.method_27042().removeIf(var1x -> var1x.method_20863().equals(var1));
      this.method_27041().removeIf(var1x -> var1x.method_20863().equals(var1));
   }

   public List<ClientResourceManager> method_27041() {
      return this.field_0004;
   }
}
