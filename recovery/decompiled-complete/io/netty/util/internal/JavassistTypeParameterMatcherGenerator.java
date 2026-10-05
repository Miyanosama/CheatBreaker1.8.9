package io.netty.util.internal;

import com.jagrosh.discordipc.entities.pipe.WindowsPipe;
import io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Method;
import javassist.ClassClassPath;
import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import net.minecraft.world.ColorizerFoliage;
import net.optifine.http.HttpUtils;

public class JavassistTypeParameterMatcherGenerator {
   public SpdyHeaderBlockRawDecoder __junk2677184381012429206;
   public HttpUtils __junk5458160945256832789;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(JavassistTypeParameterMatcherGenerator.class);
   public WindowsPipe __junk1265806658950481876;
   public static ClassPool classPool = new ClassPool(true);
   public ColorizerFoliage __junk516570497921003971;

   public static String typeName(Class<?> var0) {
      return var0.isArray() ? typeName(var0.getComponentType()) + "[]" : var0.getName();
   }

   static {
      classPool.appendClassPath(new ClassClassPath(NoOpTypeParameterMatcher.class));
   }

   public static TypeParameterMatcher generate(Class<?> var0, ClassLoader var1) {
      String var2 = typeName(var0);
      String var3 = "io.netty.util.internal.__matchers__." + var2 + "Matcher";

      try {
         try {
            return (TypeParameterMatcher)Class.forName(var3, true, var1).newInstance();
         } catch (Exception var8) {
            CtClass var4 = classPool.getAndRename(NoOpTypeParameterMatcher.class.getName(), var3);
            var4.setModifiers(var4.getModifiers() | 16);
            var4.getDeclaredMethod("match").setBody("{ return $1 instanceof " + var2 + "; }");
            byte[] var5 = var4.toBytecode();
            var4.detach();
            Method var6 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            var6.setAccessible(true);
            Class var7 = (Class)var6.invoke(var1, var3, var5, 0, var5.length);
            if (var0 != Object.class) {
               logger.debug("Generated: {}", var7.getName());
            }

            return (TypeParameterMatcher)var7.newInstance();
         }
      } catch (RuntimeException var9) {
         throw var9;
      } catch (Exception var10) {
         throw new RuntimeException(var10);
      }
   }

   public static TypeParameterMatcher generate(Class<?> var0) {
      ClassLoader var1 = PlatformDependent.getContextClassLoader();
      if (var1 == null) {
         var1 = PlatformDependent.getSystemClassLoader();
      }

      return generate(var0, var1);
   }

   public static void appendClassPath(String var0) {
      classPool.appendClassPath(var0);
   }

   public static void appendClassPath(ClassPath var0) {
      classPool.appendClassPath(var0);
   }
}
