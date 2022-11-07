package fun.pigeondog.theater.chain.controller;

import com.alibaba.fastjson2.JSONObject;
import fun.pigeondog.common.annotation.CommonResultAdvice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 * 测试用
 * @author yzguo
 * @date 2022/10/16 13:34
 */
@Api(tags = "测试类")
@RestController
@CommonResultAdvice
@RequestMapping("/test")
public class TestController {

    @ApiOperation("测试获取字符串")
    @GetMapping("/str")
    public JSONObject test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data","hello");

        //throw new RuntimeException();
        return jsonObject;
    }
}
