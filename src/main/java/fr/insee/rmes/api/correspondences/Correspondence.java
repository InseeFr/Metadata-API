package fr.insee.rmes.api.correspondences;

import java.util.List;

public class Correspondence {

	private Poste posteSource;
	private List<Poste> postesCibles;

	public Correspondence() {

	}
	
	public Correspondence(Poste k, List<Poste> v) {
		
		this.posteSource = k;
		this.postesCibles = v;
		
	}

	public Poste getPosteSource() {
		return posteSource;
	}

	public void setPosteSource(Poste posteSource) {
		this.posteSource = posteSource;
	}

	public List<Poste> getPostesCibles() {
		return postesCibles;
	}

	public void setPostesCibles(List<Poste> postesCibles) {
		this.postesCibles = postesCibles;
	}

}
