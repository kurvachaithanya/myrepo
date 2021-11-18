 //declarative pipeline
pipeline{
    agent any
    stages{
        stage("download artifacts"){
            steps{
                println "downloading artifacts from s3"
            }
        }
        stage("copy artifacts"){
            steps{
                println "copy artifacts to tomcat server"
            }
        }
    }
}