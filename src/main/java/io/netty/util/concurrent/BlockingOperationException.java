package io.netty.util.concurrent;

import net.minecraft.client.gui.spectator.BaseSpectatorGroup;
import net.minecraft.client.resources.data.BaseMetadataSectionSerializer;
import org.json.HTTPTokener;

public class BlockingOperationException extends IllegalStateException {
   public static final long serialVersionUID = 2462223247762460301L;

   public BlockingOperationException(String var1) {
      super(var1);
   }

   public BlockingOperationException(Throwable var1) {
      super(var1);
   }

   public BlockingOperationException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public BlockingOperationException() {
   }
}
