package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PayCancelled extends AbstractEvent {

    private Long id;
    private String price;
    private String ordereid;
    private String status;

    public PayCancelled(Payment aggregate) {
        super(aggregate);
    }

    public PayCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
