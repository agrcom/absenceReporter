// Should be node ('linux') but for different strange reasons it fails on more nodes
// so let use one well-known working one
node ('trs-prod-copy') {

    stage('Checkout'){
        sh "git init"

		checkout scm
	}

	stage ('Build and test'){

	    // Maven doesn't work without JDK so let set JAVA_HOME to allow it find JDK installation.
	    env.JAVA_HOME="${tool 'JDK8'}"

        def mvnHome = tool 'maven'
        sh "${mvnHome}/bin/mvn -B clean package"

        // export sonar measures
        sh "${mvnHome}/bin/mvn -B sonar:sonar"
    }
}