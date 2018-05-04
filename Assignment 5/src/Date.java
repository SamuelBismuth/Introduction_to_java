public class Date {

	int day;
	int month;
	int year;
	private boolean valid = true;

	public Date(){
		this.day = 1;
		this.month = 01;
		this.year = 1000;
	}

	public Date(int day, int month, int year) {
		if((day < 32 && month < 13 && year < 10000) && (day > 0 && month > 0 && year > 999)){
			this.day = day;
			this.month = month;
			this.year = year;
		}
		else{
			System.out.println("Your date is not valid !!");
			valid = false;
			return;
		}
	}

	public Date(Date other){
		this.day = other.day;
		this.month = other.month;
		this.year = other.year;
	}

	public void setDate(int day, int month, int year){
		if((day < 32 && month < 13 && year < 10000) && (day > 0 && month > 0 && year > 999)){
			this.day = day;
			this.month = month;
			this.year = year;
		}
		else{
			System.out.println("Your date is not valid !!");
			valid = false;
			return;
		}
	}

	public String toString() {
		if(valid){
			if(day > 9 && month > 9){
				return "Date : " + day + "/" + month + "/" + year;
			}
			else if(day < 9 && month > 9){
				return "Date : 0" + day + "/" + month + "/" + year;
			}
			else if(day > 9 && month < 9){
				return "Date : " + day + "/0" + month + "/" + year;
			}
			else{
				return "Date : 0" + day + "/0" + month + "/" + year;
			}
		}
		else{
			valid = true;
			return "We cannot display your date !";
		}
	}
	
	public boolean leapYear(){
		if((this.year % 400 == 0) ||(this.year % 4 == 0 && this.year % 100 != 0)){
		return true;
		}
		return false;
	}

//	public static void main(String[] args){
//		Date date = new Date(26, 02, 1998);
//		System.out.println(date);
//		date.setDate(26, 03, 2038);
//		System.out.println(date);
//		Date date2 = new Date();
//		System.out.println(date2);
//		date2.setDate(10, 11, 1936);
//		System.out.println(date2);
//		Date date3 = new Date(date);
//		System.out.println(date3);
//		System.out.println(date3.leapYear());
//		System.out.println(date2.leapYear());
//	}

}
