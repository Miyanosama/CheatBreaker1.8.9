package org.java_websocket.framing;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;
import org.java_websocket.util.ByteBufferUtils;

public abstract class FramedataImpl1 implements Framedata {
   public Opcode optcode;
   public boolean rsv2;
   public boolean transferemasked;
   public boolean rsv1;
   public boolean fin;
   public ByteBuffer unmaskedpayload;
   public boolean rsv3;

   @Override
   public int hashCode() {
      int var1 = this.fin ? 1 : 0;
      var1 = 31 * var1 + this.optcode.hashCode();
      var1 = 31 * var1 + (this.unmaskedpayload != null ? this.unmaskedpayload.hashCode() : 0);
      var1 = 31 * var1 + (this.transferemasked ? 1 : 0);
      var1 = 31 * var1 + (this.rsv1 ? 1 : 0);
      var1 = 31 * var1 + (this.rsv2 ? 1 : 0);
      return 31 * var1 + (this.rsv3 ? 1 : 0);
   }

   public abstract void isValid() throws org.java_websocket.exceptions.InvalidDataException ;

   public void setRSV1(boolean var1) {
      this.rsv1 = var1;
   }

   @Override
   public ByteBuffer getPayloadData() {
      return this.unmaskedpayload;
   }

   public void setFin(boolean var1) {
      this.fin = var1;
   }

   @Override
   public String toString() {
      return "Framedata{ optcode:"
         + this.getOpcode()
         + ", fin:"
         + this.isFin()
         + ", rsv1:"
         + this.isRSV1()
         + ", rsv2:"
         + this.isRSV2()
         + ", rsv3:"
         + this.isRSV3()
         + ", payloadlength:[pos:"
         + this.unmaskedpayload.position()
         + ", len:"
         + this.unmaskedpayload.remaining()
         + "], payload:"
         + (this.unmaskedpayload.remaining() > 1000 ? "(too big to display)" : new String(this.unmaskedpayload.array()))
         + '}';
   }

   @Override
   public boolean isRSV1() {
      return this.rsv1;
   }

   public void setPayload(ByteBuffer var1) {
      this.unmaskedpayload = var1;
   }

   @Override
   public boolean getTransfereMasked() {
      return this.transferemasked;
   }

   public FramedataImpl1(Opcode var1) {
      this.optcode = var1;
      this.unmaskedpayload = ByteBufferUtils.getEmptyByteBuffer();
      this.fin = true;
      this.transferemasked = false;
      this.rsv1 = false;
      this.rsv2 = false;
      this.rsv3 = false;
   }

   @Override
   public boolean isRSV2() {
      return this.rsv2;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         FramedataImpl1 var2 = (FramedataImpl1)var1;
         if (this.fin != var2.fin) {
            return false;
         } else if (this.transferemasked != var2.transferemasked) {
            return false;
         } else if (this.rsv1 != var2.rsv1) {
            return false;
         } else if (this.rsv2 != var2.rsv2) {
            return false;
         } else if (this.rsv3 != var2.rsv3) {
            return false;
         } else if (this.optcode != var2.optcode) {
            return false;
         } else {
            return this.unmaskedpayload != null ? this.unmaskedpayload.equals(var2.unmaskedpayload) : var2.unmaskedpayload == null;
         }
      } else {
         return false;
      }
   }

   @Override
   public void append(Framedata var1) {
      ByteBuffer var2 = var1.getPayloadData();
      if (this.unmaskedpayload == null) {
         this.unmaskedpayload = ByteBuffer.allocate(var2.remaining());
         ((Buffer)var2).mark();
         this.unmaskedpayload.put(var2);
         ((Buffer)var2).reset();
      } else {
         ((Buffer)var2).mark();
         ((Buffer)this.unmaskedpayload).position(this.unmaskedpayload.limit());
         ((Buffer)this.unmaskedpayload).limit(this.unmaskedpayload.capacity());
         if (var2.remaining() > this.unmaskedpayload.remaining()) {
            ByteBuffer var3 = ByteBuffer.allocate(var2.remaining() + this.unmaskedpayload.capacity());
            ((Buffer)this.unmaskedpayload).flip();
            var3.put(this.unmaskedpayload);
            var3.put(var2);
            this.unmaskedpayload = var3;
         } else {
            this.unmaskedpayload.put(var2);
         }

         ((Buffer)this.unmaskedpayload).rewind();
         ((Buffer)var2).reset();
      }

      this.fin = var1.isFin();
   }

   @Override
   public boolean isFin() {
      return this.fin;
   }

   @Override
   public Opcode getOpcode() {
      return this.optcode;
   }

   public static FramedataImpl1 get(Opcode var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Supplied opcode cannot be null");
      } else {
         switch (var0) {
            case PING:
               return new PingFrame();
            case PONG:
               return new PongFrame();
            case TEXT:
               return new TextFrame();
            case BINARY:
               return new BinaryFrame();
            case CLOSING:
               return new CloseFrame();
            case CONTINUOUS:
               return new ContinuousFrame();
            default:
               throw new IllegalArgumentException("Supplied opcode is invalid");
         }
      }
   }

   public void setTransferemasked(boolean var1) {
      this.transferemasked = var1;
   }

   @Override
   public boolean isRSV3() {
      return this.rsv3;
   }

   public void setRSV3(boolean var1) {
      this.rsv3 = var1;
   }

   public void setRSV2(boolean var1) {
      this.rsv2 = var1;
   }
}
