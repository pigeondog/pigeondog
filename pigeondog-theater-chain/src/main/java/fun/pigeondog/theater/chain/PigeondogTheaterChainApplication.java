package fun.pigeondog.theater.chain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PigeondogTheaterChainApplication
 *
 * @author yzguo
 * @date 2022/11/3 15:02
 */

@SpringBootApplication(scanBasePackages={"fun.pigeondog.**"})
public class PigeondogTheaterChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(PigeondogTheaterChainApplication.class, args);
    }
}
