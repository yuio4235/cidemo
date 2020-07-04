pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
        dockerTool 'docker'
    }
    stages {
        stage('Cleanup Workspace') {
            steps {
                echo ">>>>>>>>>>>>> Clean Start >>>>>>>>>>>>>"
                sh 'mvn clean'
                echo ">>>>>>>>>>>>> Clean   End >>>>>>>>>>>>>"
            }
        }
        stage('Packaging Artifacts') {
            steps {
                echo ">>>>>>>>>>>>> Package Start >>>>>>>>>>>>>"
                sh "mvn package"
                echo ">>>>>>>>>>>>> Package   End >>>>>>>>>>>>>"
            }
        }
        stage('Building Docker Iamge') {
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
        stage('Deploy Docker Image to AliCloud') {
            steps {
                echo '>>>>>>>>>>>>> Start to Deploy Image to AliCloud >>>>>>>>>>>>>>'
                script {
                    def version = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
                    def targetGroupId= sh script: 'mvn help:evaluate -Dexpression=project.groupId -q -DforceStdout', returnStdout: true
                    def targetArtifactId = sh script: 'mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout', returnStdout: true
                    def finalName = sh script: 'mvn help:evaluate -Dexpression=project.build.finalName -q -DforceStdout', returnStdout: true
                    sh script: 'docker login --username=yuio4235@aliyun.com --password=Xiaodan@#2208 registry.cn-qingdao.aliyuncs.com'
                    sh script: "docker tag ${targetGroupId}.${targetArtifactId}:${version} registry.cn-qingdao.aliyuncs.com/gstech/${targetArtifactId}:${version}"
                    sh script: "docker push registry.cn-qingdao.aliyuncs.com/gstech/${targetArtifactId}:${version}"
                }
                echo '>>>>>>>>>>>>> Finish to Deploy Image to AliCloud >>>>>>>>>>>>>'
            }
        }
        stage('Deploy Projects to Kubernetes Cluster') {
            steps {
                echo '>>>>>>>>>>>>> Start to Deploy Projects to Kubernetes >>>>>>>>>>>>>>'
                echo '>>>>>>>>>>>>> Finish to Deploy Projects to Kubernetes >>>>>>>>>>>>>'
            }
        }
        stage('Testing') {
            steps {
                echo '>>>>>>>>>>>>> Start Testing >>>>>>>>>>>>>>'
                echo '>>>>>>>>>>>>> Finish Testing >>>>>>>>>>>>>'
            }
        }
    }
}