package hh.homework.taskManager;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("hh/homework/taskManager/controllers")
public class TaskManagerConfig implements WebMvcConfigurer {
}
