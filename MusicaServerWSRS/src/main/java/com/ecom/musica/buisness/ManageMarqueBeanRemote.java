package com.ecom.musica.buisness;

import java.util.List;
import javax.ejb.Remote;
import com.ecom.musica.entities.Marque;

@Remote
public interface ManageMarqueBeanRemote {
	public List<Marque> getAllMarques() ;
	public int getMarqueId(String marqueLibelle) throws Exception;
}
