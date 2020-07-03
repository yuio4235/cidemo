pipeline {
    agent docker {
    }
    tools {
        maven 'maven3.6.3'
        jdk 'jdk8'
        dockerTool 'docker'
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
            agent { dockerfile true }
            steps {
                echo '>>>>>>>>>>>>> Build Docker Image Start >>>>>>>>>>>>>'
                script {
                    def version = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
                    def targetGroupId= sh script: 'mvn help:evaluate -Dexpression=project.groupId -q -DforceStdout', returnStdout: true
                    def targetArtifactId = sh script: 'mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout', returnStdout: true
                    def finalName = sh script: 'mvn help:evaluate -Dexpression=project.build.finalName -q -DforceStdout', returnStdout: true
                    echo "project version: ${version}"
                    echo "targetGroupId: ${targetGroupId}"
                    echo "targetArtifactId: ${targetArtifactId}"
                    echo "finalName: ${finalName}"
                    sh script: "docker build --build-arg JAR_FILE=./target/${finalName} -t ${targetGroupId}.${targetArtifactId}:${version} ."
                }
                echo '>>>>>>>>>>>>> Build Docker Image   End >>>>>>>>>>>>>'
            }
        }
    }
}