package rw;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ParticleSwarmMainController
{
	private static final int NUM_PARTICLES = 1000000;

	private static final double		ALPHA		= 1;
	private static final double		B			= .1;
	private static final double		VI_INTERVAL	= 20;
	private static final double		DELTA_T		= .01;
	private static final DecayType	DECAY_TYPE	= DecayType.HYPERBOLIC;

	public static void main(final String[] p_args)
	{
		final Random random = new Random();

		final ParticleSwarm swarm = new ParticleSwarm();
		for (int i = 0; i < NUM_PARTICLES; i++)
		{
			final Particle1D p = new Particle1D(random.nextDouble());
			swarm.add(p);
		}

		final JFrame frame = new JFrame();
		final HistogramPanel panel = new HistogramPanel();
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		final ParticleFunction pf = new MazurRWFunction(B, ALPHA, 1 / VI_INTERVAL, DELTA_T, DECAY_TYPE);

		while (true)
		{
			panel.showHistogram(Histogram.convertToHistogram(swarm, 0, 1, 740));
			swarm.applyToAll(pf);
		}
	}
}