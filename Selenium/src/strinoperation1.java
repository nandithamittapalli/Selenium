
public class strinoperation1 {
public static void main(String[] args) {
	String arr="nanditha";
	int count=0;
	for(int i=0;i<arr.length();i++) {
		if(arr.charAt(i)=='n') {
			count++;
		}
	}
	System.out.println(count);
}
}
