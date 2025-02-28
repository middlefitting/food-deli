package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.RiderApplication;
import untitled.domain.CookInfoAdded;
import untitled.domain.DeliveryCancelled;

@Entity
@Table(name = "Rider_table")
@Data
//<<< DDD / Aggregate Root
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderid;

    private String address;

    private String status;

    private String customerid;

    public static RiderRepository repository() {
        RiderRepository riderRepository = RiderApplication.applicationContext.getBean(
            RiderRepository.class
        );
        return riderRepository;
    }

    //<<< Clean Arch / Port Method
    public void accept(AcceptCommand acceptCommand) {
        //implement business logic here:

        DeliveryAccepted deliveryAccepted = new DeliveryAccepted(this);
        deliveryAccepted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void deliveryStart(DeliveryStartCommand deliveryStartCommand) {
        //implement business logic here:

        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void deliveryComplete(
        DeliveryCompleteCommand deliveryCompleteCommand
    ) {
        //implement business logic here:

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void addCookInfo(CookFinished cookFinished) {
        //implement business logic here:

        /** Example 1:  new item 
        Rider rider = new Rider();
        repository().save(rider);

        CookInfoAdded cookInfoAdded = new CookInfoAdded(rider);
        cookInfoAdded.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(cookFinished.get???()).ifPresent(rider->{
            
            rider // do something
            repository().save(rider);

            CookInfoAdded cookInfoAdded = new CookInfoAdded(rider);
            cookInfoAdded.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void deliveryCancel(CooCancelled cooCancelled) {
        //implement business logic here:

        /** Example 1:  new item 
        Rider rider = new Rider();
        repository().save(rider);

        DeliveryCancelled deliveryCancelled = new DeliveryCancelled(rider);
        deliveryCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(cooCancelled.get???()).ifPresent(rider->{
            
            rider // do something
            repository().save(rider);

            DeliveryCancelled deliveryCancelled = new DeliveryCancelled(rider);
            deliveryCancelled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
