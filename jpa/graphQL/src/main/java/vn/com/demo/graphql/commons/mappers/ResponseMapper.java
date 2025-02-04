package vn.com.demo.graphql.commons.mappers;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import vn.com.demo.graphql.commons.dto.BaseResponse;

import java.util.List;

@UtilityClass
public class ResponseMapper {

    public static <T> BaseResponse<List<T>> mapResponseToResponseEntity(Page<T> response) {
        BaseResponse.MetaData metaData = BaseResponse.MetaData.builder()
                .page(response.getNumber())
                .size(response.getSize())
                .build();

        return BaseResponse.<List<T>>builder()
                .data(response.getContent())
                .metaData(metaData)
                .build();
    }

    public static <T> BaseResponse<T> mapResponseToResponseEntity(T response) {
        return BaseResponse.<T>builder()
                .data(response)
                .build();
    }

}
