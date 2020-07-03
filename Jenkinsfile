pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
        jdk 'jdk8'
    }
    stages {
        stage('Build') {
            steps {
                echo '>>>>>>>>>>>>> test mvn >>>>>>>>>>>>>'
                sh 'mvn --version'
            }
        }
    }
}