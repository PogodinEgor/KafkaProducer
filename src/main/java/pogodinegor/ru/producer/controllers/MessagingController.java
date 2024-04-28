package pogodinegor.ru.producer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pogodinegor.ru.producer.dto.EmployeeDTO;
import pogodinegor.ru.producer.dto.EmployeePhoneDTO;
import pogodinegor.ru.producer.dto.PhoneDTO;
import pogodinegor.ru.producer.service.KafkaConsumerServiceImpl;

@Tag(name = "Messaging Controller", description = "Контроллер генерации и отправки данных в очередь сообщений.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessagingController {

    private final KafkaConsumerServiceImpl kafkaConsumerService;


    @Operation(summary = "Генерация телефона", description = "Генерация телефона и последующая отправка данных в очередь.")
    @PostMapping("/phone/send")
    public ResponseEntity<?> sendPhoneDTO(@RequestBody PhoneDTO phoneDTO) {
        if (!isValidPhoneDTO(phoneDTO)) {
            return ResponseEntity.badRequest().body("Не удалось отправить сообщение!");
        }
        kafkaConsumerService.sendPhoneDTO(phoneDTO);
        return ResponseEntity.ok().body("Сообщение успешно отправлено.");

    }

    @Operation(summary = "Генерация сотрудника", description = "Генерация сотрудника и последующая отправка данных в очередь.")
    @PostMapping("/employee/send")
    public ResponseEntity<?> sendEmployeeDTO(@RequestBody EmployeeDTO employeeDTO) {
        if(!isValidEmployeeDTO(employeeDTO)){
            return ResponseEntity.badRequest().body("Не удалось отправить сообщение!");

        }
        kafkaConsumerService.sendEmployeeDTO(employeeDTO);
        return ResponseEntity.ok().body("Сообщение успешно отправлено.");
    }

    @Operation(summary = "Привязка сотрудника к телефону", description = "Введите id сотрудника и телефона что бы привязать их.")
    @PostMapping("/employee-phone/send")
    public ResponseEntity<?> sendEmployeePhoneLink(@RequestBody EmployeePhoneDTO employeePhoneDTO) {
        if(!isValidEmployeePhoneDTO(employeePhoneDTO)){
            return ResponseEntity.badRequest().body("Не удалось отправить сообщение!");

        }
        kafkaConsumerService.sendEmployeePhoneLink(employeePhoneDTO);
        return ResponseEntity.ok().body("Сообщение успешно отправлено.");
    }


    private boolean isValidPhoneDTO(PhoneDTO phoneDTO) {
        return phoneDTO.getNumber() != null && !phoneDTO.getNumber().isEmpty();
    }

    private boolean isValidEmployeeDTO(EmployeeDTO employeeDTO) {
        return employeeDTO.getFullName() != null && !employeeDTO.getFullName().isEmpty();
    }

    private boolean isValidEmployeePhoneDTO(EmployeePhoneDTO employeePhoneDTO) {
        return employeePhoneDTO.getEmployeeId() != null && employeePhoneDTO.getPhoneId() != null;
    }

}
