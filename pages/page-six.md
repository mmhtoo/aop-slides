---
layout: intro
---

<Text value="Logging Sample" />
<div grid="~ cols-2 gap-4" >
<div>
```java
@RestController
@RequestMapping( value = "/demo" )
@AllArgsConstructor
public class DemoController {
    @GetMapping
    public String sayHello(
            @RequestParam( value = "message" 
            , required = false ) String message
    ){
        return message == null ? "Hello!" : message;
    }
    @PostMapping( value = "/transfer" )
    public Response transfer(
            @RequestBody OwnTransferDto body
    ){
        // call transfer logic
        return Response.builder()
                .description(String.
                format("Successfully transfer %s from %s to %s.",
                body.getAmount(),
                body.getFromAccount(),
                body.getToAccount()))
                .status(HttpStatus.OK)
                .build();
    }
}
```
</div>
<div>
```java {all|1,3|2,5|6|7-9|7-15|16-19|16-24|25-27|25-45|all|false}
@Aspect 
@Component
@AllArgsConstructor
public class LoggingAspect {
    private final HttpServletRequest request;
    private final static Logger logger =  LoggerFactory.getLogger(LoggingAspect.class);
    @Before(
            value = "execution(* com.mmhtoo.demo.controller.*.*(..))"
    )
    public void useLogBeforeRun(JoinPoint joinPoint) throws IOException {
        logger.info("Target Class "+joinPoint.getTarget().getClass());
        logger.info("Before running Target Method"+joinPoint.getSignature().getName());
        logger.info("HTTP Method "+request.getMethod());
        logger.info("Request URL "+request.getRequestURL());
    }
    @AfterReturning(
            value = "execution(* com.mmhtoo.demo.controller.*.*(..))",
            returning = "returnVal"
    )
    public void useLogAfterRun(JoinPoint joinPoint,Object returnVal) throws IOException {
        logger.info("Target Class "+joinPoint.getTarget().getClass());
        logger.info("Before running Target Method"+joinPoint.getSignature().getName());
        logger.info("HTTP Method "+request.getMethod());
    }
    @Around(
            value = "execution(* com.mmhtoo.demo.controller.*.*(..))"
    )
    public Object useLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Target Class : "+joinPoint.getTarget().getClass());
        logger.info("Before running Target Method :  "+joinPoint.getSignature().getName());
        logger.info("HTTP Method : "+request.getMethod());
        logger.info("Request URL : "+request.getRequestURL());

        Object [] args = joinPoint.getArgs();
        Arrays.stream(args).forEach((arg) ->{
            if(arg != null) logger.info("Parameter "+arg.toString());
        });
        Object returnVal = joinPoint.proceed(args);

        logger.info("After running Target Method : "+joinPoint.getSignature().getName());
        logger.info("Result : "+returnVal.toString());

        return returnVal;
    }
}
```
</div>
</div>
