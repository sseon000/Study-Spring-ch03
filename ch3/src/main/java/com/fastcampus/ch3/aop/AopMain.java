package com.fastcampus.ch3.aop;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

public class AopMain {
	public static void main(String[] args) throws Exception {
		MyAdvice myAdvide = new MyAdvice();
		
		Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
		Object obj = myClass.newInstance();
		
		for(Method m : myClass.getDeclaredMethods()) {
			myAdvide.invoke(m, obj, null);
		}
	}
}

class MyAdvice {
	Pattern p = Pattern.compile("a.*");
	
	// 1. 패턴 매치를 이용해 특정 메서드 호출
	boolean matches(Method m) {
		Matcher matcher = p.matcher(m.getName());
		return matcher.matches();
	}
	
	void invoke(Method m, Object obj, Object... args) throws Exception {
		// 1. 패턴 매치를 이용해 특정 메서드 호출
		if(matches(m)) { 
			System.out.println("[before]{");
		}
		
		m.invoke(obj, args); // aaa(), aaa2(), aaa3()
		
		// 2. 어노테이션 여부를 이용해 특정 메서드 호출 
		if(m.getAnnotation(Transactional.class)!=null) {
			System.out.println("}[after]");
		}
	}
}

class MyClass {
	void aaa() {
		System.out.println("aaa() is callid");
	}
	
	void aaa2() {
		System.out.println("aaa2() is callid");
	}
	
	@Transactional
	void bbb() {
		System.out.println("bbb() is callid");
	}
}