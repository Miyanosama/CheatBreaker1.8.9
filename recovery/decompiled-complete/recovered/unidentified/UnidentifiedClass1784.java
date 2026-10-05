package recovered.unidentified;

import io.netty.handler.codec.http.multipart.HttpPostBodyUtil$SeekAheadNoBackArrayException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.network.PingResponseHandler;
import net.minecraft.stats.StatBase$2;
import net.minecraft.stats.StatList;
import net.optifine.entity.model.ModelAdapterChest;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.IExpressionResolver;

public class UnidentifiedClass1784 {
   public StatList field_0003;
   public PingResponseHandler field_0005;
   public StatBase$2 field_0002;
   public ModelAdapterChest field_0004;
   public HttpPostBodyUtil$SeekAheadNoBackArrayException field_0000;
   public ShaderUniform field_0001;

   public static void method_12378(String[] var0) {
      ExpressionParser var1 = new ExpressionParser((IExpressionResolver)null);

      while (true) {
         try {
            InputStreamReader var2 = new InputStreamReader(System.in);
            BufferedReader var3 = new BufferedReader(var2);
            String var4 = var3.readLine();
            if (var4.length() <= 0) {
               return;
            }

            IExpression var5 = var1.parse(var4);
            if (var5 instanceof IExpressionFloat) {
               IExpressionFloat var6 = (IExpressionFloat)var5;
               float var7 = var6.eval();
               System.out.println("" + var7);
            }

            if (var5 instanceof IExpressionBool) {
               IExpressionBool var9 = (IExpressionBool)var5;
               boolean var10 = var9.eval();
               System.out.println("" + var10);
            }
         } catch (Exception var8) {
            var8.printStackTrace();
         }
      }
   }
}
