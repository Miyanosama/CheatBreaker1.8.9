package io.netty.util.internal;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import net.minecraft.item.ItemAxe;
import net.minecraft.network.play.client.C0CPacketInput;
import org.java_websocket.exceptions.IncompleteException;

public class ConcurrentSet<E> extends AbstractSet<E> implements Serializable {
   public static final long serialVersionUID = -6761513279741915432L;
   public ConcurrentMap<E, Boolean> map = PlatformDependent.newConcurrentHashMap();

   @Override
   public void clear() {
      this.map.clear();
   }

   @Override
   public boolean add(E var1) {
      return this.map.putIfAbsent((E)var1, Boolean.TRUE) == null;
   }

   @Override
   public boolean remove(Object var1) {
      return this.map.remove(var1) != null;
   }

   @Override
   public int size() {
      return this.map.size();
   }

   @Override
   public boolean contains(Object var1) {
      return this.map.containsKey(var1);
   }

   @Override
   public Iterator<E> iterator() {
      return this.map.keySet().iterator();
   }
}
