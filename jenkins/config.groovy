import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*

def instance = Jenkins.getInstance()
def cloudName = 'kubernetes'

// Either create a new config if it doesn't exist or use the existing one
KubernetesCloud kubernetes = instance.getCloud(cloudName) ?: new KubernetesCloud(cloudName)

kubernetes.setServerUrl("https://kubernetes.default.svc.cluster.local")
kubernetes.setNamespace("default")
// Change to your local jenkins URL, note that the format starts with service-name.namespace
kubernetes.setJenkinsUrl("http://jenkins.default.svc.cluster.local")

if (!instance.getCloud(cloudName)) {
  instance.clouds.add(kubernetes)
}

instance.save()
