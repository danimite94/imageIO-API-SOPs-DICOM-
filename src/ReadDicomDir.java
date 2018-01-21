import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;

import fr.apteryx.imageio.dicom.DataSet;
import fr.apteryx.imageio.dicom.FileSet; 
import fr.apteryx.imageio.dicom.FileSet.Directory;
import fr.apteryx.imageio.dicom.Plugin;

public class ReadDicomDir {

    Vector<Atributes> atributosExames;
    
    
    public ReadDicomDir(){
        Plugin.setLicenseKey("NM73KIZUPKHLFLAQM5L0V9U"); 
        atributosExames = new Vector<Atributes>();     
    }
    
    public Vector<Atributes> leDirectorio(String path, Vector<Atributes> atributosExames) throws Exception{
    	File file = new File(path);
		
		if(!file.exists() && !file.isDirectory()){
			System.out.println("File doesnt exist, check your path");
			JOptionPane.showMessageDialog(null, "Non existent file!","Warning", JOptionPane.WARNING_MESSAGE);
		}
    	try{
    		FileSet fileset = new FileSet(file, null);
    		
    		Directory RD = fileset.getRootDirectory();
        	
        	for(int i = 0; i < RD.getNumRecords(); i ++){
        		DataSet patient = RD.getRecord(i).getAttributes();
        		Directory LL1 = RD.getRecord(i).getLowerLevelDirectory();
        		for(int j = 0; j < LL1.getNumRecords(); j++){
        			DataSet study = LL1.getRecord(j).getAttributes();
        			Directory LL2 = LL1.getRecord(j).getLowerLevelDirectory();
        			for(int k = 0; k < LL2.getNumRecords(); k++){
        				DataSet serie = LL2.getRecord(k).getAttributes();
        				Directory LL3 = LL2.getRecord(k).getLowerLevelDirectory();
        				for(int l = 0; l < LL3.getNumRecords(); l++){
        					if(LL3.getRecord(l).getType().equals("IMAGE")){
        						DataSet image = LL3.getRecord(l).getAttributes();
            					Atributes DataSets = new Atributes(patient, study, serie, image);
            					atributosExames.add(DataSets);
        					}
        				}
        			}
        		}	
        	}
    	}
    	catch(IOException e1){
    		System.out.println("Error while reading the file");
    		JOptionPane.showMessageDialog(null, "Error reading DICOMDIR","Warning", JOptionPane.WARNING_MESSAGE);
    	}

        return atributosExames;
    }

}
