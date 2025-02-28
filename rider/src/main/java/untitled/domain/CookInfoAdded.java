package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class CookInfoAdded extends AbstractEvent {

    private Long id;
    private String orderid;
    private String address;
    private String status;
    private String customerid;

    public CookInfoAdded(Rider aggregate) {
        super(aggregate);
    }

    public CookInfoAdded() {
        super();
    }
}
//>>> DDD / Domain Event
