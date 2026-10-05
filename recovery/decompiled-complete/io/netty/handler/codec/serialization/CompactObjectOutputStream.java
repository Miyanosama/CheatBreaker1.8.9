package io.netty.handler.codec.serialization;

import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;

public class CompactObjectOutputStream extends ObjectOutputStream {
   public static int TYPE_FAT_DESCRIPTOR;
   public static int TYPE_THIN_DESCRIPTOR;

   public CompactObjectOutputStream(OutputStream var1) {
      super(var1);
   }

   @Override
   public void writeClassDescriptor(ObjectStreamClass var1) {
      Class var2 = var1.forClass();
      if (!var2.isPrimitive() && !var2.isArray() && !var2.isInterface() && var1.getSerialVersionUID() != (119538835L & 4277222400245973284L)) {
         this.write(1);
         this.writeUTF(var1.getName());
      } else {
         this.write(0);
         super.writeClassDescriptor(var1);
      }
   }

   @Override
   public void writeStreamHeader() {
      this.writeByte(5);
   }
}
