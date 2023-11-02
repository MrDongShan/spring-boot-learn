package com.mrdongshan.hello.world.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController 为 @ResponseBody + @Controller ，其作用是将该类标识为controller类，并将其注入到spring容器中
@RestController
public class DemoController {
    /**
     * Hello，World
     *
     * @param who 参数，非必须
     * @return Hello, ${who}
     */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false, name = "who") String who) {
        if (StrUtil.isBlank(who)) {
            who = "World";
        }
        return StrUtil.format("Hello, {}!", who);
        // 1.类添加注解 @RestController
        // 2.创建方法添加 @GetMapping("路径") 等用于定义HTTP请求的注解
        // 3.访问 localhost:端口号(默认8080)/路径  如本例中：GET http://localhost:8080/hello
    }

}
