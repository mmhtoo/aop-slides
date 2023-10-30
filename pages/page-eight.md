---
layout: intro
---

<Text value="Retry Mechanism" />

<div>
```java {all|25-39|all|false}
@Aspect
@Component
public class ValidationCheckAspect {
    private final Logger logger = LoggerFactory.getLogger(ValidationCheckAspect.class);

    @Around(
            value = "@annotation(com.mmhtoo.demo.annotation.CheckValidation) && args(..)"
    )
    public Object useCheckValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object [] args = joinPoint.getArgs();
        BindingResult bindingResult = (BindingResult) args[1];

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                sb.append(String.format("%s - %s",fieldError.getField(),fieldError.getDefaultMessage()));
            });
            return Response
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .description(sb.toString())
                    .build();
        }
        int currentTry = 0;
        int MAX_TRY = 5;
        do{
            try{
                return joinPoint.proceed(args);
            }catch(Exception e){
               logger.error(e.getMessage());
                currentTry++;
            }
        }while(currentTry < MAX_TRY);
        return Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .description("System Fail!")
                .build();
    }
}
```
</div>