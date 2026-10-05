package io.netty.handler.timeout;

import net.minecraft.village.VillageCollection;

public class WriteTimeoutException extends TimeoutException {
   public static final long serialVersionUID = -144786655770296065L;
   public static WriteTimeoutException INSTANCE = new WriteTimeoutException();
}
