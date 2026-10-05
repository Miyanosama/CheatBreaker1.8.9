package io.netty.util;

import com.cheatbreaker.client.ui.overlay.element.DraggableElement;
import io.netty.handler.stream.ChunkedWriteHandler$3;
import io.netty.util.concurrent.FastThreadLocal;
import net.minecraft.world.gen.structure.MapGenVillage$Start;
import net.optifine.shaders.gui.GuiButtonEnumShaderOption$1;
import org.newsclub.net.unix.AFUNIXSocketImpl$AFUNIXOutputStream;
import recovered.unidentified.UnidentifiedClass3379;

public class Recycler$1 extends FastThreadLocal<Recycler$Stack<T>> {
   public AFUNIXSocketImpl$AFUNIXOutputStream __junk8408427723967633991;
   public DraggableElement __junk6029467255158600025;
   public ChunkedWriteHandler$3 __junk6552152788723191572;
   public GuiButtonEnumShaderOption$1 __junk2012363948767657651;
   public UnidentifiedClass3379 __junk3698868707549918575;
   public MapGenVillage$Start __junk7760501514070710445;

   public Recycler$Stack<T> initialValue() {
      return new Recycler$Stack(this.this$0, Thread.currentThread(), Recycler.access$000(this.this$0));
   }

   public Recycler$1(Recycler var1) {
      this.this$0 = var1;
      super();
   }
}
