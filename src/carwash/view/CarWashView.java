package carwash.view;

import java.util.Observable;

import carwash.event.CarWashEvent;
import carwash.event.StartEvent;
import carwash.event.StopEvent;
import carwash.state.CarWashState;
import simulator.event.Event;
import simulator.view.SimView;

public class CarWashView extends SimView {
	
	private CarWashState state;
	
	public CarWashView(CarWashState state) {
		super(state);
		setState(state);
	}
	
	public void setState(CarWashState state) {
		this.state = state;
	}
	
	public void printOut (CarWashState state, Event e) {
		if (e instanceof StartEvent)
		{
			System.out.println(String.format("Fast machines: %s",
					state.getNumFast()));
			System.out.println(String.format("Slow machines: %s",
					state.getNumSlow()));
			System.out.println(String.format("Fast distribution: (%s, %s)",
					state.getFastMin(),
					state.getFastMax()));
			System.out.println(String.format("Slow distribution: (%s, %s)",
					state.getSlowMin(),
					state.getSlowMax()));
			System.out.println(String.format(
					"Exponential distribution with lambda = %s",
					state.getLambda()));
			System.out.println(String.format("Seed = %s", state.getSeed()));
			System.out.println(String.format("Max Queue size: %s",
					state.getMaxQueueSize()));

			System.out.println("----------------------------------------");

			System.out.println("Time\tFast\tSlow\tId\tEvent\tIdleTime\tQueueTime\tQueueSize\tRejected");
			
			String reportLine = String.format(
					"%.2f\t%s\t%s\t-\t%s\t%s\t\t%.2f\t\t%s\t\t%s",
					e.getTime(), state.getFreeFast(),
					state.getFreeSlow(), e.getName(),
					state.getTotalIdleTime(), state.getTotalQueueTime(),
					state.getCarQueueSize(), state.getRejected());
			System.out.println(reportLine);
		}
		
		else if (e instanceof StopEvent) {
			String reportLine = String.format(
					"%.2f\t%s\t%s\t-\t%s\t%.2f\t\t%.2f\t\t%s\t\t%s",
					e.getTime(), state.getFreeFast(),
					state.getFreeSlow(), e.getName(),
					state.getTotalIdleTime(), state.getTotalQueueTime(),
					state.getCarQueueSize(), state.getRejected());
			System.out.println(reportLine);

			System.out.println("----------------------------------------");

			System.out.println(String.format("Total idle machine time: %.2f",
					state.getTotalIdleTime()));
			System.out.println(String.format("Total queueing time: %.2f",
					state.getTotalQueueTime()));
			System.out.println(String.format("Mean queueing time: %.2f",
					state.getMeanQueueTime()));
			System.out.println(String.format("Rejected cars: %s",
					state.getRejected()));
			System.out.println();
		}
		
		else if(e instanceof CarWashEvent)
		{
			e = ((CarWashEvent)e);
			String reportLine = String.format(
					"%.2f\t%s\t%s\t%s\t%s\t%.2f\t\t%.2f\t\t%s\t\t%s",
					e.getTime(), state.getFreeFast(),
					state.getFreeSlow(), ((CarWashEvent)e).getCar().getId(),
					e.getName(), state.getTotalIdleTime(),
					state.getTotalQueueTime(), state.getCarQueueSize(),
					state.getRejected());
			System.out.println(reportLine);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Event e = (Event)arg;
		printOut(state, e);
	}
}
