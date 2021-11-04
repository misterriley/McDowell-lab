package rw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HistogramPanel extends JPanel
{
	private static final int	TICK_LENGTH				= 70;
	private static final int	X_AXIS_BUFFER			= 10;
	private static final int	MEAN_STRING_Y_OFFSET	= 30;
	private static final int	MEAN_STRING_X_OFFSET	= 5;
	private static final int	CHARS_IN_MEAN_DECIMAL	= 8;
	private static final int	BASE_Y_OFFSET			= 45;
	private static final int	GRAPH_X_BUFFER			= 100;

	/**
	 *
	 */
	private static final long serialVersionUID = -1190775272443244256L;

	private static String firstNChars(final String p_string, final int p_n)
	{
		if (p_string.length() <= p_n)
		{
			return p_string;
		}

		return p_string.substring(0, p_n);
	}

	private static int getLocation(final double p_x, final int p_zeroLoc, final int p_oneLoc)
	{
		return p_zeroLoc + (int) (p_x * (p_oneLoc - p_zeroLoc));
	}

	private double m_scale = 1;

	// Count the occurrence of 26 letters
	private Histogram m_histogram;

	/** Override getPreferredSize */

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(850, (int) (850 / 1.618));
	}

	/** Set the count and display histogram */

	public void showHistogram(final Histogram p_histogram)
	{
		m_histogram = p_histogram;
		repaint();
	}

	/** Paint the histogram */

	@Override
	protected void paintComponent(final Graphics g)
	{
		super.paintComponent(g);

		if (m_histogram == null)
		{
			return; // No display if count is null
		}

		g.setColor(Color.GRAY);

		// Find the panel size and bar width and interval dynamically
		final int width = getWidth();
		final int height = getHeight();
		final int interval = width - GRAPH_X_BUFFER;
		final int individualWidth = interval / m_histogram.getCounts().length;

		int maxCount = 0;

		for (int i = 0; i < m_histogram.getCounts().length; i++)
		{
			if (maxCount < m_histogram.getCounts()[i])
			{
				maxCount = m_histogram.getCounts()[i];
			}
		}

		while (m_scale < maxCount)
		{
			m_scale *= 2;
		}

		// x is the start position for the first bar in the histogram
		int x = GRAPH_X_BUFFER / 2;

		for (int i = 0; i < m_histogram.getCounts().length; i++)
		{
			// Find the bar height
			final int barHeight =

				(int) (m_histogram.getCounts()[i] / m_scale * (height - 2 * BASE_Y_OFFSET));

			// Display a bar (i.e. rectangle)
			g.fillRect(x, height - BASE_Y_OFFSET - barHeight, individualWidth, barHeight);

			// Move x for displaying the next character
			x += individualWidth;
		}

		g.setColor(Color.BLACK);
		// Draw a horizontal base line
		g.drawLine(X_AXIS_BUFFER, height - BASE_Y_OFFSET, width - X_AXIS_BUFFER, height - BASE_Y_OFFSET);
		g
			.drawLine(
				GRAPH_X_BUFFER / 2,
				height - BASE_Y_OFFSET - TICK_LENGTH / 2,
				GRAPH_X_BUFFER / 2,
				height - BASE_Y_OFFSET + TICK_LENGTH / 2);
		g.drawLine(x, height - BASE_Y_OFFSET - TICK_LENGTH / 2, x, height - BASE_Y_OFFSET + TICK_LENGTH / 2);

		g.setColor(Color.RED);
		final int meanLoc = getLocation(m_histogram.getMean(), GRAPH_X_BUFFER / 2, x);
		g.drawLine(meanLoc, BASE_Y_OFFSET / 2, meanLoc, height - BASE_Y_OFFSET / 2);
		g
			.drawString(
				Messages.getString("HistogramPanel.0") //$NON-NLS-1$
					+ firstNChars(String.valueOf(m_histogram.getMean()), CHARS_IN_MEAN_DECIMAL),meanLoc + MEAN_STRING_X_OFFSET,
				MEAN_STRING_Y_OFFSET);
	}
}
