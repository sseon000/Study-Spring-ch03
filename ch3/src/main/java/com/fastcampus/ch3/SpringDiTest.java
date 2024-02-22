//package com.fastcampus.ch3;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//// @Component("engine")에서 ("engine")(Class명에 앞글자 소문자가 생략) == <bean id="engine" class="com.fastcampus.ch3.Engine />
//@Component class Engine {}
//@Component class TurboEngine extends Engine {}
//@Component class SuperEngine extends Engine {}
//@Component class Door {}
//@Component class Car {
//	@Value("red") String color;
//	@Value("100") int oil;
//	@Autowired
//	// @Qualifier("superEngine") - by Type 여러개 중 택 1 매칭하는 법 / @Autowired + @Qualifier(by Type + matching) vs @Resource (by Name)
//	Engine engine; // by Type <-> getBean이 by Type이 여러개면 에러지만 @Autowried는 by Type검색으로 여러개(engine, superEngine, turboEngine) 검색시 engine 변수명을 by Name 사용해서 매칭
//	@Autowired Door[] doors;
//	
//	public Car() {} // 기본 생성자를 꼭 만들어주자
//	public Car(String color, int oil, Engine engine, Door[] doors) {
//		super();
//		this.color = color;
//		this.oil = oil;
//		this.engine = engine;
//		this.doors = doors;
//	}
//	public String getColor() {
//		return color;
//	}
//	public void setColor(String color) {
//		this.color = color;
//	}
//	public int getOil() {
//		return oil;
//	}
//	public void setOil(int oil) {
//		this.oil = oil;
//	}
//	public Engine getEngine() {
//		return engine;
//	}
//	public void setEngine(Engine engine) {
//		this.engine = engine;
//	}
//	public Door[] getDoors() {
//		return doors;
//	}
//	public void setDoors(Door[] doors) {
//		this.doors = doors;
//	}
//	@Override
//	public String toString() {
//		return "Car [color=" + color + ", oil=" + oil + ", engine=" + engine + ", doors=" + Arrays.toString(doors)
//				+ "]";
//	}
//}
//
//
//public class SpringDiTest {
//	public static void main(String[] args) {
//		ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//		
//		Car car = (Car)ac.getBean("car"); // by Name
////		Car car2 = ac.getBean("car", Car.class); // by Name, 타입정보를 param으로 주고 형변환을 생략하는 방법
////		Car car3 = (Car)ac.getBean(Car.class); // by Type
////		Engine engine = (Engine)ac.getBean("engine"); // by Name
//		// Engine engine = (Engine)ac.getBean("superEngine"); // by Name
//		// Engine engine = (Engine)ac.getBean("TurboEngine"); // by Name
//		// Engine engine = (Engine)ac.getBean(Engine.class); // by Type << 같은 타입이 여러개 일 경우엔 Error -> engine, superEngine, turboEngine 
//		// Door door = (Door)ac.getBean("door");
//		// 1. 직접 setter로 설정
////		car.setColor("red");
////		car.setOil(100);
////		car.setEngine(engine);
////		car.setDoors(new Door[] { ac.getBean("door", Door.class), ac.getBean("door", Door.class), ac.getBean("door", Door.class), ac.getBean("door", Door.class) });
//		// 2. config.xml에 설정 : src/main/resource/config.xml
//		
//		System.out.println("car = " + car);
//	}
//}
