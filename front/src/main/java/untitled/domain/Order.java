package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.FrontApplication;
import untitled.domain.OrderCancelled;
import untitled.domain.OrderPlaced;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String foodid;

    private String options;

    private String price;

    private String status;

    private String address;

    private String customerid;

    private String storeid;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = FrontApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateStatus(DeliveryAccepted deliveryAccepted) {
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        Paid paid = new Paid(order);
        paid.publishAfterCommit();
        Accepted accepted = new Accepted(order);
        accepted.publishAfterCommit();
        CookStarted cookStarted = new CookStarted(order);
        cookStarted.publishAfterCommit();
        CookFinished cookFinished = new CookFinished(order);
        cookFinished.publishAfterCommit();
        Accepted accepted = new Accepted(order);
        accepted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(deliveryAccepted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            Paid paid = new Paid(order);
            paid.publishAfterCommit();
            Accepted accepted = new Accepted(order);
            accepted.publishAfterCommit();
            CookStarted cookStarted = new CookStarted(order);
            cookStarted.publishAfterCommit();
            CookFinished cookFinished = new CookFinished(order);
            cookFinished.publishAfterCommit();
            Accepted accepted = new Accepted(order);
            accepted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(DeliveryStarted deliveryStarted) {
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        Paid paid = new Paid(order);
        paid.publishAfterCommit();
        Accepted accepted = new Accepted(order);
        accepted.publishAfterCommit();
        CookStarted cookStarted = new CookStarted(order);
        cookStarted.publishAfterCommit();
        CookFinished cookFinished = new CookFinished(order);
        cookFinished.publishAfterCommit();
        Accepted accepted = new Accepted(order);
        accepted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(deliveryStarted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            Paid paid = new Paid(order);
            paid.publishAfterCommit();
            Accepted accepted = new Accepted(order);
            accepted.publishAfterCommit();
            CookStarted cookStarted = new CookStarted(order);
            cookStarted.publishAfterCommit();
            CookFinished cookFinished = new CookFinished(order);
            cookFinished.publishAfterCommit();
            Accepted accepted = new Accepted(order);
            accepted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(DeliveryCompleted deliveryCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        Paid paid = new Paid(order);
        paid.publishAfterCommit();
        Accepted accepted = new Accepted(order);
        accepted.publishAfterCommit();
        CookStarted cookStarted = new CookStarted(order);
        cookStarted.publishAfterCommit();
        CookFinished cookFinished = new CookFinished(order);
        cookFinished.publishAfterCommit();
        Accepted accepted = new Accepted(order);
        accepted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(deliveryCompleted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            Paid paid = new Paid(order);
            paid.publishAfterCommit();
            Accepted accepted = new Accepted(order);
            accepted.publishAfterCommit();
            CookStarted cookStarted = new CookStarted(order);
            cookStarted.publishAfterCommit();
            CookFinished cookFinished = new CookFinished(order);
            cookFinished.publishAfterCommit();
            Accepted accepted = new Accepted(order);
            accepted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void storeReject(Rejected rejected) {
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        

        repository().findById(rejected.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
