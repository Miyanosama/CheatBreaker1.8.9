package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BlockModelRenderer$VertexTranslations;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.EnumTypeAdapterFactory;
import org.apache.log4j.lf5.DefaultLF5Configurator;

public class UnidentifiedClass3248 {
   public static float field_0004;
   public BlockModelRenderer$VertexTranslations field_0007;
   public DefaultLF5Configurator field_0003;
   public float field_0006;
   public ChatComponentStyle field_0000;
   public float field_0001;
   public static float field_0008;
   public EnumTypeAdapterFactory field_0005;
   public static UnidentifiedClass3248 field_0002 = new UnidentifiedClass3248();

   public void method_20197(TickEvent var1) {
      this.field_0006 = this.field_0001;
      EntityPlayerSP var2 = Minecraft.getMinecraft().thePlayer;
      if (var2 == null) {
         this.field_0001 = 1.62F;
      } else {
         if (var2.isSneaking()) {
            this.field_0001 = 1.54F;
         } else if (!CheatBreaker.getInstance().getModuleManager().field_0041.field_0009.method_08908()) {
            this.field_0001 = 1.62F;
         } else if (this.field_0001 < 1.62F) {
            float var3 = 1.62F - this.field_0001;
            var3 = (float)(var3 * 0.4);
            this.field_0001 = 1.62F - var3;
         }
      }
   }

   public float method_20196(float var1) {
      return !CheatBreaker.getInstance().getModuleManager().field_0041.field_0014.method_08908()
         ? this.field_0001
         : this.field_0006 + (this.field_0001 - this.field_0006) * var1;
   }

   public static UnidentifiedClass3248 method_20195() {
      return field_0002;
   }

   public UnidentifiedClass3248() {
      CheatBreaker.getInstance().method_19817().method_21938(TickEvent.class, this::method_20197);
   }
}
