public class Clock {
	
	int hours;
	int minutes;
	int seconds;
	private boolean valid = true;

	public Clock() {
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}

	public Clock(Clock other) {
		this.hours = other.hours;
		this.minutes = other.minutes;
		this.seconds = other.seconds;
	}

	public Clock(int hours, int minutes, int seconds) {
		if((hours < 24 && minutes < 60 && seconds < 60) && (hours >= 0 && minutes >= 0 && seconds >= 0)){
			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
		}
		else{
			System.out.println("Your clock is not valid !!");
			valid = false;
			return;
		}
	}

	public void setClock(int hours, int minutes, int seconds) {
		if((hours < 24 && minutes < 60 && seconds < 60) && (hours >= 0 && minutes >= 0 && seconds >= 0)){
			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
		}
		else{
			System.out.println("Your clock is not valid !!");
			valid = false;
			return;
		}
	}

	public String toString() {
		if(valid){
			return "Clock [hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + "]";
		}
		else{
			valid = true;
			return "We cannot display your clock !";
		}
	}

	public void addSeconds(int seconds) {
		this.seconds = this.seconds + seconds;
		while(this.seconds > 59){
			this.seconds = this.seconds - 60;
			this.minutes = this.minutes + 1;
		}
		while(this.minutes > 59){
			this.minutes = this.minutes - 60;
			this.hours = this.hours + 1;
		}
		if(this.hours > 23){
			System.out.println("Your new clock is not valid !!");
			valid = false;
			return;
		}
	}
	
	public int diff(int hours, int minutes, int seconds){
		hours = hours * 3600;
		minutes = minutes * 60;
		int sum1 = hours + minutes + seconds;
		this.hours = this.hours * 3600;
		this.minutes = this.minutes * 60;
		int sum2 = this.hours + this.minutes + this.seconds;
		return Math.abs(sum1 - sum2);
	}

//	public static void main(String[] args) {
//		Clock time = new Clock(22, 45, 36);
//		System.out.println(time);
//		time.setClock(2, 50, 2);
//		System.out.println(time);
//		time.addSeconds(77939);
//		System.out.println(time);
//		Clock time2 = new Clock();
//		System.out.println(time2);
//		time2.setClock(10, 15, 35);
//		System.out.println(time2);
//		Clock time3 = new Clock(time);
//		System.out.println(time3);
//		System.out.println(time2.diff(9, 14, 35));
//	}

}
