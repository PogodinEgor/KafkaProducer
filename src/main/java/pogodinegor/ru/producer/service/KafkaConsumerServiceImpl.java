package pogodinegor.ru.producer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pogodinegor.ru.producer.dto.EmployeeDTO;
import pogodinegor.ru.producer.dto.EmployeePhoneDTO;
import pogodinegor.ru.producer.dto.PhoneDTO;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private final KafkaSender<String, Object> kafkaSender;

    @Override
    public void sendPhoneDTO(PhoneDTO phoneDTO) {
        String topic = "phone-lists";
        kafkaSender.send(
                        Mono.just(
                                SenderRecord.create(
                                        topic,
                                        0,
                                        System.currentTimeMillis(),
                                        String.valueOf(phoneDTO.hashCode()),
                                        phoneDTO,
                                        null
                                )
                        )
                )
                .subscribe();
    }

    @Override
    public void sendEmployeeDTO(EmployeeDTO employeeDTO) {
        String topic = "employee-lists";
        kafkaSender.send(
                        Mono.just(
                                SenderRecord.create(
                                        topic,
                                        0,
                                        System.currentTimeMillis(),
                                        String.valueOf(employeeDTO.hashCode()),
                                        employeeDTO,
                                        null
                                )
                        )
                )
                .subscribe();
    }

    @Override
    public void sendEmployeePhoneLink(EmployeePhoneDTO employeePhoneLink) {
        String topic = "employeePhoneLink-lists";
        kafkaSender.send(
                        Mono.just(
                                SenderRecord.create(
                                        topic,
                                        0,
                                        System.currentTimeMillis(),
                                        String.valueOf(employeePhoneLink.hashCode()),
                                        employeePhoneLink,
                                        null
                                )
                        )
                )
                .subscribe();
    }


}
