package shop.mtcoding.blog._core.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScriptTest {

    @Test
    public void back_test() {
        String result = Script.back("권한이 없어요");
        System.out.println("result = " + result);
    }

    @Test
    public void href_test() {
        String result = Script.href("/");
        System.out.println("result = " + result);
    }

    @Test
    public void href1_test() {
        String a = "틀림";
        String result = Script.href("/",a);
        System.out.println("result = " + result);
    }

}