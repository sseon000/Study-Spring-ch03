package com.fastcampus.ch3.diCopy4;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fastcampus.ch3.diCopy4.AppContext;
import com.fastcampus.ch3.diCopy4.Car;
import com.fastcampus.ch3.diCopy4.Door;
import com.fastcampus.ch3.diCopy4.Engine;
import com.google.common.reflect.ClassPath;

@Component class Car {
	// @Autowired(by Type)
	/*
	@Autowired Engine engine;
	@Autowired Door door;
	*/
	
	// @Resource (by Name)
	@Resource Engine engine;
	// @Resource 
	Door door;
	
	@Override
	public String toString() {
		return "Car [engine=" + engine + ", door=" + door + "]";
	}
}
@Component class SportsCar extends Car {}
@Component class Truck extends Car {}
@Component class Engine {}
@Component class Door {}

class AppContext {
	Map map; // 객체 저장소

	AppContext() {
		map = new HashMap();
		doComponentScan();
		doAutowired();
		doResource();
	}
	
	private void doResource() {
		try {
			// map에 저장된 객체의 iv중에 @Resource가 붙어 있으면
			// map에서 iv의 이름에 맞는 객체를 찾아서 연결(객체의 주소를 iv저장)
			for(Object bean : map.values()) {
				for(Field fld : bean.getClass().getDeclaredFields()) {
					if(fld.getAnnotation(Resource.class) != null) { // by Name
						fld.set(bean, getBean(fld.getName())); // car.engine = obj;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void doAutowired() {
		try {
			// map에 저장된 객체의 iv중에 @Autowired가 붙어 있으면
			// map에서 iv의 타입에 맞는 객체를 찾아서 연결(객체의 주소를 iv저장)
			for(Object bean : map.values()) {
				for(Field fld : bean.getClass().getDeclaredFields()) {
					if(fld.getAnnotation(Autowired.class) != null) { // by Type
						fld.set(bean, getBean(fld.getType())); // car.engine = obj;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void doComponentScan() {
		try {
			// 1. 패키지내의 클래스 목록을 가져온다.
			ClassLoader classLoader = AppContext.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classLoader);
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");
			// 2. 반복문으로 클래스를 하나씩 읽어와서 @Component가 붙어 있는지 확인
			for(ClassPath.ClassInfo classInfo : set) {
				Class clazz = classInfo.load();
				Component component = (Component) clazz.getAnnotation(Component.class);
				// 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
				if(component != null) {
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
					map.put(id, clazz.newInstance());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// by Name
	Object getBean(String id) {
		return map.get(id);
	}
	
	// by Type
	Object getBean(Class clazz) {
		for(Object obj : map.values()) {
			if(clazz.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
}

public class Main4 {
	public static void main(String[] args) throws Exception {
		AppContext ac = new AppContext();
		Car car = (Car) ac.getBean("car");      // by Name으로 객체를 검색
		Engine engine = (Engine) ac.getBean("engine");
		Door door = (Door) ac.getBean(Door.class); // by Type으로 객체를 검색
		 
		// 수동으로 객체를 연결
		/*
		car.engine = engine;
		car.door = door;
		*/
		
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
		System.out.println("door = " + door);
	}
}
