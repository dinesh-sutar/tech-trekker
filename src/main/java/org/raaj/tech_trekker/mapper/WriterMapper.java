package org.raaj.tech_trekker.mapper;

import org.raaj.tech_trekker.dto.WriterRequest;
import org.raaj.tech_trekker.model.WriterInfo;
import org.springframework.beans.BeanUtils;

public class WriterMapper {
    public static WriterInfo convertRequest(WriterRequest request) {
        var writerInfo = new WriterInfo();
        // binds the data from writerrequest to writerinfo where the variable and
        // data-type are same in both.
        BeanUtils.copyProperties(request, writerInfo);
        return writerInfo;
    }
}

// object reference
