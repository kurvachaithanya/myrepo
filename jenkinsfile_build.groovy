pipeline{
    agent any
    stages{
        stage("clone code"){
            steps{
                println "cloning the code"
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[ url: 'https://github.com/kurvachaithanya/boxfuse-sample-java-war-hello.git']]])
                sh "ls -l"
            }
        }
        stage("build code"){
            steps{
                println "building code"
                sh "mvn clean package"
                sh "ls -l target/"
            }
        }
        stage("upload artifacts"){
            steps{
                println "uploading artifacts to s3"
                sh "echo $BUILD_NUMBER"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war S3://chaituart/"
            }
        }
        stage("deploy"){
            steps{
                println "deploy the code usin tomcat"
            }
        } 
    }
}