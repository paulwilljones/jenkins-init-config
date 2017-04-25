import jenkins.model.*
import jenkins.install.*
import hudson.model.*
import hudson.util.*
import hudson.security.FullControlOnceLoggedInAuthorizationStrategy
import org.jenkinsci.plugins.KeycloakSecurityRealm

def instance = Jenkins.getInstance()

instance.setInstallState(InstallState.INITIAL_SETUP_COMPLETED)

def keycloakSecurityRealm = new KeycloakSecurityRealm()
def descriptorImpl = keycloakSecurityRealm.getDescriptor()

def keycloakJson = System.getenv('keycloakJson')
descriptorImpl.setKeycloakJson(keycloakJson)
instance.setSecurityRealm(keycloakSecurityRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()
