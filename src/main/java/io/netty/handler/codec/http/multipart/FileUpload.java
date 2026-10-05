package io.netty.handler.codec.http.multipart;

public interface FileUpload extends HttpData {
   void setContentTransferEncoding(String var1);

   String getContentTransferEncoding();

   void setContentType(String var1);

   void setFilename(String var1);

   String getFilename();

   FileUpload duplicate();

   FileUpload copy();

   FileUpload retain();

   String getContentType();

   FileUpload retain(int var1);
}
