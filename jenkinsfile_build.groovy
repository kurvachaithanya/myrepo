pipeline{
    agent any
    environment {
        BRANCH = "${env.BRANCH_NAME}"
    }
    stages{
        stage("clone code"){
            steps{
                println "cloning the code"
                //println "${BRANCH}"
                sh "ls -l"
                sh "ls -lart ./*"
                git branch: "${BRANCH_NAME}",
                url: 'https://github.com/kurvachaithanya/boxfuse-sample-java-war-hello.git'
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
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://chaituart/${BRANCH}/${BUILD_NUMBER}"
            }
        }
        stage("deploy"){
            steps{
                println "deploy the code usin tomcat"
            }
        } 
    }
}