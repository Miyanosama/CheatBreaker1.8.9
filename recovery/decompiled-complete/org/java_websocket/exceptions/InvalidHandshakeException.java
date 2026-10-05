package org.java_websocket.exceptions;

import io.netty.handler.codec.spdy.SpdyHeaderBlockJZlibEncoder;
import net.minecraft.client.gui.ServerListEntryLanScan;
import net.minecraft.world.chunk.storage.AnvilSaveHandler;
import net.optifine.RandomTileEntity;
import net.optifine.TextureAnimation;

public class InvalidHandshakeException extends InvalidDataException {
   public AnvilSaveHandler field_0000;
   public static long field_0001;
   public SpdyHeaderBlockJZlibEncoder field_0004;
   public RandomTileEntity field_0003;
   public TextureAnimation field_0002;
   public ServerListEntryLanScan field_0005;

   public InvalidHandshakeException() {
      super(1002);
   }

   public InvalidHandshakeException(String var1) {
      super(1002, var1);
   }

   public InvalidHandshakeException(Throwable var1) {
      super(1002, var1);
   }

   public InvalidHandshakeException(String var1, Throwable var2) {
      super(1002, var1, var2);
   }
}
