package io.netty.handler.codec.http;

// $VF: synthetic class
public class HttpObjectDecoder$1 {
   static {
      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.SKIP_CONTROL_CHARS.ordinal()] = 1;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.READ_INITIAL.ordinal()] = 2;
      } catch (NoSuchFieldError var10) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.READ_HEADER.ordinal()] = 3;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.READ_VARIABLE_LENGTH_CONTENT.ordinal()] = 4;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.READ_FIXED_LENGTH_CONTENT.ordinal()] = 5;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.READ_CHUNK_SIZE.ordinal()] = 6;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.READ_CHUNKED_CONTENT.ordinal()] = 7;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.READ_CHUNK_DELIMITER.ordinal()] = 8;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.READ_CHUNK_FOOTER.ordinal()] = 9;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.BAD_MESSAGE.ordinal()] = 10;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpObjectDecoder$State[HttpObjectDecoder$State.UPGRADED.ordinal()] = 11;
      } catch (NoSuchFieldError var1) {
      }
   }
}
