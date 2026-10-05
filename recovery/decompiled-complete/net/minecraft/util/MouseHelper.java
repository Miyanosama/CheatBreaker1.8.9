package net.minecraft.util;

import io.netty.util.concurrent.DefaultThreadFactory;
import net.optifine.texture.TextureType;
import org.java_websocket.protocols.Protocol;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import recovered.unidentified.UnidentifiedClass4400;

public class MouseHelper {
   public int field_0003;
   public DefaultThreadFactory field_0005;
   public UnidentifiedClass4400 field_0002;
   public Protocol field_0004;
   public int field_0000;
   public TextureType field_0001;

   public void method_21148() {
      this.field_0003 = Mouse.getDX();
      this.field_0000 = Mouse.getDY();
   }

   public void ungrabMouseCursor() {
      Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
      Mouse.setGrabbed(false);
   }

   public void method_21147() {
      Mouse.setGrabbed(true);
      this.field_0003 = 0;
      this.field_0000 = 0;
   }
}
