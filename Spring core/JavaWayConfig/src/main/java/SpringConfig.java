import com.guru.CourseModel.Course;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.guru")
public class SpringConfig {

//    @Bean("course1")
//    public Course getCourse(){
//        Course course = Course.builder()
//                .courseID(100)
//                .courseDescription("User Friendly")
//                .courseName("python Full stack")
//                .courseDuration("6 months")
//                .build();
//        return course;
//    }

    @Bean("course1")
    public Course getCourse(){
        Course course=new Course();
        course.setCourseID(100);
        course.setCourseDescription("User Friendly");
        course.setCourseDuration("6 months");
        course.setCourseName("python Full stack");
        return course;
    }
}
