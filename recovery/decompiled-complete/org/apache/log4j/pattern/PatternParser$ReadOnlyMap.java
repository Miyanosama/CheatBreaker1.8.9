package org.apache.log4j.pattern;

import io.netty.channel.DefaultChannelPipeline$3;
import io.netty.channel.udt.nio.NioUdtMessageAcceptorChannel;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import junit.swingui.FailureRunView;
import net.minecraft.entity.monster.EntitySilverfish$AISummonSilverfish;
import net.minecraft.server.management.ServerConfigurationManager$1;

public class PatternParser$ReadOnlyMap implements Map {
   public ServerConfigurationManager$1 field_0003;
   public Map map;
   public EntitySilverfish$AISummonSilverfish field_0002;
   public NioUdtMessageAcceptorChannel field_0004;
   public DefaultChannelPipeline$3 field_0000;
   public FailureRunView field_0001;

   public Set entrySet() {
      return this.map.entrySet();
   }

   public Set keySet() {
      return this.map.keySet();
   }

   public boolean containsKey(Object var1) {
      return this.map.containsKey(var1);
   }

   public void clear() {
      throw new UnsupportedOperationException();
   }

   public Object remove(Object var1) {
      throw new UnsupportedOperationException();
   }

   public PatternParser$ReadOnlyMap(Map var1) {
      this.map = var1;
   }

   public void putAll(Map var1) {
      throw new UnsupportedOperationException();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public int size() {
      return this.map.size();
   }

   public boolean containsValue(Object var1) {
      return this.map.containsValue(var1);
   }

   public Collection values() {
      return this.map.values();
   }

   public Object get(Object var1) {
      return this.map.get(var1);
   }

   public Object put(Object var1, Object var2) {
      throw new UnsupportedOperationException();
   }
}
