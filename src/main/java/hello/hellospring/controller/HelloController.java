package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello"; // templates/hello.html을 실행시켜라
    }

    // 파라미터를 사용하는 경우. ?name="이름 적기"  ,,, MVC 방법 이용 ( viewResolver 로 넘긴다 . )
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    // ResponseBody : body 부분에 직접 넣어준다. ( 많이 안 쓰임!! )
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // 요청한 클라이언트에 그냥 무식하게 들어간다. ( 문자일 경우- StringConverter )
    }

    // 결과가 json 방식으로 나온다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello=new Hello();
        hello.setName(name);
        return hello;   // 객체일 경우 기본 디폴트가 json 방식으로 데이타를 만들어서 반환하겠다!! ( 객체일 경우 - JsonConverter )
    }

     static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}