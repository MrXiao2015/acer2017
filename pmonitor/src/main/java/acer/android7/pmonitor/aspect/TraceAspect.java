package acer.android7.pmonitor.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MemberSignature;

import acer.android7.pmonitor.internal.DebugLog;
import acer.android7.pmonitor.internal.TimeWatch;

/**
 * Created by xiaozheng on 2017/2/18.
 */
@Aspect
public class TraceAspect {
    private static final String POINTCUT_METHOD =
            "execution(@acer.android7.pmonitor.annotation.DebugTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@acer.android7.pmonitor.annotation.DebugTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {
    }

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MemberSignature memberSignature = (MemberSignature) joinPoint.getSignature();
        String className = memberSignature.getDeclaringType().getSimpleName();
        String methodName = memberSignature.getName();

        final TimeWatch timeWatch = new TimeWatch();

        timeWatch.start();
        Object result = joinPoint.proceed();
        timeWatch.end();

        DebugLog.log(className, buildLogMessage(methodName, timeWatch.getTotalTime()));

        return result;
    }

    private static String buildLogMessage(String methodName, long methodDuration) {
        StringBuilder message = new StringBuilder();

        message.append("Pmonitor --> ");
        message.append(methodName);
        message.append(" --> ");
        message.append("[");
        message.append(methodDuration);
        message.append("ms");
        message.append("]");

        return message.toString();
    }
}
