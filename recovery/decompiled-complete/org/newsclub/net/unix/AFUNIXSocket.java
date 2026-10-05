package org.newsclub.net.unix;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import net.minecraft.block.BlockHugeMushroom$1;
import net.minecraft.client.renderer.WorldVertexBufferUploader$1;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType;
import net.minecraft.world.gen.ChunkProviderSettings$Factory;
import org.json.JSONPointer;
import recovered.unidentified.UnidentifiedClass3330;

public class AFUNIXSocket extends Socket {
   public BlockHugeMushroom$1 field_0003;
   public ItemCameraTransforms$TransformType field_0006;
   public AFUNIXSocketImpl field_0002;
   public AFUNIXSocketAddress field_0005;
   public WorldVertexBufferUploader$1 field_0000;
   public ChunkProviderSettings$Factory field_0001;
   public JSONPointer field_0007;
   public UnidentifiedClass3330 field_0004;

   @Override
   public void bind(SocketAddress var1) {
      super.bind(var1);
      this.field_0005 = (AFUNIXSocketAddress)var1;
   }

   @Override
   public String toString() {
      return this.isConnected() ? "AFUNIXSocket[fd=" + this.field_0002.getFD() + ";path=" + this.field_0005.method_01607() + "]" : "AFUNIXSocket[unconnected]";
   }

   @Override
   public void connect(SocketAddress var1) {
      this.connect(var1, 0);
   }

   public static AFUNIXSocket method_25948(AFUNIXSocketAddress var0) {
      AFUNIXSocket var1 = method_25943();
      var1.connect(var0);
      return var1;
   }

   public static boolean method_25947() {
      return NativeUnixSocket.isLoaded();
   }

   @Override
   public void connect(SocketAddress var1, int var2) {
      if (!(var1 instanceof AFUNIXSocketAddress)) {
         throw new IOException("Can only connect to endpoints of type " + AFUNIXSocketAddress.class.getName());
      } else {
         this.field_0002.connect(var1, var2);
         this.field_0005 = (AFUNIXSocketAddress)var1;
         NativeUnixSocket.method_25802(this);
      }
   }

   public AFUNIXSocket(AFUNIXSocketImpl var1) {
      super(var1);

      try {
         NativeUnixSocket.method_25809(this);
      } catch (UnsatisfiedLinkError var3) {
         var3.printStackTrace();
      }
   }

   public static AFUNIXSocket method_25949() {
      AFUNIXSocketImpl var0 = new AFUNIXSocketImpl();
      AFUNIXSocket var1 = new AFUNIXSocket(var0);
      var1.field_0002 = var0;
      return var1;
   }

   public static AFUNIXSocket method_25943() {
      AFUNIXSocketImpl$Lenient var0 = new AFUNIXSocketImpl$Lenient();
      AFUNIXSocket var1 = new AFUNIXSocket(var0);
      var1.field_0002 = var0;
      return var1;
   }
}
