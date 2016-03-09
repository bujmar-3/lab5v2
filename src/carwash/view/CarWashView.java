package carwash.view;

import java.util.Observable;
import carwash.event.StartEvent;
import carwash.event.StopEvent;
import simulator.view.SimView;
import simulator.event.Event;
import carwash.state.CarWashState;;

public class CarWashView extends SimView {
	
	private CarWashState state;
	
	public CarWashView(CarWashState state) {
		setState(state);
	}
	
	public void setState(CarWashState state) {
		this.state = state;
	}
	
	public void update (Observable o, Event e) {
		if (state.state())
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
					state.getCarQueueSize()));

			System.out.println("----------------------------------------");

			System.out
					.println("Time\tFast\tSlow\tId\tEvent\tIdleTime\tQueueTime\tQueueSize\tRejected");
			
			String reportLine = String.format(
					"%.2f\t%s\t%s\t-\t%s\t%s\t\t%.2f\t\t%s\t\t%s",
					e.getTime(), state.getFreeFast(),
					state.getFreeSlow(), e,
					state.getTotalIdleTime(), state.getTotalQueueTime(),
					state.getCarQueueSize(), state.getRejected());
			System.out.println(reportLine);
		}
		
		else if (!state.state()) {
			String reportLine = String.format(
					"%.2f\t%s\t%s\t-\t%s\t%.2f\t\t%.2f\t\t%s\t\t%s",
					e.getTime(), state.getFreeFast(),
					state.getFreeSlow(), e,
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
		
		else
		{
			String reportLine = String.format(
					"%.2f\t%s\t%s\t%s\t%s\t%.2f\t\t%.2f\t\t%s\t\t%s",
					e.getTime(), state.getFreeFast(),
					//TODO getCar()
					state.getFreeSlow(), e.getCar(),
					e, state.getTotalIdleTime(),
					state.getTotalQueueTime(), state.getCarQueueSize(),
					state.getRejected());
			System.out.println(reportLine);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		update(arg0, arg1);
		
	}
		
}
