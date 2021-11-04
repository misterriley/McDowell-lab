package rw;

import java.util.ArrayList;
import java.util.Collection;

public class ParticleSwarm
{
	private final ArrayList<Particle> m_particles;

	public ParticleSwarm()
	{
		m_particles = new ArrayList<>();
	}

	public void add(final Particle p_particle)
	{
		m_particles.add(p_particle);
	}

	public void applyToAll(final ParticleFunction p_function)
	{
		for (final Particle p : m_particles)
		{
			p.applyFunction(p_function);
		}
	}

	public Collection<Particle> getParticles()
	{
		return m_particles;
	}

	public double getSize()
	{
		return m_particles.size();
	}
}
