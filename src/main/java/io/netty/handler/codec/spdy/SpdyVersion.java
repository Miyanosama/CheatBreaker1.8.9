package io.netty.handler.codec.spdy;

public enum SpdyVersion {
   SPDY_3_1(3, 1);

   public static SpdyVersion[] $VALUES = new SpdyVersion[]{SpdyVersion.SPDY_3_1};
   public int version;
   public int minorVersion;

   SpdyVersion(int var3, int var4) {
      this.version = var3;
      this.minorVersion = var4;
   }

   public int getVersion() {
      return this.version;
   }

   public int getMinorVersion() {
      return this.minorVersion;
   }
}
