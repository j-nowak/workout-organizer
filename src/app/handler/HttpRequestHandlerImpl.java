package handler;

import play.http.HttpRequestHandler;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import java.lang.reflect.Method;

public class HttpRequestHandlerImpl implements HttpRequestHandler {
    private static final String RES_HEADER_CORS_ALLOW_ORIGIN_KEY = "Access-Control-Allow-Origin";
    private static final String PF_RES_HEADER_CORS_ALLOW_METHODS_KEY = "Access-Control-Allow-Methods";
    private static final String PF_RES_HEADER_CORS_ALLOW_METHODS_VALUE = "<HTTP_METHODS_TO_BE_ALLOWED>";
    private static final String PF_RES_HEADER_CORS_ALLOW_HEADERS_KEY = "Access-Control-Allow-Headers";
    private static final String PF_RES_HEADER_CORS_ALLOW_HEADERS_VALUE = "<HEADERS_TO_BE_ALLOWED_IN_REQUEST>";
    private static final String RES_HEADER_CONTENT_TYPE_VALUE = "<RESPONSE_CONTENT_TYPE>";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

    private static final String PRE_FLIGHT_METHOD = "OPTIONS";
    @Override
    public Action createAction(Http.Request request, Method method) {
        return new Action() {
            @Override
            public F.Promise<Result> call(Http.Context context) throws Throwable {
                String origin = context.request().getHeader("origin");
                origin = origin == null ? "*" : origin;

                context.response().setHeader(RES_HEADER_CORS_ALLOW_ORIGIN_KEY, origin);
                context.response().setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                if(context.request().method().equals(PRE_FLIGHT_METHOD)) {
                    context.response().setHeader(PF_RES_HEADER_CORS_ALLOW_METHODS_KEY, PF_RES_HEADER_CORS_ALLOW_METHODS_VALUE);
                    context.response().setHeader(PF_RES_HEADER_CORS_ALLOW_HEADERS_KEY, PF_RES_HEADER_CORS_ALLOW_HEADERS_VALUE);
                    return delegate.call(context);
                }
                context.response().setContentType(RES_HEADER_CONTENT_TYPE_VALUE);
                return delegate.call(context);
            }
        };
    }
    @Override
    public Action wrapAction(Action action) {
        return action;
    }
}