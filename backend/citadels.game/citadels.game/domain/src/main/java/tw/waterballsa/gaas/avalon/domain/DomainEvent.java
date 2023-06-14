package tw.waterballsa.gaas.avalon.domain;

/**
 * @author johnny@waterballsa.tw
 */
public class DomainEvent {
    protected String name;

    public DomainEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
