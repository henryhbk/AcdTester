package com.ibm.nlp.model;

/**
 * The Class AAPProblem.
 *
 * @author henry.feldman@ibm.com
 */
public class AAPProblem {

	/** The problem title. */
	private String problemTitle;
	
	/** The cui. */
	private String cui;
	
	/** The severe. */
	private Boolean severe;
	
	/** The plan. */
	private String plan;
	
	
	
	
	/**
	 * Instantiates a new AAP problem.
	 *
	 * @param problemTitle the problem title
	 * @param cui the cui
	 * @param severe the severe
	 * @param plan the plan
	 */
	public AAPProblem(String problemTitle, String cui, Boolean severe, String plan) {
		super();
		this.problemTitle = problemTitle;
		this.cui = cui;
		this.severe = severe;
		this.plan = plan;
	}




	/**
	 * Gets the problem title.
	 *
	 * @return the problem title
	 */
	public String getProblemTitle() {
		return problemTitle;
	}




	/**
	 * Gets the cui.
	 *
	 * @return the cui
	 */
	public String getCui() {
		return cui;
	}




	/**
	 * Gets the severe.
	 *
	 * @return the severe
	 */
	public Boolean getSevere() {
		return severe;
	}








	/**
	 * Gets the plan.
	 *
	 * @return the plan
	 */
	public String getPlan() {
		return plan;
	}




	/**
	 * Sets the problem title.
	 *
	 * @param problemTitle the new problem title
	 */
	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}




	/**
	 * Sets the cui.
	 *
	 * @param cui the new cui
	 */
	public void setCui(String cui) {
		this.cui = cui;
	}




	/**
	 * Sets the severe.
	 *
	 * @param severe the new severe
	 */
	public void setSevere(Boolean severe) {
		this.severe = severe;
	}





	/**
	 * Sets the plan.
	 *
	 * @param plan the new plan
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}




	/**
	 * Instantiates a new AAP problem.
	 */
	public AAPProblem() {
	}




	/**
	 * To string.
	 *
	 * @return the string
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AAPProblem [problemTitle=" + problemTitle + ", cui=" + cui + ", severe=" + severe + ", begin=" 
				+  ", plan=" + plan + "]";
	}

}
