package io.netty.handler.timeout;

import net.minecraft.entity.projectile.EntityWitherSkull;
import org.apache.log4j.helpers.QuietWriter;

public class ReadTimeoutException extends TimeoutException {
   public static final long serialVersionUID = 169287984113283421L;
   public static ReadTimeoutException INSTANCE = new ReadTimeoutException();
}
