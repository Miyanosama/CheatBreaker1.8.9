package io.netty.handler.codec.http.multipart;

public enum HttpPostRequestDecoder$MultiPartStatus {
   MIXEDPREAMBLE,
   MIXEDDELIMITER,
   CLOSEDELIMITER,
   FIELD,
   DISPOSITION,
   PREAMBLE,
   EPILOGUE,
   MIXEDFILEUPLOAD,
   MIXEDDISPOSITION,
   NOTSTARTED,
   HEADERDELIMITER,
   MIXEDCLOSEDELIMITER,
   FILEUPLOAD,
   PREEPILOGUE;
   // $VF: synthetic field
   public static HttpPostRequestDecoder$MultiPartStatus[] $VALUES = new HttpPostRequestDecoder$MultiPartStatus[]{
      HttpPostRequestDecoder$MultiPartStatus.NOTSTARTED,
      HttpPostRequestDecoder$MultiPartStatus.PREAMBLE,
      HttpPostRequestDecoder$MultiPartStatus.HEADERDELIMITER,
      HttpPostRequestDecoder$MultiPartStatus.DISPOSITION,
      HttpPostRequestDecoder$MultiPartStatus.FIELD,
      HttpPostRequestDecoder$MultiPartStatus.FILEUPLOAD,
      HttpPostRequestDecoder$MultiPartStatus.MIXEDPREAMBLE,
      HttpPostRequestDecoder$MultiPartStatus.MIXEDDELIMITER,
      HttpPostRequestDecoder$MultiPartStatus.MIXEDDISPOSITION,
      HttpPostRequestDecoder$MultiPartStatus.MIXEDFILEUPLOAD,
      HttpPostRequestDecoder$MultiPartStatus.MIXEDCLOSEDELIMITER,
      HttpPostRequestDecoder$MultiPartStatus.CLOSEDELIMITER,
      HttpPostRequestDecoder$MultiPartStatus.PREEPILOGUE,
      HttpPostRequestDecoder$MultiPartStatus.EPILOGUE
   };
}
