package net.minecraft.client;

import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$WrappedFullHttpRequest;
import io.netty.util.concurrent.AbstractEventExecutor$EventExecutorIterator;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.item.EnumAction;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.log4j.pattern.PatternParser;

public class ClientBrandRetriever {
   public PatternParser field_0003;
   public AbstractEventExecutor$EventExecutorIterator field_0005;
   public NetHandlerLoginClient field_0002;
   public EnumAction field_0004;
   public NBTTagCompound field_0000;
   public EntityDragonPart field_0001;
   public HttpPostRequestEncoder$WrappedFullHttpRequest field_0006;

   public static String getClientModName() {
      return "vanilla";
   }
}
