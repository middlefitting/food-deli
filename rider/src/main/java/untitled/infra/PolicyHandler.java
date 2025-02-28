package untitled.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import untitled.config.kafka.KafkaProcessor;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    RiderRepository riderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CookFinished'"
    )
    public void wheneverCookFinished_AddCookInfo(
        @Payload CookFinished cookFinished
    ) {
        CookFinished event = cookFinished;
        System.out.println(
            "\n\n##### listener AddCookInfo : " + cookFinished + "\n\n"
        );

        // Sample Logic //
        Rider.addCookInfo(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CooCancelled'"
    )
    public void wheneverCooCancelled_DeliveryCancel(
        @Payload CooCancelled cooCancelled
    ) {
        CooCancelled event = cooCancelled;
        System.out.println(
            "\n\n##### listener DeliveryCancel : " + cooCancelled + "\n\n"
        );

        // Sample Logic //
        Rider.deliveryCancel(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
