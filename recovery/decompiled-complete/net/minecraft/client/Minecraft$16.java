package net.minecraft.client;

import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe;
import io.netty.handler.codec.http.cors.CorsConfig$1;
import java.util.concurrent.Callable;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass3906;

public class Minecraft$16 implements Callable<String> {
   public EpollSocketChannel$EpollSocketUnsafe field_0001;
   public UnidentifiedClass3906 field_0003;
   public CorsConfig$1 field_0000;

   public Minecraft$16(Minecraft var1) {
      this.field_79002_a = var1;
      super();
   }

   public String call() {
      return GL11.glGetString(7937) + " GL version " + GL11.glGetString(7938) + ", " + GL11.glGetString(7936);
   }
}
