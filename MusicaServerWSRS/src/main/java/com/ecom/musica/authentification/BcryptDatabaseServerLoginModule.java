package com.ecom.musica.authentification;

import org.jboss.logging.Logger;
import org.jboss.security.auth.spi.DatabaseServerLoginModule;
import org.mindrot.jbcrypt.BCrypt;

public class BcryptDatabaseServerLoginModule extends DatabaseServerLoginModule {

    private Logger log = Logger.getLogger(getClass());

	@Override
	protected boolean validatePassword(String enteredPassword, String encrypted) {
		
		log.infof("enteredPassword === %s", enteredPassword);
		log.infof("encrypted === %s", encrypted);
		
		if (enteredPassword == null || encrypted == null) {
			return false;
		}

		return BCrypt.checkpw(enteredPassword, encrypted);
	}
}