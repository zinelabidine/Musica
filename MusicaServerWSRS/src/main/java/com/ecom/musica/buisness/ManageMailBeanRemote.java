package com.ecom.musica.buisness;

import javax.ejb.Remote;

@Remote
public interface ManageMailBeanRemote {
    public void sendMail(String encodedFile) throws Exception;
}
