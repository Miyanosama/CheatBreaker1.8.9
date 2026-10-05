package io.netty.handler.codec.rtsp;

import io.netty.channel.AbstractServerChannel;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.minecraft.network.handshake.client.C00Handshake;
import com.cheatbreaker.client.emote.type.FlossEmote$EnumSwitch;

public class RtspResponseStatuses {
   public static HttpResponseStatus CONTINUE = HttpResponseStatus.CONTINUE;
   public static HttpResponseStatus OK = HttpResponseStatus.OK;
   public static HttpResponseStatus CREATED = HttpResponseStatus.CREATED;
   public static HttpResponseStatus LOW_STORAGE_SPACE = new HttpResponseStatus(250, "Low on Storage Space");
   public static HttpResponseStatus MULTIPLE_CHOICES = HttpResponseStatus.MULTIPLE_CHOICES;
   public static HttpResponseStatus MOVED_PERMANENTLY = HttpResponseStatus.MOVED_PERMANENTLY;
   public static HttpResponseStatus MOVED_TEMPORARILY = new HttpResponseStatus(302, "Moved Temporarily");
   public static HttpResponseStatus NOT_MODIFIED = HttpResponseStatus.NOT_MODIFIED;
   public static HttpResponseStatus USE_PROXY = HttpResponseStatus.USE_PROXY;
   public static HttpResponseStatus BAD_REQUEST = HttpResponseStatus.BAD_REQUEST;
   public static HttpResponseStatus UNAUTHORIZED = HttpResponseStatus.UNAUTHORIZED;
   public static HttpResponseStatus PAYMENT_REQUIRED = HttpResponseStatus.PAYMENT_REQUIRED;
   public static HttpResponseStatus FORBIDDEN = HttpResponseStatus.FORBIDDEN;
   public static HttpResponseStatus NOT_FOUND = HttpResponseStatus.NOT_FOUND;
   public static HttpResponseStatus METHOD_NOT_ALLOWED = HttpResponseStatus.METHOD_NOT_ALLOWED;
   public static HttpResponseStatus NOT_ACCEPTABLE = HttpResponseStatus.NOT_ACCEPTABLE;
   public static HttpResponseStatus PROXY_AUTHENTICATION_REQUIRED = HttpResponseStatus.PROXY_AUTHENTICATION_REQUIRED;
   public static HttpResponseStatus REQUEST_TIMEOUT = HttpResponseStatus.REQUEST_TIMEOUT;
   public static HttpResponseStatus GONE = HttpResponseStatus.GONE;
   public static HttpResponseStatus LENGTH_REQUIRED = HttpResponseStatus.LENGTH_REQUIRED;
   public static HttpResponseStatus PRECONDITION_FAILED = HttpResponseStatus.PRECONDITION_FAILED;
   public static HttpResponseStatus REQUEST_ENTITY_TOO_LARGE = HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE;
   public static HttpResponseStatus REQUEST_URI_TOO_LONG = HttpResponseStatus.REQUEST_URI_TOO_LONG;
   public static HttpResponseStatus UNSUPPORTED_MEDIA_TYPE = HttpResponseStatus.UNSUPPORTED_MEDIA_TYPE;
   public static HttpResponseStatus PARAMETER_NOT_UNDERSTOOD = new HttpResponseStatus(451, "Parameter Not Understood");
   public static HttpResponseStatus CONFERENCE_NOT_FOUND = new HttpResponseStatus(452, "Conference Not Found");
   public static HttpResponseStatus NOT_ENOUGH_BANDWIDTH = new HttpResponseStatus(453, "Not Enough Bandwidth");
   public static HttpResponseStatus SESSION_NOT_FOUND = new HttpResponseStatus(454, "Session Not Found");
   public static HttpResponseStatus METHOD_NOT_VALID = new HttpResponseStatus(455, "Method Not Valid in This State");
   public static HttpResponseStatus HEADER_FIELD_NOT_VALID = new HttpResponseStatus(456, "Header Field Not Valid for Resource");
   public static HttpResponseStatus INVALID_RANGE = new HttpResponseStatus(457, "Invalid Range");
   public static HttpResponseStatus PARAMETER_IS_READONLY = new HttpResponseStatus(458, "Parameter Is Read-Only");
   public static HttpResponseStatus AGGREGATE_OPERATION_NOT_ALLOWED = new HttpResponseStatus(459, "Aggregate operation not allowed");
   public static HttpResponseStatus ONLY_AGGREGATE_OPERATION_ALLOWED = new HttpResponseStatus(460, "Only Aggregate operation allowed");
   public static HttpResponseStatus UNSUPPORTED_TRANSPORT = new HttpResponseStatus(461, "Unsupported transport");
   public static HttpResponseStatus DESTINATION_UNREACHABLE = new HttpResponseStatus(462, "Destination unreachable");
   public static HttpResponseStatus KEY_MANAGEMENT_FAILURE = new HttpResponseStatus(463, "Key management failure");
   public static HttpResponseStatus INTERNAL_SERVER_ERROR = HttpResponseStatus.INTERNAL_SERVER_ERROR;
   public static HttpResponseStatus NOT_IMPLEMENTED = HttpResponseStatus.NOT_IMPLEMENTED;
   public static HttpResponseStatus BAD_GATEWAY = HttpResponseStatus.BAD_GATEWAY;
   public static HttpResponseStatus SERVICE_UNAVAILABLE = HttpResponseStatus.SERVICE_UNAVAILABLE;
   public static HttpResponseStatus GATEWAY_TIMEOUT = HttpResponseStatus.GATEWAY_TIMEOUT;
   public static HttpResponseStatus RTSP_VERSION_NOT_SUPPORTED = new HttpResponseStatus(505, "RTSP Version not supported");
   public static HttpResponseStatus OPTION_NOT_SUPPORTED = new HttpResponseStatus(551, "Option not supported");

   public static HttpResponseStatus valueOf(int var0) {
      switch (var0) {
         case 250:
            return LOW_STORAGE_SPACE;
         case 302:
            return MOVED_TEMPORARILY;
         case 451:
            return PARAMETER_NOT_UNDERSTOOD;
         case 452:
            return CONFERENCE_NOT_FOUND;
         case 453:
            return NOT_ENOUGH_BANDWIDTH;
         case 454:
            return SESSION_NOT_FOUND;
         case 455:
            return METHOD_NOT_VALID;
         case 456:
            return HEADER_FIELD_NOT_VALID;
         case 457:
            return INVALID_RANGE;
         case 458:
            return PARAMETER_IS_READONLY;
         case 459:
            return AGGREGATE_OPERATION_NOT_ALLOWED;
         case 460:
            return ONLY_AGGREGATE_OPERATION_ALLOWED;
         case 461:
            return UNSUPPORTED_TRANSPORT;
         case 462:
            return DESTINATION_UNREACHABLE;
         case 463:
            return KEY_MANAGEMENT_FAILURE;
         case 505:
            return RTSP_VERSION_NOT_SUPPORTED;
         case 551:
            return OPTION_NOT_SUPPORTED;
         default:
            return HttpResponseStatus.valueOf(var0);
      }
   }
}
