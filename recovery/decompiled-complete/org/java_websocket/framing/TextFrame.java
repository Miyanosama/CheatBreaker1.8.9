package org.java_websocket.framing;

import io.netty.util.internal.NoOpTypeParameterMatcher;
import net.minecraft.client.particle.Barrier$Factory;
import net.minecraft.client.renderer.GlStateManager$TexGenState;
import net.minecraft.nbt.NBTTagFloat;
import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.util.Charsetfunctions;

public class TextFrame extends DataFrame {
   public Barrier$Factory field_0001;
   public NBTTagFloat field_0003;
   public NoOpTypeParameterMatcher field_0000;
   public GlStateManager$TexGenState field_0002;

   public TextFrame() {
      super(Opcode.TEXT);
   }

   @Override
   public void isValid() {
      super.isValid();
      if (!Charsetfunctions.isValidUTF8(this.getPayloadData())) {
         throw new InvalidDataException(1007, "Received text is no valid utf8 string!");
      }
   }
}
