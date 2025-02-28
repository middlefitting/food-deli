package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.StroeApplication;
import untitled.domain.CooCancelled;
import untitled.domain.OrderInfoAdded;
import untitled.domain.StatusUpdated;

@Entity
@Table(name = "Cooking_table")
@Data
//<<< DDD / Aggregate Root
public class Cooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String foodid;

    private String options;

    private String status;

    private String orderid;

    private String address;

    private String customerid;

    public static CookingRepository repository() {
        CookingRepository cookingRepository = StroeApplication.applicationContext.getBean(
            CookingRepository.class
        );
        return cookingRepository;
    }

    //<<< Clean Arch / Port Method
    public void accept(AcceptCommand acceptCommand) {
        //implement business logic here:

        Accepted accepted = new Accepted(this);
        accepted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void reject(RejectCommand rejectCommand) {
        //implement business logic here:

        Rejected rejected = new Rejected(this);
        rejected.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void cookStart(CookStartCommand cookStartCommand) {
        //implement business logic here:

        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void cookFinish(CookFinishCommand cookFinishCommand) {
        //implement business logic here:

        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void addOrderInfo(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        OrderInfoAdded orderInfoAdded = new OrderInfoAdded(cooking);
        orderInfoAdded.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(orderPlaced.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);

            OrderInfoAdded orderInfoAdded = new OrderInfoAdded(cooking);
            orderInfoAdded.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updatePayStatus(Paid paid) {
        //implement business logic here:

        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        StatusUpdated statusUpdated = new StatusUpdated(cooking);
        statusUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if paid.paymentId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<, Object> paymentMap = mapper.convertValue(paid.getPaymentId(), Map.class);

        repository().findById(paid.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);

            StatusUpdated statusUpdated = new StatusUpdated(cooking);
            statusUpdated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void cancelOrder(OrderCancelled orderCancelled) {
        //implement business logic here:

        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        CooCancelled cooCancelled = new CooCancelled(cooking);
        cooCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(orderCancelled.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);

            CooCancelled cooCancelled = new CooCancelled(cooking);
            cooCancelled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
