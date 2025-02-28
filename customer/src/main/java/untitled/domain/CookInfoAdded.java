package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class CookInfoAdded extends AbstractEvent {

    private Long id;
    private String orderid;
    private String address;
    private String status;
    private String customerid;
}
