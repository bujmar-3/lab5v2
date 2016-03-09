package simulator;

import carwash.event.StartEvent;
import carwash.event.StopEvent;
import carwash.state.CarWashState;
import simulator.event.EventQueue;
/**
 * @author Martin Björklund, Arvid Persson, Emil Lilja.
 * MainSim creates an CarWashState, Simulator and an EventQueue.
 * */
public class MainSim {
	/**
	 * Main method fist method to be called.
	 * Takes arguments that should be used in the simulation.
	 * @param args Stop time, max car queue size, number of fast carwashes, number of slow carwashes, 
	 * minimum distribution used for fast carwashes, max distribution used for fast carwashes, minimum distribution used for slow carwashes, 
	 * max distribution used for slow carwashes, lambda used for random number generator, seed used for random number generator.
	 * */
	public static void main(String[] args){
		if (args.length == 10){
			double stopTime = Double.parseDouble(args[0]);
			int maxQueue = Integer.parseInt(args[1]);
			int fastWash = Integer.parseInt(args[2]);
			int slowWash = Integer.parseInt(args[3]);
			double fastMin = Double.parseDouble(args[4]);
			double fastMax = Double.parseDouble(args[5]);
			double slowMin = Double.parseDouble(args[6]);
			double slowMax = Double.parseDouble(args[7]);
			double lambda = Double.parseDouble(args[8]);
			long seed = Long.parseLong(args[9]);
			
			CarWashState state = new CarWashState();
			EventQueue queue = new EventQueue();
			Simulator simulator = new Simulator(state, queue);
			
			state.createRandom(fastMax, fastMin, slowMax, slowMin, lambda, seed);
			state.createWashes(slowWash, fastWash);
			state.setMaxQueueSize(maxQueue);
			
			queue.insert(new StartEvent());
			queue.insert(new StopEvent(stopTime));
			
			simulator.execute();
		}else{
			System.out.println("Please enter arguments in the following order: StopTime, Max car queue size, number of fast washes, number of slow washes, ");
			System.out.println("minimum distribution used for fast carwashes, max distribution used for fast carwashes, minimum distribution used for slow carwashes,");
			System.out.println("max distribution used for slow carwashes, lambda used for random number generator, seed used for random number generator.");
		}
	}
}
