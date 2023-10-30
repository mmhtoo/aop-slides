---
layout: intro
---

<Text value="Validation Check" />

<div grid="~ cols-2 gap-4">
<div>
```java
@CheckValidation
@PostMapping( value = "/send-email" )
public Response sendEmail(
  @Valid @RequestBody SendEmailDto dto,
  BindingResult bindingResult
) throws Exception {
  emailService.sendEmail(dto.getToEmail(), dto.getContent());

    return Response.builder()
      .description(String
      .format("Successfully send email to %s ",dto.getToEmail()))
      .status(HttpStatus.OK)
      .build();
    }
}
@Data
@ToString
class SendEmailDto {
    @NotNull( message = "To email is required!")
    @NotEmpty( message = "To email is required!")
    @Email( message = "Invalid email format!")
    private String toEmail;

    @NotNull( message = "Content is required!")
    @NotEmpty( message = "Content is required!")
    @Size( min = 5 , max = 1024 , message = "Minimum is 5 and maximum is 1024 characters!")
    private String content;
}

```
</div>
<div>
```java {all|1-2|5-7|27-31|5-26|all|false}
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
        return joinPoint.proceed(args);
    }
}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckValidation {
}

```
</div>
</div>