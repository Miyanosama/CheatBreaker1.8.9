package io.netty.util.concurrent;

import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.PlatformDependent;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Straight;
import recovered.unidentified.UnidentifiedClass1583;

public class FastThreadLocal<V> {
   public UnidentifiedClass1583 __junk3307540254585704547;
   public int index = InternalThreadLocalMap.nextVariableIndex();
   public static int variablesToRemoveIndex = InternalThreadLocalMap.nextVariableIndex();
   public StructureNetherBridgePieces$Straight __junk3320513160019139489;

   public void set(V var1) {
      if (var1 != InternalThreadLocalMap.UNSET) {
         this.set(InternalThreadLocalMap.get(), (V)var1);
      } else {
         this.remove();
      }
   }

   public V get() {
      return this.get(InternalThreadLocalMap.get());
   }

   public static void removeAll() {
      InternalThreadLocalMap var0 = InternalThreadLocalMap.getIfSet();
      if (var0 != null) {
         try {
            Object var1 = var0.indexedVariable(variablesToRemoveIndex);
            if (var1 != null && var1 != InternalThreadLocalMap.UNSET) {
               Set var2 = (Set)var1;
               FastThreadLocal[] var3 = var2.toArray(new FastThreadLocal[var2.size()]);

               for (FastThreadLocal var7 : var3) {
                  var7.remove(var0);
               }
            }
         } finally {
            InternalThreadLocalMap.remove();
         }
      }
   }

   public boolean isSet(InternalThreadLocalMap var1) {
      return var1 != null && var1.isIndexedVariableSet(this.index);
   }

   public V get(InternalThreadLocalMap var1) {
      Object var2 = var1.indexedVariable(this.index);
      return (V)(var2 != InternalThreadLocalMap.UNSET ? var2 : this.initialize(var1));
   }

   public void onRemoval(V var1) {
   }

   public void remove() {
      this.remove(InternalThreadLocalMap.getIfSet());
   }

   public V initialValue() {
      return null;
   }

   public V initialize(InternalThreadLocalMap var1) {
      Object var2 = null;

      try {
         var2 = this.initialValue();
      } catch (Exception var4) {
         PlatformDependent.throwException(var4);
      }

      var1.setIndexedVariable(this.index, var2);
      addToVariablesToRemove(var1, this);
      return (V)var2;
   }

   public static void addToVariablesToRemove(InternalThreadLocalMap var0, FastThreadLocal<?> var1) {
      Object var2 = var0.indexedVariable(variablesToRemoveIndex);
      Set var3;
      if (var2 != InternalThreadLocalMap.UNSET && var2 != null) {
         var3 = (Set)var2;
      } else {
         var3 = Collections.newSetFromMap(new IdentityHashMap());
         var0.setIndexedVariable(variablesToRemoveIndex, var3);
      }

      var3.add(var1);
   }

   public void remove(InternalThreadLocalMap var1) {
      if (var1 != null) {
         Object var2 = var1.removeIndexedVariable(this.index);
         removeFromVariablesToRemove(var1, this);
         if (var2 != InternalThreadLocalMap.UNSET) {
            try {
               this.onRemoval((V)var2);
            } catch (Exception var4) {
               PlatformDependent.throwException(var4);
            }
         }
      }
   }

   public static void destroy() {
      InternalThreadLocalMap.destroy();
   }

   public static void removeFromVariablesToRemove(InternalThreadLocalMap var0, FastThreadLocal<?> var1) {
      Object var2 = var0.indexedVariable(variablesToRemoveIndex);
      if (var2 != InternalThreadLocalMap.UNSET && var2 != null) {
         Set var3 = (Set)var2;
         var3.remove(var1);
      }
   }

   public void set(InternalThreadLocalMap var1, V var2) {
      if (var2 != InternalThreadLocalMap.UNSET) {
         if (var1.setIndexedVariable(this.index, var2)) {
            addToVariablesToRemove(var1, this);
         }
      } else {
         this.remove(var1);
      }
   }

   public static int size() {
      InternalThreadLocalMap var0 = InternalThreadLocalMap.getIfSet();
      return var0 == null ? 0 : var0.size();
   }

   public boolean isSet() {
      return this.isSet(InternalThreadLocalMap.getIfSet());
   }
}
