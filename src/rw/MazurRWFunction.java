package rw;

import java.util.Random;

public class MazurRWFunction implements ParticleFunction
{
	private final Random	m_random;

	private final double	m_b;
	private final double	m_alpha;
	private final double	m_r;
	private final double	m_deltaT;
	private final DecayType	m_decayType;

	public MazurRWFunction(double p_b, double p_alpha, double p_r, double p_deltaT, DecayType p_decayType)
	{
		m_random = new Random();

		m_b = p_b;
		m_alpha = p_alpha;
		m_r = p_r;
		m_deltaT = p_deltaT;
		m_decayType = p_decayType;
	}

	private boolean coinflip(double p_r)
	{
		return m_random.nextDouble() <= p_r;
	}

	@Override
	public Object getOutput(Particle p_p)
	{
		final Particle1D p1D = (Particle1D)p_p;
		double value = (Double)p1D.getValue();

		if (coinflip(m_r * m_deltaT))
		{
			// reinforcer arrives

			value = value + m_alpha * (1 - value);
		}
		else
		{
			// no reinforcer - hyperbolic decay
			switch (m_decayType)
			{
				case EXPONENTIAL:
					value = value + m_alpha * m_b * (0 - value) * m_deltaT;
					break;
				case HYPERBOLIC:
					value = value + m_alpha * m_b * (0 - Math.pow(value, 2)) * m_deltaT;
					break;
				default:
					throw new RuntimeException();
			}

		}

		return value;
	}
}
