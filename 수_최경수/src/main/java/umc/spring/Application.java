package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.UserService.UserQueryService;

import static umc.spring.domain.enums.MissionStatus.CHALLENGING;

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
			ReviewQueryService reviewService = context.getBean(ReviewQueryService.class);
			MissionQueryService missionService = context.getBean(MissionQueryService.class);

			String name = "요아정";
			Float score = 4.0f;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("Name: " + name);
//			System.out.println("Score: " + score);
//
//			storeService.findStoresByNameAndScore(name, score)
//							.forEach(System.out::println);

			// 6주차 미션 - 파라미터 값 설정
//			Long userId = 1L;
//			Long reviewId = 1L;
//			Long missionId = 2L;
//			Float reviews = 3.8f;
//			MissionStatus status = CHALLENGING;
//
//			// 6주차 미션 - 유저정보 출력
//			System.out.println(userSerivce.findMemberById(userId));
//
//			// 6주차 미션 - 리뷰정보 출력
//			System.out.println(reviewService.findReview(reviewId));
//			reviewService.findReviewsByScore(reviews)
//							.forEach(System.out::println);
//
//			// 6주차 미션 - 미션정보 출력
//			System.out.println(missionService.findMission(missionId));
//			missionService.findMissionByIdAndStatus(userId, status)
//							.forEach(System.out::println);
		};
	}

}
