def jobName_1 = 'Test1'
def gitRepoPath = 'lokeshlr/Test3'
def gitBranchName = '*/master'
def ARTIFACT_SOURCE_PATH = '**/test-output/index.html'
def S3_PROFILE_NAME = 'pennymacadmin'
def S3_BUCKET_NAME = 'pnmac-jenkins-archive'
def S3_STORAGE_CLASS_NAME = 'STANDARD'
def S3_STORAGE_REGION = 'us-east-1'

freeStyleJob(jobName_1){
description(jobName_1)
scm{
     github(gitRepoPath,gitBranchName)
    }
  steps{
    batchFile('cd %WORKSPACE%\\WebAppAutomationTest'+'\n'+'test.bat')
    dsl{external('**/test.groovy')}
  }
  triggers {
        scm('*/5 * * * *')
        githubPush()
    }
	
  publishers {
        s3(S3_PROFILE_NAME) {
            entry(ARTIFACT_SOURCE_PATH, S3_BUCKET_NAME, S3_STORAGE_REGION) {
                storageClass(S3_STORAGE_CLASS_NAME)
                noUploadOnFailure()
                useServerSideEncryption()
                }
             }
         }
}
