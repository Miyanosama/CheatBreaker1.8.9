package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.module.ModuleManager;
import com.cheatbreaker.client.module.type.MiniMapModule;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.element.module.ModulesGuiButtonElement;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.module.CBModulePosition;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.overlay.friend.FriendsListElement;
import com.cheatbreaker.client.util.title.Title;
import io.netty.buffer.AbstractByteBuf;
import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.channel.AbstractChannel;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.embedded.EmbeddedSocketAddress;
import io.netty.channel.oio.OioByteStreamChannel;
import io.netty.channel.rxtx.RxtxChannelConfig;
import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.compression.Snappy;
import io.netty.handler.codec.compression.ZlibUtil;
import io.netty.handler.codec.http.CookieDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.concurrent.FailedFuture;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.Cleaner0;
import io.netty.util.internal.IntegerHolder;
import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.MpscLinkedQueue;
import io.netty.util.internal.NoOpTypeParameterMatcher;
import io.netty.util.internal.RecyclableArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import javax.vecmath.GMatrix;
import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple4f;
import javazoom.jl.decoder.LayerIDecoder;
import net.minecraft.block.BlockDoubleStoneSlab;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.gui.spectator.categories.TeleportToTeam;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.command.server.CommandAchievement;
import net.minecraft.enchantment.EnchantmentArrowFire;
import net.minecraft.enchantment.EnchantmentWaterWorker;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemSnow;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.realms.RealmsMth;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.MouseHelper;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.NoiseGeneratorImproved;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.storage.SaveFormatComparator;
import net.optifine.ConnectedProperties;
import net.optifine.Log;
import net.optifine.entity.model.ModelAdapterEnderChest;
import net.optifine.entity.model.ModelAdapterEnderCrystal;
import net.optifine.entity.model.ModelAdapterMinecart;
import net.optifine.entity.model.ModelAdapterMinecartTnt;
import net.optifine.entity.model.ModelAdapterSnowman;
import net.optifine.model.BlockModelUtils;
import net.optifine.override.ChunkCacheOF;
import net.optifine.reflect.FieldLocatorTypes;
import net.optifine.shaders.ItemAliases;
import net.optifine.shaders.SVertexAttrib;
import net.optifine.shaders.uniform.ShaderUniformBase;
import net.optifine.util.Json;
import net.optifine.util.StrUtils;
import org.apache.log4j.AsyncAppender;
import org.apache.log4j.chainsaw.LoggingReceiver;
import org.apache.log4j.helpers.ISO8601DateFormat;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$4;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryElement;
import org.apache.log4j.net.JMSSink;
import org.scijava.nativelib.NativeLibraryUtil;
import com.cheatbreaker.client.command.ModuleCommand;
import net.minecraft.util.Timer;
import net.minecraft.item.ItemReed;
import com.cheatbreaker.client.util.branch.BranchManager;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.optifine.expr.TestExpressions;
import net.minecraft.server.management.PlayerProfileCache$1;
import net.minecraft.client.renderer.entity.RenderLightningBolt;
import com.cheatbreaker.client.ui.loading.StartupLoadingGui;
import com.cheatbreaker.client.util.server.LocalServerRestrictions;
import sun.misc.Unsafe;

public class ConcurrentHashMapV8<K, V> implements Serializable, ConcurrentMap<K, V> {
   public static long CELLSBUSY;
   public static Unsafe U;
   public transient ConcurrentHashMapV8.KeySetView<K, V> keySet;
   public static final int MAX_ARRAY_SIZE = 2147483639;
   public static long CELLVALUE;
   public static final int UNTREEIFY_THRESHOLD = 6;
   public static final int RESERVED = -3;
   public static final int MIN_TREEIFY_CAPACITY = 64;
   public static final int DEFAULT_CAPACITY = 16;
   public static long SIZECTL;
   public static final int TREEIFY_THRESHOLD = 8;
   public static final int MOVED = -1;
   public transient volatile int cellsBusy;
   public static final int MAXIMUM_CAPACITY = 1073741824;
   public transient volatile int transferOrigin;
   public transient volatile int transferIndex;
   public transient ConcurrentHashMapV8.EntrySetView<K, V> entrySet;
   public static int ASHIFT;
   public static long ABASE;
   public static final int SEED_INCREMENT = 1640531527;
   public transient volatile int sizeCtl;
   public static final float LOAD_FACTOR = 0.75F;
   public static final int DEFAULT_CONCURRENCY_LEVEL = 16;
   public static long TRANSFERINDEX;
   public static final long serialVersionUID = 7249069246763182397L;
   public static final int TREEBIN = -2;
   public transient volatile ConcurrentHashMapV8.CounterCell[] counterCells;
   public static long TRANSFERORIGIN;
   public static long BASECOUNT;
   public transient ConcurrentHashMapV8.ValuesView<K, V> values;
   public static final int MIN_TRANSFER_STRIDE = 16;
   public transient volatile ConcurrentHashMapV8.Node<K, V>[] table;
   public transient volatile ConcurrentHashMapV8.Node<K, V>[] nextTable;
   public static final int HASH_BITS = 2147483647;
   public static int NCPU = Runtime.getRuntime().availableProcessors();
   public static ObjectStreamField[] serialPersistentFields = new ObjectStreamField[]{
      new ObjectStreamField("segments", ConcurrentHashMapV8.Segment[].class),
      new ObjectStreamField("segmentMask", int.class),
      new ObjectStreamField("segmentShift", int.class)
   };
   public static AtomicInteger counterHashCodeGenerator = new AtomicInteger();
   public transient volatile long baseCount;

