pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
        jdk 'jdk8'
    }
    stages {
        stage('Clean') {
            steps {
                echo ">>>>>>>>>>>>> Clean Start >>>>>>>>>>>>>"
                sh 'mvn clean'
                echo ">>>>>>>>>>>>> Clean   End >>>>>>>>>>>>>"
            }
        }
        stage('Package') {
            steps {
                echo ">>>>>>>>>>>>> Package Start >>>>>>>>>>>>>"
                sh "mvn package"
                echo ">>>>>>>>>>>>> Package   End >>>>>>>>>>>>>"
            }
        }
        stage('Build Docker Image') {
            steps {
                echo '>>>>>>>>>>>>> Build Docker Image Start >>>>>>>>>>>>>'
                script {
                    def version = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
                    def targetGroupId= sh script: 'mvn help:evaluate -Dexpression=project.groupId -q -DforceStdout', returnStdout: true
                    def targetArtifactId = sh script: 'mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout', returnStdout: true
                }
                echo 'project version:'
                echo ${version}
                echo '>>>>>>>>>>>>> Build Docker Image   End >>>>>>>>>>>>>'
            }
        }
    }
}