package booker;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class BaseBookerTest {

    private static final Moshi MOSHI = new Moshi.Builder().build();

    @BeforeClass
    public static void before() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
        RestAssured
                .filters(new io.restassured.filter.log.RequestLoggingFilter(),
                        new io.restassured.filter.log.ResponseLoggingFilter());
    }

    @AfterClass
    public static void after() {
        RestAssured.basePath = "";
        RestAssured.responseSpecification = null;
    }

    @SneakyThrows(IOException.class)
    public static <T> T fromJson(String json, Class<T> targetPojo) {
        return MOSHI
                .adapter(targetPojo)
                .lenient()
                .fromJson(json);
    }

    public static <T> String toJson(T object, Class<T> type) {
        return MOSHI
                .adapter(type)
                .lenient()
                .toJson(object);
    }

    public static <T> List<T> listFromJson(String json, Class<T> type) throws IOException {
        Type gistListType = Types.newParameterizedType(List.class, type);
        JsonAdapter<List<T>> adapter = MOSHI.adapter(gistListType);

        return adapter.lenient().fromJson(json);
    }

    public static File getJsonSchema(String schemaPath) {
        ClassLoader classLoader = BaseBookerTest.class.getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(schemaPath)).getFile());
    }

}