   public void treeifyBin(ConcurrentHashMapV8.Node<K, V>[] var1, int var2) {
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
            ConcurrentHashMapV8.Node<K,V> var3;
            if ((var3 = tabAt(var1, var2)) != null && var3.hash >= 0) {
               synchronized (var3) {
                  if (tabAt(var1, var2) == var3) {
                     ConcurrentHashMapV8.TreeNode var7 = null;
                     ConcurrentHashMapV8.TreeNode var8 = null;

                     for (ConcurrentHashMapV8.Node<K,V> var9 = var3; var9 != null; var9 = var9.next) {
                        ConcurrentHashMapV8.TreeNode var10 = new ConcurrentHashMapV8.TreeNode(var9.hash, var9.key, var9.val, null, null);
                        if ((var10.prev = var8) == null) {
                           var7 = var10;
                        } else {
                           var8.next = var10;
                        }

                        var8 = var10;
                     }

                     setTabAt(var1, var2, new ConcurrentHashMapV8.TreeBin<>(var7));
                  }
               }
            }
         }
      }
   }

   public void forEachValue(long var1, ConcurrentHashMapV8.Action<? super V> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         new ConcurrentHashMapV8.ForEachValueTask<>(null, this.batchFor(var1), 0, 0, this.table, var3).invoke();
      }
   }

   @Override
   public V get(Object var1) {
      int var8 = spread(var1.hashCode());
      ConcurrentHashMapV8.Node[] var2 = this.table;
      ConcurrentHashMapV8.Node<K,V> var3;
      int var5;
      if (this.table != null && (var5 = var2.length) > 0 && (var3 = tabAt(var2, var5 - 1 & var8)) != null) {
         int var6 = var3.hash;
         if (var3.hash == var8) {
            Object var7 = var3.key;
            if (var3.key == var1 || var7 != null && var1.equals(var7)) {
               return var3.val;
            }
         } else if (var6 < 0) {
            ConcurrentHashMapV8.Node<K,V> var4;
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
         ConcurrentHashMapV8.Node[] var6 = this.table;

         while (true) {
            int var8;
            while (var6 == null || (var8 = var6.length) == 0) {
               var6 = this.initTable();
            }

            ConcurrentHashMapV8.Node<K,V> var7;
            int var9;
            if ((var7 = tabAt(var6, var9 = var8 - 1 & var4)) == null) {
               if (casTabAt(var6, var9, null, new ConcurrentHashMapV8.Node(var4, var1, var2, null))) {
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
                           if (var7 instanceof ConcurrentHashMapV8.TreeBin) {
                              var5 = 2;
                              ConcurrentHashMapV8.TreeNode var18;
                              if ((var18 = ((ConcurrentHashMapV8.TreeBin)var7).putTreeVal(var4, var1, var2)) != null) {
                                 var11 = var18.val;
                                 if (!var3) {
                                    var18.val = (V)var2;
                                 }
                              }
                           }
                        } else {
                           var5 = 1;
                           ConcurrentHashMapV8.Node<K,V> var13 = var7;

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

                              ConcurrentHashMapV8.Node<K,V> var15 = var13;
                              if ((var13 = var13.next) == null) {
                                 var15.next = new ConcurrentHashMapV8.Node(var4, (K)var1, (V)var2, null);
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

         this.addCount(1L, var5);
         return null;
      } else {
         throw new NullPointerException();
      }
   }

   public <U> void forEach(long var1, ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> var3, ConcurrentHashMapV8.Action<? super U> var4) {
      if (var3 != null && var4 != null) {
         new ConcurrentHashMapV8.ForEachTransformedMappingTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public long sumCount() {
      ConcurrentHashMapV8.CounterCell[] var1 = this.counterCells;
      long var3 = this.baseCount;
      if (var1 != null) {
         for (int var5 = 0; var5 < var1.length; var5++) {
            ConcurrentHashMapV8.CounterCell var2;
            if ((var2 = var1[var5]) != null) {
               var3 += var2.value;
            }
         }
      }

      return var3;
   }

   public double reduceEntriesToDouble(
      long var1, ConcurrentHashMapV8.ObjectToDouble<Entry<K, V>> var3, double var4, ConcurrentHashMapV8.DoubleByDoubleToDouble var6
   ) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8.MapReduceEntriesToDoubleTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public V getOrDefault(Object var1, V var2) {
      Object var3;
      return (V)((var3 = this.get(var1)) == null ? var2 : var3);
   }

   public V reduceValues(long var1, ConcurrentHashMapV8.BiFun<? super V, ? super V, ? extends V> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8.ReduceValuesTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3).invoke();
      }
   }

   public int reduceKeysToInt(long var1, ConcurrentHashMapV8.ObjectToInt<? super K> var3, int var4, ConcurrentHashMapV8.IntByIntToInt var5) {
      if (var3 != null && var5 != null) {
         return new ConcurrentHashMapV8.MapReduceKeysToIntTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var5).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public int reduceValuesToInt(long var1, ConcurrentHashMapV8.ObjectToInt<? super V> var3, int var4, ConcurrentHashMapV8.IntByIntToInt var5) {
      if (var3 != null && var5 != null) {
         return new ConcurrentHashMapV8.MapReduceValuesToIntTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var5).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public double reduceValuesToDouble(
      long var1, ConcurrentHashMapV8.ObjectToDouble<? super V> var3, double var4, ConcurrentHashMapV8.DoubleByDoubleToDouble var6
   ) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8.MapReduceValuesToDoubleTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public static int spread(int var0) {
      return (var0 ^ var0 >>> 16) & 2147483647;
   }

   public ConcurrentHashMapV8.KeySetView<K, V> keySet() {
      ConcurrentHashMapV8.KeySetView var1 = this.keySet;
      return this.keySet != null ? var1 : (this.keySet = new ConcurrentHashMapV8.KeySetView<>(this, null));
   }

   public static <K, V> ConcurrentHashMapV8.Node<K, V> untreeify(ConcurrentHashMapV8.Node<K, V> var0) {
      ConcurrentHashMapV8.Node<K,V> var1 = null;
      ConcurrentHashMapV8.Node<K,V> var2 = null;

      for (ConcurrentHashMapV8.Node<K,V> var3 = var0; var3 != null; var3 = var3.next) {
         ConcurrentHashMapV8.Node<K,V> var4 = new ConcurrentHashMapV8.Node(var3.hash, var3.key, var3.val, null);
         if (var2 == null) {
            var1 = var4;
         } else {
            var2.next = var4;
         }

         var2 = var4;
      }

      return var1;
   }

   public void writeObject(ObjectOutputStream var1) throws java.io.IOException {
      int var2 = 0;

      int var3;
      for (var3 = 1; var3 < 16; var3 <<= 1) {
         var2++;
      }

      int var4 = 32 - var2;
      int var5 = var3 - 1;
      ConcurrentHashMapV8.Segment[] var6 = new ConcurrentHashMapV8.Segment[16];

      for (int var7 = 0; var7 < var6.length; var7++) {
         var6[var7] = new ConcurrentHashMapV8.Segment(0.75F);
      }

      var1.putFields().put("segments", var6);
      var1.putFields().put("segmentShift", var4);
      var1.putFields().put("segmentMask", var5);
      var1.writeFields();
      ConcurrentHashMapV8.Node[] var11 = this.table;
      if (this.table != null) {
         ConcurrentHashMapV8.Traverser var8 = new ConcurrentHashMapV8.Traverser(var11, var11.length, 0, var11.length);

         ConcurrentHashMapV8.Node<K,V> var9;
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
            ConcurrentHashMapV8.CounterCell[] var4 = this.counterCells;
            if (this.counterCells == null) {
               long var5 = this.baseCount;
               if (U.compareAndSwapLong(this, BASECOUNT, this.baseCount, var7 = var5 + var1)) {
                  break label71;
               }
            }

            var14 = true;
            var15 = InternalThreadLocalMap.get();
            ConcurrentHashMapV8.CounterCell var10;
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

               ConcurrentHashMapV8.Node[] var16 = this.table;
               if (this.table == null || var16.length >= 1073741824) {
                  break;
               }

               if (var18 < 0) {
                  if (var18 == -1 || this.transferIndex <= this.transferOrigin) {
                     break;
                  }

                  ConcurrentHashMapV8.Node[] var17 = this.nextTable;
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

   public static <K> ConcurrentHashMapV8.KeySetView<K, Boolean> newKeySet() {
      return new ConcurrentHashMapV8.KeySetView<>(new ConcurrentHashMapV8<>(), Boolean.TRUE);
   }

   public long reduceToLong(
      long var1, ConcurrentHashMapV8.ObjectByObjectToLong<? super K, ? super V> var3, long var4, ConcurrentHashMapV8.LongByLongToLong var6
   ) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8.MapReduceMappingsToLongTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
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
         Class<ConcurrentHashMapV8.CounterCell> var1 = ConcurrentHashMapV8.CounterCell.class;
         CELLVALUE = U.objectFieldOffset(var1.getDeclaredField("value"));
         Class<ConcurrentHashMapV8.Node[]> var2 = ConcurrentHashMapV8.Node[].class;
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
      return this.sumCount() <= 0L;
   }

   public V compute(K var1, ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends V> var2) {
      if (var1 != null && var2 != null) {
         int var3 = spread(var1.hashCode());
         Object var4 = null;
         byte var5 = 0;
         int var6 = 0;
         ConcurrentHashMapV8.Node[] var7 = this.table;

         while (true) {
            int var9;
            while (var7 == null || (var9 = var7.length) == 0) {
               var7 = this.initTable();
            }

            ConcurrentHashMapV8.Node<K,V> var8;
            int var10;
            if ((var8 = tabAt(var7, var10 = var9 - 1 & var3)) == null) {
               ConcurrentHashMapV8.ReservationNode var12 = new ConcurrentHashMapV8.ReservationNode();
               synchronized (var12) {
                  if (casTabAt(var7, var10, null, var12)) {
                     var6 = 1;
                     ConcurrentHashMapV8.Node<K,V> var26 = null;

                     try {
                        if ((var4 = var2.apply(var1, null)) != null) {
                           var5 = 1;
                           var26 = new ConcurrentHashMapV8.Node(var3, var1, var4, null);
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
                           if (var8 instanceof ConcurrentHashMapV8.TreeBin) {
                              var6 = 1;
                              ConcurrentHashMapV8.TreeBin var24 = (ConcurrentHashMapV8.TreeBin)var8;
                              ConcurrentHashMapV8.TreeNode var25 = var24.root;
                              ConcurrentHashMapV8.TreeNode var27;
                              if (var24.root != null) {
                                 var27 = var25.findTreeNode(var3, var1, null);
                              } else {
                                 var27 = null;
                              }

                              Object var28 = var27 == null ? null : var27.val;
                              var4 = var2.apply(var1, (V)var28);
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
                           ConcurrentHashMapV8.Node<K,V> var13 = var8;
                           ConcurrentHashMapV8.Node<K,V> var14 = null;

                           while (true) {
                              if (var13.hash == var3) {
                                 Object var15 = var13.key;
                                 if (var13.key == var1 || var15 != null && var1.equals(var15)) {
                                    var4 = var2.apply(var1, var13.val);
                                    if (var4 != null) {
                                       var13.val = (V)var4;
                                    } else {
                                       var5 = -1;
                                       ConcurrentHashMapV8.Node<K,V> var16 = var13.next;
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
                                    var14.next = new ConcurrentHashMapV8.Node(var3, (K)var1, (V)var4, null);
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

   public V computeIfAbsent(K var1, ConcurrentHashMapV8.Fun<? super K, ? extends V> var2) {
      if (var1 != null && var2 != null) {
         int var3 = spread(var1.hashCode());
         Object var4 = null;
         int var5 = 0;
         ConcurrentHashMapV8.Node[] var6 = this.table;

         while (true) {
            int var8;
            while (var6 == null || (var8 = var6.length) == 0) {
               var6 = this.initTable();
            }

            ConcurrentHashMapV8.Node<K,V> var7;
            int var9;
            if ((var7 = tabAt(var6, var9 = var8 - 1 & var3)) == null) {
               ConcurrentHashMapV8.ReservationNode var24 = new ConcurrentHashMapV8.ReservationNode();
               synchronized (var24) {
                  if (casTabAt(var6, var9, null, var24)) {
                     var5 = 1;
                     ConcurrentHashMapV8.Node<K,V> var26 = null;

                     try {
                        if ((var4 = var2.apply(var1)) != null) {
                           var26 = new ConcurrentHashMapV8.Node(var3, var1, var4, null);
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
                           if (var7 instanceof ConcurrentHashMapV8.TreeBin) {
                              var5 = 2;
                              ConcurrentHashMapV8.TreeBin var25 = (ConcurrentHashMapV8.TreeBin)var7;
                              ConcurrentHashMapV8.TreeNode var27 = var25.root;
                              ConcurrentHashMapV8.TreeNode var15;
                              if (var25.root != null && (var15 = var27.findTreeNode(var3, var1, null)) != null) {
                                 var4 = var15.val;
                              } else if ((var4 = var2.apply(var1)) != null) {
                                 var11 = true;
                                 var25.putTreeVal(var3, var1, var4);
                              }
                           }
                        } else {
                           var5 = 1;
                           ConcurrentHashMapV8.Node<K,V> var13 = var7;

                           while (true) {
                              if (var13.hash == var3) {
                                 Object var14 = var13.key;
                                 if (var13.key == var1 || var14 != null && var1.equals(var14)) {
                                    var4 = var13.val;
                                    break;
                                 }
                              }

                              ConcurrentHashMapV8.Node<K,V> var16 = var13;
                              if ((var13 = var13.next) == null) {
                                 if ((var4 = var2.apply(var1)) != null) {
                                    var11 = true;
                                    var16.next = new ConcurrentHashMapV8.Node(var3, (K)var1, (V)var4, null);
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
            this.addCount(1L, var5);
         }

         return (V)var4;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public Collection<V> values() {
      ConcurrentHashMapV8.ValuesView var1 = this.values;
      return this.values != null ? var1 : (this.values = new ConcurrentHashMapV8.ValuesView<>(this));
   }

   public long reduceValuesToLong(long var1, ConcurrentHashMapV8.ObjectToLong<? super V> var3, long var4, ConcurrentHashMapV8.LongByLongToLong var6) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8.MapReduceValuesToLongTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public static <K, V> void setTabAt(ConcurrentHashMapV8.Node<K, V>[] var0, int var1, ConcurrentHashMapV8.Node<K, V> var2) {
      U.putObjectVolatile(var0, ((long)var1 << ASHIFT) + ABASE, var2);
   }

   public Enumeration<K> keys() {
      ConcurrentHashMapV8.Node[] var1 = this.table;
      int var2 = this.table == null ? 0 : var1.length;
      return new ConcurrentHashMapV8.KeyIterator<>(var1, var2, 0, var2, this);
   }

   public static <K> ConcurrentHashMapV8.KeySetView<K, Boolean> newKeySet(int var0) {
      return new ConcurrentHashMapV8.KeySetView<>(new ConcurrentHashMapV8<>(var0), Boolean.TRUE);
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
      ConcurrentHashMapV8.Node[] var1 = this.table;
      int var2 = this.table == null ? 0 : var1.length;
      return new ConcurrentHashMapV8.ValueIterator<>(var1, var2, 0, var2, this);
   }

   public V computeIfPresent(K var1, ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends V> var2) {
      if (var1 != null && var2 != null) {
         int var3 = spread(var1.hashCode());
         Object var4 = null;
         byte var5 = 0;
         int var6 = 0;
         ConcurrentHashMapV8.Node[] var7 = this.table;

         while (true) {
            int var9;
            while (var7 == null || (var9 = var7.length) == 0) {
               var7 = this.initTable();
            }

            ConcurrentHashMapV8.Node<K,V> var8;
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
                        if (var8 instanceof ConcurrentHashMapV8.TreeBin) {
                           var6 = 2;
                           ConcurrentHashMapV8.TreeBin var19 = (ConcurrentHashMapV8.TreeBin)var8;
                           ConcurrentHashMapV8.TreeNode var20 = var19.root;
                           ConcurrentHashMapV8.TreeNode var21;
                           if (var19.root != null && (var21 = var20.findTreeNode(var3, var1, null)) != null) {
                              var4 = var2.apply(var1, (V)var21.val);
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
                        ConcurrentHashMapV8.Node<K,V> var13 = var8;
                        ConcurrentHashMapV8.Node<K,V> var14 = null;

                        while (true) {
                           if (var13.hash == var3) {
                              Object var15 = var13.key;
                              if (var13.key == var1 || var15 != null && var1.equals(var15)) {
                                 var4 = var2.apply(var1, var13.val);
                                 if (var4 != null) {
                                    var13.val = (V)var4;
                                 } else {
                                    var5 = -1;
                                    ConcurrentHashMapV8.Node<K,V> var16 = var13.next;
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
      long var1, ConcurrentHashMapV8.ObjectByObjectToDouble<? super K, ? super V> var3, double var4, ConcurrentHashMapV8.DoubleByDoubleToDouble var6
   ) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8.MapReduceMappingsToDoubleTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public boolean containsValue(Object var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8.Node[] var2 = this.table;
         if (this.table != null) {
            ConcurrentHashMapV8.Traverser var3 = new ConcurrentHashMapV8.Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8.Node<K,V> var4;
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

   public V merge(K var1, V var2, ConcurrentHashMapV8.BiFun<? super V, ? super V, ? extends V> var3) {
      if (var1 != null && var2 != null && var3 != null) {
         int var4 = spread(var1.hashCode());
         Object var5 = null;
         byte var6 = 0;
         int var7 = 0;
         ConcurrentHashMapV8.Node[] var8 = this.table;

         while (true) {
            int var10;
            while (var8 == null || (var10 = var8.length) == 0) {
               var8 = this.initTable();
            }

            ConcurrentHashMapV8.Node<K,V> var9;
            int var11;
            if ((var9 = tabAt(var8, var11 = var10 - 1 & var4)) == null) {
               if (casTabAt(var8, var11, null, new ConcurrentHashMapV8.Node(var4, var1, var2, null))) {
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
                           if (var9 instanceof ConcurrentHashMapV8.TreeBin) {
                              var7 = 2;
                              ConcurrentHashMapV8.TreeBin var20 = (ConcurrentHashMapV8.TreeBin)var9;
                              ConcurrentHashMapV8.TreeNode var21 = var20.root;
                              ConcurrentHashMapV8.TreeNode var22 = var21 == null ? null : var21.findTreeNode(var4, var1, null);
                              var5 = var22 == null ? var2 : var3.apply((V)var22.val, var2);
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
                           ConcurrentHashMapV8.Node<K,V> var14 = var9;
                           ConcurrentHashMapV8.Node<K,V> var15 = null;

                           while (true) {
                              if (var14.hash == var4) {
                                 Object var16 = var14.key;
                                 if (var14.key == var1 || var16 != null && var1.equals(var16)) {
                                    var5 = var3.apply(var14.val, var2);
                                    if (var5 != null) {
                                       var14.val = (V)var5;
                                    } else {
                                       var6 = -1;
                                       ConcurrentHashMapV8.Node<K,V> var17 = var14.next;
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
                                 var15.next = new ConcurrentHashMapV8.Node(var4, (K)var1, (V)var2, null);
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
      ConcurrentHashMapV8.EntrySetView var1 = this.entrySet;
      return this.entrySet != null ? var1 : (this.entrySet = new ConcurrentHashMapV8.EntrySetView<>(this));
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
         ConcurrentHashMapV8.CounterCell[] var8 = this.counterCells;
         int var10;
         if (this.counterCells != null && (var10 = var8.length) > 0) {
            ConcurrentHashMapV8.CounterCell var9;
            if ((var9 = var8[var10 - 1 & var6]) == null) {
               if (this.cellsBusy == 0) {
                  ConcurrentHashMapV8.CounterCell var37 = new ConcurrentHashMapV8.CounterCell(var2);
                  if (this.cellsBusy == 0 && U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                     boolean var39 = false;

                     try {
                        ConcurrentHashMapV8.CounterCell[] var15 = this.counterCells;
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
                        ConcurrentHashMapV8.CounterCell[] var38 = new ConcurrentHashMapV8.CounterCell[var10 << 1];

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
                  ConcurrentHashMapV8.CounterCell[] var14 = new ConcurrentHashMapV8.CounterCell[2];
                  var14[var6 & 1] = new ConcurrentHashMapV8.CounterCell(var2);
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

         ConcurrentHashMapV8.Node[] var4 = this.table;
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
                     ConcurrentHashMapV8.Node[] var6 = new ConcurrentHashMapV8.Node[var5];
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
         int var6 = var4 >= 1073741824L ? 1073741824 : tableSizeFor((int)var4);
         this.sizeCtl = var6;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void replaceAll(ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends V> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8.Node[] var2 = this.table;
         if (this.table != null) {
            ConcurrentHashMapV8.Traverser var3 = new ConcurrentHashMapV8.Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8.Node<K,V> var4;
            while ((var4 = var3.advance()) != null) {
               Object var5 = var4.val;
               Object var6 = var4.key;

               Object var7;
               do {
                  var7 = var1.apply((K)var6, (V)var5);
                  if (var7 == null) {
                     throw new NullPointerException();
                  }
               } while (this.replaceNode(var6, (V)var7, var5) == null && (var5 = this.get(var6)) != null);
            }
         }
      }
   }

   public <U> void forEachKey(long var1, ConcurrentHashMapV8.Fun<? super K, ? extends U> var3, ConcurrentHashMapV8.Action<? super U> var4) {
      if (var3 != null && var4 != null) {
         new ConcurrentHashMapV8.ForEachTransformedKeyTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U searchKeys(long var1, ConcurrentHashMapV8.Fun<? super K, ? extends U> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8.SearchKeysTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, new AtomicReference()).invoke();
      }
   }

   public <U> U searchValues(long var1, ConcurrentHashMapV8.Fun<? super V, ? extends U> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8.SearchValuesTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, new AtomicReference()).invoke();
      }
   }

   public void forEachEntry(long var1, ConcurrentHashMapV8.Action<? super Entry<K, V>> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         new ConcurrentHashMapV8.ForEachEntryTask<>(null, this.batchFor(var1), 0, 0, this.table, var3).invoke();
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

   public int reduceToInt(long var1, ConcurrentHashMapV8.ObjectByObjectToInt<? super K, ? super V> var3, int var4, ConcurrentHashMapV8.IntByIntToInt var5) {
      if (var3 != null && var5 != null) {
         return new ConcurrentHashMapV8.MapReduceMappingsToIntTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var5).invoke();
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

   public K reduceKeys(long var1, ConcurrentHashMapV8.BiFun<? super K, ? super K, ? extends K> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8.ReduceKeysTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3).invoke();
      }
   }

   public <U> U reduceKeys(long var1, ConcurrentHashMapV8.Fun<? super K, ? extends U> var3, ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> var4) {
      if (var3 != null && var4 != null) {
         return new ConcurrentHashMapV8.MapReduceKeysTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = 0;
      ConcurrentHashMapV8.Node[] var2 = this.table;
      if (this.table != null) {
         ConcurrentHashMapV8.Traverser var3 = new ConcurrentHashMapV8.Traverser(var2, var2.length, 0, var2.length);

         ConcurrentHashMapV8.Node<K,V> var4;
         while ((var4 = var3.advance()) != null) {
            var1 += var4.key.hashCode() ^ var4.val.hashCode();
         }
      }

      return var1;
   }

   public long mappingCount() {
      long var1 = this.sumCount();
      return var1 < 0L ? 0L : var1;
   }

   public int reduceEntriesToInt(long var1, ConcurrentHashMapV8.ObjectToInt<Entry<K, V>> var3, int var4, ConcurrentHashMapV8.IntByIntToInt var5) {
      if (var3 != null && var5 != null) {
         return new ConcurrentHashMapV8.MapReduceEntriesToIntTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var5).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U search(long var1, ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8.SearchMappingsTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, new AtomicReference()).invoke();
      }
   }

   @Override
   public V remove(Object var1) {
      return this.replaceNode(var1, null, null);
   }

   public void readObject(ObjectInputStream var1) throws java.io.IOException, java.lang.ClassNotFoundException {
      this.sizeCtl = -1;
      var1.defaultReadObject();
      long var2 = 0L;
      ConcurrentHashMapV8.Node<K,V> var4 = null;

      while (true) {
         Object var5 = var1.readObject();
         Object var6 = var1.readObject();
         if (var5 == null || var6 == null) {
            if (var2 == 0L) {
               this.sizeCtl = 0;
            } else {
               int var22;
               if (var2 >= 536870912L) {
                  var22 = 1073741824;
               } else {
                  int var23 = (int)var2;
                  var22 = tableSizeFor(var23 + (var23 >>> 1) + 1);
               }

               var6 = new ConcurrentHashMapV8.Node[var22];
               int var7 = var22 - 1;
               long var8 = 0L;

               while (var4 != null) {
                  ConcurrentHashMapV8.Node<K,V> var11 = var4.next;
                  int var13 = var4.hash;
                  int var14 = var13 & var7;
                  boolean var10;
                  ConcurrentHashMapV8.Node<K,V> var12;
                  if ((var12 = tabAt((ConcurrentHashMapV8.Node<K, V>[])var6, var14)) == null) {
                     var10 = true;
                  } else {
                     Object var15 = var4.key;
                     if (var12.hash < 0) {
                        ConcurrentHashMapV8.TreeBin var25 = (ConcurrentHashMapV8.TreeBin)var12;
                        if (var25.putTreeVal(var13, var15, var4.val) == null) {
                           var8++;
                        }

                        var10 = false;
                     } else {
                        int var16 = 0;
                        var10 = true;

                        for (ConcurrentHashMapV8.Node<K,V> var17 = var12; var17 != null; var17 = var17.next) {
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
                           var8++;
                           var4.next = var12;
                           ConcurrentHashMapV8.TreeNode var19 = null;
                           ConcurrentHashMapV8.TreeNode var20 = null;

                           for (ConcurrentHashMapV8.Node<K,V> var26 = var4; var26 != null; var26 = var26.next) {
                              ConcurrentHashMapV8.TreeNode var21 = new ConcurrentHashMapV8.TreeNode(var26.hash, var26.key, var26.val, null, null);
                              if ((var21.prev = var20) == null) {
                                 var19 = var21;
                              } else {
                                 var20.next = var21;
                              }

                              var20 = var21;
                           }

                           setTabAt((ConcurrentHashMapV8.Node<K, V>[])var6, var14, new ConcurrentHashMapV8.TreeBin<>(var19));
                        }
                     }
                  }

                  if (var10) {
                     var8++;
                     var4.next = var12;
                     setTabAt((ConcurrentHashMapV8.Node<K, V>[])var6, var14, var4);
                  }

                  var4 = var11;
               }

               this.table = (ConcurrentHashMapV8.Node<K, V>[])var6;
               this.sizeCtl = var22 - (var22 >>> 2);
               this.baseCount = var8;
            }

            return;
         }

         var4 = new ConcurrentHashMapV8.Node(spread(var5.hashCode()), var5, var6, var4);
         var2++;
      }
   }

   public double reduceKeysToDouble(long var1, ConcurrentHashMapV8.ObjectToDouble<? super K> var3, double var4, ConcurrentHashMapV8.DoubleByDoubleToDouble var6) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8.MapReduceKeysToDoubleTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U reduce(
      long var1, ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> var3, ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> var4
   ) {
      if (var3 != null && var4 != null) {
         return new ConcurrentHashMapV8.MapReduceMappingsTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public void clear() {
      long var1 = 0L;
      int var3 = 0;
      ConcurrentHashMapV8.Node[] var4 = this.table;

      while (var4 != null && var3 < var4.length) {
         ConcurrentHashMapV8.Node<K,V> var6 = tabAt(var4, var3);
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
                     for (Object var8 = var5 >= 0 ? var6 : (var6 instanceof ConcurrentHashMapV8.TreeBin ? ((ConcurrentHashMapV8.TreeBin)var6).first : null);
                        var8 != null;
                        var8 = ((ConcurrentHashMapV8.Node)var8).next
                     ) {
                        var1--;
                     }

                     setTabAt(var4, var3++, null);
                  }
               }
            }
         }
      }

      if (var1 != 0L) {
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
         ConcurrentHashMapV8.Node[] var3 = this.table;
         int var4 = this.table == null ? 0 : var3.length;
         ConcurrentHashMapV8.Traverser var5 = new ConcurrentHashMapV8.Traverser(var3, var4, 0, var4);

         ConcurrentHashMapV8.Node<K,V> var6;
         while ((var6 = var5.advance()) != null) {
            Object var7 = var6.val;
            Object var8 = var2.get(var6.key);
            if (var8 == null || var8 != var7 && !var8.equals(var7)) {
               return false;
            }
         }

         for (Entry var12 : (Iterable<Entry>)(Iterable<?>)(var2.entrySet())) {
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

   public Entry<K, V> reduceEntries(long var1, ConcurrentHashMapV8.BiFun<Entry<K, V>, Entry<K, V>, ? extends Entry<K, V>> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8.ReduceEntriesTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3).invoke();
      }
   }

   public static int compareComparables(Class<?> var0, Object var1, Object var2) {
      return var2 != null && var2.getClass() == var0 ? ((Comparable)var1).compareTo(var2) : 0;
   }

   public ConcurrentHashMapV8.KeySetView<K, V> keySet(V var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8.KeySetView<>(this, (V)var1);
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

   public static <K, V> ConcurrentHashMapV8.Node<K, V> tabAt(ConcurrentHashMapV8.Node<K, V>[] var0, int var1) {
      return (ConcurrentHashMapV8.Node<K, V>)U.getObjectVolatile(var0, ((long)var1 << ASHIFT) + ABASE);
   }

   public static <K, V> boolean casTabAt(
      ConcurrentHashMapV8.Node<K, V>[] var0, int var1, ConcurrentHashMapV8.Node<K, V> var2, ConcurrentHashMapV8.Node<K, V> var3
   ) {
      return U.compareAndSwapObject(var0, ((long)var1 << ASHIFT) + ABASE, var2, var3);
   }

   public V replaceNode(Object var1, V var2, Object var3) {
      int var4 = spread(var1.hashCode());
      ConcurrentHashMapV8.Node[] var5 = this.table;

      ConcurrentHashMapV8.Node<K,V> var6;
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
                     if (var6 instanceof ConcurrentHashMapV8.TreeBin) {
                        var11 = true;
                        ConcurrentHashMapV8.TreeBin var19 = (ConcurrentHashMapV8.TreeBin)var6;
                        ConcurrentHashMapV8.TreeNode var20 = var19.root;
                        ConcurrentHashMapV8.TreeNode var21;
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
                     ConcurrentHashMapV8.Node<K,V> var13 = var6;
                     ConcurrentHashMapV8.Node<K,V> var14 = null;

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
                     this.addCount(-1L, -1);
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
            return AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {

               public Unsafe run() throws java.lang.Exception {
                  Class<Unsafe> var1x = Unsafe.class;

                  for (Field var5 : var1x.getDeclaredFields()) {
                     var5.setAccessible(true);
                     Object var6 = var5.get(null);
                     if (var1x.isInstance(var6)) {
                        return var1x.cast(var6);
                     }
                  }

                  throw new NoSuchFieldError("the Unsafe");
               }
            });
         } catch (PrivilegedActionException var1) {
            throw new RuntimeException("Could not initialize intrinsics", var1.getCause());
         }
      }
   }

   @Override
   public int size() {
      long var1 = this.sumCount();
      return var1 < 0L ? 0 : (var1 > 2147483647L ? Integer.MAX_VALUE : (int)var1);
   }

   public void forEachKey(long var1, ConcurrentHashMapV8.Action<? super K> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         new ConcurrentHashMapV8.ForEachKeyTask<>(null, this.batchFor(var1), 0, 0, this.table, var3).invoke();
      }
   }

   @Override
   public String toString() {
      ConcurrentHashMapV8.Node[] var1 = this.table;
      int var2 = this.table == null ? 0 : var1.length;
      ConcurrentHashMapV8.Traverser var3 = new ConcurrentHashMapV8.Traverser(var1, var2, 0, var2);
      StringBuilder var4 = new StringBuilder();
      var4.append('{');
      ConcurrentHashMapV8.Node<K,V> var5;
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

   public long reduceEntriesToLong(long var1, ConcurrentHashMapV8.ObjectToLong<Entry<K, V>> var3, long var4, ConcurrentHashMapV8.LongByLongToLong var6) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8.MapReduceEntriesToLongTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public void forEach(ConcurrentHashMapV8.BiAction<? super K, ? super V> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ConcurrentHashMapV8.Node[] var2 = this.table;
         if (this.table != null) {
            ConcurrentHashMapV8.Traverser var3 = new ConcurrentHashMapV8.Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8.Node<K,V> var4;
            while ((var4 = var3.advance()) != null) {
               var1.apply(var4.key, var4.val);
            }
         }
      }
   }

   public void transfer(ConcurrentHashMapV8.Node<K, V>[] var1, ConcurrentHashMapV8.Node<K, V>[] var2) {
      int var3 = var1.length;
      int var4;
      if ((var4 = NCPU > 1 ? (var3 >>> 3) / NCPU : var3) < 16) {
         var4 = 16;
      }

      if (var2 == null) {
         try {
            ConcurrentHashMapV8.Node[] var5 = new ConcurrentHashMapV8.Node[var3 << 1];
            var2 = var5;
         } catch (Throwable var29) {
            this.sizeCtl = Integer.MAX_VALUE;
            return;
         }

         this.nextTable = var2;
         this.transferOrigin = var3;
         this.transferIndex = var3;
         ConcurrentHashMapV8.ForwardingNode var31 = new ConcurrentHashMapV8.ForwardingNode(var1);
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
      ConcurrentHashMapV8.ForwardingNode var33 = new ConcurrentHashMapV8.ForwardingNode(var2);
      boolean var34 = true;
      boolean var36 = false;
      int var9 = 0;
      int var10 = 0;

      while (true) {
         while (!var34) {
            if (var9 >= 0 && var9 < var3 && var9 + var3 < var32) {
               ConcurrentHashMapV8.Node<K,V> var14;
               if ((var14 = tabAt(var1, var9)) != null) {
                  int var13 = var14.hash;
                  if (var14.hash == -1) {
                     var34 = true;
                  } else {
                     synchronized (var14) {
                        if (tabAt(var1, var9) == var14) {
                           if (var13 >= 0) {
                              int var18 = var13 & var3;
                              ConcurrentHashMapV8.Node<K,V> var19 = var14;

                              for (ConcurrentHashMapV8.Node<K,V> var20 = var14.next; var20 != null; var20 = var20.next) {
                                 int var21 = var20.hash & var3;
                                 if (var21 != var18) {
                                    var18 = var21;
                                    var19 = var20;
                                 }
                              }

                              ConcurrentHashMapV8.Node<K,V> var16;
                              ConcurrentHashMapV8.Node<K,V> var17;
                              if (var18 == 0) {
                                 var16 = var19;
                                 var17 = null;
                              } else {
                                 var17 = var19;
                                 var16 = null;
                              }

                              for (ConcurrentHashMapV8.Node<K,V> var42 = var14; var42 != var19; var42 = var42.next) {
                                 int var44 = var42.hash;
                                 Object var22 = var42.key;
                                 Object var23 = var42.val;
                                 if ((var44 & var3) == 0) {
                                    var16 = new ConcurrentHashMapV8.Node(var44, var22, var23, var16);
                                 } else {
                                    var17 = new ConcurrentHashMapV8.Node(var44, var22, var23, var17);
                                 }
                              }

                              setTabAt(var2, var9, var16);
                              setTabAt(var2, var9 + var3, var17);
                              setTabAt(var1, var9, var33);
                              var34 = true;
                           } else if (var14 instanceof ConcurrentHashMapV8.TreeBin) {
                              ConcurrentHashMapV8.TreeBin var40 = (ConcurrentHashMapV8.TreeBin)var14;
                              ConcurrentHashMapV8.TreeNode var41 = null;
                              ConcurrentHashMapV8.TreeNode var43 = null;
                              ConcurrentHashMapV8.TreeNode var45 = null;
                              ConcurrentHashMapV8.TreeNode var46 = null;
                              int var47 = 0;
                              int var24 = 0;

                              for (Object var25 = var40.first; var25 != null; var25 = ((ConcurrentHashMapV8.Node)var25).next) {
                                 int var26 = ((ConcurrentHashMapV8.Node)var25).hash;
                                 ConcurrentHashMapV8.TreeNode var27 = new ConcurrentHashMapV8.TreeNode(
                                    var26, ((ConcurrentHashMapV8.Node)var25).key, ((ConcurrentHashMapV8.Node)var25).val, null, null
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

                              Object var38 = var47 <= 6 ? untreeify(var41) : (var24 != 0 ? new ConcurrentHashMapV8.TreeBin(var41) : var40);
                              Object var39 = var24 <= 6 ? untreeify(var45) : (var47 != 0 ? new ConcurrentHashMapV8.TreeBin(var45) : var40);
                              setTabAt(var2, var9, (ConcurrentHashMapV8.Node<K, V>)var38);
                              setTabAt(var2, var9 + var3, (ConcurrentHashMapV8.Node<K, V>)var39);
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
      if (var1 != Long.MAX_VALUE && (var3 = this.sumCount()) > 1L && var3 >= var1) {
         int var5 = ForkJoinPool.getCommonPoolParallelism() << 2;
         long var6;
         return var1 > 0L && (var6 = var3 / var1) < var5 ? (int)var6 : var5;
      } else {
         return 0;
      }
   }

   public <U> U reduceEntries(
      long var1, ConcurrentHashMapV8.Fun<Entry<K, V>, ? extends U> var3, ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> var4
   ) {
      if (var3 != null && var4 != null) {
         return new ConcurrentHashMapV8.MapReduceEntriesTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public ConcurrentHashMapV8.Node<K, V>[] helpTransfer(ConcurrentHashMapV8.Node<K, V>[] var1, ConcurrentHashMapV8.Node<K, V> var2) {
      if (var2 instanceof ConcurrentHashMapV8.ForwardingNode) {
         ConcurrentHashMapV8.Node[] var3 = ((ConcurrentHashMapV8.ForwardingNode)var2).nextTable;
         if (((ConcurrentHashMapV8.ForwardingNode)var2).nextTable != null) {
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

   public ConcurrentHashMapV8.Node<K, V>[] initTable() {
      ConcurrentHashMapV8.Node[] var1;
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
                  ConcurrentHashMapV8.Node[] var4 = new ConcurrentHashMapV8.Node[var3];
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

   public <U> void forEachEntry(long var1, ConcurrentHashMapV8.Fun<Entry<K, V>, ? extends U> var3, ConcurrentHashMapV8.Action<? super U> var4) {
      if (var3 != null && var4 != null) {
         new ConcurrentHashMapV8.ForEachTransformedEntryTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U reduceValues(long var1, ConcurrentHashMapV8.Fun<? super V, ? extends U> var3, ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> var4) {
      if (var3 != null && var4 != null) {
         return new ConcurrentHashMapV8.MapReduceValuesTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> void forEachValue(long var1, ConcurrentHashMapV8.Fun<? super V, ? extends U> var3, ConcurrentHashMapV8.Action<? super U> var4) {
      if (var3 != null && var4 != null) {
         new ConcurrentHashMapV8.ForEachTransformedValueTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, var4).invoke();
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

   public void forEach(long var1, ConcurrentHashMapV8.BiAction<? super K, ? super V> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         new ConcurrentHashMapV8.ForEachMappingTask<>(null, this.batchFor(var1), 0, 0, this.table, var3).invoke();
      }
   }

   public long reduceKeysToLong(long var1, ConcurrentHashMapV8.ObjectToLong<? super K> var3, long var4, ConcurrentHashMapV8.LongByLongToLong var6) {
      if (var3 != null && var6 != null) {
         return new ConcurrentHashMapV8.MapReduceKeysToLongTask<>(null, this.batchFor(var1), 0, 0, this.table, null, var3, var4, var6).invoke();
      } else {
         throw new NullPointerException();
      }
   }

   public <U> U searchEntries(long var1, ConcurrentHashMapV8.Fun<Entry<K, V>, ? extends U> var3) {
      if (var3 == null) {
         throw new NullPointerException();
      } else {
         return new ConcurrentHashMapV8.SearchEntriesTask<K, V, U>(null, this.batchFor(var1), 0, 0, this.table, var3, new AtomicReference()).invoke();
      }
   }

   public interface Action<A> {
      void apply(A var1);
   }

   public static class BaseIterator<K, V> extends ConcurrentHashMapV8.Traverser<K, V> {
      public ConcurrentHashMapV8.Node<K, V> lastReturned;
      public ConcurrentHashMapV8<K, V> map;

      public boolean hasMoreElements() {
         return this.next != null;
      }

      public boolean hasNext() {
         return this.next != null;
      }

      public void remove() {
         ConcurrentHashMapV8.Node<K,V> var1 = this.lastReturned;
         if (this.lastReturned == null) {
            throw new IllegalStateException();
         } else {
            this.lastReturned = null;
            this.map.replaceNode(var1.key, null, null);
         }
      }

      public BaseIterator(ConcurrentHashMapV8.Node<K, V>[] var1, int var2, int var3, int var4, ConcurrentHashMapV8<K, V> var5) {
         super(var1, var2, var3, var4);
         this.map = var5;
         this.advance();
      }
   }

   public interface BiAction<A, B> {
      void apply(A var1, B var2);
   }

   public interface BiFun<A, B, T> {
      T apply(A var1, B var2);
   }

   public abstract static class BulkTask<K, V, R> extends CountedCompleter<R> {
      public ConcurrentHashMapV8.Node<K, V> next;
      public int batch;
      public int baseSize;
      public int index;
      public ConcurrentHashMapV8.Node<K, V>[] tab;
      public int baseIndex;
      public int baseLimit;

      public BulkTask(ConcurrentHashMapV8.BulkTask<K, V, ?> var1, int var2, int var3, int var4, ConcurrentHashMapV8.Node<K, V>[] var5) {
         super(var1);
         this.batch = var2;
         this.index = this.baseIndex = var3;
         if ((this.tab = var5) == null) {
            this.baseSize = this.baseLimit = 0;
         } else if (var1 == null) {
            this.baseSize = this.baseLimit = var5.length;
         } else {
            this.baseLimit = var4;
            this.baseSize = var1.baseSize;
         }
      }

      public ConcurrentHashMapV8.Node<K, V> advance() {
         Object var1 = this.next;
         if (this.next != null) {
            var1 = ((ConcurrentHashMapV8.Node)var1).next;
         }

         while (true) {
            if (var1 != null) {
               return this.next = (ConcurrentHashMapV8.Node<K, V>)var1;
            }

            if (this.baseIndex >= this.baseLimit) {
               break;
            }

            ConcurrentHashMapV8.Node[] var2 = this.tab;
            if (this.tab == null) {
               break;
            }

            int var4;
            int var10000 = var4 = var2.length;
            int var3 = this.index;
            if (var10000 <= this.index || var3 < 0) {
               break;
            }

            if ((var1 = ConcurrentHashMapV8.tabAt(var2, this.index)) != null && ((ConcurrentHashMapV8.Node)var1).hash < 0) {
               if (var1 instanceof ConcurrentHashMapV8.ForwardingNode) {
                  this.tab = ((ConcurrentHashMapV8.ForwardingNode)var1).nextTable;
                  var1 = null;
                  continue;
               }

               if (var1 instanceof ConcurrentHashMapV8.TreeBin) {
                  var1 = ((ConcurrentHashMapV8.TreeBin)var1).first;
               } else {
                  var1 = null;
               }
            }

            if ((this.index = this.index + this.baseSize) >= var4) {
               this.index = ++this.baseIndex;
            }
         }

         return this.next = null;
      }
   }

   public abstract static class CollectionView<K, V, E> implements Serializable, Collection<E> {
      public static final long serialVersionUID = 7249069246763182397L;
      public static final String oomeMsg = "Required array size too large";
      public ConcurrentHashMapV8<K, V> map;

      @Override
      public boolean containsAll(Collection<?> var1) {
         if (var1 != this) {
            for (Object var3 : var1) {
               if (var3 == null || !this.contains(var3)) {
                  return false;
               }
            }
         }

         return true;
      }

      @Override
      public int size() {
         return this.map.size();
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append('[');
         Iterator var2 = this.iterator();
         if (var2.hasNext()) {
            while (true) {
               Object var3 = var2.next();
               var1.append(var3 == this ? "(this Collection)" : var3);
               if (!var2.hasNext()) {
                  break;
               }

               var1.append(',').append(' ');
            }
         }

         return var1.append(']').toString();
      }

      public ConcurrentHashMapV8<K, V> getMap() {
         return this.map;
      }

      @Override
      public void clear() {
         this.map.clear();
      }

      public CollectionView(ConcurrentHashMapV8<K, V> var1) {
         this.map = var1;
      }

      @Override
      public Object[] toArray() {
         long var1 = this.map.mappingCount();
         if (var1 > 2147483639L) {
            throw new OutOfMemoryError("Required array size too large");
         } else {
            int var3 = (int)var1;
            Object[] var4 = new Object[var3];
            int var5 = 0;

            for (Object var7 : this) {
               if (var5 == var3) {
                  if (var3 >= 2147483639) {
                     throw new OutOfMemoryError("Required array size too large");
                  }

                  if (var3 >= 1073741819) {
                     var3 = 2147483639;
                  } else {
                     var3 += (var3 >>> 1) + 1;
                  }

                  var4 = Arrays.copyOf(var4, var3);
               }

               var4[var5++] = var7;
            }

            return var5 == var3 ? var4 : Arrays.copyOf(var4, var5);
         }
      }

      @Override
      public <T> T[] toArray(T[] var1) {
         long var2 = this.map.mappingCount();
         if (var2 > 2147483639L) {
            throw new OutOfMemoryError("Required array size too large");
         } else {
            int var4 = (int)var2;
            Object[] var5 = var1.length >= var4 ? var1 : (Object[])Array.newInstance(var1.getClass().getComponentType(), var4);
            int var6 = var5.length;
            int var7 = 0;

            for (Object var9 : this) {
               if (var7 == var6) {
                  if (var6 >= 2147483639) {
                     throw new OutOfMemoryError("Required array size too large");
                  }

                  if (var6 >= 1073741819) {
                     var6 = 2147483639;
                  } else {
                     var6 += (var6 >>> 1) + 1;
                  }

                  var5 = Arrays.copyOf(var5, var6);
               }

               var5[var7++] = var9;
            }

            if (var1 == var5 && var7 < var6) {
               var5[var7] = null;
               return (T[])var5;
            } else {
               return (T[])(var7 == var6 ? var5 : Arrays.copyOf(var5, var7));
            }
         }
      }

      @Override
      public boolean retainAll(Collection<?> var1) {
         boolean var2 = false;
         Iterator var3 = this.iterator();

         while (var3.hasNext()) {
            if (!var1.contains(var3.next())) {
               var3.remove();
               var2 = true;
            }
         }

         return var2;
      }

      @Override
      public boolean isEmpty() {
         return this.map.isEmpty();
      }

      @Override
      public abstract Iterator<E> iterator();

      @Override
      public abstract boolean contains(Object var1);

      @Override
      public abstract boolean remove(Object var1);

      @Override
      public boolean removeAll(Collection<?> var1) {
         boolean var2 = false;
         Iterator var3 = this.iterator();

         while (var3.hasNext()) {
            if (var1.contains(var3.next())) {
               var3.remove();
               var2 = true;
            }
         }

         return var2;
      }
   }

   public interface ConcurrentHashMapSpliterator<T> {
      boolean tryAdvance(ConcurrentHashMapV8.Action<? super T> var1);

      long estimateSize();

      ConcurrentHashMapV8.ConcurrentHashMapSpliterator<T> trySplit();

      void forEachRemaining(ConcurrentHashMapV8.Action<? super T> var1);
   }

   public static final class CounterCell {
      public volatile long p1;
      public volatile long p3;
      public volatile long p0;
      public volatile long p6;
      public volatile long value;
      public volatile long p5;
      public volatile long q0;
      public volatile long q3;
      public volatile long q4;
      public volatile long q5;
      public volatile long q2;
      public volatile long p2;
      public volatile long q1;
      public volatile long q6;
      public volatile long p4;

      public CounterCell(long var1) {
         this.value = var1;
      }
   }

   public static final class CounterHashCode {
      public int code;
   }

   public interface DoubleByDoubleToDouble {
      double apply(double var1, double var3);
   }

   public static final class EntryIterator<K, V> extends ConcurrentHashMapV8.BaseIterator<K, V> implements Iterator<Entry<K, V>> {

      public EntryIterator(ConcurrentHashMapV8.Node<K, V>[] var1, int var2, int var3, int var4, ConcurrentHashMapV8<K, V> var5) {
         super(var1, var2, var3, var4, var5);
      }

      public Entry<K, V> next() {
         ConcurrentHashMapV8.Node<K,V> var1 = this.next;
         if (this.next == null) {
            throw new NoSuchElementException();
         } else {
            Object var2 = var1.key;
            Object var3 = var1.val;
            this.lastReturned = var1;
            this.advance();
            return new ConcurrentHashMapV8.MapEntry<>((K)var2, (V)var3, this.map);
         }
      }
   }

   public static final class EntrySetView<K, V> extends ConcurrentHashMapV8.CollectionView<K, V, Entry<K, V>> implements Serializable, Set<Entry<K, V>> {
      public static final long serialVersionUID = 2249069246763182397L;

      public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<Entry<K, V>> spliterator166() {
         ConcurrentHashMapV8 var2 = this.map;
         long var3 = var2.sumCount();
         ConcurrentHashMapV8.Node[] var1 = var2.table;
         int var5 = var2.table == null ? 0 : var1.length;
         return new ConcurrentHashMapV8.EntrySpliterator<>(var1, var5, 0, var5, var3 < 0L ? 0L : var3, var2);
      }

      public EntrySetView(ConcurrentHashMapV8<K, V> var1) {
         super(var1);
      }

      @Override
      public boolean contains(Object var1) {
         Object var2;
         Object var3;
         Object var4;
         Entry var5;
         return var1 instanceof Entry
            && (var2 = (var5 = (Entry)var1).getKey()) != null
            && (var4 = this.map.get(var2)) != null
            && (var3 = var5.getValue()) != null
            && (var3 == var4 || var3.equals(var4));
      }

      @Override
      public boolean remove(Object var1) {
         Object var2;
         Object var3;
         Entry var4;
         return var1 instanceof Entry && (var2 = (var4 = (Entry)var1).getKey()) != null && (var3 = var4.getValue()) != null && this.map.remove(var2, var3);
      }

      public void forEach(ConcurrentHashMapV8.Action<? super Entry<K, V>> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            ConcurrentHashMapV8.Node[] var2 = this.map.table;
            if (this.map.table != null) {
               ConcurrentHashMapV8.Traverser var3 = new ConcurrentHashMapV8.Traverser(var2, var2.length, 0, var2.length);

               ConcurrentHashMapV8.Node<K,V> var4;
               while ((var4 = var3.advance()) != null) {
                  var1.apply(new ConcurrentHashMapV8.MapEntry<>(var4.key, var4.val, this.map));
               }
            }
         }
      }

      @Override
      public int hashCode() {
         int var1 = 0;
         ConcurrentHashMapV8.Node[] var2 = this.map.table;
         if (this.map.table != null) {
            ConcurrentHashMapV8.Traverser var3 = new ConcurrentHashMapV8.Traverser(var2, var2.length, 0, var2.length);

            ConcurrentHashMapV8.Node<K,V> var4;
            while ((var4 = var3.advance()) != null) {
               var1 += var4.hashCode();
            }
         }

         return var1;
      }

      @Override
      public boolean equals(Object var1) {
         Set var2;
         return var1 instanceof Set && ((var2 = (Set)var1) == this || this.containsAll(var2) && var2.containsAll(this));
      }

      @Override
      public boolean addAll(Collection<? extends Entry<K, V>> var1) {
         boolean var2 = false;

         for (Entry var4 : var1) {
            if (this.add(var4)) {
               var2 = true;
            }
         }

         return var2;
      }

      @Override
      public Iterator<Entry<K, V>> iterator() {
         ConcurrentHashMapV8 var1 = this.map;
         ConcurrentHashMapV8.Node[] var2 = var1.table;
         int var3 = var1.table == null ? 0 : var2.length;
         return new ConcurrentHashMapV8.EntryIterator<>(var2, var3, 0, var3, var1);
      }

      public boolean add(Entry<K, V> var1) {
         return this.map.putVal((K)var1.getKey(), (V)var1.getValue(), false) == null;
      }
   }

   public static final class EntrySpliterator<K, V>
      extends ConcurrentHashMapV8.Traverser<K, V>
      implements ConcurrentHashMapV8.ConcurrentHashMapSpliterator<Entry<K, V>> {
      public long est;
      public ConcurrentHashMapV8<K, V> map;

      @Override
      public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<Entry<K, V>> trySplit() {
         int var1 = this.baseIndex;
         int var2 = this.baseLimit;
         int var3;
         return (var3 = this.baseIndex + this.baseLimit >>> 1) <= var1
            ? null
            : new ConcurrentHashMapV8.EntrySpliterator<>(this.tab, this.baseSize, this.baseLimit = var3, var2, this.est >>>= 1, this.map);
      }

      @Override
      public long estimateSize() {
         return this.est;
      }

      @Override
      public void forEachRemaining(ConcurrentHashMapV8.Action<? super Entry<K, V>> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            ConcurrentHashMapV8.Node<K,V> var2;
            while ((var2 = this.advance()) != null) {
               var1.apply(new ConcurrentHashMapV8.MapEntry<>(var2.key, var2.val, this.map));
            }
         }
      }

      @Override
      public boolean tryAdvance(ConcurrentHashMapV8.Action<? super Entry<K, V>> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            ConcurrentHashMapV8.Node<K,V> var2;
            if ((var2 = this.advance()) == null) {
               return false;
            } else {
               var1.apply(new ConcurrentHashMapV8.MapEntry<>(var2.key, var2.val, this.map));
               return true;
            }
         }
      }

      public EntrySpliterator(ConcurrentHashMapV8.Node<K, V>[] var1, int var2, int var3, int var4, long var5, ConcurrentHashMapV8<K, V> var7) {
         super(var1, var2, var3, var4);
         this.map = var7;
         this.est = var5;
      }
   }

   public static final class ForEachEntryTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Void> {
      public ConcurrentHashMapV8.Action<? super Entry<K, V>> action;

      public ForEachEntryTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.Action<? super Entry<K, V>> var6
      ) {
         super(var1, var2, var3, var4, var5);
         this.action = var6;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.Action var1 = this.action;
         if (this.action != null) {
            int var2 = this.baseIndex;

            while (this.batch > 0) {
               int var3 = this.baseLimit;
               int var4;
               if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
                  break;
               }

               this.addToPendingCount(1);
               new ConcurrentHashMapV8.ForEachEntryTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, var1).fork();
            }

            ConcurrentHashMapV8.Node<K,V> var5;

            while ((var5 = this.advance()) != null) {
               var1.apply(var5);
            }

            this.propagateCompletion();
         }
      }
   }

   public static final class ForEachKeyTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Void> {
      public ConcurrentHashMapV8.Action<? super K> action;

      @Override
      public void compute() {
         ConcurrentHashMapV8.Action var1 = this.action;
         if (this.action != null) {
            int var2 = this.baseIndex;

            while (this.batch > 0) {
               int var3 = this.baseLimit;
               int var4;
               if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
                  break;
               }

               this.addToPendingCount(1);
               new ConcurrentHashMapV8.ForEachKeyTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, var1).fork();
            }

            ConcurrentHashMapV8.Node<K,V> var5;

            while ((var5 = this.advance()) != null) {
               var1.apply(var5.key);
            }

            this.propagateCompletion();
         }
      }

      public ForEachKeyTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.Action<? super K> var6
      ) {
         super(var1, var2, var3, var4, var5);
         this.action = var6;
      }
   }

   public static final class ForEachMappingTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Void> {
      public ConcurrentHashMapV8.BiAction<? super K, ? super V> action;

      @Override
      public void compute() {
         ConcurrentHashMapV8.BiAction var1 = this.action;
         if (this.action != null) {
            int var2 = this.baseIndex;

            while (this.batch > 0) {
               int var3 = this.baseLimit;
               int var4;
               if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
                  break;
               }

               this.addToPendingCount(1);
               new ConcurrentHashMapV8.ForEachMappingTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, var1).fork();
            }

            ConcurrentHashMapV8.Node<K,V> var5;

            while ((var5 = this.advance()) != null) {
               var1.apply(var5.key, var5.val);
            }

            this.propagateCompletion();
         }
      }

      public ForEachMappingTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.BiAction<? super K, ? super V> var6
      ) {
         super(var1, var2, var3, var4, var5);
         this.action = var6;
      }
   }

   public static final class ForEachTransformedEntryTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, Void> {
      public ConcurrentHashMapV8.Action<? super U> action;
      public ConcurrentHashMapV8.Fun<Entry<K, V>, ? extends U> transformer;

      @Override
      public void compute() {
         ConcurrentHashMapV8.Fun var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.Action var2 = this.action;
            if (this.action != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  this.addToPendingCount(1);
                  new ConcurrentHashMapV8.ForEachTransformedEntryTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
               }

               ConcurrentHashMapV8.Node<K,V> var6;

               while ((var6 = this.advance()) != null) {
                  Object var7;
                  if ((var7 = var1.apply(var6)) != null) {
                     var2.apply(var7);
                  }
               }

               this.propagateCompletion();
            }
         }
      }

      public ForEachTransformedEntryTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.Fun<Entry<K, V>, ? extends U> var6,
         ConcurrentHashMapV8.Action<? super U> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.transformer = var6;
         this.action = var7;
      }
   }

   public static final class ForEachTransformedKeyTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, Void> {
      public ConcurrentHashMapV8.Action<? super U> action;
      public ConcurrentHashMapV8.Fun<? super K, ? extends U> transformer;

      public ForEachTransformedKeyTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.Fun<? super K, ? extends U> var6,
         ConcurrentHashMapV8.Action<? super U> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.transformer = var6;
         this.action = var7;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.Fun var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.Action var2 = this.action;
            if (this.action != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  this.addToPendingCount(1);
                  new ConcurrentHashMapV8.ForEachTransformedKeyTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
               }

               ConcurrentHashMapV8.Node<K,V> var6;

               while ((var6 = this.advance()) != null) {
                  Object var7;
                  if ((var7 = var1.apply(var6.key)) != null) {
                     var2.apply(var7);
                  }
               }

               this.propagateCompletion();
            }
         }
      }
   }

   public static final class ForEachTransformedMappingTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, Void> {
      public ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> transformer;
      public ConcurrentHashMapV8.Action<? super U> action;

      public ForEachTransformedMappingTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> var6,
         ConcurrentHashMapV8.Action<? super U> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.transformer = var6;
         this.action = var7;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.BiFun var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.Action var2 = this.action;
            if (this.action != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  this.addToPendingCount(1);
                  new ConcurrentHashMapV8.ForEachTransformedMappingTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
               }

               ConcurrentHashMapV8.Node<K,V> var6;

               while ((var6 = this.advance()) != null) {
                  Object var7;
                  if ((var7 = var1.apply(var6.key, var6.val)) != null) {
                     var2.apply(var7);
                  }
               }

               this.propagateCompletion();
            }
         }
      }
   }

   public static final class ForEachTransformedValueTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, Void> {
      public ConcurrentHashMapV8.Action<? super U> action;
      public ConcurrentHashMapV8.Fun<? super V, ? extends U> transformer;

      @Override
      public void compute() {
         ConcurrentHashMapV8.Fun var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.Action var2 = this.action;
            if (this.action != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  this.addToPendingCount(1);
                  new ConcurrentHashMapV8.ForEachTransformedValueTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
               }

               ConcurrentHashMapV8.Node<K,V> var6;

               while ((var6 = this.advance()) != null) {
                  Object var7;
                  if ((var7 = var1.apply(var6.val)) != null) {
                     var2.apply(var7);
                  }
               }

               this.propagateCompletion();
            }
         }
      }

      public ForEachTransformedValueTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.Fun<? super V, ? extends U> var6,
         ConcurrentHashMapV8.Action<? super U> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.transformer = var6;
         this.action = var7;
      }
   }

   public static final class ForEachValueTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Void> {
      public ConcurrentHashMapV8.Action<? super V> action;

      public ForEachValueTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.Action<? super V> var6
      ) {
         super(var1, var2, var3, var4, var5);
         this.action = var6;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.Action var1 = this.action;
         if (this.action != null) {
            int var2 = this.baseIndex;

            while (this.batch > 0) {
               int var3 = this.baseLimit;
               int var4;
               if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
                  break;
               }

               this.addToPendingCount(1);
               new ConcurrentHashMapV8.ForEachValueTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, var1).fork();
            }

            ConcurrentHashMapV8.Node<K,V> var5;

            while ((var5 = this.advance()) != null) {
               var1.apply(var5.val);
            }

            this.propagateCompletion();
         }
      }
   }

   public static final class ForwardingNode<K, V> extends ConcurrentHashMapV8.Node<K, V> {
      public ConcurrentHashMapV8.Node<K, V>[] nextTable;

      @Override
      public ConcurrentHashMapV8.Node<K, V> find(int var1, Object var2) {
         ConcurrentHashMapV8.Node[] var3 = this.nextTable;

         ConcurrentHashMapV8.Node<K,V> var4;
         int var5;
         label41:
         while (var2 != null && var3 != null && (var5 = var3.length) != 0 && (var4 = ConcurrentHashMapV8.tabAt(var3, var5 - 1 & var1)) != null) {
            do {
               int var6 = var4.hash;
               if (var4.hash == var1) {
                  Object var7 = var4.key;
                  if (var4.key == var2 || var7 != null && var2.equals(var7)) {
                     return var4;
                  }
               }

               if (var6 < 0) {
                  if (!(var4 instanceof ConcurrentHashMapV8.ForwardingNode)) {
                     return var4.find(var1, var2);
                  }

                  var3 = ((ConcurrentHashMapV8.ForwardingNode)var4).nextTable;
                  continue label41;
               }
            } while ((var4 = var4.next) != null);

            return null;
         }

         return null;
      }

      public ForwardingNode(ConcurrentHashMapV8.Node<K, V>[] var1) {
         super(-1, null, null, null);
         this.nextTable = var1;
      }
   }

   public interface Fun<A, T> {
      T apply(A var1);
   }

   public interface IntByIntToInt {
      int apply(int var1, int var2);
   }

   public static final class KeyIterator<K, V> extends ConcurrentHashMapV8.BaseIterator<K, V> implements Enumeration<K>, Iterator<K> {

      @Override
      public K nextElement() {
         return this.next();
      }

      @Override
      public K next() {
         ConcurrentHashMapV8.Node<K,V> var1 = this.next;
         if (this.next == null) {
            throw new NoSuchElementException();
         } else {
            Object var2 = var1.key;
            this.lastReturned = var1;
            this.advance();
            return (K)var2;
         }
      }

      public KeyIterator(ConcurrentHashMapV8.Node<K, V>[] var1, int var2, int var3, int var4, ConcurrentHashMapV8<K, V> var5) {
         super(var1, var2, var3, var4, var5);
      }
   }

   public static class KeySetView<K, V> extends ConcurrentHashMapV8.CollectionView<K, V, K> implements Serializable, Set<K> {
      public static final long serialVersionUID = 7249069246763182397L;
      public V value;

      @Override
      public boolean addAll(Collection<? extends K> var1) {
         boolean var2 = false;
         Object var3 = this.value;
         if (this.value == null) {
            throw new UnsupportedOperationException();
         } else {
            for (Object var5 : var1) {
               if (this.map.putVal((K)var5, (V)var3, true) == null) {
                  var2 = true;
               }
            }

            return var2;
         }
      }

      @Override
      public boolean remove(Object var1) {
         return this.map.remove(var1) != null;
      }

      @Override
      public boolean equals(Object var1) {
         Set var2;
         return var1 instanceof Set && ((var2 = (Set)var1) == this || this.containsAll(var2) && var2.containsAll(this));
      }

      @Override
      public boolean add(K var1) {
         Object var2 = this.value;
         if (this.value == null) {
            throw new UnsupportedOperationException();
         } else {
            return this.map.putVal((K)var1, (V)var2, true) == null;
         }
      }

      @Override
      public boolean contains(Object var1) {
         return this.map.containsKey(var1);
      }

      public V getMappedValue() {
         return this.value;
      }

      public KeySetView(ConcurrentHashMapV8<K, V> var1, V var2) {
         super(var1);
         this.value = (V)var2;
      }

      public void forEach(ConcurrentHashMapV8.Action<? super K> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            ConcurrentHashMapV8.Node[] var2 = this.map.table;
            if (this.map.table != null) {
               ConcurrentHashMapV8.Traverser var3 = new ConcurrentHashMapV8.Traverser(var2, var2.length, 0, var2.length);

               ConcurrentHashMapV8.Node<K,V> var4;
               while ((var4 = var3.advance()) != null) {
                  var1.apply(var4.key);
               }
            }
         }
      }

      @Override
      public int hashCode() {
         int var1 = 0;

         for (Object var3 : this) {
            var1 += var3.hashCode();
         }

         return var1;
      }

      public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<K> spliterator166() {
         ConcurrentHashMapV8 var2 = this.map;
         long var3 = var2.sumCount();
         ConcurrentHashMapV8.Node[] var1 = var2.table;
         int var5 = var2.table == null ? 0 : var1.length;
         return new ConcurrentHashMapV8.KeySpliterator<>(var1, var5, 0, var5, var3 < 0L ? 0L : var3);
      }

      @Override
      public Iterator<K> iterator() {
         ConcurrentHashMapV8 var2 = this.map;
         ConcurrentHashMapV8.Node[] var1 = var2.table;
         int var3 = var2.table == null ? 0 : var1.length;
         return new ConcurrentHashMapV8.KeyIterator<>(var1, var3, 0, var3, var2);
      }
   }

   public static final class KeySpliterator<K, V> extends ConcurrentHashMapV8.Traverser<K, V> implements ConcurrentHashMapV8.ConcurrentHashMapSpliterator<K> {
      public long est;

      @Override
      public boolean tryAdvance(ConcurrentHashMapV8.Action<? super K> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            ConcurrentHashMapV8.Node<K,V> var2;
            if ((var2 = this.advance()) == null) {
               return false;
            } else {
               var1.apply(var2.key);
               return true;
            }
         }
      }

      public KeySpliterator(ConcurrentHashMapV8.Node<K, V>[] var1, int var2, int var3, int var4, long var5) {
         super(var1, var2, var3, var4);
         this.est = var5;
      }

      @Override
      public void forEachRemaining(ConcurrentHashMapV8.Action<? super K> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            ConcurrentHashMapV8.Node<K,V> var2;
            while ((var2 = this.advance()) != null) {
               var1.apply(var2.key);
            }
         }
      }

      @Override
      public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<K> trySplit() {
         int var1 = this.baseIndex;
         int var2 = this.baseLimit;
         int var3;
         return (var3 = this.baseIndex + this.baseLimit >>> 1) <= var1
            ? null
            : new ConcurrentHashMapV8.KeySpliterator<>(this.tab, this.baseSize, this.baseLimit = var3, var2, this.est >>>= 1);
      }

      @Override
      public long estimateSize() {
         return this.est;
      }
   }

   public interface LongByLongToLong {
      long apply(long var1, long var3);
   }

   public static final class MapEntry<K, V> implements Entry<K, V> {
      public ConcurrentHashMapV8<K, V> map;
      public V val;
      public K key;

      @Override
      public boolean equals(Object var1) {
         Object var2;
         Object var3;
         Entry var4;
         return var1 instanceof Entry
            && (var2 = (var4 = (Entry)var1).getKey()) != null
            && (var3 = var4.getValue()) != null
            && (var2 == this.key || var2.equals(this.key))
            && (var3 == this.val || var3.equals(this.val));
      }

      @Override
      public String toString() {
         return this.key + "=" + this.val;
      }

      @Override
      public V getValue() {
         return this.val;
      }

      public MapEntry(K var1, V var2, ConcurrentHashMapV8<K, V> var3) {
         this.key = (K)var1;
         this.val = (V)var2;
         this.map = var3;
      }

      @Override
      public V setValue(V var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            Object var2 = this.val;
            this.val = (V)var1;
            this.map.put(this.key, (V)var1);
            return (V)var2;
         }
      }

      @Override
      public K getKey() {
         return this.key;
      }

      @Override
      public int hashCode() {
         return this.key.hashCode() ^ this.val.hashCode();
      }
   }

   public static final class MapReduceEntriesTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, U> {
      public ConcurrentHashMapV8.MapReduceEntriesTask<K, V, U> rights;
      public ConcurrentHashMapV8.Fun<Entry<K, V>, ? extends U> transformer;
      public U result;
      public ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
      public ConcurrentHashMapV8.MapReduceEntriesTask<K, V, U> nextRight;

      public MapReduceEntriesTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceEntriesTask<K, V, U> var6,
         ConcurrentHashMapV8.Fun<Entry<K, V>, ? extends U> var7,
         ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> var8
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.reducer = var8;
      }

      @Override
      public U getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.Fun var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.BiFun var2 = this.reducer;
            if (this.reducer != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceEntriesTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, this.rights, var1, var2
                     ))
                     .fork();
               }

               Object var9 = null;

               ConcurrentHashMapV8.Node<K,V> var10;
               while ((var10 = this.advance()) != null) {
                  Object var12;
                  if ((var12 = var1.apply(var10)) != null) {
                     var9 = var9 == null ? var12 : var2.apply(var9, var12);
                  }
               }

               this.result = (U)var9;

               for (CountedCompleter var11 = this.firstComplete(); var11 != null; var11 = var11.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceEntriesTask var13 = (ConcurrentHashMapV8.MapReduceEntriesTask)var11;

                  for (ConcurrentHashMapV8.MapReduceEntriesTask var6 = var13.rights; var6 != null; var6 = var13.rights = var6.nextRight) {
                     Object var8 = var6.result;
                     if (var6.result != null) {
                        Object var7 = var13.result;
                        var13.result = (U)(var13.result == null ? var8 : var2.apply(var7, var8));
                     }
                  }
               }
            }
         }
      }
   }

   public static final class MapReduceEntriesToDoubleTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Double> {
      public double result;
      public ConcurrentHashMapV8.MapReduceEntriesToDoubleTask<K, V> nextRight;
      public double basis;
      public ConcurrentHashMapV8.ObjectToDouble<Entry<K, V>> transformer;
      public ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
      public ConcurrentHashMapV8.MapReduceEntriesToDoubleTask<K, V> rights;

      public MapReduceEntriesToDoubleTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceEntriesToDoubleTask<K, V> var6,
         ConcurrentHashMapV8.ObjectToDouble<Entry<K, V>> var7,
         double var8,
         ConcurrentHashMapV8.DoubleByDoubleToDouble var10
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var10;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectToDouble var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.DoubleByDoubleToDouble var2 = this.reducer;
            if (this.reducer != null) {
               double var3 = this.basis;
               int var5 = this.baseIndex;

               while (this.batch > 0) {
                  int var6 = this.baseLimit;
                  int var7;
                  if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceEntriesToDoubleTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var8;

               while ((var8 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var8));
               }

               this.result = var3;

               for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceEntriesToDoubleTask var10 = (ConcurrentHashMapV8.MapReduceEntriesToDoubleTask)var9;

                  for (ConcurrentHashMapV8.MapReduceEntriesToDoubleTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                     var10.result = var2.apply(var10.result, var11.result);
                  }
               }
            }
         }
      }

      public Double getRawResult() {
         return this.result;
      }
   }

   public static final class MapReduceEntriesToIntTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Integer> {
      public ConcurrentHashMapV8.IntByIntToInt reducer;
      public ConcurrentHashMapV8.MapReduceEntriesToIntTask<K, V> nextRight;
      public ConcurrentHashMapV8.MapReduceEntriesToIntTask<K, V> rights;
      public int result;
      public int basis;
      public ConcurrentHashMapV8.ObjectToInt<Entry<K, V>> transformer;

      public Integer getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectToInt var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.IntByIntToInt var2 = this.reducer;
            if (this.reducer != null) {
               int var3 = this.basis;
               int var4 = this.baseIndex;

               while (this.batch > 0) {
                  int var5 = this.baseLimit;
                  int var6;
                  if ((var6 = this.baseLimit + var4 >>> 1) <= var4) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceEntriesToIntTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var6, var5, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var7;

               while ((var7 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var7));
               }

               this.result = var3;

               for (CountedCompleter var8 = this.firstComplete(); var8 != null; var8 = var8.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceEntriesToIntTask var9 = (ConcurrentHashMapV8.MapReduceEntriesToIntTask)var8;

                  for (ConcurrentHashMapV8.MapReduceEntriesToIntTask var10 = var9.rights; var10 != null; var10 = var9.rights = var10.nextRight) {
                     var9.result = var2.apply(var9.result, var10.result);
                  }
               }
            }
         }
      }

      public MapReduceEntriesToIntTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceEntriesToIntTask<K, V> var6,
         ConcurrentHashMapV8.ObjectToInt<Entry<K, V>> var7,
         int var8,
         ConcurrentHashMapV8.IntByIntToInt var9
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var9;
      }
   }

   public static final class MapReduceEntriesToLongTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Long> {
      public ConcurrentHashMapV8.MapReduceEntriesToLongTask<K, V> nextRight;
      public ConcurrentHashMapV8.LongByLongToLong reducer;
      public long result;
      public ConcurrentHashMapV8.ObjectToLong<Entry<K, V>> transformer;
      public ConcurrentHashMapV8.MapReduceEntriesToLongTask<K, V> rights;
      public long basis;

      public Long getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectToLong var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.LongByLongToLong var2 = this.reducer;
            if (this.reducer != null) {
               long var3 = this.basis;
               int var5 = this.baseIndex;

               while (this.batch > 0) {
                  int var6 = this.baseLimit;
                  int var7;
                  if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceEntriesToLongTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var8;

               while ((var8 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var8));
               }

               this.result = var3;

               for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceEntriesToLongTask var10 = (ConcurrentHashMapV8.MapReduceEntriesToLongTask)var9;

                  for (ConcurrentHashMapV8.MapReduceEntriesToLongTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                     var10.result = var2.apply(var10.result, var11.result);
                  }
               }
            }
         }
      }

      public MapReduceEntriesToLongTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceEntriesToLongTask<K, V> var6,
         ConcurrentHashMapV8.ObjectToLong<Entry<K, V>> var7,
         long var8,
         ConcurrentHashMapV8.LongByLongToLong var10
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var10;
      }
   }

   public static final class MapReduceKeysTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, U> {
      public ConcurrentHashMapV8.MapReduceKeysTask<K, V, U> nextRight;
      public ConcurrentHashMapV8.Fun<? super K, ? extends U> transformer;
      public ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
      public U result;
      public ConcurrentHashMapV8.MapReduceKeysTask<K, V, U> rights;

      public MapReduceKeysTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceKeysTask<K, V, U> var6,
         ConcurrentHashMapV8.Fun<? super K, ? extends U> var7,
         ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> var8
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.reducer = var8;
      }

      @Override
      public U getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.Fun var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.BiFun var2 = this.reducer;
            if (this.reducer != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceKeysTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, this.rights, var1, var2
                     ))
                     .fork();
               }

               Object var9 = null;

               ConcurrentHashMapV8.Node<K,V> var10;
               while ((var10 = this.advance()) != null) {
                  Object var12;
                  if ((var12 = var1.apply(var10.key)) != null) {
                     var9 = var9 == null ? var12 : var2.apply(var9, var12);
                  }
               }

               this.result = (U)var9;

               for (CountedCompleter var11 = this.firstComplete(); var11 != null; var11 = var11.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceKeysTask var13 = (ConcurrentHashMapV8.MapReduceKeysTask)var11;

                  for (ConcurrentHashMapV8.MapReduceKeysTask var6 = var13.rights; var6 != null; var6 = var13.rights = var6.nextRight) {
                     Object var8 = var6.result;
                     if (var6.result != null) {
                        Object var7 = var13.result;
                        var13.result = (U)(var13.result == null ? var8 : var2.apply(var7, var8));
                     }
                  }
               }
            }
         }
      }
   }

   public static final class MapReduceKeysToDoubleTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Double> {
      public ConcurrentHashMapV8.MapReduceKeysToDoubleTask<K, V> rights;
      public double basis;
      public ConcurrentHashMapV8.MapReduceKeysToDoubleTask<K, V> nextRight;
      public ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
      public double result;
      public ConcurrentHashMapV8.ObjectToDouble<? super K> transformer;

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectToDouble var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.DoubleByDoubleToDouble var2 = this.reducer;
            if (this.reducer != null) {
               double var3 = this.basis;
               int var5 = this.baseIndex;

               while (this.batch > 0) {
                  int var6 = this.baseLimit;
                  int var7;
                  if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceKeysToDoubleTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var8;

               while ((var8 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var8.key));
               }

               this.result = var3;

               for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceKeysToDoubleTask var10 = (ConcurrentHashMapV8.MapReduceKeysToDoubleTask)var9;

                  for (ConcurrentHashMapV8.MapReduceKeysToDoubleTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                     var10.result = var2.apply(var10.result, var11.result);
                  }
               }
            }
         }
      }

      public Double getRawResult() {
         return this.result;
      }

      public MapReduceKeysToDoubleTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceKeysToDoubleTask<K, V> var6,
         ConcurrentHashMapV8.ObjectToDouble<? super K> var7,
         double var8,
         ConcurrentHashMapV8.DoubleByDoubleToDouble var10
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var10;
      }
   }

   public static final class MapReduceKeysToIntTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Integer> {
      public ConcurrentHashMapV8.ObjectToInt<? super K> transformer;
      public ConcurrentHashMapV8.MapReduceKeysToIntTask<K, V> rights;
      public ConcurrentHashMapV8.IntByIntToInt reducer;
      public ConcurrentHashMapV8.MapReduceKeysToIntTask<K, V> nextRight;
      public int result;
      public int basis;

      public MapReduceKeysToIntTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceKeysToIntTask<K, V> var6,
         ConcurrentHashMapV8.ObjectToInt<? super K> var7,
         int var8,
         ConcurrentHashMapV8.IntByIntToInt var9
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var9;
      }

      public Integer getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectToInt var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.IntByIntToInt var2 = this.reducer;
            if (this.reducer != null) {
               int var3 = this.basis;
               int var4 = this.baseIndex;

               while (this.batch > 0) {
                  int var5 = this.baseLimit;
                  int var6;
                  if ((var6 = this.baseLimit + var4 >>> 1) <= var4) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceKeysToIntTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var6, var5, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var7;

               while ((var7 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var7.key));
               }

               this.result = var3;

               for (CountedCompleter var8 = this.firstComplete(); var8 != null; var8 = var8.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceKeysToIntTask var9 = (ConcurrentHashMapV8.MapReduceKeysToIntTask)var8;

                  for (ConcurrentHashMapV8.MapReduceKeysToIntTask var10 = var9.rights; var10 != null; var10 = var9.rights = var10.nextRight) {
                     var9.result = var2.apply(var9.result, var10.result);
                  }
               }
            }
         }
      }
   }

   public static final class MapReduceKeysToLongTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Long> {
      public ConcurrentHashMapV8.LongByLongToLong reducer;
      public long basis;
      public ConcurrentHashMapV8.MapReduceKeysToLongTask<K, V> rights;
      public ConcurrentHashMapV8.MapReduceKeysToLongTask<K, V> nextRight;
      public ConcurrentHashMapV8.ObjectToLong<? super K> transformer;
      public long result;

      public Long getRawResult() {
         return this.result;
      }

      public MapReduceKeysToLongTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceKeysToLongTask<K, V> var6,
         ConcurrentHashMapV8.ObjectToLong<? super K> var7,
         long var8,
         ConcurrentHashMapV8.LongByLongToLong var10
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var10;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectToLong var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.LongByLongToLong var2 = this.reducer;
            if (this.reducer != null) {
               long var3 = this.basis;
               int var5 = this.baseIndex;

               while (this.batch > 0) {
                  int var6 = this.baseLimit;
                  int var7;
                  if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceKeysToLongTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var8;

               while ((var8 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var8.key));
               }

               this.result = var3;

               for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceKeysToLongTask var10 = (ConcurrentHashMapV8.MapReduceKeysToLongTask)var9;

                  for (ConcurrentHashMapV8.MapReduceKeysToLongTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                     var10.result = var2.apply(var10.result, var11.result);
                  }
               }
            }
         }
      }
   }

   public static final class MapReduceMappingsTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, U> {
      public ConcurrentHashMapV8.MapReduceMappingsTask<K, V, U> nextRight;
      public ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> transformer;
      public ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
      public ConcurrentHashMapV8.MapReduceMappingsTask<K, V, U> rights;
      public U result;

      public MapReduceMappingsTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceMappingsTask<K, V, U> var6,
         ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> var7,
         ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> var8
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.reducer = var8;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.BiFun var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.BiFun var2 = this.reducer;
            if (this.reducer != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceMappingsTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, this.rights, var1, var2
                     ))
                     .fork();
               }

               Object var9 = null;

               ConcurrentHashMapV8.Node<K,V> var10;
               while ((var10 = this.advance()) != null) {
                  Object var12;
                  if ((var12 = var1.apply(var10.key, var10.val)) != null) {
                     var9 = var9 == null ? var12 : var2.apply(var9, var12);
                  }
               }

               this.result = (U)var9;

               for (CountedCompleter var11 = this.firstComplete(); var11 != null; var11 = var11.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceMappingsTask var13 = (ConcurrentHashMapV8.MapReduceMappingsTask)var11;

                  for (ConcurrentHashMapV8.MapReduceMappingsTask var6 = var13.rights; var6 != null; var6 = var13.rights = var6.nextRight) {
                     Object var8 = var6.result;
                     if (var6.result != null) {
                        Object var7 = var13.result;
                        var13.result = (U)(var13.result == null ? var8 : var2.apply(var7, var8));
                     }
                  }
               }
            }
         }
      }

      @Override
      public U getRawResult() {
         return this.result;
      }
   }

   public static final class MapReduceMappingsToDoubleTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Double> {
      public ConcurrentHashMapV8.ObjectByObjectToDouble<? super K, ? super V> transformer;
      public ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
      public double result;
      public ConcurrentHashMapV8.MapReduceMappingsToDoubleTask<K, V> rights;
      public ConcurrentHashMapV8.MapReduceMappingsToDoubleTask<K, V> nextRight;
      public double basis;

      public Double getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectByObjectToDouble var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.DoubleByDoubleToDouble var2 = this.reducer;
            if (this.reducer != null) {
               double var3 = this.basis;
               int var5 = this.baseIndex;

               while (this.batch > 0) {
                  int var6 = this.baseLimit;
                  int var7;
                  if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceMappingsToDoubleTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var8;

               while ((var8 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var8.key, var8.val));
               }

               this.result = var3;

               for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceMappingsToDoubleTask var10 = (ConcurrentHashMapV8.MapReduceMappingsToDoubleTask)var9;

                  for (ConcurrentHashMapV8.MapReduceMappingsToDoubleTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                     var10.result = var2.apply(var10.result, var11.result);
                  }
               }
            }
         }
      }

      public MapReduceMappingsToDoubleTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceMappingsToDoubleTask<K, V> var6,
         ConcurrentHashMapV8.ObjectByObjectToDouble<? super K, ? super V> var7,
         double var8,
         ConcurrentHashMapV8.DoubleByDoubleToDouble var10
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var10;
      }
   }

   public static final class MapReduceMappingsToIntTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Integer> {
      public ConcurrentHashMapV8.IntByIntToInt reducer;
      public int basis;
      public ConcurrentHashMapV8.ObjectByObjectToInt<? super K, ? super V> transformer;
      public ConcurrentHashMapV8.MapReduceMappingsToIntTask<K, V> nextRight;
      public int result;
      public ConcurrentHashMapV8.MapReduceMappingsToIntTask<K, V> rights;

      public MapReduceMappingsToIntTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceMappingsToIntTask<K, V> var6,
         ConcurrentHashMapV8.ObjectByObjectToInt<? super K, ? super V> var7,
         int var8,
         ConcurrentHashMapV8.IntByIntToInt var9
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var9;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectByObjectToInt var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.IntByIntToInt var2 = this.reducer;
            if (this.reducer != null) {
               int var3 = this.basis;
               int var4 = this.baseIndex;

               while (this.batch > 0) {
                  int var5 = this.baseLimit;
                  int var6;
                  if ((var6 = this.baseLimit + var4 >>> 1) <= var4) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceMappingsToIntTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var6, var5, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var7;

               while ((var7 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var7.key, var7.val));
               }

               this.result = var3;

               for (CountedCompleter var8 = this.firstComplete(); var8 != null; var8 = var8.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceMappingsToIntTask var9 = (ConcurrentHashMapV8.MapReduceMappingsToIntTask)var8;

                  for (ConcurrentHashMapV8.MapReduceMappingsToIntTask var10 = var9.rights; var10 != null; var10 = var9.rights = var10.nextRight) {
                     var9.result = var2.apply(var9.result, var10.result);
                  }
               }
            }
         }
      }

      public Integer getRawResult() {
         return this.result;
      }
   }

   public static final class MapReduceMappingsToLongTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Long> {
      public long result;
      public long basis;
      public ConcurrentHashMapV8.MapReduceMappingsToLongTask<K, V> rights;
      public ConcurrentHashMapV8.ObjectByObjectToLong<? super K, ? super V> transformer;
      public ConcurrentHashMapV8.MapReduceMappingsToLongTask<K, V> nextRight;
      public ConcurrentHashMapV8.LongByLongToLong reducer;

      public Long getRawResult() {
         return this.result;
      }

      public MapReduceMappingsToLongTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceMappingsToLongTask<K, V> var6,
         ConcurrentHashMapV8.ObjectByObjectToLong<? super K, ? super V> var7,
         long var8,
         ConcurrentHashMapV8.LongByLongToLong var10
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var10;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectByObjectToLong var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.LongByLongToLong var2 = this.reducer;
            if (this.reducer != null) {
               long var3 = this.basis;
               int var5 = this.baseIndex;

               while (this.batch > 0) {
                  int var6 = this.baseLimit;
                  int var7;
                  if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceMappingsToLongTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var8;

               while ((var8 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var8.key, var8.val));
               }

               this.result = var3;

               for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceMappingsToLongTask var10 = (ConcurrentHashMapV8.MapReduceMappingsToLongTask)var9;

                  for (ConcurrentHashMapV8.MapReduceMappingsToLongTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                     var10.result = var2.apply(var10.result, var11.result);
                  }
               }
            }
         }
      }
   }

   public static final class MapReduceValuesTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, U> {
      public ConcurrentHashMapV8.MapReduceValuesTask<K, V, U> nextRight;
      public U result;
      public ConcurrentHashMapV8.MapReduceValuesTask<K, V, U> rights;
      public ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> reducer;
      public ConcurrentHashMapV8.Fun<? super V, ? extends U> transformer;

      @Override
      public U getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.Fun var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.BiFun var2 = this.reducer;
            if (this.reducer != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceValuesTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, this.rights, var1, var2
                     ))
                     .fork();
               }

               Object var9 = null;

               ConcurrentHashMapV8.Node<K,V> var10;
               while ((var10 = this.advance()) != null) {
                  Object var12;
                  if ((var12 = var1.apply(var10.val)) != null) {
                     var9 = var9 == null ? var12 : var2.apply(var9, var12);
                  }
               }

               this.result = (U)var9;

               for (CountedCompleter var11 = this.firstComplete(); var11 != null; var11 = var11.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceValuesTask var13 = (ConcurrentHashMapV8.MapReduceValuesTask)var11;

                  for (ConcurrentHashMapV8.MapReduceValuesTask var6 = var13.rights; var6 != null; var6 = var13.rights = var6.nextRight) {
                     Object var8 = var6.result;
                     if (var6.result != null) {
                        Object var7 = var13.result;
                        var13.result = (U)(var13.result == null ? var8 : var2.apply(var7, var8));
                     }
                  }
               }
            }
         }
      }

      public MapReduceValuesTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceValuesTask<K, V, U> var6,
         ConcurrentHashMapV8.Fun<? super V, ? extends U> var7,
         ConcurrentHashMapV8.BiFun<? super U, ? super U, ? extends U> var8
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.reducer = var8;
      }
   }

   public static final class MapReduceValuesToDoubleTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Double> {
      public ConcurrentHashMapV8.DoubleByDoubleToDouble reducer;
      public double basis;
      public ConcurrentHashMapV8.ObjectToDouble<? super V> transformer;
      public double result;
      public ConcurrentHashMapV8.MapReduceValuesToDoubleTask<K, V> nextRight;
      public ConcurrentHashMapV8.MapReduceValuesToDoubleTask<K, V> rights;

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectToDouble var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.DoubleByDoubleToDouble var2 = this.reducer;
            if (this.reducer != null) {
               double var3 = this.basis;
               int var5 = this.baseIndex;

               while (this.batch > 0) {
                  int var6 = this.baseLimit;
                  int var7;
                  if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceValuesToDoubleTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var8;

               while ((var8 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var8.val));
               }

               this.result = var3;

               for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceValuesToDoubleTask var10 = (ConcurrentHashMapV8.MapReduceValuesToDoubleTask)var9;

                  for (ConcurrentHashMapV8.MapReduceValuesToDoubleTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                     var10.result = var2.apply(var10.result, var11.result);
                  }
               }
            }
         }
      }

      public MapReduceValuesToDoubleTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceValuesToDoubleTask<K, V> var6,
         ConcurrentHashMapV8.ObjectToDouble<? super V> var7,
         double var8,
         ConcurrentHashMapV8.DoubleByDoubleToDouble var10
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var10;
      }

      public Double getRawResult() {
         return this.result;
      }
   }

   public static final class MapReduceValuesToIntTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Integer> {
      public int basis;
      public ConcurrentHashMapV8.ObjectToInt<? super V> transformer;
      public ConcurrentHashMapV8.MapReduceValuesToIntTask<K, V> rights;
      public ConcurrentHashMapV8.IntByIntToInt reducer;
      public int result;
      public ConcurrentHashMapV8.MapReduceValuesToIntTask<K, V> nextRight;

      public Integer getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectToInt var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.IntByIntToInt var2 = this.reducer;
            if (this.reducer != null) {
               int var3 = this.basis;
               int var4 = this.baseIndex;

               while (this.batch > 0) {
                  int var5 = this.baseLimit;
                  int var6;
                  if ((var6 = this.baseLimit + var4 >>> 1) <= var4) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceValuesToIntTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var6, var5, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var7;

               while ((var7 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var7.val));
               }

               this.result = var3;

               for (CountedCompleter var8 = this.firstComplete(); var8 != null; var8 = var8.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceValuesToIntTask var9 = (ConcurrentHashMapV8.MapReduceValuesToIntTask)var8;

                  for (ConcurrentHashMapV8.MapReduceValuesToIntTask var10 = var9.rights; var10 != null; var10 = var9.rights = var10.nextRight) {
                     var9.result = var2.apply(var9.result, var10.result);
                  }
               }
            }
         }
      }

      public MapReduceValuesToIntTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceValuesToIntTask<K, V> var6,
         ConcurrentHashMapV8.ObjectToInt<? super V> var7,
         int var8,
         ConcurrentHashMapV8.IntByIntToInt var9
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var9;
      }
   }

   public static final class MapReduceValuesToLongTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Long> {
      public ConcurrentHashMapV8.MapReduceValuesToLongTask<K, V> rights;
      public ConcurrentHashMapV8.MapReduceValuesToLongTask<K, V> nextRight;
      public long result;
      public long basis;
      public ConcurrentHashMapV8.ObjectToLong<? super V> transformer;
      public ConcurrentHashMapV8.LongByLongToLong reducer;

      public Long getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.ObjectToLong var1 = this.transformer;
         if (this.transformer != null) {
            ConcurrentHashMapV8.LongByLongToLong var2 = this.reducer;
            if (this.reducer != null) {
               long var3 = this.basis;
               int var5 = this.baseIndex;

               while (this.batch > 0) {
                  int var6 = this.baseLimit;
                  int var7;
                  if ((var7 = this.baseLimit + var5 >>> 1) <= var5) {
                     break;
                  }

                  this.addToPendingCount(1);
                  (this.rights = new ConcurrentHashMapV8.MapReduceValuesToLongTask<>(
                        this, this.batch >>>= 1, this.baseLimit = var7, var6, this.tab, this.rights, var1, var3, var2
                     ))
                     .fork();
               }

               ConcurrentHashMapV8.Node<K,V> var8;

               while ((var8 = this.advance()) != null) {
                  var3 = var2.apply(var3, var1.apply(var8.val));
               }

               this.result = var3;

               for (CountedCompleter var9 = this.firstComplete(); var9 != null; var9 = var9.nextComplete()) {
                  ConcurrentHashMapV8.MapReduceValuesToLongTask var10 = (ConcurrentHashMapV8.MapReduceValuesToLongTask)var9;

                  for (ConcurrentHashMapV8.MapReduceValuesToLongTask var11 = var10.rights; var11 != null; var11 = var10.rights = var11.nextRight) {
                     var10.result = var2.apply(var10.result, var11.result);
                  }
               }
            }
         }
      }

      public MapReduceValuesToLongTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.MapReduceValuesToLongTask<K, V> var6,
         ConcurrentHashMapV8.ObjectToLong<? super V> var7,
         long var8,
         ConcurrentHashMapV8.LongByLongToLong var10
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.transformer = var7;
         this.basis = var8;
         this.reducer = var10;
      }
   }

   public static class Node<K, V> implements Entry<K, V> {
      public volatile V val;
      public K key;
      public int hash;
      public volatile ConcurrentHashMapV8.Node<K, V> next;

      @Override
      public String toString() {
         return this.key + "=" + this.val;
      }

      public Node(int var1, K var2, V var3, ConcurrentHashMapV8.Node<K, V> var4) {
         this.hash = var1;
         this.key = (K)var2;
         this.val = (V)var3;
         this.next = var4;
      }

      @Override
      public V setValue(V var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public V getValue() {
         return this.val;
      }

      @Override
      public int hashCode() {
         return this.key.hashCode() ^ this.val.hashCode();
      }

      @Override
      public K getKey() {
         return this.key;
      }

      public ConcurrentHashMapV8.Node<K, V> find(int var1, Object var2) {
         ConcurrentHashMapV8.Node<K,V> var3 = this;
         if (var2 != null) {
            do {
               if (var3.hash == var1) {
                  Object var4 = var3.key;
                  if (var3.key == var2 || var4 != null && var2.equals(var4)) {
                     return var3;
                  }
               }
            } while ((var3 = var3.next) != null);
         }

         return null;
      }

      @Override
      public boolean equals(Object var1) {
         Object var2;
         Object var3;
         Entry var5;
         if (var1 instanceof Entry
            && (var2 = (var5 = (Entry)var1).getKey()) != null
            && (var3 = var5.getValue()) != null
            && (var2 == this.key || var2.equals(this.key))) {
            Object var4 = this.val;
            if (var3 == this.val || var3.equals(var4)) {
               return true;
            }
         }

         return false;
      }
   }

   public interface ObjectByObjectToDouble<A, B> {
      double apply(A var1, B var2);
   }

   public interface ObjectByObjectToInt<A, B> {
      int apply(A var1, B var2);
   }

   public interface ObjectByObjectToLong<A, B> {
      long apply(A var1, B var2);
   }

   public interface ObjectToDouble<A> {
      double apply(A var1);
   }

   public interface ObjectToInt<A> {
      int apply(A var1);
   }

   public interface ObjectToLong<A> {
      long apply(A var1);
   }

   public static final class ReduceEntriesTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, Entry<K, V>> {
      public ConcurrentHashMapV8.BiFun<Entry<K, V>, Entry<K, V>, ? extends Entry<K, V>> reducer;
      public ConcurrentHashMapV8.ReduceEntriesTask<K, V> nextRight;
      public ConcurrentHashMapV8.ReduceEntriesTask<K, V> rights;
      public Entry<K, V> result;

      public ReduceEntriesTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.ReduceEntriesTask<K, V> var6,
         ConcurrentHashMapV8.BiFun<Entry<K, V>, Entry<K, V>, ? extends Entry<K, V>> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.reducer = var7;
      }

      public Entry<K, V> getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.BiFun var1 = this.reducer;
         if (this.reducer != null) {
            int var2 = this.baseIndex;

            while (this.batch > 0) {
               int var3 = this.baseLimit;
               int var4;
               if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
                  break;
               }

               this.addToPendingCount(1);
               (this.rights = new ConcurrentHashMapV8.ReduceEntriesTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, this.rights, var1))
                  .fork();
            }

            Object var8 = null;

            ConcurrentHashMapV8.Node<K,V> var9;
            while ((var9 = this.advance()) != null) {
               var8 = var8 == null ? var9 : (Entry)var1.apply(var8, var9);
            }

            this.result = (Entry<K, V>)var8;

            for (CountedCompleter var10 = this.firstComplete(); var10 != null; var10 = var10.nextComplete()) {
               ConcurrentHashMapV8.ReduceEntriesTask var11 = (ConcurrentHashMapV8.ReduceEntriesTask)var10;

               for (ConcurrentHashMapV8.ReduceEntriesTask var5 = var11.rights; var5 != null; var5 = var11.rights = var5.nextRight) {
                  Entry var7 = var5.result;
                  if (var5.result != null) {
                     Entry var6 = var11.result;
                     var11.result = var11.result == null ? var7 : (Entry)var1.apply(var6, var7);
                  }
               }
            }
         }
      }
   }

   public static final class ReduceKeysTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, K> {
      public K result;
      public ConcurrentHashMapV8.ReduceKeysTask<K, V> nextRight;
      public ConcurrentHashMapV8.BiFun<? super K, ? super K, ? extends K> reducer;
      public ConcurrentHashMapV8.ReduceKeysTask<K, V> rights;

      public ReduceKeysTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.ReduceKeysTask<K, V> var6,
         ConcurrentHashMapV8.BiFun<? super K, ? super K, ? extends K> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.reducer = var7;
      }

      @Override
      public K getRawResult() {
         return this.result;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.BiFun var1 = this.reducer;
         if (this.reducer != null) {
            int var2 = this.baseIndex;

            while (this.batch > 0) {
               int var3 = this.baseLimit;
               int var4;
               if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
                  break;
               }

               this.addToPendingCount(1);
               (this.rights = new ConcurrentHashMapV8.ReduceKeysTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, this.rights, var1))
                  .fork();
            }

            Object var8 = null;

            ConcurrentHashMapV8.Node<K,V> var9;
            while ((var9 = this.advance()) != null) {
               Object var11 = var9.key;
               var8 = var8 == null ? var11 : (var11 == null ? var8 : var1.apply(var8, var11));
            }

            this.result = (K)var8;

            for (CountedCompleter var10 = this.firstComplete(); var10 != null; var10 = var10.nextComplete()) {
               ConcurrentHashMapV8.ReduceKeysTask var12 = (ConcurrentHashMapV8.ReduceKeysTask)var10;

               for (ConcurrentHashMapV8.ReduceKeysTask var5 = var12.rights; var5 != null; var5 = var12.rights = var5.nextRight) {
                  Object var7 = var5.result;
                  if (var5.result != null) {
                     Object var6 = var12.result;
                     var12.result = (K)(var12.result == null ? var7 : var1.apply(var6, var7));
                  }
               }
            }
         }
      }
   }

   public static final class ReduceValuesTask<K, V> extends ConcurrentHashMapV8.BulkTask<K, V, V> {
      public V result;
      public ConcurrentHashMapV8.ReduceValuesTask<K, V> nextRight;
      public ConcurrentHashMapV8.BiFun<? super V, ? super V, ? extends V> reducer;
      public ConcurrentHashMapV8.ReduceValuesTask<K, V> rights;

      public ReduceValuesTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.ReduceValuesTask<K, V> var6,
         ConcurrentHashMapV8.BiFun<? super V, ? super V, ? extends V> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.nextRight = var6;
         this.reducer = var7;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.BiFun var1 = this.reducer;
         if (this.reducer != null) {
            int var2 = this.baseIndex;

            while (this.batch > 0) {
               int var3 = this.baseLimit;
               int var4;
               if ((var4 = this.baseLimit + var2 >>> 1) <= var2) {
                  break;
               }

               this.addToPendingCount(1);
               (this.rights = new ConcurrentHashMapV8.ReduceValuesTask<>(this, this.batch >>>= 1, this.baseLimit = var4, var3, this.tab, this.rights, var1))
                  .fork();
            }

            Object var8 = null;

            ConcurrentHashMapV8.Node<K,V> var9;
            while ((var9 = this.advance()) != null) {
               Object var11 = var9.val;
               var8 = var8 == null ? var11 : var1.apply(var8, var11);
            }

            this.result = (V)var8;

            for (CountedCompleter var10 = this.firstComplete(); var10 != null; var10 = var10.nextComplete()) {
               ConcurrentHashMapV8.ReduceValuesTask var12 = (ConcurrentHashMapV8.ReduceValuesTask)var10;

               for (ConcurrentHashMapV8.ReduceValuesTask var5 = var12.rights; var5 != null; var5 = var12.rights = var5.nextRight) {
                  Object var7 = var5.result;
                  if (var5.result != null) {
                     Object var6 = var12.result;
                     var12.result = (V)(var12.result == null ? var7 : var1.apply(var6, var7));
                  }
               }
            }
         }
      }

      @Override
      public V getRawResult() {
         return this.result;
      }
   }

   public static final class ReservationNode<K, V> extends ConcurrentHashMapV8.Node<K, V> {

      @Override
      public ConcurrentHashMapV8.Node<K, V> find(int var1, Object var2) {
         return null;
      }

      public ReservationNode() {
         super(-3, null, null, null);
      }
   }

   public static final class SearchEntriesTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, U> {
      public AtomicReference<U> result;
      public ConcurrentHashMapV8.Fun<Entry<K, V>, ? extends U> searchFunction;

      public SearchEntriesTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.Fun<Entry<K, V>, ? extends U> var6,
         AtomicReference<U> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.searchFunction = var6;
         this.result = var7;
      }

      @Override
      public U getRawResult() {
         return this.result.get();
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.Fun var1 = this.searchFunction;
         if (this.searchFunction != null) {
            AtomicReference var2 = this.result;
            if (this.result != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  if (var2.get() != null) {
                     return;
                  }

                  this.addToPendingCount(1);
                  new ConcurrentHashMapV8.SearchEntriesTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
               }

               while (var2.get() == null) {
                  ConcurrentHashMapV8.Node<K,V> var7;
                  if ((var7 = this.advance()) == null) {
                     this.propagateCompletion();
                     break;
                  }

                  Object var6;
                  if ((var6 = var1.apply(var7)) != null) {
                     if (var2.compareAndSet(null, var6)) {
                        this.quietlyCompleteRoot();
                     }

                     return;
                  }
               }
            }
         }
      }
   }

   public static final class SearchKeysTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, U> {
      public AtomicReference<U> result;
      public ConcurrentHashMapV8.Fun<? super K, ? extends U> searchFunction;

      public SearchKeysTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.Fun<? super K, ? extends U> var6,
         AtomicReference<U> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.searchFunction = var6;
         this.result = var7;
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.Fun var1 = this.searchFunction;
         if (this.searchFunction != null) {
            AtomicReference var2 = this.result;
            if (this.result != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  if (var2.get() != null) {
                     return;
                  }

                  this.addToPendingCount(1);
                  new ConcurrentHashMapV8.SearchKeysTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
               }

               while (var2.get() == null) {
                  ConcurrentHashMapV8.Node<K,V> var7;
                  if ((var7 = this.advance()) == null) {
                     this.propagateCompletion();
                     break;
                  }

                  Object var6;
                  if ((var6 = var1.apply(var7.key)) != null) {
                     if (var2.compareAndSet(null, var6)) {
                        this.quietlyCompleteRoot();
                     }
                     break;
                  }
               }
            }
         }
      }

      @Override
      public U getRawResult() {
         return this.result.get();
      }
   }

   public static final class SearchMappingsTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, U> {
      public AtomicReference<U> result;
      public ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> searchFunction;

      @Override
      public void compute() {
         ConcurrentHashMapV8.BiFun var1 = this.searchFunction;
         if (this.searchFunction != null) {
            AtomicReference var2 = this.result;
            if (this.result != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  if (var2.get() != null) {
                     return;
                  }

                  this.addToPendingCount(1);
                  new ConcurrentHashMapV8.SearchMappingsTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
               }

               while (var2.get() == null) {
                  ConcurrentHashMapV8.Node<K,V> var7;
                  if ((var7 = this.advance()) == null) {
                     this.propagateCompletion();
                     break;
                  }

                  Object var6;
                  if ((var6 = var1.apply(var7.key, var7.val)) != null) {
                     if (var2.compareAndSet(null, var6)) {
                        this.quietlyCompleteRoot();
                     }
                     break;
                  }
               }
            }
         }
      }

      public SearchMappingsTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.BiFun<? super K, ? super V, ? extends U> var6,
         AtomicReference<U> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.searchFunction = var6;
         this.result = var7;
      }

      @Override
      public U getRawResult() {
         return this.result.get();
      }
   }

   public static final class SearchValuesTask<K, V, U> extends ConcurrentHashMapV8.BulkTask<K, V, U> {
      public AtomicReference<U> result;
      public ConcurrentHashMapV8.Fun<? super V, ? extends U> searchFunction;

      public SearchValuesTask(
         ConcurrentHashMapV8.BulkTask<K, V, ?> var1,
         int var2,
         int var3,
         int var4,
         ConcurrentHashMapV8.Node<K, V>[] var5,
         ConcurrentHashMapV8.Fun<? super V, ? extends U> var6,
         AtomicReference<U> var7
      ) {
         super(var1, var2, var3, var4, var5);
         this.searchFunction = var6;
         this.result = var7;
      }

      @Override
      public U getRawResult() {
         return this.result.get();
      }

      @Override
      public void compute() {
         ConcurrentHashMapV8.Fun var1 = this.searchFunction;
         if (this.searchFunction != null) {
            AtomicReference var2 = this.result;
            if (this.result != null) {
               int var3 = this.baseIndex;

               while (this.batch > 0) {
                  int var4 = this.baseLimit;
                  int var5;
                  if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                     break;
                  }

                  if (var2.get() != null) {
                     return;
                  }

                  this.addToPendingCount(1);
                  new ConcurrentHashMapV8.SearchValuesTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
               }

               while (var2.get() == null) {
                  ConcurrentHashMapV8.Node<K,V> var7;
                  if ((var7 = this.advance()) == null) {
                     this.propagateCompletion();
                     break;
                  }

                  Object var6;
                  if ((var6 = var1.apply(var7.val)) != null) {
                     if (var2.compareAndSet(null, var6)) {
                        this.quietlyCompleteRoot();
                     }
                     break;
                  }
               }
            }
         }
      }
   }

   public static class Segment<K, V> extends ReentrantLock implements Serializable {
      public float loadFactor;
      public static final long serialVersionUID = 2249069246763182397L;

      public Segment(float var1) {
         this.loadFactor = var1;
      }
   }

   public static class Traverser<K, V> {
      public ConcurrentHashMapV8.Node<K, V> next;
      public ConcurrentHashMapV8.Node<K, V>[] tab;
      public int index;
      public int baseSize;
      public int baseIndex;
      public int baseLimit;

      public ConcurrentHashMapV8.Node<K, V> advance() {
         Object var1 = this.next;
         if (this.next != null) {
            var1 = ((ConcurrentHashMapV8.Node)var1).next;
         }

         while (true) {
            if (var1 != null) {
               return this.next = (ConcurrentHashMapV8.Node<K, V>)var1;
            }

            if (this.baseIndex >= this.baseLimit) {
               break;
            }

            ConcurrentHashMapV8.Node[] var2 = this.tab;
            if (this.tab == null) {
               break;
            }

            int var4;
            int var10000 = var4 = var2.length;
            int var3 = this.index;
            if (var10000 <= this.index || var3 < 0) {
               break;
            }

            if ((var1 = ConcurrentHashMapV8.tabAt(var2, this.index)) != null && ((ConcurrentHashMapV8.Node)var1).hash < 0) {
               if (var1 instanceof ConcurrentHashMapV8.ForwardingNode) {
                  this.tab = ((ConcurrentHashMapV8.ForwardingNode)var1).nextTable;
                  var1 = null;
                  continue;
               }

               if (var1 instanceof ConcurrentHashMapV8.TreeBin) {
                  var1 = ((ConcurrentHashMapV8.TreeBin)var1).first;
               } else {
                  var1 = null;
               }
            }

            if ((this.index = this.index + this.baseSize) >= var4) {
               this.index = ++this.baseIndex;
            }
         }

         return this.next = null;
      }

      public Traverser(ConcurrentHashMapV8.Node<K, V>[] var1, int var2, int var3, int var4) {
         this.tab = var1;
         this.baseSize = var2;
         this.baseIndex = this.index = var3;
         this.baseLimit = var4;
         this.next = null;
      }
   }

   public static final class TreeBin<K, V> extends ConcurrentHashMapV8.Node<K, V> {
      public static Unsafe U;
      public volatile ConcurrentHashMapV8.TreeNode<K, V> first;
      public volatile int lockState;
      public volatile Thread waiter;
      public static final int WAITER = 2;
      public static long LOCKSTATE;
      public static final int READER = 4;
      public static final int WRITER = 1;
      // $VF: synthetic field
      public static final boolean $assertionsDisabled = !ConcurrentHashMapV8.class.desiredAssertionStatus();
      public ConcurrentHashMapV8.TreeNode<K, V> root;

      static {
         try {
            U = ConcurrentHashMapV8.getUnsafe();
            Class<ConcurrentHashMapV8.TreeBin> var0 = ConcurrentHashMapV8.TreeBin.class;
            LOCKSTATE = U.objectFieldOffset(var0.getDeclaredField("lockState"));
         } catch (Exception var1) {
            throw new Error(var1);
         }
      }

      public static <K, V> ConcurrentHashMapV8.TreeNode<K, V> rotateRight(ConcurrentHashMapV8.TreeNode<K, V> var0, ConcurrentHashMapV8.TreeNode<K, V> var1) {
         if (var1 != null) {
            ConcurrentHashMapV8.TreeNode var2 = var1.left;
            if (var1.left != null) {
               ConcurrentHashMapV8.TreeNode var4;
               if ((var4 = var1.left = var2.right) != null) {
                  var4.parent = var1;
               }

               ConcurrentHashMapV8.TreeNode var3;
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

      public static <K, V> boolean checkInvariants(ConcurrentHashMapV8.TreeNode<K, V> var0) {
         ConcurrentHashMapV8.TreeNode var1 = var0.parent;
         ConcurrentHashMapV8.TreeNode var2 = var0.left;
         ConcurrentHashMapV8.TreeNode var3 = var0.right;
         ConcurrentHashMapV8.TreeNode var4 = var0.prev;
         ConcurrentHashMapV8.TreeNode var5 = (ConcurrentHashMapV8.TreeNode)var0.next;
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

      public static <K, V> ConcurrentHashMapV8.TreeNode<K, V> rotateLeft(ConcurrentHashMapV8.TreeNode<K, V> var0, ConcurrentHashMapV8.TreeNode<K, V> var1) {
         if (var1 != null) {
            ConcurrentHashMapV8.TreeNode var2 = var1.right;
            if (var1.right != null) {
               ConcurrentHashMapV8.TreeNode var4;
               if ((var4 = var1.right = var2.left) != null) {
                  var4.parent = var1;
               }

               ConcurrentHashMapV8.TreeNode var3;
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

      public static <K, V> ConcurrentHashMapV8.TreeNode<K, V> balanceInsertion(ConcurrentHashMapV8.TreeNode<K, V> var0, ConcurrentHashMapV8.TreeNode<K, V> var1) {
         var1.red = true;

         while (true) {
            ConcurrentHashMapV8.TreeNode var2 = var1.parent;
            if (var1.parent == null) {
               var1.red = false;
               return var1;
            }

            if (!var2.red) {
               break;
            }

            ConcurrentHashMapV8.TreeNode var3 = var2.parent;
            if (var2.parent == null) {
               break;
            }

            ConcurrentHashMapV8.TreeNode var4 = var3.left;
            if (var2 == var3.left) {
               ConcurrentHashMapV8.TreeNode var5 = var3.right;
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
      public ConcurrentHashMapV8.Node<K, V> find(int var1, Object var2) {
         if (var2 != null) {
            for (Object var3 = this.first; var3 != null; var3 = ((ConcurrentHashMapV8.Node)var3).next) {
               int var4 = this.lockState;
               if ((this.lockState & 3) != 0) {
                  if (((ConcurrentHashMapV8.Node)var3).hash == var1) {
                     Object var5 = ((ConcurrentHashMapV8.Node)var3).key;
                     if (((ConcurrentHashMapV8.Node)var3).key == var2 || var5 != null && var2.equals(var5)) {
                        return (ConcurrentHashMapV8.Node<K, V>)var3;
                     }
                  }
               } else if (U.compareAndSwapInt(this, LOCKSTATE, var4, var4 + 4)) {
                  ConcurrentHashMapV8.TreeNode var7;
                  try {
                     ConcurrentHashMapV8.TreeNode var6 = this.root;
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

      public static <K, V> ConcurrentHashMapV8.TreeNode<K, V> balanceDeletion(ConcurrentHashMapV8.TreeNode<K, V> var0, ConcurrentHashMapV8.TreeNode<K, V> var1) {
         while (var1 != null && var1 != var0) {
            ConcurrentHashMapV8.TreeNode var2 = var1.parent;
            if (var1.parent == null) {
               var1.red = false;
               return var1;
            }

            if (var1.red) {
               var1.red = false;
               return var0;
            }

            ConcurrentHashMapV8.TreeNode var3 = var2.left;
            if (var2.left == var1) {
               ConcurrentHashMapV8.TreeNode var4 = var2.right;
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
                  ConcurrentHashMapV8.TreeNode var8 = var4.left;
                  ConcurrentHashMapV8.TreeNode var9 = var4.right;
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
                  ConcurrentHashMapV8.TreeNode var5 = var3.left;
                  ConcurrentHashMapV8.TreeNode var6 = var3.right;
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

      public TreeBin(ConcurrentHashMapV8.TreeNode<K, V> var1) {
         super(-2, null, null, null);
         this.first = var1;
         ConcurrentHashMapV8.TreeNode var2 = null;
         ConcurrentHashMapV8.TreeNode var3 = var1;

         while (var3 != null) {
            ConcurrentHashMapV8.TreeNode var4 = (ConcurrentHashMapV8.TreeNode)var3.next;
            var3.left = var3.right = null;
            if (var2 == null) {
               var3.parent = null;
               var3.red = false;
               var2 = var3;
            } else {
               Object var5 = var3.key;
               int var6 = var3.hash;
               Class var7 = null;
               ConcurrentHashMapV8.TreeNode var8 = var2;

               int var9;
               ConcurrentHashMapV8.TreeNode var11;
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

      public ConcurrentHashMapV8.TreeNode<K, V> putTreeVal(int var1, K var2, V var3) {
         Class var4 = null;
         ConcurrentHashMapV8.TreeNode var5 = this.root;

         while (true) {
            if (var5 == null) {
               this.first = this.root = new ConcurrentHashMapV8.TreeNode<>(var1, (K)var2, (V)var3, null, null);
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
                        ConcurrentHashMapV8.TreeNode var10 = var5.right;
                        ConcurrentHashMapV8.TreeNode var9;
                        if (var5.right != null && (var9 = var10.findTreeNode(var1, var2, var4)) != null) {
                           return var9;
                        }

                        var6 = -1;
                     }
                  }
               }

               ConcurrentHashMapV8.TreeNode var11 = var5;
               if ((var5 = var6 < 0 ? var5.left : var5.right) != null) {
                  continue;
               }

               ConcurrentHashMapV8.TreeNode var13 = this.first;
               ConcurrentHashMapV8.TreeNode var12;
               this.first = var12 = new ConcurrentHashMapV8.TreeNode<>(var1, var2, var3, var13, var11);
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

      public boolean removeTreeNode(ConcurrentHashMapV8.TreeNode<K, V> var1) {
         ConcurrentHashMapV8.TreeNode var2 = (ConcurrentHashMapV8.TreeNode)var1.next;
         ConcurrentHashMapV8.TreeNode var3 = var1.prev;
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
            ConcurrentHashMapV8.TreeNode var4 = this.root;
            if (this.root != null && var4.right != null) {
               ConcurrentHashMapV8.TreeNode var5 = var4.left;
               if (var4.left != null && var5.left != null) {
                  this.lockRoot();

                  try {
                     ConcurrentHashMapV8.TreeNode var7 = var1.left;
                     ConcurrentHashMapV8.TreeNode var8 = var1.right;
                     ConcurrentHashMapV8.TreeNode var6;
                     if (var7 != null && var8 != null) {
                        ConcurrentHashMapV8.TreeNode var9 = var8;

                        while (true) {
                           ConcurrentHashMapV8.TreeNode var10 = var9.left;
                           if (var9.left == null) {
                              boolean var11 = var9.red;
                              var9.red = var1.red;
                              var1.red = var11;
                              ConcurrentHashMapV8.TreeNode var12 = var9.right;
                              ConcurrentHashMapV8.TreeNode var13 = var1.parent;
                              if (var9 == var8) {
                                 var1.parent = var9;
                                 var9.right = var1;
                              } else {
                                 ConcurrentHashMapV8.TreeNode var14 = var9.parent;
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
                        ConcurrentHashMapV8.TreeNode var18 = var6.parent = var1.parent;
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
                        ConcurrentHashMapV8.TreeNode var19 = var1.parent;
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

   public static final class TreeNode<K, V> extends ConcurrentHashMapV8.Node<K, V> {
      public ConcurrentHashMapV8.TreeNode<K, V> left;
      public ConcurrentHashMapV8.TreeNode<K, V> prev;
      public ConcurrentHashMapV8.TreeNode<K, V> parent;
      public boolean red;
      public ConcurrentHashMapV8.TreeNode<K, V> right;

      @Override
      public ConcurrentHashMapV8.Node<K, V> find(int var1, Object var2) {
         return this.findTreeNode(var1, var2, null);
      }

      public ConcurrentHashMapV8.TreeNode<K, V> findTreeNode(int var1, Object var2, Class<?> var3) {
         if (var2 != null) {
            ConcurrentHashMapV8.TreeNode var4 = this;

            do {
               ConcurrentHashMapV8.TreeNode var9 = var4.left;
               ConcurrentHashMapV8.TreeNode var10 = var4.right;
               int var5 = var4.hash;
               if (var4.hash > var1) {
                  var4 = var9;
               } else if (var5 < var1) {
                  var4 = var10;
               } else {
                  Object var7 = var4.key;
                  if (var4.key == var2 || var7 != null && var2.equals(var7)) {
                     return var4;
                  }

                  if (var9 == null && var10 == null) {
                     break;
                  }

                  int var6;
                  if ((var3 != null || (var3 = ConcurrentHashMapV8.comparableClassFor(var2)) != null)
                     && (var6 = ConcurrentHashMapV8.compareComparables(var3, var2, var7)) != 0) {
                     var4 = var6 < 0 ? var9 : var10;
                  } else if (var9 == null) {
                     var4 = var10;
                  } else {
                     ConcurrentHashMapV8.TreeNode var8;
                     if (var10 != null && (var8 = var10.findTreeNode(var1, var2, var3)) != null) {
                        return var8;
                     }

                     var4 = var9;
                  }
               }
            } while (var4 != null);
         }

         return null;
      }

      public TreeNode(int var1, K var2, V var3, ConcurrentHashMapV8.Node<K, V> var4, ConcurrentHashMapV8.TreeNode<K, V> var5) {
         super(var1, (K)var2, (V)var3, var4);
         this.parent = var5;
      }
   }

   public static final class ValueIterator<K, V> extends ConcurrentHashMapV8.BaseIterator<K, V> implements Enumeration<V>, Iterator<V> {

      @Override
      public V next() {
         ConcurrentHashMapV8.Node<K,V> var1 = this.next;
         if (this.next == null) {
            throw new NoSuchElementException();
         } else {
            Object var2 = var1.val;
            this.lastReturned = var1;
            this.advance();
            return (V)var2;
         }
      }

      public ValueIterator(ConcurrentHashMapV8.Node<K, V>[] var1, int var2, int var3, int var4, ConcurrentHashMapV8<K, V> var5) {
         super(var1, var2, var3, var4, var5);
      }

      @Override
      public V nextElement() {
         return this.next();
      }
   }

   public static final class ValueSpliterator<K, V> extends ConcurrentHashMapV8.Traverser<K, V> implements ConcurrentHashMapV8.ConcurrentHashMapSpliterator<V> {
      public long est;

      @Override
      public long estimateSize() {
         return this.est;
      }

      public ValueSpliterator(ConcurrentHashMapV8.Node<K, V>[] var1, int var2, int var3, int var4, long var5) {
         super(var1, var2, var3, var4);
         this.est = var5;
      }

      @Override
      public boolean tryAdvance(ConcurrentHashMapV8.Action<? super V> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            ConcurrentHashMapV8.Node<K,V> var2;
            if ((var2 = this.advance()) == null) {
               return false;
            } else {
               var1.apply(var2.val);
               return true;
            }
         }
      }

      @Override
      public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<V> trySplit() {
         int var1 = this.baseIndex;
         int var2 = this.baseLimit;
         int var3;
         return (var3 = this.baseIndex + this.baseLimit >>> 1) <= var1
            ? null
            : new ConcurrentHashMapV8.ValueSpliterator<>(this.tab, this.baseSize, this.baseLimit = var3, var2, this.est >>>= 1);
      }

      @Override
      public void forEachRemaining(ConcurrentHashMapV8.Action<? super V> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            ConcurrentHashMapV8.Node<K,V> var2;
            while ((var2 = this.advance()) != null) {
               var1.apply(var2.val);
            }
         }
      }
   }

   public static final class ValuesView<K, V> extends ConcurrentHashMapV8.CollectionView<K, V, V> implements Serializable, Collection<V> {
      public static final long serialVersionUID = 2249069246763182397L;

      public void forEach(ConcurrentHashMapV8.Action<? super V> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            ConcurrentHashMapV8.Node[] var2 = this.map.table;
            if (this.map.table != null) {
               ConcurrentHashMapV8.Traverser var3 = new ConcurrentHashMapV8.Traverser(var2, var2.length, 0, var2.length);

               ConcurrentHashMapV8.Node<K,V> var4;
               while ((var4 = var3.advance()) != null) {
                  var1.apply(var4.val);
               }
            }
         }
      }

      @Override
      public boolean contains(Object var1) {
         return this.map.containsValue(var1);
      }

      @Override
      public Iterator<V> iterator() {
         ConcurrentHashMapV8 var1 = this.map;
         ConcurrentHashMapV8.Node[] var2 = var1.table;
         int var3 = var1.table == null ? 0 : var2.length;
         return new ConcurrentHashMapV8.ValueIterator<>(var2, var3, 0, var3, var1);
      }

      @Override
      public boolean addAll(Collection<? extends V> var1) {
         throw new UnsupportedOperationException();
      }

      public ValuesView(ConcurrentHashMapV8<K, V> var1) {
         super(var1);
      }

      @Override
      public boolean add(V var1) {
         throw new UnsupportedOperationException();
      }

      public ConcurrentHashMapV8.ConcurrentHashMapSpliterator<V> spliterator166() {
         ConcurrentHashMapV8 var2 = this.map;
         long var3 = var2.sumCount();
         ConcurrentHashMapV8.Node[] var1 = var2.table;
         int var5 = var2.table == null ? 0 : var1.length;
         return new ConcurrentHashMapV8.ValueSpliterator<>(var1, var5, 0, var5, var3 < 0L ? 0L : var3);
      }

      @Override
      public boolean remove(Object var1) {
         if (var1 != null) {
            Iterator var2 = this.iterator();

            while (var2.hasNext()) {
               if (var1.equals(var2.next())) {
                  var2.remove();
                  return true;
               }
            }
         }

         return false;
      }
   }
}
