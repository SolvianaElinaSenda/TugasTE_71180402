import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
    static Pajak pajak;

    @BeforeAll
    static void init(){
       System.out.println("Sebelum dieksekusi");
        pajak=new Pajak();

    }
    @AfterAll
    static void destroy(){
        System.out.println("Setelah semua diekseskusi");
        pajak=null;
    }

    // Equivalence class
    @ParameterizedTest
    @MethodSource("providePajak")
    void parameterizedTestPajak(float expected,double salary){
        assertEquals(expected,pajak.getPajak(salary));
    }
    private static Stream<Arguments> providePajak(){
        return  Stream.of(
                Arguments.of(0,3500000),
                Arguments.of(10,4500000),
                Arguments.of(22,15500000),
                Arguments.of(40,40000001),
                Arguments.of(-1,-4000000)
        );
    }

    // Boundary Value Analysis
    //Ini untuk Pajak 0%
    private static Stream<Arguments> BVAPajak(){
        return  Stream.of(
                Arguments.of(false,39999000),
                Arguments.of(false,40000000),
                Arguments.of(false,40000001)
        );
    }
    @ParameterizedTest
    @MethodSource("BVAPajak")
    void parameterizedTestBVAPajak(boolean expected,double salary){
        assertEquals(expected,pajak.getPajak(salary)==0);
    }

    //Ini untuk Pajak 10%
    private static Stream<Arguments> BVAPajak10(){
        return  Stream.of(
                Arguments.of(false,39999000),
                Arguments.of(false,40000000),
                Arguments.of(false,40000001)
        );
    }
    @ParameterizedTest
    @MethodSource("BVAPajak10")
    void parameterizedTestBVAPajak10(boolean expected,double salary){
        assertEquals(expected,pajak.getPajak(salary)==10);
    }
    //Ini untuk Pajak 22%
    private static Stream<Arguments> BVAPajak22(){
        return  Stream.of(
                Arguments.of(true,39999000),
                Arguments.of(true,40000000),
                Arguments.of(false,40000001)
        );
    }
    @ParameterizedTest
    @MethodSource("BVAPajak22")
    void parameterizedTestBVAPajak22(boolean expected,double salary){
        assertEquals(expected,pajak.getPajak(salary)==22);
    }

    //Ini untuk Pajak 40%
    private static Stream<Arguments> BVAPajak40(){
        return  Stream.of(
                Arguments.of(false,39999000),
                Arguments.of(false,40000000),
                Arguments.of(true,40000001)
        );
    }
    @ParameterizedTest
    @MethodSource("BVAPajak40")
    void parameterizedTestBVAPajak40(boolean expected,double salary){
        assertEquals(expected,pajak.getPajak(salary)==40);
    }
}
