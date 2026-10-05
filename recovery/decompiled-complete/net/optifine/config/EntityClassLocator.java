package net.optifine.config;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceKeysTask;
import io.netty.util.internal.logging.Log4JLoggerFactory;
import net.minecraft.util.JsonSerializableSet;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;
import net.optifine.util.EntityUtils;
import recovered.unidentified.UnidentifiedClass3867;

public class EntityClassLocator implements IObjectLocator {
   public RegistrySimple field_0002;
   public UnidentifiedClass3867 field_0004;
   public ConcurrentHashMapV8$MapReduceKeysTask field_0001;
   public JsonSerializableSet field_0003;
   public Log4JLoggerFactory field_0000;

   @Override
   public Object getObject(ResourceLocation var1) {
      return EntityUtils.getEntityClassByName(var1.getResourcePath());
   }
}
