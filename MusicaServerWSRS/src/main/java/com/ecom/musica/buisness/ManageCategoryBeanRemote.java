package com.ecom.musica.buisness;

import java.util.List;
import javax.ejb.Remote;
import com.ecom.musica.entities.Categorie;

@Remote
public interface ManageCategoryBeanRemote {
	public List<Categorie> getAllCategories() ;

}
