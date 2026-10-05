package org.java_websocket.framing;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.util.ByteBufferUtils;
import org.java_websocket.util.Charsetfunctions;

public class CloseFrame extends ControlFrame {
   public static int field_0008;
   public static int field_0017;
   public static int field_0007;
   public static int field_0014;
   public static int field_0002;
   public static int field_0003;
   public static int field_0018;
   public static int field_0012;
   public static int field_0004;
   public static int field_0019;
   public int code;
   public static int field_0009;
   public static int field_0011;
   public static int field_0006;
   public static int field_0013;
   public String reason;
   public static int field_0000;
   public static int field_0005;
   public static int field_0010;
   public static int field_0015;

   public int getCloseCode() {
      return this.code;
   }

   public void updatePayload() {
      byte[] var1 = Charsetfunctions.utf8Bytes(this.reason);
      ByteBuffer var2 = ByteBuffer.allocate(4);
      var2.putInt(this.code);
      ((Buffer)var2).position(2);
      ByteBuffer var3 = ByteBuffer.allocate(2 + var1.length);
      var3.put(var2);
      var3.put(var1);
      ((Buffer)var3).rewind();
      super.setPayload(var3);
   }

   @Override
   public String toString() {
      return super.toString() + "code: " + this.code;
   }

   public void setCode(int var1) {
      this.code = var1;
      if (var1 == 1015) {
         this.code = 1005;
         this.reason = "";
      }

      this.updatePayload();
   }

   @Override
   public void setPayload(ByteBuffer var1) {
      this.code = 1005;
      this.reason = "";
      ((Buffer)var1).mark();
      if (var1.remaining() == 0) {
         this.code = 1000;
      } else if (var1.remaining() == 1) {
         this.code = 1002;
      } else {
         if (var1.remaining() >= 2) {
            ByteBuffer var2 = ByteBuffer.allocate(4);
            ((Buffer)var2).position(2);
            var2.putShort(var1.getShort());
            ((Buffer)var2).position(0);
            this.code = var2.getInt();
         }

         ((Buffer)var1).reset();

         try {
            int var4 = var1.position();
            this.validateUtf8(var1, var4);
         } catch (InvalidDataException var3) {
            this.code = 1007;
            this.reason = null;
         }
      }
   }

   @Override
   public void isValid() {
      super.isValid();
      if (this.code == 1007 && this.reason.isEmpty()) {
         throw new InvalidDataException(1007, "Received text is no valid utf8 string!");
      } else if (this.code == 1005 && 0 < this.reason.length()) {
         throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
      } else if (this.code > 1015 && this.code < 3000) {
         throw new InvalidDataException(1002, "Trying to send an illegal close code!");
      } else if (this.code == 1006 || this.code == 1015 || this.code == 1005 || this.code > 4999 || this.code < 1000 || this.code == 1004) {
         throw new InvalidFrameException("closecode must not be sent over the wire: " + this.code);
      }
   }

   public CloseFrame() {
      super(Opcode.CLOSING);
      this.setReason("");
      this.setCode(1000);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null || this.getClass() != var1.getClass()) {
         return false;
      } else if (!super.equals(var1)) {
         return false;
      } else {
         CloseFrame var2 = (CloseFrame)var1;
         if (this.code != var2.code) {
            return false;
         } else {
            return this.reason != null ? this.reason.equals(var2.reason) : var2.reason == null;
         }
      }
   }

   public void setReason(String var1) {
      if (var1 == null) {
         var1 = "";
      }

      this.reason = var1;
      this.updatePayload();
   }

   public String getMessage() {
      return this.reason;
   }

   @Override
   public ByteBuffer getPayloadData() {
      return this.code == 1005 ? ByteBufferUtils.getEmptyByteBuffer() : super.getPayloadData();
   }

   public void validateUtf8(ByteBuffer var1, int var2) {
      try {
         ((Buffer)var1).position(var1.position() + 2);
         this.reason = Charsetfunctions.stringUtf8(var1);
      } catch (IllegalArgumentException var7) {
         throw new InvalidDataException(1007);
      } finally {
         ((Buffer)var1).position(var2);
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + this.code;
      return 31 * var1 + (this.reason != null ? this.reason.hashCode() : 0);
   }
}
