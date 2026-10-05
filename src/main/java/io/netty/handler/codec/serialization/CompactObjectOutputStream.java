package io.netty.handler.codec.serialization;

import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;

public class CompactObjectOutputStream extends ObjectOutputStream {
   public static final int TYPE_FAT_DESCRIPTOR = 0;
   public static final int TYPE_THIN_DESCRIPTOR = 1;

   public CompactObjectOutputStream(OutputStream var1) throws java.io.IOException {
      super(var1);
   }

   @Override
   public void writeClassDescriptor(ObjectStreamClass var1) throws java.io.IOException {
      Class var2 = var1.forClass();
      if (!var2.isPrimitive() && !var2.isArray() && !var2.isInterface() && var1.getSerialVersionUID() != 0L) {
         this.write(1);
         this.writeUTF(var1.getName());
      } else {
         this.write(0);
         super.writeClassDescriptor(var1);
      }
   }

   @Override
   public void writeStreamHeader() throws java.io.IOException {
      this.writeByte(5);
   }
}
