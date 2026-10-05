package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.ui.module.CBModulesGui;
import io.netty.util.internal.IntegerHolder;
import io.netty.util.internal.InternalThreadLocalMap;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import sun.misc.Unsafe;

public class ConcurrentHashMapV8<K, V> implements Serializable, ConcurrentMap<K, V> {
   public static long CELLSBUSY;
   public static Unsafe U;
   public transient ConcurrentHashMapV8$KeySetView<K, V> keySet;
   public static int MAX_ARRAY_SIZE;
   public static long CELLVALUE;
   public static int UNTREEIFY_THRESHOLD;
   public static int RESERVED;
   public static int MIN_TREEIFY_CAPACITY;
   public static int NCPU = Runtime.getRuntime().availableProcessors();
   public static long SIZECTL;
   public static AtomicInteger counterHashCodeGenerator = new AtomicInteger();
   public static int DEFAULT_CAPACITY;
   public transient volatile int cellsBusy;
   public static int TREEIFY_THRESHOLD;
   public transient volatile int transferOrigin;
   public transient volatile int transferIndex;
   public transient ConcurrentHashMapV8$EntrySetView<K, V> entrySet;
   public static int ASHIFT;
   public static long ABASE;
   public CBModulesGui __junk4581957044656708357;
   public static int MOVED;
   public transient volatile int sizeCtl;
   public static int MAXIMUM_CAPACITY;
   public static int SEED_INCREMENT;
   public static long TRANSFERINDEX;
   public static ObjectStreamField[] serialPersistentFields = new ObjectStreamField[]{
      new ObjectStreamField("segments", ConcurrentHashMapV8$Segment[].class),
      new ObjectStreamField("segmentMask", int.class),
      new ObjectStreamField("segmentShift", int.class)
   };
   public static float LOAD_FACTOR;
   public transient volatile ConcurrentHashMapV8$CounterCell[] counterCells;
   public static long TRANSFERORIGIN;
   public static long BASECOUNT;
   public transient ConcurrentHashMapV8$ValuesView<K, V> values;
   public static int DEFAULT_CONCURRENCY_LEVEL;
   public transient volatile ConcurrentHashMapV8$Node<K, V>[] table;
   public transient volatile ConcurrentHashMapV8$Node<K, V>[] nextTable;
   public static long serialVersionUID;
   public static int TREEBIN;
   public static int MIN_TRANSFER_STRIDE;
   public static int HASH_BITS;
   public transient volatile long baseCount;

   public void treeifyBin(ConcurrentHashMapV8$Node<K, V>[] var1, int var2) {
      if (var1 != null) {
         int var4;
         if ((var4 = var1.length) < 64) {
            if (var1 == this.table) {
               int var5 = this.sizeCtl;
               if (this.sizeCtl >= 0 && U.compareAndSwapInt(this, SIZECTL, var5, -2)) {
                  this.transfer(var1, null);
               }
            }
         } else {
            ConcurrentHashMapV8$Node var3;
            if ((var3 = tabAt(var1, var2)) != null && var3.hash >= 0) {
               synchronized (var3) {
                  if (tabAt(var1, var2) == var3) {
                     ConcurrentHashMapV8$TreeNode var7 = null;
                     ConcurrentHashMapV8$TreeNode var8 = null;

                     for (ConcurrentHashMapV8$Node var9 = var3; var9 != null; var9 = var9.next) {
                        ConcurrentHashMapV8$TreeNode var10 = new ConcurrentHashMapV8$TreeNode(var9.hash, var9.key, var9.val, null, null);
                        if ((var10.prev = var8) == null) {
                           var7 = var10;
                        } else {
                           var8.next = var10;
                        }

                        var8 = var10;
                     }

                     setTabAt(var1, var2, new ConcurrentHashMapV8$TreeBin<>(var7));
                  }
               }
            }
         }
      }
   }

