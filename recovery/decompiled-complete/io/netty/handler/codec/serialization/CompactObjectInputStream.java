package io.netty.handler.codec.serialization;

import java.io.EOFException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.StreamCorruptedException;
import net.minecraft.client.Minecraft$10;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.PlayerCapabilities;
import recovered.unidentified.UnidentifiedClass1388;

public class CompactObjectInputStream extends ObjectInputStream {
   public UnidentifiedClass1388 __junk8180590977119717852;
   public Framebuffer __junk4350275554086612042;
   public ClassResolver classResolver;
   public Minecraft$10 __junk4164573528634327297;
   public PlayerCapabilities __junk116086075108225938;

   @Override
   public Class<?> resolveClass(ObjectStreamClass var1) {
      Class var2;
      try {
         var2 = this.classResolver.resolve(var1.getName());
      } catch (ClassNotFoundException var4) {
         var2 = super.resolveClass(var1);
      }

      return var2;
   }

   @Override
   public ObjectStreamClass readClassDescriptor() {
      int var1 = this.read();
      if (var1 < 0) {
         throw new EOFException();
      } else {
         switch (var1) {
            case 0:
               return super.readClassDescriptor();
            case 1:
               String var2 = this.readUTF();
               Class var3 = this.classResolver.resolve(var2);
               return ObjectStreamClass.lookupAny(var3);
            default:
               throw new StreamCorruptedException("Unexpected class descriptor type: " + var1);
         }
      }
   }

   public CompactObjectInputStream(InputStream var1, ClassResolver var2) {
      super(var1);
      this.classResolver = var2;
   }

   @Override
   public void readStreamHeader() {
      int var1 = this.readByte() & 255;
      if (var1 != 5) {
         throw new StreamCorruptedException("Unsupported version: " + var1);
      }
   }
}
