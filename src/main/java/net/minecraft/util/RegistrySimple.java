package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrySimple<K, V> implements IRegistry<K, V> {
   public Map<K, V> registryObjects = this.createUnderlyingMap();
   public static Logger logger = LogManager.getLogger();

   @Override
   public Iterator<V> iterator() {
      return this.registryObjects.values().iterator();
   }

   public Set<K> getKeys() {
      return Collections.unmodifiableSet(this.registryObjects.keySet());
   }

   @Override
   public V getObject(K var1) {
      return this.registryObjects.get(var1);
   }

   @Override
   public void putObject(K var1, V var2) {
      Validate.notNull(var1);
      Validate.notNull(var2);
      if (this.registryObjects.containsKey(var1)) {
         logger.debug("Adding duplicate key '" + var1 + "' to registry");
      }

      this.registryObjects.put((K)var1, (V)var2);
   }

   public boolean containsKey(K var1) {
      return this.registryObjects.containsKey(var1);
   }

   public Map<K, V> createUnderlyingMap() {
      return Maps.newHashMap();
   }
}
