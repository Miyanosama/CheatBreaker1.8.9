package net.minecraft.client.gui;

import io.netty.handler.codec.http.HttpObjectDecoder$State;
import javazoom.jl.decoder.LayerIIIDecoder$gr_info_s;
import net.minecraft.client.Minecraft;
import net.minecraft.command.server.CommandBanIp;
import net.minecraft.realms.RealmsScrolledSelectionList;
import net.optifine.entity.model.anim.RenderEntityParameterFloat;
import org.java_websocket.exceptions.InvalidFrameException;

public class GuiSlotRealmsProxy extends GuiSlot {
   public CommandBanIp field_0003;
   public LayerIIIDecoder$gr_info_s field_0005;
   public RealmsScrolledSelectionList field_0002;
   public RenderEntityParameterFloat field_0004;
   public HttpObjectDecoder$State field_0000;
   public InvalidFrameException field_0001;

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_0002.renderItem(var1, var2, var3, var4, var5, var6);
   }

   public int getMouseY() {
      return super.mouseY;
   }

   @Override
   public int getScrollBarX() {
      return this.field_0002.method_06326();
   }

   @Override
   public int getContentHeight() {
      return this.field_0002.method_06324();
   }

   @Override
   public boolean isSelected(int var1) {
      return this.field_0002.isSelectedItem(var1);
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.field_0002.selectItem(var1, var2, var3, var4);
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
   }

   @Override
   public void drawBackground() {
      this.field_0002.renderBackground();
   }

   public int method_29928() {
      return super.b;
   }

   public int getMouseX() {
      return super.mouseX;
   }

   @Override
   public int getSize() {
      return this.field_0002.method_06323();
   }

   public GuiSlotRealmsProxy(RealmsScrolledSelectionList var1, int var2, int var3, int var4, int var5, int var6) {
      super(Minecraft.getMinecraft(), var2, var3, var4, var5, var6);
      this.field_0002 = var1;
   }
}
