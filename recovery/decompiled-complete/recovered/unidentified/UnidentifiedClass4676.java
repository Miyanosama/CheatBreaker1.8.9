package recovered.unidentified;

import io.netty.util.HashedWheelTimer$1;
import io.netty.util.internal.StringUtil;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.util.ChatStyle$Serializer;
import net.optifine.http.HttpResponse;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass4676 {
   public static int field_0003 = 7425;
   public static UnidentifiedClass0525 field_0006 = new UnidentifiedClass0525();
   public ChatStyle$Serializer field_0002;
   public StringUtil field_0005;
   public HashedWheelTimer$1 field_0000;
   public static UnidentifiedClass1590 field_0001 = new UnidentifiedClass1590(null);
   public HttpResponse field_0007;
   public EntityAINearestAttackableTarget field_0004;

   public static void method_28203(int var0, int var1, int var2, int var3) {
      if (var0 != field_0001.field_0005 || var1 != field_0001.field_0006 || var2 != field_0001.field_0001 || var3 != field_0001.field_0002) {
         field_0001.field_0005 = var0;
         field_0001.field_0006 = var1;
         field_0001.field_0001 = var2;
         field_0001.field_0002 = var3;
         OpenGlHelper.glBlendFunc(var0, var1, var2, var3);
      }
   }

   public static void method_28200(float var0, float var1, float var2, float var3) {
      if (var0 != field_0006.field_0004 || var1 != field_0006.field_0006 || var2 != field_0006.field_0001 || var3 != field_0006.field_0003) {
         field_0006.field_0004 = var0;
         field_0006.field_0006 = var1;
         field_0006.field_0001 = var2;
         field_0006.field_0003 = var3;
         GL11.glColor4f(var0, var1, var2, var3);
      }
   }

   public static void method_28199() {
      field_0001.field_0000.method_03839();
   }

   public static void method_28204() {
      field_0001.field_0000.method_03841();
   }

   public static void method_28201(int var0) {
      if (var0 != field_0003) {
         field_0003 = var0;
         GL11.glShadeModel(var0);
      }
   }

   public static void method_28202(int var0, int var1) {
      if (var0 != field_0001.field_0005 || var1 != field_0001.field_0006) {
         field_0001.field_0005 = var0;
         field_0001.field_0006 = var1;
         GL11.glBlendFunc(var0, var1);
      }
   }
}
