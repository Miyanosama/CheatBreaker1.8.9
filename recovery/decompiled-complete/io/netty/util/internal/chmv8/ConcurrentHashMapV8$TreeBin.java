package io.netty.util.internal.chmv8;

import java.util.concurrent.locks.LockSupport;
import net.minecraft.client.gui.spectator.SpectatorMenu$EndSpectatorObject;
import net.optifine.entity.model.ModelAdapterMinecart;
import sun.misc.Unsafe;

public class ConcurrentHashMapV8$TreeBin<K, V> extends ConcurrentHashMapV8$Node<K, V> {
   public static Unsafe U;
   public ModelAdapterMinecart __junk5322420595520303931;
   public volatile ConcurrentHashMapV8$TreeNode<K, V> first;
   public volatile int lockState;
   public volatile Thread waiter;
   public static int WAITER;
   public SpectatorMenu$EndSpectatorObject __junk7872916053448720426;
   public static long LOCKSTATE;
   public static int READER;
   public static int WRITER;
   public ConcurrentHashMapV8$TreeNode<K, V> root;

   static {
      try {
         U = ConcurrentHashMapV8.access$000();
         Class<ConcurrentHashMapV8$TreeBin> var0 = ConcurrentHashMapV8$TreeBin.class;
         LOCKSTATE = U.objectFieldOffset(var0.getDeclaredField("lockState"));
      } catch (Exception var1) {
         throw new Error(var1);
      }
   }

   public static <K, V> ConcurrentHashMapV8$TreeNode<K, V> rotateRight(ConcurrentHashMapV8$TreeNode<K, V> var0, ConcurrentHashMapV8$TreeNode<K, V> var1) {
      if (var1 != null) {
         ConcurrentHashMapV8$TreeNode var2 = var1.left;
         if (var1.left != null) {
            ConcurrentHashMapV8$TreeNode var4;
            if ((var4 = var1.left = var2.right) != null) {
               var4.parent = var1;
            }

            ConcurrentHashMapV8$TreeNode var3;
            if ((var3 = var2.parent = var1.parent) == null) {
               var0 = var2;
               var2.red = false;
            } else if (var3.right == var1) {
               var3.right = var2;
            } else {
               var3.left = var2;
            }

            var2.right = var1;
            var1.parent = var2;
         }
      }

      return var0;
   }

   public void contendedLock() {
      boolean var1 = false;

      while (true) {
         int var2 = this.lockState;
         if ((this.lockState & 1) == 0) {
            if (U.compareAndSwapInt(this, LOCKSTATE, var2, 1)) {
               if (var1) {
                  this.waiter = null;
               }

               return;
            }
         } else if ((var2 & 2) == 0) {
            if (U.compareAndSwapInt(this, LOCKSTATE, var2, var2 | 2)) {
               var1 = true;
               this.waiter = Thread.currentThread();
            }
         } else if (var1) {
            LockSupport.park(this);
         }
      }
   }

