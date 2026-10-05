package io.netty.util.internal;

import io.netty.channel.socket.oio.OioSocketChannel;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.dispenser.IBehaviorDispenseItem$1;
import net.minecraft.item.ItemTool;
import recovered.unidentified.UnidentifiedClass1256;

public abstract class OneTimeTask extends MpscLinkedQueueNode<Runnable> implements Runnable {
   public IBehaviorDispenseItem$1 __junk1313712125177683680;
   public UnidentifiedClass1256 __junk8456423251447836101;
   public OioSocketChannel __junk9064002876616992660;
   public EntityNotFoundException __junk3786458674110320843;
   public ItemTool __junk5701513804706860373;

   public Runnable value() {
      return this;
   }
}
