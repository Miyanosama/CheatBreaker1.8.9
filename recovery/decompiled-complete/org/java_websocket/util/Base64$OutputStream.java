package org.java_websocket.util;

import com.cheatbreaker.client.nethandler.client.PacketVoiceChannelSwitch;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.minecraft.client.audio.SoundPoolEntry;

public class Base64$OutputStream extends FilterOutputStream {
   public int options;
   public byte[] buffer;
   public SoundPoolEntry field_0004;
   public boolean suspendEncoding;
   public byte[] decodabet;
   public int position;
   public byte[] b4;
   public int lineLength;
   public PacketVoiceChannelSwitch field_0003;
   public int bufferLength;
   public boolean breakLines;
   public boolean encode;

   @Override
   public void write(byte[] var1, int var2, int var3) {
      if (this.suspendEncoding) {
         this.out.write(var1, var2, var3);
      } else {
         for (int var4 = 0; var4 < var3; var4++) {
            this.write(var1[var2 + var4]);
         }
      }
   }

   public Base64$OutputStream(OutputStream var1, int var2) {
      super(var1);
      this.breakLines = (var2 & 8) != 0;
      this.encode = (var2 & 1) != 0;
      this.bufferLength = this.encode ? 3 : 4;
      this.buffer = new byte[this.bufferLength];
      this.position = 0;
      this.lineLength = 0;
      this.suspendEncoding = false;
      this.b4 = new byte[4];
      this.options = var2;
      this.decodabet = Base64.access$000(var2);
   }

   public void flushBase64() {
      if (this.position > 0) {
         if (!this.encode) {
            throw new IOException("Base64 input not properly padded.");
         }

         this.out.write(Base64.access$100(this.b4, this.buffer, this.position, this.options));
         this.position = 0;
      }
   }

   @Override
   public void write(int var1) {
      if (this.suspendEncoding) {
         this.out.write(var1);
      } else {
         if (this.encode) {
            this.buffer[this.position++] = (byte)var1;
            if (this.position >= this.bufferLength) {
               this.out.write(Base64.access$100(this.b4, this.buffer, this.bufferLength, this.options));
               this.lineLength += 4;
               if (this.breakLines && this.lineLength >= 76) {
                  this.out.write(10);
                  this.lineLength = 0;
               }

               this.position = 0;
            }
         } else if (this.decodabet[var1 & 127] > -5) {
            this.buffer[this.position++] = (byte)var1;
            if (this.position >= this.bufferLength) {
               int var2 = Base64.access$200(this.buffer, 0, this.b4, 0, this.options);
               this.out.write(this.b4, 0, var2);
               this.position = 0;
            }
         } else if (this.decodabet[var1 & 127] != -5) {
            throw new IOException("Invalid character in Base64 data.");
         }
      }
   }

   public Base64$OutputStream(OutputStream var1) {
      this(var1, 1);
   }

   @Override
   public void close() {
      this.flushBase64();
      super.close();
      this.buffer = null;
      this.out = null;
   }
}
