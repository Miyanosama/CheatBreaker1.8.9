package io.netty.util.internal;

import java.security.PrivilegedAction;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.command.CommandBase$CoordinateArg;

public class PlatformDependent0$1 implements PrivilegedAction<ClassLoader> {
   public CommandBase$CoordinateArg __junk6025486474472462114;
   public BlockDoublePlant __junk2338736128084010767;

   public ClassLoader run() {
      return this.val$clazz.getClassLoader();
   }

   public PlatformDependent0$1(Class var1) {
      this.val$clazz = var1;
      super();
   }
}
