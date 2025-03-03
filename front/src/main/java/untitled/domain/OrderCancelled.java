package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderCancelled extends AbstractEvent {

    private Long id;
    private String foodid;
    private String options;
    private String price;
    private String status;
    private String address;
    private String customerid;
    private String storeid;

    public OrderCancelled(Order aggregate) {
        super(aggregate);
    }

    public OrderCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
