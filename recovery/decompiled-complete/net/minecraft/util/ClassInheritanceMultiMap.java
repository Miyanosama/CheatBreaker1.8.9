package net.minecraft.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.optifine.util.IteratorCache;

public class ClassInheritanceMultiMap<T> extends AbstractSet<T> {
   public Set<Class<?>> knownKeys;
   public List<T> values;
   public boolean empty;
   public static Set<Class<?>> field_181158_a = Collections.newSetFromMap(new ConcurrentHashMap<>());
   public Class<T> baseClass;
   public Map<Class<?>, List<T>> map = Maps.newHashMap();

   public void createLookup(Class<?> var1) {
      field_181158_a.add(var1);
      int var2 = this.values.size();

      for (int var3 = 0; var3 < var2; var3++) {
         Object var4 = this.values.get(var3);
         if (var1.isAssignableFrom(var4.getClass())) {
            this.addForClass((T)var4, var1);
         }
      }

      this.knownKeys.add(var1);
   }

   @Override
   public int size() {
      return this.values.size();
   }

   @Override
   public boolean remove(Object var1) {
      Object var2 = var1;
      boolean var3 = false;

      for (Class var5 : this.knownKeys) {
         if (var5.isAssignableFrom(var2.getClass())) {
            List var6 = this.map.get(var5);
            if (var6 != null && var6.remove(var2)) {
               var3 = true;
            }
         }
      }

      this.empty = this.values.size() == 0;
      return var3;
   }

   @Override
   public boolean add(T var1) {
      for (Class var3 : this.knownKeys) {
         if (var3.isAssignableFrom(var1.getClass())) {
            this.addForClass((T)var1, var3);
         }
      }

      this.empty = this.values.size() == 0;
      return true;
   }

   public <S> Iterable<S> getByClass(Class<S> var1) {
      return new ClassInheritanceMultiMap$1(this, var1);
   }

   public ClassInheritanceMultiMap(Class<T> var1) {
      this.knownKeys = Sets.newIdentityHashSet();
      this.values = Lists.newArrayList();
      this.baseClass = var1;
      this.knownKeys.add(var1);
      this.map.put(var1, this.values);

      for (Class var3 : field_181158_a) {
         this.createLookup(var3);
      }

      this.empty = this.values.size() == 0;
   }

   @Override
   public boolean contains(Object var1) {
      return Iterators.contains(this.getByClass(var1.getClass()).iterator(), var1);
   }

   @Override
   public Iterator<T> iterator() {
      return (Iterator<T>)(this.values.isEmpty() ? Iterators.emptyIterator() : IteratorCache.getReadOnly(this.values));
   }

   @Override
   public boolean isEmpty() {
      return this.empty;
   }

   public Class<?> initializeClassLookup(Class<?> var1) {
      if (this.baseClass.isAssignableFrom(var1)) {
         if (!this.knownKeys.contains(var1)) {
            this.createLookup(var1);
         }

         return var1;
      } else {
         throw new IllegalArgumentException("Don't know how to search for " + var1);
      }
   }

   public void addForClass(T var1, Class<?> var2) {
      List var3 = this.map.get(var2);
      if (var3 == null) {
         this.map.put(var2, Lists.newArrayList(new Object[]{var1}));
      } else {
         var3.add(var1);
      }

      this.empty = this.values.size() == 0;
   }
}
