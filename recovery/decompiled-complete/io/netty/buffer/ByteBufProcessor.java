package io.netty.buffer;

public interface ByteBufProcessor {
   ByteBufProcessor FIND_NON_LF = new ByteBufProcessor$6();
   ByteBufProcessor FIND_NON_CR = new ByteBufProcessor$4();
   ByteBufProcessor FIND_LF = new ByteBufProcessor$5();
   ByteBufProcessor FIND_CRLF = new ByteBufProcessor$7();
   ByteBufProcessor FIND_NON_CRLF = new ByteBufProcessor$8();
   ByteBufProcessor FIND_NON_LINEAR_WHITESPACE = new ByteBufProcessor$10();
   ByteBufProcessor FIND_NUL = new ByteBufProcessor$1();
   ByteBufProcessor FIND_CR = new ByteBufProcessor$3();
   ByteBufProcessor FIND_NON_NUL = new ByteBufProcessor$2();
   ByteBufProcessor FIND_LINEAR_WHITESPACE = new ByteBufProcessor$9();

   boolean process(byte var1);
}
