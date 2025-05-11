package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.UserService.UserQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context){
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);
			UserQueryService userSerivce = context.getBean(UserQueryService.class);

			// 파라미터 값 설정
			Long userId = 1L;
			String name = "요아정";
			Float score = 4.0f;

			// 유저정보 출력
			System.out.println(userSerivce.findMemberById(userId));
			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("Name: " + name);
//			System.out.println("Score: " + score);
//
//			storeService.findStoresByNameAndScore(name, score)
//							.forEach(System.out::println);
		};
	}

}
