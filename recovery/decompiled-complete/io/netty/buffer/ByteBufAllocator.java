package io.netty.buffer;

public interface ByteBufAllocator {
   ByteBufAllocator DEFAULT = ByteBufUtil.DEFAULT_ALLOCATOR;

   boolean isDirectBufferPooled();

   CompositeByteBuf compositeBuffer();

   CompositeByteBuf compositeDirectBuffer(int var1);

   ByteBuf buffer();

   ByteBuf directBuffer();

   ByteBuf heapBuffer(int var1, int var2);

   CompositeByteBuf compositeHeapBuffer();

   ByteBuf ioBuffer(int var1, int var2);

   ByteBuf buffer(int var1, int var2);

   CompositeByteBuf compositeDirectBuffer();

   ByteBuf directBuffer(int var1);

   ByteBuf heapBuffer(int var1);

   CompositeByteBuf compositeHeapBuffer(int var1);

   ByteBuf ioBuffer(int var1);

   CompositeByteBuf compositeBuffer(int var1);

   ByteBuf heapBuffer();

   ByteBuf directBuffer(int var1, int var2);

   ByteBuf ioBuffer();

   ByteBuf buffer(int var1);
}
