package pogodinegor.ru.producer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "ProducerKafka",
                description = "Микросервис 'ProducerKafka' предназначен отправки сгенерированных данных с последующей отправкой их в другой сервис, для последующего сохранения в бд. "
                        ,
                version = "0.0.1",
                contact = @Contact(
                        name = "Pogodin Egor",
                        email = "666deadkain999@gmail.com",
                        url = "https://github.com/PogodinEgor"
                )
        )
)
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
