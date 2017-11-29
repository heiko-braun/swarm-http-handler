package foo.bar;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

public class HeaderFilter implements HttpHandler {

    public HeaderFilter(HttpHandler next) {

        this.next = next;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.addResponseCommitListener(exchange1 -> {
            exchange1.getResponseHeaders().add(HttpString.tryFromString("x-custom-header"), "value");
        });
        next.handleRequest(exchange);
    }

    private HttpHandler next;
}
