package net.minecraft.client.network;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker$1;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.block.properties.PropertyBool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanServerDetector {
   public WebSocketServerHandshaker$1 field_0001;
   public static Logger logger = LogManager.getLogger();
   public static AtomicInteger field_148551_a = new AtomicInteger(0);
   public PropertyBool field_0002;
}
