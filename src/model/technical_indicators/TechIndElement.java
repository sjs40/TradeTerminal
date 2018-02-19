package model.technical_indicators;

import org.jfree.data.time.Minute;

import java.util.Date;

/**
 * This is the interface for a Technical Indicatior element. The two
 * main methods here are getX and getY, with getDate only used to
 * compare in order to trim the technical indicator data to fit
 * the length of the price data.
 */
public interface TechIndElement {

  Minute getX();

  double getY();

  Date getDate();

}
