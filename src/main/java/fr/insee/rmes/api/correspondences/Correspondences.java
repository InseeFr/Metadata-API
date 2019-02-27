package fr.insee.rmes.api.correspondences;

import java.util.ArrayList;
import java.util.List;

public class Correspondences {
	
	private List<Correspondence> correspondences = new ArrayList<Correspondence>();
	
	public Correspondences() {

	}

	public List<Correspondence> getCorrespondences() {
		return correspondences;
	}

	public void setCorrespondences(List<Correspondence> correspondences) {
		this.correspondences = correspondences;
	}
	
	

}
