package rw;

public class Particle1D implements Particle
{
	public static double getMean(final ParticleSwarm p_1dSwarm)
	{
		double value = 0;
		for (final Particle p : p_1dSwarm.getParticles())
		{
			value += (Double) p.getValue();
		}

		return value / p_1dSwarm.getSize();
	}

	private double m_value;

	public Particle1D(final double p_initialValue)
	{
		m_value = p_initialValue;
	}

	@Override
	public void applyFunction(final ParticleFunction p_function)
	{
		final Object output = p_function.getOutput(this);
		final double d = ((Double) output);
		m_value = d;
	}

	@Override
	public Object getValue()
	{
		return m_value;
	}
}
