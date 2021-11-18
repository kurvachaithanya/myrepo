 //declarative pipeline
pipeline{
    agent any
    parameters{
    string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'from which branch artifacts want to deploy',) 
    string(name: 'BUILD_NUM', defaultValue: '', description: 'from which BUILD NUMBER artifacts want to deploy',)
    string(name: 'SERVER_IP', defaultValue: '', description: 'To which server artifacts want to deploy',)
    }
    stages{
        stage("download artifacts"){
            steps{
                println "Downloading artifacts from s3"
                sh "aws s3 ls"
                sh "aws s3 ls s3://chaituart"
                sh "aws s3 ls s3://chaituart/${BRANCH_NAME}/${BUILD_NUM}"
                sh "aws s3 cp s3://chaituart/${BRANCH_NAME}/${BUILD_NUM}/hello-${BUILD_NUM}.war ."
            }
        }
        stage("copy artifacts"){
            steps{
                println "copy artifacts to tomcat server"
                sh "ssh -i /tmp/mine.pem ec2@user:${SERVER_IP}"
            }
        }
    }
}