   public static <K, V> boolean checkInvariants(ConcurrentHashMapV8$TreeNode<K, V> var0) {
      ConcurrentHashMapV8$TreeNode var1 = var0.parent;
      ConcurrentHashMapV8$TreeNode var2 = var0.left;
      ConcurrentHashMapV8$TreeNode var3 = var0.right;
      ConcurrentHashMapV8$TreeNode var4 = var0.prev;
      ConcurrentHashMapV8$TreeNode var5 = (ConcurrentHashMapV8$TreeNode)var0.next;
      if (var4 != null && var4.next != var0) {
         return false;
      } else if (var5 != null && var5.prev != var0) {
         return false;
      } else if (var1 != null && var0 != var1.left && var0 != var1.right) {
         return false;
      } else if (var2 == null || var2.parent == var0 && var2.hash <= var0.hash) {
         if (var3 == null || var3.parent == var0 && var3.hash >= var0.hash) {
            if (var0.red && var2 != null && var2.red && var3 != null && var3.red) {
               return false;
            } else {
               return var2 != null && !checkInvariants(var2) ? false : var3 == null || checkInvariants(var3);
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static <K, V> ConcurrentHashMapV8$TreeNode<K, V> rotateLeft(ConcurrentHashMapV8$TreeNode<K, V> var0, ConcurrentHashMapV8$TreeNode<K, V> var1) {
      if (var1 != null) {
         ConcurrentHashMapV8$TreeNode var2 = var1.right;
         if (var1.right != null) {
            ConcurrentHashMapV8$TreeNode var4;
            if ((var4 = var1.right = var2.left) != null) {
               var4.parent = var1;
            }

            ConcurrentHashMapV8$TreeNode var3;
            if ((var3 = var2.parent = var1.parent) == null) {
               var0 = var2;
               var2.red = false;
            } else if (var3.left == var1) {
               var3.left = var2;
            } else {
               var3.right = var2;
            }

            var2.left = var1;
            var1.parent = var2;
         }
      }

      return var0;
   }

   public static <K, V> ConcurrentHashMapV8$TreeNode<K, V> balanceInsertion(ConcurrentHashMapV8$TreeNode<K, V> var0, ConcurrentHashMapV8$TreeNode<K, V> var1) {
      var1.red = true;

      while (true) {
         ConcurrentHashMapV8$TreeNode var2 = var1.parent;
         if (var1.parent == null) {
            var1.red = false;
            return var1;
         }

         if (!var2.red) {
            break;
         }

         ConcurrentHashMapV8$TreeNode var3 = var2.parent;
         if (var2.parent == null) {
            break;
         }

         ConcurrentHashMapV8$TreeNode var4 = var3.left;
         if (var2 == var3.left) {
            ConcurrentHashMapV8$TreeNode var5 = var3.right;
            if (var3.right != null && var5.red) {
               var5.red = false;
               var2.red = false;
               var3.red = true;
               var1 = var3;
            } else {
               if (var1 == var2.right) {
                  var1 = var2;
                  var0 = rotateLeft(var0, var2);
                  var3 = (var2 = var2.parent) == null ? null : var2.parent;
               }

               if (var2 != null) {
                  var2.red = false;
                  if (var3 != null) {
                     var3.red = true;
                     var0 = rotateRight(var0, var3);
                  }
               }
            }
         } else if (var4 != null && var4.red) {
            var4.red = false;
            var2.red = false;
            var3.red = true;
            var1 = var3;
         } else {
            if (var1 == var2.left) {
               var1 = var2;
               var0 = rotateRight(var0, var2);
               var3 = (var2 = var2.parent) == null ? null : var2.parent;
            }

            if (var2 != null) {
               var2.red = false;
               if (var3 != null) {
                  var3.red = true;
                  var0 = rotateLeft(var0, var3);
               }
            }
         }
      }

      return var0;
   }

   @Override
   public ConcurrentHashMapV8$Node<K, V> find(int var1, Object var2) {
      if (var2 != null) {
         for (Object var3 = this.first; var3 != null; var3 = ((ConcurrentHashMapV8$Node)var3).next) {
            int var4 = this.lockState;
            if ((this.lockState & 3) != 0) {
               if (((ConcurrentHashMapV8$Node)var3).hash == var1) {
                  Object var5 = ((ConcurrentHashMapV8$Node)var3).key;
                  if (((ConcurrentHashMapV8$Node)var3).key == var2 || var5 != null && var2.equals(var5)) {
                     return (ConcurrentHashMapV8$Node<K, V>)var3;
                  }
               }
            } else if (U.compareAndSwapInt(this, LOCKSTATE, var4, var4 + 4)) {
               ConcurrentHashMapV8$TreeNode var7;
               try {
                  ConcurrentHashMapV8$TreeNode var6 = this.root;
                  var7 = this.root == null ? null : var6.findTreeNode(var1, var2, null);
               } finally {
                  int var12;
                  do {
                     var12 = this.lockState;
                  } while (!U.compareAndSwapInt(this, LOCKSTATE, this.lockState, var12 - 4));

                  if (var12 == 6) {
                     Thread var11 = this.waiter;
                     if (this.waiter != null) {
                        LockSupport.unpark(var11);
                     }
                  }
               }

               return var7;
            }
         }
      }

      return null;
   }

   public void lockRoot() {
      if (!U.compareAndSwapInt(this, LOCKSTATE, 0, 1)) {
         this.contendedLock();
      }
   }

   public static <K, V> ConcurrentHashMapV8$TreeNode<K, V> balanceDeletion(ConcurrentHashMapV8$TreeNode<K, V> var0, ConcurrentHashMapV8$TreeNode<K, V> var1) {
      while (var1 != null && var1 != var0) {
         ConcurrentHashMapV8$TreeNode var2 = var1.parent;
         if (var1.parent == null) {
            var1.red = false;
            return var1;
         }

         if (var1.red) {
            var1.red = false;
            return var0;
         }

         ConcurrentHashMapV8$TreeNode var3 = var2.left;
         if (var2.left == var1) {
            ConcurrentHashMapV8$TreeNode var4 = var2.right;
            if (var2.right != null && var4.red) {
               var4.red = false;
               var2.red = true;
               var0 = rotateLeft(var0, var2);
               var2 = var1.parent;
               var4 = var1.parent == null ? null : var2.right;
            }

            if (var4 == null) {
               var1 = var2;
            } else {
               ConcurrentHashMapV8$TreeNode var8 = var4.left;
               ConcurrentHashMapV8$TreeNode var9 = var4.right;
               if (var9 != null && var9.red || var8 != null && var8.red) {
                  if (var9 == null || !var9.red) {
                     if (var8 != null) {
                        var8.red = false;
                     }

                     var4.red = true;
                     var0 = rotateRight(var0, var4);
                     var2 = var1.parent;
                     var4 = var1.parent == null ? null : var2.right;
                  }

                  if (var4 != null) {
                     var4.red = var2 == null ? false : var2.red;
                     var9 = var4.right;
                     if (var4.right != null) {
                        var9.red = false;
                     }
                  }

                  if (var2 != null) {
                     var2.red = false;
                     var0 = rotateLeft(var0, var2);
                  }

                  var1 = var0;
               } else {
                  var4.red = true;
                  var1 = var2;
               }
            }
         } else {
            if (var3 != null && var3.red) {
               var3.red = false;
               var2.red = true;
               var0 = rotateRight(var0, var2);
               var2 = var1.parent;
               var3 = var1.parent == null ? null : var2.left;
            }

            if (var3 == null) {
               var1 = var2;
            } else {
               ConcurrentHashMapV8$TreeNode var5 = var3.left;
               ConcurrentHashMapV8$TreeNode var6 = var3.right;
               if (var5 != null && var5.red || var6 != null && var6.red) {
                  if (var5 == null || !var5.red) {
                     if (var6 != null) {
                        var6.red = false;
                     }

                     var3.red = true;
                     var0 = rotateLeft(var0, var3);
                     var2 = var1.parent;
                     var3 = var1.parent == null ? null : var2.left;
                  }

                  if (var3 != null) {
                     var3.red = var2 == null ? false : var2.red;
                     var5 = var3.left;
                     if (var3.left != null) {
                        var5.red = false;
                     }
                  }

                  if (var2 != null) {
                     var2.red = false;
                     var0 = rotateRight(var0, var2);
                  }

                  var1 = var0;
               } else {
                  var3.red = true;
                  var1 = var2;
               }
            }
         }
      }

      return var0;
   }

   public ConcurrentHashMapV8$TreeBin(ConcurrentHashMapV8$TreeNode<K, V> var1) {
      super(-2, null, null, null);
      this.first = var1;
      ConcurrentHashMapV8$TreeNode var2 = null;
      ConcurrentHashMapV8$TreeNode var3 = var1;

      while (var3 != null) {
         ConcurrentHashMapV8$TreeNode var4 = (ConcurrentHashMapV8$TreeNode)var3.next;
         var3.left = var3.right = null;
         if (var2 == null) {
            var3.parent = null;
            var3.red = false;
            var2 = var3;
         } else {
            Object var5 = var3.key;
            int var6 = var3.hash;
            Class var7 = null;
            ConcurrentHashMapV8$TreeNode var8 = var2;

            int var9;
            ConcurrentHashMapV8$TreeNode var11;
            do {
               int var10 = var8.hash;
               if (var8.hash > var6) {
                  var9 = -1;
               } else if (var10 < var6) {
                  var9 = 1;
               } else if (var7 == null && (var7 = ConcurrentHashMapV8.comparableClassFor(var5)) == null) {
                  var9 = 0;
               } else {
                  var9 = ConcurrentHashMapV8.compareComparables(var7, var5, var8.key);
               }

               var11 = var8;
            } while ((var8 = var9 <= 0 ? var8.left : var8.right) != null);

            var3.parent = var11;
            if (var9 <= 0) {
               var11.left = var3;
            } else {
               var11.right = var3;
            }

            var2 = balanceInsertion(var2, var3);
         }

         var3 = var4;
      }

      this.root = var2;
   }

   public void unlockRoot() {
      this.lockState = 0;
   }

   public ConcurrentHashMapV8$TreeNode<K, V> putTreeVal(int var1, K var2, V var3) {
      Class var4 = null;
      ConcurrentHashMapV8$TreeNode var5 = this.root;

      while (true) {
         if (var5 == null) {
            this.first = this.root = new ConcurrentHashMapV8$TreeNode<>(var1, (K)var2, (V)var3, null, null);
         } else {
            int var7 = var5.hash;
            int var6;
            if (var5.hash > var1) {
               var6 = -1;
            } else if (var7 < var1) {
               var6 = 1;
            } else {
               Object var8 = var5.key;
               if (var5.key == var2 || var8 != null && var2.equals(var8)) {
                  return var5;
               }

               if (var4 == null && (var4 = ConcurrentHashMapV8.comparableClassFor(var2)) == null
                  || (var6 = ConcurrentHashMapV8.compareComparables(var4, var2, var8)) == 0) {
                  if (var5.left == null) {
                     var6 = 1;
                  } else {
                     ConcurrentHashMapV8$TreeNode var10 = var5.right;
                     ConcurrentHashMapV8$TreeNode var9;
                     if (var5.right != null && (var9 = var10.findTreeNode(var1, var2, var4)) != null) {
                        return var9;
                     }

                     var6 = -1;
                  }
               }
            }

            ConcurrentHashMapV8$TreeNode var11 = var5;
            if ((var5 = var6 < 0 ? var5.left : var5.right) != null) {
               continue;
            }

            ConcurrentHashMapV8$TreeNode var13 = this.first;
            ConcurrentHashMapV8$TreeNode var12;
            this.first = var12 = new ConcurrentHashMapV8$TreeNode<>(var1, var2, var3, var13, var11);
            if (var13 != null) {
               var13.prev = var12;
            }

            if (var6 < 0) {
               var11.left = var12;
            } else {
               var11.right = var12;
            }

            if (!var11.red) {
               var12.red = true;
            } else {
               this.lockRoot();

               try {
                  this.root = balanceInsertion(this.root, var12);
               } finally {
                  this.unlockRoot();
               }
            }
         }

         if (!$assertionsDisabled && !checkInvariants(this.root)) {
            throw new AssertionError();
         }

         return null;
      }
   }

   public boolean removeTreeNode(ConcurrentHashMapV8$TreeNode<K, V> var1) {
      ConcurrentHashMapV8$TreeNode var2 = (ConcurrentHashMapV8$TreeNode)var1.next;
      ConcurrentHashMapV8$TreeNode var3 = var1.prev;
      if (var3 == null) {
         this.first = var2;
      } else {
         var3.next = var2;
      }

      if (var2 != null) {
         var2.prev = var3;
      }

      if (this.first == null) {
         this.root = null;
         return true;
      } else {
         ConcurrentHashMapV8$TreeNode var4 = this.root;
         if (this.root != null && var4.right != null) {
            ConcurrentHashMapV8$TreeNode var5 = var4.left;
            if (var4.left != null && var5.left != null) {
               this.lockRoot();

               try {
                  ConcurrentHashMapV8$TreeNode var7 = var1.left;
                  ConcurrentHashMapV8$TreeNode var8 = var1.right;
                  ConcurrentHashMapV8$TreeNode var6;
                  if (var7 != null && var8 != null) {
                     ConcurrentHashMapV8$TreeNode var9 = var8;

                     while (true) {
                        ConcurrentHashMapV8$TreeNode var10 = var9.left;
                        if (var9.left == null) {
                           boolean var11 = var9.red;
                           var9.red = var1.red;
                           var1.red = var11;
                           ConcurrentHashMapV8$TreeNode var12 = var9.right;
                           ConcurrentHashMapV8$TreeNode var13 = var1.parent;
                           if (var9 == var8) {
                              var1.parent = var9;
                              var9.right = var1;
                           } else {
                              ConcurrentHashMapV8$TreeNode var14 = var9.parent;
                              if ((var1.parent = var14) != null) {
                                 if (var9 == var14.left) {
                                    var14.left = var1;
                                 } else {
                                    var14.right = var1;
                                 }
                              }

                              var9.right = var8;
                              var8.parent = var9;
                           }

                           var1.left = null;
                           var9.left = var7;
                           var7.parent = var9;
                           if ((var1.right = var12) != null) {
                              var12.parent = var1;
                           }

                           if ((var9.parent = var13) == null) {
                              var4 = var9;
                           } else if (var1 == var13.left) {
                              var13.left = var9;
                           } else {
                              var13.right = var9;
                           }

                           if (var12 != null) {
                              var6 = var12;
                           } else {
                              var6 = var1;
                           }
                           break;
                        }

                        var9 = var10;
                     }
                  } else if (var7 != null) {
                     var6 = var7;
                  } else if (var8 != null) {
                     var6 = var8;
                  } else {
                     var6 = var1;
                  }

                  if (var6 != var1) {
                     ConcurrentHashMapV8$TreeNode var18 = var6.parent = var1.parent;
                     if (var18 == null) {
                        var4 = var6;
                     } else if (var1 == var18.left) {
                        var18.left = var6;
                     } else {
                        var18.right = var6;
                     }

                     var1.left = var1.right = var1.parent = null;
                  }

                  this.root = var1.red ? var4 : balanceDeletion(var4, var6);
                  if (var1 == var6) {
                     ConcurrentHashMapV8$TreeNode var19 = var1.parent;
                     if (var1.parent != null) {
                        if (var1 == var19.left) {
                           var19.left = null;
                        } else if (var1 == var19.right) {
                           var19.right = null;
                        }

                        var1.parent = null;
                     }
                  }
               } finally {
                  this.unlockRoot();
               }

               if (!$assertionsDisabled && !checkInvariants(this.root)) {
                  throw new AssertionError();
               }

               return false;
            }
         }

         return true;
      }
   }
}
