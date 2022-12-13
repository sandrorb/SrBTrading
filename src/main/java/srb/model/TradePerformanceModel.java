package srb.model;

public class TradePerformanceModel {

	private Long id;
	
	int numOfTrade;
	
	double numOpPorMes;
	
	double probabilidadeAcertar;
	
	double payoff;
	
	double risco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumOfTrade() {
		return numOfTrade;
	}

	public void setNumOfTrade(int numOfTrade) {
		this.numOfTrade = numOfTrade;
	}

	public double getNumOpPorMes() {
		return numOpPorMes;
	}

	public void setNumOpPorMes(double d) {
		this.numOpPorMes = d;
	}

	public double getProbabilidadeAcertar() {
		return probabilidadeAcertar;
	}

	public void setProbabilidadeAcertar(double probabilidadeAcertar) {
		this.probabilidadeAcertar = probabilidadeAcertar;
	}

	public double getPayoff() {
		return payoff;
	}

	public void setPayoff(double payoff) {
		this.payoff = payoff;
	}

	public double getRisco() {
		return risco;
	}

	public void setRisco(double d) {
		this.risco = d;
	}
	

}
