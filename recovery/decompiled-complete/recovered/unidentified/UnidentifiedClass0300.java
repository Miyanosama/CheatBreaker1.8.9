package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.AbstractFade;
import io.netty.channel.oio.OioByteStreamChannel;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.util.ResourceLocation;

public abstract class UnidentifiedClass0300 {
   public AbstractFade field_0001;
   public ResourceLocation field_0003;
   public OioByteStreamChannel field_0000;
   public String field_0002;

   public ResourceLocation method_02053() {
      return this.field_0003;
   }

   public String method_02056() {
      return this.field_0002;
   }

   public AbstractFade method_02052() {
      return this.field_0001;
   }

   public UnidentifiedClass0300(String var1, AbstractFade var2) {
      this.field_0001 = var2;
      var2.method_20200();
      this.field_0002 = var1;
      this.field_0003 = new ResourceLocation("client/emote/" + var1.toLowerCase().replace("-", "").replace(" ", "") + ".png");
   }

   public abstract void method_00244(AbstractClientPlayer var1, ModelPlayer var2, float var3);

   public boolean method_02055() {
      return this.field_0001.method_21210();
   }

   public abstract void method_00243(AbstractClientPlayer var1, float var2);

   public void method_02054(AbstractClientPlayer var1) {
      if (var1 != null) {
         if (var1.aK().equals(UUID.fromString(Minecraft.getMinecraft().getSession().getPlayerID())) && CheatBreaker.getInstance().method_19783().method_01367()
            )
          {
            Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
            CheatBreaker.getInstance().method_19783().method_01381(false);
            CheatBreaker.getInstance().method_19783().method_01385(false);
         }
      }
   }
}
