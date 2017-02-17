package webservices;

import java.util.ArrayList;
import java.util.Date;

public class IntegerNumbers {
	private static ArrayList<IntObject> array;
	
	private static class IntObject {
		private int integer;
		private int powersOfTwo;
		private String createTime;
		
		IntObject(int a) {
			integer = a;
			powersOfTwo = 1;
			for (int i=0; i < a; i++) {
				powersOfTwo*=2;
			}
			createTime = (new Date()).toString();
		}
		
		int getInteger() {
			return integer;
		}
		
		int getPowersOfTwo() {
			return powersOfTwo;
		}
		
		String getCreateTime() {
			return createTime;
		}
	}
	
	static {
		array = new ArrayList<IntObject>();
	}	
	
	
	public static int createInteger() {
		int a = array.size();
		array.add(new IntObject(a));
		return a;
	}
	
	public static int getInteger(int a) {
		return array.get(a).getInteger();
	}
	
	public static int getPowersOfTwo(int a) {
		return array.get(a).getPowersOfTwo();
	}
	
	public static String getCreateTime(int a) {
		return array.get(a).getCreateTime();
	}

}
