package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryCancelled extends AbstractEvent {

    private Long id;
    private String orderid;
    private String address;
    private String status;
    private String customerid;

    public DeliveryCancelled(Rider aggregate) {
        super(aggregate);
    }

    public DeliveryCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
