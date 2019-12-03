package Models;

public class Request<T> {
    private String route;

    private T data;

    public Request (String route, T data) {
        this.route = route;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