   public void forEachValue(long var1, ConcurrentHashMapV8$Action<? super V> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         new ConcurrentHashMapV8$ForEachValueTask<>(null, this.batchFor(var1), 0, 0, this.table, var3).invoke();
      }
   }

   @Override
   public V get(Object var1) {
      int var8 = spread(var1.hashCode());
      ConcurrentHashMapV8$Node[] var2 = this.table;
      ConcurrentHashMapV8$Node var3;
      int var5;
      if (this.table != null && (var5 = var2.length) > 0 && (var3 = tabAt(var2, var5 - 1 & var8)) != null) {
         int var6 = var3.hash;
         if (var3.hash == var8) {
            Object var7 = var3.key;
            if (var3.key == var1 || var7 != null && var1.equals(var7)) {
               return var3.val;
            }
         } else if (var6 < 0) {
            ConcurrentHashMapV8$Node var4;
            return (var4 = var3.find(var8, var1)) != null ? var4.val : null;
         }

         while ((var3 = var3.next) != null) {
            if (var3.hash == var8) {
               Object var9 = var3.key;
               if (var3.key == var1 || var9 != null && var1.equals(var9)) {
                  return var3.val;
               }
            }
         }
      }

      return null;
   }

   public V putVal(K var1, V var2, boolean var3) {
      if (var1 != null && var2 != null) {
         int var4 = spread(var1.hashCode());
         int var5 = 0;
         ConcurrentHashMapV8$Node[] var6 = this.table;

         while (true) {
            int var8;
            while (var6 == null || (var8 = var6.length) == 0) {
               var6 = this.initTable();
            }

            ConcurrentHashMapV8$Node var7;
            int var9;
            if ((var7 = tabAt(var6, var9 = var8 - 1 & var4)) == null) {
               if (casTabAt(var6, var9, null, new ConcurrentHashMapV8$Node<>(var4, var1, var2, null))) {
                  break;
               }
            } else {
               int var10 = var7.hash;
               if (var7.hash == -1) {
                  var6 = this.helpTransfer(var6, var7);
               } else {
                  Object var11 = null;
                  synchronized (var7) {
                     if (tabAt(var6, var9) == var7) {
                        if (var10 < 0) {
                           if (var7 instanceof ConcurrentHashMapV8$TreeBin) {
                              var5 = 2;
                              ConcurrentHashMapV8$TreeNode var18;
                              if ((var18 = ((ConcurrentHashMapV8$TreeBin)var7).putTreeVal(var4, var1, var2)) != null) {
                                 var11 = var18.val;
                                 if (!var3) {
                                    var18.val = (V)var2;
                                 }
                              }
                           }
                        } else {
                           var5 = 1;
                           ConcurrentHashMapV8$Node var13 = var7;

                           while (true) {
                              if (var13.hash == var4) {
                                 Object var14 = var13.key;
                                 if (var13.key == var1 || var14 != null && var1.equals(var14)) {
                                    var11 = var13.val;
                                    if (!var3) {
                                       var13.val = (V)var2;
                                    }
                                    break;
                                 }
                              }

                              ConcurrentHashMapV8$Node var15 = var13;
                              if ((var13 = var13.next) == null) {
                                 var15.next = new ConcurrentHashMapV8$Node<>(var4, (K)var1, (V)var2, null);
                                 break;
                              }

                              var5++;
                           }
                        }
                     }
                  }

                  if (var5 != 0) {
                     if (var5 >= 8) {
                        this.treeifyBin(var6, var9);
                     }

                     if (var11 != null) {
                        return (V)var11;
                     }
                     break;
                  }
               }
            }
         }

         this.addCount(547095105L & 335765539L, var5);
         return null;
      } else {
         throw new NullPointerException();
      }
   }

   public <U> void forEach(long var1, ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends U> var3, ConcurrentHashMapV8$Action<? super U> var4) {
      if (var3 != null && var4 != null) {
         new ConcurrentHashMapV8$ForEachTransformedMappingTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public long sumCount() {
      ConcurrentHashMapV8$CounterCell[] var1 = this.counterCells;
      long var3 = this.baseCount;
      if (var1 != null) {
         for (int var5 = 0; var5 < var1.length; var5++) {
            ConcurrentHashMapV8$CounterCell var2;
            if ((var2 = var1[var5]) != null) {
               var3 += var2.value;
            }
         }
      }

      return var3;
   }

   public double reduceEntriesToDouble(
      long var1, ConcurrentHashMapV8$ObjectToDouble<Entry<K, V>> var3, double var4, ConcurrentHashMapV8$DoubleByDoubleToDouble var6
   ) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8$MapReduceEntriesToDoubleTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public V getOrDefault(Object var1, V var2) {
      Object var3;
      return (V)((var3 = this.get(var1)) == null ? var2 : var3);
   }

   public V reduceValues(long var1, ConcurrentHashMapV8$BiFun<? super V, ? super V, ? extends V> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8$ReduceValuesTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3).invoke();
      }
   }

   public int reduceKeysToInt(long var1, ConcurrentHashMapV8$ObjectToInt<? super K> var3, int var4, ConcurrentHashMapV8$IntByIntToInt var5) {
      if (var3 != null && var5 != null) {
         return new ConcurrentHashMapV8$MapReduceKeysToIntTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var5).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public int reduceValuesToInt(long var1, ConcurrentHashMapV8$ObjectToInt<? super V> var3, int var4, ConcurrentHashMapV8$IntByIntToInt var5) {
      if (var3 != null && var5 != null) {
         return new ConcurrentHashMapV8$MapReduceValuesToIntTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var5).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public double reduceValuesToDouble(
      long var1, ConcurrentHashMapV8$ObjectToDouble<? super V> var3, double var4, ConcurrentHashMapV8$DoubleByDoubleToDouble var6
   ) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8$MapReduceValuesToDoubleTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public static int spread(int var0) {
      return (var0 ^ var0 >>> 16) & 2147483647;
   }

   public ConcurrentHashMapV8$KeySetView<K, V> keySet() {
      ConcurrentHashMapV8$KeySetView var1 = this.keySet;
      return this.keySet != null ? var1 : (this.keySet = new ConcurrentHashMapV8$KeySetView<>(this, null));
   }

   public static <K, V> ConcurrentHashMapV8$Node<K, V> untreeify(ConcurrentHashMapV8$Node<K, V> var0) {
      ConcurrentHashMapV8$Node var1 = null;
      ConcurrentHashMapV8$Node var2 = null;

      for (ConcurrentHashMapV8$Node var3 = var0; var3 != null; var3 = var3.next) {
         ConcurrentHashMapV8$Node var4 = new ConcurrentHashMapV8$Node(var3.hash, var3.key, var3.val, null);
         if (var2 == null) {
            var1 = var4;
         } else {
            var2.next = var4;
         }

         var2 = var4;
      }

      return var1;
   }

   public void writeObject(ObjectOutputStream var1) {
      int var2 = 0;

      byte var3;
      for (var3 = 1; var3 < 16; var3 <<= 1) {
         var2++;
      }

      int var4 = 32 - var2;
      int var5 = var3 - 1;
      ConcurrentHashMapV8$Segment[] var6 = new ConcurrentHashMapV8$Segment[16];

      for (int var7 = 0; var7 < var6.length; var7++) {
         var6[var7] = new ConcurrentHashMapV8$Segment(0.75F);
      }

      var1.putFields().put("segments", var6);
      var1.putFields().put("segmentShift", var4);
      var1.putFields().put("segmentMask", var5);
      var1.writeFields();
      ConcurrentHashMapV8$Node[] var11 = this.table;
      if (this.table != null) {
         ConcurrentHashMapV8$Traverser var8 = new ConcurrentHashMapV8$Traverser(var11, var11.length, 0, var11.length);

         ConcurrentHashMapV8$Node var9;
         while ((var9 = var8.advance()) != null) {
            var1.writeObject(var9.key);
            var1.writeObject(var9.val);
         }
      }

      var1.writeObject(null);
      var1.writeObject(null);
      var6 = null;
   }

   public void addCount(long var1, int var3) {
      IntegerHolder var9;
      boolean var14;
      InternalThreadLocalMap var15;
      label74: {
         long var7;
         label71: {
            ConcurrentHashMapV8$CounterCell[] var4 = this.counterCells;
            if (this.counterCells == null) {
               long var5 = this.baseCount;
               if (U.compareAndSwapLong(this, BASECOUNT, this.baseCount, var7 = var5 + var1)) {
                  break label71;
               }
            }

            var14 = true;
            var15 = InternalThreadLocalMap.get();
            ConcurrentHashMapV8$CounterCell var10;
            int var13;
            if ((var9 = var15.counterHashCode()) == null || var4 == null || (var13 = var4.length - 1) < 0 || (var10 = var4[var13 & var9.value]) == null) {
               break label74;
            }

            long var11 = var10.value;
            if (!(var14 = U.compareAndSwapLong(var10, CELLVALUE, var10.value, var11 + var1))) {
               break label74;
            }

            if (var3 <= 1) {
               return;
            }

            var7 = this.sumCount();
         }

         if (var3 >= 0) {
            while (true) {
               int var18 = this.sizeCtl;
               if (var7 < this.sizeCtl) {
                  break;
               }

               ConcurrentHashMapV8$Node[] var16 = this.table;
               if (this.table == null || var16.length >= 1073741824) {
                  break;
               }

               if (var18 < 0) {
                  if (var18 == -1 || this.transferIndex <= this.transferOrigin) {
                     break;
                  }

                  ConcurrentHashMapV8$Node[] var17 = this.nextTable;
                  if (this.nextTable == null) {
                     break;
                  }

                  if (U.compareAndSwapInt(this, SIZECTL, var18, var18 - 1)) {
                     this.transfer(var16, var17);
                  }
               } else if (U.compareAndSwapInt(this, SIZECTL, var18, -2)) {
                  this.transfer(var16, null);
               }

               var7 = this.sumCount();
            }
         }

         return;
      }

      this.fullAddCount(var15, var1, var9, var14);
   }

   @Override
   public boolean replace(K var1, V var2, V var3) {
      if (var1 != null && var2 != null && var3 != null) {
         return this.replaceNode(var1, (V)var3, var2) != null;
      } else {
         throw new NullPointerException();
      }
   }

   public static <K> ConcurrentHashMapV8$KeySetView<K, Boolean> newKeySet() {
      return new ConcurrentHashMapV8$KeySetView<>(new ConcurrentHashMapV8<>(), Boolean.TRUE);
   }

   public long reduceToLong(
      long var1, ConcurrentHashMapV8$ObjectByObjectToLong<? super K, ? super V> var3, long var4, ConcurrentHashMapV8$LongByLongToLong var6
   ) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8$MapReduceMappingsToLongTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   static {
      try {
         U = getUnsafe();
         Class<ConcurrentHashMapV8> var0 = ConcurrentHashMapV8.class;
         SIZECTL = U.objectFieldOffset(var0.getDeclaredField("sizeCtl"));
         TRANSFERINDEX = U.objectFieldOffset(var0.getDeclaredField("transferIndex"));
         TRANSFERORIGIN = U.objectFieldOffset(var0.getDeclaredField("transferOrigin"));
         BASECOUNT = U.objectFieldOffset(var0.getDeclaredField("baseCount"));
         CELLSBUSY = U.objectFieldOffset(var0.getDeclaredField("cellsBusy"));
         Class<ConcurrentHashMapV8$CounterCell> var1 = ConcurrentHashMapV8$CounterCell.class;
         CELLVALUE = U.objectFieldOffset(var1.getDeclaredField("value"));
         Class<ConcurrentHashMapV8$Node[]> var2 = ConcurrentHashMapV8$Node[].class;
         ABASE = U.arrayBaseOffset(var2);
         int var3 = U.arrayIndexScale(var2);
         if ((var3 & var3 - 1) != 0) {
            throw new Error("data type scale not a power of two");
         } else {
            ASHIFT = 31 - Integer.numberOfLeadingZeros(var3);
         }
      } catch (Exception var4) {
         throw new Error(var4);
      }
   }

   @Override
   public boolean isEmpty() {
      return this.sumCount() <= (14819330L & 1226070816L);
   }

   public V compute(K var1, ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends V> var2) {
      if (var1 != null && var2 != null) {
         int var3 = spread(var1.hashCode());
         Object var4 = null;
         byte var5 = 0;
         int var6 = 0;
         ConcurrentHashMapV8$Node[] var7 = this.table;

         while (true) {
            int var9;
            while (var7 == null || (var9 = var7.length) == 0) {
               var7 = this.initTable();
            }

            ConcurrentHashMapV8$Node var8;
            int var10;
            if ((var8 = tabAt(var7, var10 = var9 - 1 & var3)) == null) {
               ConcurrentHashMapV8$ReservationNode var12 = new ConcurrentHashMapV8$ReservationNode();
               synchronized (var12) {
                  if (casTabAt(var7, var10, null, var12)) {
                     var6 = 1;
                     ConcurrentHashMapV8$Node var26 = null;

                     try {
                        if ((var4 = var2.apply(var1, null)) != null) {
                           var5 = 1;
                           var26 = new ConcurrentHashMapV8$Node<>(var3, var1, var4, null);
                        }
                     } finally {
                        setTabAt(var7, var10, var26);
                     }
                  }
               }

               if (var6 != 0) {
                  break;
               }
            } else {
               int var11 = var8.hash;
               if (var8.hash == -1) {
                  var7 = this.helpTransfer(var7, var8);
               } else {
                  synchronized (var8) {
                     if (tabAt(var7, var10) == var8) {
                        if (var11 < 0) {
                           if (var8 instanceof ConcurrentHashMapV8$TreeBin) {
                              var6 = 1;
                              ConcurrentHashMapV8$TreeBin var24 = (ConcurrentHashMapV8$TreeBin)var8;
                              ConcurrentHashMapV8$TreeNode var25 = var24.root;
                              ConcurrentHashMapV8$TreeNode var27;
                              if (var24.root != null) {
                                 var27 = var25.findTreeNode(var3, var1, null);
                              } else {
                                 var27 = null;
                              }

                              Object var28 = var27 == null ? null : var27.val;
                              var4 = var2.apply(var1, var28);
                              if (var4 != null) {
                                 if (var27 != null) {
                                    var27.val = (V)var4;
                                 } else {
                                    var5 = 1;
                                    var24.putTreeVal(var3, var1, var4);
                                 }
                              } else if (var27 != null) {
                                 var5 = -1;
                                 if (var24.removeTreeNode(var27)) {
                                    setTabAt(var7, var10, untreeify(var24.first));
                                 }
                              }
                           }
                        } else {
                           var6 = 1;
                           ConcurrentHashMapV8$Node var13 = var8;
                           ConcurrentHashMapV8$Node var14 = null;

                           while (true) {
                              if (var13.hash == var3) {
                                 Object var15 = var13.key;
                                 if (var13.key == var1 || var15 != null && var1.equals(var15)) {
                                    var4 = var2.apply(var1, var13.val);
                                    if (var4 != null) {
                                       var13.val = (V)var4;
                                    } else {
                                       var5 = -1;
                                       ConcurrentHashMapV8$Node var16 = var13.next;
                                       if (var14 != null) {
                                          var14.next = var16;
                                       } else {
                                          setTabAt(var7, var10, var16);
                                       }
                                    }
                                    break;
                                 }
                              }

                              var14 = var13;
                              if ((var13 = var13.next) == null) {
                                 var4 = var2.apply(var1, null);
                                 if (var4 != null) {
                                    var5 = 1;
                                    var14.next = new ConcurrentHashMapV8$Node<>(var3, (K)var1, (V)var4, null);
                                 }
                                 break;
                              }

                              var6++;
                           }
                        }
                     }
                  }

                  if (var6 != 0) {
                     if (var6 >= 8) {
                        this.treeifyBin(var7, var10);
                     }
                     break;
                  }
               }
            }
         }

         if (var5 != 0) {
            this.addCount(var5, var6);
         }

         return (V)var4;
      } else {
         throw new NullPointerException();
      }
   }

   public V computeIfAbsent(K var1, ConcurrentHashMapV8$Fun<? super K, ? extends V> var2) {
      if (var1 != null && var2 != null) {
         int var3 = spread(var1.hashCode());
         Object var4 = null;
         int var5 = 0;
         ConcurrentHashMapV8$Node[] var6 = this.table;

         while (true) {
            int var8;
            while (var6 == null || (var8 = var6.length) == 0) {
               var6 = this.initTable();
            }

            ConcurrentHashMapV8$Node var7;
            int var9;
            if ((var7 = tabAt(var6, var9 = var8 - 1 & var3)) == null) {
               ConcurrentHashMapV8$ReservationNode var24 = new ConcurrentHashMapV8$ReservationNode();
               synchronized (var24) {
                  if (casTabAt(var6, var9, null, var24)) {
                     var5 = 1;
                     ConcurrentHashMapV8$Node var26 = null;

                     try {
                        if ((var4 = var2.apply(var1)) != null) {
                           var26 = new ConcurrentHashMapV8$Node<>(var3, var1, var4, null);
                        }
                     } finally {
                        setTabAt(var6, var9, var26);
                     }
                  }
               }

               if (var5 != 0) {
                  break;
               }
            } else {
               int var10 = var7.hash;
               if (var7.hash == -1) {
                  var6 = this.helpTransfer(var6, var7);
               } else {
                  boolean var11 = false;
                  synchronized (var7) {
                     if (tabAt(var6, var9) == var7) {
                        if (var10 < 0) {
                           if (var7 instanceof ConcurrentHashMapV8$TreeBin) {
                              var5 = 2;
                              ConcurrentHashMapV8$TreeBin var25 = (ConcurrentHashMapV8$TreeBin)var7;
                              ConcurrentHashMapV8$TreeNode var27 = var25.root;
                              ConcurrentHashMapV8$TreeNode var15;
                              if (var25.root != null && (var15 = var27.findTreeNode(var3, var1, null)) != null) {
                                 var4 = var15.val;
                              } else if ((var4 = var2.apply(var1)) != null) {
                                 var11 = true;
                                 var25.putTreeVal(var3, var1, var4);
                              }
                           }
                        } else {
                           var5 = 1;
                           ConcurrentHashMapV8$Node var13 = var7;

                           while (true) {
                              if (var13.hash == var3) {
                                 Object var14 = var13.key;
                                 if (var13.key == var1 || var14 != null && var1.equals(var14)) {
                                    var4 = var13.val;
                                    break;
                                 }
                              }

                              ConcurrentHashMapV8$Node var16 = var13;
                              if ((var13 = var13.next) == null) {
                                 if ((var4 = var2.apply(var1)) != null) {
                                    var11 = true;
                                    var16.next = new ConcurrentHashMapV8$Node<>(var3, (K)var1, (V)var4, null);
                                 }
                                 break;
                              }

                              var5++;
                           }
                        }
                     }
                  }

                  if (var5 != 0) {
                     if (var5 >= 8) {
                        this.treeifyBin(var6, var9);
                     }

                     if (!var11) {
                        return (V)var4;
                     }
                     break;
                  }
               }
            }
         }

         if (var4 != null) {
            this.addCount(3334701697813495267L & 1235616257L, var5);
         }

         return (V)var4;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public Collection<V> values() {
      ConcurrentHashMapV8$ValuesView var1 = this.values;
      return this.values != null ? var1 : (this.values = new ConcurrentHashMapV8$ValuesView<>(this));
   }

   public long reduceValuesToLong(long var1, ConcurrentHashMapV8$ObjectToLong<? super V> var3, long var4, ConcurrentHashMapV8$LongByLongToLong var6) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8$MapReduceValuesToLongTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public static <K, V> void setTabAt(ConcurrentHashMapV8$Node<K, V>[] var0, int var1, ConcurrentHashMapV8$Node<K, V> var2) {
      U.putObjectVolatile(var0, ((long)var1 << ASHIFT) + ABASE, var2);
   }

   public Enumeration<K> keys() {
      ConcurrentHashMapV8$Node[] var1 = this.table;
      int var2 = this.table == null ? 0 : var1.length;
      return new ConcurrentHashMapV8$KeyIterator<>(var1, var2, 0, var2, this);
   }

   public static <K> ConcurrentHashMapV8$KeySetView<K, Boolean> newKeySet(int var0) {
      return new ConcurrentHashMapV8$KeySetView<>(new ConcurrentHashMapV8<>(var0), Boolean.TRUE);
   }

   public ConcurrentHashMapV8(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         int var2 = var1 >= 536870912 ? 1073741824 : tableSizeFor(var1 + (var1 >>> 1) + 1);
         this.sizeCtl = var2;
      }
   }

   public Enumeration<V> elements() {
      ConcurrentHashMapV8$Node[] var1 = this.table;
      int var2 = this.table == null ? 0 : var1.length;
      return new ConcurrentHashMapV8$ValueIterator<>(var1, var2, 0, var2, this);
   }

   public V computeIfPresent(K var1, ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends V> var2) {
      if (var1 != null && var2 != null) {
         int var3 = spread(var1.hashCode());
         Object var4 = null;
         byte var5 = 0;
         int var6 = 0;
         ConcurrentHashMapV8$Node[] var7 = this.table;

         while (true) {
            int var9;
            while (var7 == null || (var9 = var7.length) == 0) {
               var7 = this.initTable();
            }

            ConcurrentHashMapV8$Node var8;
            int var10;
            if ((var8 = tabAt(var7, var10 = var9 - 1 & var3)) == null) {
               break;
            }

            int var11 = var8.hash;
            if (var8.hash == -1) {
               var7 = this.helpTransfer(var7, var8);
            } else {
               synchronized (var8) {
                  if (tabAt(var7, var10) == var8) {
                     if (var11 < 0) {
                        if (var8 instanceof ConcurrentHashMapV8$TreeBin) {
                           var6 = 2;
                           ConcurrentHashMapV8$TreeBin var19 = (ConcurrentHashMapV8$TreeBin)var8;
                           ConcurrentHashMapV8$TreeNode var20 = var19.root;
                           ConcurrentHashMapV8$TreeNode var21;
                           if (var19.root != null && (var21 = var20.findTreeNode(var3, var1, null)) != null) {
                              var4 = var2.apply(var1, var21.val);
                              if (var4 != null) {
                                 var21.val = (V)var4;
                              } else {
                                 var5 = -1;
                                 if (var19.removeTreeNode(var21)) {
                                    setTabAt(var7, var10, untreeify(var19.first));
                                 }
                              }
                           }
                        }
                     } else {
                        var6 = 1;
                        ConcurrentHashMapV8$Node var13 = var8;
                        ConcurrentHashMapV8$Node var14 = null;

                        while (true) {
                           if (var13.hash == var3) {
                              Object var15 = var13.key;
                              if (var13.key == var1 || var15 != null && var1.equals(var15)) {
                                 var4 = var2.apply(var1, var13.val);
                                 if (var4 != null) {
                                    var13.val = (V)var4;
                                 } else {
                                    var5 = -1;
                                    ConcurrentHashMapV8$Node var16 = var13.next;
                                    if (var14 != null) {
                                       var14.next = var16;
                                    } else {
                                       setTabAt(var7, var10, var16);
                                    }
                                 }
                                 break;
                              }
                           }

                           var14 = var13;
                           if ((var13 = var13.next) == null) {
                              break;
                           }

                           var6++;
                        }
                     }
                  }
               }

               if (var6 != 0) {
                  break;
               }
            }
         }

         if (var5 != 0) {
            this.addCount(var5, var6);
         }

         return (V)var4;
      } else {
         throw new NullPointerException();
      }
   }

   public double reduceToDouble(
      long var1, ConcurrentHashMapV8$ObjectByObjectToDouble<? super K, ? super V> var3, double var4, ConcurrentHashMapV8$DoubleByDoubleToDouble var6
   ) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8$MapReduceMappingsToDoubleTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public boolean containsValue(Object var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node[] var2 = this.table;
         if (this.table != null) {
            ConcurrentHashMapV8$Traverser var3 = new ConcurrentHashMapV8$Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8$Node var4;
            while ((var4 = var3.advance()) != null) {
               Object var5 = var4.val;
               if (var4.val == var1 || var5 != null && var1.equals(var5)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public V put(K var1, V var2) {
      return this.putVal((K)var1, (V)var2, false);
   }

   public V merge(K var1, V var2, ConcurrentHashMapV8$BiFun<? super V, ? super V, ? extends V> var3) {
      if (var1 != null && var2 != null && var3 != null) {
         int var4 = spread(var1.hashCode());
         Object var5 = null;
         byte var6 = 0;
         int var7 = 0;
         ConcurrentHashMapV8$Node[] var8 = this.table;

         while (true) {
            int var10;
            while (var8 == null || (var10 = var8.length) == 0) {
               var8 = this.initTable();
            }

            ConcurrentHashMapV8$Node var9;
            int var11;
            if ((var9 = tabAt(var8, var11 = var10 - 1 & var4)) == null) {
               if (casTabAt(var8, var11, null, new ConcurrentHashMapV8$Node<>(var4, var1, var2, null))) {
                  var6 = 1;
                  var5 = var2;
                  break;
               }
            } else {
               int var12 = var9.hash;
               if (var9.hash == -1) {
                  var8 = this.helpTransfer(var8, var9);
               } else {
                  synchronized (var9) {
                     if (tabAt(var8, var11) == var9) {
                        if (var12 < 0) {
                           if (var9 instanceof ConcurrentHashMapV8$TreeBin) {
                              var7 = 2;
                              ConcurrentHashMapV8$TreeBin var20 = (ConcurrentHashMapV8$TreeBin)var9;
                              ConcurrentHashMapV8$TreeNode var21 = var20.root;
                              ConcurrentHashMapV8$TreeNode var22 = var21 == null ? null : var21.findTreeNode(var4, var1, null);
                              var5 = var22 == null ? var2 : var3.apply(var22.val, var2);
                              if (var5 != null) {
                                 if (var22 != null) {
                                    var22.val = (V)var5;
                                 } else {
                                    var6 = 1;
                                    var20.putTreeVal(var4, var1, var5);
                                 }
                              } else if (var22 != null) {
                                 var6 = -1;
                                 if (var20.removeTreeNode(var22)) {
                                    setTabAt(var8, var11, untreeify(var20.first));
                                 }
                              }
                           }
                        } else {
                           var7 = 1;
                           ConcurrentHashMapV8$Node var14 = var9;
                           ConcurrentHashMapV8$Node var15 = null;

                           while (true) {
                              if (var14.hash == var4) {
                                 Object var16 = var14.key;
                                 if (var14.key == var1 || var16 != null && var1.equals(var16)) {
                                    var5 = var3.apply(var14.val, var2);
                                    if (var5 != null) {
                                       var14.val = (V)var5;
                                    } else {
                                       var6 = -1;
                                       ConcurrentHashMapV8$Node var17 = var14.next;
                                       if (var15 != null) {
                                          var15.next = var17;
                                       } else {
                                          setTabAt(var8, var11, var17);
                                       }
                                    }
                                    break;
                                 }
                              }

                              var15 = var14;
                              if ((var14 = var14.next) == null) {
                                 var6 = 1;
                                 var5 = var2;
                                 var15.next = new ConcurrentHashMapV8$Node<>(var4, (K)var1, (V)var2, null);
                                 break;
                              }

                              var7++;
                           }
                        }
                     }
                  }

                  if (var7 != 0) {
                     if (var7 >= 8) {
                        this.treeifyBin(var8, var11);
                     }
                     break;
                  }
               }
            }
         }

         if (var6 != 0) {
            this.addCount(var6, var7);
         }

         return (V)var5;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public Set<Entry<K, V>> entrySet() {
      ConcurrentHashMapV8$EntrySetView var1 = this.entrySet;
      return this.entrySet != null ? var1 : (this.entrySet = new ConcurrentHashMapV8$EntrySetView<>(this));
   }

   public void fullAddCount(InternalThreadLocalMap var1, long var2, IntegerHolder var4, boolean var5) {
      int var6;
      if (var4 == null) {
         var4 = new IntegerHolder();
         int var7 = counterHashCodeGenerator.addAndGet(1640531527);
         var6 = var4.value = var7 == 0 ? 1 : var7;
         var1.setCounterHashCode(var4);
      } else {
         var6 = var4.value;
      }

      boolean var35 = false;

      while (true) {
         ConcurrentHashMapV8$CounterCell[] var8 = this.counterCells;
         int var10;
         if (this.counterCells != null && (var10 = var8.length) > 0) {
            ConcurrentHashMapV8$CounterCell var9;
            if ((var9 = var8[var10 - 1 & var6]) == null) {
               if (this.cellsBusy == 0) {
                  ConcurrentHashMapV8$CounterCell var37 = new ConcurrentHashMapV8$CounterCell(var2);
                  if (this.cellsBusy == 0 && U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                     boolean var39 = false;

                     try {
                        ConcurrentHashMapV8$CounterCell[] var15 = this.counterCells;
                        int var16;
                        int var17;
                        if (this.counterCells != null && (var16 = var15.length) > 0 && var15[var17 = var16 - 1 & var6] == null) {
                           var15[var17] = var37;
                           var39 = true;
                        }
                     } finally {
                        this.cellsBusy = 0;
                     }

                     if (var39) {
                        break;
                     }
                     continue;
                  }
               }

               var35 = false;
            } else if (!var5) {
               var5 = true;
            } else {
               long var36 = var9.value;
               if (U.compareAndSwapLong(var9, CELLVALUE, var9.value, var36 + var2)) {
                  break;
               }

               if (this.counterCells != var8 || var10 >= NCPU) {
                  var35 = false;
               } else if (!var35) {
                  var35 = true;
               } else if (this.cellsBusy == 0 && U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                  try {
                     if (this.counterCells == var8) {
                        ConcurrentHashMapV8$CounterCell[] var38 = new ConcurrentHashMapV8$CounterCell[var10 << 1];

                        for (int var40 = 0; var40 < var10; var40++) {
                           var38[var40] = var8[var40];
                        }

                        this.counterCells = var38;
                     }
                  } finally {
                     this.cellsBusy = 0;
                  }

                  var35 = false;
                  continue;
               }
            }

            var6 ^= var6 << 13;
            var6 ^= var6 >>> 17;
            var6 ^= var6 << 5;
         } else if (this.cellsBusy == 0 && this.counterCells == var8 && U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
            boolean var13 = false;

            try {
               if (this.counterCells == var8) {
                  ConcurrentHashMapV8$CounterCell[] var14 = new ConcurrentHashMapV8$CounterCell[2];
                  var14[var6 & 1] = new ConcurrentHashMapV8$CounterCell(var2);
                  this.counterCells = var14;
                  var13 = true;
               }
            } finally {
               this.cellsBusy = 0;
            }

            if (var13) {
               break;
            }
         } else {
            long var11 = this.baseCount;
            if (U.compareAndSwapLong(this, BASECOUNT, this.baseCount, var11 + var2)) {
               break;
            }
         }
      }

      var4.value = var6;
   }

   @Override
   public V replace(K var1, V var2) {
      if (var1 != null && var2 != null) {
         return this.replaceNode(var1, (V)var2, null);
      } else {
         throw new NullPointerException();
      }
   }

   public void tryPresize(int var1) {
      int var2 = var1 >= 536870912 ? 1073741824 : tableSizeFor(var1 + (var1 >>> 1) + 1);

      while (true) {
         int var3 = this.sizeCtl;
         if (this.sizeCtl < 0) {
            break;
         }

         ConcurrentHashMapV8$Node[] var4 = this.table;
         int var5;
         if (var4 != null && (var5 = var4.length) != 0) {
            if (var2 <= var3 || var5 >= 1073741824) {
               break;
            }

            if (var4 == this.table && U.compareAndSwapInt(this, SIZECTL, var3, -2)) {
               this.transfer(var4, null);
            }
         } else {
            var5 = var3 > var2 ? var3 : var2;
            if (U.compareAndSwapInt(this, SIZECTL, var3, -1)) {
               try {
                  if (this.table == var4) {
                     ConcurrentHashMapV8$Node[] var6 = new ConcurrentHashMapV8$Node[var5];
                     this.table = var6;
                     var3 = var5 - (var5 >>> 2);
                  }
               } finally {
                  this.sizeCtl = var3;
               }
            }
         }
      }
   }

   public ConcurrentHashMapV8(int var1, float var2, int var3) {
      if (var2 > 0.0F && var1 >= 0 && var3 > 0) {
         if (var1 < var3) {
            var1 = var3;
         }

         long var4 = (long)(1.0 + (float)var1 / var2);
         int var6 = var4 >= (1617461329L & 1527018788L) ? 1073741824 : tableSizeFor((int)var4);
         this.sizeCtl = var6;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void replaceAll(ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends V> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node[] var2 = this.table;
         if (this.table != null) {
            ConcurrentHashMapV8$Traverser var3 = new ConcurrentHashMapV8$Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8$Node var4;
            while ((var4 = var3.advance()) != null) {
               Object var5 = var4.val;
               Object var6 = var4.key;

               Object var7;
               do {
                  var7 = var1.apply(var6, var5);
                  if (var7 == null) {
                     throw new NullPointerException();
                  }
               } while (this.replaceNode(var6, (V)var7, var5) == null && (var5 = this.get(var6)) != null);
            }
         }
      }
   }

   public <U> void forEachKey(long var1, ConcurrentHashMapV8$Fun<? super K, ? extends U> var3, ConcurrentHashMapV8$Action<? super U> var4) {
      if (var3 != null && var4 != null) {
         new ConcurrentHashMapV8$ForEachTransformedKeyTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U searchKeys(long var1, ConcurrentHashMapV8$Fun<? super K, ? extends U> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8$SearchKeysTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, new AtomicReference()).invoke();
      }
   }

   public <U> U searchValues(long var1, ConcurrentHashMapV8$Fun<? super V, ? extends U> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8$SearchValuesTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, new AtomicReference()).invoke();
      }
   }

   public void forEachEntry(long var1, ConcurrentHashMapV8$Action<? super Entry<K, V>> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         new ConcurrentHashMapV8$ForEachEntryTask<>(null, this.batchFor(var1), 0, 0, this.table, var3).invoke();
      }
   }

   public static Class<?> comparableClassFor(Object var0) {
      if (var0 instanceof Comparable) {
         Class var1;
         if ((var1 = var0.getClass()) == String.class) {
            return var1;
         }

         Type[] var2;
         if ((var2 = var1.getGenericInterfaces()) != null) {
            for (int var6 = 0; var6 < var2.length; var6++) {
               Type[] var3;
               Type var4;
               ParameterizedType var5;
               if ((var4 = var2[var6]) instanceof ParameterizedType
                  && (var5 = (ParameterizedType)var4).getRawType() == Comparable.class
                  && (var3 = var5.getActualTypeArguments()) != null
                  && var3.length == 1
                  && var3[0] == var1) {
                  return var1;
               }
            }
         }
      }

      return null;
   }

   public int reduceToInt(long var1, ConcurrentHashMapV8$ObjectByObjectToInt<? super K, ? super V> var3, int var4, ConcurrentHashMapV8$IntByIntToInt var5) {
      if (var3 != null && var5 != null) {
         return new ConcurrentHashMapV8$MapReduceMappingsToIntTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var5).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> var1) {
      this.tryPresize(var1.size());

      for (Entry var3 : var1.entrySet()) {
         this.putVal((K)var3.getKey(), (V)var3.getValue(), false);
      }
   }

   public K reduceKeys(long var1, ConcurrentHashMapV8$BiFun<? super K, ? super K, ? extends K> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8$ReduceKeysTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3).invoke();
      }
   }

   public <U> U reduceKeys(long var1, ConcurrentHashMapV8$Fun<? super K, ? extends U> var3, ConcurrentHashMapV8$BiFun<? super U, ? super U, ? extends U> var4) {
      if (var3 != null && var4 != null) {
         return new ConcurrentHashMapV8$MapReduceKeysTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = 0;
      ConcurrentHashMapV8$Node[] var2 = this.table;
      if (this.table != null) {
         ConcurrentHashMapV8$Traverser var3 = new ConcurrentHashMapV8$Traverser(var2, var2.length, 0, var2.length);

         ConcurrentHashMapV8$Node var4;
         while ((var4 = var3.advance()) != null) {
            var1 += var4.key.hashCode() ^ var4.val.hashCode();
         }
      }

      return var1;
   }

   public long mappingCount() {
      long var1 = this.sumCount();
      return var1 < (6264963474578874665L & -6264963475030587308L) ? 8892210338125711712L & 75555865L : var1;
   }

   public int reduceEntriesToInt(long var1, ConcurrentHashMapV8$ObjectToInt<Entry<K, V>> var3, int var4, ConcurrentHashMapV8$IntByIntToInt var5) {
      if (var3 != null && var5 != null) {
         return new ConcurrentHashMapV8$MapReduceEntriesToIntTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var5).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U search(long var1, ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends U> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8$SearchMappingsTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, new AtomicReference()).invoke();
      }
   }

   @Override
   public V remove(Object var1) {
      return this.replaceNode(var1, null, null);
   }

   public void readObject(ObjectInputStream var1) {
      this.sizeCtl = -1;
      var1.defaultReadObject();
      long var2 = 8605110558293272576L & 674435392L;
      ConcurrentHashMapV8$Node var4 = null;

      while (true) {
         Object var5 = var1.readObject();
         Object var6 = var1.readObject();
         if (var5 == null || var6 == null) {
            if (var2 == (7774220440205263872L & -7774220441270187827L)) {
               this.sizeCtl = 0;
            } else {
               int var22;
               if (var2 >= (-4768406855518141872L & 807416068L)) {
                  var22 = 1073741824;
               } else {
                  int var23 = (int)var2;
                  var22 = tableSizeFor(var23 + (var23 >>> 1) + 1);
               }

               var6 = new ConcurrentHashMapV8$Node[var22];
               int var7 = var22 - 1;
               long var8 = 537503166640455680L & -537503167387257854L;

               while (var4 != null) {
                  ConcurrentHashMapV8$Node var11 = var4.next;
                  int var13 = var4.hash;
                  int var14 = var13 & var7;
                  boolean var10;
                  ConcurrentHashMapV8$Node var12;
                  if ((var12 = tabAt((ConcurrentHashMapV8$Node<K, V>[])var6, var14)) == null) {
                     var10 = true;
                  } else {
                     Object var15 = var4.key;
                     if (var12.hash < 0) {
                        ConcurrentHashMapV8$TreeBin var25 = (ConcurrentHashMapV8$TreeBin)var12;
                        if (var25.putTreeVal(var13, var15, var4.val) == null) {
                           var8 += -5221943928978141007L & 135120129L;
                        }

                        var10 = false;
                     } else {
                        int var16 = 0;
                        var10 = true;

                        for (ConcurrentHashMapV8$Node var17 = var12; var17 != null; var17 = var17.next) {
                           if (var17.hash == var13) {
                              Object var18 = var17.key;
                              if (var17.key == var15 || var18 != null && var15.equals(var18)) {
                                 var10 = false;
                                 break;
                              }
                           }

                           var16++;
                        }

                        if (var10 && var16 >= 8) {
                           var10 = false;
                           var8 += 139003205L & 5149885107500655105L;
                           var4.next = var12;
                           ConcurrentHashMapV8$TreeNode var19 = null;
                           ConcurrentHashMapV8$TreeNode var20 = null;

                           for (ConcurrentHashMapV8$Node var26 = var4; var26 != null; var26 = var26.next) {
                              ConcurrentHashMapV8$TreeNode var21 = new ConcurrentHashMapV8$TreeNode(var26.hash, var26.key, var26.val, null, null);
                              if ((var21.prev = var20) == null) {
                                 var19 = var21;
                              } else {
                                 var20.next = var21;
                              }

                              var20 = var21;
                           }

                           setTabAt((ConcurrentHashMapV8$Node<K, V>[])var6, var14, new ConcurrentHashMapV8$TreeBin<>(var19));
                        }
                     }
                  }

                  if (var10) {
                     var8 += 8997987L & 1111494657L;
                     var4.next = var12;
                     setTabAt((ConcurrentHashMapV8$Node<K, V>[])var6, var14, var4);
                  }

                  var4 = var11;
               }

               this.table = (ConcurrentHashMapV8$Node<K, V>[])var6;
               this.sizeCtl = var22 - (var22 >>> 2);
               this.baseCount = var8;
            }

            return;
         }

         var4 = new ConcurrentHashMapV8$Node<>(spread(var5.hashCode()), var5, var6, var4);
         var2 += 739864641L & 1344537115L;
      }
   }

   public double reduceKeysToDouble(long var1, ConcurrentHashMapV8$ObjectToDouble<? super K> var3, double var4, ConcurrentHashMapV8$DoubleByDoubleToDouble var6) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8$MapReduceKeysToDoubleTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U reduce(
      long var1, ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends U> var3, ConcurrentHashMapV8$BiFun<? super U, ? super U, ? extends U> var4
   ) {
      if (var3 != null && var4 != null) {
         return new ConcurrentHashMapV8$MapReduceMappingsTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public void clear() {
      long var1 = 1074792994L & 180421976L;
      int var3 = 0;
      ConcurrentHashMapV8$Node[] var4 = this.table;

      while (var4 != null && var3 < var4.length) {
         ConcurrentHashMapV8$Node var6 = tabAt(var4, var3);
         if (var6 == null) {
            var3++;
         } else {
            int var5 = var6.hash;
            if (var6.hash == -1) {
               var4 = this.helpTransfer(var4, var6);
               var3 = 0;
            } else {
               synchronized (var6) {
                  if (tabAt(var4, var3) == var6) {
                     for (Object var8 = var5 >= 0 ? var6 : (var6 instanceof ConcurrentHashMapV8$TreeBin ? ((ConcurrentHashMapV8$TreeBin)var6).first : null);
                        var8 != null;
                        var8 = ((ConcurrentHashMapV8$Node)var8).next
                     ) {
                        var1 -= -3806630604808650605L & 1344484613L;
                     }

                     setTabAt(var4, var3++, null);
                  }
               }
            }
         }
      }

      if (var1 != (137496352L & -8054249326646874110L)) {
         this.addCount(var1, -1);
      }
   }

   public ConcurrentHashMapV8() {
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 != this) {
         if (!(var1 instanceof Map)) {
            return false;
         }

         Map var2 = (Map)var1;
         ConcurrentHashMapV8$Node[] var3 = this.table;
         int var4 = this.table == null ? 0 : var3.length;
         ConcurrentHashMapV8$Traverser var5 = new ConcurrentHashMapV8$Traverser(var3, var4, 0, var4);

         ConcurrentHashMapV8$Node var6;
         while ((var6 = var5.advance()) != null) {
            Object var7 = var6.val;
            Object var8 = var2.get(var6.key);
            if (var8 == null || var8 != var7 && !var8.equals(var7)) {
               return false;
            }
         }

         for (Entry var12 : var2.entrySet()) {
            Object var9;
            Object var10;
            Object var13;
            if ((var13 = var12.getKey()) == null
               || (var9 = var12.getValue()) == null
               || (var10 = this.get(var13)) == null
               || var9 != var10 && !var9.equals(var10)) {
               return false;
            }
         }
      }

      return true;
   }

   public Entry<K, V> reduceEntries(long var1, ConcurrentHashMapV8$BiFun<Entry<K, V>, Entry<K, V>, ? extends Entry<K, V>> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8$ReduceEntriesTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3).invoke();
      }
   }

   public static int compareComparables(Class<?> var0, Object var1, Object var2) {
      return var2 != null && var2.getClass() == var0 ? ((Comparable)var1).compareTo(var2) : 0;
   }

   public ConcurrentHashMapV8$KeySetView<K, V> keySet(V var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8$KeySetView<>(this, (V)var1);
      }
   }

   public ConcurrentHashMapV8(Map<? extends K, ? extends V> var1) {
      this.sizeCtl = 16;
      this.putAll(var1);
   }

   @Override
   public boolean containsKey(Object var1) {
      return this.get(var1) != null;
   }

   public static <K, V> ConcurrentHashMapV8$Node<K, V> tabAt(ConcurrentHashMapV8$Node<K, V>[] var0, int var1) {
      return (ConcurrentHashMapV8$Node<K, V>)U.getObjectVolatile(var0, ((long)var1 << ASHIFT) + ABASE);
   }

   public static <K, V> boolean casTabAt(
      ConcurrentHashMapV8$Node<K, V>[] var0, int var1, ConcurrentHashMapV8$Node<K, V> var2, ConcurrentHashMapV8$Node<K, V> var3
   ) {
      return U.compareAndSwapObject(var0, ((long)var1 << ASHIFT) + ABASE, var2, var3);
   }

   public V replaceNode(Object var1, V var2, Object var3) {
      int var4 = spread(var1.hashCode());
      ConcurrentHashMapV8$Node[] var5 = this.table;

      ConcurrentHashMapV8$Node var6;
      int var7;
      int var8;
      while (var5 != null && (var7 = var5.length) != 0 && (var6 = tabAt(var5, var8 = var7 - 1 & var4)) != null) {
         int var9 = var6.hash;
         if (var6.hash == -1) {
            var5 = this.helpTransfer(var5, var6);
         } else {
            Object var10 = null;
            boolean var11 = false;
            synchronized (var6) {
               if (tabAt(var5, var8) == var6) {
                  if (var9 < 0) {
                     if (var6 instanceof ConcurrentHashMapV8$TreeBin) {
                        var11 = true;
                        ConcurrentHashMapV8$TreeBin var19 = (ConcurrentHashMapV8$TreeBin)var6;
                        ConcurrentHashMapV8$TreeNode var20 = var19.root;
                        ConcurrentHashMapV8$TreeNode var21;
                        if (var19.root != null && (var21 = var20.findTreeNode(var4, var1, null)) != null) {
                           Object var22 = var21.val;
                           if (var3 == null || var3 == var22 || var22 != null && var3.equals(var22)) {
                              var10 = var22;
                              if (var2 != null) {
                                 var21.val = (V)var2;
                              } else if (var19.removeTreeNode(var21)) {
                                 setTabAt(var5, var8, untreeify(var19.first));
                              }
                           }
                        }
                     }
                  } else {
                     var11 = true;
                     ConcurrentHashMapV8$Node var13 = var6;
                     ConcurrentHashMapV8$Node var14 = null;

                     do {
                        if (var13.hash == var4) {
                           Object var15 = var13.key;
                           if (var13.key == var1 || var15 != null && var1.equals(var15)) {
                              Object var16 = var13.val;
                              if (var3 == null || var3 == var16 || var16 != null && var3.equals(var16)) {
                                 var10 = var16;
                                 if (var2 != null) {
                                    var13.val = (V)var2;
                                 } else if (var14 != null) {
                                    var14.next = var13.next;
                                 } else {
                                    setTabAt(var5, var8, var13.next);
                                 }
                              }
                              break;
                           }
                        }

                        var14 = var13;
                     } while ((var13 = var13.next) != null);
                  }
               }
            }

            if (var11) {
               if (var10 != null) {
                  if (var2 == null) {
                     this.addCount(-1L & -1L, -1);
                  }

                  return (V)var10;
               }
               break;
            }
         }
      }

      return null;
   }

   public static Unsafe getUnsafe() {
      try {
         return Unsafe.getUnsafe();
      } catch (SecurityException var2) {
         try {
            return AccessController.doPrivileged(new ConcurrentHashMapV8$1());
         } catch (PrivilegedActionException var1) {
            throw new RuntimeException("Could not initialize intrinsics", var1.getCause());
         }
      }
   }

   @Override
   public int size() {
      long var1 = this.sumCount();
      return var1 < (630261696L & 8208L) ? 0 : (var1 > (2147483647L & 6294213414101188607L) ? Integer.MAX_VALUE : (int)var1);
   }

   public void forEachKey(long var1, ConcurrentHashMapV8$Action<? super K> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         new ConcurrentHashMapV8$ForEachKeyTask<>(null, this.batchFor(var1), 0, 0, this.table, var3).invoke();
      }
   }

   @Override
   public String toString() {
      ConcurrentHashMapV8$Node[] var1 = this.table;
      int var2 = this.table == null ? 0 : var1.length;
      ConcurrentHashMapV8$Traverser var3 = new ConcurrentHashMapV8$Traverser(var1, var2, 0, var2);
      StringBuilder var4 = new StringBuilder();
      var4.append('{');
      ConcurrentHashMapV8$Node var5;
      if ((var5 = var3.advance()) != null) {
         while (true) {
            Object var6 = var5.key;
            Object var7 = var5.val;
            var4.append(var6 == this ? "(this Map)" : var6);
            var4.append('=');
            var4.append(var7 == this ? "(this Map)" : var7);
            if ((var5 = var3.advance()) == null) {
               break;
            }

            var4.append(',').append(' ');
         }
      }

      return var4.append('}').toString();
   }

   public long reduceEntriesToLong(long var1, ConcurrentHashMapV8$ObjectToLong<Entry<K, V>> var3, long var4, ConcurrentHashMapV8$LongByLongToLong var6) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8$MapReduceEntriesToLongTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public void forEach(ConcurrentHashMapV8$BiAction<? super K, ? super V> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8$Node[] var2 = this.table;
         if (this.table != null) {
            ConcurrentHashMapV8$Traverser var3 = new ConcurrentHashMapV8$Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8$Node var4;
            while ((var4 = var3.advance()) != null) {
               var1.apply(var4.key, var4.val);
            }
         }
      }
   }

   public void transfer(ConcurrentHashMapV8$Node<K, V>[] var1, ConcurrentHashMapV8$Node<K, V>[] var2) {
      int var3 = var1.length;
      int var4;
      if ((var4 = NCPU > 1 ? (var3 >>> 3) / NCPU : var3) < 16) {
         var4 = 16;
      }

      if (var2 == null) {
         try {
            ConcurrentHashMapV8$Node[] var5 = new ConcurrentHashMapV8$Node[var3 << 1];
            var2 = var5;
         } catch (Throwable var29) {
            this.sizeCtl = Integer.MAX_VALUE;
            return;
         }

         this.nextTable = var2;
         this.transferOrigin = var3;
         this.transferIndex = var3;
         ConcurrentHashMapV8$ForwardingNode var31 = new ConcurrentHashMapV8$ForwardingNode(var1);
         int var6 = var3;

         while (var6 > 0) {
            int var7 = var6 > var4 ? var6 - var4 : 0;

            for (int var8 = var7; var8 < var6; var8++) {
               var2[var8] = var31;
            }

            for (int var35 = var3 + var7; var35 < var3 + var6; var35++) {
               var2[var35] = var31;
            }

            var6 = var7;
            U.putOrderedInt(this, TRANSFERORIGIN, var7);
         }
      }

      int var32 = var2.length;
      ConcurrentHashMapV8$ForwardingNode var33 = new ConcurrentHashMapV8$ForwardingNode(var2);
      boolean var34 = true;
      boolean var36 = false;
      int var9 = 0;
      int var10 = 0;

      while (true) {
         while (!var34) {
            if (var9 >= 0 && var9 < var3 && var9 + var3 < var32) {
               ConcurrentHashMapV8$Node var14;
               if ((var14 = tabAt(var1, var9)) != null) {
                  int var13 = var14.hash;
                  if (var14.hash == -1) {
                     var34 = true;
                  } else {
                     synchronized (var14) {
                        if (tabAt(var1, var9) == var14) {
                           if (var13 >= 0) {
                              int var18 = var13 & var3;
                              ConcurrentHashMapV8$Node var19 = var14;

                              for (ConcurrentHashMapV8$Node var20 = var14.next; var20 != null; var20 = var20.next) {
                                 int var21 = var20.hash & var3;
                                 if (var21 != var18) {
                                    var18 = var21;
                                    var19 = var20;
                                 }
                              }

                              ConcurrentHashMapV8$Node var16;
                              ConcurrentHashMapV8$Node var17;
                              if (var18 == 0) {
                                 var16 = var19;
                                 var17 = null;
                              } else {
                                 var17 = var19;
                                 var16 = null;
                              }

                              for (ConcurrentHashMapV8$Node var42 = var14; var42 != var19; var42 = var42.next) {
                                 int var44 = var42.hash;
                                 Object var22 = var42.key;
                                 Object var23 = var42.val;
                                 if ((var44 & var3) == 0) {
                                    var16 = new ConcurrentHashMapV8$Node<>(var44, var22, var23, var16);
                                 } else {
                                    var17 = new ConcurrentHashMapV8$Node<>(var44, var22, var23, var17);
                                 }
                              }

                              setTabAt(var2, var9, var16);
                              setTabAt(var2, var9 + var3, var17);
                              setTabAt(var1, var9, var33);
                              var34 = true;
                           } else if (var14 instanceof ConcurrentHashMapV8$TreeBin) {
                              ConcurrentHashMapV8$TreeBin var40 = (ConcurrentHashMapV8$TreeBin)var14;
                              ConcurrentHashMapV8$TreeNode var41 = null;
                              ConcurrentHashMapV8$TreeNode var43 = null;
                              ConcurrentHashMapV8$TreeNode var45 = null;
                              ConcurrentHashMapV8$TreeNode var46 = null;
                              int var47 = 0;
                              int var24 = 0;

                              for (Object var25 = var40.first; var25 != null; var25 = ((ConcurrentHashMapV8$Node)var25).next) {
                                 int var26 = ((ConcurrentHashMapV8$Node)var25).hash;
                                 ConcurrentHashMapV8$TreeNode var27 = new ConcurrentHashMapV8$TreeNode(
                                    var26, ((ConcurrentHashMapV8$Node)var25).key, ((ConcurrentHashMapV8$Node)var25).val, null, null
                                 );
                                 if ((var26 & var3) == 0) {
                                    if ((var27.prev = var43) == null) {
                                       var41 = var27;
                                    } else {
                                       var43.next = var27;
                                    }

                                    var43 = var27;
                                    var47++;
                                 } else {
                                    if ((var27.prev = var46) == null) {
                                       var45 = var27;
                                    } else {
                                       var46.next = var27;
                                    }

                                    var46 = var27;
                                    var24++;
                                 }
                              }

                              Object var38 = var47 <= 6 ? untreeify(var41) : (var24 != 0 ? new ConcurrentHashMapV8$TreeBin(var41) : var40);
                              Object var39 = var24 <= 6 ? untreeify(var45) : (var47 != 0 ? new ConcurrentHashMapV8$TreeBin(var45) : var40);
                              setTabAt(var2, var9, (ConcurrentHashMapV8$Node<K, V>)var38);
                              setTabAt(var2, var9 + var3, (ConcurrentHashMapV8$Node<K, V>)var39);
                              setTabAt(var1, var9, var33);
                              var34 = true;
                           }
                        }
                     }
                  }
               } else if (casTabAt(var1, var9, null, var33)) {
                  setTabAt(var2, var9, null);
                  setTabAt(var2, var9 + var3, null);
                  var34 = true;
               }
            } else {
               if (var36) {
                  this.nextTable = null;
                  this.table = var2;
                  this.sizeCtl = (var3 << 1) - (var3 >>> 1);
                  return;
               }

               int var15;
               do {
                  var15 = this.sizeCtl;
               } while (!U.compareAndSwapInt(this, SIZECTL, this.sizeCtl, ++var15));

               if (var15 != -1) {
                  return;
               }

               var34 = true;
               var36 = true;
               var9 = var3;
            }
         }

         if (--var9 < var10 && !var36) {
            int var11 = this.transferIndex;
            if (this.transferIndex <= this.transferOrigin) {
               var9 = -1;
               var34 = false;
            } else {
               int var12;
               if (U.compareAndSwapInt(this, TRANSFERINDEX, var11, var12 = var11 > var4 ? var11 - var4 : 0)) {
                  var10 = var12;
                  var9 = var11 - 1;
                  var34 = false;
               }
            }
         } else {
            var34 = false;
         }
      }
   }

   public boolean contains(Object var1) {
      return this.containsValue(var1);
   }

   @Override
   public V putIfAbsent(K var1, V var2) {
      return this.putVal((K)var1, (V)var2, true);
   }

   public static int tableSizeFor(int var0) {
      int var1 = var0 - 1;
      var1 |= var1 >>> 1;
      var1 |= var1 >>> 2;
      var1 |= var1 >>> 4;
      var1 |= var1 >>> 8;
      var1 |= var1 >>> 16;
      return var1 < 0 ? 1 : (var1 >= 1073741824 ? 1073741824 : var1 + 1);
   }

   public int batchFor(long var1) {
      long var3;
      if (var1 != (Long.MAX_VALUE & -1L) && (var3 = this.sumCount()) > (12583137L & 4895402735283775497L) && var3 >= var1) {
         int var5 = ForkJoinPool.getCommonPoolParallelism() << 2;
         long var6;
         return var1 > (6068832579526853272L & 25174112L) && (var6 = var3 / var1) < var5 ? (int)var6 : var5;
      } else {
         return 0;
      }
   }

   public <U> U reduceEntries(
      long var1, ConcurrentHashMapV8$Fun<Entry<K, V>, ? extends U> var3, ConcurrentHashMapV8$BiFun<? super U, ? super U, ? extends U> var4
   ) {
      if (var3 != null && var4 != null) {
         return new ConcurrentHashMapV8$MapReduceEntriesTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public ConcurrentHashMapV8$Node<K, V>[] helpTransfer(ConcurrentHashMapV8$Node<K, V>[] var1, ConcurrentHashMapV8$Node<K, V> var2) {
      if (var2 instanceof ConcurrentHashMapV8$ForwardingNode) {
         ConcurrentHashMapV8$Node[] var3 = ((ConcurrentHashMapV8$ForwardingNode)var2).nextTable;
         if (((ConcurrentHashMapV8$ForwardingNode)var2).nextTable != null) {
            if (var3 == this.nextTable && var1 == this.table && this.transferIndex > this.transferOrigin) {
               int var4 = this.sizeCtl;
               if (this.sizeCtl < -1 && U.compareAndSwapInt(this, SIZECTL, var4, var4 - 1)) {
                  this.transfer(var1, var3);
               }
            }

            return var3;
         }
      }

      return this.table;
   }

   public ConcurrentHashMapV8$Node<K, V>[] initTable() {
      ConcurrentHashMapV8$Node[] var1;
      while (true) {
         var1 = this.table;
         if (this.table != null && var1.length != 0) {
            break;
         }

         int var2 = this.sizeCtl;
         if (this.sizeCtl < 0) {
            Thread.yield();
         } else if (U.compareAndSwapInt(this, SIZECTL, var2, -1)) {
            try {
               var1 = this.table;
               if (this.table == null || var1.length == 0) {
                  int var3 = var2 > 0 ? var2 : 16;
                  ConcurrentHashMapV8$Node[] var4 = new ConcurrentHashMapV8$Node[var3];
                  var1 = var4;
                  this.table = var4;
                  var2 = var3 - (var3 >>> 2);
               }
               break;
            } finally {
               this.sizeCtl = var2;
            }
         }
      }

      return var1;
   }

   public <U> void forEachEntry(long var1, ConcurrentHashMapV8$Fun<Entry<K, V>, ? extends U> var3, ConcurrentHashMapV8$Action<? super U> var4) {
      if (var3 != null && var4 != null) {
         new ConcurrentHashMapV8$ForEachTransformedEntryTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U reduceValues(long var1, ConcurrentHashMapV8$Fun<? super V, ? extends U> var3, ConcurrentHashMapV8$BiFun<? super U, ? super U, ? extends U> var4) {
      if (var3 != null && var4 != null) {
         return new ConcurrentHashMapV8$MapReduceValuesTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> void forEachValue(long var1, ConcurrentHashMapV8$Fun<? super V, ? extends U> var3, ConcurrentHashMapV8$Action<? super U> var4) {
      if (var3 != null && var4 != null) {
         new ConcurrentHashMapV8$ForEachTransformedValueTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public ConcurrentHashMapV8(int var1, float var2) {
      this(var1, var2, 1);
   }

   @Override
   public boolean remove(Object var1, Object var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         return var2 != null && this.replaceNode(var1, null, var2) != null;
      }
   }

   public void forEach(long var1, ConcurrentHashMapV8$BiAction<? super K, ? super V> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         new ConcurrentHashMapV8$ForEachMappingTask<>(null, this.batchFor(var1), 0, 0, this.table, var3).invoke();
      }
   }

   public long reduceKeysToLong(long var1, ConcurrentHashMapV8$ObjectToLong<? super K> var3, long var4, ConcurrentHashMapV8$LongByLongToLong var6) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8$MapReduceKeysToLongTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U searchEntries(long var1, ConcurrentHashMapV8$Fun<Entry<K, V>, ? extends U> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8$SearchEntriesTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, new AtomicReference()).invoke();
      }
   }
}
