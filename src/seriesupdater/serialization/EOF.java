/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seriesupdater.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 *
 * @author kirrie
 */
public class EOF implements Externalizable {
    
    public EOF() {}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {        
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {        
    }
    
}
