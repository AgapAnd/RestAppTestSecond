package ru.agapov.restapptestsecond;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class testController {
    @ResponseBody
    @GetMapping("/reqres")
    public String resreq (@RequestParam("name") String name, @RequestParam("job") String job) {
        return "{\"name\":" + "\"" + name + "\"" + ", \"job\":" + "\"" + job + "\"" + "}";
    }
}
