package io.netty.util.internal;

import com.cheatbreaker.client.ui.mainmenu.element.IconButtonElement;
import io.netty.util.Recycler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.command.CommandPlaySound;
import net.minecraft.command.server.CommandScoreboard;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import net.minecraft.scoreboard.ServerScoreboard;

public class RecyclableArrayList extends ArrayList<Object> {
   public static final long serialVersionUID = -8605125654176467947L;
   public Recycler.Handle handle;
   public static final int DEFAULT_INITIAL_CAPACITY = 8;
   public static Recycler<RecyclableArrayList> RECYCLER = new Recycler<RecyclableArrayList>() {

      public RecyclableArrayList newObject(Recycler.Handle var1) {
         return new RecyclableArrayList(var1);
      }
   };

   public RecyclableArrayList(Recycler.Handle var1) {
      this(var1, 8);
   }

   @Override
   public Object set(int var1, Object var2) {
      if (var2 == null) {
         throw new NullPointerException("element");
      } else {
         return super.set(var1, var2);
      }
   }

   public static RecyclableArrayList newInstance(int var0) {
      RecyclableArrayList var1 = RECYCLER.get();
      var1.ensureCapacity(var0);
      return var1;
   }

   public RecyclableArrayList(Recycler.Handle var1, int var2) {
      super(var2);
      this.handle = var1;
   }

   @Override
   public boolean add(Object var1) {
      if (var1 == null) {
         throw new NullPointerException("element");
      } else {
         return super.add(var1);
      }
   }

   @Override
   public boolean addAll(int var1, Collection<?> var2) {
      checkNullElements(var2);
      return super.addAll(var1, var2);
   }

   public boolean recycle() {
      this.clear();
      return RECYCLER.recycle(this, this.handle);
   }

   @Override
   public boolean addAll(Collection<?> var1) {
      checkNullElements(var1);
      return super.addAll(var1);
   }

   public static RecyclableArrayList newInstance() {
      return newInstance(8);
   }

   public static void checkNullElements(Collection<?> var0) {
      if (var0 instanceof RandomAccess && var0 instanceof List) {
         List var4 = (List)var0;
         int var5 = var4.size();

         for (int var3 = 0; var3 < var5; var3++) {
            if (var4.get(var3) == null) {
               throw new IllegalArgumentException("c contains null values");
            }
         }
      } else {
         for (Object var2 : var0) {
            if (var2 == null) {
               throw new IllegalArgumentException("c contains null values");
            }
         }
      }
   }

   @Override
   public void add(int var1, Object var2) {
      if (var2 == null) {
         throw new NullPointerException("element");
      } else {
         super.add(var1, var2);
      }
   }
}
