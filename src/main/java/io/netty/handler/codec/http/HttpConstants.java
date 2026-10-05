package io.netty.handler.codec.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.client.model.ModelEnderMite;
import org.apache.log4j.pattern.FileLocationPatternConverter;

public class HttpConstants {
   public static final byte LF = 10;
   public static final byte CR = 13;
   public static final byte SP = 32;
   public static final byte SEMICOLON = 59;
   public static final byte EQUALS = 61;
   public static final byte DOUBLE_QUOTE = 34;
   public static final byte HT = 9;
   public static final byte COMMA = 44;
   public static final byte COLON = 58;
   public static Charset DEFAULT_CHARSET = CharsetUtil.UTF_8;
}
