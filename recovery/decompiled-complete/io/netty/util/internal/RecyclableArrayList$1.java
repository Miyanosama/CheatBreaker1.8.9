package io.netty.util.internal;

import com.cheatbreaker.client.ui.mainmenu.element.IconButtonElement;
import io.netty.buffer.ByteBufProcessor$4;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.block.BlockHugeMushroom$EnumType;
import net.minecraft.command.server.CommandScoreboard;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;

public class RecyclableArrayList$1 extends Recycler<RecyclableArrayList> {
   public IconButtonElement __junk2232984135191504167;
   public ModifiableAttributeInstance __junk125363876697623938;
   public BlockHugeMushroom$EnumType __junk9024483861584653612;
   public CommandScoreboard __junk6691134704683539899;
   public ByteBufProcessor$4 __junk5775932642667405682;

   public RecyclableArrayList newObject(Recycler$Handle var1) {
      return new RecyclableArrayList(var1, null);
   }
}
