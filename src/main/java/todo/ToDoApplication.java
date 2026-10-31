package todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//メイン関数を持つクラス
@SpringBootApplication
public class ToDoApplication {

	// /アドレスのgetMappingにつなぐ
	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}

}
