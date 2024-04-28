package pogodinegor.ru.producer.service;

import pogodinegor.ru.producer.dto.EmployeeDTO;
import pogodinegor.ru.producer.dto.EmployeePhoneDTO;
import pogodinegor.ru.producer.dto.PhoneDTO;


public interface KafkaConsumerService {
    void sendPhoneDTO(PhoneDTO phoneDTO);

    void sendEmployeeDTO(EmployeeDTO employeeDTO);

    void sendEmployeePhoneLink(EmployeePhoneDTO employeePhoneLink);

}
