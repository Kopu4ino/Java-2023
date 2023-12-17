package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
@SuppressWarnings({"InnerTypeLast", "UncommentedMain", "MagicNumber", "MultipleStringLiterals"})
public class AccessMethodsBenchmark {

    private Student student;
    private Method getNameMethod;
    private MethodHandle nameMethodHandle;
    private CallSite lambdaCallSite;
    private Supplier<String> nameSupplier;

    record Student(String firstName, String lastName) {
    }

    @Setup
    public void setup() throws Throwable {
        student = new Student("Artur", "Imbergenov");
        getNameMethod = student.getClass().getMethod("firstName");

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType returnedType = MethodType.methodType(String.class);
        nameMethodHandle = lookup.findVirtual(Student.class, "firstName", returnedType);

        lambdaCallSite = LambdaMetafactory.metafactory(
            lookup,
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            nameMethodHandle,
            MethodType.methodType(String.class)
        );

        nameSupplier = (Supplier<String>) lambdaCallSite.getTarget().invokeExact(student);
    }

    @Benchmark
    public void directMethodAccess(Blackhole bh) {
        String name = student.firstName();
        bh.consume(name);
    }

    @Benchmark
    public void reflectionMethodAccess(Blackhole bh) throws Throwable {
        String name = (String) getNameMethod.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandleAccess(Blackhole bh) throws Throwable {
        String name = (String) nameMethodHandle.invokeExact(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactoryAccess(Blackhole bh) {
        String name = nameSupplier.get();
        bh.consume(name);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(AccessMethodsBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(60))
            .build();

        new Runner(options).run();
    }
}
