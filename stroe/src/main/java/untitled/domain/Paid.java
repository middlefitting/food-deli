package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class Paid extends AbstractEvent {

    private Long id;
    private String price;
    private String ordereid;
    private String status;
}
