package rw;

public class Histogram
{
	public static Histogram convertToHistogram(
		final ParticleSwarm p_swarm,
		final double p_min,
		final double p_max,
		final int p_numBins)
	{
		final Histogram ret = new Histogram(p_min, p_max, p_numBins);

		double sum = 0;
		int count = 0;
		for (final Particle p : p_swarm.getParticles())
		{
			final double d = (Double) p.getValue();
			sum += d;
			count++;
			final int index = ret.findBin(d);
			ret.m_counts[index]++;
		}

		ret.setMean(sum / count);

		return ret;
	}

	private final int[]		m_counts;
	private final double	m_min;
	private final double	m_max;
	private final double	m_binWidth;
	private double			m_mean;

	private Histogram(final double p_min, final double p_max, final int p_numBins)
	{
		m_counts = new int[p_numBins];

		m_min = p_min;
		m_max = p_max;
		m_binWidth = (p_max - p_min) / p_numBins;
	}

	public int[] getCounts()
	{
		return m_counts;
	}

	public double getMean()
	{
		return m_mean;
	}

	private int findBin(final double p_value)
	{
		if (p_value == m_max)
		{
			return m_counts.length - 1;
		}

		return (int) ((p_value - m_min) / m_binWidth);
	}

	private void setMean(final double p_mean)
	{
		m_mean = p_mean;
	}
}
