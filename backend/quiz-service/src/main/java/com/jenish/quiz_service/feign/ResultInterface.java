package com.jenish.quiz_service.feign;

import com.jenish.quiz_service.model.RequestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("RESULT-SERVICE")
public interface ResultInterface {
    @PostMapping("result/{id}")
    public Boolean addResult(@PathVariable Integer id, @RequestBody RequestResult requestResult);
}
