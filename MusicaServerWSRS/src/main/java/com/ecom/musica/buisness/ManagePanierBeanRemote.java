package com.ecom.musica.buisness;

import javax.ejb.Remote;

@Remote
public interface ManagePanierBeanRemote {
   
    public void addToPanier(int clientId,int instrumentId,int quantite) throws Exception;

}
