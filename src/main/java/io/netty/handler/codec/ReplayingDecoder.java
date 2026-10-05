package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Signal;
import io.netty.util.internal.RecyclableArrayList;
import io.netty.util.internal.StringUtil;
import java.util.List;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import junit.awtui.TestRunner$8;

public abstract class ReplayingDecoder<S> extends ByteToMessageDecoder {
   public S state;
   public static Signal REPLAY = Signal.valueOf(ReplayingDecoder.class.getName() + ".REPLAY");
   public int checkpoint;
   public ReplayingDecoderBuffer replayable = new ReplayingDecoderBuffer();

   public S state(S var1) {
      Object var2 = this.state;
      this.state = (S)var1;
      return (S)var2;
   }

   public ReplayingDecoder() {
      this(null);
   }

   public S state() {
      return this.state;
   }

   public ReplayingDecoder(S var1) {
      this.checkpoint = -1;
      this.state = (S)var1;
   }

   public void checkpoint(S var1) {
      this.checkpoint();
      this.state((S)var1);
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      RecyclableArrayList var2 = RecyclableArrayList.newInstance();
      boolean var39 = false /* VF: Semaphore variable */;

      label324: {
         try {
            var39 = true;
            this.replayable.terminate();
            this.callDecode(var1, this.internalBuffer(), var2);
            this.decodeLast(var1, this.replayable, var2);
            var39 = false;
            break label324;
         } catch (Signal var43) {
            var43.expect(REPLAY);
            var39 = false;
         } catch (DecoderException var44) {
            throw var44;
         } catch (Exception var45) {
            throw new DecoderException(var45);
         } finally {
            if (var39) {
               try {
                  if (this.cumulation != null) {
                     this.cumulation.release();
                     this.cumulation = null;
                  }

                  int var8 = var2.size();

                  for (int var9 = 0; var9 < var8; var9++) {
                     var1.fireChannelRead(var2.get(var9));
                  }

                  if (var8 > 0) {
                     var1.fireChannelReadComplete();
                  }

                  var1.fireChannelInactive();
               } finally {
                  var2.recycle();
               }
            }
         }

         try {
            if (this.cumulation != null) {
               this.cumulation.release();
               this.cumulation = null;
            }

            int var3 = var2.size();

            for (int var4 = 0; var4 < var3; var4++) {
               var1.fireChannelRead(var2.get(var4));
            }

            if (var3 > 0) {
               var1.fireChannelReadComplete();
            }

            var1.fireChannelInactive();
            return;
         } finally {
            var2.recycle();
         }
      }

      try {
         if (this.cumulation != null) {
            this.cumulation.release();
            this.cumulation = null;
         }

         int var47 = var2.size();

         for (int var48 = 0; var48 < var47; var48++) {
            var1.fireChannelRead(var2.get(var48));
         }

         if (var47 > 0) {
            var1.fireChannelReadComplete();
         }

         var1.fireChannelInactive();
      } finally {
         var2.recycle();
      }
   }

   public void checkpoint() {
      this.checkpoint = this.internalBuffer().readerIndex();
   }

   @Override
   public void callDecode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      this.replayable.setCumulation(var2);

      try {
         while (var2.isReadable()) {
            int var4 = this.checkpoint = var2.readerIndex();
            int var5 = var3.size();
            Object var6 = this.state;
            int var7 = var2.readableBytes();

            try {
               this.decode(var1, this.replayable, var3);
               if (var1.isRemoved()) {
                  break;
               }

               if (var5 == var3.size()) {
                  if (var7 == var2.readableBytes() && var6 == this.state) {
                     throw new DecoderException(
                        StringUtil.simpleClassName(this.getClass())
                           + ".decode() must consume the inbound "
                           + "data or change its state if it did not decode anything."
                     );
                  }
                  continue;
               }
            } catch (Signal var10) {
               var10.expect(REPLAY);
               if (!var1.isRemoved()) {
                  int var9 = this.checkpoint;
                  if (var9 >= 0) {
                     var2.readerIndex(var9);
                  }
               }
               break;
            }

            if (var4 == var2.readerIndex() && var6 == this.state) {
               throw new DecoderException(
                  StringUtil.simpleClassName(this.getClass())
                     + ".decode() method must consume the inbound data "
                     + "or change its state if it decoded something."
               );
            }

            if (this.isSingleDecode()) {
               break;
            }
         }
      } catch (DecoderException var11) {
         throw var11;
      } catch (Throwable var12) {
         throw new DecoderException(var12);
      }
   }
}
