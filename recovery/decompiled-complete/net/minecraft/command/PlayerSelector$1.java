package net.minecraft.command;

import com.google.common.base.Predicate;
import io.netty.util.internal.RecyclableArrayList$1;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$CounterCell;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Bootstrap$15;
import net.minecraft.network.status.client.C01PacketPing;
import recovered.unidentified.UnidentifiedClass0877;

public class PlayerSelector$1 implements Predicate<Entity> {
   public RecyclableArrayList$1 field_0003;
   public ModelPlayer field_0006;
   public Bootstrap$15 field_0002;
   public C01PacketPing field_0000;
   public ConcurrentHashMapV8$CounterCell field_0001;
   public UnidentifiedClass0877 field_0007;

   public PlayerSelector$1(String var1, boolean var2) {
      this.field_0005 = var1;
      this.field_0004 = var2;
      super();
   }

   public boolean method_04124(Entity var1) {
      return EntityList.isStringEntityName(var1, this.field_0005) != this.field_0004;
   }
}
