/** Below  are the parameters need to define by user*/ 
def JOB_NAME = 'Test1'
def GIT_REPO_PATH = 'lokeshlr/Test3'
def GIT_BRANCH_NAME = '*/master'
def ARTIFACT_SOURCE_PATH = '**/test-output/index.html'
def S3_PROFILE_NAME = 'pennymacadmin'
def S3_BUCKET_NAME = 'pnmac-jenkins-archive/${JOB_NAME}-BUILD_ID_${BUILD_ID}'
def S3_STORAGE_CLASS_NAME = 'STANDARD'
def S3_STORAGE_REGION = 'us-east-1'
def GROOVY_SCRIPT_PATH = '**/test.groovy'
def BATCH_FILE_PATH = 'cd %WORKSPACE%\\WebAppAutomationTest'+'\n'+'test.bat'
def SCM_TRIGGER = '*/5 * * * *'

freeStyleJob(JOB_NAME){
description(JOB_NAME)
scm{
     github(GIT_REPO_PATH,GIT_BRANCH_NAME)
    }
  steps{
    batchFile(BATCH_FILE_PATH)
    dsl{external(GROOVY_SCRIPT_PATH)}
  }
  triggers {
        scm(SCM_TRIGGER)
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
