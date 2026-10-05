package org.java_websocket.framing;

import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.world.gen.MapGenBase;
import org.apache.log4j.lf5.viewer.LogTable;
import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidFrameException;

public abstract class ControlFrame extends FramedataImpl1 {
   public LogTable field_0001;
   public MapGenBase field_0000;
   public GuiPlayerTabOverlay field_0002;

   @Override
   public void isValid() {
      if (!this.isFin()) {
         throw new InvalidFrameException("Control frame cant have fin==false set");
      } else if (this.isRSV1()) {
         throw new InvalidFrameException("Control frame cant have rsv1==true set");
      } else if (this.isRSV2()) {
         throw new InvalidFrameException("Control frame cant have rsv2==true set");
      } else if (this.isRSV3()) {
         throw new InvalidFrameException("Control frame cant have rsv3==true set");
      }
   }

   public ControlFrame(Opcode var1) {
      super(var1);
   }
}
