 //declarative pipeline
pipeline{
    agent any
    parameters{
    string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'from which branch artifacts want to deploy',) 
    string(name: 'BUILD_NUM', defaultValue: 'master', description: 'from which BUILD NUMBER artifacts want to deploy',)
    }
    stages{
        stage("download artifacts"){
            steps{
                println "downloading artifacts from s3"
                sh "aws s3 ls"
                sh "aws s3 ls s3://chaituart"
                sh "aws s3 ls s3://chaituart/${BRANCH_NAME}/${BUILD_NUM}"
            }
        }
        stage("copy artifacts"){
            steps{
                println "copy artifacts to tomcat server"
            }
        }
    }
}