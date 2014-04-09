/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seriesupdater.information;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import seriesupdater.serialization.EOF;

/**
 *
 * @author kirrie
 */
public class Profile implements Externalizable {
    
    private static final long serialVersionUID = 912804676578087866L;
    
    class Episode {
        String title,numbering;
        boolean watched;                                
    }
    
    private String profileName;    
    ObservableList<Pair<Date, Episode>> profileInfo;
    
    public Profile() {}
    
    public Profile(String profileName) {
        this.profileName = profileName;        
    }
    
    @Override
    public void writeExternal(java.io.ObjectOutput out) throws IOException {  
        try {
            out.writeUTF(profileName);
            if (profileInfo != null) {
                 for (Pair<Date,Episode> pair : profileInfo) {
                     out.writeObject(pair.getKey());
                     out.writeObject(pair.getValue());
                 }
            }
            out.writeObject(new EOF());
        } finally {
            out.close();
        }
    }
    
    @Override
    public void readExternal(java.io.ObjectInput in) throws IOException, ClassNotFoundException {
        try {
            if (profileInfo == null) {
                profileInfo = FXCollections.observableList(new ArrayList<Pair<Date,Episode>>());
            } else {
                profileInfo.clear();
            }
            profileName = in.readUTF();

            while (true) {            
                Object o = in.readObject();

                if (o instanceof EOF) break;

                Date date = (Date)o;
                Episode episode = (Episode)in.readObject();        
                Pair<Date,Episode> pair = new Pair(date,episode); 
                profileInfo.add(pair);
            }
        } finally {
            in.close();
        }
        
    }

}
