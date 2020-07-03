pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
        jdk 'jdk8'
    }
    stages {
        state ('Clean') {
            steps {
                echo ">>>>>>>>>>>>> Clean Start >>>>>>>>>>>>>"
                sh 'mvn clean'
                echo ">>>>>>>>>>>>> Clean   End >>>>>>>>>>>>>"
            }
        }
        stage('Install') {
            steps {
                echo '>>>>>>>>>>>>> Install Start >>>>>>>>>>>>>'
                sh 'mvn install'
                echo '>>>>>>>>>>>>> Install   End >>>>>>>>>>>>>'
            }
        }
    }
}