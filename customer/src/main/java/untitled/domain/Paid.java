package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import untitled.infra.AbstractEvent;

@Data
public class Paid extends AbstractEvent {

    private Long id;
    private String price;
    private String ordereid;
    private String status;
}
