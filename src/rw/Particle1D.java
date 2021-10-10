package rw;

public class Particle1D implements Particle
{
	public static double getMean(ParticleSwarm p_1dSwarm)
	{
		double value = 0;
		for (final Particle p : p_1dSwarm.getParticles())
		{
			value += (Double)p.getValue();
		}

		return value / p_1dSwarm.getSize();
	}

	private double m_value;

	public Particle1D(double p_initialValue)
	{
		m_value = p_initialValue;
	}

	@Override
	public void applyFunction(ParticleFunction p_function)
	{
		final Object output = p_function.getOutput(this);
		final double d = ((Double)output).doubleValue();
		m_value = d;
	}

	@Override
	public Object getValue()
	{
		return m_value;
	}
}
