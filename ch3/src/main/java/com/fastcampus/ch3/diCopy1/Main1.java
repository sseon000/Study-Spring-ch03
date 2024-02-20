package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car {}
class SportsCar extends Car {}
class Truck extends Car {}
class Engine {}

public class Main1 {
	public static void main(String[] args) throws Exception {
		// Car car = new SportsCar();
		// Car car = new Truck();
		// 생성자를 직접 바꾸지 않고 config.txt만 바꿔주면 됨
		Car car = getCar();
		System.out.println("car = " + car);
		
		// 키를 동적으로 변경하기 위해 Object타입으로 변경함 30line
		Car car2 = (Car)getObject("car");
		Engine engine = (Engine)getObject("engine");
		System.out.println("car2 = " + car2);
		System.out.println("engine = " + engine);
	}
	
	static Car getCar() throws Exception {
		Properties p = new Properties();
		p.load(new FileReader("config.txt"));
		
		Class clazz = Class.forName(p.getProperty("car"));
		
		return (Car)clazz.newInstance();
	}
	
	static Object getObject(String key) throws Exception {
		Properties p = new Properties();
		p.load(new FileReader("config.txt"));
		
		Class clazz = Class.forName(p.getProperty(key));
		
		return clazz.newInstance();
	}

}
