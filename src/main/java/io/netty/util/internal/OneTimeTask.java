package io.netty.util.internal;

import io.netty.channel.socket.oio.OioSocketChannel;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.item.ItemTool;
import org.davidmoten.text.utils.CharSequenceConcatRightTrim$1;

public abstract class OneTimeTask extends MpscLinkedQueueNode<Runnable> implements Runnable {

   public Runnable value() {
      return this;
   }
}
