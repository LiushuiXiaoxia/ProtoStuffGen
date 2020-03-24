package cn.mycommons.stuff.core.gen;


import cn.mycommons.stuff.core.IGenerateRule;

public class UnderlineGenerateImplTest {

    public static void main(String[] args) {
        IGenerateRule rule = new UnderlineGenerateImpl();
        System.out.println(rule.gen("hello"));
        System.out.println(rule.gen("helloWorld"));
        System.out.println(rule.gen("HelloWorld"));
        System.out.println(rule.gen("mHelloWorld"));
    }
}