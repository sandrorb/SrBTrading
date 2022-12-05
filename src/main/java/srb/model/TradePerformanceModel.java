package srb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TradePerformanceModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	int numOfTrade;
	
	@NotNull
	double numOpPorMes;
	
	@NotNull
	double probabilidadeAcertar;
	
	@NotNull
	double payoff;
	
	@NotNull
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
