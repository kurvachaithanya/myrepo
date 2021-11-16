pipeline{
    agent any
    stages{
        stage("clone code"){
            steps{
                println "cloning the code"
                sh "ls -l"
            }
        }
        stage("build code"){
            steps{
                println "building code"
            }
        }
        stage("upload artifacts"){
            steps{
                println "uploading artifacts to s3"
            }
        }
        stage("deploy"){
            steps{
                println "deploy the code usin tomcat"
            }
        } 
    }
}