package pe.entity.microservice.customer.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KpiResponseObject {

	/**
	 * average
	 */
	@JsonProperty("average")
	private double average;

	/**
	 * standard deviation
	 */
	@JsonProperty("standard_deviation")
	private double standardDeviation;

	/**
	 * Kpi Response Object
	 */
	public KpiResponseObject() {

	}

	/**
	 * Kpi Response Object
	 * 
	 * @param average           average
	 * @param standardDeviation standard deviation
	 */
	public KpiResponseObject(double average, double standardDeviation) {
		super();
		this.average = average;
		this.standardDeviation = standardDeviation;
	}

	/**
	 * Get Average
	 * 
	 * @return average
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * Set Average
	 * 
	 * @param average average
	 */
	public void setAverage(double average) {
		this.average = average;
	}

	/**
	 * Get Standard Deviation
	 * 
	 * @return standard deviation
	 */
	public double getStandardDeviation() {
		return standardDeviation;
	}

	/**
	 * Set Standard Deviation
	 * 
	 * @param standardDeviation standard deviation
	 */
	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

}
