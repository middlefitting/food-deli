package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryAccepted extends AbstractEvent {

    private Long id;
    private String orderid;
    private String address;
    private String status;
    private String customerid;

    public DeliveryAccepted(Rider aggregate) {
        super(aggregate);
    }

    public DeliveryAccepted() {
        super();
    }
}
//>>> DDD / Domain Event
