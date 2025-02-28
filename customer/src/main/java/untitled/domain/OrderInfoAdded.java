package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class OrderInfoAdded extends AbstractEvent {

    private Long id;
    private String foodid;
    private String options;
    private String status;
    private String orderid;
    private String address;
    private String customerid;
}
