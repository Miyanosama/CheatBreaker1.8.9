package net.minecraft.client;

import io.netty.channel.epoll.EpollServerSocketChannel$EpollServerSocketUnsafe;
import java.util.concurrent.Callable;
import net.minecraft.client.renderer.OpenGlHelper;
import net.optifine.shaders.uniform.ShaderUniform2i;
import org.apache.log4j.Priority;

public class Minecraft$7 implements Callable<String> {
   public ShaderUniform2i field_0001;
   public Priority field_0000;
   public EpollServerSocketChannel$EpollServerSocketUnsafe field_0002;

   public String call() {
      return OpenGlHelper.getCpu();
   }

   public Minecraft$7(Minecraft var1) {
      this.field_152389_a = var1;
      super();
   }
}
