package net.minecraft.client.model;

import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import net.minecraft.client.particle.EntityNoteFX$Factory;
import net.minecraft.nbt.NBTException;
import net.minecraft.world.MinecraftException;
import org.apache.log4j.jmx.LoggerDynamicMBean;

public class TextureOffset {
   public MinecraftException field_0003;
   public EntityNoteFX$Factory field_0005;
   public LoggerDynamicMBean field_0002;
   public int textureOffsetY;
   public NBTException field_0000;
   public WebSocketClientHandshakerFactory field_0001;
   public int textureOffsetX;

   public TextureOffset(int var1, int var2) {
      this.textureOffsetX = var1;
      this.textureOffsetY = var2;
   }
}
