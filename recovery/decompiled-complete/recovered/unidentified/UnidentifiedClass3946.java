package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ResourcePackRepository$2;

public class UnidentifiedClass3946 {
   public int field_0004;
   public boolean field_0007;
   public CheatBreaker field_0003;
   public float field_0006;
   public float field_0000;
   public Minecraft field_0001 = Minecraft.getMinecraft();
   public float field_0008;
   public float field_0005;
   public ResourcePackRepository$2 field_0002;

   public void method_23796() {
      this.field_0001.gameSettings.thirdPersonView = this.field_0004;
      this.field_0007 = false;
   }

   public void method_23797(float var1, float var2) {
      float var3 = this.field_0005;
      float var4 = this.field_0008;
      this.field_0008 = (float)(this.field_0008 + (this.field_0003.getModuleManager().field_0012.field_0005.method_08908() ? -var1 : var1) * 0.15);
      this.field_0005 = (float)(this.field_0005 - (this.field_0003.getModuleManager().field_0012.field_0011.method_08908() ? -var2 : var2) * 0.15);
      if (this.field_0003.getModuleManager().field_0012.field_0007.method_08908()) {
         if (this.field_0005 < -90.0F) {
            this.field_0005 = -90.0F;
         }

         if (this.field_0005 > 90.0F) {
            this.field_0005 = 90.0F;
         }
      }

      this.field_0000 = this.field_0000 + (this.field_0005 - var3);
      this.field_0006 = this.field_0006 + (this.field_0008 - var4);
   }

   public UnidentifiedClass3946() {
      this.field_0003 = CheatBreaker.getInstance();
      this.field_0007 = false;
      this.field_0003.method_19817().method_21938(TickEvent.class, var1 -> {
         if (this.field_0003.getModuleManager().field_0012.field_0006.method_08908()) {
            if (this.field_0007 && this.field_0003.getGlobalSettings().field_0015.isPressed()) {
               this.method_23796();
            }
         } else if (this.field_0007 && !this.field_0003.getGlobalSettings().field_0015.isKeyDown()) {
            this.method_23796();
         }
      });
   }

   public void method_23799() {
      if (this.field_0003.getModuleManager().field_0012.isEnabled()) {
         this.field_0008 = this.field_0001.thePlayer.y;
         this.field_0005 = this.field_0001.thePlayer.z;
         this.field_0006 = this.field_0001.thePlayer.A;
         this.field_0000 = this.field_0001.thePlayer.B;
         this.field_0004 = this.field_0001.gameSettings.thirdPersonView;
         String var1 = this.field_0003.getModuleManager().field_0012.field_0017.method_08874();
         switch (var1) {
            case "Third":
               this.field_0001.gameSettings.thirdPersonView = 1;
               break;
            case "Reverse":
               this.field_0001.gameSettings.thirdPersonView = 2;
               break;
            default:
               this.field_0001.gameSettings.thirdPersonView = 0;
         }

         this.field_0007 = true;
      }
   }
}
