package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.TickEvent;
import io.netty.handler.codec.http.websocketx.WebSocketUtil;
import io.netty.util.concurrent.ImmediateEventExecutor$ImmediatePromise;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.optifine.expr.FunctionType$1;

public class UnidentifiedClass3379 {
   public LayerCape field_0005;
   public Minecraft field_0010 = Minecraft.getMinecraft();
   public WebSocketUtil field_0004;
   public float field_0009;
   public ImmediateEventExecutor$ImmediatePromise field_0001;
   public float field_0002;
   public FunctionType$1 field_0011;
   public int field_0008;
   public float field_0003;
   public boolean field_0012;
   public int field_0000;
   public CheatBreaker field_0006 = CheatBreaker.getInstance();
   public float field_0007;

   public void method_21020() {
      this.field_0010.gameSettings.thirdPersonView = this.field_0000;
      this.field_0012 = false;
   }

   public UnidentifiedClass3379() {
      this.field_0012 = false;
      this.field_0006
         .method_19817()
         .method_21938(
            TickEvent.class,
            var1 -> {
               if (this.field_0008 == 1 && this.field_0006.getModuleManager().field_0012.field_0004.method_08908()
                  || this.field_0008 == 2 && this.field_0006.getModuleManager().field_0012.field_0019.method_08908()) {
                  if (this.field_0012
                     && (this.field_0006.getGlobalSettings().field_0104.isPressed() || this.field_0006.getGlobalSettings().field_0060.isPressed())) {
                     this.method_21020();
                  }
               } else if (this.field_0012
                  && !this.field_0006.getGlobalSettings().field_0104.isKeyDown()
                  && !this.field_0006.getGlobalSettings().field_0060.isKeyDown()) {
                  this.method_21020();
               }
            }
         );
   }

   public void method_21021(int var1) {
      if (this.field_0006.getModuleManager().field_0012.isEnabled()) {
         this.field_0009 = this.field_0010.thePlayer.y;
         this.field_0007 = this.field_0010.thePlayer.z;
         this.field_0002 = this.field_0010.thePlayer.A;
         this.field_0003 = this.field_0010.thePlayer.B;
         this.field_0000 = this.field_0010.gameSettings.thirdPersonView;
         if (var1 == 1) {
            this.field_0010.gameSettings.thirdPersonView = 1;
         } else {
            this.field_0010.gameSettings.thirdPersonView = 2;
         }

         this.field_0012 = true;
         this.field_0008 = var1;
      }
   }
}
