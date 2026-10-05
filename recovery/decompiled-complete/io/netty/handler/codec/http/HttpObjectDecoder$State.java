package io.netty.handler.codec.http;

import net.minecraft.world.gen.feature.WorldGenTrees;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$23;

public enum HttpObjectDecoder$State {
   READ_CHUNK_DELIMITER,
   UPGRADED,
   READ_CHUNKED_CONTENT,
   READ_CHUNK_SIZE,
   SKIP_CONTROL_CHARS,
   READ_VARIABLE_LENGTH_CONTENT,
   BAD_MESSAGE,
   READ_INITIAL,
   READ_CHUNK_FOOTER,
   READ_HEADER,
   READ_FIXED_LENGTH_CONTENT;
   // $VF: synthetic field
   public static HttpObjectDecoder$State[] $VALUES = new HttpObjectDecoder$State[]{
      SKIP_CONTROL_CHARS,
      READ_INITIAL,
      HttpObjectDecoder$State.READ_HEADER,
      READ_VARIABLE_LENGTH_CONTENT,
      HttpObjectDecoder$State.READ_FIXED_LENGTH_CONTENT,
      READ_CHUNK_SIZE,
      READ_CHUNKED_CONTENT,
      READ_CHUNK_DELIMITER,
      HttpObjectDecoder$State.READ_CHUNK_FOOTER,
      BAD_MESSAGE,
      UPGRADED
   };
   public WorldGenTrees __junk7569021024932299954;
   public LogBrokerMonitor$23 __junk1845453785532860464;
}
