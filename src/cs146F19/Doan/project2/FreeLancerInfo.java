package cs146F19.Doan.project2;

public class FreeLancerInfo {
	int departing_date;
	int arrive_date;
	int max_earning;
	
	public FreeLancerInfo()
	{
		
	}
	
	public FreeLancerInfo(int d, int a, int m)
	{
		this.departing_date = d;
		this.arrive_date = a;
		this.max_earning = m;
	}
	
	public void FreeLancerDisplayInfo()
	{
		System.out.println(max_earning);
		System.out.println(departing_date);
		System.out.println(arrive_date);
		System.out.println();
	}
	
	public int getDeparting_date() {
		return departing_date;
	}
	public void setDeparting_date(int departing_date) {
		this.departing_date = departing_date;
	}
	public int getArrive_date() {
		return arrive_date;
	}
	public void setArrive_date(int arrive_date) {
		this.arrive_date = arrive_date;
	}
	public int getMax_earning() {
		return max_earning;
	}
	public void setMax_earning(int max_earning) {
		this.max_earning = max_earning;
	}
	
	
}
