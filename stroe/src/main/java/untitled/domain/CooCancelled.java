package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class CooCancelled extends AbstractEvent {

    private Long id;
    private String foodid;
    private String options;
    private String status;
    private String orderid;
    private String address;
    private String customerid;

    public CooCancelled(Cooking aggregate) {
        super(aggregate);
    }

    public CooCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
