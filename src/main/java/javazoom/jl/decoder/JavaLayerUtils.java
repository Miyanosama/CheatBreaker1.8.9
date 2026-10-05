package javazoom.jl.decoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.stats.StatFileWriter;
import net.optifine.ConnectedProperties;
import org.apache.log4j.chainsaw.ControlPanel;

public class JavaLayerUtils {
   public static JavaLayerHook hook = null;

   public static Object deserialize(InputStream var0, Class var1) throws java.io.IOException {
      if (var1 == null) {
         throw new NullPointerException("cls");
      } else {
         Object var2 = deserialize(var0, var1);
         if (!var1.isInstance(var2)) {
            throw new InvalidObjectException("type of deserialized instance not of required class.");
         } else {
            return var2;
         }
      }
   }

   public static synchronized JavaLayerHook getHook() {
      return hook;
   }

   public static synchronized InputStream getResourceAsStream(String var0) {
      InputStream var1 = null;
      if (hook != null) {
         var1 = hook.getResourceAsStream(var0);
      } else {
         Class<JavaLayerUtils> var2 = JavaLayerUtils.class;
         var1 = var2.getResourceAsStream(var0);
      }

      return var1;
   }

   public static Object deserializeArrayResource(String var0, Class var1, int var2) throws java.io.IOException {
      InputStream var3 = getResourceAsStream(var0);
      if (var3 == null) {
         throw new IOException("unable to load resource '" + var0 + "'");
      } else {
         return deserializeArray(var3, var1, var2);
      }
   }

   public static Object deserializeArray(InputStream var0, Class var1, int var2) throws java.io.IOException {
      if (var1 == null) {
         throw new NullPointerException("elemType");
      } else if (var2 < -1) {
         throw new IllegalArgumentException("length");
      } else {
         Object var3 = deserialize(var0);
         Class var4 = var3.getClass();
         if (!var4.isArray()) {
            throw new InvalidObjectException("object is not an array");
         } else {
            Class var5 = var4.getComponentType();
            if (var5 != var1) {
               throw new InvalidObjectException("unexpected array component type");
            } else {
               if (var2 != -1) {
                  int var6 = Array.getLength(var3);
                  if (var6 != var2) {
                     throw new InvalidObjectException("array length mismatch");
                  }
               }

               return var3;
            }
         }
      }
   }

   public static Object deserialize(InputStream var0) throws java.io.IOException {
      if (var0 == null) {
         throw new NullPointerException("in");
      } else {
         ObjectInputStream var1 = new ObjectInputStream(var0);

         try {
            return var1.readObject();
         } catch (ClassNotFoundException var4) {
            throw new InvalidClassException(var4.toString());
         }
      }
   }

   public static synchronized void setHook(JavaLayerHook var0) {
      hook = var0;
   }

   public static void serialize(OutputStream var0, Object var1) throws java.io.IOException {
      if (var0 == null) {
         throw new NullPointerException("out");
      } else if (var1 == null) {
         throw new NullPointerException("obj");
      } else {
         ObjectOutputStream var2 = new ObjectOutputStream(var0);
         var2.writeObject(var1);
      }
   }
}
