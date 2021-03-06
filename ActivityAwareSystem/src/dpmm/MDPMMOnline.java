package dpmm;

import java.util.Random;

import com.datumbox.common.dataobjects.Dataset;
import com.datumbox.common.dataobjects.Record;
import com.datumbox.common.utilities.RandomValue;
import com.datumbox.configuration.MemoryConfiguration;
import com.datumbox.framework.machinelearning.clustering.GaussianDPMM;
import com.datumbox.framework.machinelearning.clustering.MultinomialDPMM;

public class MDPMMOnline {
	MultinomialDPMM Model;
	String version;
    String ModelName ="";
    
    public MDPMMOnline(String path, String modelName) {
		version = path;
	    ModelName = modelName;
	    lodaModel();
    }
	
	public int predict(Object[] feature, String label){
		MemoryConfiguration memoryConfiguration = new MemoryConfiguration();
		Dataset Instance = new Dataset();
		
		Instance.add(Record.newDataVector(feature, label));
		
		Model.predict(Instance);
		Record r = Instance.get(0);
		
		int predictId = Integer.valueOf(String.valueOf(r.getYPredicted()));
		
		System.out.println("It's predictes as "+predictId);
		
		return predictId;
	}

	public void lodaModel() {
	    RandomValue.randomGenerator = new Random(42); 
	    MemoryConfiguration memoryConfiguration = new MemoryConfiguration();
	                    
	    Model = new MultinomialDPMM(ModelName);
	    //Model.setDBname(ModelName);
	    Model.getDBname();
	    Model.setMemoryConfiguration(memoryConfiguration);
	}
}